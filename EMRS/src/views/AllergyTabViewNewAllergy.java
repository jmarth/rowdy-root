package views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import database.AllergyTableGateway;
import database.AllergyTableGatewaySQLite;
import database.GatewayException;
import models.Allergy;
import models.AllergyList;
import models.Patient;
import models.Tabs;

public class AllergyTabViewNewAllergy extends JPanel {
	
	private final ButtonGroup severityButtonGroup = new ButtonGroup();
	
	// Variable for Allergy Name textfield
	private JTextField textField;
	
	// Patient this Allergy corresponds to
	Patient patient;
	Allergy a;
	private List<Allergy> allergyList;
	AllergyList al;
	
	JPanel oldPanel;
	
	// Table Gateway
	AllergyTableGateway atg;
	
	// Variables for JCheckBoxes
	List<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
	private JCheckBox chckbxAbnormalBreathing;
	private JCheckBox chckbxAbPain;
	private JCheckBox chckbxRedEyes;
	private JCheckBox chckbxWateryEyes;
	private JCheckBox chckbxRashes;
	private JCheckBox chckbxNasalCongestion;
	private JCheckBox chckbxItching;
	private JCheckBox chckbxHives;
	private JCheckBox chckbxAnxiety;
	private JCheckBox chckbxChestDiscomfort;
	private JCheckBox chckbxCough;
	private JCheckBox chckbxDiarrhea;
	private JCheckBox chckbxDifficultyBreathing;
	private JCheckBox chckbxDifficultySwallowing;
	private JCheckBox chckbxDizziness;
	private JCheckBox chckbxFlushing;
	private JCheckBox chckbxNausea;
	private JCheckBox chckbxPalpitations;
	private JCheckBox chckbxSwelling;
	private JCheckBox chckbxUnconsciousness;
	private JCheckBox chckbxWheezing;
	private JLabel lblOther;
	private JTextField otherTextField;

	// Variables for JRadioButtons
	private String severity;
	private JRadioButton rdbtnSevere;
	private JRadioButton rdbtnModerate;
	private JRadioButton rdbtnMild;
	
	// JTable from caller
	JTable allergyTable;
	

	/**
	 * Create the panel.
	 */
	public AllergyTabViewNewAllergy(final JTabbedPane tabbedPane, Patient patient, JPanel allergiesPanel, AllergyTableGateway gateway, JTable allergyTable, List<Allergy> allergyList, AllergyList al, Allergy a, Boolean exists) {
		this.patient = patient;
		this.a = a;
		this.atg = gateway;
		this.allergyTable = allergyTable;
		this.allergyList = allergyList;
		this.al = al;
		oldPanel = allergiesPanel;
		
		/**
		 * Try to connect to DB through AllergyTableGateway
		 * Set the gateway of the AllergyList
		 * Load Allergies into the AllergyList
		 */
		try {
			atg = new AllergyTableGatewaySQLite();
		} catch (GatewayException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		}
		
		if(exists){
			createExistingView(tabbedPane, patient, allergiesPanel, atg, a, allergyTable);
		} else {
			createNewView(tabbedPane, patient, allergiesPanel, atg, allergyTable);
		}

	}
	
