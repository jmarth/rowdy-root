package views;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.MasterModel;

@SuppressWarnings("serial")
public class VisitTabMasterView extends JPanel implements viewinterface {

	private JScrollPane scrollPane;
	private VisitDetailView visitNewView;
	private VisitListView visit_ListView;
	
	public VisitTabMasterView() {
		
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		
		visit_ListView = new VisitListView();
//		visitNewView = new VisitDetailView(); for New Visit
		
		scrollPane.add(visit_ListView);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		add(scrollPane, BorderLayout.CENTER);
		
		//TODO have newButton Here, changes to Cancel to go back to showView()
		
	}



	@Override
	public void ShowView() {
		showList_VisitView();
	}
	
	public void showListVisitFromNewView() {
		
		this.HideallView();
		
		scrollPane.setViewportView(visit_ListView); //reload
		
		scrollPane.validate();
		scrollPane.repaint();
		visit_ListView.ShowView();
	}
	
	public void showList_VisitView() {
		
		this.HideallView();
		
		if(visit_ListView != null) {
			scrollPane.remove(visit_ListView);
			
		}
		
		visit_ListView = new VisitListView();
		scrollPane.add(visit_ListView);
		
		scrollPane.setViewportView(visit_ListView);
		visit_ListView.reload();
		
		scrollPane.validate();
		scrollPane.repaint();
	}
	
	public void showNewVisitView() {
		
		this.HideallView();

		if(visitNewView != null) {
			scrollPane.remove(visitNewView);
		}
		
		visitNewView = new VisitDetailView(-1);
		
		scrollPane.add(visitNewView);
		scrollPane.setViewportView(visitNewView);
		
		visitNewView.showNewView();//for save button
	}
	
	@Override
	public void HideallView() {
		
	}	

	@Override
	public void reload() {
		this.ShowView();
	}

	@Override
	public MasterModel getMasterModel() {
		return ((PatientRecordView)this.getParent()).getMasterModel();
	}
	
	@Override
	public HomeView getHomeView() {
		return ((PatientRecordView)this.getParent()).getHomeView();
	}
}