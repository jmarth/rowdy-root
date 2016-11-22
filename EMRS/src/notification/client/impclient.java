package notification.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controller.EMRS;
import controller.rminotification;
import networksetup.NetworkObject;
import networksetup.message;
import notification.server.rmiserver;
import views.HomeView;

public class impclient extends UnicastRemoteObject implements rmiclient {
	
	private transient NetworkObject client;
	private transient rmiserver rserver;
	
	public impclient(rmiserver rs) throws RemoteException {
		super();
		rserver=rs;
		rs.registerclient(this);
	}


	public void setClient(NetworkObject client) {
		this.client = client;
	}

	@Override
	public void messsagereaction(message msg) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("receiving notification");
		if(EMRS.notification.getHomeview()!=null){
			EMRS.notification.getHomeview().getBtnft().setEnabled(true);
		}
		rminotification.messageaction(msg);
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
		if(EMRS.notification.getOwner().getPriority()==0){
			EMRS.notification.startnewserversetup();
		}else{
			try {
				Thread.sleep(2000);
				EMRS.notification.startnewsetup();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public void leaveserver() throws RemoteException {
		// TODO Auto-generated method stub
		this.rserver.unregisterclient(this);
	}


	@Override
	public void notifychange(message msg) throws RemoteException {
		// TODO Auto-generated method stub
		this.rserver.notifiedall(msg);
	}
}
