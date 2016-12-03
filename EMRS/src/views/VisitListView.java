package views;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.CL;
import models.MasterModel;

@SuppressWarnings("serial")
public class VisitListView extends JPanel implements viewinterface {

	private JLabel iconLabel;
	private VisitListContainer vlc;
	
	public VisitListView () {
				
		setLayout(new BorderLayout(0, 0));
		
		setBackground(CL.blueGrey);

		iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon("medical_history_icon.jpg"));
		iconLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(iconLabel, BorderLayout.NORTH);

		vlc = new VisitListContainer();
		add(vlc, BorderLayout.CENTER);
	}

	@Override
	public void HideallView() {
		//TODO
	}



	@Override
	public void ShowView() {
		
	}

	 //TODO move to VTMV figure this out
	@Override
	public void reload() {

		vlc.reload();
		
		int count = vlc.getCount();
		
		if (count == 1) {
			iconLabel.setText("\t\t" + 1 + " visit");
		} else {
			iconLabel.setText("\t\t" + count + " visits");
		}
		
		this.validate();
		this.repaint();
	}
	
	@Override
	public MasterModel getMasterModel() {
		return this.getHomeView().getMasterModel();
	}
	// get Viewport, then get ScrollPane, then get parent.
	@Override
	public HomeView getHomeView() {
		return ((VisitTabMasterView)this.getParent().getParent().getParent()).getHomeView();
	}

}
