package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import models.Allergy;
import models.AllergyList;
import models.CL;
import models.HomeModel;
import models.Patient;
import models.PatientList;
import models.Visit;
import models.VisitList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import java.awt.GridLayout;

public class VisitsTabView extends JPanel {
	private Patient patient;
	private List<Visit> patientVisitList = new ArrayList<Visit>();
	private HomeModel homeModel;
	private JScrollPane scroller;
	private JXTaskPaneContainer mainTaskPane;
	private JTabbedPane tabbedPane;	
	private JPanel scrollPanel;
	private JLabel iconLabel;
	private JSplitPane splitPane;
	public VisitsTabView(final Patient patient, final JTabbedPane tabbedPane, final HomeModel homeModel) {
		this.patient = patient;
		this.homeModel = homeModel;
		this.tabbedPane = tabbedPane;
		
		setLayout(new BorderLayout(0, 0));
		
		iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon("medical_history_icon.jpg"));
		iconLabel.setFont(new Font("Roboto", Font.BOLD, 30));
				
		mainTaskPane = new JXTaskPaneContainer();
		
		populatePanes();
		
		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.5);
		splitPane.setDividerSize(3);
		
		
		JButton btnNewVisit = new JButton("Add A New Visit");
		btnNewVisit.setBackground(CL.cararra);
		btnNewVisit.setOpaque(true);
		btnNewVisit.setBorderPainted(false);
		btnNewVisit.setForeground(CL.colorBlue);
		btnNewVisit.setFont(new Font("Tahoma", Font.BOLD, 16));

		
		JScrollPane leftPane = new JScrollPane(mainTaskPane);
		
		splitPane.setLeftComponent(leftPane);
		splitPane.setRightComponent(btnNewVisit);
		
		add(splitPane, BorderLayout.CENTER);
	
		btnNewVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitTabViewNewVisit vtvnv = new VisitTabViewNewVisit(tabbedPane, patient, homeModel);
				JScrollPane rightPane = new JScrollPane(vtvnv);
				splitPane.setRightComponent(rightPane);
			}
		});
	}
	
	public void populatePanes() {
		mainTaskPane.removeAll();

		Collection<Visit> coll = (Collection<Visit>) homeModel.getVl().getMyPidMap().get(patient.getId());
		if (coll != null) {
			patientVisitList = (List<Visit>) coll;
		}
		
		iconLabel.setText("\t\t" + patientVisitList.size() + " visits");
		mainTaskPane.add(iconLabel, BorderLayout.NORTH);
		mainTaskPane.setBackground(CL.blueGrey);

				
		int i;
		for (i = patientVisitList.size() - 1; i >= 0; i--) {
			Visit v = patientVisitList.get(i);
			JXTaskPane pane = new JXTaskPane();
			String date = v.getDateCreated();
			date = date.substring(0, date.length() - 3);
			String[] data = date.split(" ");
			pane.setTitle("Date: " + data[0] + ", Time: " + data[1] + "\t|\t" + v.getChiefComplaint());
			pane.setAnimated(false);
			pane.setCollapsed(true);
			pane.setScrollOnExpand(true);
			pane.add(new VisitTabViewNewVisit(v, patient, tabbedPane, homeModel, false));
			mainTaskPane.add(pane);
		}
		
	}
}
