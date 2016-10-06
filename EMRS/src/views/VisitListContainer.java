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
public class VisitListContainer extends JXTaskPaneContainer implements viewinterface {

	private int size;
	private JLabel iconLabel;
//	private JPanel jp;
	
	public VisitListContainer () {
		
		super();
		setBackground(CL.blueGrey);
//		removeAll();// TODO: WHY
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
//		this.setVisible(true);// WHY
//		reload();// WHY
	}

	@Override
	public void reload() {
		
		VisitList vl = getMasterModel().getvL();
		
		for (size = 0; size < vl.getMyList().size(); size++) {
			
			Visit v = vl.getMyList().get(size);
			
			JXTaskPaneVisitDetailView jxtp = new JXTaskPaneVisitDetailView(size);

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
		
			} else { // Don't have the date TODO : create date and insert to DB instead
				
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
		
		this.validate();
		this.repaint();
	}

	// Is added to a JScrollPane, so have to go up quite a bit
	@Override
	public HomeView getHomeView() {
//		if(this.getParent().getParent().getParent() instanceof VisitTabMasterView)
		return ((VisitTabMasterView)this.getParent().getParent()).getHomeView();
	}
	
	public int getCount() {
		return size;
	}

}
