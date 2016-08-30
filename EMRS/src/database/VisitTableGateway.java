package database;

import java.util.ArrayList;
import java.util.List;

import models.Patient;
import models.Visit;
public interface VisitTableGateway {
	public abstract List<Visit> fetchVisits() throws GatewayException;
	public abstract long insertVisit(Visit v) throws GatewayException;
	public List<Visit> fetchVisitsForPatinet(Patient p) throws GatewayException;
	//public abstract void close();
	public abstract ArrayList<Object> fetchVisitsCols(long id)throws GatewayException;
}
