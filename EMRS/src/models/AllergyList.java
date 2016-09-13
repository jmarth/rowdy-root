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

	/**
	 * Load records from DB into AllergyList
	 */
	public void loadFromGateway() {

		// fetch list of objects from the database

		try {
			// name fetchAllergies to fetchAllergiesList to list
			for (Allergy tmpAllergy : myGateway.fetchAllergies()) {
				myList.add(tmpAllergy);
			}

		} catch (GatewayException e) {
			System.err.println("Could not Connect to DB, in AllergyList");
			return;
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
}
