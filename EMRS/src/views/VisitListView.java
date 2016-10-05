package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DateFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXTaskPaneContainer;

import models.CL;
import models.MasterModel;
import models.Visit;
import models.VisitList;

@SuppressWarnings("serial")
public class VisitListView extends JXTaskPaneContainer implements viewinterface {

	private JLabel iconLabel;
//	private JPanel jp;
	
	public VisitListView () {
		
		super();
		
		
//		setLayout(new BorderLayout(0, 0));
//		jp = new JPanel();
//		add (jp,BorderLayout.CENTER);
		
		setBackground(CL.blueGrey);

		iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon("medical_history_icon.jpg"));
		iconLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		headerPanel.setBackground(CL.belize);
		
		removeAll();
	}

	@Override
	public void HideallView() {
		//TODO
	}

	@Override
	public MasterModel getMasterModel() {
		return this.getHomeView().getMasterModel();
	}

	@Override
	public void ShowView() {
		this.setVisible(true);
		reload();
	}

	@Override
	public void reload() {
		
		VisitList vl = getMasterModel().getvL();
		
		for (int index = 0; index < vl.getMyList().size(); index++) {
			
			Visit v = vl.getMyList().get(index);
			
			JXTaskPaneVisitDetailView jxtp = new JXTaskPaneVisitDetailView(index);

			String date = v.getDateCreated();
			if (date != null) {
				date = date.substring(0, date.length() - 3);
				String[] data = date.split(" ");
				
				String cc = v.getChiefComplaint();
				cc.trim();			
				if (cc.length() > 40) {
					cc = cc.substring(0, 40) + "...";
				}
				
				jxtp.setTitle("Date: " + data[0] + ", Time: " + data[1] + "\t|\t" + cc);
			} else {
				String cc = v.getChiefComplaint();
				cc.trim();			
				if (cc.length() > 40) {
					cc = cc.substring(0, 40) + "...";
				}
				jxtp.setTitle("NEW: " + cc);
			}
				
			
			jxtp.setAnimated(false);
			jxtp.setCollapsed(true);
			jxtp.setScrollOnExpand(true);
			
			add(jxtp);
			jxtp.reload();
		}
		
//		iconLabel.setText("\t\t" + vl.getMyList().size() + " visits");
//		add(iconLabel, BorderLayout.NORTH);
		
		this.validate();
		this.repaint();
	}

	// Is added to a JScrollPane, so have to go up quite a bit
	@Override
	public HomeView getHomeView() {
//		if(this.getParent().getParent().getParent() instanceof VisitTabMasterView)
		return ((VisitTabMasterView)this.getParent().getParent()).getHomeView();
	}

}
