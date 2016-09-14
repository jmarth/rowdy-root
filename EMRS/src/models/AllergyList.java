package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.AllergyTableGateway;
import database.AllergyTableGatewaySQLite;
import database.GatewayException;

public class AllergyList implements SCRUDWithPatientList {

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

	/**
	 * Returns ArrayList of Allergies in the AllergyList
	 * 
	 * @return All Allergies in list
	 */
	public List<Allergy> getAllergyList() {
		return myList;
	}

	public void loadAllergyListForPatient(Patient p) {

		try {
			myList = myGateway.fetchAllergiesForPatient(p);

		} catch (GatewayException e) {
			System.err.println("AllergyList failed to load from its gateway. In AllergyList Model");
			e.printStackTrace();
		}
	}

	@Override
	public Object find(Patient p) throws GatewayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long insert(Object o) throws GatewayException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object get(Patient p) throws GatewayException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Patient p) throws GatewayException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) throws GatewayException {
		// TODO Auto-generated method stub
		
	}
	
}
