package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.GatewayException;
import database.PatientTableGateway;
import database.PatientTableGatewayMySQL;
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

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearch;
	private PatientTableGateway ptg;
	private PatientList patientList;
	private PatientInfo pi;
	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
					//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					//frame.setSize(screenSize.width, screenSize.height);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 434);
		
		ptg = null;
		
		//Try to connect to database
		try {
			ptg = new PatientTableGatewayMySQL();
		} catch (GatewayException e) {
			JOptionPane.showMessageDialog(null, "Database is not responding. Please reboot your computer and maybe the database will magically appear (not really).", "Database Offline!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Database is not responding. Please reboot your computer and maybe the database will magically appear (not really).", "Database Offline!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		//Set patients from database
		patientList = new PatientList();
		patientList.setGateway(ptg);
		patientList.loadFromGateway();
		List<Patient> pl = patientList.getPatientList();
		System.out.print(pl.size());
		for(int i=0; i<pl.size(); i++)
			System.out.print(pl.get(i).getAddress());
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAccount = new JMenu("Account");
		menuBar.add(mnAccount);
		
		JMenuItem mntmAccountInfo = new JMenuItem("My Profile");
		mntmAccountInfo.addMouseListener(new MouseAdapter() {
			//@Override
			public void mouseReleased(MouseEvent arg0) {
				Profile p = new Profile();
				setCenterPanel(p.getContentPane());
			}
		});
		mnAccount.add(mntmAccountInfo);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addMouseListener(new MouseAdapter() {
			//@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
				Login login = new Login();
				login.show();
			}
		});
		mnAccount.add(mntmLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("ComboBox.selectionBackground"));
		contentPane.add(panel, BorderLayout.NORTH);
		
		
		final JLabel lblPatientSearch= new JLabel("Patient Search");
		
		textFieldSearch = new JTextField();
		textFieldSearch.setColumns(10);
		
		final JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		final JButton btnAddPatient = new JButton("Add Patient");
		final Home home = this;
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 pi = new PatientInfo(home);
				 setCenterPanel(pi.getContentPane());
				 
				 btnAddPatient.setVisible(false);
				 lblPatientSearch.setVisible(false);
				 textFieldSearch.setVisible(false);
				 btnSearch.setVisible(false);
			}
		});
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JList list = new JList();
				LayoutManager layout = contentPane.getLayout();
				Component centerComponent = ((BorderLayout) layout).getLayoutComponent(BorderLayout.CENTER);
				 if(centerComponent != null ) {
					 contentPane.remove(centerComponent);
				 }
				btnAddPatient.setVisible(true);
				lblPatientSearch.setVisible(true);
				textFieldSearch.setVisible(true);
				btnSearch.setVisible(true);
				pi.removeBalloonTips();
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnHome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddPatient)
					.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
					.addComponent(lblPatientSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSearch)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch)
						.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPatientSearch)
						.addComponent(btnAddPatient)
						.addComponent(btnHome)))
		);
		panel.setLayout(gl_panel);
	}
	
	public PatientTableGateway getPatientTableGateway() {
		return ptg;
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
	
	public void setCenterPanel(Container container) {
		LayoutManager layout = contentPane.getLayout();
		Component centerComponent = ((BorderLayout) layout).getLayoutComponent(BorderLayout.CENTER);
		if(centerComponent != null ) {
			contentPane.remove(centerComponent);
		}
		contentPane.add(container, BorderLayout.CENTER);
		contentPane.revalidate();
	}
}
