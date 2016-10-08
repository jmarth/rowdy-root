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

import javax.swing.Timer;

public class mastercomunication {
	final static String ACCESS_CODE = "emr5187";
	final static String INET_ADDR = "255.255.255.255";
    final static int PORT = 5187;
    private MulticastSocket mSocket;
    private Thread listener;
    private Timer askserver;
    private int hosttype;//server 1, client 2, unknown 0;
    private int numberofclient;
    private String activeadd;
    private String serveradd;
    private int priority;
    private int askcount;
    
    public mastercomunication() throws IOException{
    	InetAddress addr = InetAddress.getByName(INET_ADDR);
    	mSocket = new MulticastSocket(PORT);
    	mSocket.setLoopbackMode(false);
    	mSocket.setTimeToLive(2);
    	hosttype = 0;
    	activeadd ="";
    	serveradd ="";
    	numberofclient=0;
    	priority = -1;
    	askcount = 0;
    	try{
    		mSocket.setBroadcast(true);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	listener = new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
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
    	askserver = new Timer(3000,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AskExistServer();
			}	
    	});
    	askserver.start();
    }
    private void AskExistServer(){
    	try {
			message msg = new message(this.ACCESS_CODE,message.ASK_SERVER,InetAddress.getLocalHost());
			HostSend(msg,InetAddress.getLocalHost());
			
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void AnalizeMSG(message msg){
    	//TODO
    	System.out.println(msg.toString());
    }
    public void close() throws UnknownHostException, IOException{
		mSocket.leaveGroup(InetAddress.getByName(INET_ADDR));
    }
}
