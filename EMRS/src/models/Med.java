package models;

public class Med {
	private long id;
	private long pid;
	private String name;
	private String date;
	private String reason;
	
	public Med(Long param_id, long param_pid, String param_name, String param_date, String param_reason) {
		this.setId(param_id);
		this.setPid(param_pid);
		this.setName(param_name);
		this.setDate(param_date);
		this.setReason(param_reason);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

}
