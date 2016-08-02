package models;

public class Comment {
	
	public final static int allergies = 1;
	public static final int visits = 2;
	public static final int vitals =3;
	
	private long id;
	private long pid;
	private int type;
	private long typeid;

	private String commentString;
	
	
	/**
	 * Constructor to create new Comment object
	 * @param id Comment id (generated by DB)
	 * @param pid Patient id corresponding to this Comment
	 * @param commentString Comment
	 */
	public Comment(long id, long pid, int type, long typeid, String commentString) {
		super();
		this.id = id;
		this.pid = pid;
		this.type = type;
		this.typeid = typeid;
		this.commentString = commentString;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getTypeid() {
		return typeid;
	}

	public void setTypeid(long typeid) {
		this.typeid = typeid;
	}

	/**
	 * 
	 * GETTERS AND SETTERS
	 * 
	 */
	
	public long getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getCommentString() {
		return commentString;
	}

	public void setCommentString(String commentString) {
		this.commentString = commentString;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}