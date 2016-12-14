package views;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import models.MasterModel;

@SuppressWarnings("serial")
public class VitalsTabMasterView extends JPanel implements viewinterface {
	private VitalListView vldv;
	private VitalCRUDView vscv;
	/**
	 * Create the panel.
	 */
	//public VitalsTabView(final Patient patient, final JTabbedPane tabbedPane, final HomeModel homeModel) {
	public VitalsTabMasterView(){
			
		
		this.setLayout(new BorderLayout());
		vldv = new VitalListView();
		vscv = new VitalCRUDView(-1);
		this.add(vldv, BorderLayout.CENTER);
	}
	
	/**
	 * Populates the VitalsTable with all vitals related current Patient
	 * @param vitalsTable JTable to populate
	 * @param patient Patient JTable to populate
	 *
	 **/
	
	public void ShowVitalListView(){
		this.HideallView();
		this.add(vldv,BorderLayout.CENTER);
		this.vldv.ShowView();
		this.validate();
		this.repaint();
	}
	
	public void ShowVitalSCRUDView(){
		this.HideallView();
		int index = this.vldv.getSelectedRow();
		vscv = new VitalCRUDView(index);
		this.add(vscv,BorderLayout.CENTER);
		this.vscv.ShowView();
		this.validate();
		this.repaint();
	}
	
	@Override
	public void HideallView() {
		this.remove(vldv);
		this.remove(vscv);
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
		this.ShowVitalListView();
	}
	@Override
	public HomeView getHomeView() {
		return ((PatientRecordView)this.getParent()).getHomeView();
	}
}
