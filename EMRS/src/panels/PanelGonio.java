package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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

import models.Gonio;
import models.HomeModel;
import models.Patient;
import net.miginfocom.swing.MigLayout;
import views.Paint;

import javax.swing.AbstractButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class PanelGonio extends JPanel {
	
	// THESE ARE NOT IN ORDER WITH THE DB *****************
	
	private JRadioButton rdbtnYHFHA;
	private JRadioButton rdbtnNHFHA;
	private JRadioButton rdbtn_FHD_Both;
	private JRadioButton rdbtn_FHD_Right;
	private JRadioButton rdbtn_FHD_Left;

	private JCheckBox chckbxNormalGonioOD;
	private JCheckBox chckbxNormalGonioOS;
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
	private final NoneSelectedButtonGroup bgHxFHA = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgSide = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgODNonABCD = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgODNonRSQ = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgODCompABCD = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgODCompRSQ = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgODPig = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgODAntPig = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSNonABCD = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSNonRSQ = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSCompABCD = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSCompRSQ = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSPig = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSAntPig = new NoneSelectedButtonGroup();
	
	// THESE ARE NOT IN ORDER WITH THE DB *****************
	
	private JRadioButton rdbtnRODN;
	private JRadioButton rdbtnSODN;
	private JRadioButton rdbtnQODN;
	private JRadioButton rdbtnROSN;
	private JRadioButton rdbtnSOSN;
	private JRadioButton rdbtnQOSN;
	private JRadioButton rdbtnRODC;
	private JRadioButton rdbtnSODC;
	private JRadioButton rdbtnROSC;
	private JRadioButton rdbtnQODC;
	private JRadioButton rdbtnSOSC;
	private JRadioButton rdbtnQOSC;
	private JComboBox comboBox_GonioODN;
	private JComboBox comboBox_GonioODC;
	private JComboBox comboBox_GonioOSN;
	private JComboBox comboBox_GonioOSC;
	
	private HomeModel hm;
	private Patient p;
	
	private JPanel panel_1 = new JPanel();
	// THESE ARE NOT IN ORDER WITH THE DB *****************
	
	/*
	public void clearSelections(Container container) {
		for (Component c : container.getComponents()) {
	        if (c instanceof JTextField) {
	        	//((JTextField)c).setText("");
	        }
	        else if (c instanceof JTextArea) {
	        	//((JTextArea)c).setText("");
		    }
	        else if (c instanceof JRadioButton) {
	        	((JRadioButton)c).setSelected(false);
	        }
	        else if (c instanceof JCheckBox) {
	        	((JCheckBox)c).setSelected(false);
	        }
	        else if (c instanceof JComboBox) {
	        	//((JComboBox)c).setSelectedIndex(0);
	        }
	        else if (c instanceof Container) {
	        	clearSelections((Container)c);
	        }
	    }
	}
	*/

	/**
	 * Create the panel.
	 */
	public PanelGonio(HomeModel hm, Patient p) {

		this.hm = hm;
		this.p = p;
		
		setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Gonio", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, new Font("Tahoma", Font.BOLD, 20), new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[grow]", "[][][][33px,grow]"));
		
		JPanel panel_HxFHA = new JPanel();
		panel_HxFHA.setBorder(new TitledBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)), "Hx of Frontal Headaches", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_HxFHA, "cell 0 0");
		panel_HxFHA.setLayout(new MigLayout("", "[31px][33px][22px]", "[32px]"));
		
		rdbtnYHFHA = new JRadioButton("Y");
		bgHxFHA.add(rdbtnYHFHA);
		panel_HxFHA.add(rdbtnYHFHA, "cell 0 0,alignx left,aligny center");
		
		rdbtnNHFHA = new JRadioButton("N");
		bgHxFHA.add(rdbtnNHFHA);
		panel_HxFHA.add(rdbtnNHFHA, "cell 1 0,alignx left,aligny center");
		
		JPanel panel_HxFHDSide = new JPanel();
		panel_HxFHDSide.setBorder(new TitledBorder(null, "Side", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_HxFHA.add(panel_HxFHDSide, "cell 2 0");
		
		rdbtn_FHD_Right = new JRadioButton("Right");
		bgSide.add(rdbtn_FHD_Right);
		panel_HxFHDSide.add(rdbtn_FHD_Right);
		
		rdbtn_FHD_Left = new JRadioButton("Left");
		bgSide.add(rdbtn_FHD_Left);
		panel_HxFHDSide.add(rdbtn_FHD_Left);
		
		rdbtn_FHD_Both = new JRadioButton("Both");
		bgSide.add(rdbtn_FHD_Both);
		panel_HxFHDSide.add(rdbtn_FHD_Both);
		
		JPanel panel_GonioOD = new JPanel();
		panel_GonioOD.setBorder(new TitledBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)), "OD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_GonioOD, "cell 0 1,growx");
		panel_GonioOD.setLayout(new MigLayout("", "[grow][][]", "[][]"));
		
		chckbxNormalGonioOD = new JCheckBox("Normal");
		chckbxNormalGonioOD.addActionListener(new NormalODListener());
		panel_GonioOD.add(chckbxNormalGonioOD, "cell 0 0,alignx left,aligny top");
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Noncompressed", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_GonioOD.add(panel_9, "cell 1 0,grow");
		panel_9.setLayout(new MigLayout("", "[][][][][]", "[][][][]"));
		
		rdbtnAODN = new JRadioButton("A");
		bgODNonABCD.add(rdbtnAODN);
		panel_9.add(rdbtnAODN, "cell 0 0");
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		panel_9.add(separator_2, "cell 1 0 1 4,grow");
		
		comboBox_GonioODN = new JComboBox();
		comboBox_GonioODN.setModel(new DefaultComboBoxModel(new String[] {"0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", " "}));
		panel_9.add(comboBox_GonioODN, "flowx,cell 2 0");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel_9.add(separator, "cell 3 0 1 4,grow");
		
		rdbtnRODN = new JRadioButton("R");
		bgODNonRSQ.add(rdbtnRODN);
		panel_9.add(rdbtnRODN, "cell 4 0");
		
		rdbtnBODN = new JRadioButton("B");
		bgODNonABCD.add(rdbtnBODN);
		panel_9.add(rdbtnBODN, "cell 0 1");
		
		rdbtnSODN = new JRadioButton("S");
		bgODNonRSQ.add(rdbtnSODN);
		panel_9.add(rdbtnSODN, "cell 4 1");
		
		rdbtnCODN = new JRadioButton("C");
		bgODNonABCD.add(rdbtnCODN);
		panel_9.add(rdbtnCODN, "cell 0 2");
		
		rdbtnQODN = new JRadioButton("Q");
		bgODNonRSQ.add(rdbtnQODN);
		panel_9.add(rdbtnQODN, "cell 4 2");
		
		rdbtnDODN = new JRadioButton("D");
		bgODNonABCD.add(rdbtnDODN);
		panel_9.add(rdbtnDODN, "cell 0 3");
		
		JLabel lblDegrees = new JLabel("degrees");
		panel_9.add(lblDegrees, "cell 2 0");
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Compressed", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_GonioOD.add(panel_10, "cell 2 0,grow");
		panel_10.setLayout(new MigLayout("", "[][][][][]", "[][][][]"));
		
		rdbtnAODC = new JRadioButton("A");
		bgODCompABCD.add(rdbtnAODC);
		panel_10.add(rdbtnAODC, "cell 0 0");
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		panel_10.add(separator_3, "cell 1 0 1 4,grow");
		
		comboBox_GonioODC = new JComboBox();
		comboBox_GonioODC.setModel(new DefaultComboBoxModel(new String[] {"0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", " "}));
		panel_10.add(comboBox_GonioODC, "flowx,cell 2 0");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		panel_10.add(separator_1, "cell 3 0 1 4,grow");
		
		rdbtnRODC = new JRadioButton("R");
		bgODCompRSQ.add(rdbtnRODC);
		panel_10.add(rdbtnRODC, "cell 4 0");
		
		rdbtnBODC = new JRadioButton("B");
		bgODCompABCD.add(rdbtnBODC);
		panel_10.add(rdbtnBODC, "cell 0 1");
		
		rdbtnSODC = new JRadioButton("S");
		bgODCompRSQ.add(rdbtnSODC);
		panel_10.add(rdbtnSODC, "cell 4 1");
		
		rdbtnCODC = new JRadioButton("C");
		bgODCompABCD.add(rdbtnCODC);
		panel_10.add(rdbtnCODC, "cell 0 2");
		
		rdbtnQODC = new JRadioButton("Q");
		bgODCompRSQ.add(rdbtnQODC);
		panel_10.add(rdbtnQODC, "cell 4 2");
		
		rdbtnDODC = new JRadioButton("D");
		bgODCompABCD.add(rdbtnDODC);
		panel_10.add(rdbtnDODC, "cell 0 3");
		
		JLabel label = new JLabel("degrees");
		panel_10.add(label, "cell 2 0");
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(null, "Pigment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_GonioOD.add(panel_13, "cell 0 1 3 1,growx");
		panel_13.setLayout(new MigLayout("", "[][][][][grow]", "[]"));
		
		rdbtn_PigmentOD_1 = new JRadioButton("+1");
		bgODPig.add(rdbtn_PigmentOD_1);
		panel_13.add(rdbtn_PigmentOD_1, "cell 0 0");
		
		rdbtn_PigmentOD_2 = new JRadioButton("+2");
		bgODPig.add(rdbtn_PigmentOD_2);
		panel_13.add(rdbtn_PigmentOD_2, "cell 1 0");
		
		rdbtn_PigmentOD_3 = new JRadioButton("+3");
		bgODPig.add(rdbtn_PigmentOD_3);
		panel_13.add(rdbtn_PigmentOD_3, "cell 2 0");
		
		rdbtn_PigmentOD_4 = new JRadioButton("+4");
		bgODPig.add(rdbtn_PigmentOD_4);
		panel_13.add(rdbtn_PigmentOD_4, "cell 3 0");
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new TitledBorder(null, "Anterior Pigment Line", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.add(panel_14, "cell 4 0,growx");
		
		rdbtn_Y_AntPigLineOD = new JRadioButton("Y");
		bgODAntPig.add(rdbtn_Y_AntPigLineOD);
		panel_14.add(rdbtn_Y_AntPigLineOD);
		
		rdbtn_N_AntPigLineOD = new JRadioButton("N");
		bgODAntPig.add(rdbtn_N_AntPigLineOD);
		panel_14.add(rdbtn_N_AntPigLineOD);
		
		JPanel panel_GonioOS = new JPanel();
		panel_GonioOS.setBorder(new TitledBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)), "OS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_GonioOS, "cell 0 2,growx");
		panel_GonioOS.setLayout(new MigLayout("", "[grow][][]", "[][]"));
		
		chckbxNormalGonioOS = new JCheckBox("Normal");
		chckbxNormalGonioOS.addActionListener(new NormalOSListener());
		panel_GonioOS.add(chckbxNormalGonioOS, "cell 0 0,alignx left,aligny top");
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "Noncompressed", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_GonioOS.add(panel_11, "cell 1 0");
		panel_11.setLayout(new MigLayout("", "[][][][][]", "[][][][]"));
		
		rdbtnAOSN = new JRadioButton("A");
		bgOSNonABCD.add(rdbtnAOSN);
		panel_11.add(rdbtnAOSN, "cell 0 0");
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		panel_11.add(separator_4, "cell 1 0 1 4,grow");
		
		comboBox_GonioOSN = new JComboBox();
		comboBox_GonioOSN.setModel(new DefaultComboBoxModel(new String[] {"0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50"}));
		panel_11.add(comboBox_GonioOSN, "flowx,cell 2 0");
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		panel_11.add(separator_5, "cell 3 0 1 4,grow");
		
		rdbtnROSN = new JRadioButton("R");
		bgOSNonRSQ.add(rdbtnROSN);
		panel_11.add(rdbtnROSN, "cell 4 0");
		
		rdbtnBOSN = new JRadioButton("B");
		bgOSNonABCD.add(rdbtnBOSN);
		panel_11.add(rdbtnBOSN, "cell 0 1");
		
		rdbtnSOSN = new JRadioButton("S");
		bgOSNonRSQ.add(rdbtnSOSN);
		panel_11.add(rdbtnSOSN, "cell 4 1");
		
		rdbtnCOSN = new JRadioButton("C");
		bgOSNonABCD.add(rdbtnCOSN);
		panel_11.add(rdbtnCOSN, "cell 0 2");
		
		rdbtnQOSN = new JRadioButton("Q");
		bgOSNonRSQ.add(rdbtnQOSN);
		panel_11.add(rdbtnQOSN, "cell 4 2");
		
		rdbtnDOSN = new JRadioButton("D");
		bgOSNonABCD.add(rdbtnDOSN);
		panel_11.add(rdbtnDOSN, "cell 0 3");
		
		JLabel label_2 = new JLabel("degrees");
		panel_11.add(label_2, "cell 2 0");
		
		JPanel panel_GonioOS_Compressed = new JPanel();
		panel_GonioOS_Compressed.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Compressed", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_GonioOS.add(panel_GonioOS_Compressed, "cell 2 0,grow");
		panel_GonioOS_Compressed.setLayout(new MigLayout("", "[][][][][]", "[][][][]"));
		
		rdbtnAOSC = new JRadioButton("A");
		bgOSCompABCD.add(rdbtnAOSC);
		panel_GonioOS_Compressed.add(rdbtnAOSC, "cell 0 0");
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		panel_GonioOS_Compressed.add(separator_6, "cell 1 0 1 4,grow");
		
		comboBox_GonioOSC = new JComboBox();
		comboBox_GonioOSC.setModel(new DefaultComboBoxModel(new String[] {"0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50"}));
		panel_GonioOS_Compressed.add(comboBox_GonioOSC, "flowx,cell 2 0");
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setOrientation(SwingConstants.VERTICAL);
		panel_GonioOS_Compressed.add(separator_8, "cell 3 0 1 4,grow");
		
		rdbtnROSC = new JRadioButton("R");
		bgOSCompRSQ.add(rdbtnROSC);
		panel_GonioOS_Compressed.add(rdbtnROSC, "cell 4 0");
		
		rdbtnBOSC = new JRadioButton("B");
		bgOSCompABCD.add(rdbtnBOSC);
		panel_GonioOS_Compressed.add(rdbtnBOSC, "cell 0 1");
		
		rdbtnSOSC = new JRadioButton("S");
		bgOSCompRSQ.add(rdbtnSOSC);
		panel_GonioOS_Compressed.add(rdbtnSOSC, "cell 4 1");
		
		rdbtnCOSC = new JRadioButton("C");
		bgOSCompABCD.add(rdbtnCOSC);
		panel_GonioOS_Compressed.add(rdbtnCOSC, "cell 0 2");
		
		rdbtnQOSC = new JRadioButton("Q");
		bgOSCompRSQ.add(rdbtnQOSC);
		panel_GonioOS_Compressed.add(rdbtnQOSC, "cell 4 2");
		
		rdbtnDOSC = new JRadioButton("D");
		bgOSCompABCD.add(rdbtnDOSC);
		panel_GonioOS_Compressed.add(rdbtnDOSC, "cell 0 3");
		
		JLabel label_3 = new JLabel("degrees");
		panel_GonioOS_Compressed.add(label_3, "cell 2 0");
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(null, "Pigment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_GonioOS.add(panel_15, "cell 0 1 3 1,grow");
		panel_15.setLayout(new MigLayout("", "[][][][][grow]", "[]"));
		
		rdbtn_PigmentOS_1 = new JRadioButton("+1");
		bgOSPig.add(rdbtn_PigmentOS_1);
		panel_15.add(rdbtn_PigmentOS_1, "cell 0 0");
		
		rdbtn_PigmentOS_2 = new JRadioButton("+2");
		bgOSPig.add(rdbtn_PigmentOS_2);
		panel_15.add(rdbtn_PigmentOS_2, "cell 1 0");
		
		rdbtn_PigmentOS_3 = new JRadioButton("+3");
		bgOSPig.add(rdbtn_PigmentOS_3);
		panel_15.add(rdbtn_PigmentOS_3, "cell 2 0");
		
		rdbtn_PigmentOS_4 = new JRadioButton("+4");
		bgOSPig.add(rdbtn_PigmentOS_4);
		panel_15.add(rdbtn_PigmentOS_4, "cell 3 0");
		
		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new TitledBorder(null, "Anterior Pigment Line", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_15.add(panel_16, "cell 4 0,grow");
		
		rdbtn_Y_AntPigLineOS = new JRadioButton("Y");
		bgOSAntPig.add(rdbtn_Y_AntPigLineOS);
		panel_16.add(rdbtn_Y_AntPigLineOS);
		
		rdbtn_N_AntPigLineOS = new JRadioButton("N");
		bgOSAntPig.add(rdbtn_N_AntPigLineOS);
		panel_16.add(rdbtn_N_AntPigLineOS);
		
		JPanel panel_GonioSketch = new JPanel();
		panel_GonioSketch.setBorder(new TitledBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)), "Gonioscopy Diagram", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_GonioSketch, "cell 0 3,growx,aligny top");
		panel_GonioSketch.setLayout(new BoxLayout(panel_GonioSketch, BoxLayout.Y_AXIS));
		
		btnGonioSketch = new JButton("Sketch");
		btnGonioSketch.addActionListener(new GonioSketchListener());
		panel_GonioSketch.add(btnGonioSketch);
		
		lblGonioSketch = new JLabel("");
		panel_GonioSketch.add(lblGonioSketch);
	}

	private class GonioSketchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Paint firstSketch = new Paint(hm, p, lblGonioSketch, "GonioTempSketch");
			firstSketch.setContentPane(firstSketch.getContentPane());
			firstSketch.setSize(new Dimension(600,600));
			firstSketch.setResizable(false);
			
			panel_1 = (JPanel) firstSketch.getContentPane();
			panel_1.setVisible(true);
			firstSketch.setVisible(true);
		}
			
	}
	
	private class NormalODListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (chckbxNormalGonioOD.isSelected()) {
				rdbtnDODN.setSelected(true);
				comboBox_GonioODN.setSelectedIndex(8);
				rdbtnDODC.setSelected(true);
				comboBox_GonioODC.setSelectedIndex(8);
				rdbtn_PigmentOD_1.setSelected(true);
				rdbtn_N_AntPigLineOD.setSelected(true);
				rdbtnRODN.setSelected(true);
				rdbtnRODC.setSelected(true);
			}
		}

	}
	private class NormalOSListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (chckbxNormalGonioOS.isSelected()) {
				rdbtnDOSN.setSelected(true);
				comboBox_GonioOSN.setSelectedIndex(8);
				rdbtnDOSC.setSelected(true);
				comboBox_GonioOSC.setSelectedIndex(8);
				rdbtn_PigmentOS_1.setSelected(true);
				rdbtn_N_AntPigLineOS.setSelected(true);
				rdbtnROSN.setSelected(true);
				rdbtnROSC.setSelected(true);
			}
		}
		
	}
	
	public Gonio createNewGonio() {
		
		String ODDegreeN = (String)comboBox_GonioODN.getSelectedItem();
		String ODDegreeC = (String)comboBox_GonioODC.getSelectedItem();
		String OSDegreeN = (String)comboBox_GonioOSN.getSelectedItem();
		String OSDegreeC = (String)comboBox_GonioOSC.getSelectedItem();
		
		Gonio g = new Gonio(
				
				rdbtnYHFHA.isSelected() ? 1 : rdbtnNHFHA.isSelected() ? 0 : -1,
				getSelectedButtonText(bgSide) == null ? " " : getSelectedButtonText(bgSide),
				
				chckbxNormalGonioOD.isSelected() ? 1 : 0,
				getSelectedButtonText(bgODNonABCD) == null ? " " : getSelectedButtonText(bgODNonABCD),
				getSelectedButtonText(bgODCompABCD) == null ? " " : getSelectedButtonText(bgODCompABCD),
				ODDegreeN,
				ODDegreeC,
				getSelectedButtonText(bgODNonRSQ) == null ? " " : getSelectedButtonText(bgODNonRSQ),
				getSelectedButtonText(bgODCompRSQ) == null ? " " : getSelectedButtonText(bgODCompRSQ),
				getSelectedButtonText(bgODPig) == null ? " " : getSelectedButtonText(bgODPig),
				rdbtn_Y_AntPigLineOD.isSelected() ? 1 : rdbtn_N_AntPigLineOD.isSelected() ? 0 : -1,

				
				chckbxNormalGonioOS.isSelected() ? 1 : 0,
				getSelectedButtonText(bgOSNonABCD) == null ? " " : getSelectedButtonText(bgOSNonABCD),
				getSelectedButtonText(bgOSCompABCD) == null ? " " : getSelectedButtonText(bgOSCompABCD),
				OSDegreeN,
				OSDegreeC,
				getSelectedButtonText(bgOSNonRSQ) == null ? " " : getSelectedButtonText(bgOSNonRSQ),
				getSelectedButtonText(bgOSCompRSQ) == null ? " " : getSelectedButtonText(bgOSCompRSQ),
				getSelectedButtonText(bgOSPig) == null ? " " : getSelectedButtonText(bgOSPig),
				rdbtn_Y_AntPigLineOS.isSelected() ? 1 : rdbtn_N_AntPigLineOS.isSelected() ? 0 : -1
				
				);
		
		return g;
	}
	
	public void setFields(ArrayList<Object> gonioCols) {
		
		int i = -1;
		String temp;
		
		
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("1")) {
			rdbtnYHFHA.setSelected(true);
		} else if (temp.equals("0")) {
			rdbtnNHFHA.setSelected(true);
		}
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("Both")) {
			rdbtn_FHD_Both.setSelected(true);
		} else if (temp.equals("Left")) {
			rdbtn_FHD_Right.setSelected(true);
		} else if (temp.equals("Right")) {
			rdbtn_FHD_Left.setSelected(true);
		}

		
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("1")) {
			chckbxNormalGonioOD.setSelected(true);
		}
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("A")) {
			rdbtnAODN.setSelected(true);
		} else if (temp.equals("B")) {
			rdbtnBODN.setSelected(true);
		} else if (temp.equals("C")) {
			rdbtnCODN.setSelected(true);
		} else if (temp.equals("D")) {
			rdbtnDODN.setSelected(true);
		}
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("A")) {
			rdbtnAODC.setSelected(true);
		} else if (temp.equals("B")) {
			rdbtnBODC.setSelected(true);
		} else if (temp.equals("C")) {
			rdbtnCODC.setSelected(true);
		} else if (temp.equals("D")) {
			rdbtnDODC.setSelected(true);
		}
		//TODO
		//temp = gonioCols.get(++i).toString();
		//if (temp !=null) {
		//	comboBox_GonioODN.setSelectedItem(temp);
		//}
		//temp = gonioCols.get(++i).toString();
		//if (temp !=null) {
		//	comboBox_GonioODC.setSelectedItem(temp);
		//}
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("R")) {
			rdbtnRODN.setSelected(true);
		} else if (temp.equals("S")) {
			rdbtnSODN.setSelected(true);
		} else if (temp.equals("Q")) {
			rdbtnQODN.setSelected(true);
		}
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("R")) {
			rdbtnRODC.setSelected(true);
		} else if (temp.equals("S")) {
			rdbtnSODC.setSelected(true);
		} else if (temp.equals("Q")) {
			rdbtnQODC.setSelected(true);
		}
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("1")) {
			rdbtn_PigmentOD_1.setSelected(true);
		} else if (temp.equals("2")) {
			rdbtn_PigmentOD_2.setSelected(true);
		} else if (temp.equals("3")) {
			rdbtn_PigmentOD_3.setSelected(true);
		} else if (temp.equals("4")) {
			rdbtn_PigmentOD_4.setSelected(true);
		}

		temp = gonioCols.get(++i).toString();
		if (temp.equals("1")) {
			rdbtn_Y_AntPigLineOD.setSelected(true);
		} else if (temp.equals("0")) {
			rdbtn_N_AntPigLineOD.setSelected(true);
		}
		
		
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("1")) {
			rdbtnYHFHA.setSelected(true);
		} else if (temp.equals("0")) {
			rdbtnNHFHA.setSelected(true);
		}
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("Both")) {
			rdbtn_FHD_Both.setSelected(true);
		} else if (temp.equals("Left")) {
			rdbtn_FHD_Right.setSelected(true);
		} else if (temp.equals("Right")) {
			rdbtn_FHD_Left.setSelected(true);
		}

		
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("1")) {
			chckbxNormalGonioOS.setSelected(true);
		}
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("A")) {
			rdbtnAOSN.setSelected(true);
		} else if (temp.equals("B")) {
			rdbtnBOSN.setSelected(true);
		} else if (temp.equals("C")) {
			rdbtnCOSN.setSelected(true);
		} else if (temp.equals("D")) {
			rdbtnDOSN.setSelected(true);
		}
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("A")) {
			rdbtnAOSC.setSelected(true);
		} else if (temp.equals("B")) {
			rdbtnBOSC.setSelected(true);
		} else if (temp.equals("C")) {
			rdbtnCOSC.setSelected(true);
		} else if (temp.equals("D")) {
			rdbtnDOSC.setSelected(true);
		}
		//TODO
