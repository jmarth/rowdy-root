package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.GatewayException;
import database.PatientTableGateway;
import database.PatientTableGatewayMySQL;
import models.HomeModel;
import models.Patient;
import models.PatientList;

import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	final JButton btnHome = new JButton("Home");
	final JButton btnAddPatient = new JButton("Add Patient");
	final JLabel lblPatientSearch= new JLabel("Patient Search");
	final JButton btnFindPatient = new JButton("Find Patient");
	JButton btnLogout = new JButton("Logout");
	final JTextField textFieldSearch = new JTextField();
	private HomeModel homeModel;

	
	/**
	 * Creates new home window
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Home constructor
	 */
	public Home() {
		homeModel = new HomeModel(this);
		final Home home = this;
		
		//Set up gateway
		homeModel.setPatientTableGateway();
		
		//hide patient search
		textFieldSearch.setVisible(false);
		lblPatientSearch.setVisible(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 513);
		
		//set up main panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//set up top bar panel
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("ComboBox.selectionBackground"));
		contentPane.add(panel, BorderLayout.NORTH);
		
		//search filter
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String searchText = textFieldSearch.getText().toLowerCase();
				homeModel.getPatientsView().filter(searchText);
			}
		});
		textFieldSearch.setColumns(10);
	
		//add patient button on click
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 homeModel.setPatientInfo(new PatientInfo(home));
				 setCenterPanel(homeModel.getPatientInfo().getContentPane());
				 lblPatientSearch.setVisible(false);
				 textFieldSearch.setVisible(false);
			}
		});
		
		//home button on click
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showHomeView();
			}
		});
		
		//logout button on click
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to close all windows and logout?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					dispose();
					Login login = new Login();
					login.show();
				}
			}
		});
		
		//find patient button on click
		btnFindPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				homeModel.setPatientsView(new PatientsView(home));
				setCenterPanel(homeModel.getPatientsView().getContentPane());
				textFieldSearch.setVisible(true);
				lblPatientSearch.setVisible(true);
			}
		});
		
		JButton btnSketch = new JButton("Sketch");
		btnSketch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//need to maximize at first for sketch scaling to work correctly
				//this is a temp solution to the scaleing problem
				setExtendedState( getExtendedState()|JFrame.MAXIMIZED_BOTH );
				
				Paint paint = new Paint();
				setCenterPanel(paint.getContentPane());
			}
		});
		
		//set up top bar panel layout
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(2)
					.addComponent(btnHome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddPatient)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnFindPatient)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSketch)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblPatientSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnLogout)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPatientSearch)
						.addComponent(btnLogout)
						.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnHome)
						.addComponent(btnAddPatient)
						.addComponent(btnFindPatient)
						.addComponent(btnSketch)))
		);
		panel.setLayout(gl_panel);
		
		//set up center/main panel
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
	}
	
	/**
	 * Returns JPanel
	 */
	public JPanel getContentPane() {
		return contentPane;
	}
	
	/**
	 * replaces center/main panel with given jpanel
	 * @param container
	 */
	public void setCenterPanel(Container container) {
		LayoutManager layout = contentPane.getLayout();
		Component centerComponent = ((BorderLayout) layout).getLayoutComponent(BorderLayout.CENTER);
		if(centerComponent != null ) {
			contentPane.remove(centerComponent);
		}
		if(homeModel.getPatientInfo() != null)
			homeModel.getPatientInfo().hideBalloonTips();
		contentPane.add(container, BorderLayout.CENTER);
		contentPane.repaint();
		contentPane.revalidate();
	}
	
	//Sets up the center panel for the home view
	public void showHomeView() {
		textFieldSearch.setVisible(false);
		lblPatientSearch.setVisible(false);
		LayoutManager layout = contentPane.getLayout();
		Component centerComponent = ((BorderLayout) layout).getLayoutComponent(BorderLayout.CENTER);
		 if(centerComponent != null ) {
			 contentPane.remove(centerComponent);
		 }
		 if(homeModel.getPatientInfo() != null)
				homeModel.getPatientInfo().hideBalloonTips();
		contentPane.repaint();
		contentPane.revalidate();
	}
	
	/**
	 * gets the homeModel
	 * @return HomeModel
	 */
	public HomeModel getHomeModel() {
		return homeModel;
	}
	
	public JButton getFindPatientButton() {
		return btnFindPatient;
	}
	
	public JButton getAddPatientButton() {
		return btnAddPatient;
	}
}
