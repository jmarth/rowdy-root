package notification.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import networksetup.message;
import notification.client.rmiclient;

public interface rmiserver extends Remote {
	public void registerclient(rmiclient client) throws RemoteException;
	public void unregisterclient(rmiclient client) throws RemoteException;
	public void notifiedall (message msg)throws RemoteException;
	public void close() throws RemoteException;
}
