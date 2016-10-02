package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.CL;
import models.MasterModel;

@SuppressWarnings("serial")
public class MasterVisit extends JPanel implements viewinterface {

	JScrollPane scrollPane;
	private VisitListView vlv;	
	
	public MasterVisit() {
		
		setLayout(new BorderLayout(0, 0));
		
		vlv = new VisitListView();
		
		//TODO Edit a selected visit...how?
		
		JButton btnNewVisit = new JButton("Add A New Visit");
		btnNewVisit.setBackground(CL.cararra);
		btnNewVisit.setOpaque(true);
		btnNewVisit.setBorderPainted(false);
		btnNewVisit.setForeground(CL.colorBlue);
		btnNewVisit.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		scrollPane = new JScrollPane(vlv);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		add(scrollPane, BorderLayout.CENTER);
		
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