package models;

import java.io.File;
import java.util.List;
// 812w 9n
public class Visit {
	
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
	//TODO private AScan myAScan;
	//TODO private Keratometry myK;
	
	private File sketchTemp;
	
	

	public Visit(Long id, Long pid, String chiefComplaint, String assessment, String plan, String dateCreated) {
		super();
		this.id = id;
		this.pid = pid;
		this.chiefComplaint = chiefComplaint;
		this.assessment = assessment;
		this.plan = plan;
		this.dateCreated = dateCreated;
	}

	
	public Visit(Long pid, String chiefComplaint, String plan, String assessment) {
		super();
		this.pid = pid;
		this.chiefComplaint = chiefComplaint;
		this.plan = plan;
		this.assessment = assessment;
	}

}
