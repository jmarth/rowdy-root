package views;

import org.jdesktop.swingx.JXTaskPane;

import models.MasterModel;

public class VisitDetailView extends JXTaskPane implements viewinterface{
	
	private int index;
	
	private VisionPanel vp;
	private SLEPanel sle;
	private GonioPanel gp;
	private FUndusPanel fp;
	
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
