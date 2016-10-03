package database;

import java.util.List;

import models.Document;
import models.Patient;

public interface DocumentTableGateway {
	
	public long insertDocument(Document d) throws GatewayException;

	public List<Document> fetchDocumentsForPatient(Patient p) throws GatewayException;

	public void updateDocumentForPatient(Long pid) throws GatewayException;

	public void removeDocument(Long did) throws GatewayException;
}
