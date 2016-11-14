package views;

import java.awt.BorderLayout;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.GatewayException;
import models.MasterModel;
import models.Vital;

@SuppressWarnings("serial")
public class VitalListView extends JPanel implements viewinterface {
	
	private JTable vitalsTable;
	private int selectedRow;
	
	public VitalListView() {
		super();
		selectedRow=-1;
		vitalsTable = new JTable();
		
		//vitalsTable.setEnabled(false);
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
				selectedRow =-1;
				VitalListView.this.ShowVitalSCRUDView();
			}
		});
		// remove vital button
		
		JButton btnRemoveVital = new JButton("Remove Vital");
		panelButtons.add(btnRemoveVital, BorderLayout.EAST);
		btnRemoveVital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					VitalListView.this.getMasterModel().getVitalsL().delete(selectedRow);
					((DefaultTableModel) vitalsTable.getModel()).removeRow(selectedRow);
				} catch (GatewayException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnUpdateVital = new JButton("Edit Vital");
		panelButtons.add(btnUpdateVital, BorderLayout.CENTER);
		btnUpdateVital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedRow!=-1){
					VitalListView.this.ShowVitalSCRUDView();
				}
			}
		});		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.add(this.vitalsTable);
		scrollPane.setViewportView(vitalsTable);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		vitalsTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent evt) {
				if(evt.getClickCount() == 1){
					selectedRow = vitalsTable.rowAtPoint(evt.getPoint());
					vitalsTable.setRowSelectionInterval(selectedRow, selectedRow);
					return;
				}
				// Get row number of vital chosen
				selectedRow = vitalsTable.getSelectedRow();
				
				if(selectedRow == -1) {
					return;
				}
				
				// this will all change for lazy load
				List<Vital> myVitalsList = VitalListView.this.getMasterModel().getVitalsL().getMyList();
				VitalListView.this.ShowVitalSCRUDView();
			}
		});
		
		// Add JTable to scrollPane
		
		//BP contains sys/dia then unit, etc and et al
		
		vitalsTable.setToolTipText("");
		vitalsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "BP", "BG", "O2", "Hb", "Height", "Weight", "Notes"
			}));
		
	}
	private void ShowVitalSCRUDView(){
		VitalsTabMasterView vtmv = (VitalsTabMasterView) this.getParent();
		vtmv.ShowVitalSCRUDView();
	}
	@Override
	public void HideallView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MasterModel getMasterModel() {
		return this.getHomeView().getMasterModel();
	}

	@Override
	public void ShowView() {
		reload();
		if(selectedRow!=-1)
			this.vitalsTable.setRowSelectionInterval(selectedRow, selectedRow);
		this.setVisible(true);
		
	}

	@Override
	public void reload() {
		// Get model of VitalsTable in order to add rows
				// Declare variables
				//DefaultTableModel dtm = (DefaultTableModel) vitalsTable.getModel();
				DefaultTableModel dtm = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Date", "BP", "BG", "O2", "Hb", "Height", "Weight", "Notes"
						}
					);
				// Find all allergies for the given patient
				List<Vital> myVitalsList = this.getMasterModel().getVitalsL().getMyList();
				
				/*
				 * For every vital in the vitalsList
				 * .. Add that model the JTable
				 * 
				 * If it is a height, must be displayed depending on ft/inches or inches or cm
				 */		
				
				for(Vital vitals : myVitalsList) {
					
					// read somewhere sb should get a size in bytes of
					// roughly how big of a string you are going to build
					StringBuilder sb = new StringBuilder(128);
					// the ft/inches display should be condensed into a method, but w/e for now
					boolean isString = false; 
			        String height_ftin_String = "";
			        int heightInt = -1;
			        if (vitals.getHUnit() == null) {
			           
			        } else if (vitals.getHUnit().equals(Vital.FTIN)) {
			           
			            sb.append(vitals.getHFeet());
			            sb.append('\'');
			            sb.append(vitals.getHInches());
			            sb.append('\"');
			            height_ftin_String = sb.toString();
			            isString = true;
			           
			        } else if (vitals.getHUnit().equals("null")) {
			            heightInt = -100;
			        } else if (vitals.getHUnit().equals(Vital.IN)) {
			            heightInt = vitals.getHInches();
			        } else {
			            heightInt = vitals.getHCm();
			        }
			        
					dtm.addRow(new Object[]{
							vitals.getDateCreated(),
							vitals.getBps() + "/" + vitals.getBps() + " " + vitals.getBpUnit(),
							vitals.getBg() + " " + vitals.getBgUnit(),
							vitals.getO2sat() + "%",
							vitals.getHb() + " " + Vital.gdL,
							(isString ? height_ftin_String : heightInt + " " + vitals.getHUnit()),
							vitals.getWeight() + " " + vitals.getWUnit(),
							vitals.getNotes()	
						});
				}
				this.vitalsTable.setModel(dtm);
	}
	
	public int getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}
	@Override
	public HomeView getHomeView() {
		return ((VitalsTabMasterView)(this.getParent())).getHomeView();
	}
	
}
