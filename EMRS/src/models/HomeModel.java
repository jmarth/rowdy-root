package models;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.GatewayException;
import database.PatientTableGateway;
import database.PatientTableGatewayMySQL;
import views.HomeView;
import views.AddPatientView;
import views.FIndPatientsView;

public class HomeModel {
	private PatientTableGateway patientTableGateway;
	private PatientList patientList;
	private AddPatientView patientInfo;
	private FIndPatientsView patientsView;
	
	public HomeModel(HomeView homeView) {
		super();
		patientList = new PatientList();
		patientInfo = new AddPatientView(homeView);
		patientsView = new FIndPatientsView(homeView);
	}

	public void setPatientTableGateway() {
		patientTableGateway = null;
		//Try to connect to database
		try {
			patientTableGateway = new PatientTableGatewayMySQL();
		} catch (GatewayException e) {
			JOptionPane.showMessageDialog(null, "Database is not responding. Please reboot your computer and maybe the database will magically appear (not really).", "Database Offline!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Database is not responding. Please reboot your computer and maybe the database will magically appear (not really).", "Database Offline!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
	
	public PatientTableGateway getPatientTableGateway() {
		return patientTableGateway;
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
}
