package models;

import java.io.IOException;

import database.GatewayException;
import database.GonioTableGateway;
import database.GonioTableGatewaySQLite;

public class Gonio {
	
	private long id, vid;
	
	private int isHxFHA;
	private String FHASide;
	
	private int isODNormal;
	private String odABCDNon;
	private String odABCDComp;
	private String odDegreeNon;
	private String odDegreeComp;
	private String odRSQNon;
	private String odRSQComp;
	private String odPigment;
	private int isODAntPigLine;
	
	private int isOSNormal;
	private String osABCDNon;
	private String osABCDComp;
	private String osDegreeNon;
	private String osDegreeComp;
	private String osRSQNon;
	private String osRSQComp;
	private String osPigment;
	private int isOSAntPigLine;
	
	private GonioTableGateway myGateway;
	
	
	public Gonio(long id, long vid, int isHxFHA, String fHASide, int isODNormal, String odABCDNon,
			String odABCDComp, String odDegreeNon, String odDegreeComp, String odRSQNon, String odRSQComp, String odPigment, int isODAntPigLine,
			int isOSNormal, String osABCDNon, String osABCDComp, String osDegreeNon, String osDegreeComp, String osRSQNon, String osRSQComp, String osPigment,
			int isOSAntPigLine) {
		super();
		this.id = id;
		this.vid = vid;
		this.isHxFHA = isHxFHA;
		FHASide = fHASide;
		this.isODNormal = isODNormal;
		this.odABCDNon = odABCDNon;
		this.odABCDComp = odABCDComp;
		this.setOdDegreeNon(odDegreeNon);
		this.setOdDegreeComp(odDegreeComp);
		this.odRSQNon = odRSQNon;
		this.odRSQComp = odRSQComp;
		this.odPigment = odPigment;
		this.isODAntPigLine = isODAntPigLine;
		this.isOSNormal = isOSNormal;
		this.osABCDNon = osABCDNon;
		this.osABCDComp = osABCDComp;
		this.setOsDegreeNon(osDegreeNon);
		this.setOsDegreeComp(osDegreeComp);
		this.osRSQNon = osRSQNon;
		this.osRSQComp = osRSQComp;
		this.osPigment = osPigment;
		this.isOSAntPigLine = isOSAntPigLine;
	}
	

	public Gonio(int isHxFHA, String fHASide, int isODNormal, String odABCDNon,
			String odABCDComp, String odDegreeNon, String odDegreeComp, String odRSQNon, String odRSQComp, String odPigment, int isODAntPigLine,
			int isOSNormal, String osABCDNon, String osABCDComp, String osDegreeNon, String osDegreeComp, String osRSQNon, String osRSQComp, String osPigment,
			int isOSAntPigLine) {
		try {
			myGateway = new GonioTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From AnteriorChamber, IO error");
			// e.printStackTrace();
		}
		this.isHxFHA = isHxFHA;
		FHASide = fHASide;
		this.isODNormal = isODNormal;
		this.odABCDNon = odABCDNon;
		this.odABCDComp = odABCDComp;
		this.setOdDegreeNon(odDegreeNon);
		this.setOdDegreeComp(odDegreeComp);
		this.odRSQNon = odRSQNon;
		this.odRSQComp = odRSQComp;
		this.odPigment = odPigment;
		this.isODAntPigLine = isODAntPigLine;
		this.isOSNormal = isOSNormal;
		this.osABCDNon = osABCDNon;
		this.osABCDComp = osABCDComp;
		this.setOsDegreeNon(osDegreeNon);
		this.setOsDegreeComp(osDegreeComp);
		this.osRSQNon = osRSQNon;
		this.osRSQComp = osRSQComp;
		this.osPigment = osPigment;
		this.isOSAntPigLine = isOSAntPigLine;
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
	public int isHxFHA() {
		return isHxFHA;
	}
	public void setHxFHA(int isHxFHA) {
		this.isHxFHA = isHxFHA;
	}
	public String getFHASide() {
		return FHASide;
	}
	public void setFHASide(String fHASide) {
		FHASide = fHASide;
	}
	public int isODNormal() {
		return isODNormal;
	}
	public void setODNormal(int isODNormal) {
		this.isODNormal = isODNormal;
	}
	public String getOdABCDNon() {
		return odABCDNon;
	}
	public void setOdABCDNon(String odABCDNon) {
		this.odABCDNon = odABCDNon;
	}
	public String getOdABCDComp() {
		return odABCDComp;
	}
	public void setOdABCDComp(String odABCDComp) {
		this.odABCDComp = odABCDComp;
	}
	public String getOdRSQNon() {
		return odRSQNon;
	}
	public void setOdRSQNon(String odRSQNon) {
		this.odRSQNon = odRSQNon;
	}
	public String getOdRSQComp() {
		return odRSQComp;
	}
	public void setOdRSQComp(String odRSQComp) {
		this.odRSQComp = odRSQComp;
	}
	public String getOdPigment() {
		return odPigment;
	}
	public void setOdPigment(String odPigment) {
		this.odPigment = odPigment;
	}
	public int isODAntPigLine() {
		return isODAntPigLine;
	}
	public void setODAntPigLine(int isODAntPigLine) {
		this.isODAntPigLine = isODAntPigLine;
	}
	public int isOSNormal() {
		return isOSNormal;
	}
	public void setOSNormal(int isOSNormal) {
		this.isOSNormal = isOSNormal;
	}
	public String getOsABCDNon() {
		return osABCDNon;
	}
	public void setOsABCDNon(String osABCDNon) {
		this.osABCDNon = osABCDNon;
	}
	public String getOsABCDComp() {
		return osABCDComp;
	}
	public void setOsABCDComp(String osABCDComp) {
		this.osABCDComp = osABCDComp;
	}
	public String getOsRSQNon() {
		return osRSQNon;
	}
	public void setOsRSQNon(String osRSQNon) {
		this.osRSQNon = osRSQNon;
	}
	public String getOsRSQComp() {
		return osRSQComp;
	}
	public void setOsRSQComp(String osRSQComp) {
		this.osRSQComp = osRSQComp;
	}
	public String getOsPigment() {
		return osPigment;
	}
	public void setOsPigment(String osPigment) {
		this.osPigment = osPigment;
	}
	public int isOSAntPigLine() {
		return isOSAntPigLine;
	}
	public void setOSAntPigLine(int isOSAntPigLine) {
		this.isOSAntPigLine = isOSAntPigLine;
	}

	public String getOdDegreeNon() {
		return odDegreeNon;
	}

	public void setOdDegreeNon(String odDegreeNon) {
		this.odDegreeNon = odDegreeNon;
	}

	public String getOdDegreeComp() {
		return odDegreeComp;
	}

	public void setOdDegreeComp(String odDegreeComp) {
		this.odDegreeComp = odDegreeComp;
	}

	public String getOsDegreeNon() {
		return osDegreeNon;
	}

	public void setOsDegreeNon(String osDegreeNon) {
		this.osDegreeNon = osDegreeNon;
	}

	public String getOsDegreeComp() {
		return osDegreeComp;
	}

	public void setOsDegreeComp(String osDegreeComp) {
		this.osDegreeComp = osDegreeComp;
	}


	public Gonio loadGonio(long vid) {
		try {
			return myGateway.fetchGonioForVisit(vid);
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, could not fetch from DB");
//			e.printStackTrace();
		}
		return null;
	}
	
	
}
