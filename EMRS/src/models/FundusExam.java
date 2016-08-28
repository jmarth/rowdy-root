package models;

public class FundusExam {

	
	private long id;
	private long vid; // foreign key of visit it belongs to
	

	private boolean dialated;
	private String dialNotes;
	
	//cup to disc ratio
	private String CDOD;
	private boolean CDODAb; 
	private String CDODNotes;
	
	private String CDOS;
	private boolean CDOSAb; 
	private String CDOSNotes;
	
	private boolean maculaODAb; 
	private String maculaODNotes;
	private boolean maculaOSAb; 
	private String maculaOSNotes;
	
	private boolean retinaODAb; 
	private String retinaODNotes;
	private boolean retinaOSAb; 
	private String retinaOSNotes;

	/**
	 * Comes from the DB
	 * @param id
	 * @param vid
	 * @param dialated
	 * @param dialNotes
	 * @param cDODAb
	 * @param cDOD
	 * @param cDODNotes
	 * @param cDOSAb
	 * @param cDOS
	 * @param cDOSNotes
	 * @param maculaODAb
	 * @param maculaODNotes
	 * @param maculaOSAb
	 * @param maculaOSNotes
	 * @param retinaODAb
	 * @param retinaODNotes
	 * @param retinaOSAb
	 * @param retinaOSNotes
	 * @param dateCreated
	 */
	public FundusExam(long id, long vid, boolean dialated, String dialNotes, boolean cDODAb, String cDOD, String cDODNotes, boolean cDOSAb, String cDOS,
			String cDOSNotes, boolean maculaODAb, String maculaODNotes, boolean maculaOSAb, String maculaOSNotes,
			boolean retinaODAb, String retinaODNotes, boolean retinaOSAb, String retinaOSNotes) {
		super();
		this.id = id;
		this.vid = vid;
		this.dialated = dialated;
		this.dialNotes = dialNotes;
		CDOD = cDOD;
		CDODAb = cDODAb;
		CDODNotes = cDODNotes;
		CDOS = cDOS;
		CDOSAb = cDOSAb;
		CDOSNotes = cDOSNotes;
		this.maculaODAb = maculaODAb;
		this.maculaODNotes = maculaODNotes;
		this.maculaOSAb = maculaOSAb;
		this.maculaOSNotes = maculaOSNotes;
		this.retinaODAb = retinaODAb;
		this.retinaODNotes = retinaODNotes;
		this.retinaOSAb = retinaOSAb;
		this.retinaOSNotes = retinaOSNotes;
	}
	
	/**
	 * Goes to the DB
	 * @param vid
	 * @param dialated
	 * @param dialNotes
	 * @param cDODAb
	 * @param cDOD
	 * @param cDODNotes
	 * @param cDOSAb
	 * @param cDOS
	 * @param cDOSNotes
	 * @param maculaODAb
	 * @param maculaODNotes
	 * @param maculaOSAb
	 * @param maculaOSNotes
	 * @param retinaODAb
	 * @param retinaODNotes
	 * @param retinaOSAb
	 * @param retinaOSNotes
	 */
	public FundusExam(boolean dialated, String dialNotes, boolean cDODAb, String cDOD, String cDODNotes, boolean cDOSAb, String cDOS,
			String cDOSNotes, boolean maculaODAb, String maculaODNotes, boolean maculaOSAb, String maculaOSNotes,
			boolean retinaODAb, String retinaODNotes, boolean retinaOSAb, String retinaOSNotes) {
		super();
		this.dialated = dialated;
		this.dialNotes = dialNotes;
		CDOD = cDOD;
		CDODAb = cDODAb;
		CDODNotes = cDODNotes;
		CDOS = cDOS;
		CDOSAb = cDOSAb;
		CDOSNotes = cDOSNotes;
		this.maculaODAb = maculaODAb;
		this.maculaODNotes = maculaODNotes;
		this.maculaOSAb = maculaOSAb;
		this.maculaOSNotes = maculaOSNotes;
		this.retinaODAb = retinaODAb;
		this.retinaODNotes = retinaODNotes;
		this.retinaOSAb = retinaOSAb;
		this.retinaOSNotes = retinaOSNotes;
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
	
	public boolean isDialated() {
		return dialated;
	}

	public void setDialated(boolean dialated) {
		this.dialated = dialated;
	}

	public String getDial_Notes() {
		return dialNotes;
	}

	public void setDial_Notes(String dial_Notes) {
		this.dialNotes = dial_Notes;
	}
	
	public String getCDOD() {
		return CDOD;
	}

	public void setCDOD(String cDOD) {
		CDOD = cDOD;
	}

	public boolean isCDODAb() {
		return CDODAb;
	}

	public void setCDODAb(boolean cDODAb) {
		CDODAb = cDODAb;
	}

	public String getCDODNotes() {
		return CDODNotes;
	}

	public void setCDODNotes(String cDODNotes) {
		CDODNotes = cDODNotes;
	}

	public String getCDOS() {
		return CDOS;
	}

	public void setCDOS(String cDOS) {
		CDOS = cDOS;
	}

	public boolean isCDOSAb() {
		return CDOSAb;
	}

	public void setCDOSAb(boolean cDOSAb) {
		CDOSAb = cDOSAb;
	}

	public String getCDOSNotes() {
		return CDOSNotes;
	}

	public void setCDOSNotes(String cDOSNotes) {
		CDOSNotes = cDOSNotes;
	}

	public boolean isMaculaODAb() {
		return maculaODAb;
	}

	public void setMaculaODAb(boolean maculaODAb) {
		this.maculaODAb = maculaODAb;
	}

	public String getMaculaODNotes() {
		return maculaODNotes;
	}

	public void setMaculaODNotes(String maculaODNotes) {
		this.maculaODNotes = maculaODNotes;
	}

	public boolean isMaculaOSAb() {
		return maculaOSAb;
	}

	public void setMaculaOSAb(boolean maculaOSAb) {
		this.maculaOSAb = maculaOSAb;
	}

	public String getMaculaOSNotes() {
		return maculaOSNotes;
	}

	public void setMaculaOSNotes(String maculaOSNotes) {
		this.maculaOSNotes = maculaOSNotes;
	}

	public boolean isRetinaODAb() {
		return retinaODAb;
	}

	public void setRetinaODAb(boolean retinaODAb) {
		this.retinaODAb = retinaODAb;
	}

	public String getRetinaODNotes() {
		return retinaODNotes;
	}

	public void setRetinaODNotes(String retinaODNotes) {
		this.retinaODNotes = retinaODNotes;
	}

	public boolean isRetinaOSAb() {
		return retinaOSAb;
	}

	public void setRetinaOSAb(boolean retinaOSAb) {
		this.retinaOSAb = retinaOSAb;
	}

	public String getRetinaOSNotes() {
		return retinaOSNotes;
	}

	public void setRetinaOSNotes(String retinaOSNotes) {
		this.retinaOSNotes = retinaOSNotes;
	}

	
	
}
