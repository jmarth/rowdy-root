package database;

import java.util.ArrayList;
import java.util.List;

import models.DistanceVision;
import models.Patient;

public interface DistanceVisionTableGateway {
	
	public long insertDistanceVision(DistanceVision dv) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;
	public void removeDistanceVision(Long vid) throws GatewayException;
	public abstract ArrayList<Object> fetchDistanceVisionColsForVisit(long id) throws GatewayException;
	public DistanceVision fetchDistanceVisionForVisit(long vid)throws GatewayException ;
}
