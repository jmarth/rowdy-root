package models;

import java.io.IOException;
import java.io.Serializable;

import database.GatewayException;
import database.PupilsTableGateway;
import database.PupilsTableGatewaySQLite;

public class Pupils implements Serializable{
	
	private long id;
	private long vid;
	
	private int isBothPupilsNormal;
	private String bothShape;
	private String bothDiameter;
	private int isBothRAPD;
	private int isBothSynechia;
	
	private int isRightPupilNormal;
	private String rightShape;
	private String rightDiameter;
	private int isRightRAPD;
	private int isRightSynechia;
	
	private int isLeftPupilNormal;
	private String leftShape;
	private String leftDiameter;
	private int isLeftRAPD;
	private int isLeftSynechia;
	
	private transient PupilsTableGateway myGateway;
	
	public Pupils() {
		try {
			myGateway = new PupilsTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From AnteriorChamber, IO error");
			// e.printStackTrace();
		}
	}
	
	public Pupils(long id, long vid, int isBothPupilsNormal, String bothShape, String bothDiameter, int isBothRAPD,
			int isBothSynechia, int isRightPupilNormal, String rightShape, String rightDiameter,
			int isRightRAPD, int isRightSynechia, int isLeftPupilNormal, String leftShape, String leftDiameter,
			int isLeftRAPD, int isLeftSynechia) {
		super();
		this.id = id;
		this.vid = vid;
		this.isBothPupilsNormal = isBothPupilsNormal;
		this.bothShape = bothShape;
		this.bothDiameter = bothDiameter;
		this.isBothRAPD = isBothRAPD;
		this.isBothSynechia = isBothSynechia;
		this.isRightPupilNormal = isRightPupilNormal;
		this.rightShape = rightShape;
		this.rightDiameter = rightDiameter;
		this.isRightRAPD = isRightRAPD;
		this.isRightSynechia = isRightSynechia;
		this.isLeftPupilNormal = isLeftPupilNormal;
		this.leftShape = leftShape;
		this.leftDiameter = leftDiameter;
		this.isLeftRAPD = isLeftRAPD;
		this.isLeftSynechia = isLeftSynechia;
	}
	
	public Pupils(int isBothPupilsNormal, String bothShape, String bothDiameter, int isBothRAPD,
			int isBothSynechia, int isRightPupilNormal, String rightShape, String rightDiameter,
			int isRightRAPD, int isRightSynechia, int isLeftPupilNormal, String leftShape, String leftDiameter,
			int isLeftRAPD, int isLeftSynechia) {

		try {
			myGateway = new PupilsTableGatewaySQLite();
		} catch (GatewayException e) {
			System.err.println("From AnteriorChamber, cannot connect to DB");
			// e.printStackTrace();
		} catch (IOException e) {
			System.err.println("From AnteriorChamber, IO error");
			// e.printStackTrace();
		}
		this.isBothPupilsNormal = isBothPupilsNormal;
		this.bothShape = bothShape;
		this.bothDiameter = bothDiameter;
		this.isBothRAPD = isBothRAPD;
		this.isBothSynechia = isBothSynechia;
		this.isRightPupilNormal = isRightPupilNormal;
		this.rightShape = rightShape;
		this.rightDiameter = rightDiameter;
		this.isRightRAPD = isRightRAPD;
		this.isRightSynechia = isRightSynechia;
		this.isLeftPupilNormal = isLeftPupilNormal;
		this.leftShape = leftShape;
		this.leftDiameter = leftDiameter;
		this.isLeftRAPD = isLeftRAPD;
		this.isLeftSynechia = isLeftSynechia;
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
	public int isBothPupilsNormal() {
		return isBothPupilsNormal;
	}
	public void setBothPupilsNormal(int isBothPupilsNormal) {
		this.isBothPupilsNormal = isBothPupilsNormal;
	}
	public String getBothShape() {
		return bothShape;
	}
	public void setBothShape(String bothShape) {
		this.bothShape = bothShape;
	}
	public String getBothDiameter() {
		return bothDiameter;
	}
	public void setBothDiameter(String bothDiameter) {
		this.bothDiameter = bothDiameter;
	}
	public int isBothRAPD() {
		return isBothRAPD;
	}
	public void setBothRAPD(int isBothRAPD) {
		this.isBothRAPD = isBothRAPD;
	}
	public int isBothSynechia() {
		return isBothSynechia;
	}
	public void setBothSynechia(int isBothSynechia) {
		this.isBothSynechia = isBothSynechia;
	}
	public int isRightPupilNormal() {
		return isRightPupilNormal;
	}
	public void setRightPupilNormal(int isRightPupilNormal) {
		this.isRightPupilNormal = isRightPupilNormal;
	}
	public String getRightShape() {
		return rightShape;
	}
	public void setRightShape(String rightShape) {
		this.rightShape = rightShape;
	}
	public String getRightDiameter() {
		return rightDiameter;
	}
	public void setRightDiameter(String rightDiameter) {
		this.rightDiameter = rightDiameter;
	}
	public int isRightRAPD() {
		return isRightRAPD;
	}
	public void setRightRAPD(int isRightRAPD) {
		this.isRightRAPD = isRightRAPD;
	}
	public int isRightSynechia() {
		return isRightSynechia;
	}
	public void setRightSynechia(int isRightSynechia) {
		this.isRightSynechia = isRightSynechia;
	}
	public int isLeftPupilNormal() {
		return isLeftPupilNormal;
	}
	public void setLeftPupilNormal(int isLeftPupilNormal) {
		this.isLeftPupilNormal = isLeftPupilNormal;
	}
	public String getLeftShape() {
		return leftShape;
	}
	public void setLeftShape(String leftShape) {
		this.leftShape = leftShape;
	}
	public String getLeftDiameter() {
		return leftDiameter;
	}
	public void setLeftDiameter(String leftDiameter) {
		this.leftDiameter = leftDiameter;
	}
	public int isLeftRAPD() {
		return isLeftRAPD;
	}
	public void setLeftRAPD(int isLeftRAPD) {
		this.isLeftRAPD = isLeftRAPD;
	}
	public int isLeftSynechia() {
		return isLeftSynechia;
	}
	public void setLeftSynechia(int isLeftSynechia) {
		this.isLeftSynechia = isLeftSynechia;
	}

	public Pupils loadPupils(long vid) {
		try {
			return myGateway.fetchPupilsForVisit(vid);
		} catch (GatewayException e) {
			System.err.println("From Pupils, could not fetch from DB");
//			e.printStackTrace();
		}
		return null;
	}

	public long insertPupils(Pupils p) {
		long id= -1;
		
		try {
			id = myGateway.insertPupils(p);
		} catch (GatewayException e) {
			System.err.println("From Pupils: cannot insert to DB.");
//			e.printStackTrace();
		}
		
		
		return id;
	}

	public void updatePupils(Pupils p) {
		try {
			myGateway.updatePupils(p);
		} catch (GatewayException e) {
			System.err.println("From Pupils: cannot update to DB.");
//			e.printStackTrace();
		}
	}
}
