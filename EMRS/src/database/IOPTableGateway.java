package database;

import java.util.List;

import models.IOPMeasurement;

public interface IOPTableGateway {
	public long insertIOPMeasurement(IOPMeasurement v) throws GatewayException;
	public IOPMeasurement fetchIOPMeasurementForVisit(long vid) throws GatewayException;
	public abstract List<IOPMeasurement> fetchIOPMeasurementsForVisit(long vid)throws GatewayException;
	public long updateIOPMeasurements(IOPMeasurement v) throws GatewayException;
	public void removeIOPMeasurements(Long vid) throws GatewayException;
}
