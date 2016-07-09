package database;

import java.util.List;

import models.Allergy;
import models.Patient;
import models.Visit;
public interface VisitTableGateway {
	public abstract List<Visit> fetchVisits() throws GatewayException;
	public abstract long insertVisit(Visit v) throws GatewayException;
	public List<Visit> fetchVisitsForPatinet(Patient p) throws GatewayException;
	public abstract void close();
}
