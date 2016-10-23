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
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.Timer;

import notification.client.impclient;
import notification.client.rmiclient;
import notification.server.impserver;
import notification.server.rmiserver;

import views.HomeView;

public class mastercomunication {
	
	//rmi property
	public final static int RMI_PORT = 1099;
	//network address default
	public final static String ACCESS_CODE = "emr5187";
	public final static String  BROADCAST_ADDR = "255.255.255.255";
	public final static int PORT = 5187;
	public final static int SERVER = 1;
	public final static int CLIENT = 2;
	public final static int UDP_DATA_SIZE = 1472;
    //network setup
	public final static int UNKNOWN=0;
	public final static int CLIENT_CONNECTED = 1;
	public final static int CLIENT_WAITTING = 2;
	public final static int CLIENT_NOACTION = 3;
    //date time format
    final static DateFormat DATE_FORMAT =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    public static final transient int SERVER_NUM_RECEIVE = 4;
    //message
    //server receive
    public static final transient int ASK_SERVER = 0;
    public static final transient int LEAVE_SERVER = 1;
    public static final transient int CLIENT_REQUEST_JOIN = 2;
    public static final transient int SERVER_ACCEPT_LEAVE=3;
    //client receive
	public static final transient int SERVER_CLOSE = 4;
	public static final transient int OLDER_SERVER = 5;
	public static final transient int SERVER_RESPONSE = 6;
	public static final transient int SERVER_ACCEPT_JOIN = 7;
	public static final transient int NO_ACTION=8;
	
	
	
    private MulticastSocket mSocket;
    
    private Thread mlistener;
    private Timer askquestion;
    
    private NetworkObject owner;
    private boolean islisten;
    private int askcount;
	private int expectresponse;
	private InetAddress toip;
	private message currentmsg;
	
	private HomeView homeview;
	
	private Thread runserver;
	private Registry reg;
	
	private rmiserver rserver;
	private rmiclient rclient;
    
    public mastercomunication() throws IOException{
    	System.out.println("creating socket");
    	mSocket = new MulticastSocket(PORT);
    	mSocket.setLoopbackMode(true);
    	mSocket.setTimeToLive(2);
    	askcount = 0;
    	owner = null;
    	islisten = true;
    	expectresponse = NO_ACTION;
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
				while(islisten==true){
					try {
						HostListener();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}	
    	});
    	mlistener.start();
    	
