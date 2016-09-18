package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import models.MasterModel;
import models.Patient;

public class DemographicView extends JPanel implements viewinterface {
	
	//tabbedPane.addTab("Profile", null, panel, null);
	
	public DemographicView() {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{63, 293, 0};
		gbl_panel.rowHeights = new int[]{77, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		this.setLayout(gbl_panel);
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
		this.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{281, 0};
		gbl_panel_4.rowHeights = new int[]{0, 54, 0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		System.out.println("pic path "+patient.getPicPath());
		JLabel lblNewLabel_5 = new JLabel("");
		if(patient.getPicPath() == null || patient.getPicPath().equals(""))
			patient.setPicPath("user.png");
		if(patient.getPicPath() != null && !patient.getPicPath().equals("") ) {
			System.out.println("in this statment");
			ImageIcon imageIcon = new ImageIcon(patient.getPicPath());
	        Image image = imageIcon.getImage(); // transform it 
	        Image newimg = image.getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	        imageIcon = new ImageIcon(newimg);  // transform it back
	        lblNewLabel_5.setIcon(imageIcon);
		}
		System.out.println("pic path test");
	    GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
	    gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 0;
		panel_4.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		System.out.println("pic path here");
		
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
		lblfirstname.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblfirstname = new GridBagConstraints();
		gbc_lblfirstname.insets = new Insets(5, 0, 5, 5);
		gbc_lblfirstname.gridx = 0;
		gbc_lblfirstname.gridy = 0;
		panel_6.add(lblfirstname, gbc_lblfirstname);
		
		JLabel lblmiddlename = new JLabel(patient.getMiddleName());
		lblmiddlename.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblmiddlename.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblmiddlename = new GridBagConstraints();
		gbc_lblmiddlename.insets = new Insets(5, 0, 5, 5);
		gbc_lblmiddlename.gridx = 1;
		gbc_lblmiddlename.gridy = 0;
		panel_6.add(lblmiddlename, gbc_lblmiddlename);
		
		JLabel lbllastname = new JLabel(patient.getLastName());
		lbllastname.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbllastname.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lbllastname = new GridBagConstraints();
		gbc_lbllastname.insets = new Insets(5, 0, 5, 0);
		gbc_lbllastname.gridx = 2;
		gbc_lbllastname.gridy = 0;
		panel_6.add(lbllastname, gbc_lbllastname);
		
		MatteBorder border = new MatteBorder(1, 0, 0, 0, Color.GRAY);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBorder(null);
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 1;
		gbc_separator_2.gridy = 1;
		panel_6.add(separator_2, gbc_separator_2);
		
		JSeparator separator = new JSeparator();
		separator.setBorder(null);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		panel_6.add(separator, gbc_separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBorder(null);
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
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel_6.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblMiddle = new JLabel("Middle");
		lblMiddle.setForeground(Color.GRAY);
		lblMiddle.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblMiddle = new GridBagConstraints();
		gbc_lblMiddle.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiddle.gridx = 1;
		gbc_lblMiddle.gridy = 2;
		panel_6.add(lblMiddle, gbc_lblMiddle);
		
		JLabel lblNewLabel_1 = new JLabel("Family Name");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		panel_6.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblGender = new JLabel(patient.getGender());
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.SOUTH;
		gbc_lblGender.insets = new Insets(10, 0, 5, 5);
		gbc_lblGender.gridx = 0;
		gbc_lblGender.gridy = 3;
		panel_6.add(lblGender, gbc_lblGender);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblAge = new JLabel(patient.getAge()+" year(s)");
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.anchor = GridBagConstraints.SOUTH;
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.gridx = 1;
		gbc_lblAge.gridy = 3;
		panel_6.add(lblAge, gbc_lblAge);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblBirthdate = new JLabel("("+patient.getBirthDay()+"."+patient.getBirthMonth()+"."+patient.getBirthYear()+")");
		GridBagConstraints gbc_lblBirthdate = new GridBagConstraints();
		gbc_lblBirthdate.insets = new Insets(0, 0, 5, 0);
		gbc_lblBirthdate.anchor = GridBagConstraints.SOUTH;
		gbc_lblBirthdate.gridx = 2;
		gbc_lblBirthdate.gridy = 3;
		panel_6.add(lblBirthdate, gbc_lblBirthdate);
		lblBirthdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBorder(null);
		GridBagConstraints gbc_separator_5 = new GridBagConstraints();
		gbc_separator_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_5.insets = new Insets(0, 0, 5, 0);
		gbc_separator_5.gridx = 2;
		gbc_separator_5.gridy = 4;
		panel_6.add(separator_5, gbc_separator_5);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBorder(null);
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_4.insets = new Insets(0, 0, 5, 5);
		gbc_separator_4.gridx = 1;
		gbc_separator_4.gridy = 4;
		panel_6.add(separator_4, gbc_separator_4);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBorder(null);
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_3.insets = new Insets(0, 0, 5, 5);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 4;
		panel_6.add(separator_3, gbc_separator_3);
		
		JLabel lblNewLabel_2 = new JLabel("Gender");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 5;
		panel_6.add(lblNewLabel_2, gbc_lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JLabel lblNewLabel_3 = new JLabel("Age");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 5;
		panel_6.add(lblNewLabel_3, gbc_lblNewLabel_3);
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		JLabel lblNewLabel_4 = new JLabel("Birth Date");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.NORTH;
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
		gbl_panel_7.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 46, 0};
		gbl_panel_7.rowHeights = new int[]{14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		JLabel lblNewLabel_7 = new JLabel(patient.getAddress());
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 0;
		panel_7.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(patient.getAddress2());
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 0;
		panel_7.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		JLabel lblCity = new JLabel(patient.getCity());
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.gridx = 2;
		gbc_lblCity.gridy = 0;
		panel_7.add(lblCity, gbc_lblCity);
		
		JLabel lblState = new JLabel(patient.getState());
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblState = new GridBagConstraints();
		gbc_lblState.insets = new Insets(0, 0, 5, 5);
		gbc_lblState.gridx = 3;
		gbc_lblState.gridy = 0;
		panel_7.add(lblState, gbc_lblState);
		
		JLabel lblPostal = new JLabel(patient.getPostalCode());
		lblPostal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPostal = new GridBagConstraints();
		gbc_lblPostal.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostal.gridx = 4;
		gbc_lblPostal.gridy = 0;
		panel_7.add(lblPostal, gbc_lblPostal);
		
		JLabel lblCountry = new JLabel(patient.getCountry());
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 5;
		gbc_lblCountry.gridy = 0;
		panel_7.add(lblCountry, gbc_lblCountry);
		
		JLabel lblPhone = new JLabel(patient.getPhoneNumber());
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.insets = new Insets(0, 0, 5, 0);
		gbc_lblPhone.gridx = 6;
		gbc_lblPhone.gridy = 0;
		panel_7.add(lblPhone, gbc_lblPhone);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setBorder(null);
		GridBagConstraints gbc_separator_11 = new GridBagConstraints();
		gbc_separator_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_11.insets = new Insets(0, 0, 5, 5);
		gbc_separator_11.gridx = 3;
		gbc_separator_11.gridy = 1;
		panel_7.add(separator_11, gbc_separator_11);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setBorder(null);
		GridBagConstraints gbc_separator_13 = new GridBagConstraints();
		gbc_separator_13.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_13.insets = new Insets(0, 0, 5, 5);
		gbc_separator_13.gridx = 5;
		gbc_separator_13.gridy = 1;
		panel_7.add(separator_13, gbc_separator_13);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setBorder(null);
		GridBagConstraints gbc_separator_12 = new GridBagConstraints();
		gbc_separator_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_12.insets = new Insets(0, 0, 5, 5);
		gbc_separator_12.gridx = 4;
		gbc_separator_12.gridy = 1;
		panel_7.add(separator_12, gbc_separator_12);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setBorder(null);
		GridBagConstraints gbc_separator_10 = new GridBagConstraints();
		gbc_separator_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_10.insets = new Insets(0, 0, 5, 5);
		gbc_separator_10.gridx = 2;
		gbc_separator_10.gridy = 1;
		panel_7.add(separator_10, gbc_separator_10);
		
		JLabel lblNewLabel_6 = new JLabel("Address");
		lblNewLabel_6.setForeground(Color.GRAY);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 2;
		panel_7.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblAddress = new JLabel("Address 2");
		lblAddress.setForeground(Color.GRAY);
		lblAddress.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 1;
		gbc_lblAddress.gridy = 2;
		panel_7.add(lblAddress, gbc_lblAddress);
		
		JLabel lblNewLabel_9 = new JLabel("City/Village");
		lblNewLabel_9.setForeground(Color.GRAY);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 2;
		gbc_lblNewLabel_9.gridy = 2;
		panel_7.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		JLabel lblStateprovince = new JLabel("State/Province");
		lblStateprovince.setForeground(Color.GRAY);
		lblStateprovince.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblStateprovince = new GridBagConstraints();
		gbc_lblStateprovince.insets = new Insets(0, 0, 5, 5);
		gbc_lblStateprovince.gridx = 3;
		gbc_lblStateprovince.gridy = 2;
		panel_7.add(lblStateprovince, gbc_lblStateprovince);
		
		JLabel lblPostalCode = new JLabel("Postal Code");
		lblPostalCode.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblPostalCode.setForeground(Color.GRAY);
		GridBagConstraints gbc_lblPostalCode = new GridBagConstraints();
		gbc_lblPostalCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostalCode.gridx = 4;
		gbc_lblPostalCode.gridy = 2;
		panel_7.add(lblPostalCode, gbc_lblPostalCode);
		
		JLabel lblCountry_1 = new JLabel("Country");
		lblCountry_1.setForeground(Color.GRAY);
		lblCountry_1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblCountry_1 = new GridBagConstraints();
		gbc_lblCountry_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry_1.gridx = 5;
		gbc_lblCountry_1.gridy = 2;
		panel_7.add(lblCountry_1, gbc_lblCountry_1);
		
		JLabel lblTelephoneNumber = new JLabel("Telephone Number");
		lblTelephoneNumber.setForeground(Color.GRAY);
		lblTelephoneNumber.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GridBagConstraints gbc_lblTelephoneNumber = new GridBagConstraints();
		gbc_lblTelephoneNumber.insets = new Insets(0, 0, 5, 0);
		gbc_lblTelephoneNumber.gridx = 6;
		gbc_lblTelephoneNumber.gridy = 2;
		panel_7.add(lblTelephoneNumber, gbc_lblTelephoneNumber);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBorder(null);
		GridBagConstraints gbc_separator_9 = new GridBagConstraints();
		gbc_separator_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_9.insets = new Insets(0, 0, 5, 5);
		gbc_separator_9.gridx = 1;
		gbc_separator_9.gridy = 1;
		panel_7.add(separator_9, gbc_separator_9);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBorder(null);
		GridBagConstraints gbc_separator_8 = new GridBagConstraints();
		gbc_separator_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_8.insets = new Insets(0, 0, 5, 5);
		gbc_separator_8.gridx = 1;
		gbc_separator_8.gridy = 1;
		panel_7.add(separator_8, gbc_separator_8);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBorder(null);
		GridBagConstraints gbc_separator_7 = new GridBagConstraints();
		gbc_separator_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_7.insets = new Insets(0, 0, 5, 0);
		gbc_separator_7.gridx = 6;
		gbc_separator_7.gridy = 1;
		panel_7.add(separator_7, gbc_separator_7);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBorder(null);
		GridBagConstraints gbc_separator_6 = new GridBagConstraints();
		gbc_separator_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_6.insets = new Insets(0, 0, 5, 5);
		gbc_separator_6.gridx = 0;
		gbc_separator_6.gridy = 1;
		panel_7.add(separator_6, gbc_separator_6);
		//add "edit" button
		JButton editbutton = new JButton("Edit");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridx=0;
		gbc_button.gridy=1;
		gbc_button.gridwidth=2;
		gbc_button.anchor=GridBagConstraints.PAGE_START;
		this.add(editbutton, gbc_button);
		editbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HomeView homev = DemographicView.this.homeview;
				homev.getHomeModel().getAddPatientView().loadpatient(DemographicView.this.patient);
				homev.setCenterPanel(homev.getHomeModel().getAddPatientView().getContentPane());
			}
		});
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public HomeView getHomeview() {
		return homeview;
	}

	public void setHomeview(HomeView homeview) {
		this.homeview = homeview;
	}

	@Override
	public void HideallView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MasterModel getMasterModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ShowView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}
	
}
