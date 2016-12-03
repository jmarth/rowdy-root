package views;

import org.jdesktop.swingx.JXTaskPane;

import models.MasterModel;

@SuppressWarnings("serial")
public class JXTaskPaneVisitDetailView extends JXTaskPane implements viewinterface {
	
	VisitDetailView vdv;
	/*
	 * Creates a detail view for the JXTask
	 * Can be edited.
	 */
	public JXTaskPaneVisitDetailView(int index) {
		
		vdv = new VisitDetailView(index);
		add(vdv);
		vdv.showEditView();

	}	
	
	@Override
	public void HideallView() {
		//TODO
	}

	@Override
	public MasterModel getMasterModel() {
		return getHomeView().getMasterModel();
	}

	@Override
	public void ShowView() {
		reload();
		this.setVisible(true);
	}

	@Override
	public void reload() {
		vdv.reload();
	}

	@Override
	public HomeView getHomeView() {
		return ((VisitListContainer)this.getParent()).getHomeView();
	}
	
	// TODO Need to disable a visit for just simple view, but need smart way to.
//	@Override
//	public void setEnabled(boolean enabled) {
//		
//		this.setEnabled(enabled);
//		this.vdv.setEnabled(enabled);
//	}
	
}
