package models;

public class AnteriorChamber {
	private long id;
	private long vid;
	private boolean ACODNormal;
	private boolean ACOSNormal;
	private int ACDepthOD;
	private int ACDepthOS;
	private String ACAngleOD;
	private String ACAngleOS;
	private String PASOD;
	private String PASOS;
	private int ACODKP;
	private int ACOSKP;
	private boolean ShuntOD;
	private boolean ScarringOD;
	private boolean TraumaOD;
	private boolean BlebOD;
	private boolean ShuntOS;
	private boolean ScarringOS;
	private boolean TraumaOS;
	private boolean BlebOS;
	private boolean VascularOD;
	private int BlebOD_Num;
	private boolean VascularOS;
	private int BlebOS_Num;
	private boolean KSpindleOD;
	private boolean KSpindleOS;
	
	public AnteriorChamber(long id, long vid, boolean aCODNormal, boolean aCOSNormal, int aCDepthOD, int aCDepthOS,
			String aCAngleOD, String aCAngleOS, String pASOD, String pASOS, int aCODKP, int aCOSKP, boolean shuntOD,
			boolean scarringOD, boolean traumaOD, boolean blebOD, boolean shuntOS, boolean scarringOS, boolean traumaOS,
			boolean blebOS, boolean vascularOD, int blebOD_Num, boolean vascularOS, int blebOS_Num, boolean kSpindleOD,
			boolean kSpindleOS) {
		super();
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
	public AnteriorChamber(boolean aCODNormal, boolean aCOSNormal, int aCDepthOD, int aCDepthOS,
			String aCAngleOD, String aCAngleOS, String pASOD, String pASOS, int aCODKP, int aCOSKP, boolean shuntOD,
			boolean scarringOD, boolean traumaOD, boolean blebOD, boolean shuntOS, boolean scarringOS, boolean traumaOS,
			boolean blebOS, boolean vascularOD, int blebOD_Num, boolean vascularOS, int blebOS_Num, boolean kSpindleOD,
			boolean kSpindleOS) {
		super();
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
	public boolean isACODNormal() {
		return ACODNormal;
	}
	public void setACODNormal(boolean aCODNormal) {
		ACODNormal = aCODNormal;
	}
	public boolean isACOSNormal() {
		return ACOSNormal;
	}
	public void setACOSNormal(boolean aCOSNormal) {
		ACOSNormal = aCOSNormal;
	}
	public int getACDepthOD() {
		return ACDepthOD;
	}
	public void setACDepthOD(int aCDepthOD) {
		ACDepthOD = aCDepthOD;
	}
	public int getACDepthOS() {
		return ACDepthOS;
	}
	public void setACDepthOS(int aCDepthOS) {
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
	public int getACODKP() {
		return ACODKP;
	}
	public void setACODKP(int aCODKP) {
		ACODKP = aCODKP;
	}
	public int getACOSKP() {
		return ACOSKP;
	}
	public void setACOSKP(int aCOSKP) {
		ACOSKP = aCOSKP;
	}
	public boolean isShuntOD() {
		return ShuntOD;
	}
	public void setShuntOD(boolean shuntOD) {
		ShuntOD = shuntOD;
	}
	public boolean isScarringOD() {
		return ScarringOD;
	}
	public void setScarringOD(boolean scarringOD) {
		ScarringOD = scarringOD;
	}
	public boolean isTraumaOD() {
		return TraumaOD;
	}
	public void setTraumaOD(boolean traumaOD) {
		TraumaOD = traumaOD;
	}
	public boolean isBlebOD() {
		return BlebOD;
	}
	public void setBlebOD(boolean blebOD) {
		BlebOD = blebOD;
	}
	public boolean isShuntOS() {
		return ShuntOS;
	}
	public void setShuntOS(boolean shuntOS) {
		ShuntOS = shuntOS;
	}
	public boolean isScarringOS() {
		return ScarringOS;
	}
	public void setScarringOS(boolean scarringOS) {
		ScarringOS = scarringOS;
	}
	public boolean isTraumaOS() {
		return TraumaOS;
	}
	public void setTraumaOS(boolean traumaOS) {
		TraumaOS = traumaOS;
	}
	public boolean isBlebOS() {
		return BlebOS;
	}
	public void setBlebOS(boolean blebOS) {
		BlebOS = blebOS;
	}
	public boolean isVascularOD() {
		return VascularOD;
	}
	public void setVascularOD(boolean vascularOD) {
		VascularOD = vascularOD;
	}
	public int getBlebOD_Num() {
		return BlebOD_Num;
	}
	public void setBlebOD_Num(int blebOD_Num) {
		BlebOD_Num = blebOD_Num;
	}
	public boolean isVascularOS() {
		return VascularOS;
	}
	public void setVascularOS(boolean vascularOS) {
		VascularOS = vascularOS;
	}
	public int getBlebOS_Num() {
		return BlebOS_Num;
	}
	public void setBlebOS_Num(int blebOS_Num) {
		BlebOS_Num = blebOS_Num;
	}
	public boolean isKSpindleOD() {
		return KSpindleOD;
	}
	public void setKSpindleOD(boolean kSpindleOD) {
		KSpindleOD = kSpindleOD;
	}
	public boolean isKSpindleOS() {
		return KSpindleOS;
	}
	public void setKSpindleOS(boolean kSpindleOS) {
		KSpindleOS = kSpindleOS;
	}
	
	
}
