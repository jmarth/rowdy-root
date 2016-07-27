package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.GatewayException;
import database.SurgeryTableGateway;

public class SurgeryList {
	private List<Surgery> myList;
	private SurgeryTableGateway gateway;
	private HashMap<Long, Surgery> myIdMap;
	
	/**
	 * Construct a new SurgeryList
	 */
	public SurgeryList(){
		myList = new ArrayList<Surgery>();
		myIdMap = new HashMap<Long, Surgery>();
	}
	
	/**
	 * Load records from DB into SurgeryList
	 */
	public void loadFromGateway() {
		//fetch list of objects from the database
		try {
			List<Surgery> surgeries = gateway.fetchSurgeries();
			for(Surgery tmpSurgery : surgeries){
				myIdMap.put(tmpSurgery.getId(), tmpSurgery);
				myList.add(tmpSurgery);
			}
		} catch (GatewayException e) {
			//TODO: handle exception here
			return;
		}
	}
	
	/**
	 * Returns ArrayList of Surgeries in the SurgeryList
	 * @return All Surgeries in list
	 */
	public List<Surgery> getSurgeryList() {
		return myList;
	}
	
	public List<Surgery> getSurgeryListForPatient(Patient p){
		List<Surgery> tmpList = new ArrayList<Surgery>();
		
		try {
			tmpList = gateway.fetchSurgeriesForPatient(p);
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tmpList;
	}

	public void setGateway(SurgeryTableGateway gateway) {
		this.gateway = gateway;
	}
}
