package views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXTaskPaneContainer;

import constants.CL;
import database.SurgeryTemplatesTableGateway;

public class LabsAndProceduresTabView extends JPanel {
	
	// Containers --------------------------------------
	private JSplitPane splitPane;
	private JPanel labsPanel;
	private JPanel proceduresParentPanel;
	private JPanel proceduresContentPanel;
	private JPanel proceduresHeaderPanel;
	private JPanel proceduresButtonPanel;
	private JXTaskPaneContainer masterContainer;
	
	
	
	// Buttons -----------------------------------------
	JButton newProcedureButton;
	
	
	// Labels ------------------------------------------
	JLabel headerLabel;
	
	// Other -------------------------------------------
	SurgeryTemplatesTableGateway gateway;
	
	public LabsAndProceduresTabView(SurgeryTemplatesTableGateway param_gateway) {
		this.gateway = param_gateway;
		
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
		
		// setup button to create new procedures
		newProcedureButton = new JButton("New Procedure");
		newProcedureButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewProcedureView view = new NewProcedureView(gateway, LabsAndProceduresTabView.this);
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
		proceduresContentPanel.add(masterContainer, BorderLayout.CENTER);
		proceduresContentPanel.add(proceduresButtonPanel, BorderLayout.SOUTH);
		
		proceduresParentPanel = new JPanel(new BorderLayout());
		proceduresParentPanel.add(proceduresContentPanel, BorderLayout.CENTER);
		
		add(proceduresParentPanel, BorderLayout.CENTER);
		
	}

	public void reset() {
		proceduresParentPanel.removeAll();
		proceduresParentPanel.add(proceduresContentPanel, BorderLayout.CENTER);
		validate();
		repaint();
		
	}
	
}
