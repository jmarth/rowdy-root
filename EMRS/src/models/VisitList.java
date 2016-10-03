package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.VisitTableGateway;
import database.VisitTableGatewaySQLite;
import database.GatewayException;

/**
 * Keeps a list of visits for the current active patient
 * @author Charles
 *
 */
public class VisitList {

	private VisitTableGateway myGateway;
	private List<Visit> myList;
	private Map<Long, Visit> myVidMap;

	/**
	 * Construct a new VisitList
	 */
	public VisitList() {

		myList = new ArrayList<Visit>();

		try {
			myGateway = new VisitTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From VisitList, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From VisitList, IO Exception");
			// e.printStackTrace();
		}
	}

	public List<Visit> getMyList() {

		return myList;
	}

	public void loadMyListForPatient(Patient p) throws GatewayException {

		myVidMap = new HashMap<Long, Visit>();
		
		try {
			myList = myGateway.fetchVisitsForPatient(p.getId());
			System.out.println(this.toStringTest());

			for(Visit e:myList){
				e.loadVisitFromPatient();
				myVidMap.put(e.getId(), e);
			}

			

		} catch (GatewayException e) {
			System.err.println("VisitList failed to load from its gateway. In VisitList Model");
//			e.printStackTrace();
		}
	}

	public long insert(Visit a) throws GatewayException {

		a.setId(myGateway.insertVisit(a));
		this.myList.add(a);

		return a.getId();
	}
	
	public Visit getVisitById(long id) {
		return myVidMap.get(id);
	}

	public void update(Visit a) throws GatewayException {
		myGateway.updateVisit(a);
	}

	public void delete(long id) throws GatewayException {
		myGateway.removeVisit(id);
	}
	public String toStringTest() {
		String n = "";
		for (Visit v: myList)
			n += "pid: "+v.getPid()+"| vid="+v.getId()+" ";
		return n;
	}

}
