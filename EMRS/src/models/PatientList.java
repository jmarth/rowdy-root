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
	private HashMap<Long, Patient> myIdMap;
	
	public PatientList() {
		myList = new ArrayList<Patient>();
		myIdMap = new HashMap<Long, Patient>();
	}
	
	public void loadFromGateway() {
		
		//fetch list of objects from the database
		try {
			List<Patient> patients = gateway.fetchPatients();
			
			for(Patient patient: patients){
				this.addPatientToList(patient);
				patient.setBirthDayDate();
				myIdMap.put(patient.getId(), patient);
			}
			
		} catch (GatewayException e) {
			//TODO: handle exception here
			return;
		}
	}
	
	public void addPatientToList(Patient p) {
		myList.add(p);
	}
	
	public void setGateway(PatientTableGateway gateway) {
		this.gateway = gateway;
	}
	
	public List<Patient> getPatientList() {
		return myList;
	}
	
	public Patient findById(long id) {
		
		//check the identity map
		if(myIdMap.containsKey(new Long(id)))
			return myIdMap.get(new Long(id));
		
		return null;
	}
}
