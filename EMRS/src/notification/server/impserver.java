package notification.server;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import controller.EMRS;
import controller.rminotification;
import networksetup.NetworkObject;
import networksetup.mastercomunication;
import networksetup.message;
import networksetup.server;
import notification.client.impclient;
import notification.client.rmiclient;
import views.HomeView;

public class impserver extends UnicastRemoteObject implements rmiserver {
	
	private transient List<rmiclient> clientlist;
	private transient NetworkObject sv;
	
	public impserver(NetworkObject s) throws RemoteException {
		super();
		clientlist = new ArrayList<rmiclient>();
		this.sv=s;
	}

	@Override
	public synchronized void registerclient(rmiclient client) throws RemoteException {
		clientlist.add(client);
	}

	@Override
	public synchronized void unregisterclient(rmiclient client){
		System.out.println("client leave server");
		int index = clientlist.indexOf(client);
		clientlist.remove(index);
		server s = (server)sv;
		s.getClientlist().remove(index);
		s.decreaseclientnum(); 
		if(clientlist.size()==0)
			EMRS.notification.backtoaskingsetup();
		else
			for(int i =index;i<clientlist.size();i++){
				try{
					clientlist.get(i).decreasepriority();
				} catch (RemoteException ex){
					clientlist.remove(i--);
					s.setClient_num(s.getClient_num()-1);
					System.err.println("cann't send to client" + i +" in notifiedall");
					if(clientlist.size()==0)
						EMRS.notification.backtoaskingsetup();
				}
			}
	}

	@Override
	public synchronized void notifiedall(message msg) {
		System.out.println("server receive notification message");
		int index = msg.getIndex();
		if(index !=-1)
			EMRS.notification.getHomeview().getBtnft().setEnabled(true);
		server s = (server)sv;
		for(int i=0;i<index;i++){
			try {
				clientlist.get(i).messsagereaction(msg);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				clientlist.remove(i--);
				s.setClient_num(s.getClient_num()-1);
				System.err.println("cann't send to client" + i +" in notifiedall");
				if(clientlist.size()==0)
					EMRS.notification.backtoaskingsetup();
				//e.printStackTrace();
			}
		}
		for(int i=index+1;i<this.clientlist.size();i++){
			try {
				clientlist.get(i).messsagereaction(msg);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				clientlist.remove(i--);
				s.setClient_num(s.getClient_num()-1);
				System.err.println("cann't send to client" + i +" in notifiedall");
				if(clientlist.size()==0)
					EMRS.notification.backtoaskingsetup();
				//e.printStackTrace();	
			}
		}
		if(msg.getIndex()!=-1)
			rminotification.messageaction(msg);
	}

	public NetworkObject getSv() {
		return sv;
	}

	public void setSv(server sv) {
		this.sv = sv;
	}

	@Override
	public void close() throws RemoteException {
		// TODO Auto-generated method stub
		for(rmiclient e: this.clientlist){
			e.serverclose();
		}
		clientlist.clear();
	}
	private void messageanalyze(message m){
		rminotification.messageaction(m);
	}
}
