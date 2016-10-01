package database;

import models.Gonio;

public interface GonioTableGateway {

	public long insertGonioForVisit(Gonio g) throws GatewayException;
	
	public Gonio fetchGonioForVisit(long vid) throws GatewayException;
	
	public void updateGonioForVisit(long vid) throws GatewayException;

	public void removeGonio(Long vid) throws GatewayException;

}
