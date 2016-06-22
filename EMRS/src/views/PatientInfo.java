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
import java.awt.Container;

import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SwingConstants;

public class PatientInfo extends JFrame {

	private JPanel contentPane;
	private static JPanel homePane;
	private String pname;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the frame.
	 */
	public PatientInfo(JPanel p) {
		homePane = p;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{674, 0};
		gbl_contentPane.rowHeights = new int[]{451, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_12, "2, 2, fill, fill");
		panel_12.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JPanel panel_10 = new JPanel();
		panel_12.add(panel_10, "1, 1, fill, fill");
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_10.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_10.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		JLabel lblWhatsThePatients = new JLabel("What's the patient's name?");
		GridBagConstraints gbc_lblWhatsThePatients = new GridBagConstraints();
		gbc_lblWhatsThePatients.insets = new Insets(0, 0, 5, 5);
		gbc_lblWhatsThePatients.gridx = 0;
		gbc_lblWhatsThePatients.gridy = 0;
		panel_10.add(lblWhatsThePatients, gbc_lblWhatsThePatients);
		
		JLabel lblGiven = new JLabel("GIven (required)");
		GridBagConstraints gbc_lblGiven = new GridBagConstraints();
		gbc_lblGiven.insets = new Insets(0, 0, 5, 5);
		gbc_lblGiven.anchor = GridBagConstraints.WEST;
		gbc_lblGiven.gridx = 0;
		gbc_lblGiven.gridy = 1;
		panel_10.add(lblGiven, gbc_lblGiven);
		
		JLabel lblMiddle = new JLabel("Middle");
		GridBagConstraints gbc_lblMiddle = new GridBagConstraints();
		gbc_lblMiddle.anchor = GridBagConstraints.WEST;
		gbc_lblMiddle.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiddle.gridx = 1;
		gbc_lblMiddle.gridy = 1;
		panel_10.add(lblMiddle, gbc_lblMiddle);
		
		JLabel lblFamilyName = new JLabel("Family Name (required)");
		lblFamilyName.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblFamilyName = new GridBagConstraints();
		gbc_lblFamilyName.anchor = GridBagConstraints.WEST;
		gbc_lblFamilyName.insets = new Insets(0, 0, 5, 0);
		gbc_lblFamilyName.gridx = 2;
		gbc_lblFamilyName.gridy = 1;
		panel_10.add(lblFamilyName, gbc_lblFamilyName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		panel_10.add(textField, gbc_textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel_10.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 2;
		panel_10.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "Gender", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_11, "2, 4, fill, fill");
		panel_11.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JPanel panel_9 = new JPanel();
		panel_11.add(panel_9, "1, 1, fill, fill");
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Brithdate", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_5, "2, 6, fill, fill");
		panel_5.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4, "1, 1, fill, fill");
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6, "2, 8, fill, fill");
		panel_6.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Address", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.add(panel_7, "1, 1, fill, fill");
		panel_7.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JPanel panel_3 = new JPanel();
		panel_7.add(panel_3, "1, 1, fill, fill");
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Phone Number", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_8, "2, 10, fill, fill");
		panel_8.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JPanel panel_1 = new JPanel();
		panel_8.add(panel_1, "1, 1, default, fill");
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	
	public String getPname() {
		return pname;
	}
	
	public Container getContentPane() {
		return contentPane;
	}
}
