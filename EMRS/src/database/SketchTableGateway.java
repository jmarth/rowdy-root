package database;

import java.awt.Image;

public interface SketchTableGateway {
	//public abstract List<Visit> fetchVisits() throws GatewayException;
	public abstract long insertSketchToTable(Image bufferedImage, long vid, String table) throws GatewayException;
	public Image fetchSketchForVisitByTable(long vid, String table) throws GatewayException;
	public abstract void updateSketchToTable(Image imageSLE, long vid, String string) throws GatewayException;
	public abstract void close();
}
