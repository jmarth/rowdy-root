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
import database.VitalsTableGatewayMySQL;
import models.Patient;
import models.Tabs;
import models.Vitals;
import models.VitalsList;

public class VitalsTabView extends JPanel {
	
	private final String bpunit_mmHg = "mmHg";
	private final String bpunit_Pa = "Pa";
	private final String hunit_ftIn = "\'";
	private final String hunit_in = "\"";
	private final String hunit_cm= "cm";
	
	private VitalsList vitalsl = new VitalsList();
	private List<Vitals> vitalsList;
	private VitalsTableGateway vitalstg;
	private JTable vitalsTable = new JTable();
	private Patient patient;
	
	/**
	 * Create the panel.
	 */
	public VitalsTabView(final Patient patient, final JTabbedPane tabbedPane) {
		
		//createView();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnNewVital = new JButton("New Vital");
		btnNewVital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.indexOfTab(Tabs.vitals);
				tabbedPane.setComponentAt(index, null);
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
		
		// Add JTable to scrollPane
		vitalsTable.setToolTipText("");
		vitalsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"BPS", "BPD", "BP Unit", "Height", "Height Unit", "Weight", "Weight Unit", "Notes"
			}
		) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		vitalsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		vitalsTable.getColumnModel().getColumn(1).setPreferredWidth(30);
		vitalsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		vitalsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		vitalsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		vitalsTable.getColumnModel().getColumn(5).setPreferredWidth(40);
		vitalsTable.getColumnModel().getColumn(6).setPreferredWidth(30);
		//vitalsTable.getColumnModel().getColumn(7).setPreferredWidth(100);
		//populateVitalsTable(); TODO
		scrollPane.setViewportView(vitalsTable);
	}
	
	/**
	 * Populates the VitalsTable with all vitals related current Patient
	 * @param vitalsTable JTable to populate
	 * @param patient Patient JTable to populate
	 *
	 **/
	private void populateVitalsTable() {
		// Get model of VitalsTable in order to add rows
		// Declare variables
		DefaultTableModel model = (DefaultTableModel) vitalsTable.getModel();
		
		/**
		 * Try to connect to DB through VitalsTableGateway
		 * Set the gateway of the VitalsList
		 * Load Vitals into the VitalsList
		 */
		try {
			vitalstg = new VitalsTableGatewayMySQL();
			vitalsl.setGateway(vitalstg);
			vitalsl.loadFromGateway();
		} catch (GatewayException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		}
		
		// Find all vitals for the given patient
		vitalsList = vitalsl.getVitalsListForPatient(patient);
		
		/**
		 * For every vitals in the vitalsList
		 * .. Add that model the JTable
		 */
		for(Vitals vitals : vitalsList) {
			/*
			int height_int;
			if (vitals.gethFeet() != -1) {
				// feet is available, so is inches
				height_int = vitals.gethFeet();
			} else if (vitals.gethInches() != -1) {
				// only inches, no feet
			} else {
				// default
			}
			*/
		    //int height_int = vitals.getHcm();
			model.addRow(new Object[]{
					vitals.getBps(), vitals.getBpd(), vitals.getBpUnit(), vitals.getHcm(), vitals.gethUnit(), vitals.getWeight(), vitals.getwUnit(), vitals.getNotes()
				});
		}
	}

}
