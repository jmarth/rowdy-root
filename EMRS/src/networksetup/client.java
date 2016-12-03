package networksetup;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import views.HomeView;

public class client extends NetworkObject implements Serializable {
	
	private transient NetworkObject server;
	private transient HomeView hv;

	public client(server sv) throws UnknownHostException {
		super();
		server = sv;
		// TODO Auto-generated constructor stub
	}

	public NetworkObject getServer() {
		return server;
	}

	public void setServer(NetworkObject server) {
		this.server = server;
	}

	public HomeView getHv() {
		return hv;
	}

	public void setHv(HomeView hv) {
		this.hv = hv;
	}
	
}
