package views;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import models.Allergy;
import models.MasterModel;

@SuppressWarnings("serial")
public class HxMasterView extends JPanel implements viewinterface{
	
	private hxView hxv;
	private hxForm hxf;
	private AllergyTabViewNewAllergy af;
	private AddMedView mf;
	
	
	public HxMasterView () {
		hxv = new hxView();
		
		this.setLayout(new BorderLayout());
		this.add(hxv, BorderLayout.CENTER);
	}

	public hxView getHxView() {
		return hxv;
	}
	
	@Override
	public void HideallView() {
		// TODO Auto-generated method stub
	}

	@Override
	public MasterModel getMasterModel() {
		return getHomeView().getMasterModel();
	}
	
	public void showAllergyForm(boolean edit, Allergy a) {
		this.removeAll();
		if (edit) {
			af = new AllergyTabViewNewAllergy(edit, a);
		} else {
			af = new AllergyTabViewNewAllergy(edit, null);
		}
		this.add(af, BorderLayout.CENTER);
		this.validate();
		this.repaint();
	}
	
	public void showMedicationForm() {
		this.removeAll();
		mf = new AddMedView();
		this.add(mf, BorderLayout.CENTER);
		this.validate();
		this.repaint();
	}
	
	public void showHxForm(boolean edit) {
		this.removeAll();
		hxf = new hxForm(edit);
		this.add(hxf, BorderLayout.CENTER);
//		hxf.reload();
		this.validate();
		this.repaint();
	}

	@Override
	public void ShowView() {
		this.removeAll();
		hxv = new hxView();
		this.add(hxv, BorderLayout.CENTER);
		hxv.reload();
		this.validate();
		this.repaint();
	}

	@Override
	public void reload() {
		
		this.ShowView();
	}

	@Override
	public HomeView getHomeView() {
		return ((PatientRecordView)this.getParent()).getHomeView();
	}
}
