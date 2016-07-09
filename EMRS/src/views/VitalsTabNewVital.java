package views;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Font;

public class VitalsTabNewVital extends JPanel {
	private JTextField textField_BPSysUnit;
	private JTextField textField_BPDiasUnit;
	private JTextField textField_HeightUnit1;
	private JTextField textField_WeightUnit;
	private JTextField textField_HeightUnit2;

	/**
	 * Create the panel.
	 */
	public VitalsTabNewVital() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_VitalsForm = new JPanel();
		add(panel_VitalsForm, BorderLayout.CENTER);
		GridBagLayout gbl_panel_VitalsForm = new GridBagLayout();
		gbl_panel_VitalsForm.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_VitalsForm.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_VitalsForm.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_VitalsForm.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_VitalsForm.setLayout(gbl_panel_VitalsForm);
		
		JLabel lblVitals = new JLabel("Vitals");
		lblVitals.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblVitals = new GridBagConstraints();
		gbc_lblVitals.insets = new Insets(0, 0, 5, 5);
		gbc_lblVitals.gridx = 1;
		gbc_lblVitals.gridy = 1;
		panel_VitalsForm.add(lblVitals, gbc_lblVitals);
		
		JLabel lblBloodPressuresys = new JLabel("Blood Pressure (Sys):");
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
		
		JLabel lblBloodPressuredias = new JLabel("Blood Pressure (Dias):");
		GridBagConstraints gbc_lblBloodPressuredias = new GridBagConstraints();
		gbc_lblBloodPressuredias.anchor = GridBagConstraints.EAST;
		gbc_lblBloodPressuredias.insets = new Insets(0, 0, 5, 5);
		gbc_lblBloodPressuredias.gridx = 1;
		gbc_lblBloodPressuredias.gridy = 4;
		panel_VitalsForm.add(lblBloodPressuredias, gbc_lblBloodPressuredias);
		
		textField_BPDiasUnit = new JTextField();
		GridBagConstraints gbc_textField_BPDiasUnit = new GridBagConstraints();
		gbc_textField_BPDiasUnit.insets = new Insets(0, 0, 5, 5);
		gbc_textField_BPDiasUnit.fill = GridBagConstraints.HORIZONTAL;
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
		gbc_panel_HeightGroup.fill = GridBagConstraints.VERTICAL;
		gbc_panel_HeightGroup.anchor = GridBagConstraints.WEST;
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
		gbc_lblWeight.gridy = 8;
		panel_VitalsForm.add(lblWeight, gbc_lblWeight);
		
		textField_WeightUnit = new JTextField();
		GridBagConstraints gbc_textField_WeightUnit = new GridBagConstraints();
		gbc_textField_WeightUnit.insets = new Insets(0, 0, 5, 5);
		gbc_textField_WeightUnit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_WeightUnit.gridx = 2;
		gbc_textField_WeightUnit.gridy = 8;
		panel_VitalsForm.add(textField_WeightUnit, gbc_textField_WeightUnit);
		textField_WeightUnit.setColumns(10);
		
		JLabel lblWeightUnit = new JLabel("unit1");
		GridBagConstraints gbc_lblWeightUnit = new GridBagConstraints();
		gbc_lblWeightUnit.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeightUnit.gridx = 3;
		gbc_lblWeightUnit.gridy = 8;
		panel_VitalsForm.add(lblWeightUnit, gbc_lblWeightUnit);
		
		JPanel panel_WeightGroup = new JPanel();
		GridBagConstraints gbc_panel_WeightGroup = new GridBagConstraints();
		gbc_panel_WeightGroup.anchor = GridBagConstraints.WEST;
		gbc_panel_WeightGroup.insets = new Insets(0, 0, 5, 0);
		gbc_panel_WeightGroup.fill = GridBagConstraints.VERTICAL;
		gbc_panel_WeightGroup.gridx = 4;
		gbc_panel_WeightGroup.gridy = 8;
		panel_VitalsForm.add(panel_WeightGroup, gbc_panel_WeightGroup);
		
		JRadioButton rdbtnLbs = new JRadioButton("lbs");
		rdbtnLbs.setSelected(true);
		panel_WeightGroup.add(rdbtnLbs);
		
		JRadioButton rdbtnKg = new JRadioButton("kg");
		panel_WeightGroup.add(rdbtnKg);
		
		JPanel panel_Buttons = new JPanel();
		add(panel_Buttons, BorderLayout.SOUTH);
		
		JButton btnCancel = new JButton("Cancel");
		panel_Buttons.add(btnCancel);
		
		JButton btnNewButton_1 = new JButton("Save");
		panel_Buttons.add(btnNewButton_1);
		
	}

}
