package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.AllergyTableGateway;
import database.GatewayException;
import models.Allergy;
import models.AllergyList;
import models.Patient;
import models.Tabs;

/**
 * This is the JPanel that is shown in the 
 * 'Allergies' tab in the PatientRecordView
 */
public class AllergyTabView extends JPanel implements viewinterface  {
	private AllergyList al = new AllergyList();
	private List<Allergy> allergyList;
	private AllergyTableGateway atg;
	private JTable allergyTable = new JTable();
	private Patient patient;
	private int selectedRow;
	
	public AllergyTabView(final Patient patient, final JTabbedPane tabbedPane, final AllergyTableGateway atg) {
		
		this.atg = atg;
		this.patient = patient;
		allergyTable.setEnabled(false);
		
		
		
		GridBagLayout gbl_allergiesPanel = new GridBagLayout();
		gbl_allergiesPanel.columnWeights = new double[]{1.0};
		gbl_allergiesPanel.rowWeights = new double[]{0.0, 1.0};
		this.setLayout(gbl_allergiesPanel);
		
		
		
		// Create button to add a New Allergy to a Patient
		
		JButton btnNewAllergy = new JButton("New Allergy");
		btnNewAllergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.indexOfTab(Tabs.allergiesAndMeds);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, new AllergyTabViewNewAllergy(tabbedPane, patient, AllergyTabView.this, atg, allergyTable, allergyList, al, null, false));
			}
		});
		
		
		
		GridBagConstraints gbc_btnNewAllergy = new GridBagConstraints();
		gbc_btnNewAllergy.anchor = GridBagConstraints.WEST;
		gbc_btnNewAllergy.insets = new Insets(0, 10, 5, 10);
		gbc_btnNewAllergy.gridx = 0;
		gbc_btnNewAllergy.gridy = 0;
		this.add(btnNewAllergy, gbc_btnNewAllergy);
		
		
		
		// Create button to remove an Allergy from a Patient
		
		JButton btnRemoveAllergy = new JButton("Remove Allergy");
		btnRemoveAllergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Allergy atr = allergyList.get(selectedRow);
				try {
					atg.removeAllergy(atr.getId());
				} catch (GatewayException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				al.loadFromGateway();
				allergyList = al.getAllergyList();
				
				DefaultTableModel dtm = (DefaultTableModel)allergyTable.getModel();
				dtm.removeRow(selectedRow);
			}
		});
		
		
		
		GridBagConstraints gbc_btnRemoveAllergy = new GridBagConstraints();
		gbc_btnRemoveAllergy.anchor = GridBagConstraints.EAST;
		gbc_btnRemoveAllergy.insets = new Insets(0, 10, 5, 10);
		gbc_btnRemoveAllergy.gridx = 0;
		gbc_btnRemoveAllergy.gridy = 0;
		this.add(btnRemoveAllergy, gbc_btnRemoveAllergy);
		
		
		
		// Add mouseListener to allergyTable to open allergyDetailView
		
		allergyTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent evt) {
				
				if(evt.getClickCount() == 1){
					selectedRow = allergyTable.rowAtPoint(evt.getPoint());
					allergyTable.setRowSelectionInterval(selectedRow, selectedRow);
					return;
				}
				
				// Get row number of allergy chosen
				int selectedRow = allergyTable.getSelectedRow();
				
				if(selectedRow == -1) {
					return;
				}
				
				// Reload allergyList from gateway and get Allergy selected
				al.loadFromGateway();
				//allergyList = al.getAllergyList();
				
				allergyList = al.getAllergyListForPatient(patient);
				Allergy tmp = allergyList.get(selectedRow);
				
				// Get tab of allergies and change panel
				AllergyTabViewNewAllergy anv = new AllergyTabViewNewAllergy(tabbedPane, patient, AllergyTabView.this, atg, allergyTable, allergyList, al, tmp, true);
				
				int index = tabbedPane.indexOfTab(Tabs.allergiesAndMeds);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, anv);
			}
		});
		
		
		
		// Add scrollPane to fit JTable inside of for list of Allergies
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		this.add(scrollPane, gbc_scrollPane);

		
		
		// Add JTable to scrollPane
		
		allergyTable.setToolTipText("");
		allergyTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Allergy", "Severity", "Adverse Reaction"
			}
		));
		
		allergyTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		allergyTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		allergyTable.getColumnModel().getColumn(2).setPreferredWidth(175);
		
		populateAllergyTable();
		
		scrollPane.setViewportView(allergyTable);
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
		
		System.out.print("printing list"+al);
		
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

	@Override
	public void showview() {
		// TODO Auto-generated method stub
		
	}
}
