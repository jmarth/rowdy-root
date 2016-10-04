package visitPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import models.FundusExam;
import models.MasterModel;
import net.miginfocom.swing.MigLayout;
import views.FrameNewSketch;
import views.HomeView;
import views.VisitDetailView;
import views.viewinterface;

@SuppressWarnings("serial")
public class PanelFundus extends JPanel implements viewinterface {

	private int index; 
	
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
		
	private JPanel panel_1 = new JPanel();

	/**
	 * Create the panel.
	 */
	public PanelFundus(int index) {
		
		this.index = index;
		
		setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Fundus Exam", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, new Font("Tahoma", Font.BOLD, 20), new Color(0, 0, 0)));
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
		comboBox_CD_OD.setModel(new DefaultComboBoxModel(new String[] {"0.0", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1.0", " "}));
		
		chckbxAbnormal_CD_OS = new JCheckBox("Abnormal?");
		panel_Fundus_Misc.add(chckbxAbnormal_CD_OS, "flowx,cell 3 2,alignx center");
		
		comboBox_CD_OS = new JComboBox();
		panel_Fundus_Misc.add(comboBox_CD_OS, "cell 3 2,alignx center");
		comboBox_CD_OS.setModel(new DefaultComboBoxModel(new String[] {"0.0", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1.0", " "}));
		
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
		panel_FundusImage.setBorder(new TitledBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)), "Fundus Diagram", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_FundusImage, "cell 0 2,grow");
		panel_FundusImage.setLayout(new BoxLayout(panel_FundusImage, BoxLayout.Y_AXIS));
		
		btnFundusSketch = new JButton("Sketch");
		panel_FundusImage.add(btnFundusSketch);
		btnFundusSketch.addActionListener(new FundusSketchListener());
		lblFundusSketch = new JLabel("");
		panel_FundusImage.add(lblFundusSketch);
	}
	
	private class FundusSketchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			FrameNewSketch firstSketch = new FrameNewSketch(lblFundusSketch, "FundusTempSketch");
			firstSketch.setContentPane(firstSketch.getContentPane());
			firstSketch.setSize(new Dimension(600,600));
			firstSketch.setResizable(false);
			
			panel_1 = (JPanel) firstSketch.getContentPane();
			panel_1.setVisible(true);
			firstSketch.setVisible(true);
		}
		
	}
	
	public void setSketch() {
		Image image_Fundus = getMasterModel().getCurrentPatientVisitList().get(index).getSketches().getImageFundus();
		ImageIcon iconFundus = new ImageIcon(image_Fundus);
		lblFundusSketch.setIcon(iconFundus);
	}
	
	public void setFields() {

		FundusExam f = getMasterModel().getCurrentPatientVisitList().get(index).getMyFE();
		
		String temp;
		
		chckbxDialated.setSelected(f.getDialated() == 1?true:false);
		textField_Dial_Notes.setText(f.getDialNotes());
		
		chckbxAbnormal_CD_OD.setSelected(f.getCDODAb() ==1?true:false);
		
		//TODO for all temp String -> need null value for combo box
		temp = f.getCDOD();
		comboBox_CD_OD.setSelectedItem(temp);
		
		temp = f.getCDODNotes();
		textField_CD_OD.setText(temp);
		
		chckbxAbnormal_CD_OS.setSelected(f.getCDOSAb()==1?true:false);
		
		
		temp = f.getCDOS();
		comboBox_CD_OS.setSelectedItem(temp);
		
		temp = f.getCDOSNotes();
		textField_CD_OS.setText(temp);
		
		checkBox_Retina_OD.setSelected(f.getRetinaODAb()==1?true:false);
		
		temp = f.getRetinaODNotes();
		textField_Retina_OD.setText(temp);
		
		checkBox_Retina_OS.setSelected(f.getRetinaOSAb()==1?true:false);
		
		temp = f.getRetinaOSNotes();
		textField_Retina_OS.setText(temp);
		
		
		checkBox_Macula_OD.setSelected(f.getMaculaODAb()==1?true:false);
		
		temp = f.getMaculaODNotes();
		textField_Macula_Notes_OD.setText(temp);
		
		checkBox_Macula_OS.setSelected(f.getMaculaOSAb()==1?true:false);
		
		temp = f.getMaculaOSNotes();
		textField_Macula_Notes_OS.setText(temp);
		
		setSketch();
	}
	
	@Override
	public void HideallView() {
		//TODO
	}
	@Override
	public MasterModel getMasterModel() {
		return ((HomeView)this.getHomeView()).getMasterModel();
	}
	@Override
	public void ShowView() {
		//TODO
	}
	@Override
	public void reload() {
		this.setFields();
	}
	@Override
	public HomeView getHomeView() {
		return ((VisitDetailView)this.getParent()).getHomeView();
	}

}
