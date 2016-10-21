package notification.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import networksetup.NetworkObject;
import networksetup.message;
import notification.server.rmiserver;

public class impclient extends UnicastRemoteObject implements rmiclient {
	
	private NetworkObject client;
	private rmiserver rserver;
	
	public impclient(rmiserver rs) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		rserver =rs;
	}


	public void setClient(NetworkObject client) {
		this.client = client;
	}

	@Override
	public void messsagereaction(message msg) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public NetworkObject getClient() throws RemoteException {
		return client;
	}
	
}
