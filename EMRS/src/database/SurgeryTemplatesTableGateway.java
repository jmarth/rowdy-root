package database;

import java.util.List;

import models.SurgeryTemplate;

public interface SurgeryTemplatesTableGateway {
	public abstract List<SurgeryTemplate> fetchAllSurgeries() throws GatewayException;
	public long insertSurgery(SurgeryTemplate s) throws GatewayException;
}
