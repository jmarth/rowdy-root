package views;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import models.CL;
import models.Hx;
import models.Patient;
import models.Tabs;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import java.awt.SystemColor;
import javax.swing.JRadioButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import database.GatewayException;
import database.HxTableGateway;

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
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class hxForm extends JPanel {
	private JTextField pcTextField;
	private JTextField hasDATextField;
	private JTextField pshTextField;
	private JTextField fhTextField;
	
	private HxTableGateway htg;
	
	private Patient patient;
	private JRadioButton nkda;
	private JRadioButton hasDA;
	private JRadioButton sickleYes;
	private JRadioButton sickleNo;
	private JCheckBox aspirinCk;
	private JCheckBox plavixCk;
	private JCheckBox bleedingdisorderCk;
	private JCheckBox htnCk;
	private JCheckBox diabetesCk;
	private JCheckBox cadCk;
	private JCheckBox copdCk;
	private JCheckBox pvdCk;
	private JCheckBox chfCk;
	private JCheckBox hypoCk;
	private JRadioButton lawYes;
	private JRadioButton lawNo;
	private JRadioButton lawNotYet;
	private JCheckBox ck1;
	private JCheckBox ck2;
	private JCheckBox ck3;
	private JCheckBox ck4;
	private JCheckBox ck5;
	private JCheckBox ck6;
	private JCheckBox ck7;
	private JCheckBox ck8;
	private JCheckBox ck9;
	private JCheckBox ck10;
	private JCheckBox ck11;
	private JCheckBox ck12;
	private JCheckBox ck13;
	private ButtonGroup btnGroupAllergy;
	private ButtonGroup btnGroupSickleCell;
	private ButtonGroup lawBtnGroup;
	private List<JCheckBox> pmhGroup;
	private List<JCheckBox> bleedingTendencyGroup;
	private List<JCheckBox> peGroup;
	private JPanel prevPanel;
	private JTabbedPane tabbedPane;

	/**
	 * Create the panel.
	 * @param prevPanel 
	 * @param tabbedPane 
	 */
	public hxForm(Patient p, HxTableGateway hxTableGateway, final hxView prevPanel, final JTabbedPane tabbedPane) {
		
		this.htg = hxTableGateway;
		this.patient = p;
		this.prevPanel = prevPanel;
		this.tabbedPane = tabbedPane;
		
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
		
		ck1 = new JCheckBox("");
		ck1.setOpaque(true);
		ck1.setBorderPainted(true);
		ck1.setForeground(Color.BLACK);
		ck1.setBackground(Color.LIGHT_GRAY);
		ck1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck1);
		
		JLabel lblNeurological = new JLabel("Neurological");
		lblNeurological.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNeurological.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblNeurological);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_2);
		
		ck2 = new JCheckBox("");
		ck2.setBorderPainted(true);
		ck2.setOpaque(true);
		ck2.setBackground(Color.LIGHT_GRAY);
		ck2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck2);
		
		JLabel lblHeadNeck = new JLabel("Head & Neck");
		lblHeadNeck.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHeadNeck.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblHeadNeck);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_3);
		
		ck3 = new JCheckBox("");
		ck3.setOpaque(true);
		ck3.setBackground(Color.LIGHT_GRAY);
		ck3.setBorderPainted(true);
		ck3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck3);
		
		JLabel lblMouthThroat = new JLabel("Mouth & Throat");
		lblMouthThroat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMouthThroat.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblMouthThroat);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_4);
		
		ck4 = new JCheckBox("");
		ck4.setBackground(Color.LIGHT_GRAY);
		ck4.setBorderPainted(true);
		ck4.setOpaque(true);
		ck4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck4);
		
		JLabel lblBreast = new JLabel("Breast");
		lblBreast.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBreast.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblBreast);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_5);
		
		ck5 = new JCheckBox("");
		ck5.setOpaque(true);
		ck5.setBorderPainted(true);
		ck5.setBackground(Color.LIGHT_GRAY);
		ck5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck5);
		
		JLabel lblHeart = new JLabel("Heart");
		lblHeart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHeart.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblHeart);
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_6);
		
		ck6 = new JCheckBox("");
		ck6.setBackground(Color.LIGHT_GRAY);
		ck6.setBorderPainted(true);
		ck6.setOpaque(true);
		ck6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck6);
		
		JLabel lblLungs = new JLabel("Lungs");
		lblLungs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLungs.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblLungs);
		
		Component rigidArea_7 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_7);
		
		ck7 = new JCheckBox("");
		ck7.setBorderPainted(true);
		ck7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck7);
		
		JLabel lblAbdomen = new JLabel("Abdomen");
		lblAbdomen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAbdomen.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblAbdomen);
		
		Component rigidArea_8 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_8);
		
		ck8 = new JCheckBox("");
		ck8.setBorderPainted(true);
		ck8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck8);
		
		JLabel lblExtremities = new JLabel("Extremities");
		lblExtremities.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblExtremities.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblExtremities);
		
		Component rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_9);
		
		ck9 = new JCheckBox("");
		ck9.setBorderPainted(true);
		ck9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck9);
		
		JLabel lblGureproductive = new JLabel("Gu/Reproductive");
		lblGureproductive.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGureproductive.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblGureproductive);
		
		Component rigidArea_10 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_10);
		
		ck10 = new JCheckBox("");
		ck10.setBorderPainted(true);
		ck10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck10);
		
		JLabel lblRectal = new JLabel("Rectal");
		lblRectal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRectal.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblRectal);
		
		Component rigidArea_11 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_11);
		
		ck11 = new JCheckBox("");
		ck11.setBorderPainted(true);
		ck11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck11);
		
		JLabel lblLabekgResults = new JLabel("Lab/EKG Results");
		lblLabekgResults.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLabekgResults.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblLabekgResults);
		
		Component rigidArea_12 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_12);
		
		ck12 = new JCheckBox("");
		ck12.setBorderPainted(true);
		ck12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck12);
		
		JLabel lblXrayResults = new JLabel("X-Ray Results");
		lblXrayResults.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXrayResults.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_1.add(lblXrayResults);
		
		Component rigidArea_13 = Box.createRigidArea(new Dimension(20, 20));
		panel_1.add(rigidArea_13);
		
		ck13 = new JCheckBox("");
		ck13.setBorderPainted(true);
		ck13.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ck13);
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][][][][][][][][][]"));
		
		peGroup = new ArrayList<JCheckBox>();
		peGroup.add(ck1);
		peGroup.add(ck2);
		peGroup.add(ck3);
		peGroup.add(ck4);
		peGroup.add(ck5);
		peGroup.add(ck6);
		peGroup.add(ck7);
		peGroup.add(ck8);
		peGroup.add(ck9);
		peGroup.add(ck10);
		peGroup.add(ck11);
		peGroup.add(ck12);
		peGroup.add(ck13);
		
		JLabel lblPresentCondition = new JLabel("Present Condition:");
		lblPresentCondition.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblPresentCondition, "cell 0 0");
		
		pcTextField = new JTextField();
		panel.add(pcTextField, "cell 1 0,growx");
		pcTextField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(CL.belize);
		panel.add(separator, "cell 0 1 2 1,growx");
		
		JLabel lblAllergies = new JLabel("Allergies?");
		lblAllergies.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblAllergies, "flowx,cell 0 2");
		
		nkda = new JRadioButton("NKDA");
		nkda.setBorderPainted(true);
		nkda.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(nkda, "flowx,cell 1 2");
		
		hasDA = new JRadioButton("Drug Allergy:");
		hasDA.setBorderPainted(true);
		hasDA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(hasDA, "cell 1 2");
		
		btnGroupAllergy = new ButtonGroup();
		btnGroupAllergy.add(nkda);
		btnGroupAllergy.add(hasDA);
		
		hasDATextField = new JTextField();
		panel.add(hasDATextField, "cell 1 2");
		hasDATextField.setColumns(10);
		
		JLabel lblSickleCell = new JLabel("Sickle Cell?");
		lblSickleCell.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblSickleCell, "cell 0 3");
		
		sickleYes = new JRadioButton("Yes");
		sickleYes.setBorderPainted(true);
		sickleYes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(sickleYes, "flowx,cell 1 3");
		
		sickleNo = new JRadioButton("No");
		sickleNo.setBorderPainted(true);
		sickleNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(sickleNo, "cell 1 3");
		
		btnGroupSickleCell = new ButtonGroup();
		btnGroupSickleCell.add(sickleYes);
		btnGroupSickleCell.add(sickleNo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(CL.belize);
		panel.add(separator_1, "cell 0 4 2 1,growx");
		
		JLabel lblBleedingTendency = new JLabel("Bleeding Tendency?");
		lblBleedingTendency.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblBleedingTendency, "cell 0 5");
		
		aspirinCk = new JCheckBox("Aspirin");
		aspirinCk.setBorderPainted(true);
		aspirinCk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(aspirinCk, "flowx,cell 0 6 2 1");
		
		plavixCk = new JCheckBox("Plavix");
		plavixCk.setBorderPainted(true);
		plavixCk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(plavixCk, "cell 0 6 2 1");
		
		bleedingdisorderCk = new JCheckBox("Bleeding Disorder");
		bleedingdisorderCk.setBorderPainted(true);
		bleedingdisorderCk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(bleedingdisorderCk, "cell 0 6 2 1");
		
		bleedingTendencyGroup = new ArrayList<JCheckBox>();
		bleedingTendencyGroup.add(aspirinCk);
		bleedingTendencyGroup.add(plavixCk);
		bleedingTendencyGroup.add(bleedingdisorderCk);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(CL.belize);
		panel.add(separator_2, "cell 0 7 2 1,growx");
		
		JLabel lblPastMedicalHistory = new JLabel("Past Medical History");
		lblPastMedicalHistory.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblPastMedicalHistory, "cell 0 8");
		
		htnCk = new JCheckBox("Hypertension (HTN)");
		htnCk.setBorderPainted(true);
		htnCk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(htnCk, "cell 1 8");
		
		diabetesCk = new JCheckBox("Diabetes");
		diabetesCk.setBorderPainted(true);
		diabetesCk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(diabetesCk, "cell 0 9");
		
		cadCk = new JCheckBox("Coronary Artery Disease (CAD)");
		cadCk.setBorderPainted(true);
		cadCk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(cadCk, "cell 1 9");
		
		copdCk = new JCheckBox("COPD");
		copdCk.setBorderPainted(true);
		copdCk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(copdCk, "cell 0 10");
		
		pvdCk = new JCheckBox("Peripheral Vascular Disease (PVD)");
		pvdCk.setBorderPainted(true);
		pvdCk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(pvdCk, "cell 1 10");
		
		chfCk = new JCheckBox("Congestive Heart Failure (CHF)");
		chfCk.setBorderPainted(true);
		chfCk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(chfCk, "flowx,cell 1 11");
		
		hypoCk = new JCheckBox("Hypothyroidism");
		hypoCk.setBorderPainted(true);
		hypoCk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(hypoCk, "cell 1 12");
		
		pmhGroup = new ArrayList<JCheckBox>();
		pmhGroup.add(htnCk);
		pmhGroup.add(diabetesCk);
		pmhGroup.add(cadCk);
		pmhGroup.add(copdCk);
		pmhGroup.add(pvdCk);
		pmhGroup.add(chfCk);
		pmhGroup.add(hypoCk);
		
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(CL.belize);
		panel.add(separator_3, "cell 0 13 2 1,growx");
		
		JLabel lblPastSurgicalHistory = new JLabel("Past Surgical History:");
		lblPastSurgicalHistory.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblPastSurgicalHistory, "cell 0 14,alignx trailing");
		
		pshTextField = new JTextField();
		panel.add(pshTextField, "cell 1 14,growx");
		pshTextField.setColumns(10);
		
		JLabel lblFamilyHistory = new JLabel("Family History:");
		lblFamilyHistory.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblFamilyHistory, "cell 0 15,alignx trailing");
		
		fhTextField = new JTextField();
		panel.add(fhTextField, "cell 1 15,growx");
		fhTextField.setColumns(10);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(CL.belize);
		panel.add(separator_4, "cell 0 16 2 1,growx");
		
		JLabel lblRisksBenefitsPotential = new JLabel("Risks, benefits, potential complications,");
		lblRisksBenefitsPotential.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		panel.add(lblRisksBenefitsPotential, "cell 0 17 2 1,alignx left,growy");
		
		JLabel lblAndAlternativesFor = new JLabel("and alternatives for surgery discussed with patient?");
		lblAndAlternativesFor.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		panel.add(lblAndAlternativesFor, "cell 0 18 2 1");
		
		lawYes = new JRadioButton("Yes");
		lawYes.setBorderPainted(true);
		lawYes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lawYes, "flowx,cell 0 19 2 1");
		
		lawNo = new JRadioButton("No");
		lawNo.setBorderPainted(true);
		lawNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lawNo, "cell 0 19");
		
		lawNotYet = new JRadioButton("Not Yet");
		lawNotYet.setBorderPainted(true);
		lawNotYet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lawNotYet, "cell 0 19");
		
		lawBtnGroup = new ButtonGroup();
		lawBtnGroup.add(lawYes);
		lawBtnGroup.add(lawNo);
		lawBtnGroup.add(lawNotYet);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Hx tmpHx = hxForm.this.getAllFields();
				try {
					htg.insertHx(tmpHx);
				} catch (GatewayException e1) {
					e1.printStackTrace();
				}
				prevPanel.populateHealthHistory();
				int index = tabbedPane.indexOfTab(Tabs.hx);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, prevPanel);
				
			}
			
		});
		panel_2.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.indexOfTab(Tabs.hx);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, prevPanel);
			}
			
		});
		panel_2.add(btnCancel);
		setLayout(groupLayout);

	}

	protected Hx getAllFields() {
		String drugAllergy = "";
		drugAllergy += (nkda.isSelected()) ? "NKDA" : hasDATextField.getText();
		
		// bleedingTendency = aspirin:plavix:bleedingdisorder:sicklecell
		String bleedingTendency = "";
		for (JCheckBox c : bleedingTendencyGroup) {
			bleedingTendency += (c.isSelected()) ? "1" : "0";
		}
		bleedingTendency += (sickleYes.isSelected()) ? "1" : "0";
		
		String pastMedicalHistory = "";
		for (JCheckBox c : pmhGroup) {
			pastMedicalHistory += (c.isSelected()) ? "1" : "0";
		}
		
		String law = "Risks, benefits, potential complications, and alternatives for surgery discussed with patient?  ";
		
		if (lawYes.isSelected())
			law += "YES";
		else if (lawNo.isSelected())
			law += "NO";
		else
			law += "NOT YET";
		
		String pe = "";
		
		for (JCheckBox c : peGroup) {
			pe += (c.isSelected()) ? "1" : "0";
		}
		
		 Hx tmpHx = new Hx(
				(long) 0, 
				patient.getId(), 
				pcTextField.getText(), 
				drugAllergy, 
				bleedingTendency, 
				pastMedicalHistory, 
				pshTextField.getText(), 
				fhTextField.getText(), 
				law, 
				pe);
				
		
		return tmpHx;
	}
}
