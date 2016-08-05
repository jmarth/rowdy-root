package models;

public class SlitLampExam {
	
	private long id;
	private long vid; // foreign key of visit it belongs to
	
	
	private boolean ODPupilsAb; 
	private int ODPupils;
	private boolean OSPupilsAb; 
	private int OSPupils;
	private boolean ODACAb; 
	private String ODAC;
	private boolean OSACAb; 
	private String OSAC;
	private boolean ODPseudoPh; 
	private boolean OSPseudoPh; 
	private boolean ODPCO; 
	private boolean OSPCO; 
	private float ODNS;
	private String ODNSNotes;
	private float OSNS;
	private String OSNSNotes;
	private float ODCortical;
	private String ODCorticalNotes;
	private float OSCortical;
	private String OSCorticalNotes;
	private float ODPSC;
	private String ODPSCNotes;
	private float OSPSC;
	private String OSPSCNotes;
}
