package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.AllergyTableGateway;
import database.GatewayException;

public class AllergyList {
	
	private List<Allergy> myList;
	private AllergyTableGateway gateway;
	private HashMap<Long, Allergy> myIdMap;
	
	/**
	 * Construct a new AllergyList
	 */
	public AllergyList(){
		myList = new ArrayList<Allergy>();
		myIdMap = new HashMap<Long, Allergy>();
	}
	
	/**
	 * Load records from DB into AllergyList
	 */
	public void loadFromGateway() {
		//fetch list of objects from the database
		try {
			List<Allergy> allergies = gateway.fetchAllergies();
			for(Allergy tmpAllergy: allergies){
				myIdMap.put(tmpAllergy.getId(), tmpAllergy);
				myList.add(tmpAllergy);
			}
		} catch (GatewayException e) {
			//TODO: handle exception here
			return;
		}
	}
	
	/**
	 * Returns ArrayList of Allergies in the AllergyList
	 * @return All Allergies in list
	 */
	public List<Allergy> getAllergyList() {
		return myList;
	}
	
	public List<Allergy> getAllergyListForPatient(Patient p){
		List<Allergy> tmpList = new ArrayList<Allergy>();
		
		try {
			tmpList = gateway.fetchAllergiesForPatient(p);
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tmpList;
	}

	public void setGateway(AllergyTableGateway gateway) {
		this.gateway = gateway;
	}
	
	

}
