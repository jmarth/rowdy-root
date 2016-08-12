package models;

import java.io.File;
// 812w 9n
public class Visit {
	
	private long id;
	private long pid;
	private String chiefComplaint;
	private String dateCreated;
	private String assessment;
	private String plan;
	
	private File sketch1;	

	
	
	public Visit(Long id, Long pid, String chiefComplaint, String assessment, String plan, String dateCreated) {
		super();
		this.id = id;
		this.pid = pid;
		this.chiefComplaint = chiefComplaint;
		this.assessment = assessment;
		this.plan = plan;
		this.dateCreated = dateCreated;
	}

	
	public Visit(Long pid, String chiefComplaint, String plan, String assessment) {
		super();
		this.pid = pid;
		this.chiefComplaint = chiefComplaint;
		this.plan = plan;
		this.assessment = assessment;
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
	
	public File getSketch1() {
		return sketch1;
	}

	public void setSketch1(File sketch1) {
		this.sketch1 = sketch1;
	}

	public String getPlan() {
		return plan;
	}
}
