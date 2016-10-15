package networksetup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class server extends NetworkObject {
	
	List<InetAddress> clientlist;
	
	public server(int type, int priority) throws UnknownHostException {
		super(type, priority);
		clientlist = new ArrayList<InetAddress>();
		// TODO Auto-generated constructor stub
	}

	public server(InetAddress ipaddr) {
		super(ipaddr);
		clientlist = new ArrayList<InetAddress>();
		// TODO Auto-generated constructor stub
	}

	public List<InetAddress> getClientlist() {
		return clientlist;
	}

	public void setClientlist(List<InetAddress> clientlist) {
		this.clientlist = clientlist;
	}
	
}
