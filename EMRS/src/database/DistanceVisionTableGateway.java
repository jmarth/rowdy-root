package database;

import models.DistanceVision;

public interface DistanceVisionTableGateway {

	public long insertDistanceVision(DistanceVision dv) throws GatewayException;

	public DistanceVision fetchDistanceVisionForVisit(long vid) throws GatewayException;

	public void updateDistanceVision(DistanceVision dv) throws GatewayException;

	public void removeDistanceVision(Long vid) throws GatewayException;
}
