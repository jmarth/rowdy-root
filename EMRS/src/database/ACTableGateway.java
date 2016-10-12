package database;

import models.AnteriorChamber;

public interface ACTableGateway {
	
	public long insertAnteriorChamber(AnteriorChamber dv) throws GatewayException;

	public AnteriorChamber fetchAnteriorChamberForVisit(long vid) throws GatewayException;

	public abstract void updateAnteriorChamber(AnteriorChamber ac) throws GatewayException;

	public void removeAnteriorChamber(Long vid) throws GatewayException;
}