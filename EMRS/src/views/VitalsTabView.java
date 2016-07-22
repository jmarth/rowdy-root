package views;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.GatewayException;
import database.VitalsTableGateway;
import models.Patient;
import models.Tabs;
import models.Vitals;
import models.VitalsList;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class VitalsTabView extends JPanel {
	
	/*
	private final String bpunit_mmHg = "mmHg";
	private final String bpunit_Pa = "Pa";
	private final String hunit_ftIn = "\'";
	private final String hunit_in = "\"";
	private final String hunit_cm= "cm";
	*/
	
	private VitalsList vl = new VitalsList();
	private List<Vitals> myVitalsList;
	private VitalsTableGateway vtg;
	private JTable vitalsTable = new JTable();
	private Patient patient;
	private int selectedRow;
	
	/**
	 * Create the panel.
	 */
	public VitalsTabView(final Patient patient, final JTabbedPane tabbedPane, final VitalsTableGateway vtg) {
		
		this.vtg = vtg;
		this.patient = patient;
		
		vitalsTable.setEnabled(false);
	
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panelButtons = new JPanel();
		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.fill = GridBagConstraints.BOTH;
		gbc_panelButtons.insets = new Insets(0, 0, 5, 0);
		gbc_panelButtons.gridx = 0;
		gbc_panelButtons.gridy = 0;
		add(panelButtons, gbc_panelButtons);
		panelButtons.setLayout(new BorderLayout(0, 0));
		
		
		
		// new vital button
		
		JButton btnNewVital = new JButton("New Vital");
		panelButtons.add(btnNewVital, BorderLayout.WEST);
		btnNewVital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.indexOfTab(Tabs.vitals);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, new VitalsTabNewVitalsView(tabbedPane, patient, VitalsTabView.this, vtg, vitalsTable, myVitalsList, vl, null, false));
			}
		});
		
		
		
		// remove vital button
		
		JButton btnRemoveVital = new JButton("Remove Vital");
		panelButtons.add(btnRemoveVital, BorderLayout.EAST);
		btnRemoveVital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vitals vtr = myVitalsList.get(selectedRow);
				try {
					vtg.removeVitals(vtr.getId());
				} catch (GatewayException e1) {
					e1.printStackTrace();
				}
				vl.loadFromGateway();
				myVitalsList = vl.getVitalsList();
				
				((DefaultTableModel) vitalsTable.getModel()).removeRow(selectedRow);
			}
		});
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		
		
		// vitals table update listener
		
		vitalsTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent evt) {
				
				if(evt.getClickCount() == 1){
					
					selectedRow = vitalsTable.rowAtPoint(evt.getPoint());
					vitalsTable.setRowSelectionInterval(selectedRow, selectedRow);
					return;
				}
				
				// Get row number of vital chosen
				int selectedRow = vitalsTable.getSelectedRow();
				
				if(selectedRow == -1) {
					return;
				}
				
				// Reload vitalsList from gateway and get Vital selected
				vl.loadFromGateway();
				
				//vl.getVitalsListForPatient(patient);
				//System.out.println("\nvitals list from gw:");
				//for (Vitals v : vl.getVitalsList()) {
				//	System.out.println(v.getId());
				//}
				
				// this will all change for lazy load
				
				
				myVitalsList = vl.getVitalsListForPatient(patient);
				//Vitals tmp = vitalsList.get(selectedRow);
				
				Vitals tmp = myVitalsList.get(selectedRow);
				
				//System.out.println();
				//System.out.println("patient id = "+patient.getId());
				//System.out.println("selected row: "+selectedRow+"\tvid: "+tmp.getId());

				// Get tab of vitals and change panel
				VitalsTabNewVitalsView vnv = new VitalsTabNewVitalsView(tabbedPane, patient, VitalsTabView.this, vtg, vitalsTable, myVitalsList, vl, tmp, true);
				
				// swap the center component
				int index = tabbedPane.indexOfTab(Tabs.vitals);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, vnv);
			}
		});
		
		// Add JTable to scrollPane
		vitalsTable.setToolTipText("");
		vitalsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"BPS", "BPD", "BP Unit", "Height", "Height Unit", "Weight", "Weight Unit", "Notes"
			}
		)/* {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		}*/);
		
		vitalsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		vitalsTable.getColumnModel().getColumn(1).setPreferredWidth(30);
		vitalsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		vitalsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		vitalsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		vitalsTable.getColumnModel().getColumn(5).setPreferredWidth(40);
		vitalsTable.getColumnModel().getColumn(6).setPreferredWidth(30);
		vitalsTable.getColumnModel().getColumn(7).setPreferredWidth(100);
		
		populateVitalsTable();
		
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
		DefaultTableModel dtm = (DefaultTableModel) vitalsTable.getModel();
		
		vl.setGateway(vtg);
		vl.loadFromGateway();
		
		System.out.print("printing list "+vl);
		
		// Find all allergies for the given patient
		myVitalsList = vl.getVitalsListForPatient(patient);
		
		/*
		 * For every vital in the vitalsList
		 * .. Add that model the JTable
		 * 
		 * If it is a height, must be displayed depending on ft/inches or inches or cm
		 */		
		
		for(Vitals v : myVitalsList) {
			
			// read somewhere sb should get a size in bytes of
			// roughly how big of a string you are going to build
			StringBuilder sb = new StringBuilder(128);
			
			// the ft/inches display should be condensed into a method, but w/e for now
			boolean isString = false;
		       
	        String height_ftin_String = "";
	        int heightInt = -1;
	       
	        if (v.getHUnit() == null) {
	           
	        } else if (v.getHUnit().equals(Vitals.FTIN)) {
	           
	            sb.append(v.getHFeet());
	            sb.append('\'');
	            sb.append(v.getHInches());
	            sb.append('\"');
	            height_ftin_String = sb.toString();
	            isString = true;
	           
	        } else if (v.getHUnit().equals("null")) {
	            heightInt = -100;
	        } else if (v.getHUnit().equals(Vitals.IN)) {
	            heightInt = v.getHInches();
	        } else {
	            heightInt = v.getHCm();
	        }
	        
			dtm.addRow(new Object[]{
					v.getBps(),
	                v.getBps(),
	                v.getBpUnit(),
	                (isString ? height_ftin_String : heightInt),
	                v.getHUnit(),
	                v.getWeight(),
	                v.getWUnit(),
	                v.getNotes()
				});
		}
		
		
		/*
		DefaultTableModel model = (DefaultTableModel) vitalsTable.getModel();
		
		/**
		 * Try to connect to DB through VitalsTableGateway
		 * Set the gateway of the VitalsList
		 * Load Vitals into the VitalsList
		 
		try {
			vtg = new VitalsTableGatewaySQLite();
			vl.setGateway(vtg);
			vl.loadFromGateway();
		} catch (GatewayException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		}
		
		// Find all vitals for the given patient
		vitalsList = vl.getVitalsListForPatient(patient);
		
		/**
		 * For every vitals in the vitalsList
		 * .. Add that model the JTable
		 
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
			
		    //int height_int = vitals.getHcm();
			model.addRow(new Object[]{
					vitals.getBps(), vitals.getBpd(), vitals.getBpUnit(), vitals.getHcm(), vitals.gethUnit(), vitals.getWeight(), vitals.getwUnit(), vitals.getNotes()
				});
		}
		*/
	}
}
