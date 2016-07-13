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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class VitalsTabNewVitalsView extends JPanel {
	private JPanel me;
	JPanel oldPanel;
	JTable vitalsTable;
	
	private Patient patient;
	private VitalsTableGateway vtg;
		
	private JTextField textField_BPSys;
	private JTextField textField_BPDias;

	
	private JTextField textField_HeightFt;
	private JTextField textField_HeightIn;
	private JTextField textField_HeightCm;
	
	private JTextField textField_Weight;
		
	private final ButtonGroup bpButtonGroup = new ButtonGroup();
	private final ButtonGroup heightButtonGroup = new ButtonGroup();
	private final ButtonGroup weightButtonGroup = new ButtonGroup();
	
	
	
	/**
	 * Create the panel.
	 */
	public VitalsTabNewVitalsView(final JTabbedPane tabbedPane, Patient patient, JPanel vitalsPanel, VitalsTableGateway gateway, JTable vitalsTable) {
		me = this;
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
		gbl_panel_VitalsForm.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_VitalsForm.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_VitalsForm.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		textField_BPSys = new JTextField();
		GridBagConstraints gbc_textField_BPSys = new GridBagConstraints();
		gbc_textField_BPSys.insets = new Insets(0, 0, 5, 5);
		gbc_textField_BPSys.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_BPSys.gridx = 2;
		gbc_textField_BPSys.gridy = 3;
		panel_VitalsForm.add(textField_BPSys, gbc_textField_BPSys);
		textField_BPSys.setColumns(10);
		
		JLabel lblBPSysUnit = new JLabel("mm/Hg");
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
		
		
		rdbtnMmhg.setSelected(true);
		panel_BPGroup.add(rdbtnMmhg);
		
		JRadioButton rdbtnPa = new JRadioButton("Pa");
		
		
		panel_BPGroup.add(rdbtnPa);
		
		JLabel lblBloodPressuredias = new JLabel("BP (Dias):");
		GridBagConstraints gbc_lblBloodPressuredias = new GridBagConstraints();
		gbc_lblBloodPressuredias.anchor = GridBagConstraints.EAST;
		gbc_lblBloodPressuredias.insets = new Insets(0, 0, 5, 5);
		gbc_lblBloodPressuredias.gridx = 1;
		gbc_lblBloodPressuredias.gridy = 4;
		panel_VitalsForm.add(lblBloodPressuredias, gbc_lblBloodPressuredias);
		
		textField_BPDias = new JTextField();
		GridBagConstraints gbc_textField_BPDias = new GridBagConstraints();
		gbc_textField_BPDias.insets = new Insets(0, 0, 5, 5);
		gbc_textField_BPDias.fill = GridBagConstraints.BOTH;
		gbc_textField_BPDias.gridx = 2;
		gbc_textField_BPDias.gridy = 4;
		panel_VitalsForm.add(textField_BPDias, gbc_textField_BPDias);
		textField_BPDias.setColumns(10);
		
		JLabel lblBPDiasUnit = new JLabel("mm/Hg");
		GridBagConstraints gbc_lblBPDiasUnit = new GridBagConstraints();
		gbc_lblBPDiasUnit.insets = new Insets(0, 0, 5, 5);
		gbc_lblBPDiasUnit.gridx = 3;
		gbc_lblBPDiasUnit.gridy = 4;
		panel_VitalsForm.add(lblBPDiasUnit, gbc_lblBPDiasUnit);
		
		bpButtonGroup.add(rdbtnMmhg);
		bpButtonGroup.add(rdbtnPa);
		rdbtnMmhg.addActionListener(new BPListener(lblBPSysUnit, lblBPDiasUnit));
		rdbtnPa.addActionListener(new BPListener(lblBPSysUnit, lblBPDiasUnit));
		
		
		JLabel lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.EAST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 6;
		panel_VitalsForm.add(lblHeight, gbc_lblHeight);
		
		textField_HeightFt = new JTextField();
		GridBagConstraints gbc_textField_HeightFt = new GridBagConstraints();
		gbc_textField_HeightFt.insets = new Insets(0, 0, 5, 5);
		gbc_textField_HeightFt.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_HeightFt.gridx = 2;
		gbc_textField_HeightFt.gridy = 6;
		panel_VitalsForm.add(textField_HeightFt, gbc_textField_HeightFt);
		textField_HeightFt.setColumns(10);
		
		JLabel lblHeightft = new JLabel("ft");
		GridBagConstraints gbc_lblHeightft = new GridBagConstraints();
		gbc_lblHeightft.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeightft.gridx = 3;
		gbc_lblHeightft.gridy = 6;
		panel_VitalsForm.add(lblHeightft, gbc_lblHeightft);
		
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
		
		textField_HeightIn = new JTextField();
		GridBagConstraints gbc_textField_HeightIn = new GridBagConstraints();
		gbc_textField_HeightIn.insets = new Insets(0, 0, 5, 5);
		gbc_textField_HeightIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_HeightIn.gridx = 2;
		gbc_textField_HeightIn.gridy = 7;
		panel_VitalsForm.add(textField_HeightIn, gbc_textField_HeightIn);
		textField_HeightIn.setColumns(10);
		
		JLabel lblHeightIn = new JLabel("in");
		GridBagConstraints gbc_lblHeightIn = new GridBagConstraints();
		gbc_lblHeightIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeightIn.gridx = 3;
		gbc_lblHeightIn.gridy = 7;
		panel_VitalsForm.add(lblHeightIn, gbc_lblHeightIn);
		
		textField_HeightCm = new JTextField();
		GridBagConstraints gbc_textField_HeightCm = new GridBagConstraints();
		gbc_textField_HeightCm.insets = new Insets(0, 0, 5, 5);
		gbc_textField_HeightCm.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_HeightCm.gridx = 2;
		gbc_textField_HeightCm.gridy = 8;
		panel_VitalsForm.add(textField_HeightCm, gbc_textField_HeightCm);
		textField_HeightCm.setColumns(10);
		
		JLabel lblHeightCm = new JLabel("cm");
		GridBagConstraints gbc_lblHeightCm = new GridBagConstraints();
		gbc_lblHeightCm.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeightCm.gridx = 3;
		gbc_lblHeightCm.gridy = 8;
		panel_VitalsForm.add(lblHeightCm, gbc_lblHeightCm);
		
		
		rdbtnFeetInches.addActionListener(new HeightListener());
		rdbtnInches.addActionListener(new HeightListener());
		rdbtnCm.addActionListener(new HeightListener());
		
		heightButtonGroup.add(rdbtnFeetInches);
		heightButtonGroup.add(rdbtnInches);
		heightButtonGroup.add(rdbtnCm);
		
		
		
		
		JLabel lblWeight = new JLabel("Weight:");
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.anchor = GridBagConstraints.EAST;
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 1;
		gbc_lblWeight.gridy = 10;
		panel_VitalsForm.add(lblWeight, gbc_lblWeight);
		
		textField_Weight = new JTextField();
		GridBagConstraints gbc_textField_Weight = new GridBagConstraints();
		gbc_textField_Weight.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Weight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Weight.gridx = 2;
		gbc_textField_Weight.gridy = 10;
		panel_VitalsForm.add(textField_Weight, gbc_textField_Weight);
		textField_Weight.setColumns(10);
		
		JLabel lblWeightUnit = new JLabel("lbs");
		GridBagConstraints gbc_lblWeightUnit = new GridBagConstraints();
		gbc_lblWeightUnit.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeightUnit.gridx = 3;
		gbc_lblWeightUnit.gridy = 10;
		panel_VitalsForm.add(lblWeightUnit, gbc_lblWeightUnit);
		
		JPanel panel_WeightGroup = new JPanel();
		GridBagConstraints gbc_panel_WeightGroup = new GridBagConstraints();
		gbc_panel_WeightGroup.insets = new Insets(0, 0, 5, 0);
		gbc_panel_WeightGroup.gridx = 4;
		gbc_panel_WeightGroup.gridy = 10;
		panel_VitalsForm.add(panel_WeightGroup, gbc_panel_WeightGroup);
		
		JRadioButton rdbtnLbs = new JRadioButton("lbs");
		rdbtnLbs.setSelected(true);
		panel_WeightGroup.add(rdbtnLbs);
		
		JRadioButton rdbtnKg = new JRadioButton("kg");
		panel_WeightGroup.add(rdbtnKg);
		
		weightButtonGroup.add(rdbtnLbs);
		weightButtonGroup.add(rdbtnKg);
		rdbtnLbs.addActionListener(new WeightListener(lblWeightUnit));
		rdbtnKg.addActionListener(new WeightListener(lblWeightUnit));
		
		
		JLabel lblNotes = new JLabel("Notes:");
		GridBagConstraints gbc_lblNotes = new GridBagConstraints();
		gbc_lblNotes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNotes.gridx = 1;
		gbc_lblNotes.gridy = 12;
		panel_VitalsForm.add(lblNotes, gbc_lblNotes);
		
		JTextArea textArea_Notes = new JTextArea();
		textArea_Notes.setRows(4);
		GridBagConstraints gbc_textArea_Notes = new GridBagConstraints();
		gbc_textArea_Notes.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea_Notes.gridheight = 2;
		gbc_textArea_Notes.gridwidth = 3;
		gbc_textArea_Notes.anchor = GridBagConstraints.NORTH;
		gbc_textArea_Notes.gridx = 2;
		gbc_textArea_Notes.gridy = 12;
		panel_VitalsForm.add(textArea_Notes, gbc_textArea_Notes);
		
		JPanel panel_Buttons = new JPanel();
		add(panel_Buttons, BorderLayout.SOUTH);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancel(tabbedPane, oldPanel);
			}
		});
		panel_Buttons.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//save(patient, atg, tabbedPane, oldPanel, allergyTable); TODO
			}
		});
		panel_Buttons.add(btnSave);

	}
	
	private class BPListener implements ActionListener {
		JLabel lbl1, lbl2;

		public BPListener(JLabel lblBPSysUnit, JLabel lblBPDiasUnit) {
			lbl1 = lblBPSysUnit;
			lbl2 = lblBPDiasUnit;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("mm/Hg")) {
				lbl1.setText("mm/Hg");
				lbl2.setText("mm/Hg");
			} else {
				lbl1.setText("Pa");
				lbl2.setText("Pa");
			}
	    }
	}
	
	private class HeightListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("ft/inches")) {
				textField_HeightFt.setEnabled(true);
				textField_HeightIn.setEnabled(true);
				textField_HeightCm.setEnabled(true);
			} else if (e.getActionCommand().equals("inches")) {
				textField_HeightFt.setEnabled(false);
				textField_HeightIn.setEnabled(true);
				textField_HeightCm.setEnabled(false);
			} else {
				textField_HeightFt.setEnabled(false);
				textField_HeightIn.setEnabled(false);
				textField_HeightCm.setEnabled(true);
			}
			//textField_BPSys.setEnabled(false);
	    }
	}
	
	private class WeightListener implements ActionListener {
		JLabel lbl;

		public WeightListener(JLabel lblWeightUnit) {
			lbl = lblWeightUnit;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("lbs")) {
				lbl.setText("lbs");
			} else {
				lbl.setText("kg");
			}
			//textField_BPSys.setEnabled(false);
	    }
	}
	
}
