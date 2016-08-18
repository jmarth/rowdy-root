package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import database.AllergyTableGateway;
import database.DrugTableGateway;
import database.GatewayException;
import database.HxTableGateway;
import database.MedicationsTableGateway;
import models.Allergy;
import models.AllergyList;
import models.CL;
import models.Hx;
import models.Med;
import models.Patient;
import models.Tabs;
import javax.swing.JLabel;

import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.border.MatteBorder;
import javax.swing.BoxLayout;

public class hxView extends JPanel {
	private AllergyList al = new AllergyList();
	private List<Allergy> allergyList;
	private AllergyTableGateway atg;
	private MedicationsTableGateway mtg;
	private DrugTableGateway rtg;
	private HxTableGateway htg;
	private Patient patient;
	private JTable allergyTable = new JTable();
	
	private List<Hx> healthHistory;
	
	private JScrollPane allergyScroller;
	private JScrollPane medScroller;
	
	private JPanel allergyMasterPanel;
	private JPanel medMasterPanel;
	private JTable medTable;
	private JPanel medPanel;
	private JPanel allergyPanel;
	private JPanel hxMasterPanel;
	private JPanel drugAllergyPanel;
	private JPanel pmhPanel;
	private JPanel btPanel;
	private JPanel pshPanel;
	private JPanel sctPanel;
	private JPanel fhPanel;
	private JPanel lawPanel;
	private JPanel pePanel;
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
	private JPanel presentConditionPanel;
	private JButton btnNewForm;
	private JScrollPane hxScroller;
	
	private List<JCheckBox> peList;

