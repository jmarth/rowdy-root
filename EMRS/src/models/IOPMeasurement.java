package models;

public class IOPMeasurement {

	private long id;
	private long vid; // foreign key of visit it belongs to
	
	private float ODValue; 
	private String ODType;
	private String ODNotes;
	private float OSValue; 
	private String OSType;
	private String OSNotes;
	
}
