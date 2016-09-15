package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.SurgeryTemplatesTableGateway;
import database.SurgeryTemplatesTableGatewaySQLite;
import database.GatewayException;

public class SurgeryTemplatesList {

	private SurgeryTemplatesTableGateway myGateway;
	private List<SurgeryTemplate> myList;

	/**
	 * Construct a new SurgeryTemplateList
	 */
	public SurgeryTemplatesList() {

		myList = new ArrayList<SurgeryTemplate>();

		try {
			myGateway = new SurgeryTemplatesTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From SurgeryTemplateList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From SurgeryTemplateList, IO Exception");
			// e.printStackTrace();
		}
	}

	public List<SurgeryTemplate> getMyList() {

		return myList;
	}

	public void loadMyListForPatient(Patient p) throws GatewayException {

		try {
			myList = myGateway.fetchAllSurgeries(); //patient does not have specific templates, so all surgeries

		} catch (GatewayException e) {
			System.err.println("SurgeryTemplateList failed to load from its gateway. In SurgeryTemplateList Model");
//			e.printStackTrace();
		}
	}

	public long insert(SurgeryTemplate a) throws GatewayException {

		a.setId(myGateway.insertSurgeryTemplate(a));
		this.myList.add(a);

		return a.getId();
	}

	public void update(SurgeryTemplate a) throws GatewayException {
		myGateway.updateSurgeryTemplate(a);
	}

	public void delete(long id) throws GatewayException {
		myGateway.removeSurgeryTemplate(id);
	}

}
