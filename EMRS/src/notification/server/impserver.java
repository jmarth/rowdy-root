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

public class impserver extends UnicastRemoteObject implements rmiserver {
	
	private List<rmiclient> clientlist;
	private NetworkObject sv;
	
	public impserver(NetworkObject s) throws RemoteException {
		super();
		clientlist = new ArrayList<rmiclient>();
		this.sv=s;
	}

	@Override
	public synchronized void registerclient(rmiclient client) throws RemoteException {
		// TODO Auto-generated method stub
		clientlist.add(client);
	}

	@Override
	public synchronized void unregisterclient(rmiclient client) throws RemoteException {
		// TODO Auto-generated method stub
		int index = clientlist.indexOf(client);
		clientlist.remove(index);
		server s = (server)sv;
		for(InetAddress e : s.getClientlist()){
			if(e.getHostName().equals(client.getClient().getIpaddrr().getHostName())){
				s.getClientlist().remove(e);
				break;
			}
		}
		message msg = new message(mastercomunication.ACCESS_CODE,mastercomunication.LEAVE_SERVER,null,index); 
		for(int i =0;i< clientlist.size();i++){
			clientlist.get(i).messsagereaction(msg);
		}
	}

	@Override
	public synchronized void notifiedall(message msg) throws RemoteException {
		// TODO Auto-generated method stub
		for(int i=0;i< clientlist.size();i++){
			if(i!=msg.getIndex())
				clientlist.get(i).messsagereaction(msg);
		}
	}

	public NetworkObject getSv() {
		return sv;
	}

	public void setSv(server sv) {
		this.sv = sv;
	}
	
}
