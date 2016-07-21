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
	
	private List<Vitals> myPatientVitals;

	/**
	 * Construct a new VitalsList
	 */
	public VitalsList() {
		myList = new ArrayList<Vitals>();
		myIdMap = new HashMap<Long, Vitals>();
		
		myPatientVitals = new ArrayList<Vitals>();
	}

	/**
	 * Load records from DB into VitalsList
	 */
	public void loadFromGateway() {

		// fetch list of objects from the database

		try {

			List<Vitals> vitals = gateway.fetchVitals();

			for (Vitals tmpVitals : vitals) {

				myIdMap.put(tmpVitals.getId(), tmpVitals);
				myList.add(tmpVitals);
			}

		} catch (GatewayException e) {

			return;
		}
	}

	/**
	 * Returns ArrayList of Vitals in the VitalsList
	 * 
	 * @return All Vitals in list
	 */
	public List<Vitals> getVitalsList() {
		return myList;
	}

	public List<Vitals> getVitalsListForPatient(Patient p) {

		List<Vitals> tmpList = new ArrayList<Vitals>();

		try {

			tmpList = gateway.fetchVitalsForPatient(p);

		} catch (GatewayException e) {

			e.printStackTrace();
		}

		return tmpList;
	}

	public void setGateway(VitalsTableGateway gateway) {
		this.gateway = gateway;
	}

	public void loadFromGatewayForPatient(Patient patient) {
		
		loadFromGateway();
		
	}
	
	

}
