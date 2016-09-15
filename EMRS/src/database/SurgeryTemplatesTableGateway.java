package database;

import java.util.List;

import models.SurgeryTemplate;

public interface SurgeryTemplatesTableGateway {
	public abstract List<SurgeryTemplate> fetchAllSurgeries() throws GatewayException;
	public long insertSurgeryTemplate(SurgeryTemplate s) throws GatewayException;
	public abstract void updateSurgeryTemplate(SurgeryTemplate a);
	public abstract void removeSurgeryTemplate(long id);
}
