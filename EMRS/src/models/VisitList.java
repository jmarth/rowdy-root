package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.VisitTableGateway;
import database.VisitTableGatewaySQLite;
import database.GatewayException;

public class VisitList {

	private VisitTableGateway myGateway;
	private List<Visit> myList;

	/**
	 * Construct a new VisitList
	 */
	public VisitList() {

		myList = new ArrayList<Visit>();

		try {
			myGateway = new VisitTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From VisitList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From VisitList, IO Exception");
			// e.printStackTrace();
		}
	}

	/**
	 * Load records from DB into VisitList
	 */
	public void loadFromGateway() {

		// fetch list of objects from the database

		try {
			// name fetchVisits to fetchVisitsList to list
			for (Visit tmpVisit : myGateway.fetchVisits()) {
				myList.add(tmpVisit);
			}

		} catch (GatewayException e) {
			System.err.println("Could not Connect to DB, in VisitList");
			return;
		}
	}

	/**
	 * Returns ArrayList of Visits in the VisitList
	 * 
	 * @return All Visits in list
	 */
	public List<Visit> getVisitList() {
		return myList;
	}

	public void loadVisitListForPatient(Patient p) {

		try {
			myList = myGateway.fetchVisitsForPatient(p);

		} catch (GatewayException e) {
			System.err.println("VisitList failed to load from its gateway. In VisitList Model");
			e.printStackTrace();
		}
	}
}
