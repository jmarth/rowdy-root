package models;

import java.util.List;

import database.GatewayException;

public interface SCRUDWithPatientList {

	abstract public List<Object> getMyList(Patient p);
	abstract public void loadMyListForPatient(Patient p) throws GatewayException;
	abstract public Object find(Patient p) throws GatewayException;
	abstract public long insert(Object o) throws GatewayException;
	abstract public Object get(Patient p) throws GatewayException;
	abstract public void update(Patient p) throws GatewayException;
	abstract public void delete(long id) throws GatewayException;
	////
}
