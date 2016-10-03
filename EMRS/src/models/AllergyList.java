package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.AllergyTableGateway;
import database.AllergyTableGatewaySQLite;
import database.GatewayException;

public class AllergyList {

	private AllergyTableGateway myGateway;
	private List<Allergy> myList;

	/**
	 * Construct a new AllergyList
	 */
	public AllergyList() {

		myList = new ArrayList<Allergy>();

		try {
			myGateway = new AllergyTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From AllergyList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From AllergyList, IO Exception");
			// e.printStackTrace();
		}
	}

	public List<Allergy> getMyList() {
		return myList;
	}

	public void loadMyListForPatient(Patient p) throws GatewayException {

		try {
			myList = myGateway.fetchAllergiesForPatient(p);

		} catch (GatewayException e) {
			System.err.println("AllergyList failed to load from its gateway. In AllergyList Model");
//			e.printStackTrace();
		}
	}

	public long insert(Allergy a) throws GatewayException {

		a.setId(myGateway.insertAllergy(a));
		this.myList.add(a);

		return a.getId();
	}

	public void update(Allergy a) throws GatewayException {
		myGateway.updateAllergy(a);
	}

	public void delete(long id) throws GatewayException {
		myGateway.removeAllergy(id);
	}

}
