package views;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import database.GatewayException;
import models.Comment;
import models.MasterModel;
import models.Patient;
import models.Tabs;
import models.Vital;
import models.Vital;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;

public class VitalSCRUBView extends JPanel implements viewinterface {
	public enum BloodPressure_unit {mm_Hg,Pa}
	public enum BloodGlucose_unit{mmol_L,mg_dL}
	public enum Weight_unit{lbs,kg}	
	//public enum Height_unit{ft_in,in,cm}
	//public static final String[] BloodPressure_unit = {"mm/Hg","Pa"};
//	public static final String[] BloodGlucose_unit = {"mmol/L","mg/dL"};
//	public static final String[] Weight_unit = {"lbs","kg"};
//	public static final String[] Height_unit = {"ft/in","in","cm"};

	private JButton btnAppendNote;
	private JButton btnConfirm;
	private JButton btnCancel;

	private JTextField textField_BGLevel;
	private JTextField textField_O2Sat;
	private JTextField textField_Hb;
	private JTextField textField_BPS;
	private JTextField textField_BPD;
	private JTextField textField_Ft;
	private JTextField textField_In;
	private JTextField textField_Cm;
	private JTextField textField_Weight;

	private JLabel lblFt;
	private JLabel lblIn;
	private JLabel lblCm;
	private JLabel lblBP;
	private JLabel lblWeightUnit;

	private JTextArea textArea_Notes;
	
	private JComboBox cbBP_unit;
	private JComboBox cbBG_unit;
	private JComboBox cbHeight_unit;
	private JComboBox cbWeight_unit;
	/*
	 * private JScrollPane scrollPane_Comments; private JTextArea
	 * textArea_Comment; private JButton btnAppendComment;
	 */
	private JButton btnAppendCancel;

/*	private JRadioButton rdbtnKg;
	private JRadioButton rdbtnLbs;
	private JRadioButton rdbtnCm;
	private JRadioButton rdbtnIn;
	private JRadioButton rdbtnFtin;
	private JRadioButton rdbtnMgdl;
	private JRadioButton rdbtnMmoll;
	private JRadioButton rdbtnPa;
	private JRadioButton rdbtnMmhg;*/
	/*private ButtonGroup buttonGroupBP = new ButtonGroup();
	private ButtonGroup buttonGroupHeight = new ButtonGroup();
	private ButtonGroup buttonGroupWeight = new ButtonGroup();
	private ButtonGroup buttonGroupBloodGlucose = new ButtonGroup();*/

	private JTabbedPane tabbedPane;
	private JPanel vitalsTabView;

	public String heightUnit;
	private JTable vitalsTable;
	private JPanel panel_VitalsForm;// we don't need it anymore
	private JCheckBox chckbxFasting;
	private JLabel lblBGLevelUnit;
	private JScrollPane scrollPane;
	/**
	 * Create the panel.
	 */
	/*
	 * public VitalNewView(final JTabbedPane tabbedPane, Patient patient, final
	 * JPanel vitalsPanel, final HomeModel homeModel, JTable vitalsTable,
	 * List<Vital> vitalsList, VitalsList vl, Vital vitals, Boolean
	 * detailedView) {
	 */
	private int index;

	public VitalSCRUBView(int index) {
	
		this.index = index;
		// uncomment to view in windowbuilder
		createView();
	}

	private void populateVital() {
		if(index!=-1){
			Vital vitals = getMasterModel().getVitalsL().getMyList().get(index);
			textField_BPS.setText("" + vitals.getBps());
			textField_BPD.setText("" + vitals.getBpd());
			if (vitals.getBpUnit().equals(Vital.MMHG)) {
				rdbtnMmhg.setSelected(true);
			} else {
				rdbtnPa.setSelected(true);
			}
			if (vitals.isFasting()) {
				chckbxFasting.setSelected(true);
			}
			textField_BGLevel.setText(String.valueOf(vitals.getBg()));
			if (vitals.getBgUnit().equals(Vital.mmolL)) {
				rdbtnMmoll.setSelected(true);
			} else {
				rdbtnMgdl.setSelected(true);
			}
			textField_O2Sat.setText(String.valueOf(vitals.getO2sat()));
			textField_Hb.setText(String.valueOf(vitals.getHb()));
			if (vitals.getHUnit().equals(Vital.FTIN)) {
				textField_Ft.setText(String.valueOf(vitals.getHFeet()));
				textField_In.setText(String.valueOf(vitals.getHInches()));
				rdbtnFtin.setSelected(true);
			} else if (vitals.getHUnit().equals(Vital.IN)) {
				textField_In.setText(String.valueOf(vitals.getHInches()));
				rdbtnIn.setSelected(true);
			} else {
				textField_Cm.setText(String.valueOf(vitals.getHCm()));
				rdbtnCm.setSelected(true);
			}
			textField_Weight.setText(String.valueOf(vitals.getWeight()));
			if (vitals.getWUnit().equals(Vital.LBS)) {
				rdbtnLbs.setSelected(true);
			} else {
				rdbtnKg.setSelected(true);
			}
			textArea_Notes.setText(vitals.getNotes());
		}
	}
	//disable component
	private void prepareExistingVitalView() {
		chckbxFasting.setEnabled(false);
		for (Component c : panel_VitalsForm.getComponents()) {
			if (c instanceof JPanel) {
				for (Component c2 : ((JPanel) c).getComponents()) {
					if (c2 instanceof JTextField) {
						((JTextField) c2).setEditable(false);
					} else if (c2 instanceof JPanel) {
						for (Component c3 : ((JPanel) c2).getComponents()) {
							if (c3 instanceof JRadioButton) {
								((JRadioButton) c3).setEnabled(false);
							}
						}
					}
				}
			}
		}
		textArea_Notes.setEditable(false);
		btnConfirm.setVisible(false);
	}

