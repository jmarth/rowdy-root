package database;

import models.Refraction;

public interface RefractionTableGateway {
	
	public long insertRefraction(Refraction dv) throws GatewayException;

	public Refraction fetchRefractionForVisit(long vid) throws GatewayException;

	public void updateRefractionForVisit(Refraction r) throws GatewayException;

	public void removeRefraction(Long vid) throws GatewayException;
}
