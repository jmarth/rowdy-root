package views;

import org.jdesktop.swingx.JXTaskPane;

import models.MasterModel;
import visitPanels.PanelFundus;
import visitPanels.PanelGonio;
import visitPanels.PanelSLE;
import visitPanels.PanelVision;

public class VisitDetailView extends JXTaskPane implements viewinterface{
	
	private int index;
	
	private PanelVision vp;
	private PanelSLE sle;
	private PanelGonio gp;
	private PanelFundus fp;
	
	//private Visit myVisit;
	public VisitDetailView(int index) {
		//create panels TODO
		MasterModel mm;
		mm = (MasterModel)this.getMasterModel();
		mm.getCurrentPatientVisitList().get(index);
	}
	
	@Override
	public void HideallView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MasterModel getMasterModel() {
		return ((HomeView)this.getParent()).getMasterModel();
	}

	@Override
	public void ShowView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reload() {
		
	}

	@Override
	public HomeView getHomeView() {
		return ((HomeView)this.getParent()).getHomeView();
	}
	
	
}
