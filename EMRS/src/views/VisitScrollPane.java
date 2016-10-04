package views;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import models.MasterModel;

@SuppressWarnings("serial")
public class VisitScrollPane extends JScrollPane implements viewinterface {


	private VisitDetailView visitNewView;
	private VisitListView visitListView;
	private JButton btnNewVisit;
	
	public VisitScrollPane () {
		
	}
	
	public void showNewVisitView() {
		this.setViewportView(visitNewView);
	}
	public void showListVisitView() {
		this.setViewportView(visitListView);
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

	@Override
	public HomeView getHomeView() {
		return ((VisitTabMasterView)this.getParent()).getHomeView();
	}

}
