package panels;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.EtchedBorder;

public class PanelFundus extends JPanel {

	private JCheckBox chckbxDialated;
	private JTextField textField_Dial_Notes;
	private JCheckBox chckbxAbnormal_CD_OD;
	private JComboBox comboBox_CD_OD;
	private JCheckBox chckbxAbnormal_CD_OS;
	private JComboBox comboBox_CD_OS;
	private JTextField textField_CD_OD;
	private JTextField textField_CD_OS;
	private JTextField textField_Retina_OD;
	private JTextField textField_Retina_OS;
	private JCheckBox checkBox_Macula_OD;
	private JCheckBox checkBox_Macula_OS;
	private JTextField textField_Macula_Notes_OD;
	private JCheckBox checkBox_Retina_OD;
	private JCheckBox checkBox_Retina_OS;
	private JTextField textField_Macula_Notes_OS;
	private JButton btnFundusSketch;
	private JLabel lblFundusSketch;

	/**
	 * Create the panel.
	 */
	public PanelFundus() {
		setBorder(new TitledBorder(null, "Fundus Exam", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		JPanel panel_Fundus_Dialated = new JPanel();
		add(panel_Fundus_Dialated, "cell 0 0,growx");
		panel_Fundus_Dialated.setLayout(new MigLayout("", "[][grow][grow]", "[][][]"));
		
		chckbxDialated = new JCheckBox("Dialated?");
		panel_Fundus_Dialated.add(chckbxDialated, "cell 0 0 3 1");
		
		JLabel lblDialationNotes = new JLabel("Dialation Notes");
		panel_Fundus_Dialated.add(lblDialationNotes, "cell 0 1,alignx trailing");
		
		textField_Dial_Notes = new JTextField();
		panel_Fundus_Dialated.add(textField_Dial_Notes, "cell 1 1 2 1,growx");
		textField_Dial_Notes.setColumns(10);
		
		JSeparator separator_Dial = new JSeparator();
		panel_Fundus_Dialated.add(separator_Dial, "cell 0 2 3 1,growx");
		
		
		
		// FUNDUS MISC		
		JPanel panel_Fundus_Misc = new JPanel();
		add(panel_Fundus_Misc, "cell 0 1,grow");
		panel_Fundus_Misc.setLayout(new MigLayout("", "[][grow][][grow]", "[][][][][][][][][][]"));
		
		JLabel lblOd_4 = new JLabel("OD");
		panel_Fundus_Misc.add(lblOd_4, "cell 1 0,alignx center");
		
		JSeparator separator_F1 = new JSeparator();
		separator_F1.setOrientation(SwingConstants.VERTICAL);
		panel_Fundus_Misc.add(separator_F1, "cell 2 0,alignx center,growy");
		
		JLabel lblOs_4 = new JLabel("OS");
		lblOs_4.setBackground(new Color(212, 208, 200));
		panel_Fundus_Misc.add(lblOs_4, "cell 3 0,alignx center");
		
		JSeparator separator_F2 = new JSeparator();
		panel_Fundus_Misc.add(separator_F2, "cell 0 1 4 1,growx");
		
		JLabel lblCD = new JLabel("C/D");
		panel_Fundus_Misc.add(lblCD, "cell 0 2");
		
		chckbxAbnormal_CD_OD = new JCheckBox("Abnormal?");
		panel_Fundus_Misc.add(chckbxAbnormal_CD_OD, "flowx,cell 1 2,alignx center");
		
		comboBox_CD_OD = new JComboBox();
		panel_Fundus_Misc.add(comboBox_CD_OD, "cell 1 2,alignx center");
		comboBox_CD_OD.setModel(new DefaultComboBoxModel(new String[] {"0.0", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1.0"}));
		
		chckbxAbnormal_CD_OS = new JCheckBox("Abnormal?");
		panel_Fundus_Misc.add(chckbxAbnormal_CD_OS, "flowx,cell 3 2,alignx center");
		
		comboBox_CD_OS = new JComboBox();
		panel_Fundus_Misc.add(comboBox_CD_OS, "cell 3 2,alignx center");
		comboBox_CD_OS.setModel(new DefaultComboBoxModel(new String[] {"0.0", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1.0"}));
		
		JLabel lblNotes_CD = new JLabel("Notes");
		panel_Fundus_Misc.add(lblNotes_CD, "cell 0 3,alignx trailing");
		
		textField_CD_OD = new JTextField();
		panel_Fundus_Misc.add(textField_CD_OD, "cell 1 3,growx");
		textField_CD_OD.setColumns(20);
		
		textField_CD_OS = new JTextField();
		textField_CD_OS.setColumns(20);
		panel_Fundus_Misc.add(textField_CD_OS, "cell 3 3,growx");
		
		JSeparator separator_F3 = new JSeparator();
		panel_Fundus_Misc.add(separator_F3, "cell 0 4 4 1,growx");
		
		JLabel lblRetina = new JLabel("Retina");
		panel_Fundus_Misc.add(lblRetina, "cell 0 5");
		
		JLabel lblRetinaNotes = new JLabel("Notes");
		panel_Fundus_Misc.add(lblRetinaNotes, "cell 0 6,alignx trailing");
		
		textField_Retina_OD = new JTextField();
		textField_Retina_OD.setColumns(20);
		panel_Fundus_Misc.add(textField_Retina_OD, "cell 1 6,growx");
		
		textField_Retina_OS = new JTextField();
		textField_Retina_OS.setColumns(20);
		panel_Fundus_Misc.add(textField_Retina_OS, "cell 3 6,growx");
		
		JSeparator separator_F4 = new JSeparator();
		panel_Fundus_Misc.add(separator_F4, "cell 0 7 4 1,growx");
		
		JLabel lblMacula = new JLabel("Macula");
		panel_Fundus_Misc.add(lblMacula, "cell 0 8");
		
		checkBox_Macula_OD = new JCheckBox("Abnormal?");
		panel_Fundus_Misc.add(checkBox_Macula_OD, "cell 1 8,alignx center");
		
		checkBox_Macula_OS = new JCheckBox("Abnormal?");
		panel_Fundus_Misc.add(checkBox_Macula_OS, "cell 3 8,alignx center");
		
		JLabel lblMaculaNotes = new JLabel("Notes");
		panel_Fundus_Misc.add(lblMaculaNotes, "cell 0 9,alignx trailing");
		
		textField_Macula_Notes_OD = new JTextField();
		textField_Macula_Notes_OD.setColumns(20);
		panel_Fundus_Misc.add(textField_Macula_Notes_OD, "flowx,cell 1 9,growx");
		
		checkBox_Retina_OD = new JCheckBox("Abnormal?");
		panel_Fundus_Misc.add(checkBox_Retina_OD, "cell 1 5,alignx center");
		
		checkBox_Retina_OS = new JCheckBox("Abnormal?");
		panel_Fundus_Misc.add(checkBox_Retina_OS, "cell 3 5,alignx center");
		
		textField_Macula_Notes_OS = new JTextField();
		textField_Macula_Notes_OS.setColumns(20);
		panel_Fundus_Misc.add(textField_Macula_Notes_OS, "cell 3 9,growx");
		
		
		
		// FUNDUS SKETCH
		JPanel panel_FundusImage = new JPanel();
		panel_FundusImage.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Fundus Diagram", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_FundusImage, "cell 0 2,grow");
		panel_FundusImage.setLayout(new BoxLayout(panel_FundusImage, BoxLayout.Y_AXIS));
		
		btnFundusSketch = new JButton("Sketch");
		panel_FundusImage.add(btnFundusSketch);
		
		lblFundusSketch = new JLabel("<fundus image>");
		panel_FundusImage.add(lblFundusSketch);
	}

}
