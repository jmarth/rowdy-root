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

	public List<Surgery> getMyList() {

		return myList;
	}

	public void loadMyListForPatient(Patient p) throws GatewayException {
		
		try {
			
			myList = myGateway.fetchSurgeriesForPatient(p);

		} catch (GatewayException e) {
			System.err.println("SurgeryList failed to load from its gateway. In SurgeryList Model");
			e.printStackTrace();
		}
	}

	public long insert(Surgery s) throws GatewayException {

		s.setId(myGateway.insertSurgery(s));
		this.myList.add(s);

		return s.getId();
	}

	public void update(Surgery s) throws GatewayException {
		myGateway.updateSurgery(s);
	}

	public void delete(long id) throws GatewayException {
		myGateway.removeSurgery(id);
	}

}
