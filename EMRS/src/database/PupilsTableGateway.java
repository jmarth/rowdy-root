package database;

import models.Pupils;

public interface PupilsTableGateway {
	
	public long insertPupils(Pupils dv) throws GatewayException;

	public abstract Pupils fetchPupilsForVisit(long vid) throws GatewayException;

	public void updatePupils(Pupils p) throws GatewayException;

	public void removePupils(Long vid) throws GatewayException;
}
