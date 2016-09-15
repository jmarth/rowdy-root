package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.DocumentTableGateway;
import database.DocumentTableGatewaySQLite;
import database.GatewayException;

public class DocumentList {

	private DocumentTableGateway myGateway;
	private List<Document> myList;

	/**
	 * Construct a new DocumentList
	 */
	public DocumentList() {

		myList = new ArrayList<Document>();

		try {
			myGateway = new DocumentTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From DocumentList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From DocumentList, IO Exception");
			// e.printStackTrace();
		}
	}

	public List<Document> getMyList() {

		return myList;
	}

	public void loadMyListForPatient(Patient p) throws GatewayException {

		try {
			myList = myGateway.fetchDocumentsForPatient(p);

		} catch (GatewayException e) {
			System.err.println("DocumentList failed to load from its gateway. In DocumentList Model");
			e.printStackTrace();
		}
	}

	public long insert(Document a) throws GatewayException {

		a.setId(myGateway.insertDocument(a));
		this.myList.add(a);

		return a.getId();
	}

	
//	public void update(Document a) throws GatewayException {
//		myGateway.updateDocument(a);
//	}

	public void delete(long id) throws GatewayException {
		myGateway.removeDocument(id);
	}

}
