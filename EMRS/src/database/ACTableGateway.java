package database;


import java.util.ArrayList;

import models.AnteriorChamber;

public interface ACTableGateway {
	public long insertAnteriorChamber(AnteriorChamber dv) throws GatewayException;
	public void removeAnteriorChamber(Long vid) throws GatewayException;
	public abstract void updateAnteriorChamber(AnteriorChamber ac);
	public AnteriorChamber fetchAnteriorChamberForVisit(long vid) throws GatewayException;
	ArrayList<Object> fetchACColsForVisit(long id) throws GatewayException;
	
}