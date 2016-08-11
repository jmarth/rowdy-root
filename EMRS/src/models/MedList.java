package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.AllergyTableGateway;
import database.GatewayException;
import database.MedicationsTableGateway;

public class MedList {

	private List<Med> list;
	private MedicationsTableGateway gateway;
	private HashMap<Long, Med> myIdMap;
	
	public MedList() {
		list = new ArrayList<Med>();
		myIdMap = new HashMap<Long, Med>();
	}
	
	public void loadFromGateway() {
		//fetch list of objects from the database
		try {
			List<Med> meds = gateway.fetchMeds();
			for(Med tmpMed: meds){
				myIdMap.put(tmpMed.getId(), tmpMed);
				list.add(tmpMed);
			}
		} catch (GatewayException e) {
			//TODO: handle exception here
			return;
		}
	}
	
	public List<Med> getMedList() {
		return list;
	}
	
	public List<Med> getMedListForPatient(Patient p){
		List<Med> tmpList = new ArrayList<Med>();
		
		try {
			tmpList = gateway.fetchMedsForPatient(p);
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tmpList;
	}

	public void setGateway(MedicationsTableGateway gateway) {
		this.gateway = gateway;
	}
}
