package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.VitalsTableGateway;
import database.VitalsTableGatewaySQLite;
import database.GatewayException;

public class VitalsList {

	private VitalsTableGateway myGateway;
	private List<Vitals> myList;

	/**
	 * Construct a new VitalsList
	 */
	public VitalsList() {

		myList = new ArrayList<Vitals>();

		try {
			myGateway = new VitalsTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From VitalsList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From VitalsList, IO Exception");
			// e.printStackTrace();
		}
	}

	/**
	 * Load records from DB into VitalsList
	 */
	public void loadFromGateway() {

		// fetch list of objects from the database

		try {
			// name fetchVitals to fetchVitalsList to list
			for (Vitals tmpVitals : myGateway.fetchVitals()) {
				myList.add(tmpVitals);
			}

		} catch (GatewayException e) {
			System.err.println("Could not Connect to DB, in VitalsList");
			return;
		}
	}

	/**
	 * Returns ArrayList of Vitals in the VitalsList
	 * 
	 * @return All Vitals in list
	 */
	public List<Vitals> getVitalsList() {
		return myList;
	}

	public void loadVitalsListForPatient(Patient p) {

		try {
			myList = myGateway.fetchVitalsForPatient(p);

		} catch (GatewayException e) {
			System.err.println("VitalsList failed to load from its gateway. In VitalsList Model");
			e.printStackTrace();
		}
	}
}
