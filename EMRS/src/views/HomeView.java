package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.CL;
import models.MasterModel;

@SuppressWarnings("serial")
public class HomeView extends JFrame implements viewinterface{
	
	private static final Logger logger = LogManager.getLogger(HomeView.class);

	//private JPanel contentPane;
	
	private MasterModel model;
	
	private AddPatientView paview;
	private FindPatientsView fpview;
	private PatientRecordView prview;
	private SlideShowPanel ssview;
	//private MasterVisit mvisitview;
	
	
	private final JButton btnHome;
	
	private JButton btnAddPatient;
	private JButton btnFindPatient;
	private JLabel lblPatientSearch;
	private JTextField textFieldSearch;
	private JButton btnLogout;
	
	
	private JButton btnAllergyAlert;
	
	private JPanel center;
	/**
	 * Home constructor.
	 * 
	 * Inits the "HomeModel" which has all the gateways, lists, and some actual views.
	 * 
	 */
	public HomeView() {
		// Models should be independent; a view should grab from a model.
		super();
		this.model = new MasterModel();
		paview = new AddPatientView();
		fpview = new FindPatientsView();
		prview = new PatientRecordView();
		ssview = new SlideShowPanel();
		//TODO mvisitview = new MasterVisit();
		this.HideallView();
		this.ssview.setVisible(true);
		setTitle("EMRS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(200, 100, 1139, 800); // set size of Home View
		
		// set up contentPane panel
		center = new JPanel(null);
		center.add(this.ssview);
		center.add(this.paview);
		center.add(this.fpview);
		center.add(this.prview);
		this.setLayout(new BorderLayout());
		this.setBackground(CL.cararra);
		//this.add(paview, BorderLayout.CENTER);
		//this.add(fpview, BorderLayout.CENTER);
		//TODO this.add(prview, BorderLayout.CENTER);
		//this.add(ssview, BorderLayout.CENTER);
		this.add(center,BorderLayout.CENTER);
		
		//TODO this.add(mvisitview, BorderLayout.CENTER);
		// setup slide show in center panel_1
		//SlideShowPanel ssp = new SlideShowPanel();		
		//panel_1.add(ssp);
		btnHome = new JButton("");
		btnAddPatient = new JButton("Add Patient");
		btnFindPatient = new JButton("Find Patient");
		lblPatientSearch= new JLabel("Patient Search");
		textFieldSearch = new JTextField();
		btnLogout = new JButton("Logout");
		
		//set up top bar panel
		
		JPanel northPanel = new JPanel();
		this.add(northPanel, BorderLayout.NORTH);
		northPanel.setBackground(new Color(0, 153, 204));
		textFieldSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				HomeView.this.HideallView();
				HomeView.this.fpview.setVisible(true);
			}
		});
		
		
		
		//search filter
		
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String searchText = textFieldSearch.getText().trim();
				
				HomeView.this.fpview.filter(searchText.toLowerCase());
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
				((AbstractButton) arg0.getSource()).setBorderPainted(false);
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
				HomeView.this.ShowHomeView();
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
				paview.setUpdateorinsert(paview.INSERTPATIENT);
				HomeView.this.ShowAddPatientView();
				
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
					login.setVisible(true);
					
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
				HomeView.this.textFieldSearch.setText("");
				HomeView.this.ShowFindPatientsView();
			}
		});
		
		
		
		btnAllergyAlert = new JButton("ALLERGY ALERT");
		btnAllergyAlert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
				/*HomeView.this.HideallView();
				HomeView.this.prview.setVisible(true);
				
			     home.setCenterPanel(prv);
			     Component c = prv.getComponentAt(1);
			     prv.setSelectedComponent(c);*/
			     
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
		this.pack();
		this.addComponentListener(new ComponentListener(){

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("get in hidden");
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("get in moved");
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				//TODO
				//System.out.println("homeview:\t"+center.getX()+":"+center.getY());
				updatecomponentsize();
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("get in gainedfocus");
			}
		});
	}
	private void updatecomponentsize(){
		paview.setBounds(0, 0, center.getWidth(), center.getHeight());
		ssview.setBounds(0, 0, center.getWidth(), center.getHeight());
		fpview.setBounds(0, 0, center.getWidth(), center.getHeight());
		prview.setBounds(0, 0, center.getWidth(), center.getHeight());
	}
	/**
	 *  Called by LoginView to show HomeView
	 */
	public void launchUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//setExtendedState(JFrame.MAXIMIZED_BOTH);
				setVisible(true);
			}
		});
		
	}		
	/**
	 * Helper function for checking if active Patient has Allergies
	 */
	public void checkPatientForAllergies(){
		// if patient has allergies
		if(model.getaL().getMyList().size() > 0){
			// set an alert
			btnAllergyAlert.setVisible(true);
		} else {
			btnAllergyAlert.setVisible(false);
		}
	}
	
	public FindPatientsView getFpview() {
		return fpview;
	}
	
	@Override // hide all children
	public void HideallView() {
		this.fpview.setVisible(false);
		this.prview.setVisible(false);
		this.paview.setVisible(false);
		this.ssview.setVisible(false);
	}

	public AddPatientView getPview() {
		return paview;
	}

	public PatientRecordView getPrview() {
		return prview;
	}

	public SlideShowPanel getSsview() {
		return ssview;
	}
	public void ShowHomeView(){
		this.HideallView();
		this.ssview.ShowView();;
	}
	public void ShowAddPatientView(){
		this.HideallView();
		this.paview.ShowView();
	}
	public void ShowFindPatientsView(){
		this.HideallView();
		this.fpview.ShowView();
	}
	public void ShowPatientRecode(){
		this.HideallView();
		this.prview.ShowView();
	}
	@Override
	public MasterModel getMasterModel() {
		return this.model;
	}
	@Override
	public void ShowView() {
		this.reload();
		this.setVisible(true);
		
	}
	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public HomeView getHomeView() {
		return this;
	}
}
