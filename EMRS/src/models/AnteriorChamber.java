package models;

import java.io.IOException;

import database.ACTableGateway;
import database.ACTableGatewaySQLite;
import database.GatewayException;

public class AnteriorChamber {
	private long id;
	private long vid;

	private int ACODNormal;
	private int ACOSNormal;

	private String ACDepthOD;
	private String ACDepthOS;

	private String ACAngleOD;
	private String ACAngleOS;

	private String PASOD;
	private String PASOS;

	private String ACODKP;
	private String ACOSKP;

	private int ShuntOD;
	private int ScarringOD;

	private int TraumaOD;
	private int BlebOD;

	private int ShuntOS;
	private int ScarringOS;

	private int TraumaOS;
	private int BlebOS;

	private int VascularOD;
	private String BlebOD_Num;

	private int VascularOS;
	private String BlebOS_Num;

	private int KSpindleOD;
	private int KSpindleOS;

	private ACTableGateway myGateway;
	
	public AnteriorChamber() {
		try {
			myGateway = new ACTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From AnteriorChamber, IO error");
			// e.printStackTrace();
		}
	}

	public AnteriorChamber(long id, long vid, int aCODNormal, int aCOSNormal, String aCDepthOD, String aCDepthOS,
			String aCAngleOD, String aCAngleOS, String pASOD, String pASOS, String aCODKP, String aCOSKP, int shuntOD,
			int scarringOD, int traumaOD, int blebOD, int shuntOS, int scarringOS, int traumaOS, int blebOS,
			int vascularOD, String blebOD_Num, int vascularOS, String blebOS_Num, int kSpindleOD, int kSpindleOS) {
		
		this.id = id;
		this.vid = vid;
		ACODNormal = aCODNormal;
		ACOSNormal = aCOSNormal;
		ACDepthOD = aCDepthOD;
		ACDepthOS = aCDepthOS;
		ACAngleOD = aCAngleOD;
		ACAngleOS = aCAngleOS;
		PASOD = pASOD;
		PASOS = pASOS;
		ACODKP = aCODKP;
		ACOSKP = aCOSKP;
		ShuntOD = shuntOD;
		ScarringOD = scarringOD;
		TraumaOD = traumaOD;
		BlebOD = blebOD;
		ShuntOS = shuntOS;
		ScarringOS = scarringOS;
		TraumaOS = traumaOS;
		BlebOS = blebOS;
		VascularOD = vascularOD;
		BlebOD_Num = blebOD_Num;
		VascularOS = vascularOS;
		BlebOS_Num = blebOS_Num;
		KSpindleOD = kSpindleOD;
		KSpindleOS = kSpindleOS;
	}

	public AnteriorChamber(int aCODNormal, int aCOSNormal, String aCDepthOD, String aCDepthOS, String aCAngleOD,
			String aCAngleOS, String pASOD, String pASOS, String aCODKP, String aCOSKP, int shuntOD, int scarringOD,
			int traumaOD, int blebOD, int shuntOS, int scarringOS, int traumaOS, int blebOS, int vascularOD,
			String blebOD_Num, int vascularOS, String blebOS_Num, int kSpindleOD, int kSpindleOS) {

		try {
			myGateway = new ACTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From AnteriorChamber, IO error");
			// e.printStackTrace();
		}
		ACODNormal = aCODNormal;
		ACOSNormal = aCOSNormal;
		ACDepthOD = aCDepthOD;
		ACDepthOS = aCDepthOS;
		ACAngleOD = aCAngleOD;
		ACAngleOS = aCAngleOS;
		PASOD = pASOD;
		PASOS = pASOS;
		ACODKP = aCODKP;
		ACOSKP = aCOSKP;
		ShuntOD = shuntOD;
		ScarringOD = scarringOD;
		TraumaOD = traumaOD;
		BlebOD = blebOD;
		ShuntOS = shuntOS;
		ScarringOS = scarringOS;
		TraumaOS = traumaOS;
		BlebOS = blebOS;
		VascularOD = vascularOD;
		BlebOD_Num = blebOD_Num;
		VascularOS = vascularOS;
		BlebOS_Num = blebOS_Num;
		KSpindleOD = kSpindleOD;
		KSpindleOS = kSpindleOS;
	}

	public long insertAC(AnteriorChamber ac) {

		long id = -1;
		
		try {
			
			id = myGateway.insertAnteriorChamber(ac);
			//ac.setId(id);
			
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, cannot insert to DB");
//			e.printStackTrace();
		}
		
		return id;
	}
	
