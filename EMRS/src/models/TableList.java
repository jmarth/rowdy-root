package models;

import java.util.ArrayList;
import java.util.List;

import database.GatewayException;
import database.TableGateway;

public class TableList {
	
	private TableGateway tg;
	
	private List<Object> myList;
	
	public List<Object> loadListFromGatewayGivenPatient(TableGateway tg, Patient p) {
		
		myList = new ArrayList<Object>();
		
		try {
			myList = tg.fetchListForPatientGivenGateway(p, tg);
		} catch (GatewayException e) {
			System.err.println("Error getting " + p.getFirstName() + "'s list from TableGateway!");
			e.printStackTrace();
		}
		
		return myList;
	}
}