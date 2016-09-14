package database;

import java.util.ArrayList;
import java.util.List;

import models.AnteriorChamber;
import models.Patient;

public interface ACTableGateway {
	public abstract List<AnteriorChamber> fetchAnteriorChambers() throws GatewayException;
	public List<AnteriorChamber> fetchAnteriorChambersForPatient(Patient p) throws GatewayException;
	public long insertAnteriorChamber(AnteriorChamber dv) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;...
	public void removeAnteriorChamber(Long vid) throws GatewayException;
	public abstract ArrayList<Object> fetchACColsForVisit(long id) throws GatewayException;
}
