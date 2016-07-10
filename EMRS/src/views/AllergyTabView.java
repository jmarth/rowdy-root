package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.AllergyTableGateway;
import database.AllergyTableGatewayMySQL;
import database.GatewayException;
import models.Allergy;
import models.AllergyList;
import models.Patient;

/**
 * This is the JPanel that is shown in the 
 * 'Allergies' tab in the PatientRecordView
 */
public class AllergyTabView extends JPanel {
	private AllergyList al = new AllergyList();
	private List<Allergy> allergyList;
	private AllergyTableGateway atg;
	private JTable allergyTable = new JTable();
	private Patient patient;
	
	public AllergyTabView(final Patient patient, final JTabbedPane tabbedPane, final AllergyTableGateway atg) {
		this.atg = atg;
		this.patient = patient;
		
		GridBagLayout gbl_allergiesPanel = new GridBagLayout();
		gbl_allergiesPanel.columnWeights = new double[]{1.0};
		gbl_allergiesPanel.rowWeights = new double[]{0.0, 1.0};
		this.setLayout(gbl_allergiesPanel);
				
		// Create button to add a New Allergy to a Patient
		JButton btnNewAllergy = new JButton("New Allergy");
		btnNewAllergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.indexOfTab("Allergies");
				tabbedPane.setComponentAt(index, new AllergyTabViewNewAllergy(tabbedPane, patient, AllergyTabView.this, atg, allergyTable));
			}
		});
		GridBagConstraints gbc_btnNewAllergy = new GridBagConstraints();
		gbc_btnNewAllergy.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewAllergy.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewAllergy.gridx = 0;
		gbc_btnNewAllergy.gridy = 0;
		this.add(btnNewAllergy, gbc_btnNewAllergy);
				
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
				"Allergy", "Severity", "Adverse Reaction", "Date"
			}
		) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		allergyTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		allergyTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		allergyTable.getColumnModel().getColumn(2).setPreferredWidth(175);
		allergyTable.getColumnModel().getColumn(3).setPreferredWidth(135);
		populateAllergyTable();
		scrollPane.setViewportView(allergyTable);
	}
	
	/**
	 * Populates the AllergyTable with all allergies related current Patient
	 * @param allergyTable JTable to populate
	 * @param patient Patient JTable to populate
	 */
	private void populateAllergyTable(){
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
}
