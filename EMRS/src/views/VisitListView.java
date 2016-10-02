package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXTaskPaneContainer;

import models.CL;
import models.MasterModel;
import models.Visit;

@SuppressWarnings("serial")
public class VisitListView extends JXTaskPaneContainer implements viewinterface {

	public VisitListView () {
		
		super();
		
		setLayout(new BorderLayout(0, 0));
		setBackground(CL.blueGrey);

		JLabel iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon("medical_history_icon.jpg"));
		iconLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		headerPanel.setBackground(CL.belize);
		
		removeAll();
		
		//TODO Server should return the list backwards
		int i = 0;
		for (Visit v : getMasterModel().getvL().getMyList()) {
			
			JXTaskPaneVisitDetailView jxtp = new JXTaskPaneVisitDetailView(i++);
			
			String date = v.getDateCreated();
			date = date.substring(0, date.length() - 3);
			String[] data = date.split(" ");

			String cc = v.getChiefComplaint();
			cc.trim();			
			if (cc.length() > 40) {
				cc = cc.substring(0, 40) + "...";
			}
			
			jxtp.setTitle("Date: " + data[0] + ", Time: " + data[1] + "\t|\t" + cc);
			jxtp.setAnimated(false);
			jxtp.setCollapsed(true);
			jxtp.setScrollOnExpand(true);
			
			add(jxtp);
		}
		
		iconLabel.setText("\t\t" + i+1 + " visits");
		add(iconLabel, BorderLayout.NORTH);
	}

	@Override
	public void HideallView() {
		
	}

	// Is added to a JScrollPane, so get parent's parent.
	@Override
	public MasterModel getMasterModel() {
		return ((HomeView)this.getParent().getParent()).getMasterModel();
	}

	@Override
	public void ShowView() {
		
	}

	@Override
	public void reload() {
		
	}

	// Is added to a JScrollPane, so get parent's parent.
	@Override
	public HomeView getHomeView() {
		return ((HomeView)this.getParent().getParent());
	}

}
