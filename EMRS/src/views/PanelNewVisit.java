package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXTaskPane;

import database.GatewayException;
import models.AnteriorChamber;
import models.CL;
import models.DistanceVision;
import models.FundusExam;
import models.GlassesRx;
import models.Gonio;
import models.IOPMeasurement;
import models.Lens;
import models.MasterModel;
import models.Pupils;
import models.Refraction;
import models.Tabs;
import models.Visit;
import net.miginfocom.swing.MigLayout;
import visitPanels.PanelDistanceVision;
import visitPanels.PanelFundus;
import visitPanels.PanelGlassesRx;
import visitPanels.PanelGonio;
import visitPanels.PanelIOP;
import visitPanels.PanelRefraction;
import visitPanels.PanelSLE;

@SuppressWarnings("serial")
public class PanelNewVisit extends JPanel implements viewinterface {
	
	private int index;
	
	private JTextArea textArea_CC;
	private JTextArea textArea_Assessment;
	private JTextArea textArea_Plan;

	private JPanel panel_Buttons;
	private JButton btnSave;
	private JButton btnCancel;
	
	private JTabbedPane tabbedPane;
	
	private PanelDistanceVision panel_DV;
	private PanelGlassesRx panel_GlassesRx;
	private PanelRefraction panel_Refraction;
	
	private PanelSLE panel_SLE;

	private PanelIOP panel_IOP;
	private PanelGonio panel_Gonio;
	private PanelFundus panel_Fundus;
	
	
	
//	private , image_Gonio, image_Fundus; TODO
	
	/**
	 * Constructor for an existing model
	 * @wbp.parser.constructor
	 */
	public PanelNewVisit(int index) {
		
		this.index = index;
		
		setBackground(CL.turq);
		
		createView();
		
		setVisitFields();
		panel_DV.setFields();
		panel_GlassesRx.setFields();
		panel_Refraction.setFields();
		
		panel_SLE.setFields();
		
		panel_Buttons.setVisible(false);
		
		disableFields(this); // TODO Don't make it look ugly and grey
			
	}
	
	/**
	 * For a completely new Visit
	 */
	public PanelNewVisit(int index, boolean isNew) {
		this.index = index;
		
		createView();
	}
	
	public void setVisitFields() {
		//TODO
		/*textArea_CC.setText(myVisit.getChiefComplaint());
		textArea_Assessment.setText(myVisit.getAssessment());
		textArea_Plan.setText(myVisit.getPlan());*/
	}

	/*
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
	
	/*
	
	public void populateRefractionPanel(ArrayList<Object> refractCols) {
		
		ArrayList<Component> clist = new ArrayList<Component>();
		setFields(panel_Refraction, clist);
		
		int i = 0;
		for (Iterator<Component> iter = clist.iterator(); iter.hasNext(); ) {
			Component c = iter.next();
//			System.out.println("\t i = " +i+": c ="+c.getClass()+" and refractCols = "+refractCols.get(i).toString());
			if (i == 0) {
				//TODO panel_Refraction.setFields(refractCols);
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
	*/
	
	private class SaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//TODO: error checks handled by the panels themselves?
						
			Visit visit = null;
			
			// Parse ALL the things!
			// but get the vid first.
			
			visit = new Visit((long)0,
					PanelNewVisit.this.getMasterModel().getCurrPatient().getId(),
					textArea_CC.getText(),
					textArea_Assessment.getText(),
					textArea_Plan.getText(),
					new Date().toString());
			
			long vid = 0;
			
			try {
				
				visit.setId(PanelNewVisit.this.getMasterModel().getvL().insert(visit));
				
				//TODO DistanceVision dv = panel_DV.createNewDistanceVision();
				//TODO dv.setVid(vid);
				//TODO
				/*GlassesRx glsRx = panel_GlassesRx.createNewGlassesRx();
				glsRx.setVid(vid);
				
				Refraction r = panel_Refraction.createNewRefraction();
				r.setVid(vid);
				
				Pupils p = panel_Pupils.createNewPupils();
				p.setVid(vid);
				
				AnteriorChamber ac = panel_AC.createNewAC();
				ac.setVid(vid);
				
				Lens l = panel_Lens.createNewLens();
				l.setVid(vid);
				
				IOPMeasurement iop = panel_IOP.createNewIOP();
				iop.setVid(vid);
				
				Gonio g = panel_Gonio.createNewGonio();
				g.setVid(vid);
				
				FundusExam f = panel_Fundus.createNewFundusExam();
				f.setVid(vid);
				
				
				if (label_SLE_Sketch.getIcon() != null){
					masterModel.getStg().insertSketchToTable(new File("firstSketch.png"), vid, "sketches_sle");
				}
				if (panel_Gonio.getSketchLabel().getIcon() != null){
					masterModel.getStg().insertSketchToTable(new File("GonioTempSketch.png"), vid, "sketches_gonio");
				}
				if (panel_Fundus.getSketchLabel().getIcon() != null){
					masterModel.getStg().insertSketchToTable(new File("FundusTempSketch.png"), vid, "sketches_fundus");
				}
				
				long dv_id = masterModel.getDvtg().insertDistanceVision(dv);
				long glsRx_id = masterModel.getGlsRxTG().insertGlassesRx(glsRx);
				long r_id = masterModel.getRefractionTG().insertRefraction(r);
				long p_id = masterModel.getPupilsTG().insertPupils(p);
				long ac_id = masterModel.getaCTG().insertAnteriorChamber(ac);
				long lens_id = masterModel.getLensTG().insertLens(l);
				long iop_id = masterModel.getIopTG().insertIOPMeasurement(iop);
				long g_id = masterModel.getGonioTG().insertGonio(g);
				long fun_id = masterModel.getFundusTG().insertFundusExam(f);
				
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
				
				masterModel.getVl().loadFromGateway();
				showVisitTabView();*/
				
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
		//TODO tabbedPane.setComponentAt(index, new VisitsTabView(patient, tabbedPane, masterModel));
	}	
	
