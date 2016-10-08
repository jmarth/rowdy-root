package networksetup;

import java.io.Serializable;

public class message implements Serializable {
	public static final transient int ASK_SERVER = 1;
	public static final transient int SERVER_CLOSE = 2;
	public static final transient int NEW_SERVER = 3;
	public static final transient int LEAVE_SERVER = 4;
	public static final transient int SERVER_RESPONSE = 5;
	public static final transient int CLIENT_RESPONSE = 6;
	public static final transient int CURRENT_SERVER = 7;
	private String acceptcode;
	private int command;
	private Object data;
	
	
	public message() {
		super();	
	}
	
	public message(String acceptcode, int command, Object data) {
		super();
		this.acceptcode = acceptcode;
		this.command = command;
		this.data = data;
	}
	public String getAcceptcode() {
		return acceptcode;
	}
	public void setAcceptcode(String acceptcode) {
		this.acceptcode = acceptcode;
	}
	public int getCommand() {
		return command;
	}
	public void setCommand(int command) {
		this.command = command;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String toString(){
		String result = "acceptcode: " + acceptcode +"\tcommand " + command + "\tipaddress: "+data.toString();
		return result;
		
	}
}
