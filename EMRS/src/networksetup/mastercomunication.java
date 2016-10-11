package networksetup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Timer;

public class mastercomunication {
	//network address default
	final static String ACCESS_CODE = "emr5187";
	final static String  BROADCAST_ADDR = "255.255.255.255";
    final static int PORT = 5187;
    final static int SERVER = 1;
    final static int CLIENT = 2;
    //network setup
    final static int UNKNOWN=0;
    final static int CLIENT_CONNECTED = 1;
    final static int CLIENT_WAITTING = 2;
    final static int CLIENT_NOACTION = 3;
    //date time format
    final static DateFormat DATE_FORMAT =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    //message
    public static final transient int ASK_SERVER = 1;
	public static final transient int SERVER_CLOSE = 2;
	public static final transient int NEW_SERVER = 3;
	public static final transient int LEAVE_SERVER = 4;
	public static final transient int SERVER_RESPONSE = 5;
	public static final transient int CLIENT_REQUEST_JOIN = 6;
	public static final transient int SERVER_ACCEPT_JOIN = 7;
	
    private MulticastSocket mSocket;
    
    private Thread listener;
    boolean islisten;//for stopping the thread
    private Timer askserver;
    
    private NetworkObject owner;
    private List<NetworkObject> guests;
    private NetworkObject server;
    private int askcount;
	private int clientstatus;
    
    public mastercomunication() throws IOException{
    	InetAddress addr = InetAddress.getByName(this.BROADCAST_ADDR);
    	System.out.println("creating socket");
    	mSocket = new MulticastSocket(PORT);
    	mSocket.setLoopbackMode(true);
    	mSocket.setTimeToLive(2);
    	askcount = 0;
    	islisten=true;
    	clientstatus = this.CLIENT_NOACTION;
    	owner = new NetworkObject();
    	try{
    		System.out.println("setup broadcasting network......");
    		mSocket.setBroadcast(true);
    		System.out.println("setup successful!");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	System.out.println("start listenning income message.....");
    	listener = new Thread(new Runnable(){
			@Override
			public void run() {
				
				// TODO Auto-generated method stub
				while(islisten==true){
					try {
						HostListener();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
    	});
    	listener.start();
    	System.out.println("asking server.....");
    	askserver = new Timer(3000,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(askcount<3){
					AskExistServer();
					askcount++;
				}else if(owner.getType() == UNKNOWN) {
					//askcount=0;
					try {
						owner = new NetworkObject(SERVER,0);
						System.out.println("Server Created!");
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}else if(owner.getType() == SERVER && guests.size()==0){
					System.out.println("No client so continue asking");
					askserver.setDelay(10000);
					askcount=0;
				}
				
				
			}	
    	});
    	askserver.start();
    }
    private void AskExistServer(){
    	try {
    		//input: accesscode, command code, data
			message msg = new message(ACCESS_CODE,ASK_SERVER,null);
			HostSend(msg,InetAddress.getByName(this.BROADCAST_ADDR));
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public void HostSend(message msg,InetAddress addr) throws UnknownHostException, InterruptedException {
        // Open a new DatagramSocket, which will be used to send the data.
        try{
        	//pack message
        	ByteArrayOutputStream out = new ByteArrayOutputStream();
        	ObjectOutputStream os = new ObjectOutputStream(out);
        	os.writeObject(msg);
            DatagramPacket msgPacket = new DatagramPacket(out.toByteArray(),
            out.toByteArray().length,addr, PORT);
            //send message
            mSocket.send(msgPacket);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void HostListener()throws IOException{
        byte[] buf = new byte[512];
        // Receive the information and print it.
        DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
        mSocket.receive(msgPacket);
        message msg;
        ByteArrayInputStream in = new ByteArrayInputStream(buf);
        ObjectInputStream input = new ObjectInputStream(in);
        try {
        	msg = (message)input.readObject();
        	AnalizeMSG(msg,msgPacket.getAddress());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void AnalizeMSG(message msg,InetAddress ipfrom){
    	//TODO
    	try {
			if(InetAddress.getLocalHost().getHostName().equals(((InetAddress)msg.getData()).getHostName())==false){
				if(msg.getAcceptcode().equals(this.ACCESS_CODE)==true){
					switch(msg.getCommand()){
						case ASK_SERVER:
							if(owner.getType()==this.SERVER){
								int isexistclient=0;
								for(NetworkObject e: this.guests){
									if(e.getIpaddrr().getHostName().equals(ipfrom.getHostName())){
										isexistclient = 1;
										break;
									}
								}
								if(isexistclient == 0)
										this.HostSend(new message(this.ACCESS_CODE,this.SERVER_RESPONSE,this.owner),InetAddress.getByName(this.BROADCAST_ADDR));
							}
							break;
						case NEW_SERVER:
							this.owner.setType(this.UNKNOWN);
							askserver.start();
							break;
						case LEAVE_SERVER:
							if(owner.getType()==this.SERVER){
								NetworkObject in = (NetworkObject) msg.getData();
								for(NetworkObject e : this.guests){
									if(e.getPriority()== in.getPriority()){
										this.guests.remove(e);
										//TODO
										break;
									}
								}
							}
							break;
						case SERVER_RESPONSE:
							if(owner.getType()==this.UNKNOWN){
								this.server=(NetworkObject)msg.getData();
								this.HostSend(new message(this.ACCESS_CODE, this.CLIENT_REQUEST_JOIN,this.owner),ipfrom);
							}
							break;
						case CLIENT_REQUEST_JOIN:
							if(owner.getType()==this.SERVER){
								NetworkObject in = (NetworkObject) msg.getData();
								this.guests.add(in);
								this.HostSend(new message(this.ACCESS_CODE, this.SERVER_ACCEPT_JOIN,this.guests.size()),ipfrom);
								this.askserver.stop();
							}
							break;
						case SERVER_ACCEPT_JOIN:
							if(owner.getType()==this.UNKNOWN){
								this.owner.setType(this.CLIENT);
								this.owner.setPriority((Integer)msg.getData());
								this.HostSend(new message(this.ACCESS_CODE, this.CLIENT_REQUEST_JOIN,this.owner),ipfrom);
								this.askserver.stop();
							}
							break;
						case SERVER_CLOSE:
							if(owner.getType()==this.CLIENT && owner.getPriority()==1){
								owner.setType(this.SERVER);
								owner.setPriority(0);
								this.server=null;
							}
							break;
					}
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @SuppressWarnings("deprecation")
	public void close() throws UnknownHostException, IOException{
    	try {
    		islisten=false;
			this.listener.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
