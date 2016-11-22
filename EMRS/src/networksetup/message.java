package networksetup;

import java.io.Serializable;
//for all network communication
public class message implements Serializable {
	public transient static int PATIENT_INSERT =1;
	public transient static int PATIENT_UPDATE =2;
	public transient static int PATIENT_DELETE =3;
	
	private String acceptcode;
	private int command;
	private Object data;
	private int index;
	
	
	public message() {
		super();	
	}
	
	public message(String acceptcode, int command, Object data, int index) {
		super();
		this.acceptcode = acceptcode;
		this.command = command;
		this.data = data;
		this.index=index;
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

	public int getIndex() {
		return index;
	}
	
}