    	askquestion = new Timer(3000,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AskQuesttion();
				if(askcount >=3 && askquestion.getDelay()==3000){
					askcount=0;
						startsetup();
						System.out.println("change delay to 10000");
						askquestion.setDelay(10000);
						System.out.println("Server Created!");
						runserver = new Thread( new Runnable(){

							@Override
							public void run() {
								// TODO Auto-generated method stub
								try {
									rserver = new impserver(owner,homeview);
									reg = LocateRegistry.createRegistry(RMI_PORT);
									reg.rebind("rmiemr", rserver);
								} catch (AccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						});
						runserver.start();
				}
				
				askcount++;
			}	
    	});
    	startsetup();
    	askquestion.start();
    }
    private void AskQuesttion(){
    	try {
    		//input: accesscode, command code, data
			HostSend(currentmsg,toip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
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
        byte[] buf = new byte[this.UDP_DATA_SIZE];
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
			e.printStackTrace();
		}
    }
    
    public void AnalizeMSG(message msg,InetAddress ipfrom){
    	//TODO
    	try {
    		toip = ipfrom;
			if(InetAddress.getLocalHost().getHostAddress().equals(ipfrom.getHostAddress())==false){
				//check the message is for the program
				if(msg.getAcceptcode().equals(this.ACCESS_CODE)==true){
					switch(msg.getCommand()){
						case ASK_SERVER://server will get this message
							System.out.println("receiving asking server from "+ ipfrom.getHostAddress());
							//only server proccess this message
							if(owner!=null && owner.getType()==this.SERVER){
								HostSend(new message(this.ACCESS_CODE,this.SERVER_RESPONSE,owner,owner.getPriority()),ipfrom);
							}
							break;
						case OLDER_SERVER:
							if(owner!=null && owner.getType()==this.SERVER){
								server sv = (server)msg.getData();
								if(owner.getCreateddate().after(sv.getCreateddate())){
									owner.setType(this.CLIENT);
									HostSend(new message(this.ACCESS_CODE,this.SERVER_CLOSE,owner,owner.getPriority()),InetAddress.getByName(this.BROADCAST_ADDR));
									askquestion.stop();
									startsetup();
									askquestion.start();
								}
								
							}
							break;
						case LEAVE_SERVER://TODO
							System.out.println("receiving leaving server");
//							if(owner.getType()==this.SERVER){
//								NetworkObject in = (NetworkObject) msg.getData();
//								for(NetworkObject e : this.guests){
//									if(e.getPriority()== in.getPriority()){
//										//this.guests.remove(e);
//										break;
//									}
//								}
//							}
							break;
							//from client side
						case SERVER_RESPONSE:
							System.out.println("receiving server response "+ipfrom.getHostAddress() );
							if(this.expectresponse == SERVER_RESPONSE && owner==null){
								askquestion.stop();
								this.owner = new client((server)msg.getData());
								setexpect(this.SERVER_ACCEPT_JOIN,ipfrom,
										new message(this.ACCESS_CODE,this.CLIENT_REQUEST_JOIN,ipfrom,owner.getPriority()));
								askquestion.start();
							}else if(owner.getType()==SERVER){
								server svo = (server)owner;
								server svi = (server)msg.getData();
								if(svo.getClient_num()< svi.getClient_num()){
									if(svo.getClient_num()==0){
										askquestion.stop();
										this.owner = new client((server)msg.getData());
										setexpect(this.SERVER_ACCEPT_JOIN,ipfrom,
												new message(this.ACCESS_CODE,this.CLIENT_REQUEST_JOIN,ipfrom,owner.getPriority()));
										askquestion.start();
									}else{
										//TODO rmi will take care of notify client cus reliable protocal
									}
									
								}else if(svo.getCreateddate().after(svi.getCreateddate())){
									if(svo.getClient_num()==0){
										askquestion.stop();
										this.owner = new client((server)msg.getData());
										setexpect(this.SERVER_ACCEPT_JOIN,ipfrom,
												new message(this.ACCESS_CODE,this.CLIENT_REQUEST_JOIN,ipfrom,owner.getPriority()));
										askquestion.start();
									}else{
										//TODO rmi will take care of notify client cus reliable protocal
									}
								}
							}
							break;
						case CLIENT_REQUEST_JOIN:
							System.out.println("receiving client request join "+ipfrom.getHostAddress());
							if(owner.getType()==this.SERVER ){
								server sv = (server)owner;
								owner.setIpaddrr((InetAddress)msg.getData());
								int prior = this.checkexistclient(ipfrom);
								if(sv.getClient_num()==prior){
									sv.getClientlist().add(ipfrom);
									sv.increaseclientnum();
									
								}
								this.HostSend(new message(this.ACCESS_CODE, this.SERVER_ACCEPT_JOIN,prior,owner.getPriority()),ipfrom);
								if(this.askquestion.isRunning())
									this.stopaskserver();
							}
							break;
						case SERVER_ACCEPT_JOIN:
							System.out.println("receiving server accept join "+ ipfrom.getHostAddress());
							if(owner.getType()==this.UNKNOWN && this.expectresponse==SERVER_ACCEPT_JOIN){
								
								try {
									client nclient = (client) owner;
									nclient.setServer((NetworkObject)msg.getData());
									Registry reg = LocateRegistry.getRegistry(nclient.getServer().getIpaddrr().getHostAddress(), this.RMI_PORT);
									rserver = (rmiserver) reg.lookup("rmiemr");
									
									this.rclient = new impclient(homeview,rserver);
									
									this.owner.setType(this.CLIENT);
									this.owner.setIpaddrr(ipfrom);
									this.owner.setPriority((Integer)msg.getData());
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (NotBoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//TODO create rmi client connection here
								this.stopaskserver();
								
							}
							break;
						case SERVER_CLOSE:
							System.out.println("receiving server close");
							if(owner!=null && owner.getType()== this.CLIENT){
								client clo = (client)owner;
								client clin = (client)msg.getData();
								if(clo.getServer().getIpaddrr().getHostAddress().equals(clin.getIpaddrr().getHostAddress())){
									askquestion.stop();
									this.startsetup();
									askquestion.start();
								}
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
  
    private void stopaskserver(){
    	this.askquestion.stop();
    	this.askquestion.setDelay(3000);
    	this.askcount=0;
    }
    private void startsetup(){
    	
    	try {
			this.toip=InetAddress.getByName(BROADCAST_ADDR);
			this.expectresponse=this.SERVER_RESPONSE;
	    	this.currentmsg=new message(this.ACCESS_CODE,this.ASK_SERVER,null,-1);
	    	owner = new server(SERVER,-1);
	    	this.askquestion.setDelay(3000);
	    	this.askcount=0;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    	
    }
    private void setexpect(int expectresponse, InetAddress toip, message cmsg){
    	this.askcount=0;
    	this.askquestion.setDelay(3000);
    	this.expectresponse=expectresponse;
    	this.toip=toip;
    	this.currentmsg=cmsg;
    }
    private int checkexistclient(InetAddress in){
    	server sv = (server) owner;
		int i;
		for(i =0; i< sv.getClientlist().size();i++){
			if(sv.getClientlist().get(i).getHostName().equals(in.getHostName())){
				return i;
			}
		}
		return i;	
    }
   
    public void close() {
    	
    	try {
    		if(owner.getType()== this.CLIENT){
	    		rclient.leaveserver();
	    	}
			System.out.println("finish closing communication");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
	public NetworkObject getOwner() {
		return owner;
	}
	public void setOwner(NetworkObject owner) {
		this.owner = owner;
	}
	public HomeView getHomeview() {
		return homeview;
	}
	public void setHomeview(HomeView homeview) {
		this.homeview = homeview;
	}
	public Thread getRunserver() {
		return runserver;
	}
	public void setRunserver(Thread runserver) {
		this.runserver = runserver;
	}
    
}
