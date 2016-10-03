package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import models.MasterModel;
import models.Patient;
import models.PatientList;

@SuppressWarnings("serial")
public class FindPatientsView extends JPanel implements viewinterface  {
	
	private JTable table;
	private JScrollPane scrollPane;
	/**
	 * Create the frame.
	 */
	public FindPatientsView() {
		super();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0,1,0,0));
		//this.setLayout(new BorderLayout());
		scrollPane = new JScrollPane();
		this.add(scrollPane);
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Identifier", "Name", "Gender", "Age", "Birthdate"
			}
		));
		//this.add(table,BorderLayout.CENTER);
		scrollPane.setViewportView(table);			
		//Set patients from database
		table.addMouseMotionListener(new MouseMotionAdapter() {
		   public void mouseMoved(MouseEvent e) {
		      int row = table.rowAtPoint(e.getPoint());
		      if (row > -1) {
		         table.clearSelection();
		         table.setRowSelectionInterval(row, row);
		      }
		      else {
		         table.setSelectionBackground(Color.blue);
		      }
		      table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		   }
		});
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
//		        Long patientId = (Long) table.getValueAt(row, 0); TODO why this here no use
		        Patient patient = FindPatientsView.this.getMasterModel().getpL().getMyList().get(row);
		        //FindPatientsView.this.getMasterModel().setCurrPatient(patient);
		        FindPatientsView.this.getMasterModel().loadmaster(patient);
		        showdemographic();
			}
		});
		
	}
	private void showdemographic(){
		HomeView hv = this.getHomeView();
		hv.ShowPatientRecode();
		hv.getPrview().ShowDemographicsView();
	}	
	public void filter(String searchText) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(dtm);
		table.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(searchText));
	}
	@Override
	public void HideallView() {
		this.setVisible(false);
	}
	@Override
	public void ShowView() {
		this.reload();
		//scrollPane.setBounds(this.getBounds());
		this.setVisible(true);
	}
	@Override
	public MasterModel getMasterModel() {
		return this.getHomeView().getMasterModel();
	}
	@Override
	public void reload() {
		this.getMasterModel().getpL().loadFromGateway();
		PatientList patientList = this.getMasterModel().getpL();
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Identifier", "Name", "Gender", "Age", "Birthdate"
				});
		table.setRowSorter(null);
		table.setModel(model);		  
		for(Patient patient : patientList.getMyList()) {
			String fullName =  patient.getFirstName()+" "+
					patient.getMiddleName()+" "+
					patient.getLastName();
			String age = patient.getAge()+"";
			String birthDate = patient.getBirthMonth()+"-"+
					patient.getBirthDay()+"-"+
					patient.getBirthYear();
			if(patient.getHasEstBirthDate()) {
				age = "~"+age;
				birthDate = "~"+birthDate;
			}
			model.addRow(new Object[]{patient.getId(), fullName, patient.getGender(), age, birthDate});
		}
	}
	@Override
	public HomeView getHomeView() {
		return (HomeView)SwingUtilities.getWindowAncestor(this);
	}

}
