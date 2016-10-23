package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.EMRS;
import networksetup.mastercomunication;

@SuppressWarnings("serial")
public class LoginView extends JFrame {
	
	private static final Logger logger = LogManager.getLogger(LoginView.class);
	
	// holds everything in LoginView
	private JPanel contentPane; 
	
	private JTextField textField_Username;
	private JPasswordField passwordField;

	/**
	 * Sets the Login View to be visible.
	 * Essentially "launches" the application from the main method.
	 * 
	 * @throws IOException 
	 */
	public  void newWindow() throws IOException {
		setVisible(true);
	}

	/**
	 * Create the LoginView JFrame. Initialize the GUI and the HomeView.
	 */
	public LoginView() {
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// physical size of login view
		setBounds(100, 100, 450, 300);
		
		/*
		 * Sets the JFrame's content pane to a JPanel,
		 * even though JFrame has a root pane that is the content pane.
		 * 
		 * Probably from WindowBuilder; though this makes it verbose and legible
		 * 
		 */
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setForeground(new Color(51, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		JLabel lblUsername = new JLabel("username");
		JLabel lblPassword = new JLabel("password");
		
		textField_Username = new JTextField();
		textField_Username.setColumns(10);
		
		passwordField = new JPasswordField();
		
		
		
		/*
		 * Launches the HomeView 
		 */
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				logger.info("User pressed 'Login'");
				
				setVisible(false);
				
				// releases resources of the login window
				dispose();
				
				//Create home view
				HomeView home  = new HomeView();
				EMRS.notification.setHomeview(home);
				home.setBounds(100, 100, 1080,720);
				home.launchUI();
			}
		});
		
		
		
		// From WindowBuilder
		
		JLabel lblEmrc = new JLabel("");
		lblEmrc.setIcon(new ImageIcon("logo.png"));
		lblEmrc.setFont(new Font("Roboto", Font.BOLD, 30));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnLogin))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(58)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPassword)
								.addComponent(lblUsername))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
								.addComponent(textField_Username, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
								.addComponent(lblEmrc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(97))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addComponent(lblEmrc)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_Username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(27)
					.addComponent(btnLogin)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		contentPane.setLayout(gl_contentPane);
	}
}
