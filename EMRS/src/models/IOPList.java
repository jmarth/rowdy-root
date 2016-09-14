package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.IOPTableGateway;
import database.IOPTableGatewaySQLite;
import database.GatewayException;

public class IOPList {

	private IOPTableGateway myGateway;
	private List<IOPMeasurement> myList;

	/**
	 * Construct a new IOPMeasurementList
	 */
	public IOPList() {

		myList = new ArrayList<IOPMeasurement>();

		try {
			myGateway = new IOPTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From IOPMeasurementList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From IOPMeasurementList, IO Exception");
			// e.printStackTrace();
		}
	}

	/**
	 * Load records from DB into IOPMeasurementList
	 */
	public void loadFromGateway() {

		// fetch list of objects from the database

		try {
			// name fetchIOPMeasurements to fetchIOPMeasurementsList to list
			for (IOPMeasurement tmpIOPMeasurement : myGateway.fetchIOPMeasurements()) {
				myList.add(tmpIOPMeasurement);
			}

		} catch (GatewayException e) {
			System.err.println("Could not Connect to DB, in IOPMeasurementList");
			return;
		}
	}

	/**
	 * Returns ArrayList of IOPMeasurements in the IOPMeasurementList
	 * 
	 * @return All IOPMeasurements in list
	 */
	public List<IOPMeasurement> getIOPMeasurementList() {
		return myList;
	}

	public void loadIOPMeasurementListForPatient(Patient p) {

		try {
			myList = myGateway.fetchIOPMeasurementsForPatient(p);

		} catch (GatewayException e) {
			System.err.println("IOPMeasurementList failed to load from its gateway. In IOPMeasurementList Model");
			e.printStackTrace();
		}
	}
}
