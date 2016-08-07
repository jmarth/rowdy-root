package database;

import java.util.List;

import models.Patient;
import models.VisionExam;

public interface VisionTableGateway {
	public abstract List<VisionExam> fetchVisionExams() throws GatewayException;
	public List<VisionExam> fetchVisionExamsForPatient(Patient p) throws GatewayException;
	public long insertVisionExam(VisionExam ve) throws GatewayException;
	//public long updateVitals(VisionExam v) throws GatewayException;
	public void removeVisionExam(Long vid) throws GatewayException;
}
