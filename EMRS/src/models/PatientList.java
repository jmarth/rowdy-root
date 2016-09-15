package models;

import java.io.IOException;
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

	// if fail to add, then it no add to list
	public void addPatient(Patient p) {
		try {

			myGateway.insertPatient(p);
			myIdMap.put(p.getId(), p);

		} catch (GatewayException e) {

			System.err.println("From PatientList, failed to insert Patient to DB");
		}
	}

	public List<Patient> getMyList() {
		return (List<Patient>) myIdMap.values();
	}

	public Patient findById(long id) {

		// check the identity map
		if (myIdMap.containsKey(new Long(id)))
			return myIdMap.get(new Long(id));

		return null;
	}

	public long insert(Patient p) throws GatewayException {

		p.setId(myGateway.insertPatient(p));
		this.myIdMap.put(p.getId(), p);

		return p.getId();
	}

	public void update(Patient a) throws GatewayException {
		myGateway.updatePatient(a);
	}

	// TODO
//	public void delete(long id) throws GatewayException {
//		myGateway.removePatient(id);
//	}
}
