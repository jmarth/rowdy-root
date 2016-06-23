package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.DropMode;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import database.GatewayException;
import models.Patient;
import models.PatientList;
import net.java.balloontip.BalloonTip;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PatientInfo extends JFrame {

	private Home home;
	private JPanel contentPane;
	private static JPanel homePane;
	private String pname;
	private JTextField firstNameTextField;
	private JTextField middleNameTextField;
	private JTextField lastNameTextField;
	private JTextField birthDayTextField;
	private JTextField birthYearTextField;
	private JTextField estYearsTextField;
	private JTextField estMonthsTextField;
	private JTextField addressTextField;
	private JTextField address2TextField;
	private JTextField cityTextField;
	private JTextField countryTextField;
	private JTextField postalCodeTextField;
	private JTextField stateTextField;
	private JTextField phoneNumberTextField;
	private PatientList pl;
	private final String NAME_PATTERN = "^[a-z ,.'-]+$";
	private final String NAME_PATTERN_2 = "(^[a-z ,.'-]+$)?";
	private BalloonTip firstNameBalloon;
	private BalloonTip middleNameBalloon;
	private BalloonTip lastNameBalloon;
	private BalloonTip dateDayBalloon;
	private BalloonTip dateYearBalloon ;
	private BalloonTip estYearBalloon;
	private BalloonTip estMonthBalloon;
	private BalloonTip cityBalloon;
	private BalloonTip stateBalloon;
	private BalloonTip countryBalloon;
	
	/**
	 * Create the frame.
	 */
	public PatientInfo(final Home home) {
		System.out.print("here");
		homePane = home.getContentPane();
		this.home = home;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 710, 727);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{674, 0};
		gbl_contentPane.rowHeights = new int[] {451, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {672, 0};
		gbl_panel.rowHeights = new int[] {81, 81, 81, 81, 81, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0, 0, 0, 0, 0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_7 = new JPanel();
		TitledBorder tb_1 = new TitledBorder(null, "What's the patient's name?", TitledBorder.LEADING, TitledBorder.TOP,  null, null);
		tb_1.setTitleJustification(TitledBorder.CENTER);
		tb_1.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		panel_7.setBorder(tb_1);
		
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 0;
		panel.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0, 0};
		gbl_panel_7.rowHeights = new int[]{0, 0};
		gbl_panel_7.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel_7.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		final JCheckBox hasNameCheckBox = new JCheckBox("Unidentified Patient");
		hasNameCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				firstNameTextField.setEnabled(!hasNameCheckBox.isSelected());
				firstNameTextField.setEditable(!hasNameCheckBox.isSelected());
				middleNameTextField.setEnabled(!hasNameCheckBox.isSelected());
				middleNameTextField.setEditable(!hasNameCheckBox.isSelected());
				lastNameTextField.setEnabled(!hasNameCheckBox.isSelected());
				lastNameTextField.setEditable(!hasNameCheckBox.isSelected());
			}
		});
		GridBagConstraints gbc_hasNameCheckBox = new GridBagConstraints();
		gbc_hasNameCheckBox.anchor = GridBagConstraints.WEST;
		gbc_hasNameCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_hasNameCheckBox.gridx = 0;
		gbc_hasNameCheckBox.gridy = 1;
		panel_1.add(hasNameCheckBox, gbc_hasNameCheckBox);
		
		JLabel lblNewLabel = new JLabel("Given (required)");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Middle");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Family Name (required)");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 2;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		firstNameTextField = new JTextField();
		BalloonTip firstNameBalloon = new BalloonTip(firstNameTextField, "Invalid name");
		firstNameBalloon.setVisible(false);
		addBalloonTip(firstNameTextField, firstNameBalloon, NAME_PATTERN);
		GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
		gbc_firstNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameTextField.gridx = 0;
		gbc_firstNameTextField.gridy = 3;
		panel_1.add(firstNameTextField, gbc_firstNameTextField);
		firstNameTextField.setColumns(10);
		
		middleNameTextField = new JTextField();
		BalloonTip middleNameBalloon = new BalloonTip(middleNameTextField, "Invalid name");
		middleNameBalloon.setVisible(false);
		addBalloonTip(middleNameTextField, middleNameBalloon, NAME_PATTERN_2);
		GridBagConstraints gbc_middleNameTextField = new GridBagConstraints();
		gbc_middleNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_middleNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_middleNameTextField.gridx = 1;
		gbc_middleNameTextField.gridy = 3;
		panel_1.add(middleNameTextField, gbc_middleNameTextField);
		middleNameTextField.setColumns(10);
		
		lastNameTextField = new JTextField();
		BalloonTip lastNameBalloon = new BalloonTip(lastNameTextField, "Invalid name");
		lastNameBalloon.setVisible(false);
		addBalloonTip(lastNameTextField, lastNameBalloon, NAME_PATTERN);
		GridBagConstraints gbc_lastNameTextField = new GridBagConstraints();
		gbc_lastNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameTextField.gridx = 2;
		gbc_lastNameTextField.gridy = 3;
		panel_1.add(lastNameTextField, gbc_lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		TitledBorder tb_2 = new TitledBorder(null, "What's the patient's gender?", TitledBorder.LEADING, TitledBorder.TOP,  null, null);
		tb_2.setTitleJustification(TitledBorder.CENTER);
		tb_2.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.setBorder(tb_2);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{672, 0};
		gbl_panel_2.rowHeights = new int[]{81, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.VERTICAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel_2.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("(required)");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 9;
		gbc_lblNewLabel_3.gridy = 1;
		panel_3.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		final JComboBox genderComboBox = new JComboBox();
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"male", "female"}));
		GridBagConstraints gbc_genderComboBox = new GridBagConstraints();
		gbc_genderComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_genderComboBox.gridx = 9;
		gbc_genderComboBox.gridy = 2;
		panel_3.add(genderComboBox, gbc_genderComboBox);
		
		JPanel panel_8 = new JPanel();
		TitledBorder tb_3 = new TitledBorder(null, "What's the patient's birth date?", TitledBorder.LEADING, TitledBorder.TOP,  null, null);
		tb_3.setTitleJustification(TitledBorder.CENTER);
		tb_3.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		panel_8.setBorder(tb_3);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 2;
		panel.add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{672, 0};
		gbl_panel_8.rowHeights = new int[]{81, 0};
		gbl_panel_8.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.VERTICAL;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panel_8.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblNewLabel_4 = new JLabel("Day");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 1;
		panel_4.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Month");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 1;
		panel_4.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblYear = new JLabel("Year");
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.anchor = GridBagConstraints.WEST;
		gbc_lblYear.insets = new Insets(0, 0, 5, 0);
		gbc_lblYear.gridx = 2;
		gbc_lblYear.gridy = 1;
		panel_4.add(lblYear, gbc_lblYear);
		
		birthDayTextField = new JTextField();
		BalloonTip dateDayBalloon = new BalloonTip(birthDayTextField, "Day must be 1-31");
		dateDayBalloon.setVisible(false);
		addBalloonTip(birthDayTextField, dateDayBalloon, "^(([1-9]|[12][0-9]|3[01])$)?");
		GridBagConstraints gbc_birthDayTextField = new GridBagConstraints();
		gbc_birthDayTextField.anchor = GridBagConstraints.WEST;
		gbc_birthDayTextField.insets = new Insets(0, 0, 5, 5);
		gbc_birthDayTextField.gridx = 0;
		gbc_birthDayTextField.gridy = 2;
		panel_4.add(birthDayTextField, gbc_birthDayTextField);
		birthDayTextField.setColumns(10);
		
		final JComboBox birthMonthComboBox = new JComboBox();
		birthMonthComboBox.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		GridBagConstraints gbc_birthMonthComboBox = new GridBagConstraints();
		gbc_birthMonthComboBox.anchor = GridBagConstraints.WEST;
		gbc_birthMonthComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_birthMonthComboBox.gridx = 1;
		gbc_birthMonthComboBox.gridy = 2;
		panel_4.add(birthMonthComboBox, gbc_birthMonthComboBox);
		
		birthYearTextField = new JTextField();
		BalloonTip dateYearBalloon = new BalloonTip(birthYearTextField, "Invalid year");
		dateYearBalloon.setVisible(false);
		addBalloonTip(birthYearTextField, dateYearBalloon, "(^\\d{4}$)?");
		GridBagConstraints gbc_birthYearTextField = new GridBagConstraints();
		gbc_birthYearTextField.anchor = GridBagConstraints.WEST;
		gbc_birthYearTextField.insets = new Insets(0, 0, 5, 0);
		gbc_birthYearTextField.gridx = 2;
		gbc_birthYearTextField.gridy = 2;
		panel_4.add(birthYearTextField, gbc_birthYearTextField);
		birthYearTextField.setColumns(10);
		
		JLabel lblOr = new JLabel("Or");
		GridBagConstraints gbc_lblOr = new GridBagConstraints();
		gbc_lblOr.insets = new Insets(0, 0, 5, 5);
		gbc_lblOr.gridx = 1;
		gbc_lblOr.gridy = 3;
		panel_4.add(lblOr, gbc_lblOr);
		
		JLabel lblEstimatedYears = new JLabel("Estimated Years");
		GridBagConstraints gbc_lblEstimatedYears = new GridBagConstraints();
		gbc_lblEstimatedYears.anchor = GridBagConstraints.WEST;
		gbc_lblEstimatedYears.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstimatedYears.gridx = 0;
		gbc_lblEstimatedYears.gridy = 4;
		panel_4.add(lblEstimatedYears, gbc_lblEstimatedYears);
		
		JLabel lblEstimatedMonths = new JLabel("Estimated Months");
		GridBagConstraints gbc_lblEstimatedMonths = new GridBagConstraints();
		gbc_lblEstimatedMonths.anchor = GridBagConstraints.WEST;
		gbc_lblEstimatedMonths.insets = new Insets(0, 0, 5, 0);
		gbc_lblEstimatedMonths.gridx = 2;
		gbc_lblEstimatedMonths.gridy = 4;
		panel_4.add(lblEstimatedMonths, gbc_lblEstimatedMonths);
		
		estYearsTextField = new JTextField();
		BalloonTip estYearBalloon = new BalloonTip(estYearsTextField, "Invalid age");
		estYearBalloon.setVisible(false);
		addBalloonTip(estYearsTextField, estYearBalloon, "(^\\d{3}$)?");
		GridBagConstraints gbc_estYearsTextField = new GridBagConstraints();
		gbc_estYearsTextField.anchor = GridBagConstraints.WEST;
		gbc_estYearsTextField.insets = new Insets(0, 0, 0, 5);
		gbc_estYearsTextField.gridx = 0;
		gbc_estYearsTextField.gridy = 5;
		panel_4.add(estYearsTextField, gbc_estYearsTextField);
		estYearsTextField.setColumns(10);
		
		estMonthsTextField = new JTextField();
		BalloonTip estMonthBalloon = new BalloonTip(estMonthsTextField, "Month must be 1-12");
		estMonthBalloon.setVisible(false);
		addBalloonTip(estMonthsTextField, estMonthBalloon, "(1[0-2]|[1-9])?");
		GridBagConstraints gbc_estMonthsTextField = new GridBagConstraints();
		gbc_estMonthsTextField.anchor = GridBagConstraints.WEST;
		gbc_estMonthsTextField.gridx = 2;
		gbc_estMonthsTextField.gridy = 5;
		panel_4.add(estMonthsTextField, gbc_estMonthsTextField);
		estMonthsTextField.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		TitledBorder tb_4 = new TitledBorder(null, "What's the patient's address?", TitledBorder.LEADING, TitledBorder.TOP,  null, null);
		tb_4.setTitleJustification(TitledBorder.CENTER);
		tb_4.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		panel_9.setBorder(tb_4);
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_9.insets = new Insets(0, 0, 5, 0);
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 3;
		panel.add(panel_9, gbc_panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[]{672, 0};
		gbl_panel_9.rowHeights = new int[]{81, 0, 0};
		gbl_panel_9.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_9.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_9.setLayout(gbl_panel_9);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.VERTICAL;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		panel_9.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] {0, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JLabel lblAddress = new JLabel("Address");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.anchor = GridBagConstraints.WEST;
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 1;
		panel_5.add(lblAddress, gbc_lblAddress);
		
		addressTextField = new JTextField();
		GridBagConstraints gbc_addressTextField = new GridBagConstraints();
		gbc_addressTextField.insets = new Insets(0, 0, 5, 0);
		gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressTextField.gridx = 1;
		gbc_addressTextField.gridy = 1;
		panel_5.add(addressTextField, gbc_addressTextField);
		addressTextField.setColumns(25);
		
		JLabel lblAddress_1 = new JLabel("Address 2");
		GridBagConstraints gbc_lblAddress_1 = new GridBagConstraints();
		gbc_lblAddress_1.anchor = GridBagConstraints.EAST;
		gbc_lblAddress_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblAddress_1.gridx = 0;
		gbc_lblAddress_1.gridy = 2;
		panel_5.add(lblAddress_1, gbc_lblAddress_1);
		
		address2TextField = new JTextField();
		GridBagConstraints gbc_address2TextField = new GridBagConstraints();
		gbc_address2TextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_address2TextField.gridx = 1;
		gbc_address2TextField.gridy = 2;
		panel_5.add(address2TextField, gbc_address2TextField);
		address2TextField.setColumns(10);
		
		JPanel panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.VERTICAL;
		gbc_panel_11.gridx = 0;
		gbc_panel_11.gridy = 1;
		panel_9.add(panel_11, gbc_panel_11);
		GridBagLayout gbl_panel_11 = new GridBagLayout();
		gbl_panel_11.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_11.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_11.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_11.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_11.setLayout(gbl_panel_11);
		
		JLabel lblCityvillage = new JLabel("City/Village");
		GridBagConstraints gbc_lblCityvillage = new GridBagConstraints();
		gbc_lblCityvillage.anchor = GridBagConstraints.WEST;
		gbc_lblCityvillage.insets = new Insets(0, 0, 5, 5);
		gbc_lblCityvillage.gridx = 0;
		gbc_lblCityvillage.gridy = 0;
		panel_11.add(lblCityvillage, gbc_lblCityvillage);
		
		JLabel lblStateprovince = new JLabel("State/Province");
		GridBagConstraints gbc_lblStateprovince = new GridBagConstraints();
		gbc_lblStateprovince.insets = new Insets(0, 0, 5, 5);
		gbc_lblStateprovince.gridx = 1;
		gbc_lblStateprovince.gridy = 0;
		panel_11.add(lblStateprovince, gbc_lblStateprovince);
		
		JLabel lblCountry = new JLabel("Country");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 2;
		gbc_lblCountry.gridy = 0;
		panel_11.add(lblCountry, gbc_lblCountry);
		
		JLabel lblPostalCode = new JLabel("Postal Code");
		GridBagConstraints gbc_lblPostalCode = new GridBagConstraints();
		gbc_lblPostalCode.insets = new Insets(0, 0, 5, 0);
		gbc_lblPostalCode.gridx = 3;
		gbc_lblPostalCode.gridy = 0;
		panel_11.add(lblPostalCode, gbc_lblPostalCode);
		
		cityTextField = new JTextField();
		BalloonTip cityBalloon = new BalloonTip(cityTextField, "Invalid name");
		cityBalloon.setVisible(false);
		addBalloonTip(cityTextField, cityBalloon, NAME_PATTERN_2);
		GridBagConstraints gbc_cityTextField = new GridBagConstraints();
		gbc_cityTextField.insets = new Insets(0, 0, 5, 5);
		gbc_cityTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityTextField.gridx = 0;
		gbc_cityTextField.gridy = 1;
		panel_11.add(cityTextField, gbc_cityTextField);
		cityTextField.setColumns(10);
		
		stateTextField = new JTextField();
		BalloonTip stateBalloon = new BalloonTip(stateTextField, "Invalid name");
		stateBalloon.setVisible(false);
		addBalloonTip(stateTextField, stateBalloon, "NAME_PATTERN_2");
		GridBagConstraints gbc_stateTextField = new GridBagConstraints();
		gbc_stateTextField.insets = new Insets(0, 0, 5, 5);
		gbc_stateTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_stateTextField.gridx = 1;
		gbc_stateTextField.gridy = 1;
		panel_11.add(stateTextField, gbc_stateTextField);
		stateTextField.setColumns(10);
		
		countryTextField = new JTextField();
		BalloonTip countryBalloon = new BalloonTip(countryTextField, "Invalid name");
		countryBalloon.setVisible(false);
		addBalloonTip(countryTextField, countryBalloon, "NAME_PATTERN_2");
		GridBagConstraints gbc_countryTextField = new GridBagConstraints();
		gbc_countryTextField.insets = new Insets(0, 0, 5, 5);
		gbc_countryTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_countryTextField.gridx = 2;
		gbc_countryTextField.gridy = 1;
		panel_11.add(countryTextField, gbc_countryTextField);
		countryTextField.setColumns(10);
		
		postalCodeTextField = new JTextField();
		GridBagConstraints gbc_postalCodeTextField = new GridBagConstraints();
		gbc_postalCodeTextField.insets = new Insets(0, 0, 5, 0);
		gbc_postalCodeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_postalCodeTextField.gridx = 3;
		gbc_postalCodeTextField.gridy = 1;
		panel_11.add(postalCodeTextField, gbc_postalCodeTextField);
		postalCodeTextField.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		TitledBorder tb_5 = new TitledBorder(null, "What's the patient's phone number?", TitledBorder.LEADING, TitledBorder.TOP,  null, null);
		tb_5.setTitleJustification(TitledBorder.CENTER);
		tb_5.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		panel_6.setBorder(tb_5);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 4;
		panel.add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{672, 0};
		gbl_panel_6.rowHeights = new int[]{81, 0};
		gbl_panel_6.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JPanel panel_10 = new JPanel();
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 0;
		panel_6.add(panel_10, gbc_panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{672, 0};
		gbl_panel_10.rowHeights = new int[]{20, 0};
		gbl_panel_10.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		JPanel panel_12 = new JPanel();
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.VERTICAL;
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 0;
		panel_10.add(panel_12, gbc_panel_12);
		GridBagLayout gbl_panel_12 = new GridBagLayout();
		gbl_panel_12.columnWidths = new int[]{0, 0};
		gbl_panel_12.rowHeights = new int[]{0, 0};
		gbl_panel_12.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_12.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_12.setLayout(gbl_panel_12);
		
		phoneNumberTextField = new JTextField();
		GridBagConstraints gbc_phoneNumberTextField = new GridBagConstraints();
		gbc_phoneNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumberTextField.gridx = 0;
		gbc_phoneNumberTextField.gridy = 0;
		panel_12.add(phoneNumberTextField, gbc_phoneNumberTextField);
		phoneNumberTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			  {
				//focus and unfocus all textfields to show any errors
				for (Component C : contentPane.getComponents()) {  
				    if (C instanceof JTextField || C instanceof JTextArea){

				        //((JTextComponent) C)..setfo; //abstract superclass
				    }
				}
				  Patient patient = new Patient(hasNameCheckBox.isSelected(),
						  firstNameTextField.getText(),
						  middleNameTextField.getText(),
						  lastNameTextField.getText(),
						  genderComboBox.getSelectedItem().toString(),
						  Integer.parseInt(birthDayTextField.getText()),
						  birthMonthComboBox.getSelectedItem().toString(),
						  Integer.parseInt(birthYearTextField.getText()),
						  Integer.parseInt(estYearsTextField.getText()),
						  Integer.parseInt(estMonthsTextField.getText()),
						  addressTextField.getText(),
						  address2TextField.getText(),
						  cityTextField.getText(),
						  stateTextField.getText(),
						  countryTextField.getText(),
						  postalCodeTextField.getText(),
						  phoneNumberTextField.getText());
				  try {
					home.getPatientTableGateway().insertPatient(patient);
				} catch (GatewayException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 5;
		panel.add(btnNewButton, gbc_btnNewButton);
	}
	
	public String getPname() {
		return pname;
	}
	
	public Container getContentPane() {
		return contentPane;
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
	
}
