package database;

import java.util.List;

import models.Patient;
import models.SlitLampExam;

public interface SLETableGateway {
	public abstract List<SlitLampExam> fetchSlitLampExams() throws GatewayException;
	public List<SlitLampExam> fetchSlitLampExamsForPatient(Patient p) throws GatewayException;
	public long insertSlitLampExams(SlitLampExam v) throws GatewayException;
	//public long updateSlitLampExam(SlitLampExam v) throws GatewayException;
	public void removeSlitLampExam(Long vid) throws GatewayException;
}
