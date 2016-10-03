package database;

import models.GlassesRx;

public interface GlassesRxTableGateway {
	
	public long insertGlassesRx(GlassesRx dv) throws GatewayException;

	public GlassesRx fetchGlassesRxForVisit(long vid) throws GatewayException;

	public void updateGlassesRxForVisit(long vid) throws GatewayException;

	public void removeGlassesRx(Long vid) throws GatewayException;

}