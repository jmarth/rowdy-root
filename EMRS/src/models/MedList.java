package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.GatewayException;
import database.MedicationsTableGateway;
import database.MedicationsTableGatewaySQLite;

public class MedList {

	private MedicationsTableGateway myGateway;
	private List<Med> myList;

	/**
	 * Construct a new MedsList
	 */
	public MedList() {

		myList = new ArrayList<Med>();

		try {
			myGateway = new MedicationsTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From MedsList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From MedsList, IO Exception");
			// e.printStackTrace();
		}
	}

	/**
	 * Load records from DB into MedsList
	 */
	public void loadFromGateway() {

		// fetch list of objects from the database

		try {
			// name fetchMeds to fetchMedsList to list
			for (Med tmpMed : myGateway.fetchMeds()) {
				myList.add(tmpMed);
			}

		} catch (GatewayException e) {
			
			System.err.println("Could not Connect to DB, in MedList");
			return;
		}
	}

	/**
	 * Returns ArrayList of Medications in the MedsList
	 * 
	 * @return All Medications in list
	 */
	public List<Med> getMedList() {
		return myList;
	}

	public void loadMedicationsListForPatient(Patient p) {

		try {
			myList = myGateway.fetchMedsForPatient(p);

		} catch (GatewayException e) {
			System.err.println("MedList failed to load from its gateway. In MedList Model");
			//e.printStackTrace();
		}
	}
}
