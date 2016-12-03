package models;

import java.io.Serializable;

public class Hx implements Serializable{
	private Long id;
	private Long pid;
	private String presentCondition;
	private String da;
	private String bt;
	private String pmh;
	private String psh;
	private String fh;
	private String law;
	private String pe;

	public Hx(Long id, Long pid, String pc, String da, String bt, String pmh, String psh, String fh, String law,
			String pe) {
		super();
		this.id = id;
		this.pid = pid;
		this.presentCondition = pc;
		this.da = da;
		this.bt = bt;
		this.pmh = pmh;
		this.psh = psh;
		this.fh = fh;
		this.law = law;
		this.pe = pe;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getPc() {
		return presentCondition;
	}
	public void setPc(String pc) {
		this.presentCondition = pc;
	}
	public String getDa() {
		return da;
	}
	public void setDa(String da) {
		this.da = da;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getPmh() {
		return pmh;
	}
	public void setPmh(String pmh) {
		this.pmh = pmh;
	}
	public String getPsh() {
		return psh;
	}
	public void setPsh(String psh) {
		this.psh = psh;
	}
	public String getFh() {
		return fh;
	}
	public void setFh(String fh) {
		this.fh = fh;
	}
	public String getLaw() {
		return law;
	}
	public void setLaw(String law) {
		this.law = law;
	}
	public String getPe() {
		return pe;
	}
	public void setPe(String pe) {
		this.pe = pe;
	}
	
}
