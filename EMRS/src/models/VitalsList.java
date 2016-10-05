package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.VitalsTableGateway;
import database.VitalsTableGatewaySQLite;
import database.GatewayException;

public class VitalsList {

	private VitalsTableGateway myGateway;
	private List<Vital> myList;

	/**
	 * Construct a new VitalList
	 */
	public VitalsList() {

		myList = new ArrayList<Vital>();

		try {
			myGateway = new VitalsTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From VitalList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From VitalList, IO Exception");
			// e.printStackTrace();
		}
	}

	public List<Vital> getMyList() {

		return myList;
	}

	public void loadMyListForPatient(Patient p) throws GatewayException {

		try {
			myList = myGateway.fetchVitalsForPatient(p);

		} catch (GatewayException e) {
			System.err.println("VitalList failed to load from its gateway. In VitalList Model");
			//e.printStackTrace();
		}
	}

	public long insert(Vital a) throws GatewayException {
		a.setId(myGateway.insertVitals(a));
		this.myList.add(a);
		return a.getId();
	}

	public void update(Vital a,int index) throws GatewayException {
		myGateway.updateVitals(a);
		this.myList.remove(index);
		this.myList.add(index, a);
	}

	public void delete(int index) throws GatewayException {
		Vital vt = this.myList.get(index);
		myGateway.removeVitals(vt.getId());
		this.myList.remove(index);
	}

}
