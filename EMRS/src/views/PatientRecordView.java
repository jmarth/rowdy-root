package views;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.CL;
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
	
	private DemographicView dgv;
	private hxView hxv;
	private VitalsTabMasterView vsv;
	private VitalsTabMasterView vtv;
	private LabsAndProceduresTabView lpv;
	private DocumentsTabView dtv;
	
	/**
	 * Create the frame.
	 */
	//public PatientRecordView(final HomeModel homeModel, Patient patient) {
	public PatientRecordView() {
		setBounds(100, 100, 987, 1105);
		// Create demographics tab
		JLabel lab1 = new JLabel(Tabs.demographics);
		lab1.setPreferredSize(new Dimension(145, 30));
		lab1.setHorizontalAlignment(JLabel.CENTER);
		dgv = new DemographicView();
		this.addTab("", null, dgv, null);
		this.setTabComponentAt(0, lab1);
		
		// Create allergies & meds tab
		/*TODO JLabel lab2 = new JLabel(Tabs.hx);
		lab2.setPreferredSize(new Dimension(145, 30));
		lab2.setHorizontalAlignment(JLabel.CENTER);
		hxv = new hxView();
		this.addTab(Tabs.hx, null,hxv, null);
		this.setTabComponentAt(1, lab2);
		
		// Create visits tab
		JLabel lab3 = new JLabel(Tabs.ped);
		lab3.setPreferredSize(new Dimension(145, 30));
		lab3.setHorizontalAlignment(JLabel.CENTER);
		vsv = new VitalsTabMasterView();
		this.addTab(Tabs.ped, null,vsv, null);
		this.setTabComponentAt(2, lab3);*/
		
		
		// create vitals tab
		/*TODO JLabel lab4 = new JLabel(Tabs.vitals);
		lab4.setPreferredSize(new Dimension(145, 30));
		lab4.setHorizontalAlignment(JLabel.CENTER);
				
		vtv = new VitalsTabMasterView();
		this.addTab(Tabs.vitals, null, vtv, null);
		this.setTabComponentAt(3, lab4);*/
		
		// create labs tab
		/*TODO JLabel lab6 = new JLabel(Tabs.labs);
		lab6.setPreferredSize(new Dimension(145, 30));
		lab6.setHorizontalAlignment(JLabel.CENTER);
		
		lpv = new LabsAndProceduresTabView();
		this.addTab(Tabs.labs, null,lpv, null);
		this.setTabComponentAt(4, lab6);*/
		
		// create documents tab
		/*TODO JLabel lab7 = new JLabel(Tabs.docs);
		lab7.setPreferredSize(new Dimension(145, 30));
		lab7.setHorizontalAlignment(JLabel.CENTER);
		
		dtv = new DocumentsTabView();
		this.addTab(Tabs.docs, null,dtv, null);
		this.setTabComponentAt(5, lab7);*/
		
		this.setBackground(CL.porcelian);
		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
			        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
			        int index = sourceTabbedPane.getSelectedIndex();
			        String selectedTab = sourceTabbedPane.getTitleAt(index);
			        if(selectedTab.equals(Tabs.demographics)) {
			        	dgv.reload();
			        }
			        else if (selectedTab.equals(Tabs.allergiesAndMeds)) {
			        	//TODO
			        }
			        else if (selectedTab.equals(Tabs.ped)) {
			        	vsv.reload();
			        }
			        else if (selectedTab.equals(Tabs.vitals)) {
			        	vtv.reload();
			        }
			        else if (selectedTab.equals(Tabs.labs)) {
			        	lpv.reload();
			        }else if(selectedTab.equals(Tabs.docs)){
			        	dtv.reload();
			        }else if(selectedTab.equals(Tabs.hx)){
			        	hxv.reload();
			        }
		      }
		};
		addChangeListener(changeListener);
		
		
	}
	
	@Override
	public void HideallView() {
		dgv.HideallView();;
		/*TODO hxv.HideallView();
		vsv.HideallView();
		vtv.HideallView();
		lpv.HideallView();
		dtv.HideallView();*/
	}
	
	public void ShowHxView(){
		this.ShowView();
		this.setSelectedIndex(1);
		this.hxv.ShowView();
	}
	
	public void ShowVisitsView(){
		this.ShowView();
		this.setSelectedIndex(2);
		this.vsv.ShowView();
	}

	public void ShowVitalsView(){
		this.ShowView();
		this.setSelectedIndex(3);
		this.vtv.ShowView();
	}
	
	public void ShowLabandProceduresView(){
		this.ShowView();
		this.setSelectedIndex(4);
		this.lpv.ShowView();
	}
	
	public void ShowDocumentsView(){
		this.ShowView();
		this.setSelectedIndex(5);
		this.dtv.ShowView();
	}
	
	public void ShowDemographicsView() {
		this.getHomeView().ShowPatientRecode();
		this.setSelectedIndex(0);
		this.dgv.ShowView();
	}

	@Override
	public MasterModel getMasterModel() {
		return this.getHomeView().getMasterModel();
	}

	@Override
	public void ShowView() {
		reload();
		this.setVisible(true);
	}

	@Override
	public void reload() {
		
	}

	@Override
	public HomeView getHomeView() {
		return (HomeView)SwingUtilities.getWindowAncestor(this);
	}
}
