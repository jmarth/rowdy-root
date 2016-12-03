package models;

import java.awt.image.BufferedImage;
import java.io.IOException;

import database.GatewayException;
import database.SketchTableGateway;
import database.SketchTableGatewaySQLite;

public class Sketches {
	
	private SketchTableGateway myGateway;
	private BufferedImage imageSLE, imageGonio, imageFundus;
	
	public Sketches() {
		try {
			myGateway = new SketchTableGatewaySQLite();
		} catch (IOException e) {
			System.err.println("From Sketches, SQLite DB file not found.");
		} catch (GatewayException e) {
			System.err.println("From Sketches, could not connect to DB");
		}
	}
	
	public void loadSketches(long vid) {
		//TODO what if load null?
		try {
			imageSLE = myGateway.fetchSketchForVisitByTable(vid, "sketches_SLE");
			imageGonio = myGateway.fetchSketchForVisitByTable(vid, "sketches_gonio");
			imageFundus = myGateway.fetchSketchForVisitByTable(vid, "sketches_fundus");
		} catch (GatewayException e) {
			System.err.println("From Sketches, failed getting one of the sketches from DB");
//			e.printStackTrace();
		}
	}
	
	public void insertSLESketch(long vid) {
		try {
			if (imageSLE == null) {
				return;
			} else {
				myGateway.insertSketchToTable(imageSLE, vid, "sketches_sle");
			}
		} catch (GatewayException e) {
			System.err.println("From Sketches: cannot insert to DB.");
//			e.printStackTrace();
		}
	}
	
	public void insertFundusSketch(long vid) {
		try {
			if (imageFundus == null) {
				return;
			} else {
				myGateway.insertSketchToTable(imageFundus, vid, "sketches_fundus");
			}
		} catch (GatewayException e) {
			System.err.println("From Sketches: cannot insert to DB.");
//			e.printStackTrace();
		}
		
	}
	
	public void insertGonioSketch(long vid) {
		try {
			if (imageGonio == null) {
				return;
			} else {
				myGateway.insertSketchToTable(imageGonio, vid, "sketches_gonio");
			}
		} catch (GatewayException e) {
			System.err.println("From Sketches: cannot insert to DB.");
//			e.printStackTrace();
		}
	}
	
	public void setSketches(BufferedImage imageSLE, BufferedImage imageGonio, BufferedImage imageFundus) {
		this.imageSLE = imageSLE;
		this.imageGonio = imageGonio;
		this.imageFundus = imageFundus;

	}

	public BufferedImage getImageSLE() {
		return imageSLE;
	}

	public void setImageSLE(BufferedImage imageSLE) {
		this.imageSLE = imageSLE;
	}

	public BufferedImage getImageGonio() {
		return imageGonio;
	}

	public void setImageGonio(BufferedImage imageGonio) {
		this.imageGonio = imageGonio;
	}

	public BufferedImage getImageFundus() {
		return imageFundus;
	}

	public void setImageFundus(BufferedImage imageFundus) {
		this.imageFundus = imageFundus;
	}

	public void updateSLESketch(long vid) {
		try {
			if (imageSLE == null) {
				return;
			} else {
				myGateway.updateSketchToTable(imageSLE, vid, "sketches_sle");
			}
		} catch (GatewayException e) {
			System.err.println("From Sketches: cannot update to DB.");
//			e.printStackTrace();
		}
	}

	public void updateFundusSketch(long vid) {
		try {
			if (imageFundus == null) {
				return;
			} else {
				myGateway.updateSketchToTable(imageFundus, vid, "sketches_fundus");
			}
		} catch (GatewayException e) {
			System.err.println("From Sketches: cannot update to DB.");
//			e.printStackTrace();
		}
		
	}

	public void updateGonioSketch(long vid) {
		try {
			if (imageGonio == null) {
				return;
			} else {
				myGateway.updateSketchToTable(imageGonio, vid, "sketches_gonio");
			}
		} catch (GatewayException e) {
			System.err.println("From Sketches: cannot update to DB.");
//			e.printStackTrace();
		}
		
	}
	
	
}
