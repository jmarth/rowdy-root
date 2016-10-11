package networksetup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
//for client and server info
public class NetworkObject {
	private InetAddress ipaddrr;
	private Date createddate;
	private int type;
	private int priority;
	
	public NetworkObject (int type, int priority) throws UnknownHostException{
		ipaddrr = InetAddress.getLocalHost();
		createddate = new Date();
		this.type = type;
		this.priority = priority;
	}

	public NetworkObject () throws UnknownHostException{
		ipaddrr = InetAddress.getLocalHost();
		createddate = new Date();
		this.type = mastercomunication.UNKNOWN;
		this.priority = -1;
	}
	
	public NetworkObject(InetAddress ipaddrr, Date createddate, int type, int priority) {
		super();
		this.ipaddrr = ipaddrr;
		this.createddate = createddate;
		this.type = type;
		this.priority = priority;
	}

	public InetAddress getIpaddrr() {
		return ipaddrr;
	}

	public void setIpaddrr(InetAddress ipaddrr) {
		this.ipaddrr = ipaddrr;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	
	
	
}
