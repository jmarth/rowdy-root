package models;

import java.io.IOException;

import database.GatewayException;
import database.VisitTableGateway;
import database.VisitTableGatewayMySQL;

public class Visit {
	private long id;
	private long pid;
	private String chiefComplaint;
	private double autorefractionOdSphere;
	private double autorefractionOdCylinder;
	private double autorefractionOdAxis;
	private double autorefractionOsSphere;
	private double autorefractionOsCylinder;
	private double autorefractionOsdAxis;
	private double arcOdSphere;
	private double arcOdCylinder;
	private double arcOdAxis;
	private double arcOsSphere;
	private double arcOsCylinder;
	private double arcOsAxis;
	private double feRow1Col1;
	private double feRow1Col2;
	private double feRow2Col1;
	private double feRow2Col2;
	private String assessment;
	private String dateCreated;
	private VisitTableGatewayMySQL vtg;
	
	/**
	 * Constructor
	 * @param chiefComplaint
	 * @param autorefractionOdSphere
	 * @param autorefractionOdCylinder
	 * @param autorefractionOdAxis
	 * @param autorefractionOsSphere
	 * @param autorefractionOsCylinder
	 * @param autorefractionOsdAxis
	 * @param arcOdSphere
	 * @param arcOdCylinder
	 * @param arcOdAxis
	 * @param arcOsSphere
	 * @param arcOsCylinder
	 * @param arcOsAxis
	 * @param feRow1Col1
	 * @param feRow1Col2
	 * @param feRow2Col1
	 * @param feRow2Col2
	 * @param assessment
	 */
	public Visit(Long id, Long pid, String chiefComplaint, double autorefractionOdSphere, double autorefractionOdCylinder,
			double autorefractionOdAxis, double autorefractionOsSphere, double autorefractionOsCylinder,
			double autorefractionOsdAxis, double arcOdSphere, double arcOdCylinder, double arcOdAxis,
			double arcOsSphere, double arcOsCylinder, double arcOsAxis, double feRow1Col1, double feRow1Col2,
			double feRow2Col1, double feRow2Col2, String assessment, String dateCreated) {
		super();
		this.id = id;
		this.pid = pid;
		this.chiefComplaint = chiefComplaint;
		this.autorefractionOdSphere = autorefractionOdSphere;
		this.autorefractionOdCylinder = autorefractionOdCylinder;
		this.autorefractionOdAxis = autorefractionOdAxis;
		this.autorefractionOsSphere = autorefractionOsSphere;
		this.autorefractionOsCylinder = autorefractionOsCylinder;
		this.autorefractionOsdAxis = autorefractionOsdAxis;
		this.arcOdSphere = arcOdSphere;
		this.arcOdCylinder = arcOdCylinder;
		this.arcOdAxis = arcOdAxis;
		this.arcOsSphere = arcOsSphere;
		this.arcOsCylinder = arcOsCylinder;
		this.arcOsAxis = arcOsAxis;
		this.feRow1Col1 = feRow1Col1;
		this.feRow1Col2 = feRow1Col2;
		this.feRow2Col1 = feRow2Col1;
		this.feRow2Col2 = feRow2Col2;
		this.assessment = assessment;
		this.dateCreated = dateCreated;
		vtg = null;
		setGateway();
	}
	
	/**
	 * 
	 * @param pid
	 * @param chiefComplaint
	 * @param autorefractionOdSphere
	 * @param autorefractionOdCylinder
	 * @param autorefractionOdAxis
	 * @param autorefractionOsSphere
	 * @param autorefractionOsCylinder
	 * @param autorefractionOsdAxis
	 * @param arcOdSphere
	 * @param arcOdCylinder
	 * @param arcOdAxis
	 * @param arcOsSphere
	 * @param arcOsCylinder
	 * @param arcOsAxis
	 * @param feRow1Col1
	 * @param feRow1Col2
	 * @param feRow2Col1
	 * @param feRow2Col2
	 * @param assessment
	 */
	public Visit(Long pid, String chiefComplaint, double autorefractionOdSphere, double autorefractionOdCylinder,
			double autorefractionOdAxis, double autorefractionOsSphere, double autorefractionOsCylinder,
			double autorefractionOsdAxis, double arcOdSphere, double arcOdCylinder, double arcOdAxis,
			double arcOsSphere, double arcOsCylinder, double arcOsAxis, double feRow1Col1, double feRow1Col2,
			double feRow2Col1, double feRow2Col2, String assessment) {
		super();
		this.pid = pid;
		this.chiefComplaint = chiefComplaint;
		this.autorefractionOdSphere = autorefractionOdSphere;
		this.autorefractionOdCylinder = autorefractionOdCylinder;
		this.autorefractionOdAxis = autorefractionOdAxis;
		this.autorefractionOsSphere = autorefractionOsSphere;
		this.autorefractionOsCylinder = autorefractionOsCylinder;
		this.autorefractionOsdAxis = autorefractionOsdAxis;
		this.arcOdSphere = arcOdSphere;
		this.arcOdCylinder = arcOdCylinder;
		this.arcOdAxis = arcOdAxis;
		this.arcOsSphere = arcOsSphere;
		this.arcOsCylinder = arcOsCylinder;
		this.arcOsAxis = arcOsAxis;
		this.feRow1Col1 = feRow1Col1;
		this.feRow1Col2 = feRow1Col2;
		this.feRow2Col1 = feRow2Col1;
		this.feRow2Col2 = feRow2Col2;
		this.assessment = assessment;
		vtg = null;
		setGateway();
	}
	
