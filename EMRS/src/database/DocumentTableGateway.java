package database;

import models.Patient;

import java.util.List;

import models.Document;

public interface DocumentTableGateway {
	public List<Document> fetchDocumentsForPatient(Patient p) throws GatewayException;
	public long insertDocument(Document d) throws GatewayException;
	public void removeDocument(Long did) throws GatewayException;
}
