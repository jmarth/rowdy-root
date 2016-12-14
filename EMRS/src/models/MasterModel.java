package models;

import database.DrugTableGateway;
import database.DrugTableGatewaySQLite;
import database.GatewayException;

import java.io.IOException;
import java.util.List;

public class MasterModel {
	
	private Patient currPatient;
	
	private HxList hxL;
	private AllergyList aL;
	private MedList mL;
	private DocumentList dL;
	private PatientList pL;
	private SurgeryList sL;
	private VisitList vL;
	private VitalsList vitalsL;
	private SurgeryTemplatesList stll;
	private DrugTableGateway rtg;

	public MasterModel () {
		
		currPatient = null;
		hxL = new HxList();
		aL = new AllergyList();
		mL = new MedList();
		dL = new DocumentList();
		pL = new PatientList();
		sL = new SurgeryList();
		vL = new VisitList();
		vitalsL = new VitalsList();
		stll = new SurgeryTemplatesList();
		try {
			rtg = new DrugTableGatewaySQLite();
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadmaster(Patient patient){
		
		try {
			
			this.currPatient = patient;
			hxL.loadMyListForPatient(currPatient.getId());
			aL.loadMyListForPatient(currPatient);
			mL.loadMyListForPatient(currPatient);
			dL.loadMyListForPatient(currPatient);
			sL.loadMyListForPatient(currPatient);
			vL.loadMyListForPatient(currPatient);
			vitalsL.loadMyListForPatient(currPatient);
			stll.loadMyListForPatient();
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

	public HxList getHx() {
		return hxL;
	}

	public void setHx(HxList hxl) {
		this.hxL = hxl;
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

	public List<Visit> getCurrentPatientVisitList() {
		return vL.getMyList();
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
	public SurgeryTemplatesList getStll() {
		return stll;
	}
	public void setStll(SurgeryTemplatesList stll) {
		this.stll = stll;
	}
	
	public DrugTableGateway getDrugTableGateway() {
		return rtg;
	}

}
