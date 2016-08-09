package database;

import java.util.List;

import models.Drug;

public interface DrugTableGateway {
	public abstract List<Drug> fetchDrugs() throws GatewayException;
	public Drug fetchDrug(String trade) throws GatewayException;
}
