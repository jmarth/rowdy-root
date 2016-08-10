package models;

public class IOPMeasurement {

	private long id;
	private long vid; // foreign key of visit it belongs to
	
	private String ODValue; 
	private String ODType;
	private String ODNotes;
	private String OSValue; 
	private String OSType;
	private String OSNotes;
	private String dateCreated;
	
	public IOPMeasurement(long id, long vid, String oDValue, String oDType, String oDNotes, String oSValue, String oSType,
			String oSNotes, String dateCreated) {
		super();
		this.id = id;
		this.vid = vid;
		ODValue = oDValue;
		ODType = oDType;
		ODNotes = oDNotes;
		OSValue = oSValue;
		OSType = oSType;
		OSNotes = oSNotes;
		this.dateCreated = dateCreated;
	}
	
	public IOPMeasurement(long vid, String oDValue, String oDType, String oDNotes, String oSValue, String oSType,
			String oSNotes) {
		super();
		this.vid = vid;
		ODValue = oDValue;
		ODType = oDType;
		ODNotes = oDNotes;
		OSValue = oSValue;
		OSType = oSType;
		OSNotes = oSNotes;
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

	public String getODValue() {
		return ODValue;
	}

	public void setODValue(String oDValue) {
		ODValue = oDValue;
	}

	public String getODType() {
		return ODType;
	}

	public void setODType(String oDType) {
		ODType = oDType;
	}

	public String getODNotes() {
		return ODNotes;
	}

	public void setODNotes(String oDNotes) {
		ODNotes = oDNotes;
	}

	public String getOSValue() {
		return OSValue;
	}

	public void setOSValue(String oSValue) {
		OSValue = oSValue;
	}

	public String getOSType() {
		return OSType;
	}

	public void setOSType(String oSType) {
		OSType = oSType;
	}

	public String getOSNotes() {
		return OSNotes;
	}

	public void setOSNotes(String oSNotes) {
		OSNotes = oSNotes;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}
