package models;

import java.io.IOException;

import javax.swing.JOptionPane;

import database.ACTableGateway;
import database.ACTableGatewaySQLite;
import database.AllergyTableGateway;
import database.AllergyTableGatewaySQLite;
import database.CommentTableGateway;
import database.CommentTableGatewaySQLite;
import database.DistanceVisionTableGateway;
import database.DistanceVisionTableGatewaySQLite;
import database.DocumentTableGateway;
import database.DocumentTableGatewaySQLite;
import database.FundusTableGateway;
import database.FundusTableGatewaySQLite;
import database.DrugTableGateway;
import database.DrugTableGatewaySQLite;
import database.GatewayException;
import database.GlassesRxTableGateway;
import database.GlassesRxTableGatewaySQLite;
import database.GonioTableGateway;
import database.GonioTableGatewaySQLite;
import database.IOPTableGateway;
import database.IOPTableGatewaySQLite;
import database.LensTableGateway;
import database.LensTableGatewaySQLite;
import database.HxTableGateway;
import database.HxTableGatewaySQLite;
import database.MedicationsTableGateway;
import database.MedicationsTableGatewaySQLite;
import database.PatientTableGateway;
import database.PatientTableGatewaySQLite;
import database.PupilsTableGateway;
import database.PupilsTableGatewaySQLite;
import database.RefractionTableGateway;
import database.RefractionTableGatewaySQLite;
import database.SketchTableGateway;
import database.SketchTableGatewaySQLite;
import database.SurgeryTableGateway;
import database.SurgeryTableGatewaySQLite;
import database.SurgeryTemplatesTableGateway;
import database.SurgeryTemplatesTableGatewaySQLite;
import database.VisitTableGateway;
import database.VisitTableGatewaySQLite;
import database.VitalsTableGateway;
import database.VitalsTableGatewaySQLite;
import views.AddPatientView;
import views.FindPatientsView;
import views.HomeView;

public class HomeModel {
	
