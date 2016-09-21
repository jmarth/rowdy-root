package database;

import java.util.List;

import models.SurgeryTemplate;

public interface SurgeryTemplatesTableGateway {
	// patient doesn't not have a specific surgery template, but global ones available.
	public abstract List<SurgeryTemplate> fetchAllSurgeryTemplates() throws GatewayException; 
	public long insertSurgeryTemplate(SurgeryTemplate s) throws GatewayException;
	public abstract void updateSurgeryTemplate(SurgeryTemplate a);
	public abstract void removeSurgeryTemplate(long id);
}
