package views;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import database.GatewayException;
import models.Comment;
import models.HomeModel;
import models.Patient;
import models.Tabs;
import models.Vitals;
import models.VitalsList;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JCheckBox;

public class VitalsTabNewVitalsView2 extends JPanel {

	private JButton btnAppendNote;
	private JButton btnConfirm;
	private JButton btnCancel;

	private JTextField textField_BGLevel;
	private JTextField textField_O2Sat;
	private JTextField textField_Hb;
	private JTextField textField_BPS;
	private JTextField textField_BPD;
	private JTextField textField_Ft;
	private JTextField textField_In;
	private JTextField textField_Cm;
	private JTextField textField_Weight;

	private JLabel lblFt;
	private JLabel lblIn;
	private JLabel lblCm;
	private JLabel lblBP;
	private JLabel lblWeightUnit;

	private JTextArea textArea_Notes;

	private JScrollPane scrollPane_Comments;
	private JTextArea textArea_Comment;
	private JButton btnAppendComment;
	private JButton btnAppendCancel;

	private final ButtonGroup buttonGroupBP = new ButtonGroup();
	private final ButtonGroup buttonGroupHeight = new ButtonGroup();
	private final ButtonGroup buttonGroupWeight = new ButtonGroup();
	private final ButtonGroup buttonGroupBloodGlucose = new ButtonGroup();

	private final JTabbedPane tabbedPane;
	private final JPanel vitalsTabView;

	private Patient patient;
	private HomeModel homeModel;
	private List<Comment> commentsList;
	public String heightUnit;
	private JTable vitalsTable;
	private List<Vitals> vitalsList;
	private JPanel panel_CommentForm;
	private Vitals vitals;
	private JPanel contentPane;
	private JPanel panel_VitalsForm;

	// TODO Systolic and Diastolic labels are backwards, should be S/D

	/**
	 * Create the panel.
	 */
	public VitalsTabNewVitalsView2(final JTabbedPane tabbedPane, Patient patient, final JPanel vitalsPanel,
			final HomeModel homeModel, JTable vitalsTable, List<Vitals> vitalsList, VitalsList vl, Vitals vitals,
			Boolean detailedView) {

		this.patient = patient;
		this.tabbedPane = tabbedPane;
		this.vitalsTabView = vitalsPanel;
		this.homeModel = homeModel;
		this.vitalsTable = vitalsTable;
		this.vitalsList = vitalsList;
		this.vitals = vitals;
		contentPane = this;

		/////////////////////////////////
		createView();
		if (detailedView) {

			commentsList = null;

			try {
				commentsList = homeModel.getCtg().fetchCommentsForPatientByType(patient, Comment.vitals,
						vitals.getId());

			} catch (GatewayException e) {
				e.printStackTrace();
			}

			createView();

			disableFields();

		} else {

			createView();
		}
	}

	private void disableFields() {
		for (Component c : panel_VitalsForm.getComponents()) {
			if (c instanceof JPanel) {
				for (Component c2 : ((JPanel) c).getComponents()) {
					if (c2 instanceof JTextField) {
						((JTextField) c2).setEditable(false);
						((JTextField) c2).setText("YO");
					}
				}
			}
		}
		textArea_Notes.setEditable(false);
		textArea_Notes.setText("YOYO");
	}

