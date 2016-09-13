package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.SurgeryTableGateway;
import database.SurgeryTableGatewaySQLite;
import database.GatewayException;

public class SurgeryList {

	private SurgeryTableGateway myGateway;
	private List<Surgery> myList;

	/**
	 * Construct a new SurgeryList
	 */
	public SurgeryList() {

		myList = new ArrayList<Surgery>();

		try {
			myGateway = new SurgeryTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From SurgeryList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From SurgeryList, IO Exception");
			// e.printStackTrace();
		}
	}

	/**
	 * Load records from DB into SurgeryList
	 */
	public void loadFromGateway() {

		// fetch list of objects from the database

		try {
			// name fetchSurgeries to fetchSurgeriesList to list
			for (Surgery tmpSurgery : myGateway.fetchSurgeries()) {
				myList.add(tmpSurgery);
			}

		} catch (GatewayException e) {
			System.err.println("Could not Connect to DB, in SurgeryList");
			return;
		}
	}

	/**
	 * Returns ArrayList of Surgeries in the SurgeryList
	 * 
	 * @return All Surgeries in list
	 */
	public List<Surgery> getSurgeryList() {
		return myList;
	}

	public void loadSurgeryListForPatient(Patient p) {

		try {
			myList = myGateway.fetchSurgeriesForPatient(p);

		} catch (GatewayException e) {
			System.err.println("SurgeryList failed to load from its gateway. In SurgeryList Model");
			e.printStackTrace();
		}
	}
}
