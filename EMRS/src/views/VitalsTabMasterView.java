package views;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.MasterModel;

@SuppressWarnings("serial")
public class VitalsTabMasterView extends JPanel implements viewinterface {
	private VitalListView vldv;
	private VitalSCRUBView vscv;
	/**
	 * Create the panel.
	 */
	//public VitalsTabView(final Patient patient, final JTabbedPane tabbedPane, final HomeModel homeModel) {
	public VitalsTabMasterView(){
			
		
		this.setLayout(new BorderLayout());
		vldv = new VitalListView();
		vscv = new VitalSCRUBView(-1);
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
		System.err.println(index);
		vscv=new VitalSCRUBView(index);
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
