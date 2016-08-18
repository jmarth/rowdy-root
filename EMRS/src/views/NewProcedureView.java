package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import database.GatewayException;
import database.SurgeryTemplatesTableGateway;
import models.CL;
import models.Surgery;
import models.SurgeryTemplate;
import models.SurgeryTemplatesList;
import models.Tabs;

public class NewProcedureView extends JPanel {
	
	// Containers --------------------------------------
	JPanel comboboxPanel;
	JPanel buttonPanel;
	JPanel mainPanel;
	LabsAndProceduresTabView parent;
	
	
	// Buttons -----------------------------------------
	private JButton saveButton;
	private JButton cancelButton;
	private JComboBox<String> comboBox;
	
	
	// Labels ------------------------------------------
	private JLabel titleLabel;
	
	
	// Fields ------------------------------------------
	private JTextArea textArea;
	private JTextPane textPane;

	
	// Other -------------------------------------------
	private SurgeryTemplatesList list; 
	private SurgeryTemplatesTableGateway gateway;
	
	private JScrollPane scroller;
	

	

	
	
	public NewProcedureView(SurgeryTemplatesTableGateway param_gateway, LabsAndProceduresTabView param_parent) {
		this.gateway = param_gateway;
		this.parent = param_parent;
		
		
		setLayout(new BorderLayout());
		
		mainPanel = new JPanel(new BorderLayout());
		
		titleLabel = new JLabel("Surgical Templates:");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

			
		setupComboBox();
		comboboxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		comboboxPanel.add(titleLabel);
		comboboxPanel.add(comboBox);
		
		
		
		textArea = new JTextArea();
		textArea.setText("");
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setColumns(60);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textArea.setBorder(BorderFactory.createLineBorder(CL.blueGrey, 3));
		
		textPane = new JTextPane();
		
		scroller = new JScrollPane();
		scroller.setViewportView(textPane);
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Object tmp = comboBox.getSelectedItem();
				
				/** tmp will be null when nothing has been selected from the combo box **/
				if (tmp == null) {
					return;
				}
				
				/** get the title of the template **/
				String title = ((String) tmp).toString();
				
				/** get the body of the template (description) **/
				String body = textPane.getText();
				long pid = parent.getPatient().getId();
								
				Surgery s = new Surgery(0, pid, title, body);
								
				int index = parent.prv.indexOfTab(Tabs.labs);
				parent.prv.setComponentAt(index, null);
				parent.prv.setComponentAt(index, new SafeSurgery(s, parent, NewProcedureView.this));
								
			}
			
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.reset();
				int index = parent.prv.indexOfTab(Tabs.labs);
				parent.prv.setComponentAt(index, null);
				parent.prv.setComponentAt(index, parent);
			}
			
		});
		
		
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		mainPanel.add(comboboxPanel, BorderLayout.NORTH);
		mainPanel.add(scroller, BorderLayout.CENTER);
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
		
		
		comboBox = new JComboBox<String>(array);
		comboBox.setSelectedItem(null);
		
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> tmp = (JComboBox<String>) e.getSource();
				String title = (String) tmp.getSelectedItem();
				SurgeryTemplate template = list.findTemplate(title);
				//textArea.setText(template.getDescription());
				textPane.setText(template.getDescription());
			}
			
		});
	}
}
