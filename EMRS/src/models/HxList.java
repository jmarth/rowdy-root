package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.HxTableGateway;
import database.HxTableGatewaySQLite;
import database.GatewayException;

public class HxList {

	private HxTableGateway myGateway;
	private List<Hx> myList;

	public HxList() {

		myList = new ArrayList<Hx>();

		try {
			myGateway = new HxTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From HxList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From HxList, IO Exception");
			// e.printStackTrace();
		}
	}

	public List<Hx> getMyList() {
		return myList;
	}

	public void loadMyListForPatient(long pid) throws GatewayException {

		try {
			myList = myGateway.fetchHxForPatient(pid);

		} catch (GatewayException e) {
			System.err.println("HxList failed to load from its gateway. In AllergyList Model");
//			e.printStackTrace();
		}
	}

	public long insert(Hx a) throws GatewayException {

		a.setId(myGateway.insertHx(a));
		this.myList.add(a);

		return a.getId();
	}

	public void update(Hx a) throws GatewayException {
		myGateway.updateHx(a);
	}

	public void delete(long id) throws GatewayException {
		myGateway.removeHx(id);
	}
}

