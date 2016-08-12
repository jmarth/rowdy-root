package views;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.miginfocom.swing.MigLayout;
import panels.PanelAC;
import panels.PanelRefraction;
import panels.PanelDistanceVision;
import panels.PanelFundus;
import panels.PanelGlassesRx;
import panels.PanelGonio;
import panels.PanelIOP;
import panels.PanelLens;
import panels.PanelPupils;

import javax.swing.border.TitledBorder;

import database.GatewayException;
import models.FundusExam;
import models.GlassesRx;
import models.HomeModel;
import models.IOPMeasurement;
import models.Patient;
import models.Pupils;
import models.Refraction;
import models.SlitLampExam;
import models.Tabs;
import models.AnteriorChamber;
import models.DistanceVision;
import models.Visit;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;

public class VisitTabViewNewVisit extends JPanel {

	private JTextArea textArea_CC;
		
	private JTextArea textArea_Assessment;
	private JTextArea textArea_Plan;

	private JPanel panel_Buttons;
	private JButton btnSave;
	private JButton btnCancel;
	
	private Patient patient;
	private JTabbedPane tabbedPane;
	private HomeModel homeModel;
	
	private JLabel label_SLE_Sketch;

	private PanelDistanceVision panel_DV;

	private PanelGlassesRx panel_GlassesRx;

	private PanelRefraction panel_Refraction;

	private PanelPupils panel_SLE_Pupils;

	private PanelAC panel_SLE_AC;

	private PanelLens panel_SLE_Lens;

	private PanelIOP panel_IOP;

	private PanelGonio panel_Gonio;

	private PanelFundus panel_Fundus;
	
	
	/**
	 * Create the panel.
	 */
	/**
	 * @wbp.parser.constructor
	 */
	public VisitTabViewNewVisit(Visit v, Patient patient, final JTabbedPane tabbedPane, HomeModel homeModel, boolean forJXTaskPane) {
		
		this.patient = patient;
		this.tabbedPane = tabbedPane;
		this.homeModel = homeModel;
				
		createView();
		
		panel_Buttons.setVisible(false);
		btnSave.setVisible(false);
		btnCancel.setVisible(false);
				
		disableFields(this);
		
		if (forJXTaskPane) {
			// will be for the JXTaskPane
			// Note, this boolean will probably not be used.
			
			//TODO
			try {
				ArrayList<Image> sketches = (ArrayList<Image>) homeModel.getStg().fetchSketchesForPatinet(v.getId());
				if(sketches.size() != 0) {
					Image img = sketches.get(0);
					ImageIcon icon = new ImageIcon(img);
					label_SLE_Sketch.setIcon(icon);
				}
			} catch (GatewayException e) {
				e.printStackTrace();
			}
			
		} else {
			
			// never used... can probably get rid of boolean and this
			// keeping boolean for now, maybe useful for sometime?
			
		}
		
		
		
	}
	
	/**
	 *  Used for an actual new visit only
	 */
	public VisitTabViewNewVisit(Patient patient, final JTabbedPane tabbedPane, HomeModel homeModel) {
		
		this.patient = patient;
		this.tabbedPane = tabbedPane;
		this.homeModel = homeModel;
		
		createView();
	}
	
	private class SaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//TODO: error checks handled by the panels themselves?
						
			Visit visit = null;
			
			// Parse ALL the things!
			// but get the vid first.
			
			visit = new Visit(
					patient.getId(),
					textArea_CC.getText(),
					textArea_Assessment.getText(),
					textArea_Plan.getText()
					);
			
			long vid = 0;
			
