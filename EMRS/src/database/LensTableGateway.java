package database;

import models.Lens;

public interface LensTableGateway {
	
	public long insertLensForVisit(Lens dv) throws GatewayException;

	public Lens fetchLensForVisit(long vid) throws GatewayException;

	public void updateLensForVisit(Lens l) throws GatewayException;

	public void removeLens(Long vid) throws GatewayException;
}
