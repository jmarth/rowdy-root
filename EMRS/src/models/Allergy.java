package models;

import java.awt.List;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.AllergyTableGateway;
import database.GatewayException;

public class Allergy {
	
	private long id;
	private long pid;
	private String allergy;
	private String severity;
	private String adverseReaction;
	
	
	/**
	 * Constructor to create new Allergy object
	 * @param id Allergy id (generated by DB)
	 * @param pid Patient id corresponding to this Allergy
	 * @param allergy Name of Allergy
	 * @param severity Severity of allergic reaction
	 * @param adverseReaction Symptoms of reaction
	 */
	public Allergy(long id, long pid, String allergy, String severity, String adverseReaction) {
		super();
		this.id = id;
		this.pid = pid;
		this.allergy = allergy;
		this.severity = severity;
		this.adverseReaction = adverseReaction;
	}

	/**
	 * 
	 * GETTERS AND SETTERS
	 * 
	 */
	
	public long getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getAdverseReaction() {
		return adverseReaction;
	}

	public void setAdverseReaction(String adverseReaction) {
		this.adverseReaction = adverseReaction;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
