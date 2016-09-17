package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import database.GatewayException;
import database.SurgeryTableGateway;
import database.SurgeryTemplatesTableGateway;
import models.CL;
import models.MasterModel;
import models.Patient;
import models.Surgery;

public class LabsAndProceduresTabView  extends JPanel implements viewinterface {
	
	// Containers --------------------------------------
	private JSplitPane splitPane;
	private JPanel labsPanel;
	private JPanel proceduresParentPanel;
	private JPanel proceduresContentPanel;
	private JPanel proceduresHeaderPanel;
	private JPanel proceduresButtonPanel;
	private JXTaskPaneContainer masterContainer;
	private JScrollPane procedureScroller;
	
	
	
	// Buttons -----------------------------------------
	JButton newProcedureButton;
	
	
	// Labels ------------------------------------------
	JLabel headerLabel;
	
	// Other -------------------------------------------
	SurgeryTableGateway gate1;
	SurgeryTemplatesTableGateway gate2;
	PatientRecordView prv;
	Patient patient;
	
	public SurgeryTableGateway getGate1() {
		return gate1;
	}

	public SurgeryTemplatesTableGateway getGate2() {
		return gate2;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	//public LabsAndProceduresTabView(Patient param_patient, PatientRecordView param_prv, SurgeryTableGateway param_gate1, SurgeryTemplatesTableGateway param_gate2) {
	public LabsAndProceduresTabView(){
		this.patient = param_patient;
		this.prv = param_prv;
		this.gate1 = param_gate1;
		this.gate2 = param_gate2;
		
		setLayout(new BorderLayout());
		
		// setup header panel
		headerLabel = new JLabel("Procedure History");
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		proceduresHeaderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		proceduresHeaderPanel.setBackground(CL.belize);
		proceduresHeaderPanel.add(headerLabel);
		
		// setup main panel
		masterContainer = new JXTaskPaneContainer();
		
		populateMasterContainer();
		
		procedureScroller = new JScrollPane(masterContainer);
		
		// setup button to create new procedures
		newProcedureButton = new JButton("New Procedure");
		newProcedureButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewProcedureView view = new NewProcedureView(gate2, LabsAndProceduresTabView.this);
				proceduresParentPanel.removeAll();
				proceduresParentPanel.add(view, BorderLayout.CENTER);
				validate();
				repaint();
			}
			
		});
		
		// setup button panel
		proceduresButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		proceduresButtonPanel.add(newProcedureButton);
		
		// setup procedure panel which holds all previous components
		proceduresContentPanel = new JPanel(new BorderLayout());
		proceduresContentPanel.add(proceduresHeaderPanel, BorderLayout.NORTH);
		proceduresContentPanel.add(procedureScroller, BorderLayout.CENTER);
		proceduresContentPanel.add(proceduresButtonPanel, BorderLayout.SOUTH);
		
		proceduresParentPanel = new JPanel(new BorderLayout());
		proceduresParentPanel.add(proceduresContentPanel, BorderLayout.CENTER);
		
		add(proceduresParentPanel, BorderLayout.CENTER);
		
	}

	private void populateMasterContainer() {
		masterContainer.removeAll();

		ArrayList<Surgery> surgeries = new ArrayList<Surgery>();
		try {
			surgeries = (ArrayList<Surgery>) gate1.fetchSurgeriesForPatient(patient);
		} catch (GatewayException e) {
			e.printStackTrace();
		}
		
		
		if (surgeries.size() == 0) {
			JLabel label = new JLabel("No procedures done");
			label.setFont(new Font("Tahoma", Font.ITALIC, 16));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			masterContainer.add(label);
			return;
		}
		
		for (Surgery tmp : surgeries) {
			JXTaskPane pane = new JXTaskPane();
			ClosedProcedureView view = new ClosedProcedureView(tmp);
			pane.setTitle(tmp.getTitle());
			pane.setAnimated(false);
			pane.setCollapsed(true);
			pane.setScrollOnExpand(false);
			pane.add(view);
			masterContainer.add(pane);
			validate();
			repaint();
		}
	}

	public void reset() {
		proceduresParentPanel.removeAll();
		proceduresParentPanel.add(proceduresContentPanel, BorderLayout.CENTER);
		validate();
		repaint();
		
	}

	public void resetAndUpdate() {
		proceduresParentPanel.removeAll();
		populateMasterContainer();
		proceduresParentPanel.add(proceduresContentPanel, BorderLayout.CENTER);
		validate();
		repaint();
	}

	@Override
	public void showview() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void HideallView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MasterModel getMasterModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ShowView() {
		// TODO Auto-generated method stub
		
	}
	
}
