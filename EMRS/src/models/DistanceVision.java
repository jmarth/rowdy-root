package models;

public class DistanceVision {
	
	private long id;
	private long vid;
	private String DVODSC;
	private String DVOSSC;
	private String DVODCC;
	private String DVOSCC;
	
	public DistanceVision(String dVODSC, String dVOSSC, String dVODCC, String dVOSCC) {
		super();
		DVODSC = dVODSC;
		DVOSSC = dVOSSC;
		DVODCC = dVODCC;
		DVOSCC = dVOSCC;
	}
	
	public DistanceVision(long id, long vid, String dVODSC, String dVOSSC, String dVODCC, String dVOSCC) {
		super();
		this.id = id;
		this.vid = vid;
		DVODSC = dVODSC;
		DVOSSC = dVOSSC;
		DVODCC = dVODCC;
		DVOSCC = dVOSCC;
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

	public String getDVODSC() {
		return DVODSC;
	}

	public void setDVODSC(String dVODSC) {
		DVODSC = dVODSC;
	}

	public String getDVOSSC() {
		return DVOSSC;
	}

	public void setDVOSSC(String dVOSSC) {
		DVOSSC = dVOSSC;
	}

	public String getDVODCC() {
		return DVODCC;
	}

	public void setDVODCC(String dVODCC) {
		DVODCC = dVODCC;
	}

	public String getDVOSCC() {
		return DVOSCC;
	}

	public void setDVOSCC(String dVOSCC) {
		DVOSCC = dVOSCC;
	}

	
}
