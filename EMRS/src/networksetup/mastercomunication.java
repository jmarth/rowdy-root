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
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	public static final transient int OLDER_SERVER = 3;
	public static final transient int LEAVE_SERVER = 4;
	public static final transient int SERVER_RESPONSE = 5;
	public static final transient int CLIENT_REQUEST_JOIN = 6;
	public static final transient int SERVER_ACCEPT_JOIN = 7;
	public static final transient int NO_ACTION=8;
	protected static final int exspectresponse = 0;
	
	
    private MulticastSocket mSocket;
    
    private Thread mlistener;
    private Timer askquestion;
    
    private NetworkObject owner;
    private boolean islisten;
    private int askcount;
	private int expectresponse;
	private InetAddress toip;
	private message currentmsg;
    
    public mastercomunication() throws IOException{
    	InetAddress addr = InetAddress.getByName(this.BROADCAST_ADDR);
    	System.out.println("creating socket");
    	mSocket = new MulticastSocket(PORT);
    	mSocket.setLoopbackMode(true);
    	mSocket.setTimeToLive(2);
    	askcount = 0;
    	owner = null;
    	islisten = true;
    	try{
    		System.out.println("setup broadcasting network......");
    		mSocket.setBroadcast(true);
    		System.out.println("setup successful!");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	System.out.println("start listenning income message.....");
    	mlistener = new Thread(new Runnable(){
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
    	mlistener.start();
    	startsetup();
    	askquestion = new Timer(3000,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("asking question code "+ currentmsg.getCommand());
				// TODO Auto-generated method stub
				AskQuesttion();
				askcount++;
				if(askcount >=3){
					//create server because no server response
					if(owner==null && exspectresponse == SERVER_RESPONSE) {
						try {
							owner = new server(SERVER,0);
							askquestion.setDelay(10000);
							System.out.println("Server Created!");
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					// from client side, in the middle of setting connection, server not response anymore 	
					}else if(exspectresponse == SERVER_ACCEPT_JOIN){
						startsetup();
					}
				}
			}	
    	});
    	askquestion.start();
    }
    private void AskQuesttion(){
    	try {
    		//input: accesscode, command code, data
			message msg = new message(ACCESS_CODE,ASK_SERVER,this.owner.getIpaddrr());
			//this.actionstatus=action;
			HostSend(msg,toip);
			
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
    		toip = ipfrom;
    		System.out.println("receiving message from "+ ipfrom.getHostAddress());
			if(InetAddress.getLocalHost().getHostName().equals(ipfrom.getHostAddress())==false){
				//check the message is for the program
				if(msg.getAcceptcode().equals(this.ACCESS_CODE)==true){
					switch(msg.getCommand()){
						case ASK_SERVER://server will get this message
							System.out.println("receiving asking server from "+ ipfrom.getHostName());
							//only server proccess this message
							if(owner!=null && owner.getType()==this.SERVER){
								server sv = (server) owner;
								int i;
								for(i =0; i< sv.getClientlist().size();i++){
									if(sv.getClientlist().get(i).getHostName().equals(ipfrom.getHostName())){
										break;
									}
								}
								if(i==sv.getClientlist().size()){
									HostSend(new message(this.ACCESS_CODE,this.SERVER_RESPONSE,owner),ipfrom);
								}
							}
							
							break;
						case OLDER_SERVER:
							this.askquestion.stop();
							System.out.println("receiving older server");
							this.owner= new client((server)msg.getData());
							setaction(this.SERVER_ACCEPT_JOIN,ipfrom,
									new message(this.ACCESS_CODE,this.CLIENT_REQUEST_JOIN,ipfrom));
							this.askquestion.start();
							break;
						case LEAVE_SERVER://TODO
							System.out.println("receiving leaving server");
//							if(owner.getType()==this.SERVER){
//								NetworkObject in = (NetworkObject) msg.getData();
//								for(NetworkObject e : this.guests){
//									if(e.getPriority()== in.getPriority()){
//										//this.guests.remove(e);
//										//TODO
//										break;
//									}
//								}
//							}
							break;
							//from client side
						case SERVER_RESPONSE:
							System.out.println("receiving server response");
							if(this.expectresponse == SERVER_RESPONSE && owner==null){
								askquestion.stop();
								this.owner = new client((server)msg.getData());
								setaction(this.SERVER_ACCEPT_JOIN,ipfrom,
										new message(this.ACCESS_CODE,this.CLIENT_REQUEST_JOIN,ipfrom));
								askquestion.start();
							}else if(owner.getType()==SERVER){
								NetworkObject in = (NetworkObject) msg.getData();
								if(owner.getCreateddate().before(in.getCreateddate())){
									HostSend(new message(this.ACCESS_CODE,this.OLDER_SERVER,ipfrom),ipfrom);
								}
							}
							break;
						case CLIENT_REQUEST_JOIN:
							System.out.println("receiving client request join");
							if(owner.getType()==this.SERVER){
								server sv = (server)owner;
								owner.setIpaddrr((InetAddress)msg.getData());
								sv.clientlist.add(ipfrom);
								this.HostSend(new message(this.ACCESS_CODE, this.SERVER_ACCEPT_JOIN,sv.getClientlist().size()),ipfrom);
								if(this.askquestion.isRunning())
									this.stopaskserver();
							}
							break;
						case SERVER_ACCEPT_JOIN:
							System.out.println("receiving server accept join");
							if(owner.getType()==this.UNKNOWN && this.expectresponse==SERVER_ACCEPT_JOIN){
								//TODO connect to rmi later
								this.owner.setType(this.CLIENT);
								this.owner.setIpaddrr(ipfrom);
								this.owner.setPriority((Integer)msg.getData());
								//this.HostSend(new message(this.ACCESS_CODE, this.CLIENT_REQUEST_JOIN,this.owner),ipfrom);
								this.stopaskserver();
								
							}
							break;
						case SERVER_CLOSE:
							System.out.println("receiving server close");
							if(owner.getType()==this.CLIENT && owner.getPriority()==1){
								owner.setType(this.SERVER);
								owner.setPriority(0);	
							}else{
								this.askquestion.start();
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
			this.mlistener.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void stopaskserver(){
    	this.askquestion.stop();
    	this.askquestion.setDelay(3000);
    	this.askcount=0;
    }
    private void startsetup(){
    	this.expectresponse=this.SERVER_RESPONSE;
    	this.currentmsg=new message(this.ACCESS_CODE,this.ASK_SERVER,null);
    	owner=null;
    }
    private void setaction(int expectresponse, InetAddress toip, message cmsg){
    	this.askcount=0;
    	this.expectresponse=expectresponse;
    	this.toip=toip;
    	this.currentmsg=cmsg;
    }
}
