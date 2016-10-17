package networksetup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class server extends NetworkObject {
	
	List<InetAddress> clientlist;
	int[] res_receive;
	public server(int type, int priority) throws UnknownHostException {
		super(type, priority);
		res_receive = new int[mastercomunication.SERVER_NUM_RECEIVE];
		clientlist = new ArrayList<InetAddress>();
		// TODO Auto-generated constructor stub
	}

	public server(InetAddress ipaddr) {
		super(ipaddr);
		res_receive = new int[mastercomunication.SERVER_NUM_RECEIVE];
		clientlist = new ArrayList<InetAddress>();
		// TODO Auto-generated constructor stub
	}

	public List<InetAddress> getClientlist() {
		return clientlist;
	}

	public void setClientlist(List<InetAddress> clientlist) {
		this.clientlist = clientlist;
	}

	public int[] getRes_receive() {
		return res_receive;
	}

	public void setRes_receive(int[] res_receive) {
		this.res_receive = res_receive;
	}
	private void resetcount(){
		for(int i=0; i<mastercomunication.SERVER_NUM_RECEIVE;i++)
			res_receive[i]=0;
	}
}
