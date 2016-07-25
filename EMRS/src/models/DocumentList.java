package models;

import java.util.List;

import database.DocumentTableGateway;
import database.GatewayException;

import java.util.ArrayList;
import java.util.HashMap;

import models.Document;

public class DocumentList {
	
	/**
	 *  Collection of Documents
	 */
	private List<Document> myList;
	
	/**
	 * Identity map for determining if Warehouse is already in this list.
	 */
	private HashMap<Long, Document> myIdMap;
	
	/**
	 * Collection of newly added records to know when to update key in identity
	 * map.
	 */
	private ArrayList<Document> newDocuments;
	
	/**
	 * Database connection for the Document list
	 */
	private DocumentTableGateway myGateway;
	
	/**
	 * Patient who this list belongs to
	 */
	Patient patient;
	
	/**
	 * Constructor
	 */
	public DocumentList(Patient p) {
		this.patient = p;
		myList = new ArrayList<Document>();
		myIdMap = new HashMap<Long, Document>();
		newDocuments = new ArrayList<Document>();
	}
	
	/**
	 * Load all Documents from gateway
	 */
	public void loadFromGateway() {
		// fetch list of objects from the database
		List<Document> documents = null;

		try {
			documents = myGateway.fetchPatientDocuments(this.patient);
		} catch (GatewayException e) {
			e.printStackTrace();
			return;
		}

		// any Documents in list and not in database needs be removed from list

		// clear contents of list
		for (int i = myList.size() - 1; i >= 0; i--) {
			Document d = myList.get(i);
			boolean removeDocument = true;
			// don't remove a recently added record which has not been saved
			if (d.getId() == -1) {
				removeDocument = false;
			} else {
				for (Document dCheck : documents) {
					if (dCheck.getId() == d.getId()) {
						removeDocument = false;
						break;
					}
				}
			}
			// d not in database so delete it
			if (removeDocument)
				removeDocumentFromList(d);

		}

		for (Document d : documents) {
			if (!myIdMap.containsKey(d.getId()))
				addDocumentToList(d);
		}
	}
	
	/**
	 * Add a Document object to the list's collection and set its gateway to
	 * this list's gateway.
	 * 
	 * @param d Document to add
	 *           
	 */
	public void addDocumentToList(Document d) {
		myList.add(d);
		myIdMap.put(d.getId(), d); // add record to IdMap
		
	}
	
	/**
	 * Remove a Document from the list
	 * 
	 * @param d Document to remove
	 * @return Document d if found in the list, otherwise null
	 */
	public Document removeDocumentFromList(Document d) {
		if (myList.contains(d)) {
			myList.remove(d);
			myIdMap.remove(d.getId()); // also from IdMap

			return d;
		}
		return null;
	}
	
	
	// ~~~~~~~~~~~ GETTERS AND SETTERS ~~~~~~~~~~~~~~~~~~
	public List<Document> getList() {
		return myList;
	}
	public DocumentTableGateway getGateway() {
		return myGateway;
	}
	public void setList(List<Document> l) {
		myList = l;
	}

	public void setGateway(DocumentTableGateway dtg) {
		myGateway = dtg;
	}

}
