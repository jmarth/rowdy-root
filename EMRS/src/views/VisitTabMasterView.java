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
	private VisitDetailView visitNewView;
	private VisitListView visit_ListView;
	private JButton btnNewVisit;
	private JButton btnCancelVisit;

	
	public VisitTabMasterView() {
		
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		
		visit_ListView = new VisitListView();
//		
		scrollPane.add(visit_ListView);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		add(scrollPane, BorderLayout.CENTER);
		
		btnNewVisit = new JButton("Add A New Visit");
		btnNewVisit.setBackground(CL.cararra);
		btnNewVisit.setOpaque(true);
		btnNewVisit.setBorderPainted(false);
		btnNewVisit.setForeground(CL.colorBlue);
		btnNewVisit.setFont(new Font("Tahoma", Font.BOLD, 16));		
		
		add(btnNewVisit, BorderLayout.SOUTH);
		
		btnCancelVisit = new JButton("Cancel Creating New Visit");
		btnCancelVisit.setBackground(CL.cararra);
		btnCancelVisit.setOpaque(true);
		btnCancelVisit.setBorderPainted(false);
		btnCancelVisit.setForeground(CL.colorBlue);
		btnCancelVisit.setFont(new Font("Tahoma", Font.BOLD, 16));	
		
		btnNewVisit.addActionListener(new NewVisitListener());//TODO
		btnCancelVisit.addActionListener(new CancelVisitListener());
	}
	
	private class NewVisitListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			VisitTabMasterView.this.showNewVisitView();
		}
	}
	
	private class CancelVisitListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			VisitTabMasterView.this.showListVisitFromNewView();
		}
	}

	@Override
	public void HideallView() {
		
	}

	@Override
	public MasterModel getMasterModel() {
		return ((PatientRecordView)this.getParent()).getMasterModel();
	}

	@Override
	public void ShowView() {
		showListVisitView();
	}
	
	public void showListVisitFromNewView() {
		this.HideallView();
		scrollPane.setViewportView(visit_ListView); //reload
		scrollPane.validate();
		scrollPane.repaint();
		visit_ListView.ShowView();
//		this.showNewButton();
	}
	
	public void showListVisitView() {
		this.HideallView();
		if(visit_ListView != null) {
			scrollPane.remove(visit_ListView);
		}
		visit_ListView = new VisitListView();
		scrollPane.add(visit_ListView);
		
		visit_ListView.reload(); // returns patient record view
		
		scrollPane.setViewportView(visit_ListView); //reload
		scrollPane.validate();
		scrollPane.repaint();

//		visitListView.ShowView();
//		this.showNewButton();
	}
	
	public void showNewVisitView() {
		this.HideallView();

		if(visitNewView != null) {
			scrollPane.remove(visitNewView);
		}
		visitNewView = new VisitDetailView(-1);
		scrollPane.add(visitNewView);
		
		scrollPane.setViewportView(visitNewView);
		
		visitNewView.showNewView();//for save button
//		this.showCancelButton();
	}
	
//	public void showCancelButton() {
//		this.remove(btnNewVisit);
//		this.add(btnCancelVisit, BorderLayout.SOUTH);
//		this.validate();
//		this.repaint();
//	}
	
//	public void showNewButton() {
//		this.remove(btnCancelVisit);
//		this.add(btnNewVisit, BorderLayout.SOUTH);
//		this.validate();
//		this.repaint();
//	}

	@Override
	public void reload() {
		this.ShowView();
	}

	@Override
	public HomeView getHomeView() {
		return ((PatientRecordView)this.getParent()).getHomeView();
	}
}