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
	public final static int RMI_PORT = 11099;
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
    public final static DateFormat DATE_FORMAT =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
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
    	homeview = null;
    	rserver=null;
    	rclient=null;
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
				System.out.println("asking question");
				AskQuesttion();
				if(askcount >=3 && askquestion.getDelay()==3000){
					askcount=0;
						startnewserversetup();
						System.out.println("change delay to 10000");
						askquestion.setDelay(10000);
						System.out.println("Server Created!");
						
				}
				
				askcount++;
			}	
    	});
    	startnewsetup();
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
								this.stopaskserver();
								serverturnclient(ipfrom,(server)msg.getData());
								this.askquestion.start();
							}
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
								if(svo.getClient_num()< svi.getClient_num()){//# of client less so turn to client
									askquestion.stop();
									if(svo.getClient_num()==0){//# of client = 0 so just ask server
										this.owner = new client((server)msg.getData());
										setexpect(this.SERVER_ACCEPT_JOIN,ipfrom,
												new message(this.ACCESS_CODE,this.CLIENT_REQUEST_JOIN,ipfrom,owner.getPriority()));
										
									}else{// want to turn to client but still have some server
										try {
											closermiserver();
										} catch (RemoteException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (NotBoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										askquestion.start();
									}
								}else if(svo.getCreateddate().after(svi.getCreateddate())){// == # of server but created date after so turn to client
									askquestion.stop();
									if(svo.getClient_num()==0){
										this.owner = new client((server)msg.getData());
										setexpect(this.SERVER_ACCEPT_JOIN,ipfrom,
												new message(this.ACCESS_CODE,this.CLIENT_REQUEST_JOIN,ipfrom,owner.getPriority()));
									}else{
										//TODO rmi will take care of notify client cus reliable protocal
										serverturnclient(ipfrom,(server) msg.getData());
									}
									askquestion.start();
								}else{
									HostSend(new message(this.ACCESS_CODE,this.OLDER_SERVER,owner,owner.getPriority()),ipfrom);
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
								this.HostSend(new message(this.ACCESS_CODE, this.SERVER_ACCEPT_JOIN,ipfrom,prior),ipfrom);
								if(this.askquestion.isRunning())
									this.stopaskserver();
								if(this.homeview!=null){
									homeview.getLbip().setText(owner.getIpaddrr().getHostAddress());
									homeview.getLbhosttype().setText("Server");
									homeview.getLbpriority().setText(""+owner.getPriority());
									homeview.getLbcreateddate().setText(this.DATE_FORMAT.format(owner.getCreateddate()));
								}
							}
							break;
						case SERVER_ACCEPT_JOIN:
							System.out.println("receiving server accept join "+ ipfrom.getHostAddress());
							if(owner.getType()==this.UNKNOWN && this.expectresponse==SERVER_ACCEPT_JOIN){
								
								try {
									client nclient = (client) owner;
									nclient.setPriority(msg.getIndex());
									nclient.setIpaddrr((InetAddress)msg.getData());
									Registry reg = LocateRegistry.getRegistry(nclient.getServer().getIpaddrr().getHostAddress(), this.RMI_PORT);
									rserver = (rmiserver) reg.lookup("rmiemr");
									this.rclient = new impclient(rserver);
									this.owner.setType(this.CLIENT);
									if(this.homeview!=null){
										homeview.getLbip().setText(owner.getIpaddrr().getHostAddress());
										homeview.getLbhosttype().setText("Client");
										homeview.getLbpriority().setText(""+owner.getPriority());
										homeview.getLbcreateddate().setText(this.DATE_FORMAT.format(owner.getCreateddate()));
									}
									this.stopaskserver();
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (NotBoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
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
    public void startnewserversetup(){
    	
    	try {
    		this.askquestion.stop();
			this.toip=InetAddress.getByName(BROADCAST_ADDR);
			this.expectresponse=this.SERVER_RESPONSE;
	    	this.currentmsg=new message(this.ACCESS_CODE,this.ASK_SERVER,null,-1);
	    	owner = new server(SERVER,-1);
	    	if(this.homeview!=null){
				homeview.getLbhosttype().setText("Server");
				homeview.getLbcreateddate().setText(this.DATE_FORMAT.format(owner.getCreateddate()));
			}
	    	this.askquestion.setDelay(10000);
	    	this.askcount=4;
	    	creatermiserver();
	    	this.askquestion.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    	
    }
    
    private void startnewsetup(){
    	try {
    		this.askquestion.stop();
			this.toip=InetAddress.getByName(BROADCAST_ADDR);
			this.expectresponse=this.SERVER_RESPONSE;
			this.currentmsg=new message(this.ACCESS_CODE,this.ASK_SERVER,null,-1);
	    	owner = null;
	    	this.askquestion.setDelay(3000);
	    	this.askcount=0;
	    	this.askquestion.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void backtoaskingsetup(){//just server call this function
    	try {
    		this.askquestion.stop();
			this.toip=InetAddress.getByName(BROADCAST_ADDR);
			this.expectresponse=this.SERVER_RESPONSE;
			this.currentmsg=new message(this.ACCESS_CODE,this.ASK_SERVER,null,-1);
	    	this.askquestion.setDelay(10000);
	    	this.askcount=4;
	    	this.askquestion.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
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
    		if(owner!=null){
	    		if(owner.getType()== this.CLIENT){
		    		rclient.leaveserver();
		    	}else if(owner.getType()==this.SERVER){
		    		closermiserver();
		    	}
    		}
			System.out.println("finish closing communication");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    private void creatermiserver(){
    	runserver = new Thread( new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					rserver = new impserver(owner);
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
    
    private void serverturnclient(InetAddress ipfrom, server in){
    	server sonwer = (server)owner;
		if(sonwer.getClient_num()>0){
			try {
				closermiserver();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		owner = new client(in);
		setexpect(this.SERVER_ACCEPT_JOIN,ipfrom,
				new message(this.ACCESS_CODE,this.CLIENT_REQUEST_JOIN,ipfrom,owner.getPriority()));
    }
    private void closermiserver() throws RemoteException, NotBoundException{
    	rserver.close();
    	reg.unbind("rmiemr");
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
	public rmiserver getRserver() {
		return rserver;
	}
	public void setRserver(rmiserver rserver) {
		this.rserver = rserver;
	}
	public rmiclient getRclient() {
		return rclient;
	}
	public void setRclient(rmiclient rclient) {
		this.rclient = rclient;
	}
    
}