	private class AppendListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			btnAppendNote.setVisible(false);
			panel_CommentForm.setVisible(true);
		}

	}

	private class ConfirmVitalListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			save();

			int index = tabbedPane.indexOfTab(Tabs.vitals);
			tabbedPane.setComponentAt(index, null);
			tabbedPane.setComponentAt(index, vitalsTabView);
		}

	}

	private class CancelVitalListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int index = tabbedPane.indexOfTab(Tabs.vitals);
			tabbedPane.setComponentAt(index, null);
			tabbedPane.setComponentAt(index, vitalsTabView);
		}

	}

	private class AddCommentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			btnAppendNote.setVisible(true);
			panel_CommentForm.setVisible(false);

			// probably need string builder for this ?
			String commentString = textArea_Comment.getText();

			Comment commentTmp = new Comment(0, patient.getId(), Comment.vitals, vitals.getId(), commentString);

			try {

				homeModel.getCtg().insertComment(commentTmp);

			} catch (GatewayException e1) {
				e1.printStackTrace();
			}

			// is layout to box layout
			JLabel tmpLabel = new JLabel(commentTmp.getCommentString(), JLabel.CENTER);
			tmpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			scrollPane_Comments.add(tmpLabel);
			scrollPane_Comments.add(new JSeparator(SwingConstants.HORIZONTAL));

		}

	}

	private class CancelCommentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			btnAppendNote.setVisible(true);
			panel_CommentForm.setVisible(false);
		}

	}

	private class UpdateVitalListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	private class BPListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(Vitals.MMHG)) {
				lblBP.setText(Vitals.MMHG);
			} else {
				lblBP.setText(Vitals.PA);
			}
		}
	}

	private class HeightListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals(Vitals.FTIN)) {

				textField_Ft.setEnabled(true);
				textField_In.setEnabled(true);
				textField_In.setText("");

				textField_Cm.setEnabled(false);
				textField_Cm.setText("");

				heightUnit = Vitals.FTIN;

			} else if (e.getActionCommand().equals(Vitals.IN)) {

				textField_Ft.setEnabled(false);
				textField_Ft.setText("");

				textField_In.setEnabled(true);

				textField_Cm.setEnabled(false);
				textField_Cm.setText("");

				heightUnit = Vitals.IN;

			} else {

				textField_Ft.setEnabled(false);
				textField_Ft.setText("");

				textField_In.setEnabled(false);
				textField_In.setText("");

				textField_Cm.setEnabled(true);

				heightUnit = Vitals.CM;
			}
		}
	}

	private class WeightListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals(Vitals.LBS)) {

				lblWeightUnit.setText(Vitals.LBS);

			} else {

				lblWeightUnit.setText(Vitals.KG);
			}
		}
	}

	/**
	 * Gets the selected button's name from a button group.
	 * 
	 * @param buttonGroup
	 * @return The string of the name of the selected radio button of the
	 *         buttonGroup
	 */
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}
	// TODO

	private void save() {

		StringBuilder sb = new StringBuilder(128);

		/**
		 * Create new Vitals object with correct parameters Insert the vitals to
		 * the DB through the Gateway
		 */

		// TODO

		Vitals vitals = new Vitals(0, patient.getId(), Float.parseFloat(textField_BPS.getText()),
				Float.parseFloat(textField_BPD.getText()), lblBP.getText(),
				Integer.parseInt(((textField_Ft.getText().equals("")) ? ""
						: textField_Ft.getText())),
				Integer.parseInt(((textField_In.getText().equals("")) ? ""
						: textField_In.getText())),
				Integer.parseInt(((textField_Cm.getText().equals("")) ? ""
						: textField_Cm.getText())),
				getSelectedButtonText(buttonGroupHeight), Float.parseFloat(((textField_Weight.getText().equals("")) ? ""
						: textField_Weight.getText())),
				lblWeightUnit.getText(), textArea_Notes.getText());
		try {
			long vid = homeModel.getVitalstg().insertVitals(vitals);
			vitals.setId(vid);

		} catch (GatewayException e) {
			e.printStackTrace();
		}

		/*
		 * Builds a string if ft/inches in form X'Y" otherwise, just an int
		 */

		// need to make a method for this...
		boolean isString = false;

		String height_ftin_String = null;
		int heightInt = -1;

		if (vitals.getHUnit() == null) {

		} else if (vitals.getHUnit().equals(Vitals.FTIN)) {

			sb.append(vitals.getHFeet());
			sb.append('\'');
			sb.append(vitals.getHInches());
			sb.append('\"');
			height_ftin_String = sb.toString();
			isString = true;

		} else if (vitals.getHUnit().equals("null")) {

			heightInt = -100;

		} else if (vitals.getHUnit().equals(Vitals.IN)) {

			heightInt = vitals.getHInches();

		} else {

			heightInt = vitals.getHCm();
		}

		// Add the vitals to the JTable
		// Get model of VitalsTable in order to add rows
		DefaultTableModel model = (DefaultTableModel) vitalsTable.getModel();

		// Add row
		model.addRow(new Object[] { vitals.getBps(), vitals.getBps(), vitals.getBpUnit(),
				(isString ? height_ftin_String : heightInt), vitals.getHUnit(), vitals.getWeight(), vitals.getWUnit(),
				vitals.getNotes() });

		vitalsList.add(vitals);

	}

	private void createView() {
		setLayout(new BorderLayout(0, 0));

		// Append Button at top

		JPanel panel_AppendButton = new JPanel();
		add(panel_AppendButton, BorderLayout.NORTH);
		panel_AppendButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnAppendNote = new JButton("Append Comment");
		panel_AppendButton.add(btnAppendNote);

		btnAppendNote.addActionListener(new AppendListener());

		// Main panels where all the fun stuff is

		JPanel panel_CenterMain = new JPanel();
		add(panel_CenterMain, BorderLayout.CENTER);
		panel_CenterMain.setLayout(new BorderLayout(0, 0));

		//
		panel_VitalsForm = new JPanel();
		panel_VitalsForm
				.setBorder(new TitledBorder(null, "Vitals", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_CenterMain.add(panel_VitalsForm, BorderLayout.CENTER);
		panel_VitalsForm.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][grow][grow][grow][grow]"));

		JPanel panel_BloodPressure = new JPanel();
		panel_BloodPressure.setBorder(
				new TitledBorder(null, "Blood Pressure", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_VitalsForm.add(panel_BloodPressure, "cell 0 0,alignx left,aligny center");
		panel_BloodPressure.setLayout(new MigLayout("", "[][grow][][][][grow]", "[grow][]"));

		// BP form

		JLabel lblBpSys = new JLabel("BP Sys/Dia");
		panel_BloodPressure.add(lblBpSys, "cell 0 0,alignx right");

		textField_BPS = new JTextField();
		panel_BloodPressure.add(textField_BPS, "cell 1 0,alignx left");
		textField_BPS.setColumns(5);

		JLabel label_Slash = new JLabel("/");
		panel_BloodPressure.add(label_Slash, "cell 2 0,alignx trailing");

		textField_BPD = new JTextField();
		panel_BloodPressure.add(textField_BPD, "cell 3 0,alignx left");
		textField_BPD.setColumns(5);

		lblBP = new JLabel(Vitals.MMHG);
		panel_BloodPressure.add(lblBP, "cell 4 0,alignx left");

		// BP radio buttons

		JPanel panel_BPUnit = new JPanel();
		panel_BPUnit.setBorder(new TitledBorder(null, "BP Unit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_BloodPressure.add(panel_BPUnit, "cell 5 0,alignx center,aligny center");

		JRadioButton rdbtnMmhg = new JRadioButton("mm/Hg");
		buttonGroupBP.add(rdbtnMmhg);
		panel_BPUnit.add(rdbtnMmhg);

		JRadioButton rdbtnPa = new JRadioButton("Pa");
		buttonGroupBP.add(rdbtnPa);
		panel_BPUnit.add(rdbtnPa);

		rdbtnMmhg.addActionListener(new BPListener());
		rdbtnPa.addActionListener(new BPListener());

		// Blood glucose

		JPanel panel_BloodGlucose = new JPanel();
		panel_BloodGlucose.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Blood Glucose", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_VitalsForm.add(panel_BloodGlucose, "cell 0 1,alignx left,aligny center");
		panel_BloodGlucose.setLayout(new MigLayout("", "[][][grow][][grow]", "[][grow][]"));

		JCheckBox chckbxFasting = new JCheckBox("Fasting");
		panel_BloodGlucose.add(chckbxFasting, "cell 0 1");

		JLabel lblBgLevel = new JLabel("BG Level");
		panel_BloodGlucose.add(lblBgLevel, "cell 1 1,alignx right");

		textField_BGLevel = new JTextField();
		panel_BloodGlucose.add(textField_BGLevel, "cell 2 1,alignx left");
		textField_BGLevel.setColumns(5);

		JLabel lblBGLevelUnit = new JLabel("mmol/L");
		panel_BloodGlucose.add(lblBGLevelUnit, "cell 3 1,alignx left");

		JPanel panel_BGLevelUnit = new JPanel();
		panel_BGLevelUnit.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"BG Level Unit", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_BloodGlucose.add(panel_BGLevelUnit, "cell 4 1,alignx center,aligny center");

		JRadioButton rdbtnMmoll = new JRadioButton("mmol/L");
		buttonGroupBloodGlucose.add(rdbtnMmoll);
		panel_BGLevelUnit.add(rdbtnMmoll);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("mg/dL");
		buttonGroupBloodGlucose.add(rdbtnNewRadioButton);
		panel_BGLevelUnit.add(rdbtnNewRadioButton);

		// O2 saturation

		JPanel panel_O2Saturation = new JPanel();
		panel_O2Saturation
				.setBorder(new TitledBorder(null, "O2 Saturation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_VitalsForm.add(panel_O2Saturation, "cell 0 2,alignx left,aligny center");
		panel_O2Saturation.setLayout(new MigLayout("", "[][][][grow]", "[][]"));

		JLabel lblOSaturation = new JLabel("O2 Saturation");
		panel_O2Saturation.add(lblOSaturation, "cell 0 0,alignx trailing");

		textField_O2Sat = new JTextField();
		panel_O2Saturation.add(textField_O2Sat, "cell 1 0,alignx left");
		textField_O2Sat.setColumns(5);

		JLabel label = new JLabel("%");
		panel_O2Saturation.add(label, "cell 2 0,alignx left");

		// Hemoglobin

		JPanel panel_Hemoglobin = new JPanel();
		panel_Hemoglobin
				.setBorder(new TitledBorder(null, "Hemogobin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_VitalsForm.add(panel_Hemoglobin, "cell 0 3,alignx left,aligny center");
		panel_Hemoglobin.setLayout(new MigLayout("", "[][grow]", "[][]"));

		JLabel lblHb = new JLabel("Hb");
		panel_Hemoglobin.add(lblHb, "cell 0 0,alignx trailing");

		textField_Hb = new JTextField();
		panel_Hemoglobin.add(textField_Hb, "flowx,cell 1 0,alignx left,aligny center");
		textField_Hb.setColumns(5);

		JLabel lblGmdl = new JLabel("gm/dL");
		panel_Hemoglobin.add(lblGmdl, "cell 1 0");

		// Height form

		JPanel panel_Height = new JPanel();
		panel_Height.setBorder(new TitledBorder(null, "Height", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_VitalsForm.add(panel_Height, "cell 0 4,alignx left,aligny center");
		panel_Height.setLayout(new MigLayout("", "[][grow][grow]", "[grow][][]"));

		JLabel lblHeight = new JLabel("Height");
		panel_Height.add(lblHeight, "cell 0 0,alignx right");

		textField_Ft = new JTextField();
		panel_Height.add(textField_Ft, "flowx,cell 1 0,alignx left");
		textField_Ft.setColumns(5);

		lblFt = new JLabel("ft");
		panel_Height.add(lblFt, "cell 1 0");

		textField_In = new JTextField();
		panel_Height.add(textField_In, "flowx,cell 1 1,alignx left");
		textField_In.setColumns(5);

		lblIn = new JLabel(Vitals.IN);
		panel_Height.add(lblIn, "cell 1 1");

		textField_Cm = new JTextField();
		textField_Cm.setEnabled(false);
		panel_Height.add(textField_Cm, "flowx,cell 1 2,alignx left");
		textField_Cm.setColumns(5);

		lblCm = new JLabel(Vitals.CM);
		panel_Height.add(lblCm, "cell 1 2");

		// Height radio buttons

		JPanel panel_HeightUnit = new JPanel();
		panel_HeightUnit
				.setBorder(new TitledBorder(null, "Height Unit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Height.add(panel_HeightUnit, "cell 2 0 1 3,alignx center,aligny center");

		JRadioButton rdbtnFtin = new JRadioButton(Vitals.FTIN);
		buttonGroupHeight.add(rdbtnFtin);
		panel_HeightUnit.add(rdbtnFtin);

		JRadioButton rdbtnIn = new JRadioButton(Vitals.IN);
		buttonGroupHeight.add(rdbtnIn);
		panel_HeightUnit.add(rdbtnIn);

		JRadioButton rdbtnCm = new JRadioButton(Vitals.CM);
		buttonGroupHeight.add(rdbtnCm);
		panel_HeightUnit.add(rdbtnCm);

		rdbtnFtin.setSelected(true);

		rdbtnFtin.addActionListener(new HeightListener());
		rdbtnIn.addActionListener(new HeightListener());
		rdbtnCm.addActionListener(new HeightListener());

		// Weight form

		JPanel panel_Weight = new JPanel();
		panel_Weight.setBorder(new TitledBorder(null, "Weight", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_VitalsForm.add(panel_Weight, "cell 0 5,alignx left,aligny center");
		panel_Weight.setLayout(new MigLayout("", "[][grow][grow]", "[grow]"));

		JLabel lblWeight = new JLabel("Weight");
		panel_Weight.add(lblWeight, "cell 0 0,alignx right");

		textField_Weight = new JTextField();
		panel_Weight.add(textField_Weight, "flowx,cell 1 0,alignx left");
		textField_Weight.setColumns(5);

		lblWeightUnit = new JLabel("lbs");
		panel_Weight.add(lblWeightUnit, "cell 1 0");

		// Weight radio buttons

		JPanel panel_WeightUnit = new JPanel();
		panel_WeightUnit
				.setBorder(new TitledBorder(null, "Weight Unit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Weight.add(panel_WeightUnit, "cell 2 0,alignx center,aligny center");

		JRadioButton rdbtnLbs = new JRadioButton("lbs");
		buttonGroupWeight.add(rdbtnLbs);
		panel_WeightUnit.add(rdbtnLbs);

		JRadioButton rdbtnKg = new JRadioButton("kg");
		buttonGroupWeight.add(rdbtnKg);
		panel_WeightUnit.add(rdbtnKg);

		rdbtnLbs.setSelected(true);

		rdbtnLbs.addActionListener(new WeightListener());
		rdbtnKg.addActionListener(new WeightListener());

		// Note form

		JPanel panel_Notes = new JPanel();
		panel_Notes.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Addtional Notes",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_VitalsForm.add(panel_Notes, "cell 0 6,grow");
		panel_Notes.setLayout(new MigLayout("", "[grow]", "[grow]"));

		textArea_Notes = new JTextArea();
		textArea_Notes.setLineWrap(true);
		textArea_Notes.setWrapStyleWord(true);
		panel_Notes.add(textArea_Notes, "cell 0 0,grow");

		// Comments Panel

		JPanel panel_Comments = new JPanel();
		panel_Comments.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Comments",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_VitalsForm.add(panel_Comments, "cell 0 7,growx,aligny center");
		panel_Comments.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));

		scrollPane_Comments = new JScrollPane();
		scrollPane_Comments.setEnabled(true);
		scrollPane_Comments.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_Comments.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_Comments.add(scrollPane_Comments, "cell 0 0,grow");

		panel_CommentForm = new JPanel();
		panel_CommentForm.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Append a Comment", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Comments.add(panel_CommentForm, "cell 0 1,grow");
		panel_CommentForm.setLayout(new BorderLayout(0, 0));

		panel_CommentForm.setVisible(false);

		JPanel panel_CommentScroll = new JPanel();
		panel_CommentForm.add(panel_CommentScroll, BorderLayout.CENTER);
		panel_CommentScroll.setLayout(new BorderLayout(0, 0));

		textArea_Comment = new JTextArea();
		panel_CommentScroll.add(textArea_Comment);

		JPanel panel_ButtonsCommentForm = new JPanel();
		panel_CommentForm.add(panel_ButtonsCommentForm, BorderLayout.SOUTH);

		btnAppendComment = new JButton("Append Comment");
		panel_ButtonsCommentForm.add(btnAppendComment);

		btnAppendCancel = new JButton("Cancel");
		panel_ButtonsCommentForm.add(btnAppendCancel);

		btnAppendComment.addActionListener(new AddCommentListener());
		btnAppendCancel.addActionListener(new CancelCommentListener());

		// Buttons at bottom

		JPanel panel_ConfirmCancel = new JPanel();
		add(panel_ConfirmCancel, BorderLayout.SOUTH);

		btnConfirm = new JButton("Confirm");
		panel_ConfirmCancel.add(btnConfirm);

		btnCancel = new JButton("Cancel");
		panel_ConfirmCancel.add(btnCancel);

		btnConfirm.addActionListener(new ConfirmVitalListener());
		btnCancel.addActionListener(new CancelVitalListener());

	}

}
