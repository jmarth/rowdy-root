package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdesktop.swingx.JXDatePicker;

import controller.EMRS;
import controller.rminotification;
import database.GatewayException;
import models.MasterModel;
import models.Patient;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.RoundedBalloonStyle;
import networksetup.mastercomunication;
import networksetup.message;

@SuppressWarnings("serial")
public class AddPatientView extends JPanel implements viewinterface  {
	public final static int UPDATEPATIENT =1;
	public final static int INSERTPATIENT =2;
	public enum emonth{
		January,February,March,April,May,June,July,August,September,October,November,December
	}
	public enum egender{
		male,female
	}
	private int updateorinsert;
	//Text fields
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
	//private PatientList pl; TODO why
	//combobox
	private JComboBox genderComboBox;
	private JComboBox birthMonthComboBox;
	//final String[] month = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	//image Jlabel
	private JLabel imglabel;
	
	//new button
	private JButton btnNewButton;
	//Regex
	//private final String NAME_PATTERN = "^[a-z ,.'-]+$";//the old code
	private final String NAME_PATTERN = "^[a-z]+$";// the new code (^\\d{4}$)?
	//private final String NAME_PATTERN_2 = "^$|(^[a-z ,.'-]+$)";//the old code
	private final String NAME_PATTERN_2 = "^$|(^[a-z]+$)";// the new ode
	//Balloon tips for each textfield
	private BalloonTip firstNameBalloon;
	private BalloonTip middleNameBalloon;
	private BalloonTip lastNameBalloon;
	private BalloonTip dateDayBalloon;
	private BalloonTip dateYearBalloon;
	private BalloonTip estYearBalloon;
	private BalloonTip estMonthBalloon;
	private BalloonTip cityBalloon;
	private BalloonTip stateBalloon;
	private BalloonTip countryBalloon;
	final JCheckBox hasNameCheckBox = new JCheckBox("Unidentified Patient");
	private JLabel birtDateErrorLabel;
	private String imagePath;
	
