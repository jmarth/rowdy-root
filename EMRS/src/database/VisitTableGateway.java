package database;

import java.util.List;

import models.Visit;

public interface VisitTableGateway {
	
	public abstract long insertVisit(Visit v) throws GatewayException;

	public List<Visit> fetchVisitsForPatient(long vid) throws GatewayException;

	public abstract void updateVisit(Visit v);

	public abstract void removeVisit(long id);
}
