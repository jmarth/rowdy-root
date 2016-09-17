package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.GatewayException;
import database.PatientTableGateway;
import database.PatientTableGatewaySQLite;
import models.MasterModel;
import models.Patient;
import models.PatientList;

public class FindPatientsView extends JPanel implements viewinterface  {
	
	private MasterModel model;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public FindPatientsView(MasterModel model) {
		this.model = model;
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane();
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
		scrollPane.setViewportView(table);	
				
		//Set patients from database
		populatePatientTable();
		table.addMouseMotionListener(new MouseMotionAdapter() {
		   public void mouseMoved(MouseEvent e) {
		      int row = table.rowAtPoint(e.getPoint());
		      if (row > -1) {
		         table.clearSelection();
		         table.setRowSelectionInterval(row, row);
		      }
		      else {
		         table.setSelectionBackground(Color.blue);
		         Long patientID = (Long) table.getValueAt(row, 0);
		      }
		      table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		   }
		   HomeView a;
		});
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
		        Long patientId = (Long) table.getValueAt(row, 0);
		        Patient patient = FindPatientsView.this.model.getpL().getMyList().get(row);
		        FindPatientsView.this.model.loadmaster(patient);
		        showdemographic();
			}
		});
		
	}
	private void showdemographic(){
		HomeView hv = (HomeView) this.getParent();
		hv.HideallView();
		hv.getPrview().HideallView();
	}
	public void populatePatientTable() {
		PatientList patientList = this.getMasterModel().getpL();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
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
		this.populatePatientTable();
		this.setVisible(true);
	}
	@Override
	public MasterModel getMasterModel() {
		return ((HomeView)this.getParent()).getMasterModel();
	}

}
