package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import database.AllergyTableGateway;
import database.GatewayException;
import database.MedicationsTableGateway;
import models.Allergy;
import models.AllergyList;
import models.CL;
import models.Med;
import models.Patient;
import models.Tabs;

public class hxView extends JPanel {
	private AllergyList al = new AllergyList();
	private List<Allergy> allergyList;
	private AllergyTableGateway atg;
	private MedicationsTableGateway mtg;
	private Patient patient;
	private JTable allergyTable = new JTable();
	
	private JScrollPane allergyScroller;
	private JScrollPane medScroller;
	
	private JPanel allergyMasterPanel;
	private JPanel medMasterPanel;
	private JTable medTable;
	private JPanel medPanel;
	private JPanel allergyPanel;

	/**
	 * Create the panel.
	 */
	public hxView(final Patient patient, final JTabbedPane tabbedPane, final AllergyTableGateway atg, final MedicationsTableGateway mtg) {
		
		this.atg = atg;
		this.mtg = mtg;
		this.patient = patient;
		
		setBackground(CL.belize);
		
		allergyMasterPanel = new JPanel();
		allergyMasterPanel.setForeground(Color.BLACK);
		allergyMasterPanel.setBackground(CL.belize);
		allergyMasterPanel.setBorder(new TitledBorder(new LineBorder(CL.blueGrey, 2, false), "ALLERGIES", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 20), new Color(255, 255, 255)));
		
		medMasterPanel = new JPanel();
		medMasterPanel.setBackground(CL.belize);
		medMasterPanel.setBorder(new TitledBorder(new LineBorder(CL.blueGrey, 2, false), "MEDICATIONS", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 20), new Color(255, 255, 255)));
		
		JPanel hxMasterPanel = new JPanel();
		hxMasterPanel.setBackground(CL.belize);
		hxMasterPanel.setBorder(new TitledBorder(new LineBorder(CL.blueGrey, 2, false), "HEALTH HISTORY", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 20), new Color(255, 255, 255)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(hxMasterPanel, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(allergyMasterPanel, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(medMasterPanel, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(medMasterPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
						.addComponent(allergyMasterPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(hxMasterPanel, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
					.addContainerGap())
		);
		medMasterPanel.setLayout(new BorderLayout(0, 0));
		
		medPanel = new JPanel();
		medMasterPanel.add(medPanel, BorderLayout.CENTER);
		medPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel medButtonPanel = new JPanel();
		medButtonPanel.setBackground(CL.belize);
		medPanel.add(medButtonPanel, BorderLayout.NORTH);

		
		JButton btnAddMED = new JButton("ADD");
		medButtonPanel.add(btnAddMED);
		
		JButton btnEditMED = new JButton("EDIT");
		medButtonPanel.add(btnEditMED);		
		
		
		medTable = new JTable();
		
		medTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Name", "Started Taking", "Reason"
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
		
		
		
		allergyMasterPanel.setLayout(new BorderLayout(0, 0));
		
		allergyPanel = new JPanel();
		allergyMasterPanel.add(allergyPanel, BorderLayout.CENTER);
		allergyPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel allergyButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		allergyPanel.add(allergyButtonPanel, BorderLayout.NORTH);
		allergyButtonPanel.setBackground(CL.belize);
		
		JButton btnAddAllergy = new JButton("ADD");
		btnAddAllergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.indexOfTab(Tabs.hx);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, new AllergyTabViewNewAllergy(tabbedPane, patient, hxView.this, atg, allergyTable, allergyList, al, null, false));				
			}
		});
		allergyButtonPanel.add(btnAddAllergy);
		
		JButton btnEditAllergy = new JButton("EDIT");
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
		allergyButtonPanel.add(btnEditAllergy);
		
		
		allergyScroller = new JScrollPane(allergyTable);
		allergyPanel.add(allergyScroller, BorderLayout.CENTER);
		setLayout(groupLayout);

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
		

		
		// Get model of MedTable in order to add rows
		// Declare variables
		DefaultTableModel model = (DefaultTableModel) medTable.getModel();
		
						
		/**
		 * For every med in the medList
		 * .. Add that model the JTable
		 */
		for(Med med : tmpList) {
			model.addRow(new Object[]{
					med.getName(), 
					med.getDate(), 
					med.getReason()
				});
		}
	}
}
