package models;

import java.io.IOException;

import database.GatewayException;
import database.RefractionTableGateway;
import database.RefractionTableGatewaySQLite;

public class Refraction {
	
	private long id;
	private long vid;
	private int isManifest;
	private String SC_OD_Sphere;
	private String SC_OD_Cyl;
	private String SC_OD_Axis;
	private String SC_OS_Sphere;
	private String SC_OS_Cyl;
	private String SC_OS_Axis;
	private String CC_OD_Sphere;
	private String CC_OD_Cyl;
	private String CC_OD_Axis;
	private String CC_OS_Sphere;
	private String CC_OS_Cyl;
	private String CC_OS_Axis;
	private RefractionTableGateway myGateway;
	
	public Refraction() {
		try {
			myGateway = new RefractionTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From Refraction, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From Refraction, IO error");
			// e.printStackTrace();
		}
	}
	
	public Refraction(long id, long vid, int isManifest, String sC_OD_Sphere, String sC_OD_Cyl, String sC_OD_Axis,
			String sC_OS_Sphere, String sC_OS_Cyl, String sC_OS_Axis, String cC_OD_Sphere, String cC_OD_Cyl,
			String cC_OD_Axis, String cC_OS_Sphere, String cC_OS_Cyl, String cC_OS_Axis) {
		super();
		this.id = id;
		this.vid = vid;
		this.isManifest = isManifest;
		SC_OD_Sphere = sC_OD_Sphere;
		SC_OD_Cyl = sC_OD_Cyl;
		SC_OD_Axis = sC_OD_Axis;
		SC_OS_Sphere = sC_OS_Sphere;
		SC_OS_Cyl = sC_OS_Cyl;
		SC_OS_Axis = sC_OS_Axis;
		CC_OD_Sphere = cC_OD_Sphere;
		CC_OD_Cyl = cC_OD_Cyl;
		CC_OD_Axis = cC_OD_Axis;
		CC_OS_Sphere = cC_OS_Sphere;
		CC_OS_Cyl = cC_OS_Cyl;
		CC_OS_Axis = cC_OS_Axis;
	}
	
	public Refraction(int isManifest, String sC_OD_Sphere, String sC_OD_Cyl, String sC_OD_Axis,
			String sC_OS_Sphere, String sC_OS_Cyl, String sC_OS_Axis, String cC_OD_Sphere, String cC_OD_Cyl,
			String cC_OD_Axis, String cC_OS_Sphere, String cC_OS_Cyl, String cC_OS_Axis) {
		try {
			myGateway = new RefractionTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From Refraction, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From Refraction, IO error");
			// e.printStackTrace();
		}
		this.isManifest = isManifest;
		SC_OD_Sphere = sC_OD_Sphere;
		SC_OD_Cyl = sC_OD_Cyl;
		SC_OD_Axis = sC_OD_Axis;
		SC_OS_Sphere = sC_OS_Sphere;
		SC_OS_Cyl = sC_OS_Cyl;
		SC_OS_Axis = sC_OS_Axis;
		CC_OD_Sphere = cC_OD_Sphere;
		CC_OD_Cyl = cC_OD_Cyl;
		CC_OD_Axis = cC_OD_Axis;
		CC_OS_Sphere = cC_OS_Sphere;
		CC_OS_Cyl = cC_OS_Cyl;
		CC_OS_Axis = cC_OS_Axis;
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

	public int isManifest() {
		return isManifest;
	}

	public void setManifest(int isManifest) {
		this.isManifest = isManifest;
	}

	public String getSC_OD_Sphere() {
		return SC_OD_Sphere;
	}

	public void setSC_OD_Sphere(String sC_OD_Sphere) {
		SC_OD_Sphere = sC_OD_Sphere;
	}

	public String getSC_OD_Cyl() {
		return SC_OD_Cyl;
	}

	public void setSC_OD_Cyl(String sC_OD_Cyl) {
		SC_OD_Cyl = sC_OD_Cyl;
	}

	public String getSC_OD_Axis() {
		return SC_OD_Axis;
	}

	public void setSC_OD_Axis(String sC_OD_Axis) {
		SC_OD_Axis = sC_OD_Axis;
	}

	public String getSC_OS_Sphere() {
		return SC_OS_Sphere;
	}

	public void setSC_OS_Sphere(String sC_OS_Sphere) {
		SC_OS_Sphere = sC_OS_Sphere;
	}

	public String getSC_OS_Cyl() {
		return SC_OS_Cyl;
	}

	public void setSC_OS_Cyl(String sC_OS_Cyl) {
		SC_OS_Cyl = sC_OS_Cyl;
	}

	public String getSC_OS_Axis() {
		return SC_OS_Axis;
	}

	public void setSC_OS_Axis(String sC_OS_Axis) {
		SC_OS_Axis = sC_OS_Axis;
	}

	public String getCC_OD_Sphere() {
		return CC_OD_Sphere;
	}

	public void setCC_OD_Sphere(String cC_OD_Sphere) {
		CC_OD_Sphere = cC_OD_Sphere;
	}

	public String getCC_OD_Cyl() {
		return CC_OD_Cyl;
	}

	public void setCC_OD_Cyl(String cC_OD_Cyl) {
		CC_OD_Cyl = cC_OD_Cyl;
	}

	public String getCC_OD_Axis() {
		return CC_OD_Axis;
	}

	public void setCC_OD_Axis(String cC_OD_Axis) {
		CC_OD_Axis = cC_OD_Axis;
	}

	public String getCC_OS_Sphere() {
		return CC_OS_Sphere;
	}

	public void setCC_OS_Sphere(String cC_OS_Sphere) {
		CC_OS_Sphere = cC_OS_Sphere;
	}

	public String getCC_OS_Cyl() {
		return CC_OS_Cyl;
	}

	public void setCC_OS_Cyl(String cC_OS_Cyl) {
		CC_OS_Cyl = cC_OS_Cyl;
	}

	public String getCC_OS_Axis() {
		return CC_OS_Axis;
	}

	public void setCC_OS_Axis(String cC_OS_Axis) {
		CC_OS_Axis = cC_OS_Axis;
	}

	public Refraction loadRefraction(long vid) {
		try {
			return myGateway.fetchRefractionForVisit(vid);
		} catch (GatewayException e) {
			System.err.println("From Refraction, could not fetch from DB");
//			e.printStackTrace();
		}
		return null;
	}

	public long  insertRefrac(Refraction r) {
		long id= -1;
		
		try {
			id = myGateway.insertRefraction(r);
		} catch (GatewayException e) {
			System.err.println("From Refraction: cannot insert to DB.");
//			e.printStackTrace();
		}
		
		
		return id;
	}
	
	
	
}
