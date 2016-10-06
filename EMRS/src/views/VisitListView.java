package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.CL;
import models.MasterModel;

@SuppressWarnings("serial")
public class VisitListView extends JPanel implements viewinterface {

	private JLabel iconLabel;
	private JButton btnNewVisit;
	private JButton btnCancelVisit;
	private VisitListContainer vlc;

	public VisitListView () {
		
		super();
		
		setLayout(new BorderLayout(0, 0));
		
		setBackground(CL.blueGrey);

		iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon("medical_history_icon.jpg"));
		iconLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(iconLabel, BorderLayout.NORTH);

		btnNewVisit = new JButton("Add A New Visit");
		btnNewVisit.setBackground(CL.cararra);
		btnNewVisit.setOpaque(true);
		btnNewVisit.setBorderPainted(false);
		btnNewVisit.setForeground(CL.colorBlue);
		btnNewVisit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewVisit.addActionListener(new NewVisitListener());
		add(btnNewVisit, BorderLayout.SOUTH);
		
//		btnCancelVisit = new JButton("Add A New Visit");
//		btnCancelVisit.setBackground(CL.cararra);
//		btnCancelVisit.setOpaque(true);
//		btnCancelVisit.setBorderPainted(false);
//		btnCancelVisit.setForeground(CL.colorBlue);
//		btnCancelVisit.setFont(new Font("Tahoma", Font.BOLD, 16));
//		btnCancelVisit.addActionListener(new CancelVisitListener());
//		add(btnCancelVisit, BorderLayout.SOUTH);

		vlc = new VisitListContainer();
		add(vlc, BorderLayout.CENTER);
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
		
	}

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
	
	private class NewVisitListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			((VisitTabMasterView) VisitListView.this.getParent().getParent().getParent()).showNewVisitView();
		}
	}
	
	private class CancelVisitListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			((VisitTabMasterView) VisitListView.this.getParent().getParent().getParent()).showListVisitFromNewView();
		}
	}

	// Is added to a JScrollPane, so have to go up quite a bit
	@Override
	public HomeView getHomeView() {
//		if(this.getParent().getParent().getParent() instanceof VisitTabMasterView)
		return ((VisitTabMasterView)this.getParent().getParent()).getHomeView();
	}

}
