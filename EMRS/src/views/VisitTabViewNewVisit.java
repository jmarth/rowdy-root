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
import models.Gonio;
import models.HomeModel;
import models.IOPMeasurement;
import models.Lens;
import models.Patient;
import models.Pupils;
import models.Refraction;
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
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
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
	
	private JPanel panel_1 = new JPanel();
	
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
		System.out.println("Visit id = " + v.getId() + " pid " + patient.getId());
		createView();
		
		panel_Buttons.setVisible(false);
		//btnSave.setVisible(false);
		//btnCancel.setVisible(false);
		
		disableFields(this);
		
		if (forJXTaskPane) {
			// will be for the JXTaskPane
			// Note, this boolean will probably not be used.
			
			//TODO
			try {
				//sketches
				ArrayList<Image> sketches = (ArrayList<Image>) homeModel.getStg().fetchSketchesForVisitByTable(v.getId(), "sketches_sle");
				if(sketches.size() != 0) {
					Image img = sketches.get(0);
					ImageIcon icon = new ImageIcon(img);
					label_SLE_Sketch.setIcon(icon);
				}
				sketches = (ArrayList<Image>) homeModel.getStg().fetchSketchesForVisitByTable(v.getId(), "sketches_gonio");
				if(sketches.size() != 0) {
					Image img = sketches.get(0);
					ImageIcon icon = new ImageIcon(img);
					panel_Gonio.getSketchLabel().setIcon(icon);
				}
				sketches = (ArrayList<Image>) homeModel.getStg().fetchSketchesForVisitByTable(v.getId(), "sketches_fundus");
				if(sketches.size() != 0) {
					Image img = sketches.get(0);
					ImageIcon icon = new ImageIcon(img);
					panel_Fundus.getSketchLabel().setIcon(icon);
				}
				//get db rows in object list for panels from database to populate
				ArrayList<Object> visitCols = (ArrayList<Object>) homeModel.getVtg().fetchVisitsCols(v.getId());
				ArrayList<Object> dvCols = (ArrayList<Object>) homeModel.getDvtg().fetchDistanceVisionColsForVisit(v.getId());
				ArrayList<Object> rxCols = (ArrayList<Object>) homeModel.getGlsRxTG().fetchGlassesRxColsForVisit(v.getId());
				ArrayList<Object> refractCols = (ArrayList<Object>) homeModel.getRefractionTG().fetchRefractionsColsForVisit(v.getId());
				ArrayList<Object> pupilsCols = (ArrayList<Object>) homeModel.getPupilsTG().fetchPupilsColsForVisit(v.getId());
				ArrayList<Object> acCols = (ArrayList<Object>) homeModel.getaCTG().fetchACColsForVisit(v.getId());
				ArrayList<Object> lensCols = (ArrayList<Object>) homeModel.getLensTG().fetchLensColsForVisit(v.getId());
				ArrayList<Object> iopCols = (ArrayList<Object>) homeModel.getIopTG().fetchIOPColsForVisit(v.getId());
				ArrayList<Object> gonioCols = (ArrayList<Object>) homeModel.getGonioTG().fetchGonioForVisit(v.getId());
				ArrayList<Object> fundusCols = (ArrayList<Object>) homeModel.getFundusTG().fetchFundusExamsForVisit(v.getId());
				System.out.println("VISIT ID = " + v.getId());

				populateVisitPanel(visitCols); // this panel
				populateDVPanel(dvCols);
				populateGlassesRxPanel(rxCols);
				populateRefractionPanel(refractCols);
				panel_SLE_Pupils.setFields(pupilsCols);
				panel_SLE_AC.setFields(acCols);
				panel_SLE_Lens.setFields(lensCols);
				panel_IOP.setFields(iopCols);
				panel_Gonio.setFields(gonioCols);
				panel_Fundus.setFields(fundusCols);
				
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
	
	public void populateDVPanel(ArrayList<Object> dvCols) {
		
		ArrayList<Component> clist = new ArrayList<Component>();
		setFields(panel_DV, clist);
		
		int i = 0;
		for (Component c : clist) {
			if (c instanceof JTextField) {
				((JTextField)c).setText(dvCols.get(i).toString());
			}
			i++;
		}
	}
	
	public void populateGlassesRxPanel(ArrayList<Object> rxCols) {
		
		ArrayList<Component> clist = new ArrayList<Component>();
		setFields(panel_GlassesRx, clist);
		
		int i = 0;
		for (Component c : clist) {
			if (c instanceof JTextField) {
				((JTextField)c).setText(rxCols.get(i).toString());
			}
			i++;
		}
			
		
	}
	public void populateRefractionPanel(ArrayList<Object> refractCols) {
		
		ArrayList<Component> clist = new ArrayList<Component>();
		setFields(panel_Refraction, clist);
		
		int i = 0;
		for (Iterator<Component> iter = clist.iterator(); iter.hasNext(); ) {
			Component c = iter.next();
//			System.out.println("\t i = " +i+": c ="+c.getClass()+" and refractCols = "+refractCols.get(i).toString());
			if (i == 0) {
				panel_Refraction.setFields(refractCols);
				iter.next();//skip
			}
//			System.out.println("xxx"+c.getClass());
			if (c instanceof JTextField) {
//				System.out.println("HELLO??"+refractCols.get(i).getClass());
				((JTextField)c).setText(refractCols.get(i).toString());
			}
			i++;
		}
			
		
	}

	
	public void populateVisitPanel(ArrayList<Object> visitCols) {
		String temp; int i = -1;
		
		temp = visitCols.get(++i).toString();
		textArea_CC.setText(temp);
		temp = visitCols.get(++i).toString();
		textArea_Assessment.setText(temp);
		temp = visitCols.get(++i).toString();
		textArea_Plan.setText(temp);
	}
	
	void setFields(Container container, ArrayList<Component> cl) {
		
	    for (Component c : container.getComponents()) {
	        if (c instanceof JTextField) {
	           cl.add(c);
	        } 
	        else if (c instanceof JTextArea) {
	        	cl.add(c);
		    }
	        else if (c instanceof JRadioButton) {
	        	cl.add(c);
	        }
	        else if (c instanceof JCheckBox) {
	        	cl.add(c);
	        }
	        else if (c instanceof JComboBox) {
	        	cl.add(c);
	        }
	        else if (c instanceof Container) {
	        	setFields((Container)c, cl);
	        }
	    }
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
				
				Lens l = panel_SLE_Lens.createNewLens();
				l.setVid(vid);
				
				IOPMeasurement iop = panel_IOP.createNewIOP();
				iop.setVid(vid);
				
				Gonio g = panel_Gonio.createNewGonio();
				g.setVid(vid);
				
				FundusExam f = panel_Fundus.createNewFundusExam();
				f.setVid(vid);
				
				
				if (label_SLE_Sketch.getIcon() != null){
					homeModel.getStg().insertSketchToTable(new File("firstSketch.png"), vid, "sketches_sle");
				}
				if (panel_Gonio.getSketchLabel().getIcon() != null){
					homeModel.getStg().insertSketchToTable(new File("GonioTempSketch.png"), vid, "sketches_gonio");
				}
				if (panel_Fundus.getSketchLabel().getIcon() != null){
					homeModel.getStg().insertSketchToTable(new File("FundusTempSketch.png"), vid, "sketches_fundus");
				}
				
				long dv_id = homeModel.getDvtg().insertDistanceVision(dv);
				long glsRx_id = homeModel.getGlsRxTG().insertGlassesRx(glsRx);
				long r_id = homeModel.getRefractionTG().insertRefraction(r);
				long p_id = homeModel.getPupilsTG().insertPupils(p);
				long ac_id = homeModel.getaCTG().insertAnteriorChamber(ac);
				long lens_id = homeModel.getLensTG().insertLens(l);
				long iop_id = homeModel.getIopTG().insertIOPMeasurement(iop);
				long g_id = homeModel.getGonioTG().insertGonio(g);
				long fun_id = homeModel.getFundusTG().insertFundusExam(f);
				
				//set its id to what the DB gave us
				dv.setId(dv_id);
				glsRx.setId(glsRx_id);
				r.setId(r_id);
				p.setId(p_id);
				ac.setId(ac_id);
				l.setId(lens_id);
				iop.setId(iop_id);
				g.setId(g_id);
				f.setId(fun_id);
				
				//dunno what we doing with these lists
				
				homeModel.getVl().loadFromGateway();
				showVisitTabView();
				
			} catch (GatewayException e1) {
				e1.printStackTrace();
			}
						
		}
				
	}
	
	private class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			showVisitTabView();
		}
		
	}
	
	void disableFields (Container container) {
	    for (Component c : container.getComponents()) {
	        if (c instanceof JTextField) {
	           ((JTextField)c).setEditable(false);
	        } 
	        else if (c instanceof JTextArea) {
		           ((JTextArea)c).setEditable(false);
		    }
	        else if (c instanceof JRadioButton) {
	        	((JRadioButton)c).setEnabled(false);
	        }
	        else if (c instanceof JCheckBox) {
	        	((JCheckBox)c).setEnabled(false);
	        }
	        else if (c instanceof JComboBox) {
	        	((JComboBox)c).setEnabled(false);
	        }
	        else if (c instanceof JButton) {
	        	((JButton)c).setVisible(false);
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
		
		// was for testing, wraps everything in a JScrollPane
		//JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//add(scrollPane, "cell 0 0,grow");
		
		JPanel panel_Everything = new JPanel();
		//scrollPane.setViewportView(panel_Everything);
		panel_Everything.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][]"));
		add(panel_Everything, "cell 0 0,grow");
		
		
		//Layout should be in this order
		
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
		
		
		// SLE Lens
		panel_SLE_Lens = new PanelLens();
		panel_SLE.add(panel_SLE_Lens, "cell 0 2,grow");
		
		
		
		// SLE SKETCH
		JPanel panel_SLE_Diagram = new JPanel();
		panel_SLE.add(panel_SLE_Diagram, "cell 0 3");
		panel_SLE_Diagram.setBorder(new TitledBorder(null, "Diagram", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_SLE_Diagram.setLayout(new BoxLayout(panel_SLE_Diagram, BoxLayout.Y_AXIS));
	
		JButton btnSLESketch = new JButton("Sketch");
		btnSLESketch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Paint firstSketch = new Paint(homeModel, patient, label_SLE_Sketch, "firstSketch");
				firstSketch.setContentPane(firstSketch.getContentPane());
				firstSketch.setSize(new Dimension(600,600));
				firstSketch.setResizable(false);
				
				panel_1 = (JPanel) firstSketch.getContentPane();
				panel_1.setVisible(true);
				firstSketch.setVisible(true);
				}
		});
		panel_SLE_Diagram.add(btnSLESketch);
	
		label_SLE_Sketch = new JLabel("");
		panel_SLE_Diagram.add(label_SLE_Sketch);

		
		
		//IOP
		panel_IOP = new PanelIOP();
		panel_Vision.add(panel_IOP, "cell 0 4,grow");
		
		//GONIO
		panel_Gonio = new PanelGonio(homeModel, patient);
		panel_Vision.add(panel_Gonio, "cell 0 5,grow");
		
		//FUNDUS
		panel_Fundus = new PanelFundus(homeModel, patient);
		panel_Vision.add(panel_Fundus, "cell 0 6,grow");
		/*
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
		btnCancel.addActionListener(new CancelListener());
		panel_Buttons.add(btnCancel);

	}
}
