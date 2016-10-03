package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import database.GatewayException;
import models.CL;
import models.MasterModel;
import models.Visit;
import net.miginfocom.swing.MigLayout;
import visitPanels.PanelFundus;
import visitPanels.PanelGonio;
import visitPanels.PanelIOP;
import visitPanels.PanelSLE;
import visitPanels.PanelVision;

@SuppressWarnings("serial")
public class PanelNewVisit2 extends JPanel implements viewinterface {
	
	private int index;
	
	private JTextArea textArea_CC;
	private JTextArea textArea_Assessment;
	private JTextArea textArea_Plan;

	private JPanel panel_Buttons;
	private JButton btnSave;
	private JButton btnCancel;
	
	private PanelVision panel_Vision;
	private PanelSLE panel_SLE;
	private PanelIOP panel_IOP;
	private PanelGonio panel_Gonio;
	private PanelFundus panel_Fundus;
	
	
	
//	private , image_Gonio, image_Fundus; TODO
	
	/*
	 * For creating a brand new Visit.
	 * Not for editing
	 */
	/**
	 * Constructor for an existing model
	 * @wbp.parser.constructor
	 */
	public PanelNewVisit2(int index) {
		
		this.index = index;
		
		setBackground(CL.turq);
		
		createView();
		
//		setVisitFields();
		
//		panel_Vision.setFields();
//		panel_SLE.setFields();
//		panel_IOP.setFields();
//		panel_Gonio.setFields();
//		panel_Fundus.setFields();
		
		panel_Buttons.setVisible(false);
		
//		disableFields(this); // TODO Don't make it look ugly and grey
			
	}
	
	/**
	 * For a completely new Visit
	 */
	public PanelNewVisit2(int index, boolean isNew) {
		this.index = index;
		
		createView();
	}
	
	public void setVisitFields() {
		//TODO
		/*textArea_CC.setText(myVisit.getChiefComplaint());
		textArea_Assessment.setText(myVisit.getAssessment());
		textArea_Plan.setText(myVisit.getPlan());*/
	}
	
	private class SaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//TODO: error checks handled by the panels themselves?
						
			Visit visit = null;
			
			// Parse ALL the things!
			// but get the vid first.
			
			visit = new Visit((long)0,
					PanelNewVisit2.this.getMasterModel().getCurrPatient().getId(),
					textArea_CC.getText(),
					textArea_Assessment.getText(),
					textArea_Plan.getText(),
					new Date().toString());
			
			long vid = 0;
			
			try {
				
				visit.setId(PanelNewVisit2.this.getMasterModel().getvL().insert(visit));
				
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
			//TODO
		}
	}

	public void createView() {
		
		setBackground(CL.turq);
		setLayout(new MigLayout("", "[grow]", "[grow][][grow][grow][grow][grow][grow][grow][grow]"));
		
		
		
		// CC
		JPanel panel_CC = new JPanel();
		panel_CC.setBackground(CL.turq);
		add(panel_CC, "cell 0 0,grow");
		panel_CC.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Chief Complaint", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel_CC.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_CC = new JScrollPane();
		panel_CC.add(scrollPane_CC);
		
		textArea_CC = new JTextArea();
		textArea_CC.setWrapStyleWord(true);
		textArea_CC.setLineWrap(true);
		scrollPane_CC.setViewportView(textArea_CC);
		
		
		
		// VISION PANEL
		panel_Vision = new PanelVision(index);
		panel_Vision.setBackground(CL.turq);
		add(panel_Vision, "cell 0 1,grow");

		// SLE PANEL
		panel_SLE = new PanelSLE(index);
		panel_SLE.setBackground(CL.turq);
		add(panel_SLE, "cell 0 2,grow");

		// IOP PANEL
		panel_IOP = new PanelIOP(index);
		panel_IOP.setBackground(CL.turq);
		add(panel_IOP, "cell 0 3,grow");
		
		// GONIO PANEL
		panel_Gonio = new PanelGonio(index);
		panel_Gonio.setBackground(CL.turq);
		add(panel_Gonio, "cell 0 4,grow");
		
		// FUNDUS PANEL
		panel_Fundus = new PanelFundus(index);
		panel_Fundus.setBackground(CL.turq);
		add(panel_Fundus, "cell 0 5,grow");

		
		
		// Assessment and Plan
		
		JPanel panel_Assessment = new JPanel();
		panel_Assessment.setBackground(CL.turq);
		panel_Assessment.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Assesment", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		add(panel_Assessment, "cell 0 6,grow");
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
		add(panel_Plan, "cell 0 7,grow");
		panel_Plan.setLayout(new BorderLayout(0, 0));
				
		JScrollPane scrollPane_Plan = new JScrollPane();
		panel_Plan.add(scrollPane_Plan);
		
		textArea_Plan = new JTextArea();
		textArea_Plan.setWrapStyleWord(true);
		textArea_Plan.setLineWrap(true);
		scrollPane_Plan.setViewportView(textArea_Plan);
		
		
		
		// Buttons at bottom
		panel_Buttons = new JPanel();
		add(panel_Buttons, "cell 0 8,alignx right");
		
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
