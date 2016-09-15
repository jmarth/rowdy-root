package database;

import java.util.ArrayList;

import models.GlassesRx;

public interface GlassesRxTableGateway {
	public long insertGlassesRx(GlassesRx dv) throws GatewayException;
	public void removeGlassesRx(Long vid) throws GatewayException;
	public abstract ArrayList<Object> fetchGlassesRxColsForVisit(long id) throws GatewayException;
	public GlassesRx fetchGlassesRxForVisit(long vid)throws GatewayException;
}