package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import database.GatewayException;
import models.AnteriorChamber;
import models.CL;
import models.DistanceVision;
import models.FundusExam;
import models.GlassesRx;
import models.Gonio;
import models.Lens;
import models.MasterModel;
import models.Pupils;
import models.Refraction;
import models.Sketches;
import models.Visit;
import net.miginfocom.swing.MigLayout;
import visitPanels.PanelFundus;
import visitPanels.PanelGonio;
import visitPanels.PanelIOP;
import visitPanels.PanelSLE;
import visitPanels.PanelVision;

@SuppressWarnings("serial")
public class VisitDetailView extends JPanel implements viewinterface {
		
	private int index; // index of the patient's visit in their visit list
	
	private PanelVision panel_Vision;
	private PanelSLE panel_SLE;
	private PanelGonio panel_Gonio;
	private PanelFundus panel_Fundus;
	private PanelIOP panel_IOP;
//	private Sketches sketches;
	
	private JTextArea textArea_CC;
	private JTextArea textArea_Assessment;
	private JTextArea textArea_Plan;
	
	private JPanel panel_Buttons;
	private JButton btnSave;
	private JButton btnCancel;
	private JButton btnUpdate;

	
	
	/*
	 * Creates a detail view for the JXTask
	 * Can be edited or deleted.
	 */
	public VisitDetailView(int index) {
		
		this.index = index;
		
		createView();
//		panel_Buttons.setVisible(false);
	}
	
	private class SaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			MasterModel mm = VisitDetailView.this.getVisitTabMasterView().getMasterModel();
			
			try {
				
				mm.getvL().insert(new Visit(
						-1,
						mm.getCurrPatient().getId(),
						textArea_CC.getText(),
						textArea_Assessment.getText(),
						textArea_Plan.getText(),
						null
						));
				
				Visit v = mm.getvL().getMyList().get(0); // the one we just inserted
				
				DistanceVision dv = panel_Vision.getPDV().newDV();
				dv.setVid(v.getId());
				dv.setId(dv.insertDV(dv));
				v.setMyDV(dv);
				
				GlassesRx g = panel_Vision.getPGRx().newGRx();
				g.setVid(v.getId());
				g.setId(g.insertGRx(g));
				v.setMyGlsRx(g);
				
				Refraction r = panel_Vision.getPanelRefrac().newRefrac();
				r.setVid(v.getId());
				r.setId(r.insertRefrac(r));
				v.setMyRefraction(r);
				
				Pupils p = panel_SLE.getPanelPupils().createNewPupils();
				p.setVid(v.getId());
				p.setId(p.insertPupils(p));
				v.setMyPupils(p);
				
				AnteriorChamber ac = panel_SLE.getPanelAC().createNewAC();
				ac.setVid(v.getId());
				ac.setId(ac.insertAC(ac));
				
				Lens l = panel_SLE.getPanelLens().createNewLens();
				l.setVid(v.getId());
				l.setId(l.insertLens(l));
				v.setMyLens(l);
				
				Gonio go = panel_Gonio.createNewGonio();
				go.setVid(v.getId());
				go.setId(go.insertGonio(go));
				v.setMyGonio(go);
				
				FundusExam fe = panel_Fundus.createNewFundusExam();
				fe.setVid(v.getId());
				fe.setId(fe.insertFundus(fe));
				v.setMyFE(fe);
				
				//TODO IOP
				
				Sketches s = new Sketches();
				
				Image i = null;
				
				i = panel_SLE.getSketch();
				if (i != null) {
					s.setImageSLE(panel_SLE.getSketch());
					s.insertSLESketch(v.getId());
				}
				
				i = panel_Fundus.getSketch();
				if (i != null) {
					s.setImageFundus(panel_Fundus.getSketch());
					s.insertFundusSketch(v.getId());
				}
				
				i = panel_Gonio.getSketch();
				if (i != null) {
					s.setImageGonio(panel_Gonio.getSketch());
					s.insertGonioSketch(v.getId());
				}
				
//				s.insertSLESketch(v.getId());
//				sketches.insertFundusSketch(new File("FundusTempSketch.png"), v.getId());
//				sketches.insertGonioSketch(new File("GonioTempSketch.png"), v.getId());
				
				v.setSketches(s);
				
								
			} catch (GatewayException e) {
				System.err.println("From VisitDetailView: Could not insert new Visit into DB.");
				e.printStackTrace();
			}

