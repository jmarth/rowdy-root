package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import database.GatewayException;
import database.SurgeryTemplatesTableGateway;
import models.CL;
import models.MasterModel;
import models.Patient;
import models.Surgery;
import models.SurgeryTemplate;
import models.SurgeryTemplatesList;
import models.Tabs;

public class NewProcedureView extends JPanel implements viewinterface {
	
	// Containers --------------------------------------
	JPanel comboboxPanel;
	JPanel buttonPanel;
	JPanel mainPanel;
	//LabsAndProceduresTabView parent;
	
	
	// Buttons -----------------------------------------
	private JButton saveButton;
	private JButton cancelButton;
	private JComboBox comboBox;
	
	
	// Labels ------------------------------------------
	private JLabel titleLabel;
	
	
	// Fields ------------------------------------------
	private JTextArea textArea;
	private JTextPane textPane;

	
	// Other -------------------------------------------
	//private SurgeryTemplatesList list; 
	//private SurgeryTemplatesTableGateway gateway; // gateway goes in list or model TODO
	
	private JScrollPane scroller;
	
	//public NewProcedureView(SurgeryTemplatesTableGateway param_gateway, LabsAndProceduresTabView param_parent) {
	public NewProcedureView() {
		setLayout(new BorderLayout());
		
		mainPanel = new JPanel(new BorderLayout());
		
		titleLabel = new JLabel("Surgical Templates:");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		comboBox = new JComboBox();
		comboBox.setSelectedItem(null);
		comboBox.setRenderer(new SurgeryTemplateCellRenderer());
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox tmp = (JComboBox) e.getSource();
				SurgeryTemplate template = (SurgeryTemplate)tmp.getSelectedItem();
				textArea.setText(template.getDescription());
				textPane.setText(template.getDescription());
			}	
		});
		//setupComboBox()zxc;
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
				
				SurgeryTemplate tmp = (SurgeryTemplate)comboBox.getSelectedItem();
				
				/** tmp will be null when nothing has been selected from the combo box **/
				if (tmp == null) {
					return;
				}
				
				/** get the title of the template **/
				String title = tmp.getTitle();
				
				/** get the body of the template (description) **/
				String body = textPane.getText();
				//TODO
				MasterModel m = NewProcedureView.this.getMasterModel();		
				Surgery s = new Surgery(0,m.getCurrPatient().getId(), title, body);
				LabsAndProceduresTabView parent =(LabsAndProceduresTabView)(NewProcedureView.this.getParent().getParent());
				SafeSurgery ssview = new SafeSurgery(s);
				parent.getProceduresParentPanel().removeAll();
				parent.getProceduresParentPanel().add(ssview, BorderLayout.CENTER);
				parent.validate();
				parent.repaint();
				/*try {
					m.getsL().insert(s);
					((LabsAndProceduresTabView)(NewProcedureView.this.getParent().getParent())).reset();
				} catch (GatewayException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	*/			
				//int index = parent.prv.indexOfTab(Tabs.labs);
				//parent.prv.setComponentAt(index, null);
				//parent.prv.setComponentAt(index, new SafeSurgery(s, parent, NewProcedureView.this));
								
			}
			
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				((LabsAndProceduresTabView)(NewProcedureView.this.getParent().getParent())).reset();
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
		comboBox.setSelectedItem(null);
		comboBox.setRenderer(new SurgeryTemplateCellRenderer());
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox tmp = (JComboBox) e.getSource();
				SurgeryTemplate template = (SurgeryTemplate)tmp.getSelectedItem();
				textArea.setText(template.getDescription());
				textPane.setText(template.getDescription());
			}	
		});
	}

	@Override
	public void HideallView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MasterModel getMasterModel() {
		return this.getHomeView().getMasterModel();
	}

	@Override
	public void ShowView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reload() {
		comboBox.setModel(new DefaultComboBoxModel(getMasterModel().getStll().getMyList().toArray()));
	}

	@Override
	public HomeView getHomeView() {
		return ((LabsAndProceduresTabView)(this.getParent().getParent())).getHomeView();
	}
}
