package models;

import java.io.IOException;

import database.GatewayException;
import database.LensTableGateway;
import database.LensTableGatewaySQLite;

public class Lens {
	
	private long id;
	private long vid;
	private String NS_OD;
	private String NS_OD_Notes;
	private String NS_OS;
	private String NS_OS_Notes;
	private int isStableLensOD;
	private int isStableLensOS;
	private int isPseudophakia_OD;
	private int isPseudophakia_OS;
	private int isPCO_OD;
	private int isPCO_OS;
	private String Coritcal_OD;
	private String Cortical_OD_Notes;
	private String Coritcal_OS;
	private String Cortical_OS_Notes;
	private String PSC_OD;
	private String PSC_OD_Notes;
	private String PSC_OS;
	private String PSC_OS_Notes;
	
	private LensTableGateway myGateway;
	
	public Lens() {
		try {
			myGateway = new LensTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From AnteriorChamber, IO error");
			// e.printStackTrace();
		}
	}
	
	public Lens(long id, long vid, String nS_OD, String nS_OD_Notes, String nS_OS, String nS_OS_Notes,
			int isStableLensOD, int isStableLensOS, int isPseudophakia_OD, int isPseudophakia_OS,
			int isPCO_OD, int isPCO_OS, String coritcal_OD, String cortical_OD_Notes, String coritcal_OS,
			String cortical_OS_Notes, String pSC_OD, String pSC_OD_Notes, String pSC_OS, String pSC_OS_Notes) {
		super();
		this.id = id;
		this.vid = vid;
		NS_OD = nS_OD;
		NS_OD_Notes = nS_OD_Notes;
		NS_OS = nS_OS;
		NS_OS_Notes = nS_OS_Notes;
		this.isStableLensOD = isStableLensOD;
		this.isStableLensOS = isStableLensOS;
		this.isPseudophakia_OD = isPseudophakia_OD;
		this.isPseudophakia_OS = isPseudophakia_OS;
		this.isPCO_OD = isPCO_OD;
		this.isPCO_OS = isPCO_OS;
		Coritcal_OD = coritcal_OD;
		Cortical_OD_Notes = cortical_OD_Notes;
		Coritcal_OS = coritcal_OS;
		Cortical_OS_Notes = cortical_OS_Notes;
		PSC_OD = pSC_OD;
		PSC_OD_Notes = pSC_OD_Notes;
		PSC_OS = pSC_OS;
		PSC_OS_Notes = pSC_OS_Notes;
	}
	