			VisitTabMasterView parent = getVisitTabMasterView();
			parent.showList_VisitView();
		}
				
	}
	
	private class EditListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			MasterModel mm = VisitDetailView.this.getVisitTabMasterViewForEditView().getMasterModel();
			
			try {
				
				// get index
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				String dateUpdated = dateFormat.format(date);
				
				Visit vOld = mm.getvL().getMyList().get(index);
				
				Visit vUpdate = new Visit(
						vOld.getId(),
						mm.getCurrPatient().getId(),
						textArea_CC.getText(),
						textArea_Assessment.getText(),
						textArea_Plan.getText(),
						dateUpdated
						);
				
				
				mm.getvL().update(vUpdate, index);
				
				DistanceVision dv = panel_Vision.getPDV().newDV();
				dv.setVid(vOld.getId());
				dv.setId(vOld.getMyDV().getId());
				dv.updateDV(dv);
				vUpdate.setMyDV(dv);
				
				GlassesRx g = panel_Vision.getPGRx().newGRx();
				g.setVid(vOld.getId());
				g.setId(vOld.getMyGlsRx().getId());
				g.updateGlxRx(g);
				vUpdate.setMyGlsRx(g);
				
				Refraction r = panel_Vision.getPanelRefrac().newRefrac();
				r.setVid(vOld.getId());
				r.setId(vOld.getMyRefraction().getId());
				r.updateRefraction(r);
				vUpdate.setMyRefraction(r);
				
				Pupils p = panel_SLE.getPanelPupils().createNewPupils();
				p.setVid(vOld.getId());
				p.setId(vOld.getMyPupils().getId());
				p.updatePupils(p);
				vUpdate.setMyPupils(p);
				
				AnteriorChamber ac = panel_SLE.getPanelAC().createNewAC();
				ac.setVid(vOld.getId());
				ac.setId(vOld.getMyAC().getId());
				ac.updateAC(ac);
				vUpdate.setMyAC(ac);
				
				Lens l = panel_SLE.getPanelLens().createNewLens();
				l.setVid(vOld.getId());
				l.setId(vOld.getMyLens().getId());
				l.updateLens(l);
				vUpdate.setMyLens(l);
				
				Gonio go = panel_Gonio.createNewGonio();
				go.setVid(vOld.getId());
				go.setId(vOld.getMyGonio().getId());
				go.updateGonio(go);
				vUpdate.setMyGonio(go);
				
				FundusExam fe = panel_Fundus.createNewFundusExam();
				fe.setVid(vOld.getId());
				fe.setId(vOld.getMyFE().getId());
				fe.updateFundusExam(fe);
				vUpdate.setMyFE(fe);
				
				//TODO IOP
				
				Sketches s = new Sketches();
				
				Image i = null;
				
				i = panel_SLE.getSketch();
				if (i != null) {
					s.setImageSLE(panel_SLE.getSketch());
					s.updateSLESketch(vOld.getId());
				}
				
				i = panel_Fundus.getSketch();
				if (i != null) {
					s.setImageFundus(panel_Fundus.getSketch());
					s.updateFundusSketch(vOld.getId());
				}
				
				i = panel_Gonio.getSketch();
				if (i != null) {
					s.setImageGonio(panel_Gonio.getSketch());
					s.updateGonioSketch(vOld.getId());
				}
				
				vUpdate.setSketches(s);
				
								
			} catch (GatewayException e1) {
				System.err.println("From VisitDetailView: Could not update the Visit into DB.");
				e1.printStackTrace();
			}

			VisitTabMasterView parent = getVisitTabMasterViewForEditView();
			parent.showList_VisitView();
			
		}
		
	}
	
	private class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			VisitTabMasterView parent = getVisitTabMasterViewForEditView();
			parent.showList_VisitView();
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
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new EditListener());
		panel_Buttons.add(btnUpdate);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveListener());
		panel_Buttons.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new CancelListener());
		panel_Buttons.add(btnCancel);
		
	}
	
	public VisitTabMasterView getVisitTabMasterView() {
		return (VisitTabMasterView)VisitDetailView.this.getParent().getParent().getParent();
	}

	public VisitTabMasterView getVisitTabMasterViewForEditView() {
		return (VisitTabMasterView)VisitDetailView.this.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
	}
	
	@Override
	public void HideallView() {
		//TODO		
	}

	@Override
	public void ShowView() {
		//TODO
	}

	@Override
	public void reload() {
		
		Visit v = getMasterModel().getvL().getMyList().get(index); // the one we just inserted
		
		textArea_CC.setText(v.getChiefComplaint());
		textArea_Assessment.setText(v.getAssessment());
		textArea_Plan.setText(v.getPlan());

		panel_Vision.reload();
		panel_SLE.reload();
		panel_Gonio.reload();
		panel_Fundus.reload();
		panel_IOP.reload();
	}
	
	@Override
	public MasterModel getMasterModel() {
		return this.getHomeView().getMasterModel();
	}
	
	// Have to go up a bunch through the JX wrappers
	@Override
	public HomeView getHomeView() {
		return ((JXTaskPaneVisitDetailView)this.getParent().getParent().getParent().getParent()).getHomeView();
	}
	
	public void showEditView() {
		panel_Buttons.remove(btnSave);
		panel_Buttons.remove(btnCancel);
		panel_Buttons.add(btnUpdate);
		panel_Buttons.add(btnCancel);

	}
	
	// panels with sketches call showView() to hide the newSketch panel
	public void showNewView() {
		
		panel_SLE.ShowView();
		panel_Fundus.ShowView();
		panel_Gonio.ShowView();
		
		panel_Buttons.remove(btnUpdate);
		panel_Buttons.remove(btnCancel);
		panel_Buttons.add(btnSave);
		panel_Buttons.add(btnCancel);
	}
}
