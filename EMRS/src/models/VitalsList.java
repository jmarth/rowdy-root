package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.VitalsTableGateway;
import database.GatewayException;

public class VitalsList {
	
	private List<Vitals> myList;
	private VitalsTableGateway gateway;
	private HashMap<Long, Vitals> myIdMap;
	
	/**
	 * Construct a new VitalsList
	 */
	public VitalsList(){
		myList = new ArrayList<Vitals>();
		myIdMap = new HashMap<Long, Vitals>();
	}
	
	/**
	 * Load records from DB into VitalsList
	 */
	public void loadFromGateway() {
		//fetch list of objects from the database
		try {
			List<Vitals> vitals = gateway.fetchVitals();
			for(Vitals tmpVitals: vitals){
				myIdMap.put(tmpVitals.getId(), tmpVitals);
				myList.add(tmpVitals);
			}
		} catch (GatewayException e) {
			//TODO: handle exception here
			return;
		}
	}
	
	/**
	 * Returns ArrayList of Vitals in the VitalsList
	 * @return All Vitals in list
	 */
	public List<Vitals> getVitalsList() {
		return myList;
	}
	
	public List<Vitals> getVitalsListForPatient(Patient p){
		List<Vitals> tmpList = new ArrayList<Vitals>();
		
		try {
			tmpList = gateway.fetchVitalsForPatient(p);
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmpList;
	}

	public void setGateway(VitalsTableGateway gateway) {
		this.gateway = gateway;
	}
	
	

}
