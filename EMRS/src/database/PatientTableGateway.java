package database;

import java.util.List;

import models.Patient;

public interface PatientTableGateway {
	public List<Patient> fetchPatients() throws GatewayException;
	public long insertPatient(Patient p) throws GatewayException;
	public void updatepatient (Patient p) throws GatewayException;
	
	public void close();
}