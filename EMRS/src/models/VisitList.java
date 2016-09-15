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

	public List<Visit> getMyList() {

		return myList;
	}

	public void loadMyListForPatient(Patient p) throws GatewayException {

		try {
			myList = myGateway.fetchVisitsForPatient(p);
			for(Visit e:myList){
				e.loadVisitFromPatient();
			}
			

		} catch (GatewayException e) {
			System.err.println("VisitList failed to load from its gateway. In VisitList Model");
//			e.printStackTrace();
		}
	}

	public long insert(Visit a) throws GatewayException {

		a.setId(myGateway.insertVisit(a));
		this.myList.add(a);

		return a.getId();
	}

	public void update(Visit a) throws GatewayException {
		myGateway.updateVisit(a);
	}

	public void delete(long id) throws GatewayException {
		myGateway.removeVisit(id);
	}

}
