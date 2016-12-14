package models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Vital implements Serializable{

	public static final String MMHG = "mm/Hg";
	public static final String PA = "Pa";

	public static final String FTIN = "ft/in";
	public static final String IN = "in";
	public static final String CM = "cm";

	public static final String LBS = "lbs";
	public static final String KG = "kg";

	public static final String mmolL = "mmol/L";
	public static final String mgdL = "mg/dL";

	public static final String gdL = "g/dL";

	private long id;
	private long pid;

	private float bps;
	private float bpd;
	private String bpUnit;

	private boolean fasting;
	private float bg;
	private String bgUnit;

	private float o2sat;

	private float hb;

	private int hFeet;
	private int hInches;

	private int hcm;

	private String hUnit;

	private float weight;
	private String wUnit;

	private String notes;
	private String dateCreated;

	public Vital(long id, long pid, float bps, float bpd, String bpUnit, boolean fasting, float bg, String bgUnit,
			float o2sat, float hb, int hFeet, int hInches, int hcm, String hUnit, float weight, String wUnit,
			String notes, String dateCreated) {

		super();
		this.id = id;
		this.pid = pid;
		this.bps = bps;
		this.bpd = bpd;
		this.bpUnit = bpUnit;
		this.setFasting(fasting);
		this.setBg(bg);
		this.setBgUnit(bgUnit);
		this.setO2sat(o2sat);
		this.setHb(hb);
		this.hFeet = hFeet;
		this.hInches = hInches;
		this.hcm = hcm;
		this.hUnit = hUnit;
		this.weight = weight;
		this.wUnit = wUnit;
		this.notes = notes;
		this.dateCreated =dateCreated;

	}
	
	public Vital(long id, long pid, float bps, float bpd, String bpUnit, boolean fasting, float bg, String bgUnit,
			float o2sat, float hb, int hFeet, int hInches, int hcm, String hUnit, float weight, String wUnit,
			String notes) {

		super();
		this.id = id;
		this.pid = pid;
		this.bps = bps;
		this.bpd = bpd;
		this.bpUnit = bpUnit;
		this.setFasting(fasting);
		this.setBg(bg);
		this.setBgUnit(bgUnit);
		this.setO2sat(o2sat);
		this.setHb(hb);
		this.hFeet = hFeet;
		this.hInches = hInches;
		this.hcm = hcm;
		this.hUnit = hUnit;
		this.weight = weight;
		this.wUnit = wUnit;
		this.notes = notes;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.dateCreated = dateFormat.format(date);

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

	public String toString() {
		return "id : " + id + " pid: " + pid + " ft: " + hFeet + " in: " + hInches + " cm: " + hcm;
	}

	public boolean isFasting() {
		return fasting;
	}

	public void setFasting(boolean fasting) {
		this.fasting = fasting;
	}

	public float getBg() {
		return bg;
	}

	public void setBg(float bg) {
		this.bg = bg;
	}

	public String getBgUnit() {
		return bgUnit;
	}

	public void setBgUnit(String bgUnit) {
		this.bgUnit = bgUnit;
	}

	public float getO2sat() {
		return o2sat;
	}

	public void setO2sat(float o2sat) {
		this.o2sat = o2sat;
	}

	public float getHb() {
		return hb;
	}

	public void setHb(float hb) {
		this.hb = hb;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
}
