package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.GatewayException;
import database.SurgeryTemplatesTableGateway;

public class SurgeryTemplatesList {
	
	private List<SurgeryTemplate> list;
	private SurgeryTemplatesTableGateway gateway;
	private HashMap<Long, SurgeryTemplate> myIdMap;
	
	/**
	 * Construct a new SurgeryList
	 */
	public SurgeryTemplatesList() {
		list = new ArrayList<SurgeryTemplate>();
		myIdMap = new HashMap<Long, SurgeryTemplate>();
	}
	
	/**
	 * Load records from DB into Surgery List
	 */
	public void loadFromGateway() {
		//fetch list of objects from the database
		try {
			List<SurgeryTemplate> tmpList = gateway.fetchAllSurgeries();
			
			for(SurgeryTemplate tmp: tmpList){
				myIdMap.put(tmp.getId(), tmp);
				list.add(tmp);
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
	public List<SurgeryTemplate> getSurgeryTemplatesList() {
		return list;
	}

	public void setGateway(SurgeryTemplatesTableGateway gateway) {
		this.gateway = gateway;
	}

	public SurgeryTemplate findTemplate(String title) {
		for (SurgeryTemplate tmp : getSurgeryTemplatesList()) {
			if (title.equals(tmp.getTitle())) {
				return tmp;
			}
		}
		return null;
	}
	

}
