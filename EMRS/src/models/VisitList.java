package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.map.MultiValueMap;

import database.GatewayException;
import database.VisitTableGateway;

public class VisitList {
	private List<Visit> myList;
	private VisitTableGateway gateway;
	private HashMap<Long, Visit> myIdMap;
	MultiValueMap myPidMap;
	
	/**
	 * Construct a new VisitList
	 */
	public VisitList(){
		myList = new ArrayList<Visit>();
		myIdMap = new HashMap<Long, Visit>();
	}
	
	/**
	 * Load records from DB into VisitList
	 */
	public void loadFromGateway() {
		//fetch list of objects from the database
		try {
			myPidMap = new MultiValueMap();
			System.out.println("loading now");
			List<Visit> visitsTmp = gateway.fetchVisits();
			System.out.println("\n done loading now");
			for(Visit tmpVisit: visitsTmp){
				myIdMap.put(tmpVisit.getId(), tmpVisit);
				myPidMap.put(tmpVisit.getPid(), tmpVisit);
				myList.add(tmpVisit);
			}
		} catch (GatewayException e) {
			//TODO: handle exception here
			return;
		}
	}
	
	public MultiValueMap<Long, Visit> getMyPidMap() {
		return myPidMap;
	}

	public void setMyPidMap(MultiValueMap<Long, Visit> myPidMap) {
		this.myPidMap = myPidMap;
	}

	/**
	 * Returns ArrayList of Allergies in the VisitList
	 * @return All Allergies in list
	 */
	public List<Visit> getVisitList() {
		return myList;
	}
	
	public List<Visit> getVisitListForPatient(Patient p){
		List<Visit> tmpList = new ArrayList<Visit>();
		
		try {
			tmpList = gateway.fetchVisitsForPatinet(p);
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tmpList;
	}

	public void setGateway(VisitTableGateway gateway) {
		this.gateway = gateway;
	}
}
