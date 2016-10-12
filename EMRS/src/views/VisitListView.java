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
	
	private JPanel btnPanel;

	public VisitListView () {
		
		super();
		
		setLayout(new BorderLayout(0, 0));
		
//		btnPanel = new JPanel();
		
		setBackground(CL.blueGrey);

		iconLabel = new JLabel();
		iconLabel.setIcon(new ImageIcon("medical_history_icon.jpg"));
		iconLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		add(iconLabel, BorderLayout.NORTH);

		vlc = new VisitListContainer();
		add(vlc, BorderLayout.CENTER);

		btnNewVisit = new JButton("Add A New Visit");
		btnNewVisit.setBackground(CL.cararra);
		btnNewVisit.setOpaque(true);
		btnNewVisit.setBorderPainted(false);
		btnNewVisit.setForeground(CL.colorBlue);
		btnNewVisit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewVisit.addActionListener(new NewVisitListener());
		add(btnNewVisit, BorderLayout.SOUTH);
		
		btnCancelVisit = new JButton("Cancel New Visit");
		btnCancelVisit.setBackground(CL.cararra);
		btnCancelVisit.setOpaque(true);
		btnCancelVisit.setBorderPainted(false);
		btnCancelVisit.setForeground(CL.colorBlue);
		btnCancelVisit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancelVisit.addActionListener(new CancelVisitListener());
		add(btnCancelVisit, BorderLayout.SOUTH);
		
//		btnCancelVisit.setVisible(false);
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
	
	public void showCancelButton() {
		this.remove(btnNewVisit);
		this.add(btnCancelVisit, BorderLayout.SOUTH);
		
		this.validate();
		this.repaint();
//		btnNewVisit.setVisible(false);
//		btnCancelVisit.setVisible(true);
	}
	
	public void showNewButton() {
		this.remove(btnCancelVisit);
		this.add(btnNewVisit, BorderLayout.SOUTH);
		this.validate();
		this.repaint();
//		btnCancelVisit.setVisible(false);
//		btnNewVisit.setVisible(true);
	}

	@Override
	public void reload() {
		
		this.showNewButton();

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

	// get Viewport, then get ScrollPane, then get parent.
	@Override
	public HomeView getHomeView() {
		return ((VisitTabMasterView)this.getParent().getParent().getParent()).getHomeView();
	}

}