			try {
				
				vid = homeModel.getVtg().insertVisit(visit);
				
				DistanceVision dv = panel_DV.createNewDistanceVision();
				dv.setVid(vid);
				
				GlassesRx glsRx = panel_GlassesRx.createNewGlassesRx();
				glsRx.setVid(vid);
				
				Refraction r = panel_Refraction.createNewRefraction();
				r.setVid(vid);
				
				Pupils p = panel_SLE_Pupils.createNewPupils();
				p.setVid(vid);
				
				AnteriorChamber ac = panel_SLE_AC.createNewAC();
				ac.setVid(vid);
				
				// not done, where i stopped
				//Lens l = panel_SLE_Lens.createNewLens();
				//l.setVid(vid);
				
//				if (label_SLE_Sketch.getIcon() != null){
//					homeModel.getStg().insertSketch(new File("firstSketch.png"), vid);
//				}
				
				long dv_id = homeModel.getDvtg().insertDistanceVision(dv);
				long glsRx_id = homeModel.getGlsRxT().insertGlassesRx(glsRx);
				long r_id = homeModel.getRefractionTG().insertRefraction(r);
				long p_id = homeModel.getPupilsTG().insertPupils(p);
				long ac_id = homeModel.getaCTG().insertAnteriorChamber(ac);
				
				
				//set its id to what the DB gave us
				dv.setId(dv_id);
				glsRx.setId(glsRx_id);
				r.setId(r_id);
				p.setId(p_id);
				ac.setId(ac_id);
				
				//dunno what we doing with these lists
				
				homeModel.getVl().loadFromGateway();
				showVisitTabView();
				
			} catch (GatewayException e1) {
				e1.printStackTrace();
			}
						
		}
				
	}
	
	// should be able to use on panels, need to be tweaked
	void disableFields (Container container) {
	    for (Component c : container.getComponents()) {
	        if (c instanceof JTextField) {
	           ((JTextField)c).setEditable(false);
	        } 
	        else if (c instanceof JTextArea) {
		           ((JTextArea)c).setEditable(false);
		    }
	        else if (c instanceof Container) {
	        	disableFields((Container)c);
	        }
	    }
	}	
	
	/**
	 * sets Visit tab to VisitsTabView
	 */
	public void showVisitTabView() {
		int index = tabbedPane.indexOfTab(Tabs.ped);
		tabbedPane.setComponentAt(index, null);
		tabbedPane.setComponentAt(index, new VisitsTabView(patient, tabbedPane, homeModel));
	}	
	
	public void createView() {
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		// was for testing
		//JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//add(scrollPane, "cell 0 0,grow");
		
		JPanel panel_Everything = new JPanel();
		//scrollPane.setViewportView(panel_Everything);
		panel_Everything.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][]"));
		add(panel_Everything, "cell 0 0,grow");
		
		
		//Layout should be in this order
		// uncomment to add the panel, with all it is about 3000 px tall
		
		/*	Panel hierarchy *
		 
		    CC
		    PED
			    Vision
					DV
					GlsRx
					Refraction
					SLE <- is a local panel in Vision, no external
						pupils
						AC
						lens
						SLE Diagram <- is a local panel in SLE, no external
					IOP
					GONIO
						GONIO Diagram
					FUNDUS
						FUNDUS Diagram
			Assessment
			Plan
			Buttons
			
		 */
				
		
		// CC
		JPanel panel_CC = new JPanel();
		panel_Everything.add(panel_CC, "cell 0 0,grow");
		panel_CC.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Chief Complaint", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_CC.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_CC = new JScrollPane();
		panel_CC.add(scrollPane_CC);
		
		textArea_CC = new JTextArea();
		textArea_CC.setWrapStyleWord(true);
		textArea_CC.setLineWrap(true);
		scrollPane_CC.setViewportView(textArea_CC);
		
		
		
		// PED ===
		JPanel panel_PED = new JPanel();
		panel_Everything.add(panel_PED, "cell 0 1,grow");
		panel_PED.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Physical Exam Detail", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PED.setLayout(new MigLayout("", "[grow]", "[grow]"));
				
		
		
		// VISION ===
		JPanel panel_Vision = new JPanel();
		panel_PED.add(panel_Vision, "cell 0 0,grow");
		panel_Vision.setLayout(new MigLayout("", "[grow]", "[][][][grow][][grow][grow]"));
		
		
		
		// DV
		panel_DV = new PanelDistanceVision();
		panel_Vision.add(panel_DV, "cell 0 0,growx");
		
		/**/
		// GLASSES RX		
		panel_GlassesRx = new PanelGlassesRx();
		panel_Vision.add(panel_GlassesRx, "cell 0 1,growx");
		
		// Refraction
		panel_Refraction = new PanelRefraction();
		panel_Vision.add(panel_Refraction, "cell 0 2,growx");
		
		
		
		
		// SLE ==
		// TODO Make SLE panel contain all the others...maybe need separate objects for SLE?
		JPanel panel_SLE = new JPanel();
		panel_SLE.setBorder(new TitledBorder(null, "Slit Lamp Exam", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Vision.add(panel_SLE, "cell 0 3,grow");
		panel_SLE.setLayout(new MigLayout("", "[grow]", "[grow][][grow]"));
				
		
		
		// PUPILS
		panel_SLE_Pupils = new PanelPupils();
		panel_SLE.add(panel_SLE_Pupils, "cell 0 0,grow");
		
		
		// AC
		panel_SLE_AC = new PanelAC();
		panel_SLE.add(panel_SLE_AC, "cell 0 1,grow");
		
		/*
		// SLE Lens
		panel_SLE_Lens = new PanelLens();
		panel_SLE.add(panel_SLE_Lens, "cell 0 2,grow");
		
		
		
		// SLE SKETCH
		JPanel panel_SLE_Diagram = new JPanel();
		panel_SLE.add(panel_SLE_Diagram, "cell 0 3");
		panel_SLE_Diagram.setBorder(new TitledBorder(null, "Diagram", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_SLE_Diagram.setLayout(new BoxLayout(panel_SLE_Diagram, BoxLayout.Y_AXIS));
	
		JButton btnSLESketch = new JButton("Sketch");
		panel_SLE_Diagram.add(btnSLESketch);
	
		label_SLE_Sketch = new JLabel("<sle image>");
		panel_SLE_Diagram.add(label_SLE_Sketch);

		
		
		//IOP
		panel_IOP = new PanelIOP();
		panel_Vision.add(panel_IOP, "cell 0 4,grow");
		
		//GONIO
		panel_Gonio = new PanelGonio();
		panel_Vision.add(panel_Gonio, "cell 0 5,grow");
		
		//FUNDUS
		panel_Fundus = new PanelFundus();
		panel_Vision.add(panel_Fundus, "cell 0 6,grow");
		
		*/
		
		// Assessment
		JPanel panel_Assessment = new JPanel();
		panel_Assessment.setBorder(new TitledBorder(null, "Assesment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Everything.add(panel_Assessment, "cell 0 2,grow");
		panel_Assessment.setLayout(new BorderLayout(0, 0));
				
		JScrollPane scrollPane_Assessment = new JScrollPane();
		panel_Assessment.add(scrollPane_Assessment);
		
		textArea_Assessment = new JTextArea();
		textArea_Assessment.setWrapStyleWord(true);
		textArea_Assessment.setLineWrap(true);
		scrollPane_Assessment.setViewportView(textArea_Assessment);
		
		JPanel panel_Plan = new JPanel();
		panel_Plan.setBorder(new TitledBorder(null, "Plan", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Everything.add(panel_Plan, "cell 0 3,grow");
		panel_Plan.setLayout(new BorderLayout(0, 0));
		
		
		
		// Plan
		JScrollPane scrollPane_Plan = new JScrollPane();
		panel_Plan.add(scrollPane_Plan);
		
		textArea_Plan = new JTextArea();
		textArea_Plan.setWrapStyleWord(true);
		textArea_Plan.setLineWrap(true);
		scrollPane_Plan.setViewportView(textArea_Plan);
		
		
		
		// Buttons
		panel_Buttons = new JPanel();
		panel_Everything.add(panel_Buttons, "cell 0 4,alignx right");
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveListener());
		panel_Buttons.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		panel_Buttons.add(btnCancel);

	}
}
