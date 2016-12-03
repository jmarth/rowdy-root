package database;

import java.awt.image.BufferedImage;

public interface SketchTableGateway {
	//public abstract List<Visit> fetchVisits() throws GatewayException;
	public abstract long insertSketchToTable(BufferedImage bufferedImage, long vid, String table) throws GatewayException;
	public BufferedImage fetchSketchForVisitByTable(long vid, String table) throws GatewayException;
	public abstract void updateSketchToTable(BufferedImage bufferedImage, long vid, String string) throws GatewayException;
	public abstract void close();
}