	/**
	 * getter for id;
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * setter for id
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * getter for pid
	 * @return pid
	 */
	public long getPid() {
		return pid;
	}

	/**
	 * setter for pid
	 * @param pid
	 */
	public void setPid(long pid) {
		this.pid = pid;
	}

	/**
	 * chiefComplaint getter
	 * @return chiefComplaint
	 */
	public String getChiefComplaint() {
		return chiefComplaint;
	}

	/**
	 * setter for chiefComplaint
	 * @param chiefComplaint
	 */
	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}

	/**
	 * getter for autorefractionOdSphere
	 * @return autorefractionOdSphere
	 */
	public double getAutorefractionOdSphere() {
		return autorefractionOdSphere;
	}

	/**
	 * setter for autorefractionOdSphere
	 * @param autorefractionOdSphere
	 */
	public void setAutorefractionOdSphere(double autorefractionOdSphere) {
		this.autorefractionOdSphere = autorefractionOdSphere;
	}

	/**
	 * getter for autorefractionOdCylinder
	 * @return autorefractionOdCylinder
	 */
	public double getAutorefractionOdCylinder() {
		return autorefractionOdCylinder;
	}

	/**
	 * setter for autorefractionOdCylinder
	 * @param autorefractionOdCylinder
	 */
	public void setAutorefractionOdCylinder(double autorefractionOdCylinder) {
		this.autorefractionOdCylinder = autorefractionOdCylinder;
	}

	/**
	 * getter for autorefractionOdAxis
	 * @return autorefractionOdAxis
	 */
	public double getAutorefractionOdAxis() {
		return autorefractionOdAxis;
	}

	/**
	 * setter for autorefractionOdAxis
	 * @param autorefractionOdAxis
	 */
	public void setAutorefractionOdAxis(double autorefractionOdAxis) {
		this.autorefractionOdAxis = autorefractionOdAxis;
	}

	/**
	 * getter for autorefractionOsSphere
	 * @return autorefractionOsSphere
	 */
	public double getAutorefractionOsSphere() {
		return autorefractionOsSphere;
	}

	/**
	 * setter for setAutorefractionOsSphere
	 * @param autorefractionOsSphere
	 */
	public void setAutorefractionOsSphere(double autorefractionOsSphere) {
		this.autorefractionOsSphere = autorefractionOsSphere;
	}

	/**
	 * getter for autorefractionOsCylinder
	 * @return autorefractionOsCylinder
	 */
	public double getAutorefractionOsCylinder() {
		return autorefractionOsCylinder;
	}

	/**
	 * setter for autorefractionOsCylinder
	 * @param autorefractionOsCylinder
	 */
	public void setAutorefractionOsCylinder(double autorefractionOsCylinder) {
		this.autorefractionOsCylinder = autorefractionOsCylinder;
	}

	/**
	 * getter for autorefractionOsdAxis
	 * @return autorefractionOsdAxis
	 */
	public double getAutorefractionOsdAxis() {
		return autorefractionOsdAxis;
	}
	
	/**
	 * setter for autorefractionOsdAxis
	 * @param autorefractionOsdAxis
	 */
	public void setAutorefractionOsdAxis(double autorefractionOsdAxis) {
		this.autorefractionOsdAxis = autorefractionOsdAxis;
	}

	/**
	 * getter for arcOdSphere
	 * @return arcOdSphere
	 */
	public double getArcOdSphere() {
		return arcOdSphere;
	}

	/**
	 * setter for arcOdSphere
	 * @param arcOdSphere
	 */
	public void setArcOdSphere(double arcOdSphere) {
		this.arcOdSphere = arcOdSphere;
	}

	/**
	 * getter for arcOdCylinder
	 * @return arcOdCylinder
	 */
	public double getArcOdCylinder() {
		return arcOdCylinder;
	}

	/**
	 * setter for arcOdCylinder
	 * @param arcOdCylinder
	 */
	public void setArcOdCylinder(double arcOdCylinder) {
		this.arcOdCylinder = arcOdCylinder;
	}

	/**
	 * getter for arcOdAxis
	 * @return arcOdAxis
	 */
	public double getArcOdAxis() {
		return arcOdAxis;
	}

	/**
	 * setter for arcOdAxis
	 * @param arcOdAxis
	 */
	public void setArcOdAxis(double arcOdAxis) {
		this.arcOdAxis = arcOdAxis;
	}

	/**
	 * getter for arcOsSphere
	 * @return arcOsSphere
	 */
	public double getArcOsSphere() {
		return arcOsSphere;
	}

	/**
	 * setter for arcOsSphere
	 * @param arcOsSphere
	 */
	public void setArcOsSphere(double arcOsSphere) {
		this.arcOsSphere = arcOsSphere;
	}

	/**
	 * getter for arcOsCylinder
	 * @return arcOsCylinder
	 */
	public double getArcOsCylinder() {
		return arcOsCylinder;
	}

	/**
	 * setter for arcOsCylinder
	 * @param arcOsCylinder
	 */
	public void setArcOsCylinder(double arcOsCylinder) {
		this.arcOsCylinder = arcOsCylinder;
	}

	/**
	 * getter for arcOsAxis
	 * @return arcOsAxis
	 */
	public double getArcOsAxis() {
		return arcOsAxis;
	}

	/**
	 * setter for arcOsAxis
	 * @param arcOsAxis
	 */
	public void setArcOsAxis(double arcOsAxis) {
		this.arcOsAxis = arcOsAxis;
	}

	/**
	 * getter for feRow1Col1
	 * @return feRow1Col1
	 */
	public double getFeRow1Col1() {
		return feRow1Col1;
	}

	/**
	 * setter for setFeRow1Col1
	 * @param feRow1Col1
	 */
	public void setFeRow1Col1(double feRow1Col1) {
		this.feRow1Col1 = feRow1Col1;
	}

	/**
	 * getter for feRow1Col2
	 * @return feRow1Col2
	 */
	public double getFeRow1Col2() {
		return feRow1Col2;
	}

	/**
	 * setter for feRow1Col2
	 * @param feRow1Col2
	 */
	public void setFeRow1Col2(double feRow1Col2) {
		this.feRow1Col2 = feRow1Col2;
	}

	/**
	 * getter for feRow2Col1
	 * @return feRow2Col1
	 */
	public double getFeRow2Col1() {
		return feRow2Col1;
	}

	/**
	 * setter for feRow2Col1
	 * @param feRow2Col1
	 */
	public void setFeRow2Col1(double feRow2Col1) {
		this.feRow2Col1 = feRow2Col1;
	}

	/**
	 * setter for feRow2Col2
	 * @return feRow2Col2
	 */
	public double getFeRow2Col2() {
		return feRow2Col2;
	}

	/**
	 * setter for feRow2Col2
	 * @param feRow2Col2
	 */
	public void setFeRow2Col2(double feRow2Col2) {
		this.feRow2Col2 = feRow2Col2;
	}

	/**
	 * getter for assessment
	 * @return assessment
	 */
	public String getAssessment() {
		return assessment;
	}

	/**
	 * setter for assessment
	 * @param assessment
	 */
	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}
	
	/**
	 * sets the visit gateway
	 */
	public void setGateway() {
		try {
			vtg = new VisitTableGatewayMySQL();
		} catch (GatewayException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		}
	}
	
	/**
	 * getter for visit gateway
	 * @return vtg
	 */
	public VisitTableGateway getGateway() {
		return vtg;
	}
	
	/**
	 * getter for dateCreated
	 * @return dateCreated
	 */
	public String getDateCreated() {
		return dateCreated;
	}

	/**
	 * setter for dateCreated
	 * @param dateCreated
	 */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
}
