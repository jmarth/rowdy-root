package database;

import java.awt.Image;
import java.io.File;
import java.util.List;

import models.Allergy;
import models.Patient;
import models.Visit;
public interface SketchTableGateway {
	//public abstract List<Visit> fetchVisits() throws GatewayException;
	public abstract long insertSketch(File file, long vid) throws GatewayException;
	public List<Image> fetchSketchesForPatinet(long vid) throws GatewayException;
	public abstract void close();
}
