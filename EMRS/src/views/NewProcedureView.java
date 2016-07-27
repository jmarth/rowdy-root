package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import org.jdesktop.swingx.JXTaskPaneContainer;

import database.GatewayException;
import database.SurgeryTemplatesTableGateway;
import models.Surgery;
import models.SurgeryTemplate;
import models.SurgeryTemplatesList;

public class NewProcedureView extends JPanel {
	
	// Containers --------------------------------------
	JPanel comboboxPanel;
	JPanel buttonPanel;
	JPanel mainPanel;
	LabsAndProceduresTabView parent;
	
	
	// Buttons -----------------------------------------
	private JButton saveButton;
	private JButton cancelButton;
	private JComboBox comboBox;
	
	
	// Labels ------------------------------------------
	private JLabel titleLabel;
	
	
	// Fields ------------------------------------------
	private JTextArea textArea;

	
	// Other -------------------------------------------
	private SurgeryTemplatesList list; 
	private SurgeryTemplatesTableGateway gateway;
	

	

	
	
	public NewProcedureView(SurgeryTemplatesTableGateway param_gateway, LabsAndProceduresTabView param_parent) {
		this.gateway = param_gateway;
		this.parent = param_parent;
		
		
		setLayout(new BorderLayout());
		
		mainPanel = new JPanel(new BorderLayout());
			
		setupComboBox();
		comboboxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		comboboxPanel.add(comboBox);
		
		textArea = new JTextArea();
		textArea.setText("");
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setColumns(60);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		saveButton = new JButton("Confirm");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Object tmp = comboBox.getSelectedItem();
				if (tmp == null) {
					return;
				}
				String title = (String) tmp.toString();
				String body = textArea.getText();
				long pid = parent.getPatient().getId();
				
				long newID = 0;
				
				Surgery s = new Surgery(0, pid, title, body);
				
				try {
					newID = parent.gate1.insertSurgery(s);
				} catch (GatewayException e1) {
					e1.printStackTrace();
				}
				
				s.setID(newID);
				
				parent.resetAndUpdate();
				
			}
			
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.reset();
				
			}
			
		});
		
		
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		mainPanel.add(comboboxPanel, BorderLayout.NORTH);
		mainPanel.add(textArea, BorderLayout.CENTER);
		add(mainPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
	}

	private void setupComboBox() {
		
		list = new SurgeryTemplatesList();
		list.setGateway(gateway);
		list.loadFromGateway();
		
		ArrayList<SurgeryTemplate> templates = (ArrayList<SurgeryTemplate>) list.getSurgeryTemplatesList();
		
		String[] array = new String[templates.size()];
		
		int i;
		for (i = 0; i < templates.size(); i++) {
			array[i] = templates.get(i).getTitle();
		}
		
		
		comboBox = new JComboBox(array);
		comboBox.setSelectedItem(null);
		
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox tmp = (JComboBox) e.getSource();
				String title = (String) tmp.getSelectedItem();
				SurgeryTemplate template = list.findTemplate(title);
				textArea.setText(template.getDescription());
			}
			
		});
	}
}
