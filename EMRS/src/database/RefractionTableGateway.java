package database;

import java.util.ArrayList;
import java.util.List;

import models.Refraction;
import models.Patient;

public interface RefractionTableGateway {
	public abstract List<Refraction> fetchRefractions() throws GatewayException;
	public List<Refraction> fetchRefractionsForPatient(Patient p) throws GatewayException;
	public long insertRefraction(Refraction dv) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;
	public void removeRefraction(Long vid) throws GatewayException;
	public abstract ArrayList<Object> fetchRefractionsColsForVisit(long id) throws GatewayException;
}
