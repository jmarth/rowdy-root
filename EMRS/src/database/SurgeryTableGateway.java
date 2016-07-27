package database;

import java.util.List;

import models.Patient;
import models.Surgery;

public interface SurgeryTableGateway {
	public abstract List<Surgery> fetchSurgeries() throws GatewayException;
	public List<Surgery> fetchSurgeriesForPatient(Patient p) throws GatewayException;
	public long insertSurgery(Surgery s) throws GatewayException;
}
