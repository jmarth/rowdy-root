package views;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;

import database.GatewayException;
import models.FundusExam;
import models.HomeModel;
import models.IOPMeasurement;
import models.Patient;
import models.SlitLampExam;
import models.Tabs;
import models.VisionExam;
import models.Visit;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VisitTabViewNewVisit extends JPanel {

	private JTextArea textArea_CC;
	
	
	private JTextField textField_DVODSC;
	private JTextField textField_DVOSSC;
	private JTextField textField_DVODCC;
	private JTextField textField_DVOSCC;
	
	private JTextField textField_ARSC_OD_Sphere;
	private JTextField textField_ARSC_OD_Cyl;
	private JTextField textField_ARSC_OD_Axis;
	private JTextField textField_ARSC_OS_Sphere;
	private JTextField textField_ARSC_OS_Cyl;
	private JTextField textField_ARSC_OS_Axis;
	
	private JTextField textField_ARCC_OD_Sphere;
	private JTextField textField_ARCC_OD_Cyl;
	private JTextField textField_ARCC_OD_Axis;
	private JTextField textField_ARCC_OS_Sphere;
	private JTextField textField_ARCC_OS_Cyl;
	private JTextField textField_ARCC_OS_Axis;
	
	private JTextField textField_Rx_OD_Sphere;
	private JTextField textField_Rx_OD_Cyl;
	private JTextField textField_Rx_OD_Axis;
	private JTextField textField_Rx_OD_Add;
	
	private JTextField textField_Rx_OS_Sphere;
	private JTextField textField_Rx_OS_Cyl;
	private JTextField textField_Rx_OS_Axis;
	private JTextField textField_Rx_OS_Add;
	
	private JTextField textField_GlassesRxNotes;
	

	private JCheckBox checkBox_SLE_Pupils_OD_Ab;
	private JTextField textField_SLE_Pupils_OD;
	private JCheckBox checkBox_SLE_Pupils_OS_Ab;
	private JTextField textField_SLE_Pupils_OS;

	private JCheckBox checkBox_SLE_AC_OD_Ab;
	private JTextField textField_SLE_AC_OD;
	private JCheckBox checkBox_SLE_AC_OS_Ab;
	private JTextField textField_SLE_AC_OS;
	
	private JCheckBox chckbx_SLE_Pseudophakia_OD;
	private JCheckBox chckbx_SLE_Pseudophakia_OS;
	
	private JCheckBox chckbx_SLE_PCO_OD;
	private JCheckBox chckbx_SLE_PCO_OS;

	private JComboBox comboBox_SLE_NS_OD;
	private JTextField textField_SLE_NS_OD;
	private JComboBox comboBox_SLE_NS_OS;
	private JTextField textField_SLE_NS_OS;
	
	private JComboBox comboBox_SLE_Coritcal_OD;
	private JTextField textField_SLE_Cortical_OD;
	private JComboBox comboBox_SLE_Coritcal_OS;
	private JTextField textField_SLE_Cortical_OS;
	
	private JComboBox comboBox_SLE_PSC_OD;
	private JTextField textField_SLE_PSC_OD;
	private JComboBox comboBox_SLE_PSC_OS;
	private JTextField textField_SLE_PSC_OS;
	
	
	private JTextField textField_IOP_Value_OD;
	private JTextField textField_IOP_Type_OD;
	private JTextField textField_IOP_Notes_OD;
	private JTextField textField_IOP_Value_OS;
	private JTextField textField_IOP_Type_OS;
	private JTextField textField_IOP_Notes_OS;

	
	private JCheckBox chckbxDialated;
	private JTextField textField_Dial_Notes;

	private JComboBox comboBox_CD_OD;
	private JCheckBox chckbxAbnormal_CD_OD;
	private JTextField textField_CD_OD;
	private JComboBox comboBox_CD_OS;
	private JCheckBox chckbxAbnormal_CD_OS;
	private JTextField textField_CD_OS;

	private JCheckBox checkBox_Macula_OD;
	private JTextField textField_Macula_Notes_OD;
	private JCheckBox checkBox_Macula_OS;
	private JTextField textField_Macula_Notes_OS;

	private JCheckBox checkBox_Retina_OD;
	private JTextField textField_Retina_OD;
	private JCheckBox checkBox_Retina_OS;
	private JTextField textField_Retina_OS;
	
	
	private JTextArea textArea_Assessment;
	private JTextArea textArea_Plan;

	
	private JButton btnGonioSketch;
	private JButton btnFundusSketch;
	private JButton btnSLESketch;
	
	private JPanel panel_Buttons;
	private JButton btnSave;
	private JButton btnCancel;
	
	private Patient patient;
	private JTabbedPane tabbedPane;
	private HomeModel homeModel;
	private JLabel label_SLE_Sketch;
	private JLabel lblGonioSketch;
	private JLabel lblFundusSketch;
	
	/**
	 * Create the panel.
	 */
	/**
	 * @wbp.parser.constructor
	 */
	public VisitTabViewNewVisit(Visit v, Patient patient, final JTabbedPane tabbedPane, HomeModel homeModel, boolean forJXTaskPane) {
		
		this.patient = patient;
		this.tabbedPane = tabbedPane;
		this.homeModel = homeModel;
		
		// set ALL the things!
		
		createView();
		
		panel_Buttons.setVisible(false);
		btnSave.setVisible(false);
		btnCancel.setVisible(false);
		
		btnSLESketch.setVisible(false);
		btnGonioSketch.setVisible(false);
		btnFundusSketch.setVisible(false);
		
		disableFields(this);
		
		if (forJXTaskPane) {
			// will be for the JXTaskPane
			
			//TODO
			try {
				ArrayList<Image> sketches = (ArrayList<Image>) homeModel.getStg().fetchSketchesForPatinet(v.getId());
				if(sketches.size() != 0) {
					Image img = sketches.get(0);
					ImageIcon icon = new ImageIcon(img);
					label_SLE_Sketch.setIcon(icon);
				}
			} catch (GatewayException e) {
				e.printStackTrace();
			}
			
		} else {
			
			// never used... can probably get rid of boolean and this
			// keeping boolean for now, maybe useful for sometime?
			
		}
		
		
		
	}
	
	/**
	 *  Used for an actual new visit only
	 */
	public VisitTabViewNewVisit(Patient patient, final JTabbedPane tabbedPane, HomeModel homeModel) {
		
		this.patient = patient;
		this.tabbedPane = tabbedPane;
		this.homeModel = homeModel;
		
		createView();
	}
	
	private class SaveListener extends MouseAdapter {
		
		public void mouseReleased(MouseEvent e) {
			//TODO: MAKE IT HAPPEN
			//TODO: ERROR CHECKS
			
			VisionExam ve = null;
			SlitLampExam sle = null;
			IOPMeasurement iopm = null;
			FundusExam fe = null;
			
			// Parse ALL the things!
			Visit visit = new Visit(
					patient.getId(),
					textArea_CC.getText(),
					textArea_Assessment.getText(),
					textArea_Plan.getText()
					);
			
			long vid = 0;
			
			try {
				
				vid = homeModel.getVtg().insertVisit(visit);
				
				if (label_SLE_Sketch.getIcon() != null){
					homeModel.getStg().insertSketch(new File("firstSketch.png"), vid);
				}
				
				homeModel.getVl().loadFromGateway();
				showVisitTabView();
				
			} catch (GatewayException e1) {
				e1.printStackTrace();
			}
			
			// this might be really bad with memory??
			// be sure these are nulled by visit?
			
			ve = new VisionExam(
					vid,
					textField_DVODSC.getText(), 
					textField_DVOSSC.getText(), 
					textField_DVODCC.getText(), 
					textField_DVOSCC.getText(),
					Float.parseFloat(textField_ARSC_OD_Sphere.getText()), 
					Float.parseFloat(textField_ARSC_OD_Cyl.getText()), 
					Float.parseFloat(textField_ARSC_OD_Axis.getText()), 
					Float.parseFloat(textField_ARSC_OS_Sphere.getText()), 
					Float.parseFloat(textField_ARSC_OS_Cyl.getText()), 
					Float.parseFloat(textField_ARSC_OS_Axis.getText()), 
					Float.parseFloat(textField_ARCC_OD_Sphere.getText()), 
					Float.parseFloat(textField_ARCC_OD_Cyl.getText()), 
					Float.parseFloat(textField_ARCC_OD_Axis.getText()), 
					Float.parseFloat(textField_ARCC_OS_Sphere.getText()), 
					Float.parseFloat(textField_ARCC_OS_Cyl.getText()), 
					Float.parseFloat(textField_ARCC_OS_Axis.getText()), 
					Float.parseFloat(textField_Rx_OD_Sphere.getText()), 
					Float.parseFloat(textField_Rx_OD_Cyl.getText()), 
					Float.parseFloat(textField_Rx_OD_Axis.getText()), 
					Float.parseFloat(textField_Rx_OD_Add.getText()), 
					Float.parseFloat(textField_Rx_OS_Sphere.getText()), 
					Float.parseFloat(textField_Rx_OS_Cyl.getText()), 
					Float.parseFloat(textField_Rx_OS_Axis.getText()), 
					Float.parseFloat(textField_Rx_OS_Add.getText()),
					textField_GlassesRxNotes.getText()
					);
			
			sle = new SlitLampExam(
					vid,
					checkBox_SLE_Pupils_OD_Ab.isSelected(),
					textField_SLE_Pupils_OD.getText(),
					checkBox_SLE_Pupils_OS_Ab.isSelected(),
					textField_SLE_Pupils_OS.getText(),
					checkBox_SLE_AC_OD_Ab.isSelected(),
					textField_SLE_AC_OD.getText(),
					checkBox_SLE_AC_OS_Ab.isSelected(),
					textField_SLE_AC_OS.getText(),
					chckbx_SLE_Pseudophakia_OD.isSelected(),
					chckbx_SLE_Pseudophakia_OS.isSelected(),
					chckbx_SLE_PCO_OD.isSelected(),
					chckbx_SLE_PCO_OS.isSelected(),
					(String)comboBox_SLE_NS_OD.getSelectedItem(),
					textField_SLE_NS_OD.getText(),
					(String)comboBox_SLE_NS_OS.getSelectedItem(),
					textField_SLE_NS_OS.getText(),
					(String)comboBox_SLE_Coritcal_OD.getSelectedItem(),
					textField_SLE_Cortical_OD.getText(),
					(String)comboBox_SLE_Coritcal_OS.getSelectedItem(),
					textField_SLE_Cortical_OS.getText(),
					(String)comboBox_SLE_PSC_OD.getSelectedItem(),
					textField_SLE_PSC_OD.getText(),
					(String)comboBox_SLE_PSC_OS.getSelectedItem(),
					textField_SLE_PSC_OS.getText()
					);
			
			iopm = new IOPMeasurement(
					vid,
					textField_IOP_Value_OD.getText(),
					textField_IOP_Type_OD.getText(),
					textField_IOP_Notes_OD.getText(),
					textField_IOP_Value_OS.getText(),
					textField_IOP_Type_OS.getText(),
					textField_IOP_Notes_OS.getText()
					);
			
			
			fe = new FundusExam(
					vid,
					chckbxDialated.isSelected(),
					textField_Dial_Notes.getText(),
					chckbxAbnormal_CD_OD.isSelected(),
					(Float)comboBox_CD_OD.getSelectedItem(),
					textField_CD_OD.getText(),
					chckbxAbnormal_CD_OS.isSelected(),
					(Float)comboBox_CD_OS.getSelectedItem(),
					textField_CD_OS.getText(),
					checkBox_Macula_OD.isSelected(),
					textField_Macula_Notes_OD.getText(),
					checkBox_Macula_OS.isSelected(),
					textField_Macula_Notes_OS.getText(),
					checkBox_Retina_OD.isSelected(),
					textField_Retina_OD.getText(),
					checkBox_Retina_OS.isSelected(),
					textField_Retina_OS.getText()
					);
			
			
			
			//ve.setId(); // set from DB
			ve.setVid(visit.getPid());
			
			
		}
	}
	
	public void save() {
		
	}
	
	void disableFields (Container container) {
	    for (Component c : container.getComponents()) {
	        if (c instanceof JTextField) {
	           ((JTextField)c).setEditable(false);
	        } 
	        else if (c instanceof JTextArea) {
		           ((JTextArea)c).setEditable(false);
		    }
	        else if (c instanceof Container) {
	        	disableFields((Container)c);
	        }
	    }
	}	
	
	/**
	 * sets Visit tab to VisitsTabView
	 */
	public void showVisitTabView() {
		int index = tabbedPane.indexOfTab(Tabs.ped);
		tabbedPane.setComponentAt(index, null);
		tabbedPane.setComponentAt(index, new VisitsTabView(patient, tabbedPane, homeModel));
	}	
	
	public void createView() {
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JPanel panel_Everything = new JPanel();
		add(panel_Everything, "cell 0 0,grow");
		panel_Everything.setLayout(new MigLayout("", "[grow]", "[grow][grow][]"));
		
		
		// CC -----------------------------------------------------------------
		
		JPanel panel_CC = new JPanel();
		panel_Everything.add(panel_CC, "cell 0 0,grow");
		panel_CC.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Chief Complaint", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_CC.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane_CC = new JScrollPane();
		panel_CC.add(scrollPane_CC, "cell 0 0,grow");
		
		textArea_CC = new JTextArea();
		textArea_CC.setWrapStyleWord(true);
		textArea_CC.setLineWrap(true);
		scrollPane_CC.setViewportView(textArea_CC);
		
		// END CC /////////////////////////////////////////////////////////////
		
		
		// PED ================================================================
		
		JPanel panel_PED = new JPanel();
		panel_Everything.add(panel_PED, "cell 0 1,grow");
		panel_PED.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Physical Exam Detail", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PED.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		
		
		
		// VISION -------------------------------------------------------------
		
		JPanel panel_Vision = new JPanel();
		panel_PED.add(panel_Vision, "cell 0 0,grow");
		panel_Vision.setLayout(new MigLayout("", "[grow]", "[][][][grow][][grow][grow]"));
		
		
		
		// DV -----------------------------------------------------------------
		
		JPanel panel_DV = new JPanel();
		panel_DV.setBorder(new TitledBorder(null, "Distance Vision", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Vision.add(panel_DV, "cell 0 0,growx");
		panel_DV.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		JPanel panel_DVSC = new JPanel();
		panel_DV.add(panel_DVSC, "cell 0 0,grow");
		panel_DVSC.setBorder(new TitledBorder(null, "Without Glasses", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_DVSC.setLayout(new MigLayout("", "[][][grow]", "[][]"));
		
		JLabel lblRightEye = new JLabel("Right Eye");
		panel_DVSC.add(lblRightEye, "cell 0 0");
		
		JLabel lbl_20 = new JLabel("20/");
		panel_DVSC.add(lbl_20, "cell 1 0,alignx trailing");
		
		textField_DVODSC = new JTextField();
		panel_DVSC.add(textField_DVODSC, "cell 2 0,growx");
		textField_DVODSC.setColumns(20);
		
		JLabel lblLeftEye = new JLabel("Left Eye");
		panel_DVSC.add(lblLeftEye, "cell 0 1");
		
		JLabel lbl_20_1 = new JLabel("20/");
		panel_DVSC.add(lbl_20_1, "cell 1 1,alignx trailing");
		
		textField_DVOSSC = new JTextField();
		panel_DVSC.add(textField_DVOSSC, "cell 2 1,growx");
		textField_DVOSSC.setColumns(20);
		
		JPanel panel_DVCC = new JPanel();
		panel_DV.add(panel_DVCC, "cell 0 1,grow");
		panel_DVCC.setBorder(new TitledBorder(null, "With Glasses", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_DVCC.setLayout(new MigLayout("", "[][][grow]", "[][]"));
		
		JLabel lblRightEye_1 = new JLabel("Right Eye");
		panel_DVCC.add(lblRightEye_1, "cell 0 0");
		
		JLabel lbl_20_2 = new JLabel("20/");
		panel_DVCC.add(lbl_20_2, "cell 1 0,alignx trailing");
		
		textField_DVODCC = new JTextField();
		textField_DVODCC.setColumns(20);
		panel_DVCC.add(textField_DVODCC, "cell 2 0,growx");
		
		JLabel lblLeftEye_1 = new JLabel("Left Eye");
		panel_DVCC.add(lblLeftEye_1, "cell 0 1");
		
		JLabel lbl_20_3 = new JLabel("20/");
		panel_DVCC.add(lbl_20_3, "cell 1 1,alignx trailing");
		
		textField_DVOSCC = new JTextField();
		textField_DVOSCC.setColumns(20);
		panel_DVCC.add(textField_DVOSCC, "cell 2 1,growx");
		
		// END DV /////////////////////////////////////////////////////////////
		
		
		
		// AR -----------------------------------------------------------------
		
		JPanel panel_Autorefraction = new JPanel();
		panel_Autorefraction.setBorder(new TitledBorder(null, "Autorefraction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Vision.add(panel_Autorefraction, "cell 0 1,growx");
		panel_Autorefraction.setLayout(new MigLayout("", "[][grow][grow][grow]", "[][][][][][][][][]"));
		
		JLabel lblSphere_AR = new JLabel("Sphere");
		panel_Autorefraction.add(lblSphere_AR, "cell 1 0,alignx center");
		
		JLabel lblCylinder_AR = new JLabel("Cylinder");
		panel_Autorefraction.add(lblCylinder_AR, "cell 2 0,alignx center");
		
		JLabel lblAxis_AR = new JLabel("Axis");
		panel_Autorefraction.add(lblAxis_AR, "cell 3 0,alignx center");
		
		JSeparator separator_AR1 = new JSeparator();
		panel_Autorefraction.add(separator_AR1, "cell 0 1 4 1,growx");
		
		JLabel lblARSC = new JLabel("ARs\u0304c");
		panel_Autorefraction.add(lblARSC, "cell 0 2");
		
		JLabel lblOd_1 = new JLabel("OD");
		panel_Autorefraction.add(lblOd_1, "cell 0 3,alignx trailing");
		
		textField_ARSC_OD_Sphere = new JTextField();
		panel_Autorefraction.add(textField_ARSC_OD_Sphere, "cell 1 3,growx");
		textField_ARSC_OD_Sphere.setColumns(8);
		
		textField_ARSC_OD_Cyl = new JTextField();
		panel_Autorefraction.add(textField_ARSC_OD_Cyl, "cell 2 3,growx");
		textField_ARSC_OD_Cyl.setColumns(8);
		
		textField_ARSC_OD_Axis = new JTextField();
		textField_ARSC_OD_Axis.setColumns(8);
		panel_Autorefraction.add(textField_ARSC_OD_Axis, "cell 3 3,growx");
		
		JLabel lblOs_1 = new JLabel("OS");
		panel_Autorefraction.add(lblOs_1, "cell 0 4,alignx trailing");
		
		textField_ARSC_OS_Sphere = new JTextField();
		textField_ARSC_OS_Sphere.setColumns(8);
		panel_Autorefraction.add(textField_ARSC_OS_Sphere, "cell 1 4,growx");
		
		textField_ARSC_OS_Cyl = new JTextField();
		textField_ARSC_OS_Cyl.setColumns(8);
		panel_Autorefraction.add(textField_ARSC_OS_Cyl, "cell 2 4,growx");
		
		textField_ARSC_OS_Axis = new JTextField();
		textField_ARSC_OS_Axis.setColumns(8);
		panel_Autorefraction.add(textField_ARSC_OS_Axis, "cell 3 4,growx");
		
		JSeparator separator_AR2 = new JSeparator();
		panel_Autorefraction.add(separator_AR2, "cell 0 5 4 1,growx");
		
		JLabel lblARCC = new JLabel("ARc\u0304c");
		panel_Autorefraction.add(lblARCC, "cell 0 6");
		
		JLabel lblOd_2 = new JLabel("OD");
		panel_Autorefraction.add(lblOd_2, "cell 0 7,alignx trailing");
		
		textField_ARCC_OD_Sphere = new JTextField();
		textField_ARCC_OD_Sphere.setColumns(8);
		panel_Autorefraction.add(textField_ARCC_OD_Sphere, "cell 1 7,growx");
		
		textField_ARCC_OD_Cyl = new JTextField();
		textField_ARCC_OD_Cyl.setColumns(8);
		panel_Autorefraction.add(textField_ARCC_OD_Cyl, "cell 2 7,growx");
		
		textField_ARCC_OD_Axis = new JTextField();
		textField_ARCC_OD_Axis.setColumns(8);
		panel_Autorefraction.add(textField_ARCC_OD_Axis, "cell 3 7,growx");
		
		JLabel lblOs_2 = new JLabel("OS");
		panel_Autorefraction.add(lblOs_2, "cell 0 8,alignx trailing");
		
		textField_ARCC_OS_Sphere = new JTextField();
		textField_ARCC_OS_Sphere.setColumns(8);
		panel_Autorefraction.add(textField_ARCC_OS_Sphere, "cell 1 8,growx");
		
		textField_ARCC_OS_Cyl = new JTextField();
		textField_ARCC_OS_Cyl.setColumns(8);
		panel_Autorefraction.add(textField_ARCC_OS_Cyl, "cell 2 8,growx");
		
		textField_ARCC_OS_Axis = new JTextField();
		textField_ARCC_OS_Axis.setColumns(8);
		panel_Autorefraction.add(textField_ARCC_OS_Axis, "cell 3 8,growx");
		
		// END AR /////////////////////////////////////////////////////////////
		
		
		
		// GLASSES RX ---------------------------------------------------------
		
		JPanel panel_GlassesRx = new JPanel();
		panel_Vision.add(panel_GlassesRx, "cell 0 2,growx");
		panel_GlassesRx.setBorder(new TitledBorder(null, "Glasses Rx", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_GlassesRx.setLayout(new MigLayout("", "[][grow][grow][grow][grow]", "[][][][]"));
		
		JLabel lblSphereRx = new JLabel("Sphere");
		panel_GlassesRx.add(lblSphereRx, "cell 1 0,alignx center");
		
		JLabel lblCylinderRx = new JLabel("Cylinder");
		panel_GlassesRx.add(lblCylinderRx, "cell 2 0,alignx center");
		
		JLabel lblAxisRx = new JLabel("Axis");
		panel_GlassesRx.add(lblAxisRx, "cell 3 0,alignx center");
		
		JLabel lblAddRx = new JLabel("Add");
		panel_GlassesRx.add(lblAddRx, "cell 4 0,alignx center");
		
		JLabel lblOdRx = new JLabel("OD");
		panel_GlassesRx.add(lblOdRx, "cell 0 1,alignx trailing");
		
		textField_Rx_OD_Sphere = new JTextField();
		panel_GlassesRx.add(textField_Rx_OD_Sphere, "cell 1 1,growx");
		textField_Rx_OD_Sphere.setColumns(8);
		
		textField_Rx_OD_Cyl = new JTextField();
		panel_GlassesRx.add(textField_Rx_OD_Cyl, "cell 2 1,growx");
		textField_Rx_OD_Cyl.setColumns(8);
		
		textField_Rx_OD_Axis = new JTextField();
		panel_GlassesRx.add(textField_Rx_OD_Axis, "cell 3 1,growx");
		textField_Rx_OD_Axis.setColumns(8);
		
		textField_Rx_OD_Add = new JTextField();
		panel_GlassesRx.add(textField_Rx_OD_Add, "cell 4 1,growx");
		textField_Rx_OD_Add.setColumns(8);
		
		JLabel lblOsRx = new JLabel("OS");
		panel_GlassesRx.add(lblOsRx, "cell 0 2,alignx trailing");
		
		textField_Rx_OS_Sphere = new JTextField();
		panel_GlassesRx.add(textField_Rx_OS_Sphere, "cell 1 2,growx");
		textField_Rx_OS_Sphere.setColumns(8);
		
		textField_Rx_OS_Cyl = new JTextField();
		panel_GlassesRx.add(textField_Rx_OS_Cyl, "cell 2 2,growx");
		textField_Rx_OS_Cyl.setColumns(8);
		
		textField_Rx_OS_Axis = new JTextField();
		panel_GlassesRx.add(textField_Rx_OS_Axis, "cell 3 2,growx");
		textField_Rx_OS_Axis.setColumns(8);
		
		textField_Rx_OS_Add = new JTextField();
		panel_GlassesRx.add(textField_Rx_OS_Add, "cell 4 2,growx");
		textField_Rx_OS_Add.setColumns(8);
		
		JLabel lblGlassesRxNotes = new JLabel("Rx Notes");
		panel_GlassesRx.add(lblGlassesRxNotes, "cell 0 3");
		
		textField_GlassesRxNotes = new JTextField();
		panel_GlassesRx.add(textField_GlassesRxNotes, "cell 1 3 4 1,growx");
		textField_GlassesRxNotes.setColumns(20);
		
		// END GLS RX /////////////////////////////////////////////////////////
		
		
		
		// SLE ----------------------------------------------------------------
		
		JPanel panel_SLE = new JPanel();
		panel_SLE.setBorder(new TitledBorder(null, "Slit Lamp Exam", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Vision.add(panel_SLE, "cell 0 3,grow");
		panel_SLE.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		
		// SLE MISC -----------------------------------------------------------
		
		JPanel panel_SLE_Misc = new JPanel();
		panel_SLE.add(panel_SLE_Misc, "cell 0 0,grow");
		panel_SLE_Misc.setLayout(new MigLayout("", "[][grow][][grow]", "[][][][][][][][][][][][]"));
		
		JLabel lblOdSLE = new JLabel("OD");
		panel_SLE_Misc.add(lblOdSLE, "cell 1 0,alignx center");
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		panel_SLE_Misc.add(separator_7, "cell 2 0,grow");
		
		JLabel lblOsSLE = new JLabel("OS");
		panel_SLE_Misc.add(lblOsSLE, "cell 3 0,alignx center");
		
		JSeparator separator_SLE_1 = new JSeparator();
		panel_SLE_Misc.add(separator_SLE_1, "cell 0 1 4 1,growx");
		
		JLabel lblPupils = new JLabel("Pupils");
		panel_SLE_Misc.add(lblPupils, "cell 0 2");
		
		checkBox_SLE_Pupils_OD_Ab = new JCheckBox("Abnormal?");
		panel_SLE_Misc.add(checkBox_SLE_Pupils_OD_Ab, "cell 1 2,alignx center");
		
		checkBox_SLE_Pupils_OS_Ab = new JCheckBox("Abnormal?");
		panel_SLE_Misc.add(checkBox_SLE_Pupils_OS_Ab, "cell 3 2,alignx center");
		
		textField_SLE_Pupils_OD = new JTextField();
		panel_SLE_Misc.add(textField_SLE_Pupils_OD, "cell 1 3,growx");
		textField_SLE_Pupils_OD.setColumns(10);
		
		textField_SLE_Pupils_OS = new JTextField();
		panel_SLE_Misc.add(textField_SLE_Pupils_OS, "cell 3 3,growx");
		textField_SLE_Pupils_OS.setColumns(10);
		
		JSeparator separator_SLE_2 = new JSeparator();
		panel_SLE_Misc.add(separator_SLE_2, "cell 0 4 4 1,growx");
		
		JLabel lblAc = new JLabel("AC");
		panel_SLE_Misc.add(lblAc, "cell 0 5");
		
		checkBox_SLE_AC_OD_Ab = new JCheckBox("Abnormal?");
		panel_SLE_Misc.add(checkBox_SLE_AC_OD_Ab, "cell 1 5,alignx center");
		
		checkBox_SLE_AC_OS_Ab = new JCheckBox("Abnormal?");
		panel_SLE_Misc.add(checkBox_SLE_AC_OS_Ab, "cell 3 5,alignx center");
		
		textField_SLE_AC_OD = new JTextField();
		textField_SLE_AC_OD.setColumns(10);
		panel_SLE_Misc.add(textField_SLE_AC_OD, "cell 1 6,growx");
		
		textField_SLE_AC_OS = new JTextField();
		textField_SLE_AC_OS.setColumns(10);
		panel_SLE_Misc.add(textField_SLE_AC_OS, "cell 3 6,growx");
		
		JSeparator separator_SLE_3 = new JSeparator();
		panel_SLE_Misc.add(separator_SLE_3, "cell 0 7 4 1,growx");
		
		JLabel lblLens = new JLabel("Lens");
		panel_SLE_Misc.add(lblLens, "cell 0 8");
		
		JLabel lblNs = new JLabel("NS");
		panel_SLE_Misc.add(lblNs, "cell 0 9,alignx trailing");
		
		comboBox_SLE_NS_OD = new JComboBox();
		comboBox_SLE_NS_OD.setModel(new DefaultComboBoxModel(new String[] {"Clear", "Trace", "+1", "+2", "+3", "+4", "Brunescent"}));
		panel_SLE_Misc.add(comboBox_SLE_NS_OD, "flowx,cell 1 9");
		
		textField_SLE_NS_OD = new JTextField();
		textField_SLE_NS_OD.setColumns(10);
		panel_SLE_Misc.add(textField_SLE_NS_OD, "cell 1 9,growx");
		
		comboBox_SLE_NS_OS = new JComboBox();
		comboBox_SLE_NS_OS.setModel(new DefaultComboBoxModel(new String[] {"Clear", "Trace", "+1", "+2", "+3", "+4", "Brunescent"}));
		panel_SLE_Misc.add(comboBox_SLE_NS_OS, "flowx,cell 3 9");
		
		textField_SLE_NS_OS = new JTextField();
		textField_SLE_NS_OS.setColumns(10);
		panel_SLE_Misc.add(textField_SLE_NS_OS, "cell 3 9,growx");
		
		chckbx_SLE_Pseudophakia_OD = new JCheckBox("Pseudophakia?");
		panel_SLE_Misc.add(chckbx_SLE_Pseudophakia_OD, "flowx,cell 1 8,alignx center");
		
		chckbx_SLE_Pseudophakia_OS = new JCheckBox("Pseudophakia?");
		panel_SLE_Misc.add(chckbx_SLE_Pseudophakia_OS, "flowx,cell 3 8,alignx center");
		
		chckbx_SLE_PCO_OD = new JCheckBox("PCO?");
		panel_SLE_Misc.add(chckbx_SLE_PCO_OD, "cell 1 8");
		
		chckbx_SLE_PCO_OS = new JCheckBox("PCO?");
		panel_SLE_Misc.add(chckbx_SLE_PCO_OS, "cell 3 8");
		
		JLabel lblCortical = new JLabel("Cortical");
		panel_SLE_Misc.add(lblCortical, "cell 0 10,alignx trailing");
		
		comboBox_SLE_Coritcal_OD = new JComboBox();
		comboBox_SLE_Coritcal_OD.setModel(new DefaultComboBoxModel(new String[] {"Clear", "Trace", "+1", "+2", "+3", "+4"}));
		panel_SLE_Misc.add(comboBox_SLE_Coritcal_OD, "flowx,cell 1 10");
		
		textField_SLE_Cortical_OD = new JTextField();
		textField_SLE_Cortical_OD.setColumns(10);
		panel_SLE_Misc.add(textField_SLE_Cortical_OD, "cell 1 10,growx");
		
		comboBox_SLE_Coritcal_OS = new JComboBox();
		comboBox_SLE_Coritcal_OS.setModel(new DefaultComboBoxModel(new String[] {"Clear", "Trace", "+1", "+2", "+3", "+4"}));
		panel_SLE_Misc.add(comboBox_SLE_Coritcal_OS, "flowx,cell 3 10");
		
		textField_SLE_Cortical_OS = new JTextField();
		textField_SLE_Cortical_OS.setColumns(10);
		panel_SLE_Misc.add(textField_SLE_Cortical_OS, "cell 3 10,growx");
		
		JLabel lblPsc = new JLabel("PSC");
		panel_SLE_Misc.add(lblPsc, "cell 0 11,alignx trailing");
		
		comboBox_SLE_PSC_OD = new JComboBox();
		comboBox_SLE_PSC_OD.setModel(new DefaultComboBoxModel(new String[] {"Clear", "Trace", "+1", "+2", "+3", "+4"}));
		panel_SLE_Misc.add(comboBox_SLE_PSC_OD, "flowx,cell 1 11");
		
		textField_SLE_PSC_OD = new JTextField();
		textField_SLE_PSC_OD.setColumns(10);
		panel_SLE_Misc.add(textField_SLE_PSC_OD, "cell 1 11,growx");
		
		comboBox_SLE_PSC_OS = new JComboBox();
		comboBox_SLE_PSC_OS.setModel(new DefaultComboBoxModel(new String[] {"Clear", "Trace", "+1", "+2", "+3", "+4"}));
		panel_SLE_Misc.add(comboBox_SLE_PSC_OS, "flowx,cell 3 11");
		
		textField_SLE_PSC_OS = new JTextField();
		textField_SLE_PSC_OS.setColumns(10);
		panel_SLE_Misc.add(textField_SLE_PSC_OS, "cell 3 11,growx");
		
		// END SLE MISC //////////////////////////////////////////////////////
		
		
		// SLE SKETCH
		
		JPanel panel_SLE_Diagram = new JPanel();
		panel_SLE.add(panel_SLE_Diagram, "cell 0 1");
		panel_SLE_Diagram.setBorder(new TitledBorder(null, "Diagram", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_SLE_Diagram.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));		
		
		btnSLESketch = new JButton("Sketch");
		panel_SLE_Diagram.add(btnSLESketch);
		
		label_SLE_Sketch = new JLabel("");
		panel_SLE_Diagram.add(label_SLE_Sketch);
		
		// END SLE ////////////////////////////////////////////////////////////
		
		
		// IOP ----------------------------------------------------------------
		
		JPanel panel_IOP = new JPanel();
		panel_IOP.setBorder(new TitledBorder(null, "Intraocular Pressure", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Vision.add(panel_IOP, "cell 0 4,grow");
		panel_IOP.setLayout(new MigLayout("", "[][grow][grow][grow]", "[][][]"));
		
		JLabel lblValue = new JLabel("Value");
		panel_IOP.add(lblValue, "cell 1 0,alignx center");
		
		JLabel lblType = new JLabel("Type");
		panel_IOP.add(lblType, "cell 2 0,alignx center");
		
		JLabel lblNotes = new JLabel("Notes");
		panel_IOP.add(lblNotes, "cell 3 0,alignx center");
		
		JLabel lblOd_3 = new JLabel("OD");
		panel_IOP.add(lblOd_3, "cell 0 1,alignx trailing");
		
		textField_IOP_Value_OD = new JTextField();
		panel_IOP.add(textField_IOP_Value_OD, "cell 1 1,growx");
		textField_IOP_Value_OD.setColumns(8);
		
		textField_IOP_Type_OD = new JTextField();
		textField_IOP_Type_OD.setColumns(8);
		panel_IOP.add(textField_IOP_Type_OD, "cell 2 1,growx");
		
		textField_IOP_Notes_OD = new JTextField();
		textField_IOP_Notes_OD.setColumns(10);
		panel_IOP.add(textField_IOP_Notes_OD, "cell 3 1,growx");
		
		JLabel lblOs_3 = new JLabel("OS");
		panel_IOP.add(lblOs_3, "cell 0 2,alignx trailing");
		
		textField_IOP_Value_OS = new JTextField();
		textField_IOP_Value_OS.setColumns(8);
		panel_IOP.add(textField_IOP_Value_OS, "cell 1 2,growx");
		
		textField_IOP_Type_OS = new JTextField();
		textField_IOP_Type_OS.setColumns(8);
		panel_IOP.add(textField_IOP_Type_OS, "cell 2 2,growx");
		
		textField_IOP_Notes_OS = new JTextField();
		textField_IOP_Notes_OS.setColumns(10);
		panel_IOP.add(textField_IOP_Notes_OS, "cell 3 2,growx");
		
		// END IOP ////////////////////////////////////////////////////////////
		
		
		
		// GONIO --------------------------------------------------------------
		
		JPanel panel_Gonio = new JPanel();
		panel_Gonio.setBorder(new TitledBorder(null, "Gonioscopy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Vision.add(panel_Gonio, "cell 0 5,alignx left,growy");
		
		btnGonioSketch = new JButton("Sketch");
		panel_Gonio.add(btnGonioSketch);
		
		lblGonioSketch = new JLabel("<gonio image>");
		panel_Gonio.add(lblGonioSketch);
		
		// END GONIO //////////////////////////////////////////////////////////
		
		
		
		
		
		// FUNDUS /////////////////////////////////////////////////////////////
		
		JPanel panel_FundusExam = new JPanel();
		panel_FundusExam.setBorder(new TitledBorder(null, "Fundus Exam", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Vision.add(panel_FundusExam, "cell 0 6,grow");
		panel_FundusExam.setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		JPanel panel_Fundus_Dialated = new JPanel();
		panel_FundusExam.add(panel_Fundus_Dialated, "cell 0 0,growx");
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
		
		// FUNDUS MISC --------------------------------------------------------
		
		JPanel panel_Fundus_Misc = new JPanel();
		panel_FundusExam.add(panel_Fundus_Misc, "cell 0 1,grow");
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
		
		// FUNDUS SKETCH ------------------------------------------------------
		
		JPanel panel_FundusImage = new JPanel();
		panel_FundusImage.setBorder(new TitledBorder(null, "Fundus", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_FundusExam.add(panel_FundusImage, "cell 0 2,alignx left,growy");
		
		btnFundusSketch = new JButton("Sketch");
		panel_FundusImage.add(btnFundusSketch);
		
		lblFundusSketch = new JLabel("<fundus image>");
		panel_FundusImage.add(lblFundusSketch);
		
		// END FUNDUS /////////////////////////////////////////////////////////
		
		// END VISION  ////////////////////////////////////////////////////////
		
		
		// ASSESSMENT/PLAN ----------------------------------------------------
		
		JPanel panel_AssessmentAndPlan = new JPanel();
		panel_PED.add(panel_AssessmentAndPlan, "cell 0 1,grow");
		panel_AssessmentAndPlan.setLayout(new MigLayout("", "[grow]", "[][grow][][grow]"));
		
		
		// ASSESSMENT
		
		JLabel lblAssessment = new JLabel("Assessment");
		panel_AssessmentAndPlan.add(lblAssessment, "cell 0 0");
		
		JScrollPane scrollPane_Assessment = new JScrollPane();
		panel_AssessmentAndPlan.add(scrollPane_Assessment, "cell 0 1,grow");
		
		textArea_Assessment = new JTextArea();
		textArea_Assessment.setWrapStyleWord(true);
		textArea_Assessment.setLineWrap(true);
		scrollPane_Assessment.setViewportView(textArea_Assessment);
		
		
		// PLAN
		
		JLabel lblPlan = new JLabel("Plan");
		panel_AssessmentAndPlan.add(lblPlan, "cell 0 2");
		
		JScrollPane scrollPane_Plan = new JScrollPane();
		panel_AssessmentAndPlan.add(scrollPane_Plan, "cell 0 3,grow");
		
		textArea_Plan = new JTextArea();
		textArea_Plan.setWrapStyleWord(true);
		textArea_Plan.setLineWrap(true);
		scrollPane_Plan.setViewportView(textArea_Plan);
		
		panel_Buttons = new JPanel();
		panel_Everything.add(panel_Buttons, "cell 0 2,alignx center");
		
		btnSave = new JButton("Save");
		panel_Buttons.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		panel_Buttons.add(btnCancel);

	}
	
	
}
