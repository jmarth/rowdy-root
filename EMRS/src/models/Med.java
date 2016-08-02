package models;

public class Med {
	private Long id;
	private Long pid;
	private String name;
	private String date;
	private String reason;
	
	public Med(Long param_id, Long param_pid, String param_name, String param_date, String param_reason) {
		this.setId(param_id);
		this.setPid(param_pid);
		this.setName(param_name);
		this.setDate(param_date);
		this.setReason(param_reason);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
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

}