	/**
	 * Create the panel.
	 * @param drugTableGateway 
	 * @param hxTableGateway 
	 */
	public hxView(final Patient patient, final JTabbedPane tabbedPane, final AllergyTableGateway atg, final MedicationsTableGateway mtg, DrugTableGateway drugTableGateway, HxTableGateway hxTableGateway) {
		
		this.atg = atg;
		this.mtg = mtg;
		this.rtg = drugTableGateway;
		this.htg = hxTableGateway;
		this.patient = patient;
		
		
		setBackground(CL.colorBlue);
		
		allergyMasterPanel = new JPanel();
		allergyMasterPanel.setBackground(CL.colorBlue);
		allergyMasterPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Allergies", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		medMasterPanel = new JPanel();
		medMasterPanel.setBackground(CL.colorBlue);
		medMasterPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Medications", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		
		hxMasterPanel = new JPanel();
		hxMasterPanel.setBackground(CL.colorBlue);
		hxMasterPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Health History", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		presentConditionPanel = new JPanel();
		presentConditionPanel.setBackground(new Color(255, 250, 250));
		presentConditionPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "History of Present Condition", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(hxMasterPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(allergyMasterPanel, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
								.addComponent(presentConditionPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(medMasterPanel, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(presentConditionPanel, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(allergyMasterPanel, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
						.addComponent(medMasterPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(hxMasterPanel, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		presentConditionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		/** CODE CUT FROM HERE **/
		buildHx();

		
		btnNewForm = new JButton("Fill out Health History");
		btnNewForm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hxView prevPanel = hxView.this;
				int index = tabbedPane.indexOfTab(Tabs.hx);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, new hxForm(patient, htg, prevPanel, tabbedPane));
			}
			
		});
		
		try {
			healthHistory = htg.fetchHxForPatient(this.patient);
		} catch (GatewayException e1) {
			e1.printStackTrace();
		}
		
		if (!(healthHistory.isEmpty())) {
			populateHealthHistory();
		}
		else {
			presentConditionPanel.add(btnNewForm);
		}
		
		
		medMasterPanel.setLayout(new BorderLayout(0, 0));
		
		medPanel = new JPanel();
		medMasterPanel.add(medPanel, BorderLayout.CENTER);
		medPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel medButtonPanel = new JPanel();
		medButtonPanel.setBackground(CL.colorBlue);
		medPanel.add(medButtonPanel, BorderLayout.NORTH);

		
		JButton btnAddMED = new JButton("ADD");
		btnAddMED.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddMED.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel prevPanel = hxView.this;
				int index = tabbedPane.indexOfTab("Health History");
				tabbedPane.setComponentAt(index, new AddMedView(rtg, hxView.this.mtg, tabbedPane, hxView.this, hxView.this.patient));
				revalidate();
				repaint();
			}
			
		});
		medButtonPanel.add(btnAddMED);
		
		
		medTable = new JTable();
		medTable.setEnabled(false);
		
		medTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Trade Name", "Generic Name"
				}
		));
		
		populateMedTable();
		
		medScroller = new JScrollPane(medTable);
		
		medPanel.add(medScroller, BorderLayout.CENTER);
		
		allergyTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Allergy", "Severity", "Adverse Reaction"
				}
		));
		
		

		populateAllergyTable();
		
		allergyPanel = new JPanel();
		allergyPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel allergyButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		allergyPanel.add(allergyButtonPanel, BorderLayout.NORTH);
		allergyButtonPanel.setBackground(CL.colorBlue);
		
		JButton btnAddAllergy = new JButton("ADD");
		btnAddAllergy.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddAllergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.indexOfTab(Tabs.hx);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, new AllergyTabViewNewAllergy(tabbedPane, patient, hxView.this, atg, allergyTable, allergyList, al, null, false));				
			}
		});
		allergyButtonPanel.add(btnAddAllergy);
		
		JButton btnEditAllergy = new JButton("EDIT");
		btnEditAllergy.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEditAllergy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selection = allergyTable.getSelectedRow();
				if  (selection == -1) {
					return;
				}
				al.loadFromGateway();
				allergyList = al.getAllergyListForPatient(patient);
				Allergy tmp = allergyList.get(selection);
				
				AllergyTabViewNewAllergy anv = new AllergyTabViewNewAllergy(tabbedPane, patient, hxView.this, atg, allergyTable, allergyList, al, tmp, true);
				
				int index = tabbedPane.indexOfTab(Tabs.hx);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, anv);
			}
			
		});
		allergyMasterPanel.setLayout(new BorderLayout(0, 0));
		allergyButtonPanel.add(btnEditAllergy);
		
		
		allergyScroller = new JScrollPane(allergyTable);
		allergyPanel.add(allergyScroller, BorderLayout.CENTER);
		allergyMasterPanel.add(allergyPanel);
		setLayout(groupLayout);

	}
	

	public void populateHealthHistory() {
		presentConditionPanel.removeAll();
		healthHistory = new ArrayList<Hx>();
		
		try {
			healthHistory = htg.fetchHxForPatient(patient);
		} catch (GatewayException e) {
			e.printStackTrace();
		}
		Hx hx = null;
		
		if (healthHistory.size() > 0)
			hx = healthHistory.get(0);
		else
			return;
		
		
		presentConditionPanel.add(new JLabel(hx.getPc()));
		
		drugAllergyPanel.add(new JLabel(hx.getDa(), JLabel.CENTER));
		
		String bleeds = hx.getBt();
		System.out.println("BLEEDS: " + bleeds);
		String[] bleedingTendencies = {"Aspirin", "Plavix", "Bleeding Disorder"};
		for (int i = 0; i < bleeds.length() - 1; i++) {
			Character c = bleeds.charAt(i);
			JCheckBox ck = new JCheckBox(bleedingTendencies[i]);
			ck.setEnabled(false);
			if (c == '1')
				ck.setSelected(true);
			btPanel.add(ck);
		}
		
		Character sickle = bleeds.charAt(3);
		JRadioButton sickleYes = new JRadioButton("Yes");
		sickleYes.setEnabled(false);
		JRadioButton sickleNo = new JRadioButton("No");
		sickleNo.setEnabled(false);
		if (sickle == '1') {
			sickleYes.setSelected(true);
		} else {
			sickleNo.setSelected(true);
		}
		
		sctPanel.add(new JLabel("Sickle Cell Trait?"));
		sctPanel.add(sickleYes);
		sctPanel.add(sickleNo);
		
		String[] pastMedicalHistory = {
				"Hypertension (HTN)", 
				"Diabetes", 
				"Coronary Artery Disease (CAD)", 
				"COPD", 
				"Peripheral Vascular Disease (PVD)", 
				"Congestive Heart Failure (CHF)",
				"Hypotension"
		};
		String pmh = hx.getPmh();
		for (int i = 0; i < pmh.length(); i++) {
			Character c = pmh.charAt(i);
			JCheckBox ck = new JCheckBox(pastMedicalHistory[i]);
			ck.setEnabled(false);
			if (c == '1')
				ck.setSelected(true);
			pmhPanel.add(ck);
			
		}
		
		pshPanel.add(new JLabel(hx.getPsh()));
		
		fhPanel.add(new JLabel(hx.getFh()));
		
		lawPanel.add(new JLabel(hx.getLaw()));
		
		String physicalExam = hx.getPe();
		int i = 0;
		for (JCheckBox ck : peList) {
			ck.setEnabled(false);
			Character c = physicalExam.charAt(i);
			if (c == '1')
				ck.setSelected(true);
			i++;
		}
	}


	/**
	 * Populates the AllergyTable with all allergies related current Patient
	 * @param allergyTable JTable to populate
	 * @param patient Patient JTable to populate
	 */
	public void populateAllergyTable(){
		
		// Get model of AllergyTable in order to add rows
		// Declare variables
		DefaultTableModel model = (DefaultTableModel) allergyTable.getModel();
		
		al.setGateway(atg);
		al.loadFromGateway();
		
		// Find all allergies for the given patient
		allergyList = al.getAllergyListForPatient(patient);
		
		/**
		 * For every allergy in the allergyList
		 * .. Add that model the JTable
		 */
		for(Allergy allergy : allergyList) {
			model.addRow(new Object[]{
					allergy.getAllergy(), 
					allergy.getSeverity(), 
					allergy.getAdverseReaction()
				});
		}
	}
	
	public void populateMedTable(){
		List<Med> tmpList = new ArrayList<Med>();
		
		try {
			tmpList = mtg.fetchMedsForPatient(patient);
		} catch (GatewayException e) {
			e.printStackTrace();
		}
		System.out.println(tmpList.size());
		
		
		// Get model of MedTable in order to add rows
		// Declare variables
		DefaultTableModel model = (DefaultTableModel) medTable.getModel();
		
		// reset data in table
		model.setRowCount(0);
		
						
		/**
		 * For every med in the medList
		 * .. Add that model the JTable
		 */
		for(Med med : tmpList) {
			model.addRow(new Object[]{
					med.getTradeName(), 
					med.getGenericName(), 
			});
		}
	}
	
	public void buildHx() {
		drugAllergyPanel = new JPanel();
		drugAllergyPanel.setBackground(new Color(255, 250, 250));
		drugAllergyPanel.setBorder(new TitledBorder(new MatteBorder(3, 0, 0, 0, (Color) new Color(0, 0, 0)), "Major Drug Allergy", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		
		btPanel = new JPanel();
		btPanel.setBorder(new TitledBorder(new MatteBorder(3, 0, 0, 0, (Color) new Color(0, 0, 0)), "Bleeding Tendency", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		btPanel.setBackground(new Color(255, 250, 250));
		
		sctPanel = new JPanel();
		sctPanel.setBorder(new TitledBorder(new MatteBorder(3, 0, 0, 0, (Color) new Color(0, 0, 0)), "Sickle Cell Trait", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		sctPanel.setBackground(new Color(255, 250, 250));
		
		pmhPanel = new JPanel();
		pmhPanel.setBorder(new TitledBorder(new MatteBorder(3, 0, 0, 0, (Color) new Color(0, 0, 0)), "Past Medical History", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		pmhPanel.setBackground(new Color(255, 250, 250));
		
		pshPanel = new JPanel();
		pshPanel.setBorder(new TitledBorder(new MatteBorder(3, 0, 0, 0, (Color) new Color(0, 0, 0)), "Past Surgical History", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		pshPanel.setBackground(new Color(255, 250, 250));
		
		fhPanel = new JPanel();
		fhPanel.setBorder(new TitledBorder(new MatteBorder(3, 0, 0, 0, (Color) new Color(0, 0, 0)), "Family History", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		fhPanel.setBackground(new Color(255, 250, 250));
		
		lawPanel = new JPanel();
		lawPanel.setBackground(new Color(255, 250, 250));
		lawPanel.setBorder(new TitledBorder(new MatteBorder(3, 0, 0, 0, (Color) new Color(0, 0, 0)), "Risks, Benefits, Complications", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		
		pePanel = new JPanel();
		pePanel.setBackground(new Color(255, 250, 250));
		pePanel.setBorder(new TitledBorder(new MatteBorder(3, 0, 0, 0, (Color) new Color(0, 0, 0)), "Physical Exam", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		pePanel.setLayout(new GridLayout(14, 3, 1, 1));
		
		JLabel label_5 = new JLabel("PHYSICAL EXAM");
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		pePanel.add(label_5);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea);
		
		JLabel label_6 = new JLabel("Normal for Patient");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_6);
		
		JLabel label_7 = new JLabel("Vital signs");
		label_7.setHorizontalAlignment(SwingConstants.TRAILING);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_7);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_1);
		
		ck1 = new JCheckBox("");
		ck1.setEnabled(false);
		ck1.setOpaque(true);
		ck1.setHorizontalAlignment(SwingConstants.CENTER);
		ck1.setForeground(Color.BLACK);
		ck1.setBorderPainted(true);
		ck1.setBackground(new Color(255, 255, 255));
		pePanel.add(ck1);
		
		JLabel label_8 = new JLabel("Neurological");
		label_8.setHorizontalAlignment(SwingConstants.TRAILING);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_8);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_2);
		
		ck2 = new JCheckBox("");
		ck2.setEnabled(false);
		ck2.setOpaque(true);
		ck2.setHorizontalAlignment(SwingConstants.CENTER);
		ck2.setBorderPainted(true);
		ck2.setBackground(new Color(255, 255, 255));
		pePanel.add(ck2);
		
		JLabel label_9 = new JLabel("Head & Neck");
		label_9.setHorizontalAlignment(SwingConstants.TRAILING);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_9);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_3);
		
		ck3 = new JCheckBox("");
		ck3.setEnabled(false);
		ck3.setOpaque(true);
		ck3.setHorizontalAlignment(SwingConstants.CENTER);
		ck3.setBorderPainted(true);
		ck3.setBackground(new Color(255, 255, 255));
		pePanel.add(ck3);
		
		JLabel label_10 = new JLabel("Mouth & Throat");
		label_10.setHorizontalAlignment(SwingConstants.TRAILING);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_10);
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_4);
		
		ck4 = new JCheckBox("");
		ck4.setEnabled(false);
		ck4.setOpaque(true);
		ck4.setHorizontalAlignment(SwingConstants.CENTER);
		ck4.setBorderPainted(true);
		ck4.setBackground(new Color(255, 255, 255));
		pePanel.add(ck4);
		
		JLabel label_11 = new JLabel("Breast");
		label_11.setHorizontalAlignment(SwingConstants.TRAILING);
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_11);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_5);
		
		ck5 = new JCheckBox("");
		ck5.setEnabled(false);
		ck5.setOpaque(true);
		ck5.setHorizontalAlignment(SwingConstants.CENTER);
		ck5.setBorderPainted(true);
		ck5.setBackground(new Color(255, 255, 255));
		pePanel.add(ck5);
		
		JLabel label_12 = new JLabel("Heart");
		label_12.setHorizontalAlignment(SwingConstants.TRAILING);
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_12);
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_6);
		
		ck6 = new JCheckBox("");
		ck6.setEnabled(false);
		ck6.setOpaque(true);
		ck6.setHorizontalAlignment(SwingConstants.CENTER);
		ck6.setBorderPainted(true);
		ck6.setBackground(new Color(255, 255, 255));
		pePanel.add(ck6);
		
		JLabel label_13 = new JLabel("Lungs");
		label_13.setHorizontalAlignment(SwingConstants.TRAILING);
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_13);
		
		Component rigidArea_7 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_7);
		
		ck7 = new JCheckBox("");
		ck7.setEnabled(false);
		ck7.setBackground(new Color(255, 255, 255));
		ck7.setHorizontalAlignment(SwingConstants.CENTER);
		ck7.setBorderPainted(true);
		pePanel.add(ck7);
		
		JLabel label_14 = new JLabel("Abdomen");
		label_14.setHorizontalAlignment(SwingConstants.TRAILING);
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_14);
		
		Component rigidArea_8 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_8);
		
		ck8 = new JCheckBox("");
		ck8.setEnabled(false);
		ck8.setBackground(new Color(255, 255, 255));
		ck8.setHorizontalAlignment(SwingConstants.CENTER);
		ck8.setBorderPainted(true);
		pePanel.add(ck8);
		
		JLabel label_15 = new JLabel("Extremities");
		label_15.setHorizontalAlignment(SwingConstants.TRAILING);
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_15);
		
		Component rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_9);
		
		ck9 = new JCheckBox("");
		ck9.setEnabled(false);
		ck9.setBackground(new Color(255, 255, 255));
		ck9.setHorizontalAlignment(SwingConstants.CENTER);
		ck9.setBorderPainted(true);
		pePanel.add(ck9);
		
		JLabel label_16 = new JLabel("Gu/Reproductive");
		label_16.setHorizontalAlignment(SwingConstants.TRAILING);
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_16);
		
		Component rigidArea_10 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_10);
		
		ck10 = new JCheckBox("");
		ck10.setEnabled(false);
		ck10.setBackground(new Color(255, 255, 255));
		ck10.setHorizontalAlignment(SwingConstants.CENTER);
		ck10.setBorderPainted(true);
		pePanel.add(ck10);
		
		JLabel label_17 = new JLabel("Rectal");
		label_17.setHorizontalAlignment(SwingConstants.TRAILING);
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_17);
		
		Component rigidArea_11 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_11);
		
		ck11 = new JCheckBox("");
		ck11.setEnabled(false);
		ck11.setBackground(new Color(255, 255, 255));
		ck11.setHorizontalAlignment(SwingConstants.CENTER);
		ck11.setBorderPainted(true);
		pePanel.add(ck11);
		
		JLabel label_18 = new JLabel("Lab/EKG Results");
		label_18.setHorizontalAlignment(SwingConstants.TRAILING);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_18);
		
		Component rigidArea_12 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_12);
		
		ck12 = new JCheckBox("");
		ck12.setEnabled(false);
		ck12.setForeground(new Color(0, 0, 0));
		ck12.setBackground(new Color(255, 255, 255));
		ck12.setHorizontalAlignment(SwingConstants.CENTER);
		ck12.setBorderPainted(true);
		pePanel.add(ck12);
		
		JLabel label_19 = new JLabel("X-Ray Results");
		label_19.setHorizontalAlignment(SwingConstants.TRAILING);
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pePanel.add(label_19);
		
		Component rigidArea_13 = Box.createRigidArea(new Dimension(20, 20));
		pePanel.add(rigidArea_13);
		
		ck13 = new JCheckBox("");
		ck13.setEnabled(false);
		ck13.setBackground(new Color(255, 255, 255));
		ck13.setHorizontalAlignment(SwingConstants.CENTER);
		ck13.setBorderPainted(true);
		pePanel.add(ck13);
		
		peList = new ArrayList<JCheckBox>();
		
		peList.add(ck1);
		peList.add(ck2);
		peList.add(ck3);
		peList.add(ck4);
		peList.add(ck5);
		peList.add(ck6);
		peList.add(ck7);
		peList.add(ck8);
		peList.add(ck9);
		peList.add(ck10);
		peList.add(ck11);
		peList.add(ck12);
		peList.add(ck13);
		
		GroupLayout gl_hxMasterPanel = new GroupLayout(hxMasterPanel);
		gl_hxMasterPanel.setHorizontalGroup(
			gl_hxMasterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_hxMasterPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_hxMasterPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_hxMasterPanel.createSequentialGroup()
							.addComponent(lawPanel, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
							.addGap(3))
						.addGroup(gl_hxMasterPanel.createSequentialGroup()
							.addGroup(gl_hxMasterPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btPanel, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
								.addComponent(sctPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
								.addComponent(pshPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
								.addComponent(drugAllergyPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_hxMasterPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(pmhPanel, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
								.addComponent(fhPanel, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pePanel, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
					.addGap(9))
		);
		gl_hxMasterPanel.setVerticalGroup(
			gl_hxMasterPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_hxMasterPanel.createSequentialGroup()
					.addGroup(gl_hxMasterPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(pePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_hxMasterPanel.createSequentialGroup()
							.addGroup(gl_hxMasterPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_hxMasterPanel.createSequentialGroup()
									.addComponent(drugAllergyPanel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btPanel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
								.addComponent(pmhPanel, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_hxMasterPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(fhPanel, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
								.addComponent(pshPanel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addComponent(lawPanel, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))
					.addGap(31))
				.addGroup(gl_hxMasterPanel.createSequentialGroup()
					.addGap(162)
					.addComponent(sctPanel, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					.addGap(179))
		);
		btPanel.setLayout(new BoxLayout(btPanel, BoxLayout.Y_AXIS));
		pmhPanel.setLayout(new BoxLayout(pmhPanel, BoxLayout.Y_AXIS));
		drugAllergyPanel.setLayout(new BoxLayout(drugAllergyPanel, BoxLayout.Y_AXIS));
		hxMasterPanel.setLayout(gl_hxMasterPanel);
	}
}
