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
import models.Tabs;
import models.Visit;
import models.VisitList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
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
	private JSplitPane splitPane = new JSplitPane();
	
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
						
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{187, 75, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnNewVisit = new JButton("Add A New Visit");
		GridBagConstraints gbc_btnNewVisit = new GridBagConstraints();
		gbc_btnNewVisit.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewVisit.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewVisit.gridx = 0;
		gbc_btnNewVisit.gridy = 0;
		panel.add(btnNewVisit, gbc_btnNewVisit);
		
		add(panel, BorderLayout.NORTH);
		
		scroller = new JScrollPane(splitPane);
		splitPane.setResizeWeight(0.75d);
		
		JScrollPane leftPane = new JScrollPane(mainTaskPane);
		
		splitPane.setLeftComponent(leftPane);
		splitPane.setRightComponent(btnNewVisit);
		
		add(scroller, BorderLayout.CENTER);
	
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
				
		int i;
		for (i = patientVisitList.size() - 1; i >= 0; i--) {
			Visit v = patientVisitList.get(i);
			JXTaskPane pane = new JXTaskPane();
			pane.setTitle(v.getDateCreated() + "\t|\t" + v.getChiefComplaint());
			pane.setAnimated(false);
			pane.setCollapsed(true);
			pane.setScrollOnExpand(true);
			pane.add(new VisitTabViewNewVisit(v, patient, tabbedPane, homeModel, false));
			mainTaskPane.add(pane);
		}
		
	}
}
