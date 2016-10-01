package database;

import java.util.List;

import models.Allergy;

public interface AllergyTableGateway {
	
	public long insertAllergy(Allergy a) throws GatewayException;

	public List<Allergy> fetchAllergiesForPatient(long pid) throws GatewayException;

	public void updateAllergy(Allergy a) throws GatewayException;

	public void removeAllergy(Long aid) throws GatewayException;
}
