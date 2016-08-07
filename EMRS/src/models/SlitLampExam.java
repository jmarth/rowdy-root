package models;

public class SlitLampExam {
	
	private long id;
	private long vid; // foreign key of visit it belongs to
	
	
	private boolean ODPupilsAb; 
	private String ODPupils;
	private boolean OSPupilsAb; 
	private String OSPupils;
	private boolean ODACAb; 
	private String ODAC;
	private boolean OSACAb; 
	private String OSAC;
	private boolean ODPseudoPh; 
	private boolean OSPseudoPh; 
	private boolean ODPCO; 
	private boolean OSPCO; 
	private String ODNS;
	private String ODNSNotes;
	private String OSNS;
	private String OSNSNotes;
	private String ODCortical;
	private String ODCorticalNotes;
	private String OSCortical;
	private String OSCorticalNotes;
	private String ODPSC;
	private String ODPSCNotes;
	private String OSPSC;
	private String OSPSCNotes;
	
	private String dateCreated;
	
	public SlitLampExam(long id, long vid, boolean oDPupilsAb, String oDPupils, boolean oSPupilsAb, String oSPupils,
			boolean oDACAb, String oDAC, boolean oSACAb, String oSAC, boolean oDPseudoPh, boolean oSPseudoPh,
			boolean oDPCO, boolean oSPCO, String oDNS, String oDNSNotes, String oSNS, String oSNSNotes, String oDCortical,
			String oDCorticalNotes, String oSCortical, String oSCorticalNotes, String oDPSC, String oDPSCNotes,
			String oSPSC, String oSPSCNotes, String dateCreated) {
		super();
		this.id = id;
		this.vid = vid;
		ODPupilsAb = oDPupilsAb;
		ODPupils = oDPupils;
		OSPupilsAb = oSPupilsAb;
		OSPupils = oSPupils;
		ODACAb = oDACAb;
		ODAC = oDAC;
		OSACAb = oSACAb;
		OSAC = oSAC;
		ODPseudoPh = oDPseudoPh;
		OSPseudoPh = oSPseudoPh;
		ODPCO = oDPCO;
		OSPCO = oSPCO;
		ODNS = oDNS;
		ODNSNotes = oDNSNotes;
		OSNS = oSNS;
		OSNSNotes = oSNSNotes;
		ODCortical = oDCortical;
		ODCorticalNotes = oDCorticalNotes;
		OSCortical = oSCortical;
		OSCorticalNotes = oSCorticalNotes;
		ODPSC = oDPSC;
		ODPSCNotes = oDPSCNotes;
		OSPSC = oSPSC;
		OSPSCNotes = oSPSCNotes;
		this.dateCreated = dateCreated;

	}
	
