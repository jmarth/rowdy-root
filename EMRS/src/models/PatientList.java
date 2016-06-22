package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;

import database.GatewayException;
import database.PatientTableGateway;

public class PatientList {
	private List<Patient> myList;
	private PatientTableGateway gateway;
	
	public PatientList() {
		myList = new ArrayList<Patient>();
	}
	
	public void loadFromGateway() {
		//fetch list of objects from the database
		try {
			List<Patient> parts = gateway.fetchPatients();
			for(int i=0; i<parts.size(); i++){
				this.addPartToList(parts.get(i));
			}
		} catch (GatewayException e) {
			//TODO: handle exception here
			return;
		}
	}
	
	public void addPartToList(Patient p) {
		myList.add(p);
	}
	
	public void setGateway(PatientTableGateway gateway) {
		this.gateway = gateway;
	}
	
	public List<Patient> getPatientList() {
		return myList;
	}
}
