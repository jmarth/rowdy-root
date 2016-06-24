package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import database.GatewayException;
import database.PatientTableGateway;
import database.PatientTableGatewayMySQL;
import models.Patient;
import models.PatientList;

import java.awt.Font;

public class PatientsView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private PatientTableGateway ptg;
	private List<Patient> patientList;

	/**
	 * Create the frame.
	 */
	public PatientsView(final Home home) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
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
		
		ptg = null;
		
		//Try to connect to database
		try {
			ptg = new PatientTableGatewayMySQL();
		} catch (GatewayException e) {
			JOptionPane.showMessageDialog(null, "Database is not responding. Please reboot your computer and maybe the database will magically appear (not really).", "Database Offline!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Database is not responding. Please reboot your computer and maybe the database will magically appear (not really).", "Database Offline!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		//Set patients from database
		final PatientList pl = new PatientList();
		pl.setGateway(ptg);
		pl.loadFromGateway();
		patientList = pl.getPatientList();
		populatePatientTable(home);
		
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
			        Long patientId = (Long) table.getValueAt(row, 0);
			        System.out.println("id is "+patientId);
			        Patient patient = pl.findById(patientId);
			        System.out.println("name is "+patient.getFirstName());
			        PatientProfile pp = new PatientProfile(home, patient.getFirstName());
			        home.setCenterPanel(pp.getContentPane());
				}
			});
		
	}
	
	public Container getContentPane() {
		return contentPane;
	}
	
	public void populatePatientTable(Home home) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for(Patient patient : patientList) {
			String fullName =  patient.getFirstName()+" "+
					patient.getMiddleName()+" "+
					patient.getLastName();
			PatientProfile pp = new PatientProfile(home, fullName);
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

}
