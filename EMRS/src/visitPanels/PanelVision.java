package visitPanels;

import javax.swing.JPanel;

import models.CL;
import models.MasterModel;
import net.miginfocom.swing.MigLayout;
import views.HomeView;
import views.VisitDetailView;
import views.viewinterface;

@SuppressWarnings("serial")
public class PanelVision extends JPanel implements viewinterface {

//	private int index;
	
	private PanelDistanceVision panelDV;
	private PanelGlassesRx panelGlsRx;
	private PanelRefraction panelRefrac;
	
	public PanelVision (int index) {
//		this.index = index;

		setBackground(CL.turq);
		setLayout(new MigLayout("", "[grow,fill]", "[][][]"));
		//TODO panelDV = new PanelDistanceVision();
		panelDV = new PanelDistanceVision(index);
		panelGlsRx = new PanelGlassesRx(index);
		panelRefrac = new PanelRefraction(index);
		add(panelDV, "cell 0 0,alignx center,aligny top");
		add(panelGlsRx, "cell 0 1,alignx left,aligny top");
		add(panelRefrac, "cell 0 2,alignx center,aligny top");		
	}
	
	@Override
	public void HideallView() {
		
	}
	@Override
	public MasterModel getMasterModel() {
		return getHomeView().getMasterModel();
	}
	@Override
	public void ShowView() {
		//TODO
	}
	@Override
	public void reload() {		
		panelDV.reload();
		panelGlsRx.reload();
		panelRefrac.reload();
	}
	@Override
	public HomeView getHomeView() {
		return ((VisitDetailView)this.getParent()).getHomeView();
	}
	
	public PanelDistanceVision getPDV() {
		return panelDV;
	}

	public PanelGlassesRx getPGRx() {
		return panelGlsRx;
	}

	public PanelRefraction getPanelRefrac() {
		return panelRefrac;
	}
}
