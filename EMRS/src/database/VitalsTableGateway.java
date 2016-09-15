package database;

import java.util.List;

import models.Patient;
import models.Vital;

public interface VitalsTableGateway {
	public List<Vital> fetchVitalsForPatient(Patient p) throws GatewayException;
	public long insertVitals(Vital v) throws GatewayException;
	public long updateVitals(Vital v) throws GatewayException;
	public void removeVitals(Long vid) throws GatewayException;
}
