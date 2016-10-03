package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.MedicationsTableGateway;
import database.MedicationsTableGatewaySQLite;
import database.GatewayException;

public class MedList {

	private MedicationsTableGateway myGateway;
	private List<Med> myList;

	/**
	 * Construct a new MedList
	 */
	public MedList() {

		myList = new ArrayList<Med>();

		try {
			myGateway = new MedicationsTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From MedList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From MedList, IO Exception");
			// e.printStackTrace();
		}
	}

	public List<Med> getMyList() {

		return myList;
	}

	public void loadMyListForPatient(Patient p) throws GatewayException {

		try {
			myList = myGateway.fetchMedsForPatient(p);

		} catch (GatewayException e) {
			System.err.println("MedList failed to load from its gateway. In MedList Model");
//			e.printStackTrace();
		}
	}

	public long insert(Med a) throws GatewayException {

		a.setId(myGateway.insertMed(a));
		this.myList.add(a);

		return a.getId();
	}

	public void update(Med a) throws GatewayException {
		myGateway.updateMed(a);
	}

	public void delete(long id) throws GatewayException {
		myGateway.removeMed(id);
	}

}
