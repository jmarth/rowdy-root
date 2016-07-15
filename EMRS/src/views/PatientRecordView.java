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
		this.addTab("Profile", null, patientProfileTabView, null);
		
		// Create tab for Allergies
		JPanel allergyTabView = new AllergyTabView(patient, this, homeModel.getAtg());
		this.addTab("Allergies", null, allergyTabView, null);
		System.out.print("allgergies done");
		
		// Create tab for Visit
		final VisitsTabView visitsTabView = new VisitsTabView(patient, this, homeModel);
		this.addTab("Visits", null, visitsTabView, null);
		System.out.print("visits done");
				
		JPanel VitalsTabView = new VitalsTabView(patient, this);
		this.addTab("Vitals", null, VitalsTabView, null);
		
		
		JPanel panel_1 = new JPanel();
		this.addTab("Meds", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		this.addTab("Labs", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		this.addTab("Orders", null, panel_3, null);
		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        String selectedTab = sourceTabbedPane.getTitleAt(index);
		        if(selectedTab.equals("Visits")) {
		        	visitsTabView.populatePanes();
		        	logger.info("User selected 'Visits' tab");
		        }
		        else if (selectedTab.equals("Allergies")) {
		        	logger.info("User selected 'Allergies' tab");
		        }
		        else if (selectedTab.equals("Vitals")) {
		        	logger.info("User selected 'Vitals' tab");
		        }
		        else if (selectedTab.equals("Meds")) {
		        	logger.info("User selected 'Meds' tab");
		        }
		        else if (selectedTab.equals("Labs")) {
		        	logger.info("User selected 'Labs' tab");
		        }
		      }
		};
		addChangeListener(changeListener);
		
		
	}
}
