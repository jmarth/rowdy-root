package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import models.CL;
import models.MasterModel;
import net.miginfocom.swing.MigLayout;
import visitPanels.PanelFundus;
import visitPanels.PanelGonio;
import visitPanels.PanelIOP;
import visitPanels.PanelSLE;
import visitPanels.PanelVision;

@SuppressWarnings("serial")
public class VisitNewView extends JPanel implements viewinterface {

	private int index;
	
	private PanelVision panel_Vision;
	private PanelSLE panel_SLE;
	private PanelGonio panel_Gonio;
	private PanelFundus panel_Fundus;
	private PanelIOP panel_IOP;
	
	private JTextArea textArea_CC;
	private JTextArea textArea_Assessment;
	private JTextArea textArea_Plan;
	
	private JPanel panel_Buttons;
	private JButton btnSave;
	private JButton btnCancel;
	
	public VisitNewView(int index) {
		this.index = index;
		createView();
	}
	
	private class SaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	private class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
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
		// TODO
	}

	@Override
	public MasterModel getMasterModel() {
		return getHomeView().getMasterModel();
	}

	@Override
	public void ShowView() {
		// TODO
	}

	@Override
	public void reload() {
		// TODO
	}

	// Goes through the JScrollPane, view port, etc
	@Override
	public HomeView getHomeView() {
		return ((PatientRecordView)(this.getParent()).getParent().getParent().getParent()).getHomeView();
	}

}
