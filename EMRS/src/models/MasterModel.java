package models;

import database.GatewayException;

public class MasterModel {
	
	private Patient currPatient;
	
	private Hx hx;
	//private HxList hxL;
		
	private AllergyList aL;
	private MedList mL;
	private DocumentList dL;
	private PatientList pL;
	private SurgeryList sL;
	private VisitList vL;
	private VitalsList vitalsL;
	
	public MasterModel () {
		
		aL = new AllergyList();
		mL = new MedList();
		dL = new DocumentList();
		pL = new PatientList();
		sL = new SurgeryList();
		vL = new VisitList();
		vitalsL = new VitalsList();
	}
	public void loadmaster(Patient patient){
		try {
			this.currPatient=patient;
			aL.loadMyListForPatient(currPatient);
			mL.loadMyListForPatient(currPatient);
			dL.loadMyListForPatient(currPatient);
			sL.loadMyListForPatient(currPatient);
			vL.loadMyListForPatient(currPatient);
			vitalsL.loadMyListForPatient(currPatient);
		} catch (GatewayException e) {
			System.err.println("from MasterModel, can not fetch from database");
		}
			
	}
	public Patient getCurrPatient() {
		return currPatient;
	}

	public void setCurrPatient(Patient currPatient) {
		this.currPatient = currPatient;
	}

	public Hx getHx() {
		return hx;
	}

	public void setHx(Hx hx) {
		this.hx = hx;
	}

	public AllergyList getaL() {
		return aL;
	}

	public void setaL(AllergyList aL) {
		this.aL = aL;
	}

	public MedList getmL() {
		return mL;
	}

	public void setmL(MedList mL) {
		this.mL = mL;
	}

	public DocumentList getdL() {
		return dL;
	}

	public void setdL(DocumentList dL) {
		this.dL = dL;
	}

	public PatientList getpL() {
		return pL;
	}

	public void setpL(PatientList pL) {
		this.pL = pL;
	}

	public SurgeryList getsL() {
		return sL;
	}

	public void setsL(SurgeryList sL) {
		this.sL = sL;
	}

	public VisitList getvL() {
		return vL;
	}

	public void setvL(VisitList vL) {
		this.vL = vL;
	}

	public VitalsList getVitalsL() {
		return vitalsL;
	}

	public void setVitalsL(VitalsList vitalsL) {
		this.vitalsL = vitalsL;
	}
	/*public void clear(){
		aL.clear();
		mL.clear();
		dL.clear();
		pL.clear();
		sL.clear();
		vL.clear();
		vitalsL = new VitalsList();
	}*/
	
	
}
