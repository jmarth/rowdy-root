package views;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import database.AllergyTableGatewayMySQL;
import database.GatewayException;
import database.VitalsTableGateway;
import database.VitalsTableGatewayMySQL;
import models.Allergy;
import models.Patient;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.JTextArea;

public class VitalsTabNewVital extends JPanel {
	
	private final ButtonGroup bpButtonGroup = new ButtonGroup();
	private final ButtonGroup heightButtonGroup = new ButtonGroup();
	private final ButtonGroup weightButtonGroup = new ButtonGroup();
	
	private JTextField textField_BPSysUnit;
	private JTextField textField_BPDiasUnit;
	private JTextField textField_HeightUnit1;
	private JTextField textField_WeightUnit;
	private JTextField textField_HeightUnit2;
	
	JPanel oldPanel;
	
	private Patient patient;
	private VitalsTableGateway vtg;
	JTable vitalsTable;

	/**
	 * Create the panel.
	 */
	public VitalsTabNewVital(final JTabbedPane tabbedPane, Patient patient, JPanel vitalsPanel, VitalsTableGateway gateway, JTable vitalsTable) {
		this.patient = patient;
		this.vtg = gateway;
		this.vitalsTable = vitalsTable;
		oldPanel =  vitalsPanel;
		
		/**
		 * Try to connect to DB through AllergyTableGateway
		 * Set the gateway of the AllergyList
		 * Load Allergies into the AllergyList
		 */
		try {
			vtg = new VitalsTableGatewayMySQL();
		} catch (GatewayException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		}
		
		// Put all GUI lines in a separate method to keep clean :)
		createView(tabbedPane, patient, vitalsPanel, vtg, vitalsTable);
		
		
	}
	
	/**
	 * Called from ActionListener for Cancel button
	 * @param tabbedPane JTabbedPane to alter
	 * @param oldPanel Panel to switch back to
	 */
	public void cancel(JTabbedPane tabbedPane, JPanel oldPanel){
		int index = tabbedPane.indexOfTab("Vitals");
		tabbedPane.setComponentAt(index, null);
		tabbedPane.setComponentAt(index, oldPanel);
	}
	
