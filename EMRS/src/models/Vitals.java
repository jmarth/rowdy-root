package models;

public class Vitals {
	
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



	public int gethFeet() {
		return hFeet;
	}



	public void sethFeet(int hFeet) {
		this.hFeet = hFeet;
	}



	public int gethInches() {
		return hInches;
	}



	public void sethInches(int hInches) {
		this.hInches = hInches;
	}



	public int getHcm() {
		return hcm;
	}



	public void setHcm(int hcm) {
		this.hcm = hcm;
	}



	public String gethUnit() {
		return hUnit;
	}



	public void sethUnit(String hUnit) {
		this.hUnit = hUnit;
	}



	public float getWeight() {
		return weight;
	}



	public void setWeight(float weight) {
		this.weight = weight;
	}



	public String getwUnit() {
		return wUnit;
	}



	public void setwUnit(String wUnit) {
		this.wUnit = wUnit;
	}
	
	public String getNotes() {
		return notes;
	}



	public void setNotes(String notes) {
		this.notes = notes;
	}



	//public Object getVitals() {
	//	retu
	//}
	
	
}
