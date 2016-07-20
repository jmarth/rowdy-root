package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import models.HomeModel;
import models.Patient;
import models.Visit;

public class HistoryTabView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HomeModel homeModel;
	
	private Patient patient;
	
	private JTabbedPane tabbedPane;
	
	private List<Visit> visitList = new ArrayList<Visit>();
	
	private JScrollPane scroller;
	
	private JXTaskPaneContainer mainTaskPane;
		
	public HistoryTabView(final Patient pt, final HomeModel hm, final JTabbedPane tp) {
		
		this.patient = pt;
		this.homeModel = hm;
		this.tabbedPane = tp;
		
		setLayout(new BorderLayout());
		
		// initialize main container to hold individual task panes (may be 0 or more individual task panes)
		mainTaskPane = new JXTaskPaneContainer();
		
		// populate task panes with visit panels
		populatePanes();		
		
		// add 'task pane container' which contains all of the individual 'task panes', to 'scrollpane'
		scroller = new JScrollPane(mainTaskPane);
		
		// add scroller with contains main container and all sub containers to this panel
		add(scroller, BorderLayout.CENTER);
	}



	public void populatePanes() {
		
		mainTaskPane.removeAll();
		
		JLabel iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon("medical_history_icon.jpg"));
		
		Collection<Visit> coll = (Collection<Visit>) homeModel.getVl().getMyPidMap().get(patient.getId());
		
		if (coll != null) {
			visitList = (List<Visit>) coll;
		}
		
		iconLabel.setText("\t\t" + visitList.size() + " total visits");
		iconLabel.setFont(new Font("Roboto", Font.BOLD, 30));
		mainTaskPane.add(iconLabel);
				
		for (Visit v : visitList) {
			JXTaskPane pane = new JXTaskPane();
			pane.setTitle(v.getDateCreated() + "\t|\t" + v.getChiefComplaint());
			pane.setAnimated(false);
			pane.setCollapsed(true);
			pane.add(new VisitTabViewNewVisit(v, patient, tabbedPane, homeModel, false));
			mainTaskPane.add(pane);
		}
	}
}
