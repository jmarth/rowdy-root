package database;

import java.util.List;

import models.Lens;
import models.Patient;

public interface LensTableGateway {
	public abstract List<Lens> fetchLens() throws GatewayException;
	public List<Lens> fetchLensForPatient(Patient p) throws GatewayException;
	public long insertLens(Lens dv) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;
	public void removeLens(Long vid) throws GatewayException;
}
