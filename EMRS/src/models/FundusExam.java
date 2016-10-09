package models;

import java.io.IOException;

import database.FundusTableGateway;
import database.FundusTableGatewaySQLite;
import database.GatewayException;

public class FundusExam {

	
	private long id;
	private long vid; // foreign key of visit it belongs to
	

	private int dialated;
	private String dialNotes;
	
	//cup to disc ratio
	private int CDODAb; 
	private String CDOD;
	private String CDODNotes;

	private int CDOSAb; 
	private String CDOS;
	private String CDOSNotes;

	private int retinaODAb; 
	private String retinaODNotes;
	
	private int retinaOSAb; 
	private String retinaOSNotes;
	
	private int maculaODAb; 
	private String maculaODNotes;
	
	private int maculaOSAb; 
	private String maculaOSNotes;
	
	private FundusTableGateway myGateway;
	
	public FundusExam(){
		try {
			myGateway = new FundusTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From AnteriorChamber, IO error");
			// e.printStackTrace();
		}
	}
	
	public FundusExam(long id, long vid, int dialated, String dialNotes, int cDODAb, String cDOD, String cDODNotes,
			int cDOSAb, String cDOS, String cDOSNotes, int retinaODAb, String retinaODNotes, int retinaOSAb,
			String retinaOSNotes, int maculaODAb, String maculaODNotes, int maculaOSAb, String maculaOSNotes) {
		
		this.id = id;
		this.vid = vid;
		this.dialated = dialated;
		this.dialNotes = dialNotes;
		CDODAb = cDODAb;
		CDOD = cDOD;
		CDODNotes = cDODNotes;
		CDOSAb = cDOSAb;
		CDOS = cDOS;
		CDOSNotes = cDOSNotes;
		this.retinaODAb = retinaODAb;
		this.retinaODNotes = retinaODNotes;
		this.retinaOSAb = retinaOSAb;
		this.retinaOSNotes = retinaOSNotes;
		this.maculaODAb = maculaODAb;
		this.maculaODNotes = maculaODNotes;
		this.maculaOSAb = maculaOSAb;
		this.maculaOSNotes = maculaOSNotes;
	}
	public FundusExam(int dialated, String dialNotes, int cDODAb, String cDOD, String cDODNotes,
			int cDOSAb, String cDOS, String cDOSNotes, int retinaODAb, String retinaODNotes, int retinaOSAb,
			String retinaOSNotes, int maculaODAb, String maculaODNotes, int maculaOSAb, String maculaOSNotes) {
		try {
			myGateway = new FundusTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From AnteriorChamber, IO error");
			// e.printStackTrace();
		}
		this.dialated = dialated;
		this.dialNotes = dialNotes;
		CDODAb = cDODAb;
		CDOD = cDOD;
		CDODNotes = cDODNotes;
		CDOSAb = cDOSAb;
		CDOS = cDOS;
		CDOSNotes = cDOSNotes;
		this.retinaODAb = retinaODAb;
		this.retinaODNotes = retinaODNotes;
		this.retinaOSAb = retinaOSAb;
		this.retinaOSNotes = retinaOSNotes;
		this.maculaODAb = maculaODAb;
		this.maculaODNotes = maculaODNotes;
		this.maculaOSAb = maculaOSAb;
		this.maculaOSNotes = maculaOSNotes;
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
	public int getDialated() {
		return dialated;
	}
	public void setDialated(int dialated) {
		this.dialated = dialated;
	}
	public String getDialNotes() {
		return dialNotes;
	}
	public void setDialNotes(String dialNotes) {
		this.dialNotes = dialNotes;
	}
	public int getCDODAb() {
		return CDODAb;
	}
	public void setCDODAb(int cDODAb) {
		CDODAb = cDODAb;
	}
	public String getCDOD() {
		return CDOD;
	}
	public void setCDOD(String cDOD) {
		CDOD = cDOD;
	}
	public String getCDODNotes() {
		return CDODNotes;
	}
	public void setCDODNotes(String cDODNotes) {
		CDODNotes = cDODNotes;
	}
	public int getCDOSAb() {
		return CDOSAb;
	}
	public void setCDOSAb(int cDOSAb) {
		CDOSAb = cDOSAb;
	}
	public String getCDOS() {
		return CDOS;
	}
	public void setCDOS(String cDOS) {
		CDOS = cDOS;
	}
	public String getCDOSNotes() {
		return CDOSNotes;
	}
	public void setCDOSNotes(String cDOSNotes) {
		CDOSNotes = cDOSNotes;
	}
	public int getRetinaODAb() {
		return retinaODAb;
	}
	public void setRetinaODAb(int retinaODAb) {
		this.retinaODAb = retinaODAb;
	}
	public String getRetinaODNotes() {
		return retinaODNotes;
	}
	public void setRetinaODNotes(String retinaODNotes) {
		this.retinaODNotes = retinaODNotes;
	}
	public int getRetinaOSAb() {
		return retinaOSAb;
	}
	public void setRetinaOSAb(int retinaOSAb) {
		this.retinaOSAb = retinaOSAb;
	}
	public String getRetinaOSNotes() {
		return retinaOSNotes;
	}
	public void setRetinaOSNotes(String retinaOSNotes) {
		this.retinaOSNotes = retinaOSNotes;
	}
	public int getMaculaODAb() {
		return maculaODAb;
	}
	public void setMaculaODAb(int maculaODAb) {
		this.maculaODAb = maculaODAb;
	}
	public String getMaculaODNotes() {
		return maculaODNotes;
	}
	public void setMaculaODNotes(String maculaODNotes) {
		this.maculaODNotes = maculaODNotes;
	}
	public int getMaculaOSAb() {
		return maculaOSAb;
	}
	public void setMaculaOSAb(int maculaOSAb) {
		this.maculaOSAb = maculaOSAb;
	}
	public String getMaculaOSNotes() {
		return maculaOSNotes;
	}
	public void setMaculaOSNotes(String maculaOSNotes) {
		this.maculaOSNotes = maculaOSNotes;
	}
	public FundusExam loadFE(long vid){
		try {
			return myGateway.fetchFundusExamForVisit(vid);
		} catch (GatewayException e) {
			System.err.println("From FundusExam, could not fetch from DB");
//			e.printStackTrace();
		}
		return null;
	}

	public long insertFundus(FundusExam fe) {
		long id= -1;
		
		try {
			id = myGateway.insertFundusExam(fe);
		} catch (GatewayException e) {
			System.err.println("From FundusExam: cannot insert to DB.");
//			e.printStackTrace();
		}
		
		
		return id;
	}
	
}
