package database;

import java.util.List;

import models.Patient;
import models.Vitals;

public interface VitalsTableGateway {
	public abstract List<Vitals> fetchVitals() throws GatewayException;
	public List<Vitals> fetchVitalsForPatient(Patient p) throws GatewayException;
	public long insertVitals(Vitals v) throws GatewayException;
	public long updateVitals(Vitals v) throws GatewayException;
	public void removeVitals(Long vid) throws GatewayException;
}
