package models;

public class Med {
	private Long id;
	private Long pid;
	private String tradeName;
	private String genericName;
	private String directions;

	
	public Med(Long param_id, Long param_pid, String param_trade, String param_generic, String param_directions) {
		this.setId(param_id);
		this.setPid(param_pid);
		this.setTradeName(param_trade);
		this.setGenericName(param_generic);
		this.setDirections(param_directions);
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

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}


}
