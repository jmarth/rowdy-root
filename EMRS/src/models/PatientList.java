package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.GatewayException;
import database.PatientTableGateway;
import database.PatientTableGatewaySQLite;

public class PatientList {

	private PatientTableGateway myGateway;
	private HashMap<Long, Patient> myIdMap;

	public PatientList() {
		myIdMap = new HashMap<Long, Patient>();
		try {
			myGateway = new PatientTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From PatientList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From PatientList, IO Exception");
			// e.printStackTrace();
		}
	}

	public void loadFromGateway() {
		// fetch list of objects from the database
		myIdMap = new HashMap<Long, Patient>();
		try {
			
			List<Patient> patients = myGateway.fetchPatients();
			for (Patient patient : patients) {
				patient.setBirthDayDate();
				myIdMap.put(patient.getId(), patient);
			}
			
		} catch (GatewayException e) {
			return;
		}
	}
	
	public ArrayList<Patient> getMyList() {
		ArrayList<Patient> temp = new ArrayList<Patient>();
		temp.addAll(myIdMap.values());
		return temp;
	}

	public Patient findById(long id) {
		// check the identity map
		if (myIdMap.containsKey(id))
			return myIdMap.get(id);
		return null;
	}

	public void insert(Patient p) throws GatewayException {

		p.setId(myGateway.insertPatient(p));
		this.myIdMap.put(p.getId(), p);
	}

	public void update(Patient a) throws GatewayException {
		this.myGateway.updatePatient(a);
	}

	// TODO
//	public void delete(long id) throws GatewayException {
//		myGateway.removePatient(id);
//	}
}
