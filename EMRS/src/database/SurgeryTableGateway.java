package database;

import java.util.List;

import models.Patient;
import models.Surgery;

public interface SurgeryTableGateway {
	public List<Surgery> fetchSurgeriesForPatient(Patient p) throws GatewayException;
	public long insertSurgery(Surgery s) throws GatewayException;
	public abstract void updateSurgery(Surgery s)throws GatewayException;
	public abstract void removeSurgery(long id);
}
