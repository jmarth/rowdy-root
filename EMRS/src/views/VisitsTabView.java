package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import models.CL;
import models.HomeModel;
import models.MasterModel;
import models.Patient;
import models.Tabs;
import models.Visit;

public class VisitsTabView extends JPanel implements viewinterface {
	private Patient patient;
	private List<Visit> patientVisitList = new ArrayList<Visit>();
	private HomeModel homeModel;
	private JScrollPane scroller;
	private JXTaskPaneContainer mainTaskPane;
	private JTabbedPane tabbedPane;	
	private JPanel scrollPanel;
	private JLabel iconLabel;
	private JSplitPane splitPane;
	private JPanel headerPanel;
	
	private boolean painted = false;

	//public VisitsTabView(final Patient patient, final JTabbedPane tabbedPane, final HomeModel homeModel) {
	public VisitsTableView(){
		this.patient = patient;
		this.homeModel = homeModel;
		this.tabbedPane = tabbedPane;
		
		setLayout(new BorderLayout(0, 0));
		
		iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon("medical_history_icon.jpg"));
		iconLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		headerPanel.setBackground(CL.belize);
				
		mainTaskPane = new JXTaskPaneContainer();
		
		populatePanes();
		
		/*
		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.5d);
		splitPane.setDividerSize(3);
		*/
		
		
		JButton btnNewVisit = new JButton("Add A New Visit");
		btnNewVisit.setBackground(CL.cararra);
		btnNewVisit.setOpaque(true);
		btnNewVisit.setBorderPainted(false);
		btnNewVisit.setForeground(CL.colorBlue);
		btnNewVisit.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		JScrollPane leftPane = new JScrollPane(mainTaskPane);
		leftPane.getVerticalScrollBar().setUnitIncrement(16);
		/*
		splitPane.setLeftComponent(leftPane);
		splitPane.setRightComponent(btnNewVisit);
		
		splitPane.setDividerLocation(0.5d);
		*/
		add(leftPane, BorderLayout.CENTER);
		
		add(btnNewVisit, BorderLayout.SOUTH);
		
		btnNewVisit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitTabViewNewVisit vtvnv = new VisitTabViewNewVisit(patient, tabbedPane, homeModel);
				JScrollPane rightPane = new JScrollPane(vtvnv);
				rightPane.getVerticalScrollBar().setUnitIncrement(16);
				int index = tabbedPane.indexOfTab(Tabs.ped);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, rightPane);
				//splitPane.setRightComponent(rightPane);
			}
		});
		
	}
	
	//extend JXTaskPane, the mainJX panes will, the list will have the list
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
			String cc = v.getChiefComplaint();
			cc.trim();
			if (cc.length() > 40) {
				cc = cc.substring(0, 40) + "...";
			}
			pane.setTitle("Date: " + data[0] + ", Time: " + data[1] + "\t|\t" + cc);
			pane.setAnimated(false);
			pane.setCollapsed(true);
			pane.setScrollOnExpand(true);
			pane.add(new VisitTabViewNewVisit(v, patient, tabbedPane, homeModel, true));
			mainTaskPane.add(pane);
		}
		
	}

	@Override
	public void HideallView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MasterModel getMasterModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ShowView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}
}