	/**
	 * Called from ActionListener for Cancel button
	 * @param tabbedPane JTabbedPane to alter
	 * @param oldPanel Panel to switch back to
	 */
	public void cancel(JTabbedPane tabbedPane, JPanel oldPanel){
		int index = tabbedPane.indexOfTab(Tabs.allergiesAndMeds);
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
	public void save(Patient patient, AllergyTableGateway atg, JTabbedPane tabbedPane, JPanel oldPanel, JTable allergyTable){
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
		
		if(!otherTextField.getText().isEmpty()){
			strBuild.append(otherTextField.getText());
			strBuild.append("/");
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
			long aid = atg.insertAllergy(allergy);
			allergy.setId(aid);
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Change the panel back to allergy table
		// NEED TO FIGURE HOW TO UPDATE TABLE WHEN SWITCHING BACK TO SHOW NEW ALLERGY
		
		// Add the allergy to the JTable
		// Get model of AllergyTable in order to add rows
		DefaultTableModel model = (DefaultTableModel) allergyTable.getModel();
		// Add row		
		model.addRow(new Object[]{
				allergy.getAllergy(), 
				allergy.getSeverity(), 
				allergy.getAdverseReaction()
		});
		
		// Add allergy to allergyList
		allergyList.add(allergy);
		
		int index = tabbedPane.indexOfTab(Tabs.allergiesAndMeds);
		
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
	public void updateAllergy(Patient patient, AllergyTableGateway atg, JTabbedPane tabbedPane, JPanel oldPanel, JTable allergyTable){
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
		
		if(!otherTextField.getText().isEmpty()){
			strBuild.append(otherTextField.getText());
			strBuild.append("/");
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
		Allergy allergy = new Allergy(a.getId(), patient.getId(), textField.getText(), severity, strBuild.toString());
		try {
			atg.updateAllergy(allergy);
			al.loadFromGateway();
			allergyList = al.getAllergyList();
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Update row in JTable
		int selectedRow = allergyTable.getSelectedRow();
		allergyTable.setValueAt(allergy.getAllergy(), selectedRow, 0);
		allergyTable.setValueAt(allergy.getSeverity(), selectedRow, 1);
		allergyTable.setValueAt(allergy.getAdverseReaction(), selectedRow, 2);
		
		// Update the Allergy in the allergyList
		allergyList.set(selectedRow, allergy);
		
		DefaultTableModel dtm = (DefaultTableModel)allergyTable.getModel();
		
		// Change the panel back to allergy table
		// NEED TO FIGURE HOW TO UPDATE TABLE WHEN SWITCHING BACK TO SHOW NEW ALLERGY
		
		int index = tabbedPane.indexOfTab(Tabs.allergiesAndMeds);
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
	public void createNewView(final JTabbedPane tabbedPane, final Patient patient, JPanel allergiesPanel, final AllergyTableGateway atg, final JTable allergyTable){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		chckbxHives = new JCheckBox("Hives");
		checkboxes.add(chckbxHives);
		chckbxHives.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxHives = new GridBagConstraints();
		gbc_chckbxHives.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_chckbxHives.insets = new Insets(25, 0, 5, 5);
		gbc_chckbxHives.gridx = 1;
		gbc_chckbxHives.gridy = 7;
		add(chckbxHives, gbc_chckbxHives);
		
		chckbxItching = new JCheckBox("Itching");
		checkboxes.add(chckbxItching);
		chckbxItching.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxItching = new GridBagConstraints();
		gbc_chckbxItching.anchor = GridBagConstraints.WEST;
		gbc_chckbxItching.insets = new Insets(25, 0, 5, 5);
		gbc_chckbxItching.gridx = 2;
		gbc_chckbxItching.gridy = 7;
		add(chckbxItching, gbc_chckbxItching);
		
		chckbxNasalCongestion = new JCheckBox("Nasal Congestion");
		checkboxes.add(chckbxNasalCongestion);
		chckbxNasalCongestion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxNasalCongestion = new GridBagConstraints();
		gbc_chckbxNasalCongestion.anchor = GridBagConstraints.WEST;
		gbc_chckbxNasalCongestion.insets = new Insets(25, 0, 5, 5);
		gbc_chckbxNasalCongestion.gridx = 3;
		gbc_chckbxNasalCongestion.gridy = 7;
		add(chckbxNasalCongestion, gbc_chckbxNasalCongestion);
		
		chckbxRashes = new JCheckBox("Rashes");
		checkboxes.add(chckbxRashes);
		chckbxRashes.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxRashes = new GridBagConstraints();
		gbc_chckbxRashes.anchor = GridBagConstraints.WEST;
		gbc_chckbxRashes.insets = new Insets(25, 0, 5, 0);
		gbc_chckbxRashes.gridx = 4;
		gbc_chckbxRashes.gridy = 7;
		add(chckbxRashes, gbc_chckbxRashes);
		
		chckbxWateryEyes = new JCheckBox("Watery Eyes");
		checkboxes.add(chckbxWateryEyes);
		chckbxWateryEyes.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxWateryEyes = new GridBagConstraints();
		gbc_chckbxWateryEyes.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_chckbxWateryEyes.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxWateryEyes.gridx = 1;
		gbc_chckbxWateryEyes.gridy = 8;
		add(chckbxWateryEyes, gbc_chckbxWateryEyes);
		
		chckbxRedEyes = new JCheckBox("Red Eyes");
		checkboxes.add(chckbxRedEyes);
		chckbxRedEyes.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxRedEyes = new GridBagConstraints();
		gbc_chckbxRedEyes.anchor = GridBagConstraints.WEST;
		gbc_chckbxRedEyes.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxRedEyes.gridx = 2;
		gbc_chckbxRedEyes.gridy = 8;
		add(chckbxRedEyes, gbc_chckbxRedEyes);
		
		chckbxAbPain = new JCheckBox("Abdominal Pain");
		checkboxes.add(chckbxAbPain);
		chckbxAbPain.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxAbPain = new GridBagConstraints();
		gbc_chckbxAbPain.anchor = GridBagConstraints.WEST;
		gbc_chckbxAbPain.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAbPain.gridx = 3;
		gbc_chckbxAbPain.gridy = 8;
		add(chckbxAbPain, gbc_chckbxAbPain);
		
		chckbxAbnormalBreathing = new JCheckBox("Abnormal Breathing");
		checkboxes.add(chckbxAbnormalBreathing);
		chckbxAbnormalBreathing.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxAbnormalBreathing = new GridBagConstraints();
		gbc_chckbxAbnormalBreathing.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAbnormalBreathing.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_chckbxAbnormalBreathing.gridx = 4;
		gbc_chckbxAbnormalBreathing.gridy = 8;
		add(chckbxAbnormalBreathing, gbc_chckbxAbnormalBreathing);
		
		chckbxAnxiety = new JCheckBox("Anxiety");
		checkboxes.add(chckbxAnxiety);
		GridBagConstraints gbc_chckbxAnxiety = new GridBagConstraints();
		gbc_chckbxAnxiety.anchor = GridBagConstraints.WEST;
		gbc_chckbxAnxiety.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAnxiety.gridx = 1;
		gbc_chckbxAnxiety.gridy = 9;
		add(chckbxAnxiety, gbc_chckbxAnxiety);
		
		chckbxChestDiscomfort = new JCheckBox("Chest Discomfort");
		checkboxes.add(chckbxChestDiscomfort);
		GridBagConstraints gbc_chckbxChestDiscomfort = new GridBagConstraints();
		gbc_chckbxChestDiscomfort.anchor = GridBagConstraints.WEST;
		gbc_chckbxChestDiscomfort.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxChestDiscomfort.gridx = 2;
		gbc_chckbxChestDiscomfort.gridy = 9;
		add(chckbxChestDiscomfort, gbc_chckbxChestDiscomfort);
		
		chckbxCough = new JCheckBox("Cough");
		checkboxes.add(chckbxCough);
		GridBagConstraints gbc_chckbxCough = new GridBagConstraints();
		gbc_chckbxCough.anchor = GridBagConstraints.WEST;
		gbc_chckbxCough.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCough.gridx = 3;
		gbc_chckbxCough.gridy = 9;
		add(chckbxCough, gbc_chckbxCough);
		
		chckbxDiarrhea = new JCheckBox("Diarrhea");
		checkboxes.add(chckbxDiarrhea);
		GridBagConstraints gbc_chckbxDiarrhea = new GridBagConstraints();
		gbc_chckbxDiarrhea.anchor = GridBagConstraints.WEST;
		gbc_chckbxDiarrhea.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxDiarrhea.gridx = 4;
		gbc_chckbxDiarrhea.gridy = 9;
		add(chckbxDiarrhea, gbc_chckbxDiarrhea);
		
		chckbxDifficultyBreathing = new JCheckBox("Difficulty breathing");
		checkboxes.add(chckbxDifficultyBreathing);
		GridBagConstraints gbc_chckbxDifficultyBreathing = new GridBagConstraints();
		gbc_chckbxDifficultyBreathing.anchor = GridBagConstraints.WEST;
		gbc_chckbxDifficultyBreathing.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDifficultyBreathing.gridx = 1;
		gbc_chckbxDifficultyBreathing.gridy = 10;
		add(chckbxDifficultyBreathing, gbc_chckbxDifficultyBreathing);
		
		chckbxDifficultySwallowing = new JCheckBox("Difficulty swallowing");
		checkboxes.add(chckbxDifficultySwallowing);
		GridBagConstraints gbc_chckbxDifficultySwallowing = new GridBagConstraints();
		gbc_chckbxDifficultySwallowing.anchor = GridBagConstraints.WEST;
		gbc_chckbxDifficultySwallowing.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDifficultySwallowing.gridx = 2;
		gbc_chckbxDifficultySwallowing.gridy = 10;
		add(chckbxDifficultySwallowing, gbc_chckbxDifficultySwallowing);
		
		chckbxDizziness = new JCheckBox("Dizziness");
		checkboxes.add(chckbxDizziness);
		GridBagConstraints gbc_chckbxDizziness = new GridBagConstraints();
		gbc_chckbxDizziness.anchor = GridBagConstraints.WEST;
		gbc_chckbxDizziness.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDizziness.gridx = 3;
		gbc_chckbxDizziness.gridy = 10;
		add(chckbxDizziness, gbc_chckbxDizziness);
		
		chckbxFlushing = new JCheckBox("Flushing");
		checkboxes.add(chckbxFlushing);
		GridBagConstraints gbc_chckbxFlushing = new GridBagConstraints();
		gbc_chckbxFlushing.anchor = GridBagConstraints.WEST;
		gbc_chckbxFlushing.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxFlushing.gridx = 4;
		gbc_chckbxFlushing.gridy = 10;
		add(chckbxFlushing, gbc_chckbxFlushing);
		
		chckbxNausea = new JCheckBox("Nausea");
		checkboxes.add(chckbxNausea);
		GridBagConstraints gbc_chckbxNausea = new GridBagConstraints();
		gbc_chckbxNausea.anchor = GridBagConstraints.WEST;
		gbc_chckbxNausea.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNausea.gridx = 1;
		gbc_chckbxNausea.gridy = 11;
		add(chckbxNausea, gbc_chckbxNausea);
		
		chckbxPalpitations = new JCheckBox("Palpitations");
		checkboxes.add(chckbxPalpitations);
		GridBagConstraints gbc_chckbxPalpitations = new GridBagConstraints();
		gbc_chckbxPalpitations.anchor = GridBagConstraints.WEST;
		gbc_chckbxPalpitations.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPalpitations.gridx = 2;
		gbc_chckbxPalpitations.gridy = 11;
		add(chckbxPalpitations, gbc_chckbxPalpitations);
		
		chckbxSwelling = new JCheckBox("Swelling");
		checkboxes.add(chckbxSwelling);
		GridBagConstraints gbc_chckbxSwelling = new GridBagConstraints();
		gbc_chckbxSwelling.anchor = GridBagConstraints.WEST;
		gbc_chckbxSwelling.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSwelling.gridx = 3;
		gbc_chckbxSwelling.gridy = 11;
		add(chckbxSwelling, gbc_chckbxSwelling);
		
		chckbxUnconsciousness = new JCheckBox("Unconsciousness");
		checkboxes.add(chckbxUnconsciousness);
		GridBagConstraints gbc_chckbxUnconsciousness = new GridBagConstraints();
		gbc_chckbxUnconsciousness.anchor = GridBagConstraints.WEST;
		gbc_chckbxUnconsciousness.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUnconsciousness.gridx = 4;
		gbc_chckbxUnconsciousness.gridy = 11;
		add(chckbxUnconsciousness, gbc_chckbxUnconsciousness);
		
		chckbxWheezing = new JCheckBox("Wheezing");
		checkboxes.add(chckbxWheezing);
		GridBagConstraints gbc_chckbxWheezing = new GridBagConstraints();
		gbc_chckbxWheezing.anchor = GridBagConstraints.WEST;
		gbc_chckbxWheezing.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxWheezing.gridx = 1;
		gbc_chckbxWheezing.gridy = 12;
		add(chckbxWheezing, gbc_chckbxWheezing);
		
		lblOther = new JLabel("Other");
		GridBagConstraints gbc_lblOther = new GridBagConstraints();
		gbc_lblOther.anchor = GridBagConstraints.EAST;
		gbc_lblOther.insets = new Insets(0, 0, 5, 5);
		gbc_lblOther.gridx = 1;
		gbc_lblOther.gridy = 13;
		add(lblOther, gbc_lblOther);
		
		otherTextField = new JTextField();
		GridBagConstraints gbc_otherTextField = new GridBagConstraints();
		gbc_otherTextField.gridwidth = 2;
		gbc_otherTextField.insets = new Insets(0, 0, 5, 0);
		gbc_otherTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_otherTextField.gridx = 2;
		gbc_otherTextField.gridy = 13;
		add(otherTextField, gbc_otherTextField);
		otherTextField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(patient, atg, tabbedPane, oldPanel, allergyTable);
			}
		});
		
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(50, 0, 0, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 16;
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
		gbc_btnCancel.gridy = 16;
		add(btnCancel, gbc_btnCancel);
	}
	
	/**
	 * Creates NewAllergyFormView GUI with populated data
	 * @param tabbedPane JTabbedPane to alter
	 * @param patient Patient that allergy regards to
	 * @param allergiesPanel Old JPanel to set back to on cancel or save
	 * @param atg Gateway for Allergy table
	 */
	public void createExistingView(final JTabbedPane tabbedPane, final Patient patient, JPanel allergiesPanel, final AllergyTableGateway atg, Allergy a, final JTable allergyTable){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAllergy = new JLabel("Allergy");
		lblAllergy.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblAllergy = new GridBagConstraints();
		gbc_lblAllergy.insets = new Insets(25, 25, 5, 5);
		gbc_lblAllergy.gridx = 0;
		gbc_lblAllergy.gridy = 1;
		add(lblAllergy, gbc_lblAllergy);
		
		textField = new JTextField();
		textField.setText(a.getAllergy());
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
		
		
		// Populate severity button based off allergy
		String sev_str = a.getSeverity();
		if(sev_str.equals("Severe")){
			rdbtnSevere.setSelected(true);
		} else if (sev_str.equals("Moderate")) {
			rdbtnModerate.setSelected(true);
		} else {
			rdbtnMild.setSelected(true);
		}
		
		JLabel lblReactions = new JLabel("Reactions");
		lblReactions.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblReactions = new GridBagConstraints();
		gbc_lblReactions.insets = new Insets(25, 25, 5, 5);
		gbc_lblReactions.gridx = 0;
		gbc_lblReactions.gridy = 7;
		add(lblReactions, gbc_lblReactions);
		
		// Get String of reactions from Allergy
		String advr_str = a.getAdverseReaction();
		
		chckbxHives = new JCheckBox("Hives");
		if(advr_str.contains("Hives"))
			chckbxHives.setSelected(true);
		checkboxes.add(chckbxHives);
		chckbxHives.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxHives = new GridBagConstraints();
		gbc_chckbxHives.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_chckbxHives.insets = new Insets(25, 0, 5, 5);
		gbc_chckbxHives.gridx = 1;
		gbc_chckbxHives.gridy = 7;
		add(chckbxHives, gbc_chckbxHives);
		
		chckbxItching = new JCheckBox("Itching");
		if(advr_str.contains("Itching"))
			chckbxItching.setSelected(true);
		checkboxes.add(chckbxItching);
		chckbxItching.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxItching = new GridBagConstraints();
		gbc_chckbxItching.anchor = GridBagConstraints.WEST;
		gbc_chckbxItching.insets = new Insets(25, 0, 5, 5);
		gbc_chckbxItching.gridx = 2;
		gbc_chckbxItching.gridy = 7;
		add(chckbxItching, gbc_chckbxItching);
		
		chckbxNasalCongestion = new JCheckBox("Nasal Congestion");
		if(advr_str.contains("Nasal Congestiong"))
			chckbxNasalCongestion.setSelected(true);
		checkboxes.add(chckbxNasalCongestion);
		chckbxNasalCongestion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxNasalCongestion = new GridBagConstraints();
		gbc_chckbxNasalCongestion.anchor = GridBagConstraints.WEST;
		gbc_chckbxNasalCongestion.insets = new Insets(25, 0, 5, 5);
		gbc_chckbxNasalCongestion.gridx = 3;
		gbc_chckbxNasalCongestion.gridy = 7;
		add(chckbxNasalCongestion, gbc_chckbxNasalCongestion);
		
		chckbxRashes = new JCheckBox("Rashes");
		if(advr_str.contains("Rashes"))
			chckbxRashes.setSelected(true);
		checkboxes.add(chckbxRashes);
		chckbxRashes.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxRashes = new GridBagConstraints();
		gbc_chckbxRashes.anchor = GridBagConstraints.WEST;
		gbc_chckbxRashes.insets = new Insets(25, 0, 5, 0);
		gbc_chckbxRashes.gridx = 4;
		gbc_chckbxRashes.gridy = 7;
		add(chckbxRashes, gbc_chckbxRashes);
		
		chckbxWateryEyes = new JCheckBox("Watery Eyes");
		if(advr_str.contains("Watery Eyes"))
			chckbxWateryEyes.setSelected(true);
		checkboxes.add(chckbxWateryEyes);
		chckbxWateryEyes.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxWateryEyes = new GridBagConstraints();
		gbc_chckbxWateryEyes.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_chckbxWateryEyes.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxWateryEyes.gridx = 1;
		gbc_chckbxWateryEyes.gridy = 8;
		add(chckbxWateryEyes, gbc_chckbxWateryEyes);
		
		chckbxRedEyes = new JCheckBox("Red Eyes");
		if(advr_str.contains("Red Eyes"))
			chckbxRedEyes.setSelected(true);
		checkboxes.add(chckbxRedEyes);
		chckbxRedEyes.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxRedEyes = new GridBagConstraints();
		gbc_chckbxRedEyes.anchor = GridBagConstraints.WEST;
		gbc_chckbxRedEyes.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxRedEyes.gridx = 2;
		gbc_chckbxRedEyes.gridy = 8;
		add(chckbxRedEyes, gbc_chckbxRedEyes);
		
		chckbxAbPain = new JCheckBox("Abdominal Pain");
		if(advr_str.contains("Abdominal Pain"))
			chckbxAbPain.setSelected(true);
		checkboxes.add(chckbxAbPain);
		chckbxAbPain.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxAbPain = new GridBagConstraints();
		gbc_chckbxAbPain.anchor = GridBagConstraints.WEST;
		gbc_chckbxAbPain.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAbPain.gridx = 3;
		gbc_chckbxAbPain.gridy = 8;
		add(chckbxAbPain, gbc_chckbxAbPain);
		
		chckbxAbnormalBreathing = new JCheckBox("Abnormal Breathing");
		if(advr_str.contains("Abnormal Breathing"))
			chckbxAbnormalBreathing.setSelected(true);
		checkboxes.add(chckbxAbnormalBreathing);
		chckbxAbnormalBreathing.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxAbnormalBreathing = new GridBagConstraints();
		gbc_chckbxAbnormalBreathing.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxAbnormalBreathing.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_chckbxAbnormalBreathing.gridx = 4;
		gbc_chckbxAbnormalBreathing.gridy = 8;
		add(chckbxAbnormalBreathing, gbc_chckbxAbnormalBreathing);
		
		chckbxAnxiety = new JCheckBox("Anxiety");
		if(advr_str.contains("Anxiety"))
			chckbxAnxiety.setSelected(true);
		checkboxes.add(chckbxAnxiety);
		GridBagConstraints gbc_chckbxAnxiety = new GridBagConstraints();
		gbc_chckbxAnxiety.anchor = GridBagConstraints.WEST;
		gbc_chckbxAnxiety.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAnxiety.gridx = 1;
		gbc_chckbxAnxiety.gridy = 9;
		add(chckbxAnxiety, gbc_chckbxAnxiety);
		
		chckbxChestDiscomfort = new JCheckBox("Chest Discomfort");
		if(advr_str.contains("Chest Discomfort"))
			chckbxChestDiscomfort.setSelected(true);
		checkboxes.add(chckbxChestDiscomfort);
		GridBagConstraints gbc_chckbxChestDiscomfort = new GridBagConstraints();
		gbc_chckbxChestDiscomfort.anchor = GridBagConstraints.WEST;
		gbc_chckbxChestDiscomfort.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxChestDiscomfort.gridx = 2;
		gbc_chckbxChestDiscomfort.gridy = 9;
		add(chckbxChestDiscomfort, gbc_chckbxChestDiscomfort);
		
		chckbxCough = new JCheckBox("Cough");
		if(advr_str.contains("Cough"))
			chckbxCough.setSelected(true);
		checkboxes.add(chckbxCough);
		GridBagConstraints gbc_chckbxCough = new GridBagConstraints();
		gbc_chckbxCough.anchor = GridBagConstraints.WEST;
		gbc_chckbxCough.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCough.gridx = 3;
		gbc_chckbxCough.gridy = 9;
		add(chckbxCough, gbc_chckbxCough);
		
		chckbxDiarrhea = new JCheckBox("Diarrhea");
		if(advr_str.contains("Diarrhea"))
			chckbxDiarrhea.setSelected(true);
		checkboxes.add(chckbxDiarrhea);
		GridBagConstraints gbc_chckbxDiarrhea = new GridBagConstraints();
		gbc_chckbxDiarrhea.anchor = GridBagConstraints.WEST;
		gbc_chckbxDiarrhea.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxDiarrhea.gridx = 4;
		gbc_chckbxDiarrhea.gridy = 9;
		add(chckbxDiarrhea, gbc_chckbxDiarrhea);
		
		chckbxDifficultyBreathing = new JCheckBox("Difficulty breathing");
		if(advr_str.contains("Difficulty breathing"))
			chckbxDifficultyBreathing.setSelected(true);
		checkboxes.add(chckbxDifficultyBreathing);
		GridBagConstraints gbc_chckbxDifficultyBreathing = new GridBagConstraints();
		gbc_chckbxDifficultyBreathing.anchor = GridBagConstraints.WEST;
		gbc_chckbxDifficultyBreathing.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDifficultyBreathing.gridx = 1;
		gbc_chckbxDifficultyBreathing.gridy = 10;
		add(chckbxDifficultyBreathing, gbc_chckbxDifficultyBreathing);
		
		chckbxDifficultySwallowing = new JCheckBox("Difficulty swallowing");
		if(advr_str.contains("Difficulty swallowing"))
			chckbxDifficultySwallowing.setSelected(true);
		checkboxes.add(chckbxDifficultySwallowing);
		GridBagConstraints gbc_chckbxDifficultySwallowing = new GridBagConstraints();
		gbc_chckbxDifficultySwallowing.anchor = GridBagConstraints.WEST;
		gbc_chckbxDifficultySwallowing.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDifficultySwallowing.gridx = 2;
		gbc_chckbxDifficultySwallowing.gridy = 10;
		add(chckbxDifficultySwallowing, gbc_chckbxDifficultySwallowing);
		
		chckbxDizziness = new JCheckBox("Dizziness");
		if(advr_str.contains("Dizziness"))
			chckbxDizziness.setSelected(true);
		checkboxes.add(chckbxDizziness);
		GridBagConstraints gbc_chckbxDizziness = new GridBagConstraints();
		gbc_chckbxDizziness.anchor = GridBagConstraints.WEST;
		gbc_chckbxDizziness.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDizziness.gridx = 3;
		gbc_chckbxDizziness.gridy = 10;
		add(chckbxDizziness, gbc_chckbxDizziness);
		
		chckbxFlushing = new JCheckBox("Flushing");
		if(advr_str.contains("Flushing"))
			chckbxFlushing.setSelected(true);
		checkboxes.add(chckbxFlushing);
		GridBagConstraints gbc_chckbxFlushing = new GridBagConstraints();
		gbc_chckbxFlushing.anchor = GridBagConstraints.WEST;
		gbc_chckbxFlushing.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxFlushing.gridx = 4;
		gbc_chckbxFlushing.gridy = 10;
		add(chckbxFlushing, gbc_chckbxFlushing);
		
		chckbxNausea = new JCheckBox("Nausea");
		if(advr_str.contains("Nausea"))
			chckbxNausea.setSelected(true);
		checkboxes.add(chckbxNausea);
		GridBagConstraints gbc_chckbxNausea = new GridBagConstraints();
		gbc_chckbxNausea.anchor = GridBagConstraints.WEST;
		gbc_chckbxNausea.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNausea.gridx = 1;
		gbc_chckbxNausea.gridy = 11;
		add(chckbxNausea, gbc_chckbxNausea);
		
		chckbxPalpitations = new JCheckBox("Palpitations");
		if(advr_str.contains("Palpitations"))
			chckbxPalpitations.setSelected(true);
		checkboxes.add(chckbxPalpitations);
		GridBagConstraints gbc_chckbxPalpitations = new GridBagConstraints();
		gbc_chckbxPalpitations.anchor = GridBagConstraints.WEST;
		gbc_chckbxPalpitations.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPalpitations.gridx = 2;
		gbc_chckbxPalpitations.gridy = 11;
		add(chckbxPalpitations, gbc_chckbxPalpitations);
		
		chckbxSwelling = new JCheckBox("Swelling");
		if(advr_str.contains("Swelling"))
			chckbxSwelling.setSelected(true);
		checkboxes.add(chckbxSwelling);
		GridBagConstraints gbc_chckbxSwelling = new GridBagConstraints();
		gbc_chckbxSwelling.anchor = GridBagConstraints.WEST;
		gbc_chckbxSwelling.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSwelling.gridx = 3;
		gbc_chckbxSwelling.gridy = 11;
		add(chckbxSwelling, gbc_chckbxSwelling);
		
		chckbxUnconsciousness = new JCheckBox("Unconsciousness");
		if(advr_str.contains("Unconsciousness"))
			chckbxUnconsciousness.setSelected(true);
		checkboxes.add(chckbxUnconsciousness);
		GridBagConstraints gbc_chckbxUnconsciousness = new GridBagConstraints();
		gbc_chckbxUnconsciousness.anchor = GridBagConstraints.WEST;
		gbc_chckbxUnconsciousness.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUnconsciousness.gridx = 4;
		gbc_chckbxUnconsciousness.gridy = 11;
		add(chckbxUnconsciousness, gbc_chckbxUnconsciousness);
		
		chckbxWheezing = new JCheckBox("Wheezing");
		if(advr_str.contains("Wheezing"))
			chckbxWheezing.setSelected(true);
		checkboxes.add(chckbxWheezing);
		GridBagConstraints gbc_chckbxWheezing = new GridBagConstraints();
		gbc_chckbxWheezing.anchor = GridBagConstraints.WEST;
		gbc_chckbxWheezing.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxWheezing.gridx = 1;
		gbc_chckbxWheezing.gridy = 12;
		add(chckbxWheezing, gbc_chckbxWheezing);
		
		lblOther = new JLabel("Other");
		GridBagConstraints gbc_lblOther = new GridBagConstraints();
		gbc_lblOther.anchor = GridBagConstraints.EAST;
		gbc_lblOther.insets = new Insets(0, 0, 5, 5);
		gbc_lblOther.gridx = 1;
		gbc_lblOther.gridy = 13;
		add(lblOther, gbc_lblOther);
		
		otherTextField = new JTextField();
		GridBagConstraints gbc_otherTextField = new GridBagConstraints();
		gbc_otherTextField.gridwidth = 2;
		gbc_otherTextField.insets = new Insets(0, 0, 5, 0);
		gbc_otherTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_otherTextField.gridx = 2;
		gbc_otherTextField.gridy = 13;
		add(otherTextField, gbc_otherTextField);
		otherTextField.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAllergy(patient, atg, tabbedPane, oldPanel, allergyTable);
			}
		});
		
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(50, 0, 0, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 16;
		add(btnUpdate, gbc_btnSave);
		
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
		gbc_btnCancel.gridy = 16;
		add(btnCancel, gbc_btnCancel);
	}


}
