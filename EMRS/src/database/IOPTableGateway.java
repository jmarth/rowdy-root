package database;

import java.util.List;

import models.IOPMeasurement;
import models.Patient;

public interface IOPTableGateway {
	public abstract List<IOPMeasurement> fetchIOPMeasurements() throws GatewayException;
	public List<IOPMeasurement> fetchIOPMeasurementsForPatient(Patient p) throws GatewayException;
	public long insertIOPMeasurement(IOPMeasurement v) throws GatewayException;
	//public long updateIOPMeasurements(IOPMeasurement v) throws GatewayException;
	public void removeIOPMeasurements(Long vid) throws GatewayException;
}
