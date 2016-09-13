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

	/**
	 * Returns ArrayList of Documents in the DocumentList
	 * 
	 * @return All Documents in list
	 */
	public List<Document> getDocumentList() {
		return myList;
	}

	public void loadDocumentListForPatient(Patient p) {

		try {
			myList = myGateway.fetchDocumentsForPatient(p);

		} catch (GatewayException e) {
			System.err.println("DocumentList failed to load from its gateway. In DocumentList Model");
			e.printStackTrace();
		}
	}
}
