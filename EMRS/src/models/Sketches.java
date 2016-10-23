package models;

import java.awt.Image;
import java.io.IOException;

import database.GatewayException;
import database.SketchTableGateway;
import database.SketchTableGatewaySQLite;

public class Sketches {
	
	private SketchTableGateway myGateway;
	private Image imageSLE, imageGonio, imageFundus;
	
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
	
	public void setSketches(Image imageSLE, Image imageGonio, Image imageFundus) {
		this.imageSLE = imageSLE;
		this.imageGonio = imageGonio;
		this.imageFundus = imageFundus;

	}

	public Image getImageSLE() {
		return imageSLE;
	}

	public void setImageSLE(Image imageSLE) {
		this.imageSLE = imageSLE;
	}

	public Image getImageGonio() {
		return imageGonio;
	}

	public void setImageGonio(Image imageGonio) {
		this.imageGonio = imageGonio;
	}

	public Image getImageFundus() {
		return imageFundus;
	}

	public void setImageFundus(Image imageFundus) {
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
