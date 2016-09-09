package views;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.CL;
import models.HomeModel;
import models.Patient;
import models.Tabs;
import models.VisitList;

/**
 * Patient record view is a JTabbedPane
 * It allows users to view specific patient info
 * This view ONLY contains tabs that consist of other views
 */
public class PatientRecordView extends JTabbedPane {
	
	private static final Logger logger = LogManager.getLogger(FindPatientsView.class);

	private Patient patient;
	
	// Variables for Allergy tab
	JPanel allergiesPanel;
	private JTable allergyTable;
	
	// Vars for Visit Tab
	private VisitList vl = new VisitList();
	
	
	/**
	 * Create the frame.
	 */
	//public PatientRecordView(final HomeModel homeModel, Patient patient) {
	public PatientRecordView(HomeView homeview, Patient patient) {
		this.patient = patient;
		setBounds(100, 100, 987, 1105);
		
		// Create demographics tab
		JLabel lab1 = new JLabel(Tabs.demographics);
		lab1.setPreferredSize(new Dimension(145, 30));
		lab1.setHorizontalAlignment(JLabel.CENTER);
		JPanel patientProfileTabView = new ProfileTabView(homeview, patient);
		this.addTab("", null, patientProfileTabView, null);
		this.setTabComponentAt(0, lab1);
		
		// Create allergies & meds tab
		JLabel lab2 = new JLabel(Tabs.hx);
		lab2.setPreferredSize(new Dimension(145, 30));
		lab2.setHorizontalAlignment(JLabel.CENTER);
		JPanel hxView = new hxView(patient, this, homeview.getHomeModel().getAtg(), homeview.getHomeModel().getMtg(), homeview.getHomeModel().getRtg(), homeview.getHomeModel().getHtg());
		this.addTab(Tabs.hx, null, hxView, null);
		this.setTabComponentAt(1, lab2);
		
		// Create visits tab
		JLabel lab3 = new JLabel(Tabs.ped);
		lab3.setPreferredSize(new Dimension(145, 30));
		lab3.setHorizontalAlignment(JLabel.CENTER);
		
		final VisitsTabView visitsTabView = new VisitsTabView(patient, this, homeview.getHomeModel());
		this.addTab(Tabs.ped, null, visitsTabView, null);
		this.setTabComponentAt(2, lab3);
		System.out.print("visits done");
		
		// create vitals tab
		JLabel lab4 = new JLabel(Tabs.vitals);
		lab4.setPreferredSize(new Dimension(145, 30));
		lab4.setHorizontalAlignment(JLabel.CENTER);
				
		JPanel VitalsTabView = new VitalsTabView(patient, this, homeview.getHomeModel());
		this.addTab(Tabs.vitals, null, VitalsTabView, null);
		this.setTabComponentAt(3, lab4);
		
		/** create testing tab
		JLabel lab5 = new JLabel(Tabs.testing);
		lab5.setPreferredSize(new Dimension(145, 30));
		lab5.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel panel_2 = new JPanel();
		this.addTab(Tabs.testing, null, panel_2);
		this.setTabComponentAt(4, lab5);
		**/
		
		// create labs tab
		JLabel lab6 = new JLabel(Tabs.labs);
		lab6.setPreferredSize(new Dimension(145, 30));
		lab6.setHorizontalAlignment(JLabel.CENTER);
		
		LabsAndProceduresTabView labs  = new LabsAndProceduresTabView(patient, this, homeview.getHomeModel().getSrg(), homeview.getHomeModel().getSttg());
		this.addTab(Tabs.labs, null, labs, null);
		this.setTabComponentAt(4, lab6);
		
		// create documents tab
		JLabel lab7 = new JLabel(Tabs.docs);
		lab7.setPreferredSize(new Dimension(145, 30));
		lab7.setHorizontalAlignment(JLabel.CENTER);
		
		DocumentsTabView docsView = new DocumentsTabView(homeview.getHomeModel().getDtg(), patient);
		this.addTab(Tabs.docs, null, docsView, null);
		this.setTabComponentAt(5, lab7);
		
		this.setBackground(CL.porcelian);
		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        String selectedTab = sourceTabbedPane.getTitleAt(index);
		        if(selectedTab.equals(Tabs.demographics)) {
		        	visitsTabView.populatePanes();
		        	logger.info("User selected 'Demographics' tab");
		        }
		        else if (selectedTab.equals(Tabs.allergiesAndMeds)) {
		        	logger.info("User selected 'Allergies & Meds' tab");
		        }
		        else if (selectedTab.equals(Tabs.ped)) {
		        	logger.info("User selected 'Physical Exam Detail' tab");
		        }
		        else if (selectedTab.equals(Tabs.vitals)) {
		        	logger.info("User selected 'Vitals' tab");
		        	
		        	// load the table
		        	
		        }
		        else if (selectedTab.equals(Tabs.testing)) {
		        	logger.info("User selected 'Special Testing' tab");
		        }
		        else if (selectedTab.equals(Tabs.labs)) {
		        	logger.info("User selected 'Labs & Procedures' tab");
		        }
		      }
		};
		addChangeListener(changeListener);
		
		
	}
}
