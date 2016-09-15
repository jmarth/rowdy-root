package database;

import java.util.ArrayList;
import java.util.List;

import models.Lens;
import models.Patient;

public interface LensTableGateway {
	public long insertLens(Lens dv) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;
	public void removeLens(Long vid) throws GatewayException;
	public abstract ArrayList<Object> fetchLensColsForVisit(long id) throws GatewayException;
	public Lens fetchLensForVisit(long vid)throws GatewayException;
}
