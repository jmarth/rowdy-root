package database;

import java.util.List;

import models.Gonio;
import models.Patient;

public interface GonioTableGateway {
	public abstract List<Gonio> fetchGonios() throws GatewayException;
	public List<Gonio> fetchGoniosForPatient(Patient p) throws GatewayException;
	public long insertGonio(Gonio dv) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;
	public void removeGonio(Long vid) throws GatewayException;
}