//		temp = gonioCols.get(++i).toString();
//		if (temp !=null) {
//			comboBox_GonioOSN.setSelectedItem(temp);
//		}
//		temp = gonioCols.get(++i).toString();
//		if (temp !=null) {
//			comboBox_GonioOSC.setSelectedItem(temp);
//		}
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("R")) {
			rdbtnROSN.setSelected(true);
		} else if (temp.equals("S")) {
			rdbtnSOSN.setSelected(true);
		} else if (temp.equals("Q")) {
			rdbtnQOSN.setSelected(true);
		}
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("R")) {
			rdbtnROSC.setSelected(true);
		} else if (temp.equals("S")) {
			rdbtnSOSC.setSelected(true);
		} else if (temp.equals("Q")) {
			rdbtnQOSC.setSelected(true);
		}
		
		temp = gonioCols.get(++i).toString();
		if (temp.equals("1")) {
			rdbtn_PigmentOS_1.setSelected(true);
		} else if (temp.equals("2")) {
			rdbtn_PigmentOS_2.setSelected(true);
		} else if (temp.equals("3")) {
			rdbtn_PigmentOS_3.setSelected(true);
		} else if (temp.equals("4")) {
			rdbtn_PigmentOS_4.setSelected(true);
		}

		temp = gonioCols.get(++i).toString();
		if (temp.equals("1")) {
			rdbtn_Y_AntPigLineOS.setSelected(true);
		} else if (temp.equals("0")) {
			rdbtn_N_AntPigLineOS.setSelected(true);
		}
		
	}
	
	/**
	 * Gets the selected button's name from a button group.
	 * 
	 * @param buttonGroup
	 * @return The string of the name of the selected radio button of the
	 *         buttonGroup
	 */
	public String getSelectedButtonText(NoneSelectedButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}

	public JLabel getSketchLabel() {
		
		return lblGonioSketch;
	}
}
