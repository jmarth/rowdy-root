package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

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
				
		// Create butZSton to add a New Allergy to a Patient
		JButton btnNewAllergy = new JButton("New Allergy");
		btnNewAllergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.indexOfTab("Allergies");
				tabbedPane.setComponentAt(index, new AllergyTabViewNewAllergy(tabbedPane, patient, AllergyTabView.this, atg, allergyTable, allergyList, al, null, false));
			}
		});
		GridBagConstraints gbc_btnNewAllergy = new GridBagConstraints();
		gbc_btnNewAllergy.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewAllergy.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewAllergy.gridx = 0;
		gbc_btnNewAllergy.gridy = 0;
		this.add(btnNewAllergy, gbc_btnNewAllergy);
		
		// add mouseListener to allergyTable to highlight row on hover
		allergyTable.addMouseMotionListener(new MouseMotionAdapter() {
			   public void mouseMoved(MouseEvent e) {
			      int row = allergyTable.rowAtPoint(e.getPoint());
			      if (row > -1) {
			         allergyTable.clearSelection();
			         allergyTable.setRowSelectionInterval(row, row);
			      }
			      else {
			         allergyTable.setSelectionBackground(Color.blue);
			      }
			      allergyTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
			   }
			});
		// Add mouseListener to allergyTable to open allergyDetailView
		allergyTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				// Get row number of allergy chosen
				int selectedRow = allergyTable.getSelectedRow();
				
				// Reload allergyList from gateway and get Allergy selected
				al.loadFromGateway();
				allergyList = al.getAllergyList();
				Allergy tmp = allergyList.get(selectedRow);
				
				// Get tab of allergies and change panel
				int index = tabbedPane.indexOfTab("Allergies");
				AllergyTabViewNewAllergy anv = new AllergyTabViewNewAllergy(tabbedPane, patient, AllergyTabView.this, atg, allergyTable, allergyList, al, tmp, true);
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
}
