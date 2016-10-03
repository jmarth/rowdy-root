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
			System.err.println("From IOPList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From IOPList, IO Exception");
			// e.printStackTrace();
		}
	}

	public List<IOPMeasurement> getMyList() {

		return myList;
	}

	public void loadMyListForVisit(long vid) throws GatewayException {
		
		myList = null;

		try {
			myList = myGateway.fetchIOPMeasurementsForVisit(vid);
			
		} catch (GatewayException e) {
			System.err.println("IOPList failed to load from its gateway. In IOPList Model");
//			e.printStackTrace();
		}

	}

	public long insert(IOPMeasurement a) throws GatewayException {

		a.setId(myGateway.insertIOPMeasurement(a));
		this.myList.add(a);

		return a.getId();
	}
// TODO
//	public void update(IOPMeasurement a) throws GatewayException {
//		myGateway.updateIOPMeasurement(a);
//	}
//
//	public void delete(long id) throws GatewayException {
//		myGateway.removeIOPMeasurement(id);
//	}

}
