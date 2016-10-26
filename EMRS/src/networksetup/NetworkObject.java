package networksetup;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
//for client and server info
public class NetworkObject implements Serializable {
	private InetAddress ipaddrr;
	private Date createddate;
	private int type;
	private int priority;
	
	public NetworkObject (int type, int priority) throws UnknownHostException{
		super();
		ipaddrr = InetAddress.getLocalHost();
		createddate = new Date();
		this.type = type;
		this.priority = priority;
	}
	public NetworkObject(InetAddress ipaddrr, Date createddate, int type, int priority) {
		super();
		this.ipaddrr = ipaddrr;
		this.createddate = createddate;
		this.type = type;
		this.priority = priority;
	}
	public NetworkObject (InetAddress ipaddr){
		super();
		this.ipaddrr = ipaddr;
		createddate = new Date();
		type = mastercomunication.UNKNOWN;
		priority = -1;
	}
	
	public NetworkObject (){
		super();
		this.ipaddrr = null;
		createddate = new Date();
		type = mastercomunication.UNKNOWN;
		priority = -1;
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
	
	public void increasepriority(){
		this.priority++;
	}
	public void decreasepriority(){
		this.priority--;
	}
	
	
	
}
