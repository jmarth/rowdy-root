package views;

import javax.swing.JPanel;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.BorderLayout;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.GatewayException;
import database.VitalsTableGateway;
import models.Vitals;
import models.VitalsList;
import models.Patient;
import models.Tabs;

import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class VitalsTabNewVitalsView2 extends JPanel {

	private static final Logger log = LogManager.getLogger(VitalsTabNewVitalsView2.class);

	JPanel oldPanel;
	JTable vitalsTable;

	private Vitals vitals;
	private Patient patient;
	private VitalsTableGateway vtg;

	private JTextField textField_BPSys;
	private JTextField textField_BPDias;

	private JTextField textField_HeightFt;
	private JTextField textField_HeightIn;
	private JTextField textField_HeightCm;

	private JTextField textField_Weight;

	private ButtonGroup bpButtonGroup = new ButtonGroup();
	private ButtonGroup heightButtonGroup = new ButtonGroup();
	private ButtonGroup weightButtonGroup = new ButtonGroup();

	private String bPUnit;
	private String heightUnit;
	private String weightUnit;
	private JLabel lblBloodPressuresys;
	private JLabel lblBPSysUnit_1;
	private JLabel lblWeightUnit;
	private JTextArea textArea_Notes;
	
	private JTextArea textAreaAppend;
	private JPanel panelComments;
	private JPanel panel_CommentButtons;
	
	private VitalsList vl;

	private List<Vitals> vitalsList;

	// private String heightUnit;

	/**
	 * Create the panel.
	 */
	public VitalsTabNewVitalsView2(final JTabbedPane tabbedPane, Patient patient, JPanel vitalsPanel,
			VitalsTableGateway gateway, JTable vitalsTable, List<Vitals> vitalsList, VitalsList vl, Vitals vitals,
			Boolean exists) {

		this.patient = patient;
		this.vitals = vitals;
		this.vtg = gateway;
		this.vitalsTable = vitalsTable;
		this.vl = vl;
		this.vitalsList = vitalsList;
		oldPanel = vitalsPanel;

		/*
		 * Try to connect to DB through VitalsTableGateway Set the gateway of
		 * the VitalsList Load Vitals into the VitalsList
		 */

		/*
		 * try {
		 * 
		 * vtg = new VitalsTableGatewaySQLite();
		 * 
		 * } catch (GatewayException e) {
		 * 
		 * System.out.println("Could not connect to DB"); e.printStackTrace();
		 * 
		 * } catch (IOException e) {
		 * 
		 * System.out.println("Could not connect to DB"); e.printStackTrace(); }
		 */
		
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////
		
		createExistingView(tabbedPane, patient, vitalsPanel, vtg, vitals, vitalsTable);
		if (exists) {

			// System.out.println("exists: patientid = "+patient.getId()+"");

			createExistingView(tabbedPane, patient, vitalsPanel, vtg, vitals, vitalsTable);

		} else {

			createView(tabbedPane, patient, vitalsPanel, vtg, vitalsTable);
		}

	}

	/**
	 * Called from ActionListener for Cancel button
	 * 
	 * @param tabbedPane
	 *            JTabbedPane to alter
	 * @param oldPanel
	 *            Panel to switch back to
	 */
	public void cancel(JTabbedPane tabbedPane, JPanel oldPanel) {
		int index = tabbedPane.indexOfTab(Tabs.vitals);
		tabbedPane.setComponentAt(index, null);
		tabbedPane.setComponentAt(index, oldPanel);
	}

	/**
	 * Save Vitals to database for patient
	 * 
	 * @param patient
	 *            Patient that Vitals belongs to
	 * @param vtg
	 *            VitalsTableGateway
	 * @param tabbedPane
	 *            JTabbedPane to change when done saving
	 * @param oldPanel
	 *            JPanel to change back to when done saving
	 */
	public void save(Patient patient, VitalsTableGateway vtg, JTabbedPane tabbedPane, JPanel oldPanel,
			JTable vitalsTable) {
		StringBuilder sb = new StringBuilder(128);

		/**
		 * Create new Vitals object with correct parameters Insert the vitals to
		 * the DB through the Gateway
		 */

		Vitals vitals = new Vitals(0, patient.getId(), Float.parseFloat(textField_BPSys.getText()),
				Float.parseFloat(textField_BPDias.getText()), getLblBPSysUnit().getText(),
				Integer.parseInt(((textField_HeightFt.getText().equals("")) ? "-2"
						: textField_HeightFt.getText())),
				Integer.parseInt(((textField_HeightIn.getText().equals("")) ? "-2"
						: textField_HeightIn.getText())),
				Integer.parseInt(((textField_HeightCm.getText().equals("")) ? "-2"
						: textField_HeightCm.getText())),
				getSelectedButtonText(heightButtonGroup),
				Float.parseFloat(((textField_Weight.getText().equals("")) ? "-2"
						: textField_Weight.getText())),
				getLblWeightUnit().getText(), textArea_Notes.getText());
		try {
			long vid = vtg.insertVitals(vitals);
			vitals.setId(vid);

		} catch (GatewayException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Builds a string if ft/inches in form X'Y" otherwise, just an int
		 */

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

		// Change the panel back to vitals table
		// NEED TO FIGURE HOW TO UPDATE TABLE WHEN SWITCHING BACK TO SHOW NEW
		// ALLERGY

		// Add the vitals to the JTable
		// Get model of VitalsTable in order to add rows
		DefaultTableModel model = (DefaultTableModel) vitalsTable.getModel();

		// Add row
		model.addRow(new Object[] { vitals.getBps(), vitals.getBps(), vitals.getBpUnit(),
				(isString ? height_ftin_String : heightInt), vitals.getHUnit(), vitals.getWeight(), vitals.getWUnit(),
				vitals.getNotes() });

		vitalsList.add(vitals);

		int index = tabbedPane.indexOfTab(Tabs.vitals);

		tabbedPane.setComponentAt(index, null);
		tabbedPane.setComponentAt(index, oldPanel);
	}

	/**
	 * Save Vitals to database for patient
	 * 
	 * @param patient
	 *            Patient that Vitals belongs to
	 * @param vtg
	 *            VitalsTableGateway
	 * @param tabbedPane
	 *            JTabbedPane to change when done saving
	 * @param oldPanel
	 *            JPanel to change back to when done saving
	 */
	public void update(Patient patient, VitalsTableGateway vtg, JTabbedPane tabbedPane, JPanel oldPanel,
			JTable vitalsTable, long vid) {
		StringBuilder sb = new StringBuilder(128);

		/**
		 * Create new Vitals object with correct parameters Insert the vitals to
		 * the DB through the Gateway
		 */

		// !!!!!!!!!!! probably don't need to make another vital, could get it
		// from the model

		Vitals vitals = new Vitals(vid, patient.getId(), Float.parseFloat(textField_BPSys.getText()),
				Float.parseFloat(textField_BPDias.getText()), getLblBPSysUnit().getText(),
				Integer.parseInt(((textField_HeightFt.getText().equals("")) ? "-2"
						: textField_HeightFt.getText())),
				Integer.parseInt(((textField_HeightIn.getText().equals("")) ? "-2"
						: textField_HeightIn.getText())),
				Integer.parseInt(((textField_HeightCm.getText().equals("")) ? "-2"
						: textField_HeightCm.getText())),
				getSelectedButtonText(heightButtonGroup),
				Float.parseFloat(((textField_Weight.getText().equals("")) ? "-2"
						: textField_Weight.getText())),
				getLblWeightUnit().getText(), textArea_Notes.getText());

		try {

			long vitalid = vtg.updateVitals(vitals);
			// vitals.setId(vitalid);
			vitalsList = vl.getVitalsList();

		} catch (GatewayException e) {
			e.printStackTrace();
		}

		/*
		 * Builds a string if ft/inches in form X'Y" otherwise, just an int
		 */

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

		// JTable columns:

		// "BPS", "BPD", "BP Unit", "Height", "Height Unit", "Weight", "Weight
		// Unit", "Notes"

		// Update row in JTable
		int selectedRow = vitalsTable.getSelectedRow();
		vitalsTable.setValueAt(vitals.getBps(), selectedRow, 0);
		vitalsTable.setValueAt(vitals.getBpd(), selectedRow, 1);
		vitalsTable.setValueAt(vitals.getBpUnit(), selectedRow, 2);
		vitalsTable.setValueAt((isString ? height_ftin_String : heightInt), selectedRow, 3);
		vitalsTable.setValueAt(vitals.getHUnit(), selectedRow, 4);
		vitalsTable.setValueAt(vitals.getWeight(), selectedRow, 5);
		vitalsTable.setValueAt(vitals.getWUnit(), selectedRow, 6);
		vitalsTable.setValueAt(vitals.getNotes(), selectedRow, 7);

		// Update the Allergy in the allergyList
		vitalsList.set(selectedRow, vitals);

		// Change the panel back to vitals table
		// NEED TO FIGURE HOW TO UPDATE TABLE WHEN SWITCHING BACK TO SHOW NEW
		// ALLERGY

		// Add the vitals to the JTable
		// Get model of VitalsTable in order to add rows

		// DefaultTableModel dtm = (DefaultTableModel) vitalsTable.getModel();

		int index = tabbedPane.indexOfTab(Tabs.vitals);

		// swap the center component out for the old view
		tabbedPane.setComponentAt(index, null);
		tabbedPane.setComponentAt(index, oldPanel);
	}

	public void createView(final JTabbedPane tabbedPane, final Patient patient, JPanel vitalsPanel,
			final VitalsTableGateway vtg, final JTable vitalsTable) {

		setLayout(new BorderLayout(0, 0));

		JPanel panel_VitalsForm = new JPanel();
		add(panel_VitalsForm, BorderLayout.CENTER);
		GridBagLayout gbl_panel_VitalsForm = new GridBagLayout();
		gbl_panel_VitalsForm.columnWidths = new int[] { 0, 0, 48, 0, 0, 0 };
		gbl_panel_VitalsForm.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_VitalsForm.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_VitalsForm.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0, Double.MIN_VALUE };
		panel_VitalsForm.setLayout(gbl_panel_VitalsForm);

		JLabel lblVitals = new JLabel("Vitals");
		lblVitals.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblVitals = new GridBagConstraints();
		gbc_lblVitals.insets = new Insets(0, 0, 5, 5);
		gbc_lblVitals.gridx = 1;
		gbc_lblVitals.gridy = 1;
		panel_VitalsForm.add(lblVitals, gbc_lblVitals);

		lblBloodPressuresys = new JLabel("BP(Sys):");
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

		lblBPSysUnit_1 = new JLabel(Vitals.MMHG);
		GridBagConstraints gbc_lblBPSysUnit_1 = new GridBagConstraints();
		gbc_lblBPSysUnit_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblBPSysUnit_1.gridx = 3;
		gbc_lblBPSysUnit_1.gridy = 3;
		panel_VitalsForm.add(lblBPSysUnit_1, gbc_lblBPSysUnit_1);

		JPanel panel_BPGroup = new JPanel();
		GridBagConstraints gbc_panel_BPGroup = new GridBagConstraints();
		gbc_panel_BPGroup.insets = new Insets(0, 0, 5, 0);
		gbc_panel_BPGroup.fill = GridBagConstraints.VERTICAL;
		gbc_panel_BPGroup.gridx = 4;
		gbc_panel_BPGroup.gridy = 3;
		panel_VitalsForm.add(panel_BPGroup, gbc_panel_BPGroup);

		JRadioButton rdbtnMmhg = new JRadioButton(Vitals.MMHG);

		rdbtnMmhg.setSelected(true);
		panel_BPGroup.add(rdbtnMmhg);

		JRadioButton rdbtnPa = new JRadioButton(Vitals.PA);

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
		gbc_textField_BPDias.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_BPDias.gridx = 2;
		gbc_textField_BPDias.gridy = 4;
		panel_VitalsForm.add(textField_BPDias, gbc_textField_BPDias);
		textField_BPDias.setColumns(10);

		JLabel lblBPDiasUnit = new JLabel(Vitals.MMHG);
		GridBagConstraints gbc_lblBPDiasUnit = new GridBagConstraints();
		gbc_lblBPDiasUnit.insets = new Insets(0, 0, 5, 5);
		gbc_lblBPDiasUnit.gridx = 3;
		gbc_lblBPDiasUnit.gridy = 4;
		panel_VitalsForm.add(lblBPDiasUnit, gbc_lblBPDiasUnit);

		bpButtonGroup.add(rdbtnMmhg);
		bpButtonGroup.add(rdbtnPa);
		rdbtnMmhg.addActionListener(new BPListener(lblBPSysUnit_1, lblBPDiasUnit));
		rdbtnPa.addActionListener(new BPListener(lblBPSysUnit_1, lblBPDiasUnit));

		JLabel lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.EAST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 6;
		panel_VitalsForm.add(lblHeight, gbc_lblHeight);

		textField_HeightFt = new JTextField();
		GridBagConstraints gbc_textField_HeightFt = new GridBagConstraints();
		gbc_textField_HeightFt.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_HeightFt.insets = new Insets(0, 0, 5, 5);
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

		JRadioButton rdbtnFeetInches = new JRadioButton(Vitals.FTIN);
		rdbtnFeetInches.setSelected(true);
		panel_HeightGroup.add(rdbtnFeetInches);

		JRadioButton rdbtnInches = new JRadioButton(Vitals.IN);
		panel_HeightGroup.add(rdbtnInches);

		JRadioButton rdbtnCm = new JRadioButton(Vitals.CM);
		panel_HeightGroup.add(rdbtnCm);

		textField_HeightIn = new JTextField();
		GridBagConstraints gbc_textField_HeightIn = new GridBagConstraints();
		gbc_textField_HeightIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_HeightIn.insets = new Insets(0, 0, 5, 5);
		gbc_textField_HeightIn.gridx = 2;
		gbc_textField_HeightIn.gridy = 7;
		panel_VitalsForm.add(textField_HeightIn, gbc_textField_HeightIn);
		textField_HeightIn.setColumns(10);

		JLabel lblHeightIn = new JLabel(Vitals.IN);
		GridBagConstraints gbc_lblHeightIn = new GridBagConstraints();
		gbc_lblHeightIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeightIn.gridx = 3;
		gbc_lblHeightIn.gridy = 7;
		panel_VitalsForm.add(lblHeightIn, gbc_lblHeightIn);

		textField_HeightCm = new JTextField();
		GridBagConstraints gbc_textField_HeightCm = new GridBagConstraints();
		gbc_textField_HeightCm.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_HeightCm.insets = new Insets(0, 0, 5, 5);
		gbc_textField_HeightCm.gridx = 2;
		gbc_textField_HeightCm.gridy = 8;
		panel_VitalsForm.add(textField_HeightCm, gbc_textField_HeightCm);
		textField_HeightCm.setColumns(10);

		textField_HeightCm.setEnabled(false);

		JLabel lblHeightCm = new JLabel(Vitals.CM);
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
		gbc_textField_Weight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Weight.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Weight.gridx = 2;
		gbc_textField_Weight.gridy = 10;
		panel_VitalsForm.add(textField_Weight, gbc_textField_Weight);
		textField_Weight.setColumns(10);

		lblWeightUnit = new JLabel(Vitals.LBS);
		GridBagConstraints gbc_lblWeightUnit_1 = new GridBagConstraints();
		gbc_lblWeightUnit_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeightUnit_1.gridx = 3;
		gbc_lblWeightUnit_1.gridy = 10;
		panel_VitalsForm.add(lblWeightUnit, gbc_lblWeightUnit_1);

		JPanel panel_WeightGroup = new JPanel();
		GridBagConstraints gbc_panel_WeightGroup = new GridBagConstraints();
		gbc_panel_WeightGroup.insets = new Insets(0, 0, 5, 0);
		gbc_panel_WeightGroup.gridx = 4;
		gbc_panel_WeightGroup.gridy = 10;
		panel_VitalsForm.add(panel_WeightGroup, gbc_panel_WeightGroup);

		JRadioButton rdbtnLbs = new JRadioButton(Vitals.LBS);
		rdbtnLbs.setSelected(true);
		panel_WeightGroup.add(rdbtnLbs);

		JRadioButton rdbtnKg = new JRadioButton(Vitals.KG);
		panel_WeightGroup.add(rdbtnKg);

		weightButtonGroup.add(rdbtnLbs);
		weightButtonGroup.add(rdbtnKg);
		rdbtnLbs.addActionListener(new WeightListener(lblWeightUnit));
		rdbtnKg.addActionListener(new WeightListener(lblWeightUnit));

		JLabel lblNotes = new JLabel("Notes:");
		GridBagConstraints gbc_lblNotes = new GridBagConstraints();
		gbc_lblNotes.anchor = GridBagConstraints.EAST;
		gbc_lblNotes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNotes.gridx = 1;
		gbc_lblNotes.gridy = 12;
		panel_VitalsForm.add(lblNotes, gbc_lblNotes);

		textArea_Notes = new JTextArea();
		textArea_Notes.setRows(4);
		GridBagConstraints gbc_textArea_Notes = new GridBagConstraints();
		gbc_textArea_Notes.fill = GridBagConstraints.BOTH;
		gbc_textArea_Notes.gridheight = 2;
		gbc_textArea_Notes.gridwidth = 3;
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
				save(patient, vtg, tabbedPane, oldPanel, vitalsTable);
			}
		});
		panel_Buttons.add(btnSave);
	}

	public void createExistingView(final JTabbedPane tabbedPane, final Patient patient, JPanel vitalsPanel,
			final VitalsTableGateway vtg, Vitals v, final JTable vitalsTable) {

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 392, 0 };
		gbl_panel.rowHeights = new int[] { 0, 434, 0, 33, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JButton btnAppend = new JButton("Append");
		GridBagConstraints gbc_btnAppend = new GridBagConstraints();
		gbc_btnAppend.fill = GridBagConstraints.VERTICAL;
		gbc_btnAppend.insets = new Insets(0, 0, 5, 0);
		gbc_btnAppend.gridx = 0;
		gbc_btnAppend.gridy = 0;
		panel.add(btnAppend, gbc_btnAppend);
		
		

		JPanel panel_VitalsForm = new JPanel();
		GridBagConstraints gbc_panel_VitalsForm = new GridBagConstraints();
		gbc_panel_VitalsForm.fill = GridBagConstraints.BOTH;
		gbc_panel_VitalsForm.insets = new Insets(0, 0, 5, 0);
		gbc_panel_VitalsForm.gridx = 0;
		gbc_panel_VitalsForm.gridy = 1;
		panel.add(panel_VitalsForm, gbc_panel_VitalsForm);
		GridBagLayout gbl_panel_VitalsForm = new GridBagLayout();
		gbl_panel_VitalsForm.columnWidths = new int[] { 0, 0, 48, 0, 0, 0 };
		gbl_panel_VitalsForm.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_VitalsForm.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_VitalsForm.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0, Double.MIN_VALUE };
		panel_VitalsForm.setLayout(gbl_panel_VitalsForm);

		JLabel lblVitals = new JLabel("Vitals");
		lblVitals.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblVitals = new GridBagConstraints();
		gbc_lblVitals.insets = new Insets(0, 0, 5, 5);
		gbc_lblVitals.gridx = 1;
		gbc_lblVitals.gridy = 1;
		panel_VitalsForm.add(lblVitals, gbc_lblVitals);

		// BP

		lblBloodPressuresys = new JLabel("BP(Sys):");
		GridBagConstraints gbc_lblBloodPressuresys = new GridBagConstraints();
		gbc_lblBloodPressuresys.anchor = GridBagConstraints.EAST;
		gbc_lblBloodPressuresys.insets = new Insets(0, 0, 5, 5);
		gbc_lblBloodPressuresys.gridx = 1;
		gbc_lblBloodPressuresys.gridy = 3;
		panel_VitalsForm.add(lblBloodPressuresys, gbc_lblBloodPressuresys);

		// set bps text
		textField_BPSys = new JTextField();
		textField_BPSys.setText(String.valueOf(v.getBps()));
		GridBagConstraints gbc_textField_BPSys = new GridBagConstraints();
		gbc_textField_BPSys.insets = new Insets(0, 0, 5, 5);
		gbc_textField_BPSys.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_BPSys.gridx = 2;
		gbc_textField_BPSys.gridy = 3;
		panel_VitalsForm.add(textField_BPSys, gbc_textField_BPSys);
		textField_BPSys.setColumns(10);

		// set bps Unit
		lblBPSysUnit_1 = new JLabel(v.getBpUnit());
		GridBagConstraints gbc_lblBPSysUnit_1 = new GridBagConstraints();
		gbc_lblBPSysUnit_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblBPSysUnit_1.gridx = 3;
		gbc_lblBPSysUnit_1.gridy = 3;
		panel_VitalsForm.add(lblBPSysUnit_1, gbc_lblBPSysUnit_1);

		JPanel panel_BPGroup = new JPanel();
		GridBagConstraints gbc_panel_BPGroup = new GridBagConstraints();
		gbc_panel_BPGroup.insets = new Insets(0, 0, 5, 0);
		gbc_panel_BPGroup.fill = GridBagConstraints.VERTICAL;
		gbc_panel_BPGroup.gridx = 4;
		gbc_panel_BPGroup.gridy = 3;
		panel_VitalsForm.add(panel_BPGroup, gbc_panel_BPGroup);

		JRadioButton rdbtnMmhg = new JRadioButton(Vitals.MMHG);
		panel_BPGroup.add(rdbtnMmhg);
		JRadioButton rdbtnPa = new JRadioButton(Vitals.PA);
		panel_BPGroup.add(rdbtnPa);

		JLabel lblBloodPressuredias = new JLabel("BP (Dias):");
		GridBagConstraints gbc_lblBloodPressuredias = new GridBagConstraints();
		gbc_lblBloodPressuredias.anchor = GridBagConstraints.EAST;
		gbc_lblBloodPressuredias.insets = new Insets(0, 0, 5, 5);
		gbc_lblBloodPressuredias.gridx = 1;
		gbc_lblBloodPressuredias.gridy = 4;
		panel_VitalsForm.add(lblBloodPressuredias, gbc_lblBloodPressuredias);

		// set bpd text
		textField_BPDias = new JTextField();
		textField_BPDias.setText(String.valueOf(v.getBpd()));
		GridBagConstraints gbc_textField_BPDias = new GridBagConstraints();
		gbc_textField_BPDias.insets = new Insets(0, 0, 5, 5);
		gbc_textField_BPDias.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_BPDias.gridx = 2;
		gbc_textField_BPDias.gridy = 4;
		panel_VitalsForm.add(textField_BPDias, gbc_textField_BPDias);
		textField_BPDias.setColumns(10);

		// set bpd unit
		JLabel lblBPDiasUnit = new JLabel(v.getBpUnit());
		GridBagConstraints gbc_lblBPDiasUnit = new GridBagConstraints();
		gbc_lblBPDiasUnit.insets = new Insets(0, 0, 5, 5);
		gbc_lblBPDiasUnit.gridx = 3;
		gbc_lblBPDiasUnit.gridy = 4;
		panel_VitalsForm.add(lblBPDiasUnit, gbc_lblBPDiasUnit);

		bpButtonGroup.add(rdbtnMmhg);
		bpButtonGroup.add(rdbtnPa);
		rdbtnMmhg.addActionListener(new BPListener(lblBPSysUnit_1, lblBPDiasUnit));
		rdbtnPa.addActionListener(new BPListener(lblBPSysUnit_1, lblBPDiasUnit));

		// HEIGHT

		JLabel lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.EAST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 6;
		panel_VitalsForm.add(lblHeight, gbc_lblHeight);

		textField_HeightFt = new JTextField();
		GridBagConstraints gbc_textField_HeightFt = new GridBagConstraints();
		gbc_textField_HeightFt.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_HeightFt.insets = new Insets(0, 0, 5, 5);
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

		JRadioButton rdbtnFeetInches = new JRadioButton(Vitals.FTIN);
		rdbtnFeetInches.setSelected(true);
		panel_HeightGroup.add(rdbtnFeetInches);

		JRadioButton rdbtnInches = new JRadioButton(Vitals.IN);
		panel_HeightGroup.add(rdbtnInches);

		JRadioButton rdbtnCm = new JRadioButton(Vitals.CM);
		panel_HeightGroup.add(rdbtnCm);

		rdbtnFeetInches.addActionListener(new HeightListener());
		rdbtnInches.addActionListener(new HeightListener());
		rdbtnCm.addActionListener(new HeightListener());

		heightButtonGroup.add(rdbtnFeetInches);
		heightButtonGroup.add(rdbtnInches);
		heightButtonGroup.add(rdbtnCm);

		textField_HeightIn = new JTextField();
		GridBagConstraints gbc_textField_HeightIn = new GridBagConstraints();
		gbc_textField_HeightIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_HeightIn.insets = new Insets(0, 0, 5, 5);
		gbc_textField_HeightIn.gridx = 2;
		gbc_textField_HeightIn.gridy = 7;
		panel_VitalsForm.add(textField_HeightIn, gbc_textField_HeightIn);
		textField_HeightIn.setColumns(10);

		JLabel lblHeightIn = new JLabel(Vitals.IN);
		GridBagConstraints gbc_lblHeightIn = new GridBagConstraints();
		gbc_lblHeightIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeightIn.gridx = 3;
		gbc_lblHeightIn.gridy = 7;
		panel_VitalsForm.add(lblHeightIn, gbc_lblHeightIn);

		textField_HeightCm = new JTextField();
		GridBagConstraints gbc_textField_HeightCm = new GridBagConstraints();
		gbc_textField_HeightCm.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_HeightCm.insets = new Insets(0, 0, 5, 5);
		gbc_textField_HeightCm.gridx = 2;
		gbc_textField_HeightCm.gridy = 8;
		panel_VitalsForm.add(textField_HeightCm, gbc_textField_HeightCm);
		textField_HeightCm.setColumns(10);

		// textField_HeightCm.setEnabled(false);

		JLabel lblHeightCm = new JLabel(Vitals.CM);
		GridBagConstraints gbc_lblHeightCm = new GridBagConstraints();
		gbc_lblHeightCm.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeightCm.gridx = 3;
		gbc_lblHeightCm.gridy = 8;
		panel_VitalsForm.add(lblHeightCm, gbc_lblHeightCm);

		// WEIGHT

		JLabel lblWeight = new JLabel("Weight:");
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.anchor = GridBagConstraints.EAST;
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 1;
		gbc_lblWeight.gridy = 10;
		panel_VitalsForm.add(lblWeight, gbc_lblWeight);

		// set weight
		textField_Weight = new JTextField();
		textField_Weight.setText(String.valueOf(v.getWeight()));
		GridBagConstraints gbc_textField_Weight = new GridBagConstraints();
		gbc_textField_Weight.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Weight.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Weight.gridx = 2;
		gbc_textField_Weight.gridy = 10;
		panel_VitalsForm.add(textField_Weight, gbc_textField_Weight);
		textField_Weight.setColumns(10);

		// set what weight is selected
		// set the weight radio button
		lblWeightUnit = new JLabel(Vitals.LBS);
		GridBagConstraints gbc_lblWeightUnit_1 = new GridBagConstraints();
		gbc_lblWeightUnit_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeightUnit_1.gridx = 3;
		gbc_lblWeightUnit_1.gridy = 10;
		panel_VitalsForm.add(lblWeightUnit, gbc_lblWeightUnit_1);

		JPanel panel_WeightGroup = new JPanel();
		GridBagConstraints gbc_panel_WeightGroup = new GridBagConstraints();
		gbc_panel_WeightGroup.insets = new Insets(0, 0, 5, 0);
		gbc_panel_WeightGroup.gridx = 4;
		gbc_panel_WeightGroup.gridy = 10;
		panel_VitalsForm.add(panel_WeightGroup, gbc_panel_WeightGroup);

		// weight radio button group

		JRadioButton rdbtnLbs = new JRadioButton(Vitals.LBS);
		panel_WeightGroup.add(rdbtnLbs);

		JRadioButton rdbtnKg = new JRadioButton(Vitals.KG);
		panel_WeightGroup.add(rdbtnKg);

		weightButtonGroup.add(rdbtnLbs);
		rdbtnLbs.addActionListener(new WeightListener(lblWeightUnit));
		weightButtonGroup.add(rdbtnKg);
		rdbtnKg.addActionListener(new WeightListener(lblWeightUnit));

		JLabel lblNotes = new JLabel("Notes:");
		GridBagConstraints gbc_lblNotes = new GridBagConstraints();
		gbc_lblNotes.anchor = GridBagConstraints.EAST;
		gbc_lblNotes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNotes.gridx = 1;
		gbc_lblNotes.gridy = 12;
		panel_VitalsForm.add(lblNotes, gbc_lblNotes);

		// set notes

		textArea_Notes = new JTextArea();
		textArea_Notes.setRows(4);
		textArea_Notes.setText(v.getNotes());
		GridBagConstraints gbc_textArea_Notes = new GridBagConstraints();
		gbc_textArea_Notes.fill = GridBagConstraints.BOTH;
		gbc_textArea_Notes.gridheight = 2;
		gbc_textArea_Notes.gridwidth = 3;
		gbc_textArea_Notes.gridx = 2;
		gbc_textArea_Notes.gridy = 12;
		panel_VitalsForm.add(textArea_Notes, gbc_textArea_Notes);
		
		JPanel panel_CommentsMain = new JPanel();
		panel_CommentsMain.setBorder(new TitledBorder(null, "Comments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_CommentsMain = new GridBagConstraints();
		gbc_panel_CommentsMain.fill = GridBagConstraints.BOTH;
		gbc_panel_CommentsMain.insets = new Insets(0, 0, 5, 0);
		gbc_panel_CommentsMain.gridx = 0;
		gbc_panel_CommentsMain.gridy = 2;
		panel.add(panel_CommentsMain, gbc_panel_CommentsMain);
		GridBagLayout gbl_panel_CommentsMain = new GridBagLayout();
		gbl_panel_CommentsMain.columnWidths = new int[]{392, 0};
		gbl_panel_CommentsMain.rowHeights = new int[]{32, 0, 0};
		gbl_panel_CommentsMain.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_CommentsMain.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_CommentsMain.setLayout(gbl_panel_CommentsMain);
		
		JScrollPane scrollPanelAppend = new JScrollPane();
		GridBagConstraints gbc_scrollPanelAppend = new GridBagConstraints();
		gbc_scrollPanelAppend.fill = GridBagConstraints.BOTH;
		gbc_scrollPanelAppend.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPanelAppend.gridx = 0;
		gbc_scrollPanelAppend.gridy = 0;
		panel_CommentsMain.add(scrollPanelAppend, gbc_scrollPanelAppend);
		scrollPanelAppend.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel panelAppend = new JPanel();
		scrollPanelAppend.setViewportView(panelAppend);
		GridBagLayout gbl_panelAppend = new GridBagLayout();
		gbl_panelAppend.columnWidths = new int[] { 392, 0 };
		gbl_panelAppend.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelAppend.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelAppend.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		panelAppend.setLayout(gbl_panelAppend);
		
		textAreaAppend = new JTextArea();
		textAreaAppend.setWrapStyleWord(true);
		textAreaAppend.setLineWrap(true);
		GridBagConstraints gbc_textAreaAppend = new GridBagConstraints();
		gbc_textAreaAppend.insets = new Insets(0, 0, 5, 0);
		gbc_textAreaAppend.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAreaAppend.gridx = 0;
		gbc_textAreaAppend.gridy = 0;
		panelAppend.add(textAreaAppend, gbc_textAreaAppend);
		
		textAreaAppend.setVisible(false);

		// comments
		
		
		panelComments = new JPanel();
		GridBagConstraints gbc_panelComments = new GridBagConstraints();
		gbc_panelComments.fill = GridBagConstraints.BOTH;
		gbc_panelComments.gridx = 0;
		gbc_panelComments.gridy = 1;
		panelAppend.add(panelComments, gbc_panelComments);
		
		panelComments.setLayout(new BoxLayout(panelComments, BoxLayout.Y_AXIS));
		
		// ADD NEW LABELS FROM LIST HERE
		
		JLabel lblNewLabel = new JLabel("New label");
		panelComments.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		panelComments.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panelComments.add(lblNewLabel_1);
		
		JSeparator separator_1 = new JSeparator();
		panelComments.add(separator_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panelComments.add(lblNewLabel_2);
		
		JSeparator separator_2 = new JSeparator();
		panelComments.add(separator_2);
		
		JLabel label = new JLabel("New label");
		panelComments.add(label);
		
		JSeparator separator_3 = new JSeparator();
		panelComments.add(separator_3);
		
		JLabel label_1 = new JLabel("New label");
		panelComments.add(label_1);
		
		JSeparator separator_4 = new JSeparator();
		panelComments.add(separator_4);
		
		JLabel label_2 = new JLabel("New label");
		panelComments.add(label_2);
		
		panel_CommentButtons = new JPanel();
		GridBagConstraints gbc_panel_CommentButtons = new GridBagConstraints();
		gbc_panel_CommentButtons.fill = GridBagConstraints.BOTH;
		gbc_panel_CommentButtons.gridx = 0;
		gbc_panel_CommentButtons.gridy = 1;
		panel_CommentsMain.add(panel_CommentButtons, gbc_panel_CommentButtons);
		
		JButton btnCancelComment = new JButton("Cancel");
		panel_CommentButtons.add(btnCancelComment);
		
		JButton btnAddComment = new JButton("Add Comment");
		panel_CommentButtons.add(btnAddComment);

		panel_CommentButtons.setVisible(false);
		
		JPanel panel_Buttons = new JPanel();
		GridBagConstraints gbc_panel_Buttons = new GridBagConstraints();
		gbc_panel_Buttons.fill = GridBagConstraints.BOTH;
		gbc_panel_Buttons.gridx = 0;
		gbc_panel_Buttons.gridy = 3;
		panel.add(panel_Buttons, gbc_panel_Buttons);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancel(tabbedPane, oldPanel);
			}
		});
		panel_Buttons.add(btnCancel);

		JButton btnUpdateVital = new JButton("Update Vital");
		btnUpdateVital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(patient, vtg, tabbedPane, oldPanel, vitalsTable, vitals.getId());
			}
		});
		panel_Buttons.add(btnUpdateVital);
		
		btnAppend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// hide panelComment
				panelComments.setVisible(false);
				// and Append Button
				
				
				// show textAreaAppend and confirm/cancel buttons
				textAreaAppend.setVisible(true);
				panel_CommentButtons.setVisible(true);
				
				
				
				/*
				editPanel.add(commentField, BorderLayout.CENTER);
				editPanel.add(saveButton, BorderLayout.SOUTH);
				
				BorderLayout layout = (BorderLayout) masterPanel.getLayout();
				masterPanel.remove(layout.getLayoutComponent(BorderLayout.SOUTH));
				masterPanel.add(editPanel, BorderLayout.SOUTH);
				
				masterPanel.validate();
				masterPanel.repaint();
				*/
			}
		});
		
		btnCancelComment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// hide panelComment
				panelComments.setVisible(true);
				// and Append Button
				
				
				// show textAreaAppend and confirm/cancel buttons
				textAreaAppend.setVisible(false);
				panel_CommentButtons.setVisible(false);
				
				
				
				/*
				editPanel.add(commentField, BorderLayout.CENTER);
				editPanel.add(saveButton, BorderLayout.SOUTH);
				
				BorderLayout layout = (BorderLayout) masterPanel.getLayout();
				masterPanel.remove(layout.getLayoutComponent(BorderLayout.SOUTH));
				masterPanel.add(editPanel, BorderLayout.SOUTH);
				
				masterPanel.validate();
				masterPanel.repaint();
				*/
			}
		});

		// set what BP unit button is selected

		if (v.getBpUnit().equals(Vitals.MMHG)) {

			rdbtnMmhg.setSelected(true);

		} else {

			rdbtnPa.setSelected(true);
		}

		// set text, which fields are enabled, and radio button for height

		if (v.getHUnit().equals(Vitals.FTIN)) {
			rdbtnFeetInches.setSelected(true);
			textField_HeightFt.setText(String.valueOf(v.getHFeet()));
			textField_HeightIn.setText(String.valueOf(v.getHInches()));
			textField_HeightCm.setEnabled(false);
		} else if (v.getHUnit().equals(Vitals.IN)) {
			rdbtnInches.setSelected(true);
			textField_HeightIn.setText(String.valueOf(v.getHInches()));
			textField_HeightFt.setEnabled(false);
			textField_HeightCm.setEnabled(false);
		} else {
			rdbtnCm.setSelected(true);
			textField_HeightCm.setText(String.valueOf(v.getHCm()));
			textField_HeightFt.setEnabled(false);
			textField_HeightIn.setEnabled(false);
		}

		// set weight radio button selection

		if (v.getWUnit().equals(Vitals.LBS)) {
			rdbtnLbs.setSelected(true);
		} else {
			rdbtnKg.setSelected(true);
		}
	}

	private class BPListener implements ActionListener {
		JLabel lblBPSysUnit, lblBPDiasUnit;

		public BPListener(JLabel lblBPSysUnit, JLabel lblBPDiasUnit) {
			this.lblBPSysUnit = lblBPSysUnit;
			this.lblBPDiasUnit = lblBPDiasUnit;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(Vitals.MMHG)) {
				lblBPSysUnit.setText(Vitals.MMHG);
				lblBPDiasUnit.setText(Vitals.MMHG);
			} else {
				lblBPSysUnit.setText(Vitals.PA);
				lblBPDiasUnit.setText(Vitals.PA);
			}
		}
	}

	private class HeightListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals(Vitals.FTIN)) {

				textField_HeightFt.setEnabled(true);
				textField_HeightIn.setEnabled(true);
				textField_HeightIn.setText("");

				textField_HeightCm.setEnabled(false);
				textField_HeightCm.setText("");

				heightUnit = Vitals.FTIN;

			} else if (e.getActionCommand().equals(Vitals.IN)) {

				textField_HeightFt.setEnabled(false);
				textField_HeightFt.setText("");

				textField_HeightIn.setEnabled(true);

				textField_HeightCm.setEnabled(false);
				textField_HeightCm.setText("");
				heightUnit = Vitals.IN;

			} else {

				textField_HeightFt.setEnabled(false);
				textField_HeightFt.setText("");

				textField_HeightIn.setEnabled(false);
				textField_HeightIn.setText("");

				textField_HeightCm.setEnabled(true);
				heightUnit = Vitals.CM;
			}
		}
	}

	private class WeightListener implements ActionListener {

		JLabel lbl;

		public WeightListener(JLabel lblWeightUnit) {
			lbl = lblWeightUnit;
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals(Vitals.LBS)) {

				lbl.setText(Vitals.LBS);

			} else {

				lbl.setText(Vitals.KG);
			}
		}
	}

	public String getBPUnit() {
		return bPUnit;
	}

	public void setBPUnit(String bpu) {
		bPUnit = bpu;
	}

	public String getHeightUnit() {
		return heightUnit;
	}

	public void setHeightUnit(String hu) {
		heightUnit = hu;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String wu) {
		weightUnit = wu;
	}

	public JLabel getLblBPSysUnit() {
		return lblBPSysUnit_1;
	}

	public JTextField getTextField_HeightFt() {
		return textField_HeightFt;
	}

	public JTextField getTextField_HeightIn() {
		return textField_HeightIn;
	}

	public JTextField getTextField_HeightCm() {
		return textField_HeightCm;
	}

	public JTextField getTextField_Weight() {
		return textField_Weight;
	}

	public JLabel getLblWeightUnit() {
		return lblWeightUnit;
	}

	public JTextArea getTextArea_Notes() {
		return textArea_Notes;
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
}