package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import models.CL;
import models.MasterModel;
import models.Visit;

@SuppressWarnings("serial")
public class VisitListView extends JXTaskPaneContainer implements viewinterface {
		
	private JPanel headerPanel;
	private JLabel iconLabel;

	
	public VisitListView () {
		super();
		
//		setLayout(new BorderLayout(0, 0));

		iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon("medical_history_icon.jpg"));
		iconLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		headerPanel.setBackground(CL.belize);
		
		removeAll();
		
		List<Visit> visitList = getMasterModel().getCurrentPatientVisitList(); //TODO was using .getvL() BUT should probably should return something more practical
				
		iconLabel.setText("\t\t" + visitList.size() + " visits");
		add(iconLabel, BorderLayout.NORTH);
		setBackground(CL.blueGrey);
		
		// TODO this loops backwards, does it need to?
		
		//for each
		for (int i = visitList.size() - 1; i >= 0; i--) {
			
			Visit v = visitList.get(i);
			
			JXTaskPane pane = new JXTaskPane();
			String date = v.getDateCreated();
			date = date.substring(0, date.length() - 3);
			String[] data = date.split(" ");
			
			String cc = v.getChiefComplaint();
			cc.trim();			
			if (cc.length() > 40) {
				cc = cc.substring(0, 40) + "...";
			}
			pane.setTitle("Date: " + data[0] + ", Time: " + data[1] + "\t|\t" + cc);
			pane.setAnimated(false);
			pane.setCollapsed(true);
			pane.setScrollOnExpand(true);
			
			pane.add(new PanelNewVisit(i)); // TODO not sure about this, maybe experiment, why a TaskPane?
			add(pane);
		}
		
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
