package views;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;

import models.CL;
import models.MasterModel;
import models.Surgery;
import models.Tabs;

import javax.swing.SwingConstants;

import database.GatewayException;
import database.SurgeryTableGateway;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

public class SafeSurgery extends JPanel implements viewinterface {
	
	private String title;
	private String body;
	private JTextPane textPane;
	private JLabel lblHeresWhatWe;
	private JLabel label;
	private JButton btnFinalize;
	private JButton btnGoBack;

	/**
	 * Create the panel.
	 * @param parent 
	 * @param newProcedureView 
	 * @param body 
	 * @param title 
	 */
	//public SafeSurgery(final Surgery surgery, final LabsAndProceduresTabView parent, final NewProcedureView newProcedureView) {
	public SafeSurgery(final Surgery surgery){	
		this.title = surgery.getTitle();
		this.body = surgery.getBody();
		
		JPanel panel = new JPanel();
		panel.setBackground(CL.colorBlue);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(CL.cararra);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(CL.colorBlue);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
							.addGap(8))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
							.addGap(7))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
							.addGap(8))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(9))
		);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignOnBaseline(true);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))
		);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);
		
		textPane = new JTextPane();
		textPane.setEditable(false);;
		scrollPane.setViewportView(textPane);
		
		lblHeresWhatWe = new JLabel("Here's what we found:");
		lblHeresWhatWe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHeresWhatWe.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblHeresWhatWe);
		
		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_3.add(label);
		panel_1.setLayout(gl_panel_1);
		
		btnFinalize = new JButton("Finalize");
		btnFinalize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				long newID = 0;
				try {
					SafeSurgery.this.getMasterModel().getsL().insert(surgery);
				} catch (GatewayException e1) {
					e1.printStackTrace();
				}
				((LabsAndProceduresTabView)(SafeSurgery.this.getParent().getParent())).reload();;	
			}
			
		});
		panel_2.add(btnFinalize);
		
		btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LabsAndProceduresTabView lpview =((LabsAndProceduresTabView)(SafeSurgery.this.getParent().getParent()));
				lpview.getProceduresParentPanel().removeAll();
				lpview.getProceduresParentPanel().add(lpview.getPdview(), BorderLayout.CENTER);
				lpview.validate();
				lpview.repaint();
			}
			
		});
		panel_2.add(btnGoBack);
		
		JLabel lblSafesurgery = new JLabel("SafeSurgery");
		lblSafesurgery.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSafesurgery.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblSafesurgery);
		lblSafesurgery.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		JLabel lblCheckYourAngles = new JLabel("Procedure Validation");
		lblCheckYourAngles.setForeground(Color.DARK_GRAY);
		lblCheckYourAngles.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(lblCheckYourAngles);
		setLayout(groupLayout);
		
		label.setText("Nothing");
		
		textPane.setText(body);

	}

	@Override
	public void HideallView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MasterModel getMasterModel() {
		return this.getHomeView().getMasterModel();
	}

	@Override
	public void ShowView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HomeView getHomeView() {
		return ((LabsAndProceduresTabView)(this.getParent().getParent())).getHomeView();
	}
}
