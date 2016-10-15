package networksetup;

import java.net.InetAddress;

public class client extends NetworkObject {
	
	private transient NetworkObject server;

	public client(server sv) {
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
	
}
