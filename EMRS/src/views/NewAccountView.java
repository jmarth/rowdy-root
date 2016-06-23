package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.RoundedBalloonStyle;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class NewAccountView extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JTextField firstNameTextField;
	private JTextField middleNameTextField;
	private JTextField lastNameTextField;
	private JTextField dobTextField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public NewAccountView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewAccount = new JLabel("New Account");
		lblNewAccount.setFont(new Font("Lucida Grande", Font.PLAIN, 37));
		GridBagConstraints gbc_lblNewAccount = new GridBagConstraints();
		gbc_lblNewAccount.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewAccount.gridx = 0;
		gbc_lblNewAccount.gridy = 1;
		panel.add(lblNewAccount, gbc_lblNewAccount);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 4;
		panel.add(panel_1, gbc_panel_1);
		
		JLabel lblUsername = new JLabel("Username");
		panel_1.add(lblUsername);
		
		usernameTextField = new JTextField();
		panel_1.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 5;
		panel.add(panel_2, gbc_panel_2);
		
		JLabel lblPassword = new JLabel("Password");
		panel_2.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		panel_2.add(passwordField);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 6;
		panel.add(panel_3, gbc_panel_3);
		
		JLabel lblFirstName = new JLabel("First Name");
		panel_3.add(lblFirstName);
		
		firstNameTextField = new JTextField();
		BalloonTip firstNameBalloon = createBalloonTip(firstNameTextField, "First name required!");
		addBalloonTip(firstNameTextField, firstNameBalloon, ""); // not sure what the regex argument is for
		panel_3.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		JLabel label = new JLabel("     ");
		panel_3.add(label);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		panel_3.add(lblMiddleName);
		
		middleNameTextField = new JTextField();
		panel_3.add(middleNameTextField);
		middleNameTextField.setColumns(10);
		
		JLabel label_1 = new JLabel("     ");
		panel_3.add(label_1);
		
		JLabel lblLastName = new JLabel("Last Name");
		panel_3.add(lblLastName);
		
		lastNameTextField = new JTextField();
		panel_3.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 7;
		panel.add(panel_4, gbc_panel_4);
		
		JLabel lblGender = new JLabel("Gender");
		panel_4.add(lblGender);
		
		JComboBox genderComboBox = new JComboBox();
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"-", "Male", "Female"}));
		panel_4.add(genderComboBox);
		
		JLabel label_2 = new JLabel("     ");
		panel_4.add(label_2);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		panel_4.add(lblDateOfBirth);
		
		dobTextField = new JTextField();
		panel_4.add(dobTextField);
		dobTextField.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 8;
		panel.add(panel_5, gbc_panel_5);
		
		JLabel lblRole = new JLabel("Role");
		panel_5.add(lblRole);
		
		JComboBox roleComboBox = new JComboBox();
		roleComboBox.setModel(new DefaultComboBoxModel(new String[] {"-", "Nurse", "Doctor", "Clerk", "Intern", "Admin"}));
		panel_5.add(roleComboBox);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(10, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 11;
		panel.add(panel_6, gbc_panel_6);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save Button");
			}
		});
		panel_6.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cancel Button");
			}
		});
		panel_6.add(btnCancel);
	}
	
	public void addBalloonTip(final JTextField textField, final BalloonTip balloonTip, final String regex) {
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(textField.getText());
				if(matcher.matches()) {
					balloonTip.setVisible(false);
				}
				else {
					balloonTip.setVisible(true);
				}

			}
		});
	}
	
	public boolean checkForErrors() {
		if(true) {
			JOptionPane.showMessageDialog(null, "Please fix all errors before saving.", "Field Errors!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public static BalloonTip createBalloonTip(JTextField component, String contents) {
			BalloonTipStyle tipStyle = new RoundedBalloonStyle(1, 1,
				new Color(255, 100, 100, 200), Color.gray);
			return new BalloonTip(component, contents, tipStyle, true);
}
}
