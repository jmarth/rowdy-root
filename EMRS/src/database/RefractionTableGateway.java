package database;

import java.util.ArrayList;
import java.util.List;

import models.Refraction;
import models.Patient;

public interface RefractionTableGateway {
	public long insertRefraction(Refraction dv) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;
	public void removeRefraction(Long vid) throws GatewayException;
	public abstract ArrayList<Object> fetchRefractionsColsForVisit(long id) throws GatewayException;
	public Refraction fetchRefractionForVisit(long vid) throws GatewayException;
}
