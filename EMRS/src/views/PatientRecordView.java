package views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.Allergy;
import models.AllergyList;
import models.CL;
import models.HomeModel;
import models.Patient;
import models.Tabs;
import models.VisitList;

import javax.swing.JSeparator;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.AllergyTableGatewayMySQL;
import database.GatewayException;
import javax.swing.ScrollPaneConstants;

/**
 * Patient record view is a JTabbedPane
 * It allows users to view specific patient info
 * This view ONLY contains tabs that consist of other views
 */
public class PatientRecordView extends JTabbedPane {
	
	private static final Logger logger = LogManager.getLogger(FIndPatientsView.class);

	private Patient patient;
	
	// Variables for Allergy tab
	JPanel allergiesPanel;
	private JTable allergyTable;
	
	// Vars for Visit Tab
	private VisitList vl = new VisitList();
	
	
	/**
	 * Create the frame.
	 */
	public PatientRecordView(final HomeModel homeModel, Patient patient) {
		this.patient = patient;
		setBounds(100, 100, 987, 1105);
				
		// Create tab for Profile 
		JPanel patientProfileTabView = new ProfileTabView(patient);
		this.addTab(Tabs.demographics, null, patientProfileTabView, null);
		
		// Create tab for Allergies
		JPanel allergyTabView = new AllergyTabView(patient, this, homeModel.getAtg());
		this.addTab(Tabs.allergiesAndMeds, null, allergyTabView, null);
		System.out.print("allgergies done");
		
		// Create tab for Visit
		final VisitsTabView visitsTabView = new VisitsTabView(patient, this, homeModel);
		this.addTab(Tabs.ped, null, visitsTabView, null);
		System.out.print("visits done");
				
		JPanel VitalsTabView = new VitalsTabView(patient, this);
		this.addTab(Tabs.vitals, null, VitalsTabView, null);
		
		JPanel panel_2 = new JPanel();
		this.addTab(Tabs.testing, null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		this.addTab(Tabs.labs, null, panel_3, null);
		
		this.setBackground(CL.aliceBlue);
		
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
