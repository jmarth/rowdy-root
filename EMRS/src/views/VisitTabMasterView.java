package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.CL;
import models.MasterModel;

@SuppressWarnings("serial")
public class VisitTabMasterView extends JPanel implements viewinterface {

	private JScrollPane scrollPane;
	
	private JButton btnNewVisit;
	private JButton btnCancelNewVisit;
	
	private VisitDetailView visitNewView;
	private VisitListView visit_ListView;
	
	public VisitTabMasterView() {
		
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		
		visit_ListView = new VisitListView();
//		visitNewView = new VisitDetailView(); for New Visit
		
		scrollPane.add(visit_ListView);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		btnNewVisit = new JButton("Add a New Visit");
		btnNewVisit.setBackground(CL.cararra);
		btnNewVisit.setOpaque(true);
		btnNewVisit.setBorderPainted(false);
		btnNewVisit.setForeground(CL.colorBlue);
		btnNewVisit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewVisit.addActionListener(new NewVisitListener());
		
		btnCancelNewVisit = new JButton("Cancel This New Visit");
		btnCancelNewVisit.setBackground(CL.cararra);
		btnCancelNewVisit.setOpaque(true);
		btnCancelNewVisit.setBorderPainted(false);
		btnCancelNewVisit.setForeground(CL.colorBlue);
		btnCancelNewVisit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancelNewVisit.addActionListener(new CancelNewVisitListener());
		
		add(scrollPane, BorderLayout.CENTER);
		add(btnNewVisit, BorderLayout.SOUTH);
		add(btnCancelNewVisit, BorderLayout.SOUTH);
	}
	
	private class NewVisitListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			VisitTabMasterView.this.showNewVisitView();
		}
	}
	
	private class CancelNewVisitListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			VisitTabMasterView.this.showList_VisitView();
		}
	}

	@Override
	public void ShowView() {
		this.removeAll();
		scrollPane.add(visit_ListView);
		add(scrollPane, BorderLayout.CENTER);
		add(btnNewVisit, BorderLayout.SOUTH);
		add(btnCancelNewVisit, BorderLayout.SOUTH);
		showList_VisitView();
	}
	
	public void showList_VisitView() {
		
		this.HideallView();
		
		this.remove(btnCancelNewVisit);
		this.add(btnNewVisit, BorderLayout.SOUTH);
		
		if(visit_ListView != null) {
			scrollPane.remove(visit_ListView);
			
		}
		
		visit_ListView = new VisitListView();
		scrollPane.add(visit_ListView);
		
		scrollPane.setViewportView(visit_ListView);
		visit_ListView.reload();
		
		
		this.validate();
		this.repaint();
	}
	
	public void showNewVisitView() {
		
		this.HideallView();
		
		this.remove(btnNewVisit);
		this.add(btnCancelNewVisit, BorderLayout.SOUTH);
		
		this.validate();
		this.repaint();

		if(visitNewView != null) {
			scrollPane.remove(visitNewView);
		}
		
		visitNewView = new VisitDetailView(-1);
		
		scrollPane.add(visitNewView);
		scrollPane.setViewportView(visitNewView);
		
		visitNewView.showNewView();//for save button //TODO
		
		
		this.validate();
		this.repaint();
		
	}
	
	@Override
	public void HideallView() {
		
	}	

	@Override
	public void reload() {
		this.ShowView();
	}

	@Override
	public MasterModel getMasterModel() {
		return ((PatientRecordView)this.getParent()).getMasterModel();
	}
	
	@Override
	public HomeView getHomeView() {
		return ((PatientRecordView)this.getParent()).getHomeView();
	}
}