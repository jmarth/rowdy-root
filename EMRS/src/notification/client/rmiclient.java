package notification.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import networksetup.NetworkObject;
import networksetup.message;
import views.HomeView;

public interface rmiclient extends Remote {
	public void messsagereaction(message msg) throws RemoteException;
	public NetworkObject getClient() throws RemoteException;
	public void decreasepriority() throws RemoteException;
	public void serverclose() throws RemoteException;
	public void leaveserver() throws RemoteException;
}
