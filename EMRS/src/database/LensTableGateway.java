package database;

import models.Lens;

public interface LensTableGateway {
	
	public long insertLens(Lens dv) throws GatewayException;

	public Lens fetchLensForVisit(long vid) throws GatewayException;

	public void updateLens(Lens l) throws GatewayException;

	public void removeLens(Long vid) throws GatewayException;
}
