package models;

public class VisionExam {

	private long id;
	private long vid; // foreign key of visit it belongs to
	
	//distance vision
	private String dVODsc;
	private String dVOSsc;
	private String dVODcc;
	private String dVOScc;
	
	//autorefraction sc (sine correction)
	private float aRscODSphere;
	private float aRscODCyl;
	private float aRscODAxis;
	private float aRscOSSphere;
	private float aRscOSCyl;
	private float aRscOSAxis;
	
	//autorefraction cc (cum correction)
	private float aRccODSphere;
	private float aRccODCyl;
	private float aRccODAxis;
	private float aRccOSSphere;
	private float aRccOSCyl;
	private float aRccOSAxis;
	
	//glasses Rx
	private float glsRxODSphere;
	private float glsRxODCyl;
	private float glsRxODAxis;
	private float glsRxODAdd;
	private float glsRxOSSphere;
	private float glsRxOSCyl;
	private float glsRxOSAxis;
	private float glsRxOSAdd;
	private String glsRxNotes;
	private String dateCreated;
	
	public VisionExam(long id, long vid, String dVODsc, String dVOSsc, String dVODcc, String dVOScc, float aRscODSphere,
			float aRscODCyl, float aRscODAxis, float aRscOSSphere, float aRscOSCyl, float aRscOSAxis,
			float aRccODSphere, float aRccODCyl, float aRccODAxis, float aRccOSSphere, float aRccOSCyl,
			float aRccOSAxis, float glsRxODSphere, float glsRxODCyl, float glsRxODAxis, float glsRxODAdd,
			float glsRxOSSphere, float glsRxOSCyl, float glsRxOSAxis, float glsRxOSAdd, String glsRxNotes, String dateCreated) {
		super();
		this.id = id;
		this.vid = vid;
		this.dVODsc = dVODsc;
		this.dVOSsc = dVOSsc;
		this.dVODcc = dVODcc;
		this.dVOScc = dVOScc;
		this.aRscODSphere = aRscODSphere;
		this.aRscODCyl = aRscODCyl;
		this.aRscODAxis = aRscODAxis;
		this.aRscOSSphere = aRscOSSphere;
		this.aRscOSCyl = aRscOSCyl;
		this.aRscOSAxis = aRscOSAxis;
		this.aRccODSphere = aRccODSphere;
		this.aRccODCyl = aRccODCyl;
		this.aRccODAxis = aRccODAxis;
		this.aRccOSSphere = aRccOSSphere;
		this.aRccOSCyl = aRccOSCyl;
		this.aRccOSAxis = aRccOSAxis;
		this.glsRxODSphere = glsRxODSphere;
		this.glsRxODCyl = glsRxODCyl;
		this.glsRxODAxis = glsRxODAxis;
		this.glsRxODAdd = glsRxODAdd;
		this.glsRxOSSphere = glsRxOSSphere;
		this.glsRxOSCyl = glsRxOSCyl;
		this.glsRxOSAxis = glsRxOSAxis;
		this.glsRxOSAdd = glsRxOSAdd;
		this.glsRxNotes = glsRxNotes;
		this.setDateCreated(dateCreated);
	}
	
