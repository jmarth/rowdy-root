package database;


import models.Gonio;

public interface GonioTableGateway {
	public long insertGonio(Gonio dv) throws GatewayException;
	public void removeGonio(Long vid) throws GatewayException;
	public Gonio fetchGonioForVisit(long id)throws GatewayException;
}
