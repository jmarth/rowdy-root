package models;

public class SurgeryTemplate {
		
	private String title;
	
	private String description;

	private Long id;
	
	public SurgeryTemplate(long param_id, String param_title, String param_description) {
		super();
		this.id = param_id;
		this.title = param_title;
		this.description = param_description;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return this.title;
	}

}