	/*
	 * private class AppendListener implements ActionListener {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * btnAppendNote.setVisible(false); panel_CommentForm.setVisible(true); }
	 * 
	 * }
	 */

	private class ConfirmVitalListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			save();
			VitalsTabMasterView parent = (VitalsTabMasterView)VitalSCRUBView.this.getParent();
			parent.ShowVitalListView();
		}
	}
	private class CancelVitalListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			VitalsTabMasterView parent = (VitalsTabMasterView)VitalSCRUBView.this.getParent();
			parent.ShowVitalListView();
			
		}
	}
	private class UpdateVitalListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO
		}

	}

	// TODO use as many final static strings from Vitals as possible

	private void save() {
		StringBuilder sb = new StringBuilder(128);
		/**
		 * Create new Vitals object with correct parameters Insert the vitals to
		 * the DB through the Gateway
		 */
		float bps,bpd,bg,o2,hb,w;
		int ft,in,cm;
		try{
			bps = Float.parseFloat(textField_BPS.getText());
		}catch (Exception ex){
			bps=0;
		}
		try{
			bpd= Float.parseFloat(textField_BPD.getText());
		}catch (Exception ex){
			bpd=0;
		}
		try{
			bg= Float.parseFloat(textField_BGLevel.getText());
		}catch (Exception ex){
			bg=0;
		}
		try{
			o2= Float.parseFloat(textField_O2Sat.getText());
		}catch (Exception ex){
			o2=0;
		}
		try{
			hb= Float.parseFloat(textField_Hb.getText());
		}catch (Exception ex){
			hb=0;
		}
		try{
			w= Float.parseFloat(textField_Weight.getText());
		}catch (Exception ex){
			w=0;
		}
		try{
			ft=Integer.parseInt(textField_Ft.getText());
		}catch (Exception ex){
			ft=0;
		}
		try{
			in=Integer.parseInt(textField_In.getText());
		}catch (Exception ex){
			in=0;
		}
		try{
			cm=Integer.parseInt(textField_Cm.getText());
		}catch (Exception ex){
			cm=0;
		}
		// getSelectedButtonText(buttonGroupHeight) to get unit for DB
		Vital vitals = new Vital(0, this.getMasterModel().getCurrPatient().getId(),
				bps, bpd,cbBP_unit.getSelectedItem().toString(),chckbxFasting.isSelected(),
				bg, cbBG_unit.getSelectedItem().toString(),o2,hb,
				ft, in,cm,"ft:in:cm",w,cbWeight_unit.getSelectedItem().toString(),
				textArea_Notes.getText());
		try {
			vitals.setId(this.getMasterModel().getVitalsL().insert(vitals));
		} catch (GatewayException e) {
			System.err.println("from VitalNewView, can not insert to DB.");
		}

	}

	private void createView() {
		setLayout(new BorderLayout());
		JPanel panel_ConfirmCancel = new JPanel();
		add(panel_ConfirmCancel, BorderLayout.SOUTH);

		btnConfirm = new JButton("Save");
		panel_ConfirmCancel.add(btnConfirm);

		btnCancel = new JButton("Cancel");
		panel_ConfirmCancel.add(btnCancel);

		// Main panels where all the fun stuff is

		panel_VitalsForm = new JPanel();
		panel_VitalsForm.setBorder(new TitledBorder(null, "Vitals", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//panel_CenterMain.add(panel_VitalsForm, BorderLayout.CENTER);
		this.add(panel_VitalsForm, BorderLayout.CENTER);
		panel_VitalsForm.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow][grow][grow][grow][grow][grow]"));

		JPanel panel_BloodPressure = new JPanel();
		panel_BloodPressure.setBorder(new TitledBorder(null, "Blood Pressure", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_VitalsForm.add(panel_BloodPressure, "cell 0 0,alignx center,aligny top");
		panel_BloodPressure.setLayout(new MigLayout("", "[][grow][][][][grow]", "[grow]"));

		// BP form

		JLabel lblBpSys = new JLabel("BP Sys/Dia: ");
		panel_BloodPressure.add(lblBpSys, "cell 0 0,alignx right");

		textField_BPS = new JTextField("0");
		panel_BloodPressure.add(textField_BPS, "cell 1 0,alignx left");
		textField_BPS.setColumns(5);
		textField_BPS.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try{
					Float.parseFloat(textField_BPS.getText().trim());
				}catch(Exception ex){
					textField_BPS.setText("0");
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JLabel label_Slash = new JLabel("/");
		panel_BloodPressure.add(label_Slash, "cell 2 0,alignx trailing");

		textField_BPD = new JTextField("0");
		panel_BloodPressure.add(textField_BPD, "cell 3 0,alignx left");
		textField_BPD.setColumns(5);
		textField_BPD.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try{
					Float.parseFloat(textField_BPD.getText().trim());
				}catch(Exception ex){
					textField_BPD.setText("0");
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		//TODO change to combobox
		cbBP_unit = new JComboBox(BloodPressure_unit.values());
		cbBP_unit.setSelectedIndex(0);
		panel_BloodPressure.add(cbBP_unit, "cell 4 0,alignx left");

		// Blood glucose

		JPanel panel_BloodGlucose = new JPanel();
		panel_BloodGlucose.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Blood Glucose", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_VitalsForm.add(panel_BloodGlucose, "cell 0 1,alignx center,aligny top");
		panel_BloodGlucose.setLayout(new MigLayout("", "[][][grow][][grow]", "[grow]"));

		chckbxFasting = new JCheckBox("Fasting");
		panel_BloodGlucose.add(chckbxFasting, "cell 0 0");

		JLabel lblBgLevel = new JLabel("BG Level");
		panel_BloodGlucose.add(lblBgLevel, "cell 1 0,alignx right");

		textField_BGLevel = new JTextField("0");
		panel_BloodGlucose.add(textField_BGLevel, "cell 2 0,alignx left");
		textField_BGLevel.setColumns(5);
		textField_BGLevel.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try{
					Float.parseFloat(textField_BGLevel.getText().trim());
				}catch(Exception ex){
					textField_BGLevel.setText("0");
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		// TODO chang to combobox
		cbBG_unit = new JComboBox(BloodGlucose_unit.values());
		cbBG_unit.setSelectedIndex(0);
		panel_BloodGlucose.add(cbBG_unit, "cell 3 0,alignx left");
		
		// O2 saturation

		JPanel panel_O2Saturation = new JPanel();
		panel_O2Saturation.setBorder(new TitledBorder(null, "O2 Saturation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_VitalsForm.add(panel_O2Saturation, "cell 0 2,alignx center,aligny top");
		panel_O2Saturation.setLayout(new MigLayout("", "[][][][grow]", "[]"));

		JLabel lblOSaturation = new JLabel("O2 Saturation");
		panel_O2Saturation.add(lblOSaturation, "cell 0 0,alignx trailing");

		textField_O2Sat = new JTextField("0");
		panel_O2Saturation.add(textField_O2Sat, "cell 1 0,alignx left");
		textField_O2Sat.setColumns(5);
		textField_O2Sat.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try{
					Float.parseFloat(textField_O2Sat.getText().trim());
				}catch(Exception ex){
					textField_O2Sat.setText("0");
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});

		JLabel label = new JLabel("%");
		panel_O2Saturation.add(label, "cell 2 0,alignx left");

		// Hemoglobin

		JPanel panel_Hemoglobin = new JPanel();
		panel_Hemoglobin.setBorder(new TitledBorder(null, "Hemogobin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_VitalsForm.add(panel_Hemoglobin, "cell 0 3,alignx center,aligny top");
		panel_Hemoglobin.setLayout(new MigLayout("", "[][grow]", "[]"));

		JLabel lblHb = new JLabel("Hb");
		panel_Hemoglobin.add(lblHb, "cell 0 0,alignx trailing");

		textField_Hb = new JTextField("0");
		panel_Hemoglobin.add(textField_Hb, "flowx,cell 1 0,alignx left,aligny center");
		textField_Hb.setColumns(5);
		textField_Hb.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try{
					Float.parseFloat(textField_Hb.getText().trim());
				}catch(Exception ex){
					textField_Hb.setText("0");
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JLabel lblGmdl = new JLabel("gm/dL");
		panel_Hemoglobin.add(lblGmdl, "cell 1 0");

		// Height form

		JPanel panel_Height = new JPanel();
		panel_Height.setBorder(new TitledBorder(null, "Height", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_VitalsForm.add(panel_Height, "cell 0 4,alignx center,aligny top");
		panel_Height.setLayout(new MigLayout("", "[][grow][grow]", "[grow][][]"));

		JLabel lblHeight = new JLabel("Height");
		panel_Height.add(lblHeight, "cell 0 0,alignx right");

		textField_Ft = new JTextField("0");
		panel_Height.add(textField_Ft, "flowx,cell 1 0,alignx right");
		textField_Ft.setColumns(5);
		textField_Ft.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				int ft=0,in=0,cm=0;
				try{
					ft = Integer.parseInt(textField_Ft.getText().trim());
				}catch (Exception exft){
					ft=0;
				}
				try{
					in = Integer.parseInt(textField_In.getText().trim());
				}catch(Exception exin){
					in=0;
				}
				
				cm = Math.round((float)2.54*(ft*12+in));
				textField_Ft.setText(""+ft);
				textField_In.setText(""+in);
				textField_Cm.setText(""+cm);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		//TODO change to combobox
		lblFt = new JLabel("ft");
		panel_Height.add(lblFt, "cell 2 0");

		textField_In = new JTextField("0");
		panel_Height.add(textField_In, "flowx,cell 3 0");
		textField_In.setColumns(5);
		textField_In.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				int ft=0,in=0,cm=0;
				try{
					in = Integer.parseInt(textField_In.getText().trim());
				}catch (Exception exft){
					in=0;
				}
				try{
					ft = Integer.parseInt(textField_Ft.getText().trim());
				}catch(Exception exin){
					ft=0;
				}
				cm = Math.round((float)2.54*(ft*12+in));
				ft+=in/12;
				in=in%12;
				textField_Ft.setText(""+ft);
				textField_In.setText(""+in);
				textField_Cm.setText(""+cm);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		lblIn = new JLabel(Vital.IN);
		panel_Height.add(lblIn, "cell 4 0, alignx left");

		textField_Cm = new JTextField("0");
		//textField_Cm.setEnabled(false);
		panel_Height.add(textField_Cm, "flowx,cell 1 2,alignx left");
		textField_Cm.setColumns(5);
		textField_Cm.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				int ft=0,in=0,cm=0;
				try{
					cm = Integer.parseInt(textField_Cm.getText().trim());
					in =(int) Math.round((float)cm*0.393701);
					ft = in/12;
					in = in%12;	
					System.out.println(cm);
				}catch (Exception exft){
					cm=0;in=0;ft=0;
				}		
				textField_Ft.setText(""+ft);
				textField_In.setText(""+in);
				textField_Cm.setText(""+cm);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		lblCm = new JLabel(Vital.CM);
		panel_Height.add(lblCm, "cell 1 2");

		// Weight form

		JPanel panel_Weight = new JPanel();
		panel_Weight.setBorder(new TitledBorder(null, "Weight", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_VitalsForm.add(panel_Weight, "cell 0 5,alignx center,aligny top");
		panel_Weight.setLayout(new MigLayout("", "[][grow][grow]", "[grow]"));

		JLabel lblWeight = new JLabel("Weight");
		panel_Weight.add(lblWeight, "cell 0 0,alignx right");

		textField_Weight = new JTextField("0");
		panel_Weight.add(textField_Weight, "flowx,cell 1 0,alignx left");
		textField_Weight.setColumns(5);
		textField_Weight.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try{
					Float.parseFloat(textField_Weight.getText().trim());
				}catch(Exception ex){
					textField_Weight.setText("0");
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//TODO change to combobox
		cbWeight_unit = new JComboBox(Weight_unit.values());
		cbWeight_unit.setSelectedIndex(0);
		panel_Weight.add(cbWeight_unit, "cell 1 0");
		
		// Note form

		JPanel panel_Notes = new JPanel();
		panel_Notes.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Addtional Notes",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_VitalsForm.add(panel_Notes, "cell 0 6,grow");
		panel_Notes.setLayout(new MigLayout("", "[grow]", "[grow]"));

		textArea_Notes = new JTextArea();
		textArea_Notes.setLineWrap(true);
		textArea_Notes.setWrapStyleWord(true);
		panel_Notes.add(textArea_Notes, "cell 0 0,grow");

		btnConfirm.addActionListener(new ConfirmVitalListener());
		btnCancel.addActionListener(new CancelVitalListener());

	}

	@Override
	public void HideallView() {
		// TODO Auto-generated method stub

	}

	@Override
	public MasterModel getMasterModel() {
		return this.getHomeView().getMasterModel();
	}

	@Override
	public void ShowView() {
		// TODO Auto-generated method stub
		this.reload();
		this.setVisible(true);
	}

	@Override
	public void reload() {
		this.createView();
		populateVital();
	}

	@Override
	public HomeView getHomeView() {
		return ((VitalsTabMasterView) this.getParent()).getHomeView();
	}

}
