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
		try {
			imageSLE = myGateway.fetchSketchForVisitByTable(vid, "sketches_SLE");
			imageGonio = myGateway.fetchSketchForVisitByTable(vid, "sketches_gonio");
			imageFundus = myGateway.fetchSketchForVisitByTable(vid, "sketches_fundus");
		} catch (GatewayException e) {
			System.err.println("From Sketches, failed getting one of the sketches from DB");
//			e.printStackTrace();
		}
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
	
	
}
