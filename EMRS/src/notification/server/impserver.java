package notification.server;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
	private transient HomeView homeview;
	
	public impserver(NetworkObject s, HomeView hv) throws RemoteException {
		super();
		clientlist = new ArrayList<rmiclient>();
		this.sv=s;
		this.homeview=hv;
	}

	@Override
	public synchronized void registerclient(rmiclient client) throws RemoteException {
		clientlist.add(client);
	}

	@Override
	public synchronized void unregisterclient(rmiclient client) throws RemoteException {
		int index = clientlist.indexOf(client);
		clientlist.remove(index);
		server s = (server)sv;
		s.getClientlist().remove(index);
		s.decreaseclientnum(); 
		for(rmiclient e:this.clientlist){
			e.decreasepriority();
		}
	}

	@Override
	public synchronized void notifiedall(message msg) throws RemoteException {
		System.out.println("server receive notification message");
		// TODO Auto-generated method stub
		int index = msg.getIndex();
		server s = (server)sv;
		for(int i=0;i<index;i++){
			clientlist.get(i).messsagereaction(msg);
		}
		for(int i=index+1;i<s.getClient_num();i++){
			clientlist.get(i).messsagereaction(msg);
		}
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
	}
}
