package database;

import java.util.List;

import models.IOP;

public interface IOPTableGateway {
	public long insertIOP(IOP v) throws GatewayException;
	public abstract List<IOP> fetchIOPsForVisit(long vid)throws GatewayException;
	public long updateIOP(IOP v) throws GatewayException;
	public void removeIOP(Long vid) throws GatewayException;
}
