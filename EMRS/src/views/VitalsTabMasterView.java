package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.MasterModel;

@SuppressWarnings("serial")
public class VitalsTabMasterView extends JPanel implements viewinterface {
	
	/**
	 * 
	 */
	/*
	private final String bpunit_mmHg = "mmHg";
	private final String bpunit_Pa = "Pa";
	private final String hunit_ftIn = "\'";
	private final String hunit_in = "\"";
	private final String hunit_cm= "cm";
	*/
	
	//private VitalsList vl = new VitalsList();
	//private List<Vital> myVitalsList;//no need
	//private VitalsTableGateway vtg;// goes in vital list, or model
	//private JTable vitalsTable;
	//private Patient patient;//current Active patient
	//private int selectedRow;
	
	private VitalListView vldv;
	private VitalSCRUBView vscv;
	/**
	 * Create the panel.
	 */
	//public VitalsTabView(final Patient patient, final JTabbedPane tabbedPane, final HomeModel homeModel) {
	public VitalsTabMasterView(){
			
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.add(this.vldv);
		scrollPane.add(this.vscv);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
	}
	
	/**
	 * Populates the VitalsTable with all vitals related current Patient
	 * @param vitalsTable JTable to populate
	 * @param patient Patient JTable to populate
	 *
	 **/
	/*private void populateVitalsTable() {
		
		// Get model of VitalsTable in order to add rows
		// Declare variables
		//DefaultTableModel dtm = (DefaultTableModel) vitalsTable.getModel();
		
		
		// TODO investigate this sketchy stuff
		//vl.setGateway(vtg);
		//vl.loadFromGateway();
		
		//System.out.print("printing list "+vl);
		
		// Find all allergies for the given patient
		List<Vital> myVitalsList = this.getMasterModel().getVitalsL().getMyList();
		
		
		 * For every vital in the vitalsList
		 * .. Add that model the JTable
		 * 
		 * If it is a height, must be displayed depending on ft/inches or inches or cm
		 		
		
		for(Vital vitals : myVitalsList) {
			
			// read somewhere sb should get a size in bytes of
			// roughly how big of a string you are going to build
			StringBuilder sb = new StringBuilder(128);
			
			// the ft/inches display should be condensed into a method, but w/e for now
			boolean isString = false;
		       
	        String height_ftin_String = "";
	        int heightInt = -1;
	       
	        if (vitals.getHUnit() == null) {
	           
	        } else if (vitals.getHUnit().equals(Vital.FTIN)) {
	           
	            sb.append(vitals.getHFeet());
	            sb.append('\'');
	            sb.append(vitals.getHInches());
	            sb.append('\"');
	            height_ftin_String = sb.toString();
	            isString = true;
	           
	        } else if (vitals.getHUnit().equals("null")) {
	            heightInt = -100;
	        } else if (vitals.getHUnit().equals(Vital.IN)) {
	            heightInt = vitals.getHInches();
	        } else {
	            heightInt = vitals.getHCm();
	        }
	        
			dtm.addRow(new Object[]{
					vitals.getDateCreated(),
					vitals.getBps() + "/" + vitals.getBps() + " " + vitals.getBpUnit(),
					vitals.getBg() + " " + vitals.getBgUnit(),
					vitals.getO2sat() + "%",
					vitals.getHb() + " " + Vital.gdL,
					(isString ? height_ftin_String : heightInt + " " + vitals.getHUnit()),
					vitals.getWeight() + " " + vitals.getWUnit(),
					vitals.getNotes()	
				});
		}
		
		
		
		DefaultTableModel model = (DefaultTableModel) vitalsTable.getModel();
		
		/**
		 * Try to connect to DB through VitalsTableGateway
		 * Set the gateway of the VitalsList
		 * Load Vitals into the VitalsList
		 
		try {
			vtg = new VitalsTableGatewaySQLite();
			vl.setGateway(vtg);
			vl.loadFromGateway();
		} catch (GatewayException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		}
		
		// Find all vitals for the given patient
		vitalsList = vl.getVitalsListForPatient(patient);
		
		/**
		 * For every vitals in the vitalsList
		 * .. Add that model the JTable
		 
		for(Vitals vitals : vitalsList) {
			/*
			int height_int;
			if (vitals.gethFeet() != -1) {
				// feet is available, so is inches
				height_int = vitals.gethFeet();
			} else if (vitals.gethInches() != -1) {
				// only inches, no feet
			} else {
				// default
			}
			
		    //int height_int = vitals.getHcm();
			model.addRow(new Object[]{
					vitals.getBps(), vitals.getBpd(), vitals.getBpUnit(), vitals.getHcm(), vitals.gethUnit(), vitals.getWeight(), vitals.getwUnit(), vitals.getNotes()
				});
		}
		
	}*/
	public void ShowVitalListView(){
		this.HideallView();
		this.vldv.ShowView();
	}
	public void ShowVitalSCRUDView(){
		this.HideallView();
		this.vscv.ShowView();
	}
	@Override
	public void HideallView() {
		this.vldv.setVisible(false);
		this.vscv.setVisible(false);
	}

	@Override
	public MasterModel getMasterModel() {
		return ((PatientRecordView)this.getParent()).getMasterModel();
	}

	@Override
	public void ShowView() {
		reload();
		this.setVisible(true);
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public HomeView getHomeView() {
		return ((PatientRecordView)this.getParent()).getHomeView();
	}
}
