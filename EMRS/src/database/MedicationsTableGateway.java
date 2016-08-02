package database;

import java.util.List;

import models.Med;
import models.Patient;

public interface MedicationsTableGateway {
	public abstract List<Med> fetchMeds() throws GatewayException;
	public List<Med> fetchMedsForPatient(Patient p) throws GatewayException;
	public long insertMed(Med m) throws GatewayException;
	public void updateMed(Med m) throws GatewayException;
	public void removeMed(Long mid) throws GatewayException;

}
