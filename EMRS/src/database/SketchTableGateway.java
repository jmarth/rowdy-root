package database;

import java.awt.Image;
import java.io.File;

public interface SketchTableGateway {
	//public abstract List<Visit> fetchVisits() throws GatewayException;
	public abstract long insertSketchToTable(File file, long vid, String table) throws GatewayException;
	public Image fetchSketchForVisitByTable(long vid, String table) throws GatewayException;
	public abstract void close();
}
