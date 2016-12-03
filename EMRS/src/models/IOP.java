package models;

public class IOP {

	private long id;
	private long vid; // foreign key of visit it belongs to

	private String which;
	private String measurement;
	private String type;
	private String notes;
	
	private String dateCreated;

	/*
	 * For a new model I think
	 */
	public IOP(String which, String measurement, String type, String notes) {
		super();
		this.which = which;
		this.measurement = measurement;
		this.type = type;
		this.notes = notes;
	}

	public IOP(long id, long vid, String which, String measurement, String type, String notes, String dateCreated) {
		super();
		this.id = id;
		this.vid = vid;
		this.which = which;
		this.measurement = measurement;
		this.type = type;
		this.notes = notes;
		this.dateCreated = dateCreated;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVid() {
		return vid;
	}

	public void setVid(long vid) {
		this.vid = vid;
	}

	public String getWhich() {
		return which;
	}

	public void setWhich(String which) {
		this.which = which;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void loadIOPsFromVisit() {
		// TODO Auto-generated method stub
		
	}
	
}
