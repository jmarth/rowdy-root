package views;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import database.AllergyTableGatewayMySQL;
import database.GatewayException;
import models.Allergy;
import models.Patient;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class NewAllergyFormView extends JPanel {
	private final ButtonGroup severityButtonGroup = new ButtonGroup();
	
	// Variable for Allergy Name textfield
	private JTextField textField;
	
	// Patient this Allergy corresponds to
	Patient patient;
	
	JPanel oldPanel;
	
	// Table Gateway
	AllergyTableGatewayMySQL atg;
	
	// Variables for JCheckBoxes
	List<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
	private JCheckBox chckbxNaseua;
	private JCheckBox chckbxAchy;
	private JCheckBox chckbxTired;
	private JCheckBox chckbxCoughing;
	private JCheckBox chckbxChills;
	private JCheckBox chckbxHeartPalpitations;
	private JCheckBox chckbxAnxiety;
	private JCheckBox chckbxWheezing;

	// Variables for JRadioButtons
	private String severity;
	private JRadioButton rdbtnSevere;
	private JRadioButton rdbtnModerate;
	private JRadioButton rdbtnMild;

	/**
	 * Create the panel.
	 */
	public NewAllergyFormView(final JTabbedPane tabbedPane, Patient patient, JPanel allergiesPanel, AllergyTableGatewayMySQL gateway) {
		this.patient = patient;
		this.atg = gateway;
		oldPanel = allergiesPanel;
		
		/**
		 * Try to connect to DB through AllergyTableGateway
		 * Set the gateway of the AllergyList
		 * Load Allergies into the AllergyList
		 */
		try {
			atg = new AllergyTableGatewayMySQL();
		} catch (GatewayException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		}
		
		// Put all GUI lines in a seperate method to keep clean :)
		createView(tabbedPane, patient, allergiesPanel, atg);

	}
	
	/**
	 * Called from ActionListener for Cancel button
	 * @param tabbedPane JTabbedPane to alter
	 * @param oldPanel Panel to switch back to
	 */
	public void cancel(JTabbedPane tabbedPane, JPanel oldPanel){
		int index = tabbedPane.indexOfTab("Allergies");
		tabbedPane.setComponentAt(index, null);
		tabbedPane.setComponentAt(index, oldPanel);
	}
	
	/**
	 * Save Allergy to database for patient
	 * @param patient Patient that Allergy belongs to
	 * @param atg AllergyTableGateway
	 * @param tabbedPane JTabbedPane to change when done saving
	 * @param oldPanel JPanel to change back to when done saving
	 */
	public void save(Patient patient, AllergyTableGatewayMySQL atg, JTabbedPane tabbedPane, JPanel oldPanel){
		StringBuilder strBuild = new StringBuilder();
		
		/**
		 * Iterate over collection of JCheckBoxes and if the check box is selected, append the label of the chckbox to the string (adverse_reaction)
		 */
		Iterator<JCheckBox> chckbxIterator = checkboxes.iterator();
		while(chckbxIterator.hasNext()){
			JCheckBox tmpBox = chckbxIterator.next();
			if(tmpBox.isSelected()){
				//System.out.println(tmpBox.getLabel());
				strBuild.append(tmpBox.getLabel());
				strBuild.append("/");
			}
		}
		
		// Need to delete last "/" in adverse_reaction string
		strBuild.deleteCharAt(strBuild.length()-1);
		
		/**
		 * Determine what severity radio button is selected
		 * Set severity string accordingly
		 */
		if(rdbtnSevere.isSelected()){
			severity = "Severe";
		} else if (rdbtnModerate.isSelected()){
			severity = "Moderate";
		} else {
			severity = "Mild";
		}
		
		/**
		 * Create new Allergy object with correct parameters
		 * Insert the allery to the DB through the Gateway
		 */
		Allergy allergy = new Allergy(0, patient.getId(), textField.getText(), severity, strBuild.toString());
		try {
			atg.insertAllergy(allergy);
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Change the panel back to allergy table
		// NEED TO FIGURE HOW TO UPDATE TABLE WHEN SWITCHING BACK TO SHOW NEW ALLERGY
		int index = tabbedPane.indexOfTab("Allergies");
		tabbedPane.setComponentAt(index, null);
		tabbedPane.setComponentAt(index, oldPanel);
	}
	
	/**
	 * Creates NewAllergyFormView GUI
	 * @param tabbedPane JTabbedPane to alter
	 * @param patient Patient that allergy regards to
	 * @param allergiesPanel Old JPanel to set back to on cancel or save
	 * @param atg Gateway for Allergy table
	 */
	public void createView(final JTabbedPane tabbedPane, final Patient patient, JPanel allergiesPanel, final AllergyTableGatewayMySQL atg){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAllergy = new JLabel("Allergy");
		lblAllergy.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblAllergy = new GridBagConstraints();
		gbc_lblAllergy.insets = new Insets(25, 25, 5, 5);
		gbc_lblAllergy.gridx = 0;
		gbc_lblAllergy.gridy = 1;
		add(lblAllergy, gbc_lblAllergy);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(25, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(20);
		
		JLabel lblSeverity = new JLabel("Severity");
		lblSeverity.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblSeverity = new GridBagConstraints();
		gbc_lblSeverity.insets = new Insets(25, 25, 5, 5);
		gbc_lblSeverity.gridx = 0;
		gbc_lblSeverity.gridy = 3;
		add(lblSeverity, gbc_lblSeverity);
		
		rdbtnSevere = new JRadioButton("Severe");
		severityButtonGroup.add(rdbtnSevere);
		GridBagConstraints gbc_rdbtnSevere = new GridBagConstraints();
		gbc_rdbtnSevere.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSevere.insets = new Insets(25, 0, 5, 5);
		gbc_rdbtnSevere.gridx = 1;
		gbc_rdbtnSevere.gridy = 3;
		add(rdbtnSevere, gbc_rdbtnSevere);
		
		rdbtnModerate = new JRadioButton("Moderate");
		severityButtonGroup.add(rdbtnModerate);
		GridBagConstraints gbc_rdbtnModerate = new GridBagConstraints();
		gbc_rdbtnModerate.anchor = GridBagConstraints.WEST;
		gbc_rdbtnModerate.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnModerate.gridx = 1;
		gbc_rdbtnModerate.gridy = 4;
		add(rdbtnModerate, gbc_rdbtnModerate);
		
		rdbtnMild = new JRadioButton("Mild");
		severityButtonGroup.add(rdbtnMild);
		GridBagConstraints gbc_rdbtnMild = new GridBagConstraints();
		gbc_rdbtnMild.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMild.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMild.gridx = 1;
		gbc_rdbtnMild.gridy = 5;
		add(rdbtnMild, gbc_rdbtnMild);
		
		JLabel lblReactions = new JLabel("Reactions");
		lblReactions.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblReactions = new GridBagConstraints();
		gbc_lblReactions.insets = new Insets(25, 25, 5, 5);
		gbc_lblReactions.gridx = 0;
		gbc_lblReactions.gridy = 7;
		add(lblReactions, gbc_lblReactions);
		
		chckbxWheezing = new JCheckBox("Wheezing");
		checkboxes.add(chckbxWheezing);
		chckbxWheezing.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxWheezing = new GridBagConstraints();
		gbc_chckbxWheezing.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_chckbxWheezing.insets = new Insets(25, 0, 5, 5);
		gbc_chckbxWheezing.gridx = 1;
		gbc_chckbxWheezing.gridy = 7;
		add(chckbxWheezing, gbc_chckbxWheezing);
		
		chckbxAnxiety = new JCheckBox("Anxiety");
		checkboxes.add(chckbxAnxiety);
		chckbxAnxiety.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxAnxiety = new GridBagConstraints();
		gbc_chckbxAnxiety.anchor = GridBagConstraints.WEST;
		gbc_chckbxAnxiety.insets = new Insets(25, 0, 5, 5);
		gbc_chckbxAnxiety.gridx = 2;
		gbc_chckbxAnxiety.gridy = 7;
		add(chckbxAnxiety, gbc_chckbxAnxiety);
		
		chckbxHeartPalpitations = new JCheckBox("Heart Palpitations");
		checkboxes.add(chckbxHeartPalpitations);
		chckbxHeartPalpitations.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxHeartPalpitations = new GridBagConstraints();
		gbc_chckbxHeartPalpitations.anchor = GridBagConstraints.WEST;
		gbc_chckbxHeartPalpitations.insets = new Insets(25, 0, 5, 5);
		gbc_chckbxHeartPalpitations.gridx = 3;
		gbc_chckbxHeartPalpitations.gridy = 7;
		add(chckbxHeartPalpitations, gbc_chckbxHeartPalpitations);
		
		chckbxChills = new JCheckBox("Chills");
		checkboxes.add(chckbxChills);
		chckbxChills.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxChills = new GridBagConstraints();
		gbc_chckbxChills.anchor = GridBagConstraints.WEST;
		gbc_chckbxChills.insets = new Insets(25, 0, 5, 0);
		gbc_chckbxChills.gridx = 4;
		gbc_chckbxChills.gridy = 7;
		add(chckbxChills, gbc_chckbxChills);
		
		chckbxCoughing = new JCheckBox("Coughing");
		checkboxes.add(chckbxCoughing);
		chckbxCoughing.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxCoughing = new GridBagConstraints();
		gbc_chckbxCoughing.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_chckbxCoughing.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCoughing.gridx = 1;
		gbc_chckbxCoughing.gridy = 8;
		add(chckbxCoughing, gbc_chckbxCoughing);
		
		chckbxTired = new JCheckBox("Tired");
		checkboxes.add(chckbxTired);
		chckbxTired.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxTired = new GridBagConstraints();
		gbc_chckbxTired.anchor = GridBagConstraints.WEST;
		gbc_chckbxTired.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTired.gridx = 2;
		gbc_chckbxTired.gridy = 8;
		add(chckbxTired, gbc_chckbxTired);
		
		chckbxAchy = new JCheckBox("Achy");
		checkboxes.add(chckbxAchy);
		chckbxAchy.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxAchy = new GridBagConstraints();
		gbc_chckbxAchy.anchor = GridBagConstraints.WEST;
		gbc_chckbxAchy.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAchy.gridx = 3;
		gbc_chckbxAchy.gridy = 8;
		add(chckbxAchy, gbc_chckbxAchy);
		
		chckbxNaseua = new JCheckBox("Naseua");
		checkboxes.add(chckbxNaseua);
		chckbxNaseua.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxNaseua = new GridBagConstraints();
		gbc_chckbxNaseua.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxNaseua.anchor = GridBagConstraints.BASELINE;
		gbc_chckbxNaseua.gridx = 4;
		gbc_chckbxNaseua.gridy = 8;
		add(chckbxNaseua, gbc_chckbxNaseua);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(patient, atg, tabbedPane, oldPanel);
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(50, 0, 0, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 11;
		add(btnSave, gbc_btnSave);
		
		/**
		 * Sets the tabbed pane at the Allergy index back to default allergy panel
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancel(tabbedPane, oldPanel);
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(50, 0, 0, 0);
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 11;
		add(btnCancel, gbc_btnCancel);
	}

}