	/*private AddPatientView addPatientView;
	private FindPatientsView findPatientsView;
	
	private PatientList patientList ;
	private VisitList vl = new VisitList();
	
	private PatientTableGateway ptg = null;
	
	private AllergyTableGateway atg = null;
	private VisitTableGateway vtg = null;
	private SketchTableGateway stg = null;
	private VitalsTableGateway vitalstg =null;
	private DocumentTableGateway dtg = null;
	private SurgeryTemplatesTableGateway sttg = null;
	private SurgeryTableGateway srg = null;
	private CommentTableGateway ctg =null;
	private MedicationsTableGateway mtg = null;
	private DrugTableGateway rtg = null;
	private HxTableGateway htg = null;
	
	private DistanceVisionTableGateway dvtg;
	private GlassesRxTableGateway glsRxTG;
	private RefractionTableGateway refractionTG;
	private PupilsTableGateway pupilsTG;
	private ACTableGateway aCTG;
	private LensTableGateway lensTG;
	private IOPTableGateway iopTG;
	private GonioTableGateway gonioTG;
	private FundusTableGateway fundusTG;
	
	public HomeModel(HomeView homeView) {
		super();
		patientList = new PatientList();
		addPatientView = new AddPatientView(homeView);
		findPatientsView = new FindPatientsView(homeView);
		setGateways();
	}
	
	public void setGateways() {
		
		//Gateway creations
		
		try {
			// for MySQL, these need to 
			ptg = new PatientTableGatewaySQLite();
			atg = new AllergyTableGatewaySQLite();
			vtg = new VisitTableGatewaySQLite();
			stg = new SketchTableGatewaySQLite();
			vitalstg = new VitalsTableGatewaySQLite();
			dtg = new DocumentTableGatewaySQLite();
			ctg = new CommentTableGatewaySQLite();
			mtg = new MedicationsTableGatewaySQLite();
			rtg = new DrugTableGatewaySQLite();
        	sttg = new SurgeryTemplatesTableGatewaySQLite();
        	srg = new SurgeryTableGatewaySQLite();
        	htg = new HxTableGatewaySQLite();
        	
			setDvtg(new DistanceVisionTableGatewaySQLite());
			setGlsRxT(new GlassesRxTableGatewaySQLite());
			setRefractionTG(new RefractionTableGatewaySQLite());
			setPupilsTG(new PupilsTableGatewaySQLite());
			setaCTG(new ACTableGatewaySQLite());
			setLensTG(new LensTableGatewaySQLite());
			setIopTG(new IOPTableGatewaySQLite());
			setGonioTG(new GonioTableGatewaySQLite());
			setFundusTG(new FundusTableGatewaySQLite());
			
			vl.setGateway(vtg);
        	vl.loadFromGateway();
        	
        	//wut was during merge...
        	//sttg = new SurgeryTemplatesTableGatewaySQLite();
        	//setSrg(new SurgeryTableGatewaySQLite());
        	        	
		} catch (GatewayException e) {
			JOptionPane.showMessageDialog(null, "Database is not responding.", "Database Offline!", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
			System.exit(1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Database is not responding.", "Database Error!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	public PatientList getPatientList() {
		return patientList;
	}

	public void setPatientList(PatientList patientList) {
		this.patientList = patientList;
	}

	public AddPatientView getAddPatientView() {
		return addPatientView;
	}

	public void setAddPatientView(AddPatientView addPatientView) {
		this.addPatientView = addPatientView;
	}

	public FindPatientsView getPatientsView() {
		return findPatientsView;
	}

	public void setFindPatientsView(FindPatientsView patientView) {
		this.findPatientsView = patientView;
	}
	
	public PatientTableGateway getPtg() {
		return ptg;
	}

	public void setPtg(PatientTableGateway ptg) {
		this.ptg = ptg;
	}

	public AllergyTableGateway getAtg() {
		return atg;
	}

	public void setAtg(AllergyTableGateway atg) {
		this.atg = atg;
	}
	
	public VitalsTableGateway getVitalstg() {
		return vitalstg;
	}

	public void setVitalstg(VitalsTableGateway vitalstg) {
		this.vitalstg = vitalstg;
	}

	public VisitTableGateway getVtg() {
		return vtg;
	}

	public void setVtg(VisitTableGateway vtg) {
		this.vtg = vtg;
	}

	public VisitList getVl() {
		return vl;
	}
	
	public void setVl(VisitList vl) {
		this.vl = vl;
	}
	
	public SketchTableGateway getStg() {
		return stg;
	}

	public void setStg(SketchTableGateway stg) {
		this.stg = stg;
	}

	public DocumentTableGateway getDtg() {
		return dtg;
	}

	public void setDtg(DocumentTableGateway dtg) {
		this.dtg = dtg;
	}

	public SurgeryTemplatesTableGateway getSttg() {
		return sttg;
	}

	public void setSttg(SurgeryTemplatesTableGateway sttg) {
		this.sttg = sttg;
	}

	public SurgeryTableGateway getSrg() {
		return srg;
	}

	public void setSrg(SurgeryTableGateway srg) {
		this.srg = srg;
	}
		
	public CommentTableGateway getCtg() {
		return ctg;
	}

	public void setCtg(CommentTableGateway ctg) {
		this.ctg = ctg;
	}

	public MedicationsTableGateway getMtg() {
		return this.mtg;
	}
	

	public RefractionTableGateway getRefractionTG() {
		return refractionTG;
	}

	public void setRefractionTG(RefractionTableGateway refractionTG) {
		this.refractionTG = refractionTG;
	}

	public DistanceVisionTableGateway getDvtg() {
		return dvtg;
	}

	public void setDvtg(DistanceVisionTableGateway dvtg) {
		this.dvtg = dvtg;
	}

	public GlassesRxTableGateway getGlsRxTG() {
		return glsRxTG;
	}

	public void setGlsRxT(GlassesRxTableGateway glsRxT) {
		this.glsRxTG = glsRxT;
	}

	public PupilsTableGateway getPupilsTG() {
		return pupilsTG;
	}

	public void setPupilsTG(PupilsTableGateway pupilsTG) {
		this.pupilsTG = pupilsTG;
	}

	public ACTableGateway getaCTG() {
		return aCTG;
	}

	public void setaCTG(ACTableGateway aCTG) {
		this.aCTG = aCTG;
	}

	public LensTableGateway getLensTG() {
		return lensTG;
	}

	public void setLensTG(LensTableGateway lensTG) {
		this.lensTG = lensTG;
	}

	public IOPTableGateway getIopTG() {
		return iopTG;
	}

	public void setIopTG(IOPTableGateway iopTG) {
		this.iopTG = iopTG;
	}

	public GonioTableGateway getGonioTG() {
		return gonioTG;
	}

	public void setGonioTG(GonioTableGateway gonioTG) {
		this.gonioTG = gonioTG;
	}

	public FundusTableGateway getFundusTG() {
		return fundusTG;
	}

	public void setFundusTG(FundusTableGateway fundusTG) {
		this.fundusTG = fundusTG;
	}

	public DrugTableGateway getRtg() {
		return rtg;
	}

	public void setRtg(DrugTableGateway rtg) {
		this.rtg = rtg;
	}

	public HxTableGateway getHtg() {
		return htg;
	}

	public void setHtg(HxTableGateway htg) {
		this.htg = htg;
	}*/
}
