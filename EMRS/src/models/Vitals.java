package models;

public class Vitals {

	public static final String MMHG = "mm/Hg";
    public static final String PA = "Pa";
 
    public static final String FTIN = "ft/inches";
    public static final String IN = "in";
    public static final String CM = "cm";
   
    public static final String LBS = "lbs";
    public static final String KG = "kg";
	
	private long id;
	private long pid;

	private float bps;
	private float bpd;
	private String bpUnit;

	private int hFeet;
	private int hInches;

	private int hcm;

	private String hUnit;

	private float weight;
	private String wUnit;

	private String notes;

	public Vitals(long id, long pid, float bps, float bpd, String bpUnit, int hFeet, int hInches, int hcm, String hUnit,
			float weight, String wUnit, String notes) {

		super();
		this.id = id;
		this.pid = pid;
		this.bps = bps;
		this.bpd = bpd;
		this.bpUnit = bpUnit;
		this.hFeet = hFeet;
		this.hInches = hInches;
		this.hcm = hcm;
		this.hUnit = hUnit;
		this.weight = weight;
		this.wUnit = wUnit;
		this.notes = notes;
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

	public float getBps() {
		return bps;
	}

	public void setBps(float bps) {
		this.bps = bps;
	}

	public float getBpd() {
		return bpd;
	}

	public void setBpd(float bpd) {
		this.bpd = bpd;
	}

	public String getBpUnit() {
		return bpUnit;
	}

	public void setBpUnit(String bpUnit) {
		this.bpUnit = bpUnit;
	}

	public int getHFeet() {
		return hFeet;
	}

	public void setHFeet(int hFeet) {
		this.hFeet = hFeet;
	}

	public int getHInches() {
		return hInches;
	}

	public void setHInches(int hInches) {
		this.hInches = hInches;
	}

	public int getHCm() {
		return hcm;
	}

	public void setHCm(int hcm) {
		this.hcm = hcm;
	}

	public String getHUnit() {
		return hUnit;
	}

	public void setHUnit(String hUnit) {
		this.hUnit = hUnit;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getWUnit() {
		return wUnit;
	}

	public void setWUnit(String wUnit) {
		this.wUnit = wUnit;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
