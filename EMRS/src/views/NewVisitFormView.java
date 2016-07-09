package views;

import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import database.AllergyTableGatewayMySQL;
import database.GatewayException;
import models.Allergy;
import models.Patient;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class NewVisitFormView extends JPanel {
	
	Patient patient;
	
	//gateway
	
	// sphere and cylinder = floats
	// axis = int
	private JTextField textField_Od_Sphere_Autoref;
	private JTextField textField_Od_Cylinder_Autoref;
	private JTextField textField_Od_Axis_Autoref;
	private JTextField textField_Os_Sphere_Autoref;
	private JTextField textField_Os_Cylinder_Autoref;
	private JTextField textField_Os_Axis_Autoref;
	private JTextField textField_Od_Sphere_Arc;
	private JTextField textField_Od_Cylin_Arc;
	private JTextField textField_Os_Sphere_Arc;
	private JTextField textField_Os_Cylin_Arc;
	private JTextField textField_FE1_1_1;
	private JTextField textField_FE1_1_2;
	private JTextField textField_FE1_2_1;
	private JTextField textField_FE1_2_2;
	private JTextField textField_Od_Axis_Od;
	private JTextField textField_Os_Axis_Arc;
	
	
	public NewVisitFormView(final JTabbedPane tabbedPane, Patient patient, JPanel newVisitPanel/*, VisitTableGateway gateway, final JTable allergyTable*/) {
		this.patient = patient;
		/*
		this.atg = gateway;
		this.allergyTable = allergyTable;
		oldPanel = allergiesPanel;
		*/
		/**
		 * Try to connect to DB through AllergyTableGateway
		 * Set the gateway of the AllergyList
		 * Load Allergies into the AllergyList
		 */
		
		/*
		try {
			atg = new AllergyTableGatewayMySQL();
		} catch (GatewayException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not connect to DB");
			e.printStackTrace();
		}
		*/
		
		// Put all GUI lines in a seperate method to keep clean :)
		createView(tabbedPane, patient, newVisitPanel);//, atg, allergyTable);

	}
	
	public void createView(final JTabbedPane tabbedPane, final Patient patient, JPanel newVisitPanel/*, final AllergyTableGatewayMySQL atg, final JTable allergyTable*/){
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.add(this);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 120, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblCc = new JLabel("Chief Complaint:");
		lblCc.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblCc = new GridBagConstraints();
		gbc_lblCc.anchor = GridBagConstraints.WEST;
		gbc_lblCc.insets = new Insets(0, 0, 5, 0);
		gbc_lblCc.gridx = 0;
		gbc_lblCc.gridy = 0;
		add(lblCc, gbc_lblCc);
		
		JScrollPane scrollPane_Cc = new JScrollPane();
		GridBagConstraints gbc_scrollPane_Cc = new GridBagConstraints();
		gbc_scrollPane_Cc.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Cc.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_Cc.gridx = 0;
		gbc_scrollPane_Cc.gridy = 1;
		add(scrollPane_Cc, gbc_scrollPane_Cc);
		
		JTextArea txtrCC = new JTextArea();
		txtrCC.setColumns(80);
		txtrCC.setRows(4);
		txtrCC.setWrapStyleWord(true);
		txtrCC.setLineWrap(true);
		scrollPane_Cc.setViewportView(txtrCC);
		txtrCC.setText("textArea");
		
		JLabel lblPed = new JLabel("Physical Exam Detail:");
		lblPed.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblPed = new GridBagConstraints();
		gbc_lblPed.anchor = GridBagConstraints.WEST;
		gbc_lblPed.insets = new Insets(0, 0, 5, 0);
		gbc_lblPed.gridx = 0;
		gbc_lblPed.gridy = 2;
		add(lblPed, gbc_lblPed);
		
		JLabel lblVision = new JLabel("Vision");
		GridBagConstraints gbc_lblVision = new GridBagConstraints();
		gbc_lblVision.anchor = GridBagConstraints.WEST;
		gbc_lblVision.insets = new Insets(0, 0, 5, 0);
		gbc_lblVision.gridx = 0;
		gbc_lblVision.gridy = 3;
		add(lblVision, gbc_lblVision);
		
		JPanel visionPanel = new JPanel();
		GridBagConstraints gbc_visionPanel = new GridBagConstraints();
		gbc_visionPanel.anchor = GridBagConstraints.WEST;
		gbc_visionPanel.insets = new Insets(0, 0, 5, 0);
		gbc_visionPanel.gridx = 0;
		gbc_visionPanel.gridy = 4;
		add(visionPanel, gbc_visionPanel);
		GridBagLayout gbl_visionPanel = new GridBagLayout();
		gbl_visionPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_visionPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_visionPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_visionPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		visionPanel.setLayout(gbl_visionPanel);
		
		JLabel lblAutorefraction = new JLabel("Autorefraction");
		lblAutorefraction.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblAutorefraction = new GridBagConstraints();
		gbc_lblAutorefraction.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutorefraction.gridx = 0;
		gbc_lblAutorefraction.gridy = 0;
		visionPanel.add(lblAutorefraction, gbc_lblAutorefraction);
		
		JLabel lblSphere_Autoref = new JLabel("Sphere");
		GridBagConstraints gbc_lblSphere_Autoref = new GridBagConstraints();
		gbc_lblSphere_Autoref.insets = new Insets(0, 0, 5, 5);
		gbc_lblSphere_Autoref.gridx = 2;
		gbc_lblSphere_Autoref.gridy = 0;
		visionPanel.add(lblSphere_Autoref, gbc_lblSphere_Autoref);
		
		JLabel lblCylinder_Autoref = new JLabel("Cylinder");
		GridBagConstraints gbc_lblCylinder_Autoref = new GridBagConstraints();
		gbc_lblCylinder_Autoref.insets = new Insets(0, 0, 5, 5);
		gbc_lblCylinder_Autoref.gridx = 3;
		gbc_lblCylinder_Autoref.gridy = 0;
		visionPanel.add(lblCylinder_Autoref, gbc_lblCylinder_Autoref);
		
		JLabel lblAxis_Autoref = new JLabel("Axis     ");
		GridBagConstraints gbc_lblAxis_Autoref = new GridBagConstraints();
		gbc_lblAxis_Autoref.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAxis_Autoref.insets = new Insets(0, 0, 5, 5);
		gbc_lblAxis_Autoref.gridx = 4;
		gbc_lblAxis_Autoref.gridy = 0;
		visionPanel.add(lblAxis_Autoref, gbc_lblAxis_Autoref);
		
		JLabel lblAutoref_Od = new JLabel("OD");
		GridBagConstraints gbc_lblAutoref_Od = new GridBagConstraints();
		gbc_lblAutoref_Od.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutoref_Od.anchor = GridBagConstraints.EAST;
		gbc_lblAutoref_Od.gridx = 1;
		gbc_lblAutoref_Od.gridy = 1;
		visionPanel.add(lblAutoref_Od, gbc_lblAutoref_Od);
		
		textField_Od_Sphere_Autoref = new JTextField();
		GridBagConstraints gbc_textField_Od_Sphere_Autoref = new GridBagConstraints();
		gbc_textField_Od_Sphere_Autoref.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Od_Sphere_Autoref.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Od_Sphere_Autoref.gridx = 2;
		gbc_textField_Od_Sphere_Autoref.gridy = 1;
		visionPanel.add(textField_Od_Sphere_Autoref, gbc_textField_Od_Sphere_Autoref);
		textField_Od_Sphere_Autoref.setColumns(5);
		
		textField_Od_Cylinder_Autoref = new JTextField();
		GridBagConstraints gbc_textField_Od_Cylinder_Autoref = new GridBagConstraints();
		gbc_textField_Od_Cylinder_Autoref.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Od_Cylinder_Autoref.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Od_Cylinder_Autoref.gridx = 3;
		gbc_textField_Od_Cylinder_Autoref.gridy = 1;
		visionPanel.add(textField_Od_Cylinder_Autoref, gbc_textField_Od_Cylinder_Autoref);
		textField_Od_Cylinder_Autoref.setColumns(5);
		
		textField_Od_Axis_Autoref = new JTextField();
		GridBagConstraints gbc_textField_Od_Axis_Autoref = new GridBagConstraints();
		gbc_textField_Od_Axis_Autoref.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Od_Axis_Autoref.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Od_Axis_Autoref.gridx = 4;
		gbc_textField_Od_Axis_Autoref.gridy = 1;
		visionPanel.add(textField_Od_Axis_Autoref, gbc_textField_Od_Axis_Autoref);
		textField_Od_Axis_Autoref.setColumns(5);
		
		JLabel lblAutoref_Os = new JLabel("OS");
		GridBagConstraints gbc_lblAutoref_Os = new GridBagConstraints();
		gbc_lblAutoref_Os.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutoref_Os.anchor = GridBagConstraints.EAST;
		gbc_lblAutoref_Os.gridx = 1;
		gbc_lblAutoref_Os.gridy = 2;
		visionPanel.add(lblAutoref_Os, gbc_lblAutoref_Os);
		
		textField_Os_Sphere_Autoref = new JTextField();
		GridBagConstraints gbc_textField_Os_Sphere_Autoref = new GridBagConstraints();
		gbc_textField_Os_Sphere_Autoref.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Os_Sphere_Autoref.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Os_Sphere_Autoref.gridx = 2;
		gbc_textField_Os_Sphere_Autoref.gridy = 2;
		visionPanel.add(textField_Os_Sphere_Autoref, gbc_textField_Os_Sphere_Autoref);
		textField_Os_Sphere_Autoref.setColumns(5);
		
		textField_Os_Cylinder_Autoref = new JTextField();
		GridBagConstraints gbc_textField_Os_Cylinder_Autoref = new GridBagConstraints();
		gbc_textField_Os_Cylinder_Autoref.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Os_Cylinder_Autoref.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Os_Cylinder_Autoref.gridx = 3;
		gbc_textField_Os_Cylinder_Autoref.gridy = 2;
		visionPanel.add(textField_Os_Cylinder_Autoref, gbc_textField_Os_Cylinder_Autoref);
		textField_Os_Cylinder_Autoref.setColumns(5);
		
		textField_Os_Axis_Autoref = new JTextField();
		GridBagConstraints gbc_textField_Os_Axis_Autoref = new GridBagConstraints();
		gbc_textField_Os_Axis_Autoref.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Os_Axis_Autoref.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Os_Axis_Autoref.gridx = 4;
		gbc_textField_Os_Axis_Autoref.gridy = 2;
		visionPanel.add(textField_Os_Axis_Autoref, gbc_textField_Os_Axis_Autoref);
		textField_Os_Axis_Autoref.setColumns(5);
		
		JLabel lblARc = new JLabel("ARc");
		GridBagConstraints gbc_lblARc = new GridBagConstraints();
		gbc_lblARc.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblARc.insets = new Insets(0, 0, 5, 5);
		gbc_lblARc.gridx = 0;
		gbc_lblARc.gridy = 4;
		visionPanel.add(lblARc, gbc_lblARc);
		
		JLabel lblSphere_Arc = new JLabel("Sphere");
		GridBagConstraints gbc_lblSphere_Arc = new GridBagConstraints();
		gbc_lblSphere_Arc.insets = new Insets(0, 0, 5, 5);
		gbc_lblSphere_Arc.gridx = 2;
		gbc_lblSphere_Arc.gridy = 4;
		visionPanel.add(lblSphere_Arc, gbc_lblSphere_Arc);
		
		JLabel lblCylinder_Arc = new JLabel("Cylinder");
		GridBagConstraints gbc_lblCylinder_Arc = new GridBagConstraints();
		gbc_lblCylinder_Arc.insets = new Insets(0, 0, 5, 5);
		gbc_lblCylinder_Arc.gridx = 3;
		gbc_lblCylinder_Arc.gridy = 4;
		visionPanel.add(lblCylinder_Arc, gbc_lblCylinder_Arc);
		
		JLabel lblArc_Axis = new JLabel("Axis");
		GridBagConstraints gbc_lblArc_Axis = new GridBagConstraints();
		gbc_lblArc_Axis.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblArc_Axis.insets = new Insets(0, 0, 5, 5);
		gbc_lblArc_Axis.gridx = 4;
		gbc_lblArc_Axis.gridy = 4;
		visionPanel.add(lblArc_Axis, gbc_lblArc_Axis);
		
		JLabel lblOd_Arc = new JLabel("OD");
		GridBagConstraints gbc_lblOd_Arc = new GridBagConstraints();
		gbc_lblOd_Arc.insets = new Insets(0, 0, 5, 5);
		gbc_lblOd_Arc.anchor = GridBagConstraints.EAST;
		gbc_lblOd_Arc.gridx = 1;
		gbc_lblOd_Arc.gridy = 5;
		visionPanel.add(lblOd_Arc, gbc_lblOd_Arc);
		
		textField_Od_Sphere_Arc = new JTextField();
		GridBagConstraints gbc_textField_Od_Sphere_Arc = new GridBagConstraints();
		gbc_textField_Od_Sphere_Arc.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Od_Sphere_Arc.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Od_Sphere_Arc.gridx = 2;
		gbc_textField_Od_Sphere_Arc.gridy = 5;
		visionPanel.add(textField_Od_Sphere_Arc, gbc_textField_Od_Sphere_Arc);
		textField_Od_Sphere_Arc.setColumns(5);
		
		textField_Od_Cylin_Arc = new JTextField();
		GridBagConstraints gbc_textField_Od_Cylin_Arc = new GridBagConstraints();
		gbc_textField_Od_Cylin_Arc.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Od_Cylin_Arc.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Od_Cylin_Arc.gridx = 3;
		gbc_textField_Od_Cylin_Arc.gridy = 5;
		visionPanel.add(textField_Od_Cylin_Arc, gbc_textField_Od_Cylin_Arc);
		textField_Od_Cylin_Arc.setColumns(5);
		
		textField_Od_Axis_Od = new JTextField();
		textField_Od_Axis_Od.setColumns(5);
		GridBagConstraints gbc_textField_Od_Axis_Od = new GridBagConstraints();
		gbc_textField_Od_Axis_Od.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Od_Axis_Od.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Od_Axis_Od.gridx = 4;
		gbc_textField_Od_Axis_Od.gridy = 5;
		visionPanel.add(textField_Od_Axis_Od, gbc_textField_Od_Axis_Od);
		
		JLabel lblOs_Arc = new JLabel("OS");
		GridBagConstraints gbc_lblOs_Arc = new GridBagConstraints();
		gbc_lblOs_Arc.insets = new Insets(0, 0, 5, 5);
		gbc_lblOs_Arc.anchor = GridBagConstraints.EAST;
		gbc_lblOs_Arc.gridx = 1;
		gbc_lblOs_Arc.gridy = 6;
		visionPanel.add(lblOs_Arc, gbc_lblOs_Arc);
		
		textField_Os_Sphere_Arc = new JTextField();
		GridBagConstraints gbc_textField_Os_Sphere_Arc = new GridBagConstraints();
		gbc_textField_Os_Sphere_Arc.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Os_Sphere_Arc.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Os_Sphere_Arc.gridx = 2;
		gbc_textField_Os_Sphere_Arc.gridy = 6;
		visionPanel.add(textField_Os_Sphere_Arc, gbc_textField_Os_Sphere_Arc);
		textField_Os_Sphere_Arc.setColumns(5);
		
		textField_Os_Cylin_Arc = new JTextField();
		GridBagConstraints gbc_textField_Os_Cylin_Arc = new GridBagConstraints();
		gbc_textField_Os_Cylin_Arc.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Os_Cylin_Arc.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Os_Cylin_Arc.gridx = 3;
		gbc_textField_Os_Cylin_Arc.gridy = 6;
		visionPanel.add(textField_Os_Cylin_Arc, gbc_textField_Os_Cylin_Arc);
		textField_Os_Cylin_Arc.setColumns(5);
		
		textField_Os_Axis_Arc = new JTextField();
		textField_Os_Axis_Arc.setColumns(5);
		GridBagConstraints gbc_textField_Os_Axis_Arc = new GridBagConstraints();
		gbc_textField_Os_Axis_Arc.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Os_Axis_Arc.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Os_Axis_Arc.gridx = 4;
		gbc_textField_Os_Axis_Arc.gridy = 6;
		visionPanel.add(textField_Os_Axis_Arc, gbc_textField_Os_Axis_Arc);
		
		JLabel lblToBeContinued = new JLabel("To Be Continued");
		GridBagConstraints gbc_lblToBeContinued = new GridBagConstraints();
		gbc_lblToBeContinued.insets = new Insets(0, 0, 0, 5);
		gbc_lblToBeContinued.gridx = 0;
		gbc_lblToBeContinued.gridy = 8;
		visionPanel.add(lblToBeContinued, gbc_lblToBeContinued);
		
		JLabel label = new JLabel("...");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 8;
		visionPanel.add(label, gbc_label);
		
		JPanel panel_VisionSketch = new JPanel();
		GridBagConstraints gbc_panel_VisionSketch = new GridBagConstraints();
		gbc_panel_VisionSketch.anchor = GridBagConstraints.WEST;
		gbc_panel_VisionSketch.insets = new Insets(0, 0, 5, 0);
		gbc_panel_VisionSketch.fill = GridBagConstraints.VERTICAL;
		gbc_panel_VisionSketch.gridx = 0;
		gbc_panel_VisionSketch.gridy = 6;
		add(panel_VisionSketch, gbc_panel_VisionSketch);
		
		JPanel panel_Sketch1_Placehold = new JPanel();
		panel_VisionSketch.add(panel_Sketch1_Placehold);
		
		JLabel lblSketch_1 = new JLabel("Sketch #1 goes here");
		panel_Sketch1_Placehold.add(lblSketch_1);
		
		JLabel lblFundusExam = new JLabel("Fundus Exam");
		GridBagConstraints gbc_lblFundusExam = new GridBagConstraints();
		gbc_lblFundusExam.anchor = GridBagConstraints.WEST;
		gbc_lblFundusExam.insets = new Insets(0, 0, 5, 0);
		gbc_lblFundusExam.gridx = 0;
		gbc_lblFundusExam.gridy = 7;
		add(lblFundusExam, gbc_lblFundusExam);
		
		JPanel panel_FundusExam = new JPanel();
		GridBagConstraints gbc_panel_FundusExam = new GridBagConstraints();
		gbc_panel_FundusExam.anchor = GridBagConstraints.WEST;
		gbc_panel_FundusExam.insets = new Insets(0, 0, 5, 0);
		gbc_panel_FundusExam.gridx = 0;
		gbc_panel_FundusExam.gridy = 8;
		add(panel_FundusExam, gbc_panel_FundusExam);
		GridBagLayout gbl_panel_FundusExam = new GridBagLayout();
		gbl_panel_FundusExam.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_FundusExam.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_FundusExam.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_FundusExam.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_FundusExam.setLayout(gbl_panel_FundusExam);
		
		JLabel lblFE1 = new JLabel("FE 1");
		GridBagConstraints gbc_lblFE1 = new GridBagConstraints();
		gbc_lblFE1.insets = new Insets(0, 0, 5, 5);
		gbc_lblFE1.gridx = 0;
		gbc_lblFE1.gridy = 0;
		panel_FundusExam.add(lblFE1, gbc_lblFE1);
		
		JLabel lblFE1C1 = new JLabel("FE 1 Col 1");
		GridBagConstraints gbc_lblFE1C1 = new GridBagConstraints();
		gbc_lblFE1C1.insets = new Insets(0, 0, 5, 5);
		gbc_lblFE1C1.gridx = 2;
		gbc_lblFE1C1.gridy = 0;
		panel_FundusExam.add(lblFE1C1, gbc_lblFE1C1);
		
		JLabel lblFE1C2 = new JLabel("FE 1 Col 2");
		GridBagConstraints gbc_lblFE1C2 = new GridBagConstraints();
		gbc_lblFE1C2.insets = new Insets(0, 0, 5, 0);
		gbc_lblFE1C2.gridx = 4;
		gbc_lblFE1C2.gridy = 0;
		panel_FundusExam.add(lblFE1C2, gbc_lblFE1C2);
		
		JLabel lblFE1_r1 = new JLabel("FE 1 Row 1");
		GridBagConstraints gbc_lblFE1_r1 = new GridBagConstraints();
		gbc_lblFE1_r1.insets = new Insets(0, 0, 5, 5);
		gbc_lblFE1_r1.gridx = 0;
		gbc_lblFE1_r1.gridy = 2;
		panel_FundusExam.add(lblFE1_r1, gbc_lblFE1_r1);
		
		textField_FE1_1_1 = new JTextField();
		GridBagConstraints gbc_textField_FE1_1_1 = new GridBagConstraints();
		gbc_textField_FE1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_FE1_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_FE1_1_1.gridx = 2;
		gbc_textField_FE1_1_1.gridy = 2;
		panel_FundusExam.add(textField_FE1_1_1, gbc_textField_FE1_1_1);
		textField_FE1_1_1.setColumns(5);
		
		textField_FE1_1_2 = new JTextField();
		GridBagConstraints gbc_textField_FE1_1_2 = new GridBagConstraints();
		gbc_textField_FE1_1_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_FE1_1_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_FE1_1_2.gridx = 4;
		gbc_textField_FE1_1_2.gridy = 2;
		panel_FundusExam.add(textField_FE1_1_2, gbc_textField_FE1_1_2);
		textField_FE1_1_2.setColumns(5);
		
		JLabel lblFE1_r2 = new JLabel("FE 1 Row 2");
		GridBagConstraints gbc_lblFE1_r2 = new GridBagConstraints();
		gbc_lblFE1_r2.insets = new Insets(0, 0, 0, 5);
		gbc_lblFE1_r2.gridx = 0;
		gbc_lblFE1_r2.gridy = 4;
		panel_FundusExam.add(lblFE1_r2, gbc_lblFE1_r2);
		
		textField_FE1_2_1 = new JTextField();
		GridBagConstraints gbc_textField_FE1_2_1 = new GridBagConstraints();
		gbc_textField_FE1_2_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_FE1_2_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_FE1_2_1.gridx = 2;
		gbc_textField_FE1_2_1.gridy = 4;
		panel_FundusExam.add(textField_FE1_2_1, gbc_textField_FE1_2_1);
		textField_FE1_2_1.setColumns(5);
		
		textField_FE1_2_2 = new JTextField();
		GridBagConstraints gbc_textField_FE1_2_2 = new GridBagConstraints();
		gbc_textField_FE1_2_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_FE1_2_2.gridx = 4;
		gbc_textField_FE1_2_2.gridy = 4;
		panel_FundusExam.add(textField_FE1_2_2, gbc_textField_FE1_2_2);
		textField_FE1_2_2.setColumns(5);
		
		JPanel panelFundusSketch = new JPanel();
		GridBagConstraints gbc_panelFundusSketch = new GridBagConstraints();
		gbc_panelFundusSketch.anchor = GridBagConstraints.WEST;
		gbc_panelFundusSketch.insets = new Insets(0, 0, 5, 0);
		gbc_panelFundusSketch.fill = GridBagConstraints.VERTICAL;
		gbc_panelFundusSketch.gridx = 0;
		gbc_panelFundusSketch.gridy = 10;
		add(panelFundusSketch, gbc_panelFundusSketch);
		
		JPanel panel_Sketch2_Placehold = new JPanel();
		panelFundusSketch.add(panel_Sketch2_Placehold);
		
		JLabel lblSketch2 = new JLabel("Sketch #2 goes here");
		panel_Sketch2_Placehold.add(lblSketch2);
		
		JLabel lblAp = new JLabel("Assement/Plan:");
		lblAp.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblAp = new GridBagConstraints();
		gbc_lblAp.anchor = GridBagConstraints.WEST;
		gbc_lblAp.insets = new Insets(0, 0, 5, 0);
		gbc_lblAp.gridx = 0;
		gbc_lblAp.gridy = 11;
		add(lblAp, gbc_lblAp);
		
		JScrollPane scrollPane_AP = new JScrollPane();
		GridBagConstraints gbc_scrollPane_AP = new GridBagConstraints();
		gbc_scrollPane_AP.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_AP.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_AP.gridx = 0;
		gbc_scrollPane_AP.gridy = 12;
		add(scrollPane_AP, gbc_scrollPane_AP);
		
		JTextArea txtrTextarea = new JTextArea();
		txtrTextarea.setColumns(80);
		txtrTextarea.setRows(4);
		txtrTextarea.setWrapStyleWord(true);
		txtrTextarea.setLineWrap(true);
		scrollPane_AP.setViewportView(txtrTextarea);
		txtrTextarea.setText("textArea");
	}
}
