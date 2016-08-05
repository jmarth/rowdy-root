package models;

public class VisionExam {

	private long id;
	private long vid; // foreign key of visit it belongs to
	
	//distance vision
	private String dVODsc;
	private String dVOSsc;
	private String dVODcc;
	private String dVOScc;
	
	//autorefraction sc (sine correction)
	private float aRscODSphere;
	private float aRscODCyl;
	private float aRscODAxis;
	private float aRscOSSphere;
	private float aRscOSCyl;
	private float aRscOSAxis;
	
	//autorefraction cc (cum correction)
	private float aRccODSphere;
	private float aRccODCyl;
	private float aRccODAxis;
	private float aRccOSSphere;
	private float aRccOSCyl;
	private float aRccOSAxis;
	
	//glasses Rx
	private float glsRxODSphere;
	private float glsRxODCyl;
	private float glsRxODAxis;
	private float glsRxODAdd;
	private float glsRxOSSphere;
	private float glsRxOSCyl;
	private float glsRxOSAxis;
	private float glsRxOSAdd;
	private String glsRxNotes;
	
}