	/**
	 * Save Allergy to database for patient
	 * @param patient Patient that Allergy belongs to
	 * @param atg AllergyTableGateway
	 * @param tabbedPane JTabbedPane to change when done saving
	 * @param oldPanel JPanel to change back to when done saving
	 
	public void save(Patient patient, AllergyTableGatewayMySQL atg, JTabbedPane tabbedPane, JPanel oldPanel, JTable allergyTable){
		StringBuilder strBuild = new StringBuilder();
		
		/**
		 * Iterate over collection of JCheckBoxes and if the check box is selected, append the label of the chckbox to the string (adverse_reaction)
		 
		Iterator<JCheckBox> chckbxIterator = checkboxes.iterator();
		while(chckbxIterator.hasNext()){
			JCheckBox tmpBox = chckbxIterator.next();
			if(tmpBox.isSelected()){
				//System.out.println(tmpBox.getLabel());
				strBuild.append(tmpBox.getLabel());
				strBuild.append("/");
			}
		}
		
		if(!otherTextField.getText().isEmpty()){
			strBuild.append(otherTextField.getText());
			strBuild.append("/");
		}
		
		// Need to delete last "/" in adverse_reaction string
		strBuild.deleteCharAt(strBuild.length()-1);
		
		/**
		 * Determine what severity radio button is selected
		 * Set severity string accordingly
		 
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
		
		// Add the allergy to the JTable
		// Get model of AllergyTable in order to add rows
		DefaultTableModel model = (DefaultTableModel) allergyTable.getModel();
		// Add row		
		model.addRow(new Object[]{
				allergy.getAllergy(), 
				allergy.getSeverity(), 
				allergy.getAdverseReaction()
		});
		
		tabbedPane.setComponentAt(index, null);
		tabbedPane.setComponentAt(index, oldPanel);
	}
	*/
	public void createView (final JTabbedPane tabbedPane, final Patient patient, JPanel vitalsPanel, final VitalsTableGateway vtg, final JTable vitalsTable) {
setLayout(new BorderLayout(0, 0));
		
		JPanel panel_VitalsForm = new JPanel();
		add(panel_VitalsForm, BorderLayout.CENTER);
		GridBagLayout gbl_panel_VitalsForm = new GridBagLayout();
		gbl_panel_VitalsForm.columnWidths = new int[]{0, 0, 48, 0, 0, 0};
		gbl_panel_VitalsForm.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_VitalsForm.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_VitalsForm.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_VitalsForm.setLayout(gbl_panel_VitalsForm);
		
		JLabel lblVitals = new JLabel("Vitals");
		lblVitals.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblVitals = new GridBagConstraints();
		gbc_lblVitals.insets = new Insets(0, 0, 5, 5);
		gbc_lblVitals.gridx = 1;
		gbc_lblVitals.gridy = 1;
		panel_VitalsForm.add(lblVitals, gbc_lblVitals);
		
		JLabel lblBloodPressuresys = new JLabel("BP(Sys):");
		GridBagConstraints gbc_lblBloodPressuresys = new GridBagConstraints();
		gbc_lblBloodPressuresys.anchor = GridBagConstraints.EAST;
		gbc_lblBloodPressuresys.insets = new Insets(0, 0, 5, 5);
		gbc_lblBloodPressuresys.gridx = 1;
		gbc_lblBloodPressuresys.gridy = 3;
		panel_VitalsForm.add(lblBloodPressuresys, gbc_lblBloodPressuresys);
		
		textField_BPSysUnit = new JTextField();
		GridBagConstraints gbc_textField_BPSysUnit = new GridBagConstraints();
		gbc_textField_BPSysUnit.insets = new Insets(0, 0, 5, 5);
		gbc_textField_BPSysUnit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_BPSysUnit.gridx = 2;
		gbc_textField_BPSysUnit.gridy = 3;
		panel_VitalsForm.add(textField_BPSysUnit, gbc_textField_BPSysUnit);
		textField_BPSysUnit.setColumns(10);
		
		JLabel lblBPSysUnit = new JLabel("unit1");
		GridBagConstraints gbc_lblBPSysUnit = new GridBagConstraints();
		gbc_lblBPSysUnit.insets = new Insets(0, 0, 5, 5);
		gbc_lblBPSysUnit.gridx = 3;
		gbc_lblBPSysUnit.gridy = 3;
		panel_VitalsForm.add(lblBPSysUnit, gbc_lblBPSysUnit);
		
		JPanel panel_BPGroup = new JPanel();
		GridBagConstraints gbc_panel_BPGroup = new GridBagConstraints();
		gbc_panel_BPGroup.insets = new Insets(0, 0, 5, 0);
		gbc_panel_BPGroup.fill = GridBagConstraints.BOTH;
		gbc_panel_BPGroup.gridx = 4;
		gbc_panel_BPGroup.gridy = 3;
		panel_VitalsForm.add(panel_BPGroup, gbc_panel_BPGroup);
		
		JRadioButton rdbtnMmhg = new JRadioButton("mm/Hg");
		bpButtonGroup.add(rdbtnMmhg);
		rdbtnMmhg.setSelected(true);
		panel_BPGroup.add(rdbtnMmhg);
		
		JRadioButton rdbtnPa = new JRadioButton("Pa");
		bpButtonGroup.add(rdbtnPa);
		panel_BPGroup.add(rdbtnPa);
		
		JLabel lblBloodPressuredias = new JLabel("BP (Dias):");
		GridBagConstraints gbc_lblBloodPressuredias = new GridBagConstraints();
		gbc_lblBloodPressuredias.anchor = GridBagConstraints.EAST;
		gbc_lblBloodPressuredias.insets = new Insets(0, 0, 5, 5);
		gbc_lblBloodPressuredias.gridx = 1;
		gbc_lblBloodPressuredias.gridy = 4;
		panel_VitalsForm.add(lblBloodPressuredias, gbc_lblBloodPressuredias);
		
		textField_BPDiasUnit = new JTextField();
		GridBagConstraints gbc_textField_BPDiasUnit = new GridBagConstraints();
		gbc_textField_BPDiasUnit.insets = new Insets(0, 0, 5, 5);
		gbc_textField_BPDiasUnit.fill = GridBagConstraints.BOTH;
		gbc_textField_BPDiasUnit.gridx = 2;
		gbc_textField_BPDiasUnit.gridy = 4;
		panel_VitalsForm.add(textField_BPDiasUnit, gbc_textField_BPDiasUnit);
		textField_BPDiasUnit.setColumns(10);
		
		JLabel lblBPDiasUnit = new JLabel("unit2");
		GridBagConstraints gbc_lblBPDiasUnit = new GridBagConstraints();
		gbc_lblBPDiasUnit.insets = new Insets(0, 0, 5, 5);
		gbc_lblBPDiasUnit.gridx = 3;
		gbc_lblBPDiasUnit.gridy = 4;
		panel_VitalsForm.add(lblBPDiasUnit, gbc_lblBPDiasUnit);
		
		JLabel lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.EAST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 6;
		panel_VitalsForm.add(lblHeight, gbc_lblHeight);
		
		textField_HeightUnit1 = new JTextField();
		GridBagConstraints gbc_textField_HeightUnit1 = new GridBagConstraints();
		gbc_textField_HeightUnit1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_HeightUnit1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_HeightUnit1.gridx = 2;
		gbc_textField_HeightUnit1.gridy = 6;
		panel_VitalsForm.add(textField_HeightUnit1, gbc_textField_HeightUnit1);
		textField_HeightUnit1.setColumns(10);
		
		JLabel lblHeightUnit1 = new JLabel("unit1");
		GridBagConstraints gbc_lblHeightUnit1 = new GridBagConstraints();
		gbc_lblHeightUnit1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHeightUnit1.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeightUnit1.gridx = 3;
		gbc_lblHeightUnit1.gridy = 6;
		panel_VitalsForm.add(lblHeightUnit1, gbc_lblHeightUnit1);
		
		JPanel panel_HeightGroup = new JPanel();
		GridBagConstraints gbc_panel_HeightGroup = new GridBagConstraints();
		gbc_panel_HeightGroup.insets = new Insets(0, 0, 5, 0);
		gbc_panel_HeightGroup.gridx = 4;
		gbc_panel_HeightGroup.gridy = 6;
		panel_VitalsForm.add(panel_HeightGroup, gbc_panel_HeightGroup);
		
		JRadioButton rdbtnFeetInches = new JRadioButton("ft/inches");
		rdbtnFeetInches.setSelected(true);
		panel_HeightGroup.add(rdbtnFeetInches);
		
		JRadioButton rdbtnInches = new JRadioButton("inches");
		panel_HeightGroup.add(rdbtnInches);
		
		JRadioButton rdbtnCm = new JRadioButton("cm");
		panel_HeightGroup.add(rdbtnCm);
		
		textField_HeightUnit2 = new JTextField();
		GridBagConstraints gbc_textField_HeightUnit2 = new GridBagConstraints();
		gbc_textField_HeightUnit2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_HeightUnit2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_HeightUnit2.gridx = 2;
		gbc_textField_HeightUnit2.gridy = 7;
		panel_VitalsForm.add(textField_HeightUnit2, gbc_textField_HeightUnit2);
		textField_HeightUnit2.setColumns(10);
		
		JLabel lblHeightUnit2 = new JLabel("unit2");
		GridBagConstraints gbc_lblHeightUnit2 = new GridBagConstraints();
		gbc_lblHeightUnit2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHeightUnit2.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeightUnit2.gridx = 3;
		gbc_lblHeightUnit2.gridy = 7;
		panel_VitalsForm.add(lblHeightUnit2, gbc_lblHeightUnit2);
		
		JLabel lblWeight = new JLabel("Weight:");
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.anchor = GridBagConstraints.EAST;
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 1;
		gbc_lblWeight.gridy = 9;
		panel_VitalsForm.add(lblWeight, gbc_lblWeight);
		
		textField_WeightUnit = new JTextField();
		GridBagConstraints gbc_textField_WeightUnit = new GridBagConstraints();
		gbc_textField_WeightUnit.insets = new Insets(0, 0, 5, 5);
		gbc_textField_WeightUnit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_WeightUnit.gridx = 2;
		gbc_textField_WeightUnit.gridy = 9;
		panel_VitalsForm.add(textField_WeightUnit, gbc_textField_WeightUnit);
		textField_WeightUnit.setColumns(10);
		
		JLabel lblWeightUnit = new JLabel("unit1");
		GridBagConstraints gbc_lblWeightUnit = new GridBagConstraints();
		gbc_lblWeightUnit.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeightUnit.gridx = 3;
		gbc_lblWeightUnit.gridy = 9;
		panel_VitalsForm.add(lblWeightUnit, gbc_lblWeightUnit);
		
		JPanel panel_WeightGroup = new JPanel();
		GridBagConstraints gbc_panel_WeightGroup = new GridBagConstraints();
		gbc_panel_WeightGroup.insets = new Insets(0, 0, 5, 0);
		gbc_panel_WeightGroup.gridx = 4;
		gbc_panel_WeightGroup.gridy = 9;
		panel_VitalsForm.add(panel_WeightGroup, gbc_panel_WeightGroup);
		
		JRadioButton rdbtnLbs = new JRadioButton("lbs");
		rdbtnLbs.setSelected(true);
		panel_WeightGroup.add(rdbtnLbs);
		
		JRadioButton rdbtnKg = new JRadioButton("kg");
		panel_WeightGroup.add(rdbtnKg);
		
		JLabel lblNotes = new JLabel("Notes:");
		GridBagConstraints gbc_lblNotes = new GridBagConstraints();
		gbc_lblNotes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNotes.gridx = 1;
		gbc_lblNotes.gridy = 11;
		panel_VitalsForm.add(lblNotes, gbc_lblNotes);
		
		JTextArea textArea = new JTextArea();
		textArea.setRows(4);
		textArea.setColumns(32);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 2;
		gbc_textArea.gridwidth = 3;
		gbc_textArea.anchor = GridBagConstraints.NORTHWEST;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 11;
		panel_VitalsForm.add(textArea, gbc_textArea);
		
		JPanel panel_Buttons = new JPanel();
		add(panel_Buttons, BorderLayout.SOUTH);
		
		JButton btnCancel = new JButton("Cancel");
		panel_Buttons.add(btnCancel);
		
		JButton btnNewButton_1 = new JButton("Save");
		panel_Buttons.add(btnNewButton_1);
	}

}