	public void updateAC(AnteriorChamber ac){
		try {
			myGateway.updateAnteriorChamber(ac);
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, cannot update to DB");
//			e.printStackTrace();
		}
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

	public int isACODNormal() {
		return ACODNormal;
	}

	public void setACODNormal(int aCODNormal) {
		ACODNormal = aCODNormal;
	}

	public int isACOSNormal() {
		return ACOSNormal;
	}

	public void setACOSNormal(int aCOSNormal) {
		ACOSNormal = aCOSNormal;
	}

	public String getACDepthOD() {
		return ACDepthOD;
	}

	public void setACDepthOD(String aCDepthOD) {
		ACDepthOD = aCDepthOD;
	}

	public String getACDepthOS() {
		return ACDepthOS;
	}

	public void setACDepthOS(String aCDepthOS) {
		ACDepthOS = aCDepthOS;
	}

	public String getACAngleOD() {
		return ACAngleOD;
	}

	public void setACAngleOD(String aCAngleOD) {
		ACAngleOD = aCAngleOD;
	}

	public String getACAngleOS() {
		return ACAngleOS;
	}

	public void setACAngleOS(String aCAngleOS) {
		ACAngleOS = aCAngleOS;
	}

	public String getPASOD() {
		return PASOD;
	}

	public void setPASOD(String pASOD) {
		PASOD = pASOD;
	}

	public String getPASOS() {
		return PASOS;
	}

	public void setPASOS(String pASOS) {
		PASOS = pASOS;
	}

	public String getACODKP() {
		return ACODKP;
	}

	public void setACODKP(String aCODKP) {
		ACODKP = aCODKP;
	}

	public String getACOSKP() {
		return ACOSKP;
	}

	public void setACOSKP(String aCOSKP) {
		ACOSKP = aCOSKP;
	}

	public int isShuntOD() {
		return ShuntOD;
	}

	public void setShuntOD(int shuntOD) {
		ShuntOD = shuntOD;
	}

	public int isScarringOD() {
		return ScarringOD;
	}

	public void setScarringOD(int scarringOD) {
		ScarringOD = scarringOD;
	}

	public int isTraumaOD() {
		return TraumaOD;
	}

	public void setTraumaOD(int traumaOD) {
		TraumaOD = traumaOD;
	}

	public int isBlebOD() {
		return BlebOD;
	}

	public void setBlebOD(int blebOD) {
		BlebOD = blebOD;
	}

	public int isShuntOS() {
		return ShuntOS;
	}

	public void setShuntOS(int shuntOS) {
		ShuntOS = shuntOS;
	}

	public int isScarringOS() {
		return ScarringOS;
	}

	public void setScarringOS(int scarringOS) {
		ScarringOS = scarringOS;
	}

	public int isTraumaOS() {
		return TraumaOS;
	}

	public void setTraumaOS(int traumaOS) {
		TraumaOS = traumaOS;
	}

	public int isBlebOS() {
		return BlebOS;
	}

	public void setBlebOS(int blebOS) {
		BlebOS = blebOS;
	}

	public int isVascularOD() {
		return VascularOD;
	}

	public void setVascularOD(int vascularOD) {
		VascularOD = vascularOD;
	}

	public String getBlebOD_Num() {
		return BlebOD_Num;
	}

	public void setBlebOD_Num(String blebOD_Num) {
		BlebOD_Num = blebOD_Num;
	}

	public int isVascularOS() {
		return VascularOS;
	}

	public void setVascularOS(int vascularOS) {
		VascularOS = vascularOS;
	}

	public String getBlebOS_Num() {
		return BlebOS_Num;
	}

	public void setBlebOS_Num(String blebOS_Num) {
		BlebOS_Num = blebOS_Num;
	}

	public int isKSpindleOD() {
		return KSpindleOD;
	}

	public void setKSpindleOD(int kSpindleOD) {
		KSpindleOD = kSpindleOD;
	}

	public int isKSpindleOS() {
		return KSpindleOS;
	}

	public void setKSpindleOS(int kSpindleOS) {
		KSpindleOS = kSpindleOS;
	}

	public AnteriorChamber loadAC(long vid){
		try {
			return myGateway.fetchAnteriorChamberForVisit(vid);
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, could not fetch from DB");
//			e.printStackTrace();
		}
		return null;
	}

}
