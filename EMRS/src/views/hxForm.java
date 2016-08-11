package views;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import models.CL;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Component;
import javax.swing.Box;

public class hxForm extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public hxForm() {
		
		setBackground(CL.cararra);
		
		JPanel panel = new JPanel();
		panel.setBackground(CL.cararra);
		panel.setBorder(new TitledBorder(new LineBorder(CL.belize, 2), "History", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), CL.belize));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(CL.cararra);
		panel_1.setBorder(new TitledBorder(new LineBorder(CL.belize, 2), "Physical Exam", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), CL.belize));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(CL.colorBlue);
		panel_2.setBorder(null);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 408, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
					.addGap(28)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(new GridLayout(14, 3, 1, 1));
		
		JLabel lblPhysicalExam = new JLabel("PHYSICAL EXAM");
		lblPhysicalExam.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPhysicalExam.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblPhysicalExam);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea);
		
		JLabel lblNormalForPatient = new JLabel("Normal for Patient");
		lblNormalForPatient.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNormalForPatient.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNormalForPatient);
		
		JLabel lblVitalSigns = new JLabel("Vital signs");
		lblVitalSigns.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVitalSigns.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblVitalSigns);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_1);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setOpaque(true);
		checkBox.setBorderPainted(true);
		checkBox.setForeground(Color.BLACK);
		checkBox.setBackground(Color.LIGHT_GRAY);
		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox);
		
		JLabel lblNeurological = new JLabel("Neurological");
		lblNeurological.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNeurological.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblNeurological);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_2);
		
		JCheckBox checkBox_2 = new JCheckBox("");
		checkBox_2.setBorderPainted(true);
		checkBox_2.setOpaque(true);
		checkBox_2.setBackground(Color.LIGHT_GRAY);
		checkBox_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox_2);
		
		JLabel lblHeadNeck = new JLabel("Head & Neck");
		lblHeadNeck.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHeadNeck.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblHeadNeck);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_3);
		
		JCheckBox checkBox_3 = new JCheckBox("");
		checkBox_3.setOpaque(true);
		checkBox_3.setBackground(Color.LIGHT_GRAY);
		checkBox_3.setBorderPainted(true);
		checkBox_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox_3);
		
		JLabel lblMouthThroat = new JLabel("Mouth & Throat");
		lblMouthThroat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMouthThroat.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblMouthThroat);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_4);
		
		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.setBackground(Color.LIGHT_GRAY);
		checkBox_1.setBorderPainted(true);
		checkBox_1.setOpaque(true);
		checkBox_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox_1);
		
		JLabel lblBreast = new JLabel("Breast");
		lblBreast.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBreast.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblBreast);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_5);
		
		JCheckBox checkBox_5 = new JCheckBox("");
		checkBox_5.setOpaque(true);
		checkBox_5.setBorderPainted(true);
		checkBox_5.setBackground(Color.LIGHT_GRAY);
		checkBox_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox_5);
		
		JLabel lblHeart = new JLabel("Heart");
		lblHeart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHeart.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblHeart);
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_6);
		
		JCheckBox checkBox_4 = new JCheckBox("");
		checkBox_4.setBackground(Color.LIGHT_GRAY);
		checkBox_4.setBorderPainted(true);
		checkBox_4.setOpaque(true);
		checkBox_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox_4);
		
		JLabel lblLungs = new JLabel("Lungs");
		lblLungs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLungs.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblLungs);
		
		Component rigidArea_7 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_7);
		
		JCheckBox checkBox_7 = new JCheckBox("");
		checkBox_7.setBorderPainted(true);
		checkBox_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox_7);
		
		JLabel lblAbdomen = new JLabel("Abdomen");
		lblAbdomen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAbdomen.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblAbdomen);
		
		Component rigidArea_8 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_8);
		
		JCheckBox checkBox_6 = new JCheckBox("");
		checkBox_6.setBorderPainted(true);
		checkBox_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox_6);
		
		JLabel lblExtremities = new JLabel("Extremities");
		lblExtremities.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblExtremities.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblExtremities);
		
		Component rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_9);
		
		JCheckBox checkBox_9 = new JCheckBox("");
		checkBox_9.setBorderPainted(true);
		checkBox_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox_9);
		
		JLabel lblGureproductive = new JLabel("Gu/Reproductive");
		lblGureproductive.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGureproductive.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblGureproductive);
		
		Component rigidArea_10 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_10);
		
		JCheckBox checkBox_8 = new JCheckBox("");
		checkBox_8.setBorderPainted(true);
		checkBox_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox_8);
		
		JLabel lblRectal = new JLabel("Rectal");
		lblRectal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRectal.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblRectal);
		
		Component rigidArea_11 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_11);
		
		JCheckBox checkBox_11 = new JCheckBox("");
		checkBox_11.setBorderPainted(true);
		checkBox_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox_11);
		
		JLabel lblLabekgResults = new JLabel("Lab/EKG Results");
		lblLabekgResults.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLabekgResults.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblLabekgResults);
		
		Component rigidArea_12 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_12);
		
		JCheckBox checkBox_12 = new JCheckBox("");
		checkBox_12.setBorderPainted(true);
		checkBox_12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox_12);
		
		JLabel lblXrayResults = new JLabel("X-Ray Results");
		lblXrayResults.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXrayResults.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblXrayResults);
		
		Component rigidArea_13 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_13);
		
		JCheckBox checkBox_10 = new JCheckBox("");
		checkBox_10.setBorderPainted(true);
		checkBox_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox_10);
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblPresentCondition = new JLabel("Present Condition:");
		lblPresentCondition.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblPresentCondition, "cell 0 0");
		
		textField = new JTextField();
		panel.add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(CL.belize);
		panel.add(separator, "cell 0 1 2 1,growx");
		
		JLabel lblAllergies = new JLabel("Allergies?");
		lblAllergies.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblAllergies, "flowx,cell 0 2");
		
		JRadioButton rdbtnNkda = new JRadioButton("NKDA");
		rdbtnNkda.setBorderPainted(true);
		rdbtnNkda.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(rdbtnNkda, "flowx,cell 1 2");
		
		JRadioButton hasDA = new JRadioButton("Drug Allergy:");
		hasDA.setBorderPainted(true);
		hasDA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(hasDA, "cell 1 2");
		
		ButtonGroup btnGroupAllergy = new ButtonGroup();
		btnGroupAllergy.add(rdbtnNkda);
		btnGroupAllergy.add(hasDA);
		
		textField_1 = new JTextField();
		panel.add(textField_1, "cell 1 2");
		textField_1.setColumns(10);
		
		JLabel lblSickleCell = new JLabel("Sickle Cell?");
		lblSickleCell.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblSickleCell, "cell 0 3");
		
		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBorderPainted(true);
		rdbtnYes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(rdbtnYes, "flowx,cell 1 3");
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBorderPainted(true);
		rdbtnNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(rdbtnNo, "cell 1 3");
		
		ButtonGroup btnGroupSickleCell = new ButtonGroup();
		btnGroupSickleCell.add(rdbtnYes);
		btnGroupSickleCell.add(rdbtnNo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(CL.belize);
		panel.add(separator_1, "cell 0 4 2 1,growx");
		
		JLabel lblBleedingTendency = new JLabel("Bleeding Tendency?");
		lblBleedingTendency.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblBleedingTendency, "cell 0 5");
		
		JCheckBox chckbxHypertension = new JCheckBox("Aspirin");
		chckbxHypertension.setBorderPainted(true);
		chckbxHypertension.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(chckbxHypertension, "flowx,cell 0 6 2 1");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Plavix");
		chckbxNewCheckBox.setBorderPainted(true);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(chckbxNewCheckBox, "cell 0 6 2 1");
		
		JCheckBox chckbxBleedingDisorder = new JCheckBox("Bleeding Disorder");
		chckbxBleedingDisorder.setBorderPainted(true);
		chckbxBleedingDisorder.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(chckbxBleedingDisorder, "cell 0 6 2 1");
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(CL.belize);
		panel.add(separator_2, "cell 0 7 2 1,growx");
		
		JLabel lblPastMedicalHistory = new JLabel("Past Medical History");
		lblPastMedicalHistory.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblPastMedicalHistory, "cell 0 8");
		
		JCheckBox chckbxHypertension_1 = new JCheckBox("Hypertension (HTN)");
		chckbxHypertension_1.setBorderPainted(true);
		chckbxHypertension_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(chckbxHypertension_1, "cell 1 8");
		
		JCheckBox chckbxDiabetes = new JCheckBox("Diabetes");
		chckbxDiabetes.setBorderPainted(true);
		chckbxDiabetes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(chckbxDiabetes, "cell 0 9");
		
		JCheckBox chckbxCoronaryArteryDisease = new JCheckBox("Coronary Artery Disease (CAD)");
		chckbxCoronaryArteryDisease.setBorderPainted(true);
		chckbxCoronaryArteryDisease.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(chckbxCoronaryArteryDisease, "cell 1 9");
		
		JCheckBox chckbxCopd = new JCheckBox("COPD");
		chckbxCopd.setBorderPainted(true);
		chckbxCopd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(chckbxCopd, "cell 0 10");
		
		JCheckBox chckbxPeripheralVascularDisease = new JCheckBox("Peripheral Vascular Disease (PVD)");
		chckbxPeripheralVascularDisease.setBorderPainted(true);
		chckbxPeripheralVascularDisease.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(chckbxPeripheralVascularDisease, "cell 1 10");
		
		JCheckBox chckbxCongestiveHeartFailure = new JCheckBox("Congestive Heart Failure (CHF)");
		chckbxCongestiveHeartFailure.setBorderPainted(true);
		chckbxCongestiveHeartFailure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(chckbxCongestiveHeartFailure, "flowx,cell 1 11");
		
		JCheckBox chckbxHypothyroidism = new JCheckBox("Hypothyroidism");
		chckbxHypothyroidism.setBorderPainted(true);
		chckbxHypothyroidism.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(chckbxHypothyroidism, "cell 1 12");
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(CL.belize);
		panel.add(separator_3, "cell 0 13 2 1,growx");
		
		JLabel lblPastSurgicalHistory = new JLabel("Past Surgical History:");
		lblPastSurgicalHistory.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblPastSurgicalHistory, "cell 0 14,alignx trailing");
		
		textField_2 = new JTextField();
		panel.add(textField_2, "cell 1 14,growx");
		textField_2.setColumns(10);
		
		JLabel lblFamilyHistory = new JLabel("Family History:");
		lblFamilyHistory.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblFamilyHistory, "cell 0 15,alignx trailing");
		
		textField_3 = new JTextField();
		panel.add(textField_3, "cell 1 15,growx");
		textField_3.setColumns(10);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(CL.belize);
		panel.add(separator_4, "cell 0 16 2 1,growx");
		
		JLabel lblRisksBenefitsPotential = new JLabel("Risks, benefits, potential complications,");
		lblRisksBenefitsPotential.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		panel.add(lblRisksBenefitsPotential, "cell 0 17 2 1,alignx left,growy");
		
		JLabel lblAndAlternativesFor = new JLabel("and alternatives for surgery discussed with patient?");
		lblAndAlternativesFor.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		panel.add(lblAndAlternativesFor, "cell 0 18 2 1");
		
		JRadioButton rdbtnYes_1 = new JRadioButton("Yes");
		rdbtnYes_1.setBorderPainted(true);
		rdbtnYes_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(rdbtnYes_1, "flowx,cell 0 19 2 1");
		
		JRadioButton rdbtnNo_1 = new JRadioButton("No");
		rdbtnNo_1.setBorderPainted(true);
		rdbtnNo_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(rdbtnNo_1, "cell 0 19");
		
		JRadioButton rdbtnNotYet = new JRadioButton("Not Yet");
		rdbtnNotYet.setBorderPainted(true);
		rdbtnNotYet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(rdbtnNotYet, "cell 0 19");
		
		ButtonGroup lawBtnGroup = new ButtonGroup();
		lawBtnGroup.add(rdbtnYes_1);
		lawBtnGroup.add(rdbtnNo_1);
		lawBtnGroup.add(rdbtnNotYet);
		
		JButton btnSave = new JButton("Save");
		panel_2.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		panel_2.add(btnCancel);
		setLayout(groupLayout);

	}
}
