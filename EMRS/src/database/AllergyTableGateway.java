package database;

import java.util.List;

import models.Allergy;
import models.Patient;

public interface AllergyTableGateway {
	public abstract List<Allergy> fetchAllergies() throws GatewayException;
	public List<Allergy> fetchAllergiesForPatient(Patient p) throws GatewayException;
	public long insertAllergy(Allergy a) throws GatewayException;
}