	public VisionExam(long vid, String dVODsc, String dVOSsc, String dVODcc, String dVOScc, float aRscODSphere,
			float aRscODCyl, float aRscODAxis, float aRscOSSphere, float aRscOSCyl, float aRscOSAxis,
			float aRccODSphere, float aRccODCyl, float aRccODAxis, float aRccOSSphere, float aRccOSCyl,
			float aRccOSAxis, float glsRxODSphere, float glsRxODCyl, float glsRxODAxis, float glsRxODAdd,
			float glsRxOSSphere, float glsRxOSCyl, float glsRxOSAxis, float glsRxOSAdd, String glsRxNotes) {
		super();
		this.vid = vid;
		this.dVODsc = dVODsc;
		this.dVOSsc = dVOSsc;
		this.dVODcc = dVODcc;
		this.dVOScc = dVOScc;
		this.aRscODSphere = aRscODSphere;
		this.aRscODCyl = aRscODCyl;
		this.aRscODAxis = aRscODAxis;
		this.aRscOSSphere = aRscOSSphere;
		this.aRscOSCyl = aRscOSCyl;
		this.aRscOSAxis = aRscOSAxis;
		this.aRccODSphere = aRccODSphere;
		this.aRccODCyl = aRccODCyl;
		this.aRccODAxis = aRccODAxis;
		this.aRccOSSphere = aRccOSSphere;
		this.aRccOSCyl = aRccOSCyl;
		this.aRccOSAxis = aRccOSAxis;
		this.glsRxODSphere = glsRxODSphere;
		this.glsRxODCyl = glsRxODCyl;
		this.glsRxODAxis = glsRxODAxis;
		this.glsRxODAdd = glsRxODAdd;
		this.glsRxOSSphere = glsRxOSSphere;
		this.glsRxOSCyl = glsRxOSCyl;
		this.glsRxOSAxis = glsRxOSAxis;
		this.glsRxOSAdd = glsRxOSAdd;
		this.glsRxNotes = glsRxNotes;
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
	public String getdVODsc() {
		return dVODsc;
	}
	public void setdVODsc(String dVODsc) {
		this.dVODsc = dVODsc;
	}
	public String getdVOSsc() {
		return dVOSsc;
	}
	public void setdVOSsc(String dVOSsc) {
		this.dVOSsc = dVOSsc;
	}
	public String getdVODcc() {
		return dVODcc;
	}
	public void setdVODcc(String dVODcc) {
		this.dVODcc = dVODcc;
	}
	public String getdVOScc() {
		return dVOScc;
	}
	public void setdVOScc(String dVOScc) {
		this.dVOScc = dVOScc;
	}
	public float getaRscODSphere() {
		return aRscODSphere;
	}
	public void setaRscODSphere(float aRscODSphere) {
		this.aRscODSphere = aRscODSphere;
	}
	public float getaRscODCyl() {
		return aRscODCyl;
	}
	public void setaRscODCyl(float aRscODCyl) {
		this.aRscODCyl = aRscODCyl;
	}
	public float getaRscODAxis() {
		return aRscODAxis;
	}
	public void setaRscODAxis(float aRscODAxis) {
		this.aRscODAxis = aRscODAxis;
	}
	public float getaRscOSSphere() {
		return aRscOSSphere;
	}
	public void setaRscOSSphere(float aRscOSSphere) {
		this.aRscOSSphere = aRscOSSphere;
	}
	public float getaRscOSCyl() {
		return aRscOSCyl;
	}
	public void setaRscOSCyl(float aRscOSCyl) {
		this.aRscOSCyl = aRscOSCyl;
	}
	public float getaRscOSAxis() {
		return aRscOSAxis;
	}
	public void setaRscOSAxis(float aRscOSAxis) {
		this.aRscOSAxis = aRscOSAxis;
	}
	public float getaRccODSphere() {
		return aRccODSphere;
	}
	public void setaRccODSphere(float aRccODSphere) {
		this.aRccODSphere = aRccODSphere;
	}
	public float getaRccODCyl() {
		return aRccODCyl;
	}
	public void setaRccODCyl(float aRccODCyl) {
		this.aRccODCyl = aRccODCyl;
	}
	public float getaRccODAxis() {
		return aRccODAxis;
	}
	public void setaRccODAxis(float aRccODAxis) {
		this.aRccODAxis = aRccODAxis;
	}
	public float getaRccOSSphere() {
		return aRccOSSphere;
	}
	public void setaRccOSSphere(float aRccOSSphere) {
		this.aRccOSSphere = aRccOSSphere;
	}
	public float getaRccOSCyl() {
		return aRccOSCyl;
	}
	public void setaRccOSCyl(float aRccOSCyl) {
		this.aRccOSCyl = aRccOSCyl;
	}
	public float getaRccOSAxis() {
		return aRccOSAxis;
	}
	public void setaRccOSAxis(float aRccOSAxis) {
		this.aRccOSAxis = aRccOSAxis;
	}
	public float getGlsRxODSphere() {
		return glsRxODSphere;
	}
	public void setGlsRxODSphere(float glsRxODSphere) {
		this.glsRxODSphere = glsRxODSphere;
	}
	public float getGlsRxODCyl() {
		return glsRxODCyl;
	}
	public void setGlsRxODCyl(float glsRxODCyl) {
		this.glsRxODCyl = glsRxODCyl;
	}
	public float getGlsRxODAxis() {
		return glsRxODAxis;
	}
	public void setGlsRxODAxis(float glsRxODAxis) {
		this.glsRxODAxis = glsRxODAxis;
	}
	public float getGlsRxODAdd() {
		return glsRxODAdd;
	}
	public void setGlsRxODAdd(float glsRxODAdd) {
		this.glsRxODAdd = glsRxODAdd;
	}
	public float getGlsRxOSSphere() {
		return glsRxOSSphere;
	}
	public void setGlsRxOSSphere(float glsRxOSSphere) {
		this.glsRxOSSphere = glsRxOSSphere;
	}
	public float getGlsRxOSCyl() {
		return glsRxOSCyl;
	}
	public void setGlsRxOSCyl(float glsRxOSCyl) {
		this.glsRxOSCyl = glsRxOSCyl;
	}
	public float getGlsRxOSAxis() {
		return glsRxOSAxis;
	}
	public void setGlsRxOSAxis(float glsRxOSAxis) {
		this.glsRxOSAxis = glsRxOSAxis;
	}
	public float getGlsRxOSAdd() {
		return glsRxOSAdd;
	}
	public void setGlsRxOSAdd(float glsRxOSAdd) {
		this.glsRxOSAdd = glsRxOSAdd;
	}
	public String getGlsRxNotes() {
		return glsRxNotes;
	}
	public void setGlsRxNotes(String glsRxNotes) {
		this.glsRxNotes = glsRxNotes;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}
