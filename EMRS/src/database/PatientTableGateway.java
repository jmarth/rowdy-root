package database;

import java.util.List;

import models.Patient;

public interface PatientTableGateway {
	public abstract List<Patient> fetchPatients() throws GatewayException;
	public abstract long insertPatient(Patient p) throws GatewayException;
	
	public abstract void close();
}