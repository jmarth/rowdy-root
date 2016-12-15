package models;

import java.io.Serializable;

public class Surgery implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private long pid;
		
	private String title;
	
	private String body;
	
	public Surgery(long param_id, long param_pid, String param_title, String param_body) {
		this.id = param_id;
		this.pid = param_pid;
		this.title = param_title;
		this.body = param_body;
	}

	public long getPid() {
		return this.pid;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public void setId(long newID) {
		this.id = newID;
		
	}

}
