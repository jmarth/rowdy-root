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
import models.MasterModel;
import models.Patient;
import models.Tabs;
import models.VisitList;

/**
 * Patient record view is a JTabbedPane
 * It allows users to view specific patient info
 * This view ONLY contains tabs that consist of other views
 */
public class PatientRecordView extends JTabbedPane implements viewinterface {
	
	private MasterModel model;
	private DemographicView dgv;
	private hxView hxv;
	private VisitsTabView vsv;
	private VitalsTabView vtv;
	private LabsAndProceduresTabView lpv;
	private DocumentsTabView dtv;
	
	/**
	 * Create the frame.
	 */
	//public PatientRecordView(final HomeModel homeModel, Patient patient) {
	public PatientRecordView(MasterModel model) {
		this.model = model;
		
		setBounds(100, 100, 987, 1105);
		
		// Create demographics tab
		JLabel lab1 = new JLabel(Tabs.demographics);
		lab1.setPreferredSize(new Dimension(145, 30));
		lab1.setHorizontalAlignment(JLabel.CENTER);
		dgv = new DemographicView(model);
		this.addTab("", null, dgv, null);
		this.setTabComponentAt(0, lab1);
		
		// Create allergies & meds tab
		JLabel lab2 = new JLabel(Tabs.hx);
		lab2.setPreferredSize(new Dimension(145, 30));
		lab2.setHorizontalAlignment(JLabel.CENTER);
		hxv = new hxView(model);
		this.addTab(Tabs.hx, null,hxv, null);
		this.setTabComponentAt(1, lab2);
		
		// Create visits tab
		JLabel lab3 = new JLabel(Tabs.ped);
		lab3.setPreferredSize(new Dimension(145, 30));
		lab3.setHorizontalAlignment(JLabel.CENTER);
		vsv = new VisitsTabView(model);
		this.addTab(Tabs.ped, null,vsv, null);
		this.setTabComponentAt(2, lab3);
		
		
		// create vitals tab
		JLabel lab4 = new JLabel(Tabs.vitals);
		lab4.setPreferredSize(new Dimension(145, 30));
		lab4.setHorizontalAlignment(JLabel.CENTER);
				
		vtv = new VitalsTabView(patient, this, homeview.getHomeModel());
		this.addTab(Tabs.vitals, null, vtv, null);
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
		
		lpv = new LabsAndProceduresTabView(model);
		this.addTab(Tabs.labs, null,lpv, null);
		this.setTabComponentAt(4, lab6);
		
		// create documents tab
		JLabel lab7 = new JLabel(Tabs.docs);
		lab7.setPreferredSize(new Dimension(145, 30));
		lab7.setHorizontalAlignment(JLabel.CENTER);
		
		dtv = new DocumentsTabView(model);
		this.addTab(Tabs.docs, null,dtv, null);
		this.setTabComponentAt(5, lab7);
		
		this.setBackground(CL.porcelian);
		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        String selectedTab = sourceTabbedPane.getTitleAt(index);
		        if(selectedTab.equals(Tabs.demographics)) {
		        	visitsTabView.populatePanes();
		        }
		        else if (selectedTab.equals(Tabs.allergiesAndMeds)) {
		        	
		        }
		        else if (selectedTab.equals(Tabs.ped)) {
		        	
		        }
		        else if (selectedTab.equals(Tabs.vitals)) {
		        		
		        }
		        else if (selectedTab.equals(Tabs.testing)) {
		        	
		        }
		        else if (selectedTab.equals(Tabs.labs)) {
		        	
		        }
		      }
		};
		addChangeListener(changeListener);
		
		
	}


	@Override
	public void showview() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void HideallView() {
		// TODO Auto-generated method stub
		
	}
}
