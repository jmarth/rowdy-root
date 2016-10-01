package database;

import java.util.List;

import models.Hx;
import models.Patient;

public interface HxTableGateway {
	public abstract List<Hx> fetchHx() throws GatewayException;
	public List<Hx> fetchHxForPatient(Patient p) throws GatewayException;
	public long insertHx(Hx hx) throws GatewayException;
	public void updateHx(Hx hx) throws GatewayException;
	public void removeHx(long pid) throws GatewayException;
}
