package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import models.Allergy;
import models.AllergyList;
import models.Patient;
import models.PatientList;
import models.Visit;
import models.VisitList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.table.DefaultTableModel;

import database.AllergyTableGatewayMySQL;
import database.GatewayException;
import database.PatientTableGateway;
import database.PatientTableGatewayMySQL;
import database.VisitTableGateway;
import database.VisitTableGatewayMySQL;

import java.awt.GridLayout;

public class VisitsTabView extends JPanel{
	private JTable visitsTable;
	private Patient patient;
	private VisitTableGateway vtg;
	private List<Visit> visitList;
	private VisitList vl;
	
	public VisitsTabView(final Patient patient, final JTabbedPane tabbedPane, final VisitTableGateway vtg, final VisitList vl) {
		this.patient = patient;
		this.vtg = vtg;
		this.vl = vl;
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{187, 75, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnNewVisit = new JButton("New Visit");
		GridBagConstraints gbc_btnNewVisit = new GridBagConstraints();
		gbc_btnNewVisit.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewVisit.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewVisit.gridx = 0;
		gbc_btnNewVisit.gridy = 0;
		panel.add(btnNewVisit, gbc_btnNewVisit);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		visitsTable = new JTable();
		scrollPane.setViewportView(visitsTable);
		visitsTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"Visit History"
			}
		));
	
		
		visitsTable.addMouseMotionListener(new MouseMotionAdapter() {
			   public void mouseMoved(MouseEvent e) {
			      int row = visitsTable.rowAtPoint(e.getPoint());
			      if (row > -1) {
			    	  visitsTable.clearSelection();
			    	  visitsTable.setRowSelectionInterval(row, row);
			      }
			      else {
			    	  visitsTable.setSelectionBackground(Color.blue);
			      }
			      visitsTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
			   }
			});
		visitsTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					int row = visitsTable.rowAtPoint(evt.getPoint());
					int index = tabbedPane.indexOfTab("Visits");
					System.out.println(visitList);
					Visit v = visitList.get(row-1);
					System.out.println(v.getChiefComplaint());
					System.out.println(v.getAutorefractionOdSphere());
					System.out.println(v.getAutorefractionOdCylinder());
					System.out.println(v.getAutorefractionOdAxis());
					System.out.println(v.getAutorefractionOsSphere());
					System.out.println(v.getAutorefractionOsCylinder());
					System.out.println(v.getAutorefractionOsdAxis());
					System.out.println(v.getArcOdSphere());
					System.out.println(v.getArcOdCylinder());
					System.out.println(v.getArcOdAxis());
					System.out.println(v.getArcOsSphere());
					System.out.println(v.getArcOsCylinder());
					System.out.println(v.getArcOsAxis());
					System.out.println(v.getFeRow1Col1());
					System.out.println(v.getFeRow1Col2());
					System.out.println(v.getFeRow2Col1());
					System.out.println(v.getFeRow2Col2());
					System.out.println(v.getAssessment());
		
					VisitTabViewNewVisit nv = new VisitTabViewNewVisit(v.getChiefComplaint(),
							v.getAutorefractionOdSphere()+"",
							v.getAutorefractionOdCylinder()+"",
							v.getAutorefractionOdAxis()+"",
							v.getAutorefractionOsSphere()+"",
							v.getAutorefractionOsCylinder()+"",
							v.getAutorefractionOsdAxis()+"",
							v.getArcOdSphere()+"",
							v.getArcOdCylinder()+"",
							v.getArcOdAxis()+"",
							v.getArcOsSphere()+"",
							v.getArcOsCylinder()+"",
							v.getArcOsAxis()+"",
							v.getFeRow1Col1()+"",
							v.getFeRow1Col2()+"",
							v.getFeRow2Col1()+"",
							v.getFeRow2Col2()+"",
							v.getAssessment(),
							patient,
							tabbedPane,
							vtg,
							vl);
					tabbedPane.setComponentAt(index, nv);//, atg, allergyTable));
				}
			});
	
		btnNewVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.indexOfTab("Visits");
				tabbedPane.setComponentAt(index, new VisitTabViewNewVisit(tabbedPane, patient));//, atg, allergyTable));
			}
		});
	}
	
	public void populateVisitTable(){
		// Get model of VisitTable in order to add rows
		// Declare variables
		DefaultTableModel model = (DefaultTableModel) visitsTable.getModel();
		model.setRowCount(0);

		System.out.println("\ngetting patients visits");
		// Find all allergies for the given patient
		visitList = vl.getVisitListForPatient(patient);
		System.out.println("\ngot patients visits");
		/**
		 * For every allergy in the allergyList
		 * .. Add that model the JTable
		 */

		for(Visit visit : visitList) {
			model.addRow(new Object[]{
					visit.getDateCreated()
				});
		}
	}
}
