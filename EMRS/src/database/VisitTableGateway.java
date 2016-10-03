package database;

import java.util.ArrayList;
import java.util.List;

import models.Patient;
import models.Visit;
public interface VisitTableGateway {
	public abstract long insertVisit(Visit v) throws GatewayException;
	public List<Visit> fetchVisitsForPatient(Patient p) throws GatewayException;
	public abstract ArrayList<Object> fetchVisitsCols(long id)throws GatewayException;
	public abstract void updateVisit(Visit a);
	public abstract void removeVisit(long id);
}
