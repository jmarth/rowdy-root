package database;

import java.util.ArrayList;
import java.util.List;

import models.Pupils;
import models.Patient;

public interface PupilsTableGateway {
	public abstract List<Pupils> fetchPupils() throws GatewayException;
	public List<Pupils> fetchPupilsForPatient(Patient p) throws GatewayException;
	public long insertPupils(Pupils dv) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;
	public void removePupils(Long vid) throws GatewayException;
	public ArrayList<Object> fetchPupilsColsForVisit(long id) throws GatewayException;
	public abstract Pupils fetchPupilsForVisit(long vid) throws GatewayException;
}
