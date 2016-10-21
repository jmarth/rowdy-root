package networksetup;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import views.HomeView;

public class server extends NetworkObject implements Serializable {
	
	private int client_num;
	private transient List<InetAddress> clientlist;
	private transient HomeView hv;
	
	public server(int type, int priority) throws UnknownHostException {
		super(type, priority);
		clientlist = new ArrayList<InetAddress>();
		client_num=0;
		// TODO Auto-generated constructor stub
	}

	public server(InetAddress ipaddr) {
		super(ipaddr);
		clientlist = new ArrayList<InetAddress>();
		//client_num=0;
		// TODO Auto-generated constructor stub
	}

	public List<InetAddress> getClientlist() {
		return clientlist;
	}

	public void setClientlist(List<InetAddress> clientlist) {
		this.clientlist = clientlist;
	}

	public int getClient_num() {
		return client_num;
	}

	public void setClient_num(int client_num) {
		this.client_num = client_num;
	}
	public void increaseclientnum(){
		client_num++;
	}
	public void decreaseclientnum(){
		client_num--;
	}

	public HomeView getHv() {
		return hv;
	}

	public void setHv(HomeView hv) {
		this.hv = hv;
	}
	
}
