package models;

import java.io.IOException;

import database.ACTableGatewaySQLite;
import database.GatewayException;
import database.GlassesRxTableGateway;
import database.GlassesRxTableGatewaySQLite;

public class GlassesRx {
	
	private long id;
	private long vid;
	
	private String rx_OD_Sphere;
	private String rx_OD_Cyl;
	private String rx_OD_Axis;
	private String rx_OD_Add;
	private String rx_OS_Sphere;
	private String rx_OS_Cyl;
	private String rx_OS_Axis;
	private String rx_OS_Add;
	private String glassesRxNotes;
	
	private GlassesRxTableGateway myGateway;
	
	public GlassesRx(long id, long vid, String rx_OD_Sphere, String rx_OD_Cyl, String rx_OD_Axis, String rx_OD_Add,
			String rx_OS_Sphere, String rx_OS_Cyl, String rx_OS_Axis, String rx_OS_Add, String glassesRxNotes) {
		super();
		this.id = id;
		this.vid = vid;
		this.rx_OD_Sphere = rx_OD_Sphere;
		this.rx_OD_Cyl = rx_OD_Cyl;
		this.rx_OD_Axis = rx_OD_Axis;
		this.rx_OD_Add = rx_OD_Add;
		this.rx_OS_Sphere = rx_OS_Sphere;
		this.rx_OS_Cyl = rx_OS_Cyl;
		this.rx_OS_Axis = rx_OS_Axis;
		this.rx_OS_Add = rx_OS_Add;
		this.glassesRxNotes = glassesRxNotes;
	}
	
	public GlassesRx(String rx_OD_Sphere, String rx_OD_Cyl, String rx_OD_Axis, String rx_OD_Add,
			String rx_OS_Sphere, String rx_OS_Cyl, String rx_OS_Axis, String rx_OS_Add, String glassesRxNotes) {
		try {
			myGateway = new GlassesRxTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From GlassesRx, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From GlassesRx, IO error");
			// e.printStackTrace();
		}		this.rx_OD_Sphere = rx_OD_Sphere;
		this.rx_OD_Cyl = rx_OD_Cyl;
		this.rx_OD_Axis = rx_OD_Axis;
		this.rx_OD_Add = rx_OD_Add;
		this.rx_OS_Sphere = rx_OS_Sphere;
		this.rx_OS_Cyl = rx_OS_Cyl;
		this.rx_OS_Axis = rx_OS_Axis;
		this.rx_OS_Add = rx_OS_Add;
		this.glassesRxNotes = glassesRxNotes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVid() {
		return vid;
	}

	public void setVid(long vid) {
		this.vid = vid;
	}

	public String getRx_OD_Sphere() {
		return rx_OD_Sphere;
	}

	public void setRx_OD_Sphere(String rx_OD_Sphere) {
		this.rx_OD_Sphere = rx_OD_Sphere;
	}

	public String getRx_OD_Cyl() {
		return rx_OD_Cyl;
	}

	public void setRx_OD_Cyl(String rx_OD_Cyl) {
		this.rx_OD_Cyl = rx_OD_Cyl;
	}

	public String getRx_OD_Axis() {
		return rx_OD_Axis;
	}

	public void setRx_OD_Axis(String rx_OD_Axis) {
		this.rx_OD_Axis = rx_OD_Axis;
	}

	public String getRx_OD_Add() {
		return rx_OD_Add;
	}

	public void setRx_OD_Add(String rx_OD_Add) {
		this.rx_OD_Add = rx_OD_Add;
	}

	public String getRx_OS_Sphere() {
		return rx_OS_Sphere;
	}

	public void setRx_OS_Sphere(String rx_OS_Sphere) {
		this.rx_OS_Sphere = rx_OS_Sphere;
	}

	public String getRx_OS_Cyl() {
		return rx_OS_Cyl;
	}

	public void setRx_OS_Cyl(String rx_OS_Cyl) {
		this.rx_OS_Cyl = rx_OS_Cyl;
	}

	public String getRx_OS_Axis() {
		return rx_OS_Axis;
	}

	public void setRx_OS_Axis(String rx_OS_Axis) {
		this.rx_OS_Axis = rx_OS_Axis;
	}

	public String getRx_OS_Add() {
		return rx_OS_Add;
	}

	public void setRx_OS_Add(String rx_OS_Add) {
		this.rx_OS_Add = rx_OS_Add;
	}

	public String getGlassesRxNotes() {
		return glassesRxNotes;
	}

	public void setGlassesRxNotes(String glassesRxNotes) {
		this.glassesRxNotes = glassesRxNotes;
	}

	public GlassesRx loadGlsRx() {
		try {
			return myGateway.fetchAnteriorChamberForVisit(vid);
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, could not fetch from DB");
//			e.printStackTrace();
		}
		return null;
	}
	
	
}
