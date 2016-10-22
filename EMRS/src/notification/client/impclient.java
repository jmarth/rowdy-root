package notification.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import networksetup.NetworkObject;
import networksetup.message;
import notification.server.rmiserver;
import views.HomeView;

public class impclient extends UnicastRemoteObject implements rmiclient {
	
	private transient NetworkObject client;
	private transient rmiserver rserver;
	private transient HomeView homeview;
	
	public impclient(HomeView hv) throws RemoteException {
		super();
		homeview=hv;
	}


	public void setClient(NetworkObject client) {
		this.client = client;
	}

	@Override
	public void messsagereaction(message msg) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("receiving notification");
		
	}
	
	@Override
	public NetworkObject getClient() throws RemoteException {
		return client;
	}

	@Override
	public void decreasepriority() throws RemoteException {
		// TODO Auto-generated method stub
		this.client.decreasepriority();
	}


	@Override
	public void serverclose() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
}
