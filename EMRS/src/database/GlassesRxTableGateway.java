package database;

import java.util.ArrayList;
import java.util.List;

import models.GlassesRx;
import models.Patient;

public interface GlassesRxTableGateway {
	public long insertGlassesRx(GlassesRx dv) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;
	public void removeGlassesRx(Long vid) throws GatewayException;
	public abstract ArrayList<Object> fetchGlassesRxColsForVisit(long id) throws GatewayException;
	public GlassesRx fetchAnteriorChamberForVisit(long vid)throws GatewayException;
}