	public SlitLampExam(long vid, boolean oDPupilsAb, String oDPupils, boolean oSPupilsAb, String oSPupils,
			boolean oDACAb, String oDAC, boolean oSACAb, String oSAC, boolean oDPseudoPh, boolean oSPseudoPh,
			boolean oDPCO, boolean oSPCO, String oDNS, String oDNSNotes, String oSNS, String oSNSNotes, String oDCortical,
			String oDCorticalNotes, String oSCortical, String oSCorticalNotes, String oDPSC, String oDPSCNotes,
			String oSPSC, String oSPSCNotes) {
		super();
		this.vid = vid;
		ODPupilsAb = oDPupilsAb;
		ODPupils = oDPupils;
		OSPupilsAb = oSPupilsAb;
		OSPupils = oSPupils;
		ODACAb = oDACAb;
		ODAC = oDAC;
		OSACAb = oSACAb;
		OSAC = oSAC;
		ODPseudoPh = oDPseudoPh;
		OSPseudoPh = oSPseudoPh;
		ODPCO = oDPCO;
		OSPCO = oSPCO;
		ODNS = oDNS;
		ODNSNotes = oDNSNotes;
		OSNS = oSNS;
		OSNSNotes = oSNSNotes;
		ODCortical = oDCortical;
		ODCorticalNotes = oDCorticalNotes;
		OSCortical = oSCortical;
		OSCorticalNotes = oSCorticalNotes;
		ODPSC = oDPSC;
		ODPSCNotes = oDPSCNotes;
		OSPSC = oSPSC;
		OSPSCNotes = oSPSCNotes;
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

	public boolean isODPupilsAb() {
		return ODPupilsAb;
	}

	public void setODPupilsAb(boolean oDPupilsAb) {
		ODPupilsAb = oDPupilsAb;
	}

	public String getODPupils() {
		return ODPupils;
	}

	public void setODPupils(String oDPupils) {
		ODPupils = oDPupils;
	}

	public boolean isOSPupilsAb() {
		return OSPupilsAb;
	}

	public void setOSPupilsAb(boolean oSPupilsAb) {
		OSPupilsAb = oSPupilsAb;
	}

	public String getOSPupils() {
		return OSPupils;
	}

	public void setOSPupils(String oSPupils) {
		OSPupils = oSPupils;
	}

	public boolean isODACAb() {
		return ODACAb;
	}

	public void setODACAb(boolean oDACAb) {
		ODACAb = oDACAb;
	}

	public String getODAC() {
		return ODAC;
	}

	public void setODAC(String oDAC) {
		ODAC = oDAC;
	}

	public boolean isOSACAb() {
		return OSACAb;
	}

	public void setOSACAb(boolean oSACAb) {
		OSACAb = oSACAb;
	}

	public String getOSAC() {
		return OSAC;
	}

	public void setOSAC(String oSAC) {
		OSAC = oSAC;
	}

	public boolean isODPseudoPh() {
		return ODPseudoPh;
	}

	public void setODPseudoPh(boolean oDPseudoPh) {
		ODPseudoPh = oDPseudoPh;
	}

	public boolean isOSPseudoPh() {
		return OSPseudoPh;
	}

	public void setOSPseudoPh(boolean oSPseudoPh) {
		OSPseudoPh = oSPseudoPh;
	}

	public boolean isODPCO() {
		return ODPCO;
	}

	public void setODPCO(boolean oDPCO) {
		ODPCO = oDPCO;
	}

	public boolean isOSPCO() {
		return OSPCO;
	}

	public void setOSPCO(boolean oSPCO) {
		OSPCO = oSPCO;
	}

	public String getODNS() {
		return ODNS;
	}

	public void setODNS(String oDNS) {
		ODNS = oDNS;
	}

	public String getODNSNotes() {
		return ODNSNotes;
	}

	public void setODNSNotes(String oDNSNotes) {
		ODNSNotes = oDNSNotes;
	}

	public String getOSNS() {
		return OSNS;
	}

	public void setOSNS(String oSNS) {
		OSNS = oSNS;
	}

	public String getOSNSNotes() {
		return OSNSNotes;
	}

	public void setOSNSNotes(String oSNSNotes) {
		OSNSNotes = oSNSNotes;
	}

	public String getODCortical() {
		return ODCortical;
	}

	public void setODCortical(String oDCortical) {
		ODCortical = oDCortical;
	}

	public String getODCorticalNotes() {
		return ODCorticalNotes;
	}

	public void setODCorticalNotes(String oDCorticalNotes) {
		ODCorticalNotes = oDCorticalNotes;
	}

	public String getOSCortical() {
		return OSCortical;
	}

	public void setOSCortical(String oSCortical) {
		OSCortical = oSCortical;
	}

	public String getOSCorticalNotes() {
		return OSCorticalNotes;
	}

	public void setOSCorticalNotes(String oSCorticalNotes) {
		OSCorticalNotes = oSCorticalNotes;
	}

	public String getODPSC() {
		return ODPSC;
	}

	public void setODPSC(String oDPSC) {
		ODPSC = oDPSC;
	}

	public String getODPSCNotes() {
		return ODPSCNotes;
	}

	public void setODPSCNotes(String oDPSCNotes) {
		ODPSCNotes = oDPSCNotes;
	}

	public String getOSPSC() {
		return OSPSC;
	}

	public void setOSPSC(String oSPSC) {
		OSPSC = oSPSC;
	}

	public String getOSPSCNotes() {
		return OSPSCNotes;
	}

	public void setOSPSCNotes(String oSPSCNotes) {
		OSPSCNotes = oSPSCNotes;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}
