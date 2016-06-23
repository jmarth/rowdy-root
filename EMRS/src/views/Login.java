package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import database.AccountTableGateway;
import database.AccountTableGatewayMySQL;
import database.GatewayException;
import database.PatientTableGateway;
import database.PatientTableGatewayMySQL;
import models.Account;
import models.Patient;
import models.PatientList;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	private AccountTableGateway atg;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void newWindow() throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		// Try connect to database
		// wow this is bad, why we do this twice...??
		try {
			atg = new AccountTableGatewayMySQL();
		} catch (GatewayException e) {
			JOptionPane.showMessageDialog(null, "Database is not responding for User Account.", "Database Offline!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Database is not responding for User Account.", "Database Offline!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(51, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("username");
		
		JLabel lblPassword = new JLabel("password");
		
		passwordField = new JPasswordField();
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				boolean success = false;
				// login from here, check if account in DB
				try {
					// don't use getText for p/w, returns string, string = immutable = in memory 'til garbage collection
					// use getPassword, returns char array, then hash with salt or something, hopefully
					// Dr. Robinson got web services for this, so for now its fine...until we get something better
					success = atg.loginCheck(textField.getText(), passwordField.getText());
				} catch (GatewayException e1) {
					// you blew it up
					e1.printStackTrace();
				}
				
				setVisible(false);
				dispose();
				
				//Create home view
				
				if (success) {
					Home home  = new Home();
					home.NewScreen();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Login Username or Password.", "Invalid Login", JOptionPane.ERROR_MESSAGE);
					
					// TODO: 
					// display attempts remaining before locked out, then its contact administrator
					
				}
					
				
			}
		});
		
		JLabel lblEmrc = new JLabel("EMRC");
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
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
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
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