	public Lens(String nS_OD, String nS_OD_Notes, String nS_OS, String nS_OS_Notes,
			int isStableLensOD, int isStableLensOS, int isPseudophakia_OD, int isPseudophakia_OS,
			int isPCO_OD, int isPCO_OS, String coritcal_OD, String cortical_OD_Notes, String coritcal_OS,
			String cortical_OS_Notes, String pSC_OD, String pSC_OD_Notes, String pSC_OS, String pSC_OS_Notes) {

		try {
			myGateway = new LensTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From AnteriorChamber, IO error");
			// e.printStackTrace();
		}
		NS_OD = nS_OD;
		NS_OD_Notes = nS_OD_Notes;
		NS_OS = nS_OS;
		NS_OS_Notes = nS_OS_Notes;
		this.isStableLensOD = isStableLensOD;
		this.isStableLensOS = isStableLensOS;
		this.isPseudophakia_OD = isPseudophakia_OD;
		this.isPseudophakia_OS = isPseudophakia_OS;
		this.isPCO_OD = isPCO_OD;
		this.isPCO_OS = isPCO_OS;
		Coritcal_OD = coritcal_OD;
		Cortical_OD_Notes = cortical_OD_Notes;
		Coritcal_OS = coritcal_OS;
		Cortical_OS_Notes = cortical_OS_Notes;
		PSC_OD = pSC_OD;
		PSC_OD_Notes = pSC_OD_Notes;
		PSC_OS = pSC_OS;
		PSC_OS_Notes = pSC_OS_Notes;
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
	public String getNS_OD() {
		return NS_OD;
	}
	public void setNS_OD(String nS_OD) {
		NS_OD = nS_OD;
	}
	public String getNS_OD_Notes() {
		return NS_OD_Notes;
	}
	public void setNS_OD_Notes(String nS_OD_Notes) {
		NS_OD_Notes = nS_OD_Notes;
	}
	public String getNS_OS() {
		return NS_OS;
	}
	public void setNS_OS(String nS_OS) {
		NS_OS = nS_OS;
	}
	public String getNS_OS_Notes() {
		return NS_OS_Notes;
	}
	public void setNS_OS_Notes(String nS_OS_Notes) {
		NS_OS_Notes = nS_OS_Notes;
	}
	public int isStableLensOD() {
		return isStableLensOD;
	}
	public void setStableLensOD(int isStableLensOD) {
		this.isStableLensOD = isStableLensOD;
	}
	public int isStableLensOS() {
		return isStableLensOS;
	}
	public void setStableLensOS(int isStableLensOS) {
		this.isStableLensOS = isStableLensOS;
	}
	public int isPseudophakia_OD() {
		return isPseudophakia_OD;
	}
	public void setPseudophakia_OD(int isPseudophakia_OD) {
		this.isPseudophakia_OD = isPseudophakia_OD;
	}
	public int isPseudophakia_OS() {
		return isPseudophakia_OS;
	}
	public void setPseudophakia_OS(int isPseudophakia_OS) {
		this.isPseudophakia_OS = isPseudophakia_OS;
	}
	public int isPCO_OD() {
		return isPCO_OD;
	}
	public void setPCO_OD(int isPCO_OD) {
		this.isPCO_OD = isPCO_OD;
	}
	public int isPCO_OS() {
		return isPCO_OS;
	}
	public void setPCO_OS(int isPCO_OS) {
		this.isPCO_OS = isPCO_OS;
	}
	public String getCoritcal_OD() {
		return Coritcal_OD;
	}
	public void setCoritcal_OD(String coritcal_OD) {
		Coritcal_OD = coritcal_OD;
	}
	public String getCortical_OD_Notes() {
		return Cortical_OD_Notes;
	}
	public void setCortical_OD_Notes(String cortical_OD_Notes) {
		Cortical_OD_Notes = cortical_OD_Notes;
	}
	public String getCoritcal_OS() {
		return Coritcal_OS;
	}
	public void setCoritcal_OS(String coritcal_OS) {
		Coritcal_OS = coritcal_OS;
	}
	public String getCortical_OS_Notes() {
		return Cortical_OS_Notes;
	}
	public void setCortical_OS_Notes(String cortical_OS_Notes) {
		Cortical_OS_Notes = cortical_OS_Notes;
	}
	public String getPSC_OD() {
		return PSC_OD;
	}
	public void setPSC_OD(String pSC_OD) {
		PSC_OD = pSC_OD;
	}
	public String getPSC_OD_Notes() {
		return PSC_OD_Notes;
	}
	public void setPSC_OD_Notes(String pSC_OD_Notes) {
		PSC_OD_Notes = pSC_OD_Notes;
	}
	public String getPSC_OS() {
		return PSC_OS;
	}
	public void setPSC_OS(String pSC_OS) {
		PSC_OS = pSC_OS;
	}
	public String getPSC_OS_Notes() {
		return PSC_OS_Notes;
	}
	public void setPSC_OS_Notes(String pSC_OS_Notes) {
		PSC_OS_Notes = pSC_OS_Notes;
	}

	public Lens loadLens(long vid) {
		try {
			return myGateway.fetchLensForVisit(vid);
		} catch (GatewayException e) {
			System.err.println("From Lens, could not fetch from DB");
//			e.printStackTrace();
		}
		return null;
	}

	public long insertLens(Lens l) {
		long id= -1;
		
		try {
			id = myGateway.insertLens(l);
		} catch (GatewayException e) {
			System.err.println("From Lens: cannot insert to DB.");
//			e.printStackTrace();
		}
		
		
		return id;
	}

	public void updateLens(Lens l) {
		try {
			myGateway.updateLens(l);
		} catch (GatewayException e) {
			System.err.println("From Lens: cannot update to DB.");
//			e.printStackTrace();
		}		
	}
}
	
	
