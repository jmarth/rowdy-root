package models;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Gonio {
	private long id, vid;
	private boolean isHxFHA;
	private String FHASide;
	private boolean isODNormal;
	private String odABCDNon;
	private String odABCDComp;
	private String odRSQNon;
	private String odRSQComp;
	private int odPigment;
	private boolean isODAntPigLine;
	private boolean isOSNormal;
	private String osABCDNon;
	private String osABCDComp;
	private String osRSQNon;
	private String osRSQComp;
	private int osPigment;
	private boolean isOSAntPigLine;
	public Gonio(long id, long vid, boolean isHxFHA, String fHASide, boolean isODNormal, String odABCDNon,
			String odABCDComp, String odRSQNon, String odRSQComp, int odPigment, boolean isODAntPigLine,
			boolean isOSNormal, String osABCDNon, String osABCDComp, String osRSQNon, String osRSQComp, int osPigment,
			boolean isOSAntPigLine) {
		super();
		this.id = id;
		this.vid = vid;
		this.isHxFHA = isHxFHA;
		FHASide = fHASide;
		this.isODNormal = isODNormal;
		this.odABCDNon = odABCDNon;
		this.odABCDComp = odABCDComp;
		this.odRSQNon = odRSQNon;
		this.odRSQComp = odRSQComp;
		this.odPigment = odPigment;
		this.isODAntPigLine = isODAntPigLine;
		this.isOSNormal = isOSNormal;
		this.osABCDNon = osABCDNon;
		this.osABCDComp = osABCDComp;
		this.osRSQNon = osRSQNon;
		this.osRSQComp = osRSQComp;
		this.osPigment = osPigment;
		this.isOSAntPigLine = isOSAntPigLine;
	}
	public Gonio(boolean isHxFHA, String fHASide, boolean isODNormal, String odABCDNon,
			String odABCDComp, String odRSQNon, String odRSQComp, int odPigment, boolean isODAntPigLine,
			boolean isOSNormal, String osABCDNon, String osABCDComp, String osRSQNon, String osRSQComp, int osPigment,
			boolean isOSAntPigLine) {
		super();
		this.isHxFHA = isHxFHA;
		FHASide = fHASide;
		this.isODNormal = isODNormal;
		this.odABCDNon = odABCDNon;
		this.odABCDComp = odABCDComp;
		this.odRSQNon = odRSQNon;
		this.odRSQComp = odRSQComp;
		this.odPigment = odPigment;
		this.isODAntPigLine = isODAntPigLine;
		this.isOSNormal = isOSNormal;
		this.osABCDNon = osABCDNon;
		this.osABCDComp = osABCDComp;
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
	public boolean isHxFHA() {
		return isHxFHA;
	}
	public void setHxFHA(boolean isHxFHA) {
		this.isHxFHA = isHxFHA;
	}
	public String getFHASide() {
		return FHASide;
	}
	public void setFHASide(String fHASide) {
		FHASide = fHASide;
	}
	public boolean isODNormal() {
		return isODNormal;
	}
	public void setODNormal(boolean isODNormal) {
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
	public int getOdPigment() {
		return odPigment;
	}
	public void setOdPigment(int odPigment) {
		this.odPigment = odPigment;
	}
	public boolean isODAntPigLine() {
		return isODAntPigLine;
	}
	public void setODAntPigLine(boolean isODAntPigLine) {
		this.isODAntPigLine = isODAntPigLine;
	}
	public boolean isOSNormal() {
		return isOSNormal;
	}
	public void setOSNormal(boolean isOSNormal) {
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
	public int getOsPigment() {
		return osPigment;
	}
	public void setOsPigment(int osPigment) {
		this.osPigment = osPigment;
	}
	public boolean isOSAntPigLine() {
		return isOSAntPigLine;
	}
	public void setOSAntPigLine(boolean isOSAntPigLine) {
		this.isOSAntPigLine = isOSAntPigLine;
	}
	
	
}
