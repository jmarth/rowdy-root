package models;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.AllergyTableGateway;
import database.AllergyTableGatewayMySQL;
import database.GatewayException;
import database.PatientTableGateway;
import database.PatientTableGatewayMySQL;
import database.SketchTableGateway;
import database.SketchTableGatewayMySQL;
import database.VisitTableGateway;
import database.VisitTableGatewayMySQL;
import database.VitalsTableGateway;
import database.VitalsTableGatewayMySQL;
import views.HomeView;
import views.AddPatientView;
import views.FIndPatientsView;

public class HomeModel {
	private AddPatientView patientInfo;
	private FIndPatientsView patientsView;
	private PatientList patientList ;
	private PatientTableGateway ptg = null;
	private AllergyTableGateway atg = null;
	private VisitTableGateway vtg = null;
	private SketchTableGateway stg = null;
	private VisitList vl = new VisitList();
	
	private VitalsTableGateway vitalstg =null;
	
	public HomeModel(HomeView homeView) {
		super();
		patientList = new PatientList();
		patientInfo = new AddPatientView(homeView);
		patientsView = new FIndPatientsView(homeView);
		setGateways();
	}
	
	public void setGateways() {
		//Gateway creations
		try {
			ptg = new PatientTableGatewayMySQL();
			atg = new AllergyTableGatewayMySQL();
			vtg = new VisitTableGatewayMySQL();
			stg = new SketchTableGatewayMySQL();
			
			vitalstg = new VitalsTableGatewayMySQL();
			
			vl.setGateway(vtg);
        	vl.loadFromGateway();
        	
		} catch (GatewayException e) {
			JOptionPane.showMessageDialog(null, "Database is not responding.", "Database Offline!", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
			System.exit(1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Database is not responding.", "Database Error!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
	public SketchTableGateway getStg() {
		return stg;
	}

	public void setStg(SketchTableGateway stg) {
		this.stg = stg;
	}

	public PatientList getPatientList() {
		return patientList;
	}

	public void setPatientList(PatientList patientList) {
		this.patientList = patientList;
	}

	public AddPatientView getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(AddPatientView patientInfo) {
		this.patientInfo = patientInfo;
	}

	public FIndPatientsView getPatientsView() {
		return patientsView;
	}

	public void setPatientsView(FIndPatientsView patientView) {
		this.patientsView = patientView;
	}
	
	public PatientTableGateway getPtg() {
		return ptg;
	}

	public void setPtg(PatientTableGateway ptg) {
		this.ptg = ptg;
	}

	public AllergyTableGateway getAtg() {
		return atg;
	}

	public void setAtg(AllergyTableGateway atg) {
		this.atg = atg;
	}
	
	public VitalsTableGateway getVitalstg() {
		return vitalstg;
	}

	public void setVitalstg(VitalsTableGateway vitalstg) {
		this.vitalstg = vitalstg;
	}

	public VisitTableGateway getVtg() {
		return vtg;
	}

	public void setVtg(VisitTableGateway vtg) {
		this.vtg = vtg;
	}

	public VisitList getVl() {
		return vl;
	}

	public void setVl(VisitList vl) {
		this.vl = vl;
	}
	
}
