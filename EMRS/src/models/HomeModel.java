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
import views.Home;
import views.PatientInfo;
import views.PatientsView;

public class HomeModel {
	private PatientTableGateway patientTableGateway;
	private PatientList patientList;
	private PatientInfo patientInfo;
	private PatientsView patientsView;
	
	public HomeModel(Home home) {
		super();
		patientList = new PatientList();
		patientInfo = new PatientInfo(home);
		patientsView = new PatientsView(home);
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

	public PatientInfo getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
	}

	public PatientsView getPatientsView() {
		return patientsView;
	}

	public void setPatientsView(PatientsView patientView) {
		this.patientsView = patientView;
	}
}