	private  JXDatePicker datePicker;
	/**
	 * Create the frame.
	 */
	public AddPatientView() {
		super();
		imagePath = "";
		this.updateorinsert=this.INSERTPATIENT;
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{674, 0};
		gbl_contentPane.rowHeights = new int[] {0, 0, 0, 451, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gbl_contentPane);
		
		imglabel = new JLabel("");
		ImageIcon imageIcon = new ImageIcon("user.png");
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        imglabel.setIcon(imageIcon);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		this.add(imglabel, gbc_label);
		JButton btnUploadPatientPic = new JButton("Upload Picture");
		btnUploadPatientPic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser file = new JFileChooser();
		        file.setCurrentDirectory(new File(System.getProperty("user.home")));
		        //filter the files
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
		        file.addChoosableFileFilter(filter);
		        int result = file.showSaveDialog(null);
		        //if the user click on save in Jfilechooser
		        if(result == JFileChooser.APPROVE_OPTION){
		        	File selectedFile = file.getSelectedFile();
		        	imagePath = selectedFile.getAbsolutePath();
		            /*ImageIcon imageIcon = new ImageIcon(imagePath);
		            Image image = imageIcon.getImage(); // transform it 
		            Image newimg = image.getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		            imageIcon = new ImageIcon(newimg);  // transform it back
*/		            try {
						AddPatientView.this.imglabel.setIcon(AddPatientView.this.loaduserimageicon(imagePath));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         }
			}
		});
		GridBagConstraints gbc_btnUploadPatientPic = new GridBagConstraints();
		gbc_btnUploadPatientPic.insets = new Insets(0, 0, 5, 0);
		gbc_btnUploadPatientPic.gridx = 0;
		gbc_btnUploadPatientPic.gridy = 2;
		this.add(btnUploadPatientPic, gbc_btnUploadPatientPic);
		
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		this.add(scrollPane, gbc_scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {672, 0};
		gbl_panel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0, 0, 0, 0, 0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_7 = new JPanel();
		TitledBorder tb_1 = new TitledBorder(null, "Requried Patient Information", TitledBorder.LEADING, TitledBorder.TOP,  null, null);
		tb_1.setTitleJustification(TitledBorder.CENTER);
		tb_1.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		tb_1.setTitleColor(new Color(2,108,143));
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
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		hasNameCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				firstNameTextField.setEnabled(!hasNameCheckBox.isSelected());
				firstNameTextField.setEditable(!hasNameCheckBox.isSelected());
				middleNameTextField.setEnabled(!hasNameCheckBox.isSelected());
				middleNameTextField.setEditable(!hasNameCheckBox.isSelected());
				lastNameTextField.setEnabled(!hasNameCheckBox.isSelected());
				lastNameTextField.setEditable(!hasNameCheckBox.isSelected());
				firstNameTextField.setText("");
				middleNameTextField.setText("");
				lastNameTextField.setText("");
				firstNameBalloon.setVisible(false);
				middleNameBalloon.setVisible(false);
				lastNameBalloon.setVisible(false);
			}
		});
		GridBagConstraints gbc_hasNameCheckBox = new GridBagConstraints();
		gbc_hasNameCheckBox.anchor = GridBagConstraints.SOUTHWEST;
		gbc_hasNameCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_hasNameCheckBox.gridx = 0;
		gbc_hasNameCheckBox.gridy = 1;
		panel_1.add(hasNameCheckBox, gbc_hasNameCheckBox);
		
		JPanel panel_15 = new JPanel();
		GridBagConstraints gbc_panel_15 = new GridBagConstraints();
		gbc_panel_15.insets = new Insets(0, 0, 5, 5);
		gbc_panel_15.fill = GridBagConstraints.BOTH;
		gbc_panel_15.gridx = 2;
		gbc_panel_15.gridy = 1;
		panel_1.add(panel_15, gbc_panel_15);
		GridBagLayout gbl_panel_15 = new GridBagLayout();
		gbl_panel_15.columnWidths = new int[]{0, 0};
		gbl_panel_15.rowHeights = new int[]{0, 0, 0};
		gbl_panel_15.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_15.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_15.setLayout(gbl_panel_15);
		
		JLabel lblNewLabel_3 = new JLabel("Gender (required)");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		panel_15.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		genderComboBox = new JComboBox(egender.values());
		GridBagConstraints gbc_genderComboBox = new GridBagConstraints();
		gbc_genderComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_genderComboBox.gridx = 0;
		gbc_genderComboBox.gridy = 1;
		panel_15.add(genderComboBox, gbc_genderComboBox);
		//genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"male", "female"}));
		
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
		Color ERROR_MESSAGE_FILL_COLOUR = new Color(255, 204, 204);
		
		firstNameBalloon = createBalloonTip(firstNameTextField, "Invalid name");
		firstNameBalloon.setVisible(false);
		addBalloonTip(firstNameTextField, firstNameBalloon, NAME_PATTERN);
		GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
		gbc_firstNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameTextField.gridx = 0;
		gbc_firstNameTextField.gridy = 3;
		panel_1.add(firstNameTextField, gbc_firstNameTextField);
		firstNameTextField.setColumns(15);
		
		middleNameTextField = new JTextField();
		middleNameBalloon = createBalloonTip(middleNameTextField, "Invalid name");
		middleNameBalloon.setVisible(false);
		addBalloonTip(middleNameTextField, middleNameBalloon, NAME_PATTERN_2);
		GridBagConstraints gbc_middleNameTextField = new GridBagConstraints();
		gbc_middleNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_middleNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_middleNameTextField.gridx = 1;
		gbc_middleNameTextField.gridy = 3;
		panel_1.add(middleNameTextField, gbc_middleNameTextField);
		middleNameTextField.setColumns(9);
		
		lastNameTextField = new JTextField();
		lastNameBalloon = createBalloonTip(lastNameTextField, "Invalid name");
		lastNameBalloon.setVisible(false);
		addBalloonTip(lastNameTextField, lastNameBalloon, NAME_PATTERN);
		GridBagConstraints gbc_lastNameTextField = new GridBagConstraints();
		gbc_lastNameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameTextField.gridx = 2;
		gbc_lastNameTextField.gridy = 3;
		panel_1.add(lastNameTextField, gbc_lastNameTextField);
		lastNameTextField.setColumns(15);
		TitledBorder tb_2 = new TitledBorder(null, "What's the patient's gender?", TitledBorder.LEADING, TitledBorder.TOP,  null, null);
		tb_2.setTitleJustification(TitledBorder.CENTER);
		tb_2.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		tb_2.setTitleColor(new Color(2,108,143));
		
		
		// BIRTH DATE
		
		JPanel panel_8 = new JPanel();
		TitledBorder tb_3 = new TitledBorder(null, "Patient's birth date(required)", TitledBorder.LEADING, TitledBorder.TOP,  null, null);
		tb_3.setTitleJustification(TitledBorder.CENTER);
		tb_3.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		tb_3.setTitleColor(new Color(2,108,143));
		panel_8.setBorder(tb_3);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 2;
		panel.add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{672, 0};
		gbl_panel_8.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_8.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		JPanel panel_13 = new JPanel();
		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
		gbc_panel_13.insets = new Insets(0, 0, 5, 0);
		gbc_panel_13.fill = GridBagConstraints.BOTH;
		gbc_panel_13.gridx = 0;
		gbc_panel_13.gridy = 0;
		panel_8.add(panel_13, gbc_panel_13);
		
		birtDateErrorLabel = new JLabel("");
		birtDateErrorLabel.setForeground(Color.RED);
		panel_13.add(birtDateErrorLabel);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.VERTICAL;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
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
		dateDayBalloon = createBalloonTip(birthDayTextField, "Day must be 1-31");
		addBalloonTip(birthDayTextField, dateDayBalloon, "^(([1-9]|[12][0-9]|3[01])$)?");
		GridBagConstraints gbc_birthDayTextField = new GridBagConstraints();
		gbc_birthDayTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_birthDayTextField.insets = new Insets(0, 0, 5, 5);
		gbc_birthDayTextField.gridx = 0;
		gbc_birthDayTextField.gridy = 2;
		panel_4.add(birthDayTextField, gbc_birthDayTextField);
		birthDayTextField.setColumns(15);
		
		birthMonthComboBox = new JComboBox(emonth.values());
		GridBagConstraints gbc_birthMonthComboBox = new GridBagConstraints();
		gbc_birthMonthComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_birthMonthComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_birthMonthComboBox.gridx = 1;
		gbc_birthMonthComboBox.gridy = 2;
		panel_4.add(birthMonthComboBox, gbc_birthMonthComboBox);
		
		birthYearTextField = new JTextField();
		birthYearTextField.setName("yeartext");
		dateYearBalloon = createBalloonTip(birthYearTextField, "Invalid year");
		addBalloonTip(birthYearTextField, dateYearBalloon, "(^\\d{4}$)?");//(^\\d{4}$)?
		GridBagConstraints gbc_birthYearTextField = new GridBagConstraints();
		gbc_birthYearTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_birthYearTextField.insets = new Insets(0, 0, 5, 0);
		gbc_birthYearTextField.gridx = 2;
		gbc_birthYearTextField.gridy = 2;
		panel_4.add(birthYearTextField, gbc_birthYearTextField);
		birthYearTextField.setColumns(15);
		
		
		// ADD DATE PICKER HERE
		
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
		estYearBalloon = createBalloonTip(estYearsTextField, "Invalid age");
		addBalloonTip(estYearsTextField, estYearBalloon, "(^[0-9]*$)?");
		GridBagConstraints gbc_estYearsTextField = new GridBagConstraints();
		gbc_estYearsTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_estYearsTextField.insets = new Insets(0, 0, 0, 5);
		gbc_estYearsTextField.gridx = 0;
		gbc_estYearsTextField.gridy = 5;
		panel_4.add(estYearsTextField, gbc_estYearsTextField);
		estYearsTextField.setColumns(10);
		
		estMonthsTextField = new JTextField();
		estMonthBalloon = createBalloonTip(estMonthsTextField, "Month must be 1-12");
		addBalloonTip(estMonthsTextField, estMonthBalloon, "(1[0-2]|[1-9])?");
		GridBagConstraints gbc_estMonthsTextField = new GridBagConstraints();
		gbc_estMonthsTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_estMonthsTextField.gridx = 2;
		gbc_estMonthsTextField.gridy = 5;
		panel_4.add(estMonthsTextField, gbc_estMonthsTextField);
		estMonthsTextField.setColumns(10);
		dateDayBalloon.setVisible(false);
		dateYearBalloon.setVisible(false);
		estYearBalloon.setVisible(false);
		estMonthBalloon.setVisible(false);
		
		JPanel panel_9 = new JPanel();
		TitledBorder tb_4 = new TitledBorder(null, "Patient's address", TitledBorder.LEADING, TitledBorder.TOP,  null, null);
		tb_4.setTitleJustification(TitledBorder.CENTER);
		tb_4.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		tb_4.setTitleColor(new Color(2,108,143));
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
		gbl_panel_5.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JLabel lblAddress = new JLabel("Address");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddress.anchor = GridBagConstraints.WEST;
		gbc_lblAddress.gridx = 1;
		gbc_lblAddress.gridy = 1;
		panel_5.add(lblAddress, gbc_lblAddress);
		
		addressTextField = new JTextField();
		GridBagConstraints gbc_addressTextField = new GridBagConstraints();
		gbc_addressTextField.insets = new Insets(0, 0, 5, 0);
		gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressTextField.gridx = 1;
		gbc_addressTextField.gridy = 2;
		panel_5.add(addressTextField, gbc_addressTextField);
		addressTextField.setColumns(42);
		
		JLabel lblAddress_1 = new JLabel("Address 2");
		GridBagConstraints gbc_lblAddress_1 = new GridBagConstraints();
		gbc_lblAddress_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddress_1.anchor = GridBagConstraints.WEST;
		gbc_lblAddress_1.gridx = 1;
		gbc_lblAddress_1.gridy = 3;
		panel_5.add(lblAddress_1, gbc_lblAddress_1);
		
		address2TextField = new JTextField();
		GridBagConstraints gbc_address2TextField = new GridBagConstraints();
		gbc_address2TextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_address2TextField.gridx = 1;
		gbc_address2TextField.gridy = 4;
		panel_5.add(address2TextField, gbc_address2TextField);
		address2TextField.setColumns(25);
		
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
		gbc_lblStateprovince.anchor = GridBagConstraints.WEST;
		gbc_lblStateprovince.insets = new Insets(0, 0, 5, 5);
		gbc_lblStateprovince.gridx = 1;
		gbc_lblStateprovince.gridy = 0;
		panel_11.add(lblStateprovince, gbc_lblStateprovince);
		
		JLabel lblCountry = new JLabel("Country");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.anchor = GridBagConstraints.WEST;
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 2;
		gbc_lblCountry.gridy = 0;
		panel_11.add(lblCountry, gbc_lblCountry);
		
		JLabel lblPostalCode = new JLabel("Postal Code");
		GridBagConstraints gbc_lblPostalCode = new GridBagConstraints();
		gbc_lblPostalCode.anchor = GridBagConstraints.WEST;
		gbc_lblPostalCode.insets = new Insets(0, 0, 5, 0);
		gbc_lblPostalCode.gridx = 3;
		gbc_lblPostalCode.gridy = 0;
		panel_11.add(lblPostalCode, gbc_lblPostalCode);
		
		cityTextField = new JTextField();
		cityBalloon = createBalloonTip(cityTextField, "Invalid city");
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
		stateBalloon = createBalloonTip(stateTextField, "Invalid state");
		stateBalloon.setVisible(false);
		addBalloonTip(stateTextField, stateBalloon, NAME_PATTERN_2);
		GridBagConstraints gbc_stateTextField = new GridBagConstraints();
		gbc_stateTextField.insets = new Insets(0, 0, 5, 5);
		gbc_stateTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_stateTextField.gridx = 1;
		gbc_stateTextField.gridy = 1;
		panel_11.add(stateTextField, gbc_stateTextField);
		stateTextField.setColumns(9);

		countryTextField = new JTextField();
		countryBalloon = createBalloonTip(countryTextField, "Invalid country");
		countryBalloon.setVisible(false);
		addBalloonTip(countryTextField, countryBalloon, NAME_PATTERN_2);
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
		postalCodeTextField.setColumns(9);
		
		JPanel panel_6 = new JPanel();
		TitledBorder tb_5 = new TitledBorder(null, "Patient's phone number", TitledBorder.LEADING, TitledBorder.TOP,  null, null);
		tb_5.setTitleJustification(TitledBorder.CENTER);
		tb_5.setTitleFont(new Font("Tahoma", Font.BOLD, 14));
		tb_5.setTitleColor(new Color(2,108,143));
		panel_6.setBorder(tb_5);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 4;
		panel.add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{672, 0};
		//gbl_panel_6.rowHeights = new int[]{0, 10, 0};
		gbl_panel_6.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JPanel panel_12 = new JPanel();
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.insets = new Insets(0, 0, 5, 0);
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 0;
		panel_6.add(panel_12, gbc_panel_12);
		panel_12.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_12.add(phoneNumberTextField);
		phoneNumberTextField.setColumns(20);
		firstNameTextField.requestFocus();
		
		JPanel panel_14 = new JPanel();
		GridBagConstraints gbc_panel_14 = new GridBagConstraints();
		gbc_panel_14.fill = GridBagConstraints.BOTH;
		gbc_panel_14.gridx = 0;
		gbc_panel_14.gridy = 5;
		panel.add(panel_14, gbc_panel_14);;
		
		JButton btnCancelButton = new JButton("Cancel");
		GridBagConstraints gbc_btnCancelButton = new GridBagConstraints();
		gbc_btnCancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelButton.gridx = 0;
		gbc_btnCancelButton.gridy = 0;
		panel_14.add(btnCancelButton, gbc_btnCancelButton);
		btnCancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				hideBalloonTips();
				if(AddPatientView.this.updateorinsert==AddPatientView.this.UPDATEPATIENT || AddPatientView.this.getMasterModel().getCurrPatient()!=null){
					AddPatientView.this.getHomeView().ShowPatientRecode();
				}else{
					AddPatientView.this.getHomeView().ShowHomeView();
				}
			}
		});
		
		btnNewButton = new JButton("Insert");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		panel_14.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				//check for fields errors
				if(!checkForErrors()) {
					//set default values
					int birthDay = -1;
					int birthYear = -1;
					int estYear = -1;
					int estMonth = -1;
					//Parse text fields if they are not empty
					if(!birthDayTextField.getText().equals("")) 
						birthDay = Integer.parseInt(birthDayTextField.getText());
					if(!birthYearTextField.getText().equals("")) 
						birthYear = Integer.parseInt(birthYearTextField.getText());
					if(!estYearsTextField.getText().equals("")) 
						estYear = Integer.parseInt(estYearsTextField.getText());
					if(!estMonthsTextField.getText().equals("")) 
						estMonth = Integer.parseInt(estMonthsTextField.getText());
					Patient patient = new Patient(hasNameCheckBox.isSelected(),
					firstNameTextField.getText(),
					middleNameTextField.getText(),
					lastNameTextField.getText(),
					genderComboBox.getSelectedItem().toString(),
					birthDay,
					AddPatientView.this.birthMonthComboBox.getSelectedItem().toString(),
					birthYear,
					estYear,
					estMonth,
					addressTextField.getText(),
					address2TextField.getText(),
					cityTextField.getText(),
					stateTextField.getText(),
					countryTextField.getText(),
					postalCodeTextField.getText(),
					phoneNumberTextField.getText(),
					imagePath!=null?imagePath:"");
					try {
						
						String fullName =  firstNameTextField.getText()+" "+
								middleNameTextField.getText()+" "+
								lastNameTextField.getText();
						MasterModel model = AddPatientView.this.getMasterModel();
						if(AddPatientView.this.updateorinsert==AddPatientView.INSERTPATIENT){
							model.getpL().insert(patient);
							//m.setCommand(mastercomunication.PATIENT_INSERT);
						}else{
							model = AddPatientView.this.getMasterModel();
							patient.setId(model.getCurrPatient().getId());
							model.getpL().update(patient);
							//m.setCommand(mastercomunication.PATIENT_UPDATE);
						}
						model.setCurrPatient(patient);
						//m.setData(patient);
						HomeView hv =AddPatientView.this.getHomeView();
						//hv.getPrview().ShowDemographicsView();
						if(EMRS.notification.getRclient()!=null){
							new Thread(new Runnable(){
								@Override
								public void run() {
									// TODO Auto-generated method stub
									try {
										message m = new message(mastercomunication.ACCESS_CODE,0,null,EMRS.notification.getOwner().getPriority());
										if(AddPatientView.this.updateorinsert==AddPatientView.INSERTPATIENT)
											m.setCommand(rminotification.PATIENT_INSERT);
										else
											m.setCommand(rminotification.PATIENT_UPDATE);
										MasterModel mod = getMasterModel();
										m.setData(mod.getCurrPatient());
										EMRS.notification.getRclient().notifychange(m);
									} catch (RemoteException e1) {
										EMRS.notification.startnewsetup();
										// TODO Auto-generated catch block
										System.err.println("Cann't notified to rmiserver in addpatientview. \n Thus asked new server");
									}
								}	
					    	}).start();
						} else if(EMRS.notification.getRserver()!=null){
							try {
								message m = new message(mastercomunication.ACCESS_CODE,0,null,EMRS.notification.getOwner().getPriority());
								if(AddPatientView.this.updateorinsert==AddPatientView.INSERTPATIENT)
									m.setCommand(rminotification.PATIENT_INSERT);
								else
									m.setCommand(rminotification.PATIENT_UPDATE);
								m.setData(patient);
								EMRS.notification.getRserver().notifiedall(m);
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								System.err.println("Cann't notified to rmiserver in addpatientview. \n Thus asked new server");
							}
						}
						hv.ShowPatientRecode();
					} catch (GatewayException e1) {
						// TODO Auto-generated catch block
						System.err.println("Cann't notified to all rmiclient in addpatientview.");
					}
					
				}
				
			 }
		});
	}
	/**
	 * load user image to 
	 */
	public ImageIcon loaduserimageicon(String path) throws Exception{
		ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(128, 128,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        return new ImageIcon(newimg);  // transform it back
        //AddPatientView.this.imglabel.setIcon(imageIcon);
	}
	/**
	 * clear all imput field
	 */
	public void clearinput(){
		this.hideBalloonTips();
		this.updateorinsert=this.INSERTPATIENT;
		this.hasNameCheckBox.setSelected(false);
		this.firstNameTextField.setText("");
		this.middleNameTextField.setText("");
		this.lastNameTextField.setText("");
		this.genderComboBox.setSelectedIndex(0);
		this.birthDayTextField.setText("");
		this.birthMonthComboBox.setSelectedIndex(-1);
		this.birthYearTextField.setText("");
		this.estMonthsTextField.setText("");
		this.estYearsTextField.setText("");
		this.addressTextField.setText("");
		this.address2TextField.setText("");
		this.cityTextField.setText("");
		this.stateTextField.setText("");
		this.countryTextField.setText("");
		this.postalCodeTextField.setText("");
		this.phoneNumberTextField.setText("");
		try {
			this.imglabel.setIcon(this.loaduserimageicon("user.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * adds balloontip to textfield and shows when
	 * textfield loses focus and does not match regex
	 * and hides when it matches the regex
	 * @param textField (jTextField)
	 * @param balloonTip (balloon tip to show or hide)
	 * @param regex (regex string that checks against textField text)
	 */
	public void addBalloonTip(final JTextField textField, final BalloonTip balloonTip, final String regex) {
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(textField.getText().toLowerCase());
				if(matcher.matches()){
					JTextField tf =(JTextField)arg0.getSource();
					if(regex.compareTo("(^\\d{4}$)?")==0){
						int year = Calendar.getInstance().get(Calendar.YEAR);
						try{
							year = year - Integer.parseInt(((JTextField)arg0.getSource()).getText().trim());
							if(year>0&&year<150){
								dateYearBalloon.setVisible(false);
							}else{
								dateYearBalloon.setVisible(true);
							}
						}catch(Exception ex){
							dateYearBalloon.setVisible(false);
						}
					}else{
						balloonTip.setVisible(false);
					}
					//balloonTip.setVisible(false);
				}
				else {
					balloonTip.setVisible(true);
				}
			}
		});
	}
	
	/**
	 * recursively looks for all jtextfields and request focus
	 * @param container 
	 */
	private void focusAllTextFields() {
		firstNameTextField.requestFocus();
		middleNameTextField.requestFocus();
		lastNameTextField.requestFocus();
		birthDayTextField.requestFocus();
		birthYearTextField.requestFocus();
		estYearsTextField.requestFocus();
		estMonthsTextField.requestFocus();
		addressTextField.requestFocus();
		address2TextField.requestFocus();
		cityTextField.requestFocus();
		countryTextField.requestFocus();
		postalCodeTextField.requestFocus();
		stateTextField.requestFocus();
		phoneNumberTextField.requestFocus();	
	}
	
	/**
	 * Checks to see if everything has been filled out correctly
	 * @return (boolean if error was found or not)
	 */
	private boolean checkForErrors() {
		boolean needsDOB = false;
		
		/*
		 * not sure if genius or idiotic
		 * This will focus all the textFields forcing
		 * the focus out listener to check for field errors
		 */
		focusAllTextFields();
		
		//Check if DOB has been entered show error if it has not been entered
		if((birthDayTextField.getText().equals("") || birthYearTextField.getText().equals("")) &&
				(estYearsTextField.getText().equals("") && estMonthsTextField.getText().equals(""))) {
			birtDateErrorLabel.setText("**You must enter the exact DOB or an estimated DOB**" );
			return true;
		}
		else {
			birtDateErrorLabel.setText("");
		}
		
		//If a balloonTip is showing the there is a field error
		if(firstNameBalloon.isVisible() ||
			middleNameBalloon.isVisible() ||
			lastNameBalloon.isVisible() ||
			dateDayBalloon.isVisible() ||
			dateYearBalloon.isVisible() || 
			estYearBalloon.isVisible() ||
			estMonthBalloon.isVisible() ||
			cityBalloon.isVisible() ||
			stateBalloon.isVisible() ||
			countryBalloon.isVisible()) {
			JOptionPane.showMessageDialog(null, "Please fix all errors before saving.", "Field Errors!", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}
	
	/**
	 * creates styled balloontip
	 * @param component (a jtextfield)
	 * @param contents (text to be shown in the balloon tip)
	 * @return (a new balloontip with given data)
	 */
	public static BalloonTip createBalloonTip(JTextField component, String contents) {
		BalloonTipStyle tipStyle = new RoundedBalloonStyle(1, 1,
			new Color(0,153,204), new Color(112,154,208));
		//BalloonTip bt = new BalloonTip(component, contents, tipStyle, Orientation.RIGHT_BELOW, AttachLocation.ALIGNED, 40, 20, true);
		//BalloonTipStyle edgedLook = new EdgedBalloonStyle(Color.WHITE, Color.BLUE);
		JLabel lbl = new JLabel("<html><b>"+contents+"</b></html>");
		lbl.setForeground(new Color(255,255,255));
		BalloonTip bt = new BalloonTip(component, lbl, tipStyle, Orientation.LEFT_ABOVE, AttachLocation.ALIGNED, 10, 10, false);
		JButton button = new JButton();
		ImageIcon imageIcon = new ImageIcon("closeIcon.png");
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setIcon(imageIcon);
		bt.setCloseButton(button, false);
		return bt;
	}
	
	/**
	 * hides all balloontips
	 */
	public void hideBalloonTips() {
		firstNameBalloon.setVisible(false);
		middleNameBalloon.setVisible(false);
		lastNameBalloon.setVisible(false);
		dateDayBalloon.setVisible(false);
		dateYearBalloon.setVisible(false);
		estYearBalloon.setVisible(false);
		estMonthBalloon.setVisible(false);
		cityBalloon.setVisible(false);
		stateBalloon.setVisible(false);
		countryBalloon.setVisible(false);
	}
	@Override
	public void HideallView() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		
	}
	@Override
	public MasterModel getMasterModel() {
		return this.getHomeView().getMasterModel();
	}
	@Override
	public void ShowView() {
		if(this.updateorinsert==this.UPDATEPATIENT){
			this.btnNewButton.setText("Update");
		}else{
			this.btnNewButton.setText("Insert");
		}
		this.setVisible(true);
	}
	@Override
	public void reload() {
		this.hideBalloonTips();
		Patient p = this.getMasterModel().getCurrPatient();
		this.updateorinsert=this.UPDATEPATIENT;
		this.hasNameCheckBox.setSelected(p.getHasPatientName());
		this.firstNameTextField.setText(p.getFirstName());
		this.middleNameTextField.setText(p.getMiddleName());
		this.lastNameTextField.setText(p.getLastName());
		/*if(p.getGender().compareTo("male")==0){
			this.genderComboBox.setSelectedIndex(0);
		}else{
			this.genderComboBox.setSelectedIndex(1);
		}*/
		this.genderComboBox.setSelectedIndex(egender.valueOf(p.getGender()).ordinal());
		this.birthDayTextField.setText(""+p.getBirthDay());
		this.birthMonthComboBox.setSelectedIndex(emonth.valueOf(p.getBirthMonth()).ordinal());
		this.birthYearTextField.setText(""+p.getBirthYear());
		if(p.getEstBirthMonths()!=-1)
			this.estMonthsTextField.setText(""+p.getEstBirthMonths());
		else
			this.estMonthsTextField.setText("");
		if(p.getEstBirthYears()!=-1)
			this.estYearsTextField.setText(""+p.getEstBirthYears());
		else
			this.estYearsTextField.setText("");
		this.addressTextField.setText(p.getAddress());
		this.address2TextField.setText(p.getAddress2());
		this.cityTextField.setText(p.getCity());
		this.stateTextField.setText(p.getState());
		this.countryTextField.setText(p.getCountry());
		this.postalCodeTextField.setText(p.getPostalCode());
		this.phoneNumberTextField.setText(p.getPhoneNumber());
		this.imagePath=p.getPicPath();
		try {
			this.imglabel.setIcon(this.loaduserimageicon(imagePath));
		} catch (Exception e) {
			try {
				this.imglabel.setIcon(this.loaduserimageicon("user.png"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	@Override
	public HomeView getHomeView() {
		return (HomeView)SwingUtilities.getWindowAncestor(this);
	}
	public int getUpdateorinsert() {
		return updateorinsert;
	}
	public void setUpdateorinsert(int updateorinsert) {
		this.updateorinsert = updateorinsert;
	}
	
}
