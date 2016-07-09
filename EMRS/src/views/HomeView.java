package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComponent;

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
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class HomeView extends JFrame {

	private JPanel contentPane;
	final JButton btnAddPatient = new JButton("Add Patient");
	final JLabel lblPatientSearch= new JLabel("Patient Search");
	final JButton btnFindPatient = new JButton("Find Patient");
	JButton btnLogout = new JButton("Logout");
	final JTextField textFieldSearch = new JTextField();
	private HomeModel homeModel;
	private final JButton btnHome = new JButton("");

	
	/**
	 * Creates new home window
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView frame = new HomeView();
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
	public HomeView() {
		homeModel = new HomeModel(this);
		final HomeView home = this;
		
		//Set up gateway
		homeModel.setPatientTableGateway();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1139, 1124);
		
		//set up main panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//set up top bar panel
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 204));
		contentPane.add(panel, BorderLayout.NORTH);
		textFieldSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				homeModel.setPatientsView(new FIndPatientsView(home));
				setCenterPanel(homeModel.getPatientsView().getContentPane());
			}
		});
		
		//search filter
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String searchText = textFieldSearch.getText().toLowerCase();
				homeModel.getPatientsView().filter(searchText);
			}
		});
		textFieldSearch.setColumns(10);
		MouseListener ml = (new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				((Component) arg0.getSource()).setBackground(new Color(2,108,143));
				((JComponent) arg0.getSource()).setOpaque(true);
				//btnAddPatient.setContentAreaFilled(true);
				((AbstractButton) arg0.getSource()).setBorderPainted(false);
				System.out.println("mouse over");
			}
			public void mouseExited(MouseEvent arg0) {
				((Component) arg0.getSource()).setBackground(new Color(0, 153, 204));
				((JComponent) arg0.getSource()).setOpaque(true);
				//btnAddPatient.setContentAreaFilled(true);
				((AbstractButton) arg0.getSource()).setBorderPainted(false);
				System.out.println("mouse over");
			}
		});
		
		btnHome.setIcon(new ImageIcon("logo1.png"));
		btnHome.setOpaque(true);
		btnHome.setContentAreaFilled(false);
		btnHome.setBorderPainted(false);
		btnHome.addMouseListener(ml);
		//home button on click
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showHomeView();
			}
		});
		
		btnAddPatient.addMouseListener(ml);
		btnAddPatient.setToolTipText("Add Patient");
		btnAddPatient.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddPatient.setForeground(new Color(255, 255, 255));
		btnAddPatient.setOpaque(true);
		btnAddPatient.setContentAreaFilled(false);
		btnAddPatient.setBorderPainted(false);

		//add patient button on click
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 homeModel.setPatientInfo(new AddPatientView(home));
				 setCenterPanel(homeModel.getPatientInfo().getContentPane());
			}
		});
		btnLogout.setHorizontalAlignment(SwingConstants.RIGHT);
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogout.setOpaque(true);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorderPainted(false);
		btnLogout.addMouseListener(ml);
		
		//logout button on click
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to close all windows and logout?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					dispose();
					LoginView login = new LoginView();
					login.show();
				}
			}
		});
		btnFindPatient.addMouseListener(ml);
		btnFindPatient.setForeground(new Color(255, 255, 255));
		btnFindPatient.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFindPatient.setOpaque(true);
		btnFindPatient.setContentAreaFilled(false);
		btnFindPatient.setBorderPainted(false);
		
		//find patient button on click
		btnFindPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				homeModel.setPatientsView(new FIndPatientsView(home));
				setCenterPanel(homeModel.getPatientsView().getContentPane());
			}
		});
		
		JButton btnSketch = new JButton("Sketch");
		btnSketch.addMouseListener(ml);
		btnSketch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSketch.setForeground(new Color(255, 255, 255));
		btnSketch.setOpaque(true);
		btnSketch.setContentAreaFilled(false);
		btnSketch.setBorderPainted(false);
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
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(btnHome)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAddPatient)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnFindPatient)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSketch, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
					.addComponent(lblPatientSearch, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(btnLogout)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnAddPatient, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
					.addComponent(btnFindPatient, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblPatientSearch, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
					.addComponent(btnLogout, 0, 0, Short.MAX_VALUE))
				.addComponent(btnHome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
				.addComponent(btnSketch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		);
		lblPatientSearch.setForeground(new Color(255, 255, 255));
		lblPatientSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.setLayout(gl_panel);
		
		//set up center/main panel
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
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
