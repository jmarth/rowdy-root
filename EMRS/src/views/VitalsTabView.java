package views;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.GatewayException;
import database.VitalsTableGateway;
import models.Patient;
import models.Vitals;

public class VitalsTabView extends JPanel {
	//private VitalsList vitalsl = new VitalsList();
	private List<Vitals> vitalsList;
	private VitalsTableGateway vitalstg;
	private JTable vitalsTable = new JTable();
	private Patient patient;
	/**
	 * Create the panel.
	 */
	public VitalsTabView(final Patient patient, final JTabbedPane tabbedPane) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnNewVital = new JButton("New Vital");
		btnNewVital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.indexOfTab("Vitals");
				tabbedPane.setComponentAt(index, new VitalsTabNewVitalsView(tabbedPane, patient, VitalsTabView.this, vitalstg, vitalsTable));
			}
		});
		GridBagConstraints gbc_btnNewVital = new GridBagConstraints();
		gbc_btnNewVital.anchor = GridBagConstraints.WEST;
		gbc_btnNewVital.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewVital.gridx = 0;
		gbc_btnNewVital.gridy = 0;
		add(btnNewVital, gbc_btnNewVital);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		/*
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
		*/
	}
	
	/**
	 * Populates the AllergyTable with all allergies related current Patient
	 * @param allergyTable JTable to populate
	 * @param patient Patient JTable to populate
	 *
	private void populateAllergyTable(){
		// Get model of AllergyTable in order to add rows
		// Declare variables
		DefaultTableModel model = (DefaultTableModel) allergyTable.getModel();
		
		/**
		 * Try to connect to DB through AllergyTableGateway
		 * Set the gateway of the AllergyList
		 * Load Allergies into the AllergyList
		 *
		try {
			atg = new AllergyTableGatewayMySQL();
			al.setGateway(atg);
			al.loadFromGateway();
		} catch (GatewayException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		}
		
		// Find all allergies for the given patient
		allergyList = al.getAllergyListForPatient(patient);
		
		/**
		 * For every allergy in the allergyList
		 * .. Add that model the JTable
		 *
		for(Allergy allergy : allergyList) {
			model.addRow(new Object[]{
					allergy.getAllergy(), 
					allergy.getSeverity(), 
					allergy.getAdverseReaction()
				});
		}
	}
	*/

}
