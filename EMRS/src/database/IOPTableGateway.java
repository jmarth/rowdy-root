package database;

import java.util.ArrayList;
import java.util.List;

import models.IOPMeasurement;

public interface IOPTableGateway {
	public long insertIOPMeasurement(IOPMeasurement v) throws GatewayException;
	//public long updateIOPMeasurements(IOPMeasurement v) throws GatewayException;
	public void removeIOPMeasurements(Long vid) throws GatewayException;
	public abstract ArrayList<Object> fetchIOPColsForVisit(long id)throws GatewayException;
	public abstract List<IOPMeasurement> fetchIOPMeasurementsForVisit(long vid)throws GatewayException;
}
