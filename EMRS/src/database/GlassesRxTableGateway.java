package database;

import java.util.List;

import models.GlassesRx;
import models.Patient;

public interface GlassesRxTableGateway {
	public abstract List<GlassesRx> fetchGlassesRxs() throws GatewayException;
	public List<GlassesRx> fetchGlassesRxsForPatient(Patient p) throws GatewayException;
	public long insertGlassesRx(GlassesRx dv) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;
	public void removeGlassesRx(Long vid) throws GatewayException;
}
