package models;

import java.io.File;

import database.GatewayException;

public class Visit {
	
	//final private MasterModel masterModel; // have call to parent to get mastermodel, parent's parent call , etc.
	// easier to change
	private long id;
	private long pid;
	
	private String chiefComplaint;
	private String dateCreated;
	private String assessment;
	private String plan;
	
	private AnteriorChamber myAC; 
	private DistanceVision myDV;
	private FundusExam myFE;
	private GlassesRx myGlsRx;
	private Gonio myGonio;
	private IOPList myIOPList;
	private Lens myLens;
	private Pupils myPupils;
	private Refraction myRefraction;
//	private AScan myAScan; TODO implement
//	private Keratometry myK; TODO implement
	
	private Sketches sketches;
	
	
	private File sketchTemp;
	
	

	public Visit(Long id, Long pid, String chiefComplaint, String assessment, String plan, String dateCreated) {
		//this.masterModel = masterModel;
		this.id = id;
		this.pid = pid;
		this.chiefComplaint = chiefComplaint;
		this.assessment = assessment;
		this.plan = plan;
		this.dateCreated = dateCreated;
	}

	
	public Visit(Long pid, String chiefComplaint, String plan, String assessment) {
		//this.masterModel = masterModel;
		this.pid = pid;
		this.chiefComplaint = chiefComplaint;
		this.plan = plan;
		this.assessment = assessment;
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


	public String getChiefComplaint() {
		return chiefComplaint;
	}


	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}


	public String getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}


	public String getAssessment() {
		return assessment;
	}


	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}


	public String getPlan() {
		return plan;
	}


	public void setPlan(String plan) {
		this.plan = plan;
	}


	public AnteriorChamber getMyAC() {
		return myAC;
	}


	public void setMyAC(AnteriorChamber myAC) {
		this.myAC = myAC;
	}


	public DistanceVision getMyDV() {
		return myDV;
	}


	public void setMyDV(DistanceVision myDV) {
		this.myDV = myDV;
	}


	public FundusExam getMyFE() {
		return myFE;
	}


	public void setMyFE(FundusExam myFE) {
		this.myFE = myFE;
	}


	public GlassesRx getMyGlsRx() {
		return myGlsRx;
	}


	public void setMyGlsRx(GlassesRx myGlsRx) {
		this.myGlsRx = myGlsRx;
	}


	public Gonio getMyGonio() {
		return myGonio;
	}


	public void setMyGonio(Gonio myGonio) {
		this.myGonio = myGonio;
	}


	public IOPList getMyIOPList() {
		return myIOPList;
	}


	public void setMyIOPList(IOPList myIOPList) {
		this.myIOPList = myIOPList;
	}


	public Lens getMyLens() {
		return myLens;
	}


	public void setMyLens(Lens myLens) {
		this.myLens = myLens;
	}


	public Pupils getMyPupils() {
		return myPupils;
	}


	public void setMyPupils(Pupils myPupils) {
		this.myPupils = myPupils;
	}


	public Refraction getMyRefraction() {
		return myRefraction;
	}


	public void setMyRefraction(Refraction myRefraction) {
		this.myRefraction = myRefraction;
	}


	public File getSketchTemp() {
		return sketchTemp;
	}


	public void setSketchTemp(File sketchTemp) {
		this.sketchTemp = sketchTemp;
	}

	public void loadVisitFromPatient() {
		//TODO load all the stuff
		//TODO how it know the visit id if it not created before??
		this.myAC = myAC.loadAC(id);
		this.myDV = myDV.loadDV(id);
		this.myFE = myFE.loadFE(id);
		this.myGlsRx = myGlsRx.loadGlsRx(id);
		this.myGonio = myGonio.loadGonio(id);
		this.myLens = myLens.loadLens(id);
		this.myPupils = myPupils.loadPupils(id);
		this.myRefraction = myRefraction.loadRefraction(id);
		try {
			this.myIOPList.loadMyListForVisit(id);
		} catch (GatewayException e) {
			System.err.println("From Visit, load a model fail");
		}
		
		sketches.loadSketches(id);
	}

		
	public Sketches getSketches() {
		return sketches;
	}
}
