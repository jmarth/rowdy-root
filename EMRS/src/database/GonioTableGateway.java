package database;

import java.util.ArrayList;
import java.util.List;

import models.Gonio;
import models.Patient;

public interface GonioTableGateway {
	public long insertGonio(Gonio dv) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;
	public void removeGonio(Long vid) throws GatewayException;
	public Gonio fetchGonioForVisit(long id)throws GatewayException;
}
