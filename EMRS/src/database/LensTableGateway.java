package database;

import java.util.ArrayList;

import models.Lens;

public interface LensTableGateway {
	public long insertLens(Lens dv) throws GatewayException;
	public void removeLens(Long vid) throws GatewayException;
	public abstract ArrayList<Object> fetchLensColsForVisit(long id) throws GatewayException;
	public Lens fetchLensForVisit(long vid)throws GatewayException;
}
