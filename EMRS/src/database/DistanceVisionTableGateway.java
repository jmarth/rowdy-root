package database;

import java.util.ArrayList;
import java.util.List;

import models.DistanceVision;
import models.Patient;

public interface DistanceVisionTableGateway {
	public abstract List<DistanceVision> fetchDistanceVisions() throws GatewayException;
	public List<DistanceVision> fetchDistanceVisionsForPatient(Patient p) throws GatewayException;
	public long insertDistanceVision(DistanceVision dv) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;
	public void removeDistanceVision(Long vid) throws GatewayException;
	public abstract ArrayList<Object> fetchDistanceVisionColsForVisit(long id) throws GatewayException;
}
