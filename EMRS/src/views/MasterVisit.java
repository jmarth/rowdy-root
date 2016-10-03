package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import models.CL;
import models.MasterModel;
import models.Tabs;
import models.Visit;

@SuppressWarnings("serial")
public class MasterVisit extends JPanel implements viewinterface {

	//	private JScrollPane scroller;
//	private JPanel scrollPanel;
//	private JSplitPane splitPane;

	private VisitListView vlv;	
	//detail
	
	public MasterVisit() {
		
		setLayout(new BorderLayout(0, 0));
		
		vlv = new VisitListView();
		//new visit
//		populatePanes();
		
		/*
		 * for the old split pane view, maybe re-factor will fix
		 * the previous display issue can fix it later
		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.5d);
		splitPane.setDividerSize(3);
		*
		*/
		
		//TODO Edit a selected visit...how?
		JButton btnNewVisit = new JButton("Add A New Visit");
		btnNewVisit.setBackground(CL.cararra);
		btnNewVisit.setOpaque(true);
		btnNewVisit.setBorderPainted(false);
		btnNewVisit.setForeground(CL.colorBlue);
		btnNewVisit.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		JScrollPane leftPane = new JScrollPane(vlv);
		leftPane.getVerticalScrollBar().setUnitIncrement(16);
		
		/*
		 * for the old split pane view, maybe re-factor will fix
		 * the previous display issue can fix it later
		splitPane.setLeftComponent(leftPane);
		splitPane.setRightComponent(btnNewVisit);
		splitPane.setDividerLocation(0.5d);
		*
		*/
		
		add(leftPane, BorderLayout.CENTER);
		
		add(btnNewVisit, BorderLayout.SOUTH);
		
		btnNewVisit.addActionListener(new NewVisitListener());
		
	}
	
	private class NewVisitListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			
			//TODO get selected index
			//TODO Should create the visit in the list also
			
			//TODO getMasterModel().getCurrentPatientVisitList().add(0, new Visit()); // inserts at head, then shifts others down
			
			//TODO insert to DB/list model
			
//			PanelNewVisit newVisit = new PanelNewVisit(0);
			
			//TODO add to list
			
			//show()
			
			//JScrollPane rightPane = new JScrollPane(newVisit);
			//rightPane.getVerticalScrollBar().setUnitIncrement(16);
			
			
//			int index = tabbedPane.indexOfTab(Tabs.ped);
//			tabbedPane.setComponentAt(index, null);
//			tabbedPane.setComponentAt(index, rightPane);
			//splitPane.setRightComponent(rightPane);
		}
	}
	

	@Override
	public void HideallView() {
//		this.vlv.setvi///TODO
	}

	@Override
	public MasterModel getMasterModel() {
		return ((HomeView)this.getParent()).getMasterModel();
	}

	@Override
	public void ShowView() {
		
	}

	@Override
	public void reload() {
		
	}

	@Override
	public HomeView getHomeView() {
		return ((HomeView)this.getParent());
	}
}