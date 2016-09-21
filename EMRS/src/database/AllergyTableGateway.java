package database;

import java.util.List;

import models.Allergy;
import models.Patient;

public interface AllergyTableGateway {
	public long insertAllergy(Allergy a) throws GatewayException;
	public void updateAllergy(Allergy a) throws GatewayException;
	public void removeAllergy(Long aid) throws GatewayException;
	public List<Allergy> fetchAllergiesForPatient(Patient p) throws GatewayException;
}
