package views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

import models.Patient;
import javax.swing.JSeparator;

public class PatientProfile extends JFrame {

	private JPanel contentPane;
	private static String pname;


	/**
	 * Create the frame.
	 */
	public PatientProfile(Home home, Patient patient) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 904, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Profile", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{63, 293, 0};
		gbl_panel.rowHeights = new int[]{77, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_4 = new JPanel();
		//panel_4.setBorder(new TitledBorder(null, "General Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		TitledBorder tb_1 = new TitledBorder(null, "General Information", TitledBorder.LEADING, TitledBorder.TOP,  null, null);
		tb_1.setTitleJustification(TitledBorder.LEFT);
		tb_1.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.setBorder(tb_1);
		
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 2;
		gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_4.anchor = GridBagConstraints.NORTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panel.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{281, 0};
		gbl_panel_4.rowHeights = new int[]{0, 54, 0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(patient.getPicPath());
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        lblNewLabel_5.setIcon(imageIcon);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 0;
		panel_4.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.VERTICAL;
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 1;
		panel_4.add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{78, 96, 77, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 22, 0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblfirstname = new JLabel(patient.getFirstName());
		lblfirstname.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblfirstname = new GridBagConstraints();
		gbc_lblfirstname.anchor = GridBagConstraints.WEST;
		gbc_lblfirstname.insets = new Insets(5, 0, 5, 5);
		gbc_lblfirstname.gridx = 0;
		gbc_lblfirstname.gridy = 0;
		panel_6.add(lblfirstname, gbc_lblfirstname);
		
		JLabel lblmiddlename = new JLabel(patient.getMiddleName());
		lblmiddlename.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblmiddlename = new GridBagConstraints();
		gbc_lblmiddlename.anchor = GridBagConstraints.WEST;
		gbc_lblmiddlename.insets = new Insets(5, 0, 5, 5);
		gbc_lblmiddlename.gridx = 1;
		gbc_lblmiddlename.gridy = 0;
		panel_6.add(lblmiddlename, gbc_lblmiddlename);
		
		JLabel lbllastname = new JLabel(patient.getLastName());
		lbllastname.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lbllastname = new GridBagConstraints();
		gbc_lbllastname.anchor = GridBagConstraints.WEST;
		gbc_lbllastname.insets = new Insets(5, 0, 5, 0);
		gbc_lbllastname.gridx = 2;
		gbc_lbllastname.gridy = 0;
		panel_6.add(lbllastname, gbc_lbllastname);
		
		MatteBorder border = new MatteBorder(1, 0, 0, 0, Color.GRAY);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBorder(border);
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 1;
		gbc_separator_2.gridy = 1;
		panel_6.add(separator_2, gbc_separator_2);
		
		JSeparator separator = new JSeparator();
		separator.setBorder(border);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		panel_6.add(separator, gbc_separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBorder(border);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridx = 2;
		gbc_separator_1.gridy = 1;
		panel_6.add(separator_1, gbc_separator_1);
		
		JLabel lblNewLabel = new JLabel("Given");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel_6.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblMiddle = new JLabel("Middle");
		lblMiddle.setForeground(Color.GRAY);
		lblMiddle.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblMiddle = new GridBagConstraints();
		gbc_lblMiddle.anchor = GridBagConstraints.WEST;
		gbc_lblMiddle.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiddle.gridx = 1;
		gbc_lblMiddle.gridy = 2;
		panel_6.add(lblMiddle, gbc_lblMiddle);
		
		JLabel lblNewLabel_1 = new JLabel("Family Name");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		panel_6.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblGender = new JLabel(patient.getGender());
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblGender.insets = new Insets(10, 0, 5, 5);
		gbc_lblGender.gridx = 0;
		gbc_lblGender.gridy = 3;
		panel_6.add(lblGender, gbc_lblGender);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblAge = new JLabel(patient.getAge()+" year(s)");
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.gridx = 1;
		gbc_lblAge.gridy = 3;
		panel_6.add(lblAge, gbc_lblAge);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblBirthdate = new JLabel("("+patient.getBirthDay()+"."+patient.getBirthMonth()+"."+patient.getBirthYear()+")");
		GridBagConstraints gbc_lblBirthdate = new GridBagConstraints();
		gbc_lblBirthdate.insets = new Insets(0, 0, 5, 0);
		gbc_lblBirthdate.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblBirthdate.gridx = 2;
		gbc_lblBirthdate.gridy = 3;
		panel_6.add(lblBirthdate, gbc_lblBirthdate);
		lblBirthdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBorder(border);
		GridBagConstraints gbc_separator_5 = new GridBagConstraints();
		gbc_separator_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_5.insets = new Insets(0, 0, 5, 0);
		gbc_separator_5.gridx = 2;
		gbc_separator_5.gridy = 4;
		panel_6.add(separator_5, gbc_separator_5);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBorder(border);
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_4.insets = new Insets(0, 0, 5, 5);
		gbc_separator_4.gridx = 1;
		gbc_separator_4.gridy = 4;
		panel_6.add(separator_4, gbc_separator_4);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBorder(border);
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_3.insets = new Insets(0, 0, 5, 5);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 4;
		panel_6.add(separator_3, gbc_separator_3);
		
		JLabel lblNewLabel_2 = new JLabel("Gender");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 5;
		panel_6.add(lblNewLabel_2, gbc_lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JLabel lblNewLabel_3 = new JLabel("Age");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 5;
		panel_6.add(lblNewLabel_3, gbc_lblNewLabel_3);
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JLabel lblNewLabel_4 = new JLabel("Birth Date");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 5;
		panel_6.add(lblNewLabel_4, gbc_lblNewLabel_4);
		lblNewLabel_4.setForeground(Color.GRAY);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.VERTICAL;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 2;
		panel_4.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0, 46, 0};
		gbl_panel_7.rowHeights = new int[]{14, 0, 0, 0, 14, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		String address = patient.getAddress()+" "+patient.getAddress2();
		JLabel lblNewLabel_7 = new JLabel();
		if(!patient.getCity().equals(""))
			address+=" "+patient.getCity();
		if(!patient.getState().equals(""))
			address+=" "+patient.getState();
		if(!patient.getCountry().equals(""))
			address+=" "+patient.getCountry();
		if(!patient.getPostalCode().equals(""))
			address+=" "+patient.getPostalCode();
		lblNewLabel_7.setText(address);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 0;
		panel_7.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JLabel lblPhone = new JLabel(patient.getPhoneNumber());
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.WEST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 0);
		gbc_lblPhone.gridx = 1;
		gbc_lblPhone.gridy = 0;
		panel_7.add(lblPhone, gbc_lblPhone);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBorder(border);
		GridBagConstraints gbc_separator_7 = new GridBagConstraints();
		gbc_separator_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_7.insets = new Insets(0, 0, 5, 0);
		gbc_separator_7.gridx = 1;
		gbc_separator_7.gridy = 1;
		panel_7.add(separator_7, gbc_separator_7);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBorder(border);
		GridBagConstraints gbc_separator_6 = new GridBagConstraints();
		gbc_separator_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_6.insets = new Insets(0, 0, 5, 5);
		gbc_separator_6.gridx = 0;
		gbc_separator_6.gridy = 1;
		panel_7.add(separator_6, gbc_separator_6);
		
		JLabel lblNewLabel_6 = new JLabel("Address");
		lblNewLabel_6.setForeground(Color.GRAY);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 2;
		panel_7.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblTelephoneNumber = new JLabel("Telephone Number");
		lblTelephoneNumber.setForeground(Color.GRAY);
		lblTelephoneNumber.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblTelephoneNumber = new GridBagConstraints();
		gbc_lblTelephoneNumber.insets = new Insets(0, 0, 5, 0);
		gbc_lblTelephoneNumber.gridx = 1;
		gbc_lblTelephoneNumber.gridy = 2;
		panel_7.add(lblTelephoneNumber, gbc_lblTelephoneNumber);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("History", null, panel_5, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Meds", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Labs", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Orders", null, panel_3, null);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
	}
	
	public Container getContentPane() {
		return contentPane;
	}

}
