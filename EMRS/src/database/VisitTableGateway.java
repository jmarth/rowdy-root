package database;

import java.util.List;

import models.Visit;
public interface VisitTableGateway {
	public abstract List<Visit> fetchVisits() throws GatewayException;
	public abstract long insertVisit(Visit v) throws GatewayException;
	
	public abstract void close();
}
