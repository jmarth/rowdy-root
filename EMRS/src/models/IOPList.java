package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.IOPTableGateway;
import database.IOPTableGatewaySQLite;
import database.GatewayException;

public class IOPList {

	private IOPTableGateway myGateway;
	private List<IOP> myList;
	private HashMap<Long, IOP> myIOPMap;

	/**
	 * Construct a new IOPMeasurementList
	 */
	public IOPList() {

		myList = new ArrayList<IOP>();

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

	public List<IOP> getMyList() {

		return myList;
	}

	public void loadMyListForVisit(long vid) throws GatewayException {
		
		myIOPMap = new HashMap<Long,IOP>();

		try {
			myList = myGateway.fetchIOPsForVisit(vid);
			for(IOP i : myList) {
				i.loadIOPsFromVisit();
				myIOPMap.put(i.getId(), i);
			}
		} catch (GatewayException e) {
			System.err.println("IOPList failed to load from its gateway. In IOPList Model");
//			e.printStackTrace();
		}

	}

	public long insert(IOP a) throws GatewayException {

		a.setId(myGateway.insertIOP(a));
		this.myList.add(0, a);

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
