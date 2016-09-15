package database;

import models.Allergy;

public interface AllergyTableGateway {
	public long insertAllergy(Allergy a) throws GatewayException;
	public void updateAllergy(Allergy a) throws GatewayException;
	public void removeAllergy(Long aid) throws GatewayException;
}
