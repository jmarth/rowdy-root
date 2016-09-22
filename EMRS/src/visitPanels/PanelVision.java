package visitPanels;

import javax.swing.JPanel;

import models.MasterModel;
import views.HomeView;
import views.viewinterface;

@SuppressWarnings("serial")
public class PanelVision extends JPanel implements viewinterface {

	private int index;
	
	private PanelDistanceVision panelDV;
	private PanelGlassesRx panelGlsRx;
	private PanelRefraction panelRefrac;
	
	public PanelVision (int index) {
		this.index = index;
		panelDV = new PanelDistanceVision();
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
