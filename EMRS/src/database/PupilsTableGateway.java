package database;

import java.util.ArrayList;

import models.Pupils;

public interface PupilsTableGateway {
	public long insertPupils(Pupils dv) throws GatewayException;
	public void removePupils(Long vid) throws GatewayException;
	public ArrayList<Object> fetchPupilsColsForVisit(long id) throws GatewayException;
	public abstract Pupils fetchPupilsForVisit(long vid) throws GatewayException;
}
