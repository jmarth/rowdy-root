package models;

import java.util.HashMap;
import java.util.List;

import database.GatewayException;
import database.PatientTableGateway;

public class PatientList {
	
	private PatientTableGateway gateway;
	private HashMap<Long, Patient> myIdMap;
	
	public PatientList() {
		myIdMap = new HashMap<Long, Patient>();
	}
	
	public void loadFromGateway() {
		//fetch list of objects from the database
		try {
			List<Patient> patients = gateway.fetchPatients();
			for(Patient patient: patients){
				patient.setBirthDayDate();
				myIdMap.put(patient.getId(), patient);
			}
		} catch (GatewayException e) {
			//TODO: handle exception here
			return;
		}
	}
	
	//if fail to add, then it no add to list
	public void addPatient(Patient p) {
		try {
			
			gateway.insertPatient(p);
			myIdMap.put(p.getId(), p);

		} catch (GatewayException e) {
			
			System.err.println("From PatientList, failed to insert Patient to DB");
		}
	}
	
	public List<Patient> getPatientList() {
		return (List<Patient>)myIdMap.values();
	}
	
	public Patient findById(long id) {
		
		//check the identity map
		if(myIdMap.containsKey(new Long(id)))
			return myIdMap.get(new Long(id));
		
		return null;
	}
}
