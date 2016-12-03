package models;

import java.io.Serializable;

public class Document implements Serializable {
	
	public transient static final String DEFAULT_EMPTY_NAME = "Unknown";


	private long id;
	private long pid;
	private String name;
	private String path;
	private String type;
	
	/**
	 * Document constructor
	 * @param pid Patient ID document belongs to
	 * @param name Name of document
	 * @param path Path to document
	 * @param type Extension of document
	 */
	public Document(long pid, String name, String path, String type) {
		super();
		this.pid = pid;
		this.name = name;
		this.path = path;
		this.type = type;
	}
	
	public Document(long id, long pid, String name, String path, String type) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.path = path;
		this.type = type;
	}
	
	public String getRenderedDisplayString() {
		
		String ret = name;
		
		if (ret.trim().length() == 0) {
			
			ret = DEFAULT_EMPTY_NAME;
		}
		
		return name;
	}
	
	

	
	// ----------------------------- GETTERS AND SETTERS ---------------------------------------------
		
	@Override
	public String toString() {
		return "Document [id=" + id + ", pid=" + pid + ", name=" + name + ", path=" + path + ", type=" + type + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
		
}