	public void createView() {
		
		setBackground(CL.turq);
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		/*
		 * 
		// was for testing, wraps everything in a JScrollPane
		//JScrollPane scrollPane = new JScrollPane(); // for test wrapper
		//scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // for test wrapper
		//add(scrollPane, "cell 0 0,grow"); // for test wrapper
		 * 
		 */
		
		JPanel panel_Everything = new JPanel();
		panel_Everything.setBackground(CL.turq);
		//scrollPane.setViewportView(panel_Everything); // for test wrapper
		panel_Everything.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][]"));
		add(panel_Everything, "cell 0 0,grow");
		
		
		
		// CC
		JPanel panel_CC = new JPanel();
		panel_CC.setBackground(CL.turq);
		panel_Everything.add(panel_CC, "cell 0 0,grow");
		panel_CC.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Chief Complaint", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel_CC.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_CC = new JScrollPane();
		panel_CC.add(scrollPane_CC);
		
		textArea_CC = new JTextArea();
		textArea_CC.setWrapStyleWord(true);
		textArea_CC.setLineWrap(true);
		scrollPane_CC.setViewportView(textArea_CC);
		
		
		// PED === TODO Similar to below, needs to be restructured, there was confusion early in terms
		JPanel panel_PED = new JPanel();
		panel_PED.setBackground(CL.turq);
		panel_Everything.add(panel_PED, "cell 0 1,grow");
		panel_PED.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Physical Exam Detail", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel_PED.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		
		// VISION === TODO Unfortunately, holds everything...need to be reworked later
		JPanel panel_Vision = new JPanel();
		panel_Vision.setBackground(CL.turq);
		panel_PED.add(panel_Vision, "cell 0 0,grow");
		panel_Vision.setLayout(new MigLayout("", "[grow]", "[][][][grow][][grow][grow]"));
		
		
		
		// DV
		panel_DV = new PanelDistanceVision(index);
		panel_Vision.add(panel_DV, "cell 0 0,growx");
		
		// GLASSES RX
		panel_GlassesRx = new PanelGlassesRx(index);
		panel_GlassesRx.setBackground(CL.turq);
		panel_Vision.add(panel_GlassesRx, "cell 0 1,growx");
		
		// Refraction
		panel_Refraction = new PanelRefraction(index);
		panel_Refraction.setBackground(CL.turq);
		panel_Vision.add(panel_Refraction, "cell 0 2,growx");
		
		
		
		// SLE ==
		// TODO Make SLE panel contain all the others...maybe need separate objects for SLE?
		panel_SLE = new PanelSLE(index);
		panel_Vision.add(panel_SLE, "cell 0 3,grow");


		
		//IOP
		panel_IOP = new PanelIOP(index);
		panel_IOP.setBackground(CL.turq);
		panel_Vision.add(panel_IOP, "cell 0 4,grow");
		
		
		
		//GONIO
		panel_Gonio = new PanelGonio(index);
		panel_Gonio.setBackground(CL.turq);
		panel_Vision.add(panel_Gonio, "cell 0 5,grow");
		
		
		
		//FUNDUS
		panel_Fundus = new PanelFundus(index);
		panel_Fundus.setBackground(CL.turq);
		panel_Vision.add(panel_Fundus, "cell 0 6,grow");

		
		
		// Assessment and Plan
		
		JPanel panel_Assessment = new JPanel();
		panel_Assessment.setBackground(CL.turq);
		panel_Assessment.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Assesment", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel_Everything.add(panel_Assessment, "cell 0 2,grow");
		panel_Assessment.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_Assessment = new JScrollPane();
		panel_Assessment.add(scrollPane_Assessment);
		
		textArea_Assessment = new JTextArea();
		textArea_Assessment.setWrapStyleWord(true);
		textArea_Assessment.setLineWrap(true);
		scrollPane_Assessment.setViewportView(textArea_Assessment);
		
		
		JPanel panel_Plan = new JPanel();
		panel_Plan.setBackground(CL.turq);
		panel_Plan.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Plan", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel_Everything.add(panel_Plan, "cell 0 3,grow");
		panel_Plan.setLayout(new BorderLayout(0, 0));
				
		JScrollPane scrollPane_Plan = new JScrollPane();
		panel_Plan.add(scrollPane_Plan);
		
		textArea_Plan = new JTextArea();
		textArea_Plan.setWrapStyleWord(true);
		textArea_Plan.setLineWrap(true);
		scrollPane_Plan.setViewportView(textArea_Plan);
		
		
		
		// Buttons at bottom
		panel_Buttons = new JPanel();
		panel_Everything.add(panel_Buttons, "cell 0 4,alignx right");
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveListener());
		panel_Buttons.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new CancelListener());
		panel_Buttons.add(btnCancel);
	}

	@Override
	public void HideallView() {
		
	}

	@Override
	public MasterModel getMasterModel() {
		return ((HomeView)this.getParent()).getMasterModel();

	}

	@Override
	public void ShowView() {
		
	}

	@Override
	public void reload() {
		
	}

	@Override
	public HomeView getHomeView() {
		return ((HomeView)this.getParent()).getHomeView();
	}
}
