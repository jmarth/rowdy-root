package models;

public class FundusExam {

	private long id;
	private long vid; // foreign key of visit it belongs to
	
	//cup to disc ratio
	private float CDOD;
	private boolean CDODAb; 
	private String CDODNotes;
	
	private float CDOS;
	private boolean CDOSAb; 
	private String CDOSNotes;
	
	private boolean maculaODAb; 
	private String maculaODNotes;
	private boolean maculaOSAb; 
	private String maculaOSNotes;
	
	private boolean retinaODAb; 
	private String retinaODNotes;
	private boolean retinaOSAb; 
	private String retinaOSNotes;
	
}
