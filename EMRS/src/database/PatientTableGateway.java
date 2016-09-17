package database;

import java.util.List;
import models.Patient;

public interface PatientTableGateway {
	public List<Patient> fetchPatients() throws GatewayException;
	public long insertPatient(Patient p) throws GatewayException;
	public void updatePatient (Patient p) throws GatewayException;
	//TODO remove patient
	public void close();
}