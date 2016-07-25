package models;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.AllergyTableGateway;
import database.AllergyTableGatewayMySQL;
import database.AllergyTableGatewaySQLite;
import database.DocumentTableGateway;
import database.DocumentTableGatewaySQLite;
import database.GatewayException;
import database.PatientTableGateway;
import database.PatientTableGatewayMySQL;
import database.PatientTableGatewaySQLite;
import database.SketchTableGateway;
import database.SketchTableGatewayMySQL;
import database.SketchTableGatewaySQLite;
import database.VisitTableGateway;
import database.VisitTableGatewayMySQL;
import database.VisitTableGatewaySQLite;
import database.VitalsTableGateway;
import database.VitalsTableGatewayMySQL;
import database.VitalsTableGatewaySQLite;
import views.HomeView;
import views.AddPatientView;
import views.FindPatientsView;

public class HomeModel {
	
	private AddPatientView addPatientView;
	private FindPatientsView findPatientsView;
	
	private PatientList patientList ;
	private VisitList vl = new VisitList();
	
	private PatientTableGateway ptg = null;
	private AllergyTableGateway atg = null;
	private VisitTableGateway vtg = null;
	private SketchTableGateway stg = null;
	private VitalsTableGateway vitalstg =null;
	private DocumentTableGateway dtg = null;
	

	
	public HomeModel(HomeView homeView) {
		super();
		patientList = new PatientList();
		addPatientView = new AddPatientView(homeView);
		findPatientsView = new FindPatientsView(homeView);
		setGateways();
	}
	
	public void setGateways() {
		
		//Gateway creations
		
		try {
			
			ptg = new PatientTableGatewaySQLite();
			atg = new AllergyTableGatewaySQLite();
			vtg = new VisitTableGatewaySQLite();
			stg = new SketchTableGatewaySQLite();
			vitalstg = new VitalsTableGatewaySQLite();
			dtg = new DocumentTableGatewaySQLite();
			
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

	public PatientList getPatientList() {
		return patientList;
	}

	public void setPatientList(PatientList patientList) {
		this.patientList = patientList;
	}

	public AddPatientView getAddPatientView() {
		return addPatientView;
	}

	public void setAddPatientView(AddPatientView addPatientView) {
		this.addPatientView = addPatientView;
	}

	public FindPatientsView getPatientsView() {
		return findPatientsView;
	}

	public void setFindPatientsView(FindPatientsView patientView) {
		this.findPatientsView = patientView;
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
	
	public SketchTableGateway getStg() {
		return stg;
	}

	public void setStg(SketchTableGateway stg) {
		this.stg = stg;
	}

	public DocumentTableGateway getDtg() {
		return dtg;
	}

	public void setDtg(DocumentTableGateway dtg) {
		this.dtg = dtg;
	}
}
