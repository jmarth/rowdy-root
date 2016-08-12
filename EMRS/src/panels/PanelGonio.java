package panels;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class PanelGonio extends JPanel {

	private JRadioButton rdbtnAODN;
	private JRadioButton rdbtnBODN;
	private JRadioButton rdbtnCODN;
	private JRadioButton rdbtnDODN;
	private JRadioButton rdbtnAODC;
	private JRadioButton rdbtnBODC;
	private JRadioButton rdbtnCODC;
	private JRadioButton rdbtnDODC;
	private JRadioButton rdbtn_PigmentOD_1;
	private JRadioButton rdbtn_PigmentOD_2;
	private JRadioButton rdbtn_PigmentOD_3;
	private JRadioButton rdbtn_PigmentOD_4;
	private JRadioButton rdbtn_Y_AntPigLineOD;
	private JRadioButton rdbtn_N_AntPigLineOD;
	private JRadioButton rdbtnAOSN;
	private JRadioButton rdbtnBOSN;
	private JRadioButton rdbtnCOSN;
	private JRadioButton rdbtnDOSN;
	private JRadioButton rdbtnAOSC;
	private JRadioButton rdbtnBOSC;
	private JRadioButton rdbtnCOSC;
	private JRadioButton rdbtnDOSC;
	private JRadioButton rdbtn_PigmentOS_1;
	private JRadioButton rdbtn_PigmentOS_2;
	private JRadioButton rdbtn_PigmentOS_3;
	private JRadioButton rdbtn_PigmentOS_4;
	private JRadioButton rdbtn_Y_AntPigLineOS;
	private JRadioButton rdbtn_N_AntPigLineOS;
	private JButton btnGonioSketch;
	private JLabel lblGonioSketch;

	/**
	 * Create the panel.
	 */
	public PanelGonio() {

		setBorder(new TitledBorder(null, "Gonioscopy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[grow]", "[][][][33px,grow]"));
		
		JPanel panel_HxFHA = new JPanel();
		panel_HxFHA.setBorder(new TitledBorder(null, "Hx of Frontal Headaches", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_HxFHA, "cell 0 0");
		panel_HxFHA.setLayout(new MigLayout("", "[31px][33px][22px]", "[32px]"));
		
		JRadioButton rdbtnYHFHA = new JRadioButton("Y");
		panel_HxFHA.add(rdbtnYHFHA, "cell 0 0,alignx left,aligny center");
		
		JRadioButton rdbtnNHFHA = new JRadioButton("N");
		panel_HxFHA.add(rdbtnNHFHA, "cell 1 0,alignx left,aligny center");
		
		JPanel panel_HxFHDSide = new JPanel();
		panel_HxFHDSide.setBorder(new TitledBorder(null, "Side", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_HxFHA.add(panel_HxFHDSide, "cell 2 0");
		
		JRadioButton rdbtn_FHD_Right = new JRadioButton("Right");
		panel_HxFHDSide.add(rdbtn_FHD_Right);
		
		JRadioButton rdbtn_FHD_Left = new JRadioButton("Left");
		panel_HxFHDSide.add(rdbtn_FHD_Left);
		
		JRadioButton rdbtn_FHD_Both = new JRadioButton("Both");
		panel_HxFHDSide.add(rdbtn_FHD_Both);
		
		JPanel panel_GonioOD = new JPanel();
		panel_GonioOD.setBorder(new TitledBorder(null, "OD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_GonioOD, "cell 0 1,growx");
		panel_GonioOD.setLayout(new MigLayout("", "[grow][][]", "[][]"));
		
		JCheckBox chckbxNormalGonioOD = new JCheckBox("Normal");
		panel_GonioOD.add(chckbxNormalGonioOD, "cell 0 0,alignx left,aligny top");
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Noncompressed", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_GonioOD.add(panel_9, "cell 1 0,grow");
		panel_9.setLayout(new MigLayout("", "[][][][][]", "[][][][]"));
		
		rdbtnAODN = new JRadioButton("A");
		panel_9.add(rdbtnAODN, "cell 0 0");
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		panel_9.add(separator_2, "cell 1 0 1 4,grow");
		
		JComboBox comboBox_GonioODN = new JComboBox();
		comboBox_GonioODN.setModel(new DefaultComboBoxModel(new String[] {"0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50"}));
		panel_9.add(comboBox_GonioODN, "flowx,cell 2 0");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel_9.add(separator, "cell 3 0 1 4,grow");
		
		JRadioButton rdbtnR = new JRadioButton("R");
		panel_9.add(rdbtnR, "cell 4 0");
		
		rdbtnBODN = new JRadioButton("B");
		panel_9.add(rdbtnBODN, "cell 0 1");
		
		JRadioButton rdbtnS = new JRadioButton("S");
		panel_9.add(rdbtnS, "cell 4 1");
		
		rdbtnCODN = new JRadioButton("C");
		panel_9.add(rdbtnCODN, "cell 0 2");
		
		JRadioButton rdbtnQ = new JRadioButton("Q");
		panel_9.add(rdbtnQ, "cell 4 2");
		
		rdbtnDODN = new JRadioButton("D");
		panel_9.add(rdbtnDODN, "cell 0 3");
		
		JLabel lblDegrees = new JLabel("degrees");
		panel_9.add(lblDegrees, "cell 2 0");
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Compressed", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_GonioOD.add(panel_10, "cell 2 0,grow");
		panel_10.setLayout(new MigLayout("", "[][][][][]", "[][][][]"));
		
		rdbtnAODC = new JRadioButton("A");
		panel_10.add(rdbtnAODC, "cell 0 0");
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		panel_10.add(separator_3, "cell 1 0 1 4,grow");
		
		JComboBox comboBox_GonioODC = new JComboBox();
		comboBox_GonioODC.setModel(new DefaultComboBoxModel(new String[] {"0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50"}));
		panel_10.add(comboBox_GonioODC, "flowx,cell 2 0");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		panel_10.add(separator_1, "cell 3 0 1 4,grow");
		
		JRadioButton radioButton_4 = new JRadioButton("R");
		panel_10.add(radioButton_4, "cell 4 0");
		
		rdbtnBODC = new JRadioButton("B");
		panel_10.add(rdbtnBODC, "cell 0 1");
		
		JRadioButton radioButton_5 = new JRadioButton("S");
		panel_10.add(radioButton_5, "cell 4 1");
		
		rdbtnCODC = new JRadioButton("C");
		panel_10.add(rdbtnCODC, "cell 0 2");
		
		JRadioButton radioButton_12 = new JRadioButton("Q");
		panel_10.add(radioButton_12, "cell 4 2");
		
		rdbtnDODC = new JRadioButton("D");
		panel_10.add(rdbtnDODC, "cell 0 3");
		
		JLabel label = new JLabel("degrees");
		panel_10.add(label, "cell 2 0");
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(null, "Pigment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_GonioOD.add(panel_13, "cell 0 1 3 1,growx");
		panel_13.setLayout(new MigLayout("", "[][][][][grow]", "[]"));
		
		rdbtn_PigmentOD_1 = new JRadioButton("+1");
		panel_13.add(rdbtn_PigmentOD_1, "cell 0 0");
		
		rdbtn_PigmentOD_2 = new JRadioButton("+2");
		panel_13.add(rdbtn_PigmentOD_2, "cell 1 0");
		
		rdbtn_PigmentOD_3 = new JRadioButton("+3");
		panel_13.add(rdbtn_PigmentOD_3, "cell 2 0");
		
		rdbtn_PigmentOD_4 = new JRadioButton("+4");
		panel_13.add(rdbtn_PigmentOD_4, "cell 3 0");
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new TitledBorder(null, "Anterior Pigment Line", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.add(panel_14, "cell 4 0,growx");
		
		rdbtn_Y_AntPigLineOD = new JRadioButton("Y");
		panel_14.add(rdbtn_Y_AntPigLineOD);
		
		rdbtn_N_AntPigLineOD = new JRadioButton("N");
		panel_14.add(rdbtn_N_AntPigLineOD);
		
		JPanel panel_GonioOS = new JPanel();
		panel_GonioOS.setBorder(new TitledBorder(null, "OS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_GonioOS, "cell 0 2,growx");
		panel_GonioOS.setLayout(new MigLayout("", "[grow][][]", "[][]"));
		
		JCheckBox chckbxNormalGonioOS = new JCheckBox("Normal");
		panel_GonioOS.add(chckbxNormalGonioOS, "cell 0 0,alignx left,aligny top");
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "Noncompressed", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_GonioOS.add(panel_11, "cell 1 0");
		panel_11.setLayout(new MigLayout("", "[][][][][]", "[][][][]"));
		
		rdbtnAOSN = new JRadioButton("A");
		panel_11.add(rdbtnAOSN, "cell 0 0");
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		panel_11.add(separator_4, "cell 1 0 1 4,grow");
		
		JComboBox comboBox_GonioOSN = new JComboBox();
		comboBox_GonioOSN.setModel(new DefaultComboBoxModel(new String[] {"0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50"}));
		panel_11.add(comboBox_GonioOSN, "flowx,cell 2 0");
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		panel_11.add(separator_5, "cell 3 0 1 4,grow");
		
		JRadioButton radioButton_17 = new JRadioButton("R");
		panel_11.add(radioButton_17, "cell 4 0");
		
		rdbtnBOSN = new JRadioButton("B");
		panel_11.add(rdbtnBOSN, "cell 0 1");
		
		JRadioButton radioButton_18 = new JRadioButton("S");
		panel_11.add(radioButton_18, "cell 4 1");
		
		rdbtnCOSN = new JRadioButton("C");
		panel_11.add(rdbtnCOSN, "cell 0 2");
		
		JRadioButton radioButton_19 = new JRadioButton("Q");
		panel_11.add(radioButton_19, "cell 4 2");
		
		rdbtnDOSN = new JRadioButton("D");
		panel_11.add(rdbtnDOSN, "cell 0 3");
		
		JLabel label_2 = new JLabel("degrees");
		panel_11.add(label_2, "cell 2 0");
		
		JPanel panel_GonioOS_Compressed = new JPanel();
		panel_GonioOS_Compressed.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Compressed", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_GonioOS.add(panel_GonioOS_Compressed, "cell 2 0,grow");
		panel_GonioOS_Compressed.setLayout(new MigLayout("", "[][][][][]", "[][][][]"));
		
		rdbtnAOSC = new JRadioButton("A");
		panel_GonioOS_Compressed.add(rdbtnAOSC, "cell 0 0");
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		panel_GonioOS_Compressed.add(separator_6, "cell 1 0 1 4,grow");
		
		JComboBox comboBox_GonioOSC = new JComboBox();
		comboBox_GonioOSC.setModel(new DefaultComboBoxModel(new String[] {"0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50"}));
		panel_GonioOS_Compressed.add(comboBox_GonioOSC, "flowx,cell 2 0");
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setOrientation(SwingConstants.VERTICAL);
		panel_GonioOS_Compressed.add(separator_8, "cell 3 0 1 4,grow");
		
		JRadioButton radioButton_24 = new JRadioButton("R");
		panel_GonioOS_Compressed.add(radioButton_24, "cell 4 0");
		
		rdbtnBOSC = new JRadioButton("B");
		panel_GonioOS_Compressed.add(rdbtnBOSC, "cell 0 1");
		
		JRadioButton radioButton_25 = new JRadioButton("S");
		panel_GonioOS_Compressed.add(radioButton_25, "cell 4 1");
		
		rdbtnCOSC = new JRadioButton("C");
		panel_GonioOS_Compressed.add(rdbtnCOSC, "cell 0 2");
		
		JRadioButton radioButton_26 = new JRadioButton("Q");
		panel_GonioOS_Compressed.add(radioButton_26, "cell 4 2");
		
		rdbtnDOSC = new JRadioButton("D");
		panel_GonioOS_Compressed.add(rdbtnDOSC, "cell 0 3");
		
		JLabel label_3 = new JLabel("degrees");
		panel_GonioOS_Compressed.add(label_3, "cell 2 0");
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(null, "Pigment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_GonioOS.add(panel_15, "cell 0 1 3 1,grow");
		panel_15.setLayout(new MigLayout("", "[][][][][grow]", "[]"));
		
		rdbtn_PigmentOS_1 = new JRadioButton("+1");
		panel_15.add(rdbtn_PigmentOS_1, "cell 0 0");
		
		rdbtn_PigmentOS_2 = new JRadioButton("+2");
		panel_15.add(rdbtn_PigmentOS_2, "cell 1 0");
		
		rdbtn_PigmentOS_3 = new JRadioButton("+3");
		panel_15.add(rdbtn_PigmentOS_3, "cell 2 0");
		
		rdbtn_PigmentOS_4 = new JRadioButton("+4");
		panel_15.add(rdbtn_PigmentOS_4, "cell 3 0");
		
		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new TitledBorder(null, "Anterior Pigment Line", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_15.add(panel_16, "cell 4 0,grow");
		
		rdbtn_Y_AntPigLineOS = new JRadioButton("Y");
		panel_16.add(rdbtn_Y_AntPigLineOS);
		
		rdbtn_N_AntPigLineOS = new JRadioButton("N");
		panel_16.add(rdbtn_N_AntPigLineOS);
		
		JPanel panel_GonioSketch = new JPanel();
		panel_GonioSketch.setBorder(new TitledBorder(null, "Diagram", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_GonioSketch, "cell 0 3,alignx left,aligny top");
		panel_GonioSketch.setLayout(new BoxLayout(panel_GonioSketch, BoxLayout.Y_AXIS));
		
		btnGonioSketch = new JButton("Sketch");
		panel_GonioSketch.add(btnGonioSketch);
		
		lblGonioSketch = new JLabel("<gonio image>");
		panel_GonioSketch.add(lblGonioSketch);
	}

}
