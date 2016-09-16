package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.GatewayException;
import models.Allergy;
import models.CL;
import models.HomeModel;
import models.MasterModel;
import models.Patient;

@SuppressWarnings("serial")
public class HomeView extends JFrame {
	
	private static final Logger logger = LogManager.getLogger(HomeView.class);

	//private JPanel contentPane;
	
	private HomeModel homeModel;
	private AddPatientView pview;
	private FindPatientsView fpview;
	private PatientRecordView prview;
	private SlideShowPanel ssview;
	
	
	private final JButton btnHome = new JButton("");
	
	final JButton btnAddPatient = new JButton("Add Patient");
	final JButton btnFindPatient = new JButton("Find Patient");

	final JLabel lblPatientSearch= new JLabel("Patient Search");
	final JTextField textFieldSearch = new JTextField();
	
	final JButton btnLogout = new JButton("Logout");
	
	Patient p; // TODO Why have patient?
	JButton btnAllergyAlert;
	
	private final JPanel centerPanel = new JPanel();
	

	private MasterModel model;
	
	/**
	 * Home constructor.
	 * 
	 * Inits the "HomeModel" which has all the gateways, lists, and some actual views.
	 * 
	 */
	public HomeView() {
		// Models should be independent; a view should grab from a model.
		this.model = new MasterModel();
		pview = new AddPatientView(model);
		fpview = new FindPatentsView(model);
		prview = new PatientRecordView(model);
		ssview = new SlideShowPanel();
		
		setTitle("EMRS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 1139, 1124); // set size of Home View
		
		// set up contentPane panel
		this.setLayout(new BorderLayout());
		this.setBackground(CL.cararra);
		this.add(pview, BorderLayout.CENTER);
		this.add(fpview, BorderLayout.CENTER);
		this.add(prview, BorderLayout.CENTER);
		this.add(ssview, BorderLayout.CENTER);
		// setup slide show in center panel_1
		//SlideShowPanel ssp = new SlideShowPanel();		
		//panel_1.add(ssp);
				
		
		
		//set up top bar panel
		
		JPanel northPanel = new JPanel();
		northPanel.setBackground(new Color(0, 153, 204));
		this.getContentPane().add(northPanel, BorderLayout.NORTH);
		
		textFieldSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				
				//homeModel.setFindPatientsView(new FindPatientsView(home));
				HomeView.this.fpview.who
				setCenterPanel(homeModel.getPatientsView().getContentPane());
				
			}
		});
		
		
		
		//search filter
		
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String searchText = textFieldSearch.getText();
				
				homeModel.getPatientsView().filter(searchText.toLowerCase());
				
				logger.info("User entered into search: " + searchText);
			}
		});
		textFieldSearch.setColumns(10);
		
		
		
		// Top buttons change color from mouse hover
		
		MouseListener ml = (new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				((Component) arg0.getSource()).setBackground(new Color(2,108,143));
				((JComponent) arg0.getSource()).setOpaque(true);
				//btnAddPatient.setContentAreaFilled(true);
				((AbstractButton) arg0.getSource()).setBorderPainted(false);
				//System.out.println("mouse over");
			}
			public void mouseExited(MouseEvent arg0) {
				((Component) arg0.getSource()).setBackground(new Color(0, 153, 204));
				((JComponent) arg0.getSource()).setOpaque(true);
				//btnAddPatient.setContentAreaFilled(true);
				((AbstractButton) arg0.getSource()).setBorderPainted(false);
				//System.out.println("mouse over");
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
		/********** font from 16 to 18 ********************/
		btnAddPatient.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddPatient.setForeground(new Color(255, 255, 255));
		btnAddPatient.setOpaque(true);
		btnAddPatient.setContentAreaFilled(false);
		btnAddPatient.setBorderPainted(false);
		btnAddPatient.setIcon(new ImageIcon("add-icon.png"));

		
		
		//add patient button on click
		
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 logger.info("User pressed 'Add Patient'");
				 
				 homeModel.setAddPatientView(new AddPatientView(home));
				 homeModel.getAddPatientView().clearinput();
				  setCenterPanel(homeModel.getAddPatientView().getContentPane());
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
				
				logger.info("User pressed 'Log Out'");
				
				int response = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to close all windows and logout?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (response == JOptionPane.YES_OPTION) {
					logger.info("User pressed 'YES' in logout dialogue");
					
					dispose();
					LoginView login = new LoginView();
					login.show();
					
				} else {
					
					logger.info("User pressed 'NO' in logout dialogue");
				}
			}
		});
		
		
		btnFindPatient.addMouseListener(ml);
		btnFindPatient.setForeground(new Color(255, 255, 255));
		btnFindPatient.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFindPatient.setOpaque(true);
		btnFindPatient.setContentAreaFilled(false);
		btnFindPatient.setBorderPainted(false);
		btnFindPatient.setIcon(new ImageIcon("search.png"));
		
		
		
		//find patient button on click
		
		btnFindPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				logger.info("User pressed 'Find Patient'");
				
				homeModel.setFindPatientsView(new FindPatientsView(home));
				setCenterPanel(homeModel.getPatientsView().getContentPane());
			}
		});
		
		
		
		btnAllergyAlert = new JButton("ALLERGY ALERT");
		btnAllergyAlert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 PatientRecordView prv = new PatientRecordView(HomeView.this, p);
			     home.setPatient(p);
			     
			     AllergyTabView atv = new AllergyTabView(p, prv, homeModel.getAtg());
			     
			     home.setCenterPanel(prv);
			     Component c = prv.getComponentAt(1);
			     prv.setSelectedComponent(c);
			     
			}
		});
		
		btnAllergyAlert.setForeground(CL.lightOrange);
		btnAllergyAlert.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAllergyAlert.setOpaque(true);
		btnAllergyAlert.setContentAreaFilled(false);
		btnAllergyAlert.setBorderPainted(false);
		btnAllergyAlert.setVisible(false);
		
		
		
		
		//set up top bar panel layout
		GroupLayout gl_panel = new GroupLayout(northPanel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(btnHome)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAddPatient, 0, 130, 160)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnFindPatient, 0, 130, 160)
					.addPreferredGap(ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
					.addComponent(btnAllergyAlert)
					.addGap(36)
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
					.addComponent(btnAddPatient, 0, 0, Short.MAX_VALUE)
					.addComponent(btnFindPatient, 0, 0, Short.MAX_VALUE))
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblPatientSearch, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
					.addComponent(btnLogout, 0, 0, Short.MAX_VALUE)
					.addComponent(btnAllergyAlert, 0, 0, Short.MAX_VALUE))
				.addComponent(btnHome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		);
		lblPatientSearch.setForeground(new Color(255, 255, 255));
		lblPatientSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		northPanel.setLayout(gl_panel);
		
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
	}
	
	/**
	 * Replaces center/main panel with given JPanel.
	 * @param container
	 */
	public void setCenterPanel(Container container) {
		
		LayoutManager layout = this.getContentPane().getLayout();
		
		Component centerComponent = ((BorderLayout) layout).getLayoutComponent(BorderLayout.CENTER);
		
		if(centerComponent != null ) {
			this.getContentPane().remove(centerComponent);
		}
		
		if(homeModel.getAddPatientView() != null)
			homeModel.getAddPatientView().hideBalloonTips();
		
		this.getContentPane().add(container, BorderLayout.CENTER);
		this.getContentPane().repaint();
		this.getContentPane().validate();
		
	}
	
	/**
	 * Set up the center panel for the HomeView.
	 */
	public void showHomeView() {
		
		LayoutManager layout = contentPane.getLayout();
		
		Component centerComponent = ((BorderLayout) layout).getLayoutComponent(BorderLayout.CENTER);
		
		if(centerComponent != null ) {
			contentPane.remove(centerComponent);
			
			SlideShowPanel ssp = new SlideShowPanel();
			contentPane.add(ssp);
		}
		
		if(homeModel.getAddPatientView() != null)
			homeModel.getAddPatientView().hideBalloonTips();
		
		contentPane.repaint();
		contentPane.revalidate();
	}
	//TODO 
	/**
	 * Gets the homeModel, which HomeView inits and holds a reference for.
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

	/**
	 *  Called by LoginView to show HomeView
	 */
	public void launchUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setExtendedState(JFrame.MAXIMIZED_BOTH);
				setVisible(true);
			}
		});
		
	}	
	
	/**
	 * Sets the Active Patient on the Home menu
	 * @param pp patient to set the Home menu to
	 */
	public void setPatient(Patient pp){
		p = pp;
		checkPatientForAllergies();
	}
	
	/**
	 * Helper function for checking if active Patient has Allergies
	 */
	public void checkPatientForAllergies(){
		List<Allergy> tmpAl = null;
		try {
			tmpAl = homeModel.getAtg().fetchAllergiesForPatient(p);
		} catch (GatewayException e) {
			e.printStackTrace();
		}
		// if patient has allergies
		if(tmpAl.size() > 0){
			// set an alert
			btnAllergyAlert.setVisible(true);
		} else {
			btnAllergyAlert.setVisible(false);
		}
	}
	
	/**
	 * When closing an Active Patient, call this function to remove the allery alert
	 */
	public void closePatient(){
		p = null;
		btnAllergyAlert.setVisible(false);
	}
}
