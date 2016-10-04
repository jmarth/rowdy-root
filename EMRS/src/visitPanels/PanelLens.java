package visitPanels;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import models.Lens;
import models.MasterModel;
import net.miginfocom.swing.MigLayout;
import views.HomeView;
import views.viewinterface;

@SuppressWarnings("serial")
public class PanelLens extends JPanel implements viewinterface {

	private int index;
	
	private JComboBox comboBox_SLE_NS_OD;
	private JTextField textField_SLE_NS_OD;
	
	private JComboBox comboBox_SLE_NS_OS;
	private JTextField textField_SLE_NS_OS;
	
	private JCheckBox chckbxStableLensOD;
	private JCheckBox chckbxStableLensOS;
	
	private JCheckBox chckbx_SLE_Pseudophakia_OD;
	private JCheckBox chckbx_SLE_Pseudophakia_OS;
	
	private JCheckBox chckbx_SLE_PCO_OD;
	private JCheckBox chckbx_SLE_PCO_OS;
	
	private JComboBox comboBox_SLE_Coritcal_OD;
	private JTextField textField_SLE_Cortical_OD;
	
	private JComboBox comboBox_SLE_Coritcal_OS;
	private JTextField textField_SLE_Cortical_OS;
	
	private JComboBox comboBox_SLE_PSC_OD;
	private JTextField textField_SLE_PSC_OD;
	
	private JComboBox comboBox_SLE_PSC_OS;
	private JTextField textField_SLE_PSC_OS;

	/**
	 * Create the panel.
	 */
	public PanelLens(int index) {
		
		this.index = index;
		
		setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Lens", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		//panel_SLE.add(panel_SLE_Lens, "cell 0 2,grow");
		setLayout(new MigLayout("", "[][grow][][grow]", "[][][][][][]"));
		
		JLabel lblOdSLE = new JLabel("OD");
		add(lblOdSLE, "cell 1 0,alignx center");
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		add(separator_7, "cell 2 0,grow");
		
		JLabel lblOsSLE = new JLabel("OS");
		add(lblOsSLE, "cell 3 0,alignx center");
		
		JSeparator separator_SLE_3 = new JSeparator();
		add(separator_SLE_3, "cell 0 1 4 1,growx");
		
		JLabel lblNs = new JLabel("NS");
		add(lblNs, "cell 0 3,alignx trailing");
		
		comboBox_SLE_NS_OD = new JComboBox();
		comboBox_SLE_NS_OD.setModel(new DefaultComboBoxModel(new String[] {"Clear", "Trace", "+1", "+2", "+3", "+4", "Brunescent"}));
		add(comboBox_SLE_NS_OD, "flowx,cell 1 3");
		
		textField_SLE_NS_OD = new JTextField();
		textField_SLE_NS_OD.setColumns(10);
		add(textField_SLE_NS_OD, "cell 1 3,growx");
		
		comboBox_SLE_NS_OS = new JComboBox();
		comboBox_SLE_NS_OS.setModel(new DefaultComboBoxModel(new String[] {"Clear", "Trace", "+1", "+2", "+3", "+4", "Brunescent"}));
		add(comboBox_SLE_NS_OS, "flowx,cell 3 3");
		
		textField_SLE_NS_OS = new JTextField();
		textField_SLE_NS_OS.setColumns(10);
		add(textField_SLE_NS_OS, "cell 3 3,growx");
		
		chckbxStableLensOD = new JCheckBox("Stable?");
		add(chckbxStableLensOD, "flowx,cell 1 2");
		
		chckbx_SLE_Pseudophakia_OD = new JCheckBox("Pseudophakia?");
		add(chckbx_SLE_Pseudophakia_OD, "cell 1 2,alignx center");
		
		chckbxStableLensOS = new JCheckBox("Stable?");
		add(chckbxStableLensOS, "flowx,cell 3 2");
		
		chckbx_SLE_Pseudophakia_OS = new JCheckBox("Pseudophakia?");
		add(chckbx_SLE_Pseudophakia_OS, "cell 3 2,alignx center");
		
		chckbx_SLE_PCO_OD = new JCheckBox("PCO?");
		add(chckbx_SLE_PCO_OD, "cell 1 2");
		
		chckbx_SLE_PCO_OS = new JCheckBox("PCO?");
		add(chckbx_SLE_PCO_OS, "cell 3 2");
		
		JLabel lblCortical = new JLabel("Cortical");
		add(lblCortical, "cell 0 4,alignx trailing");
		
		comboBox_SLE_Coritcal_OD = new JComboBox();
		comboBox_SLE_Coritcal_OD.setModel(new DefaultComboBoxModel(new String[] {"Clear", "Trace", "+1", "+2", "+3", "+4"}));
		add(comboBox_SLE_Coritcal_OD, "flowx,cell 1 4");
		
		textField_SLE_Cortical_OD = new JTextField();
		textField_SLE_Cortical_OD.setColumns(10);
		add(textField_SLE_Cortical_OD, "cell 1 4,growx");
		
		comboBox_SLE_Coritcal_OS = new JComboBox();
		comboBox_SLE_Coritcal_OS.setModel(new DefaultComboBoxModel(new String[] {"Clear", "Trace", "+1", "+2", "+3", "+4"}));
		add(comboBox_SLE_Coritcal_OS, "flowx,cell 3 4");
		
		textField_SLE_Cortical_OS = new JTextField();
		textField_SLE_Cortical_OS.setColumns(10);
		add(textField_SLE_Cortical_OS, "cell 3 4,growx");
		
		JLabel lblPsc = new JLabel("PSC");
		add(lblPsc, "cell 0 5,alignx trailing");
		
		comboBox_SLE_PSC_OD = new JComboBox();
		comboBox_SLE_PSC_OD.setModel(new DefaultComboBoxModel(new String[] {"Clear", "Trace", "+1", "+2", "+3", "+4"}));
		add(comboBox_SLE_PSC_OD, "flowx,cell 1 5");
		
		textField_SLE_PSC_OD = new JTextField();
		textField_SLE_PSC_OD.setColumns(10);
		add(textField_SLE_PSC_OD, "cell 1 5,growx");
		
		comboBox_SLE_PSC_OS = new JComboBox();
		comboBox_SLE_PSC_OS.setModel(new DefaultComboBoxModel(new String[] {"Clear", "Trace", "+1", "+2", "+3", "+4"}));
		add(comboBox_SLE_PSC_OS, "flowx,cell 3 5");
		
		textField_SLE_PSC_OS = new JTextField();
		textField_SLE_PSC_OS.setColumns(10);
		add(textField_SLE_PSC_OS, "cell 3 5,growx");
	}
	
	public void setFields() {
		
		Lens l = getMasterModel().getCurrentPatientVisitList().get(index).getMyLens();

		String temp;
		
		temp = l.getNS_OD();
		if (temp != null) {
			comboBox_SLE_NS_OD.setSelectedItem(temp);
		} else {
			//TODO
		}
		textField_SLE_NS_OD.setText(l.getNS_OD_Notes());
		
		temp = l.getNS_OS();
		if (temp != null) {
			comboBox_SLE_NS_OS.setSelectedItem(temp);
		} else {
			//TODO need null value for combo box
		}
		textField_SLE_NS_OS.setText(l.getNS_OS_Notes());
		
		
		
		chckbxStableLensOD.setSelected(l.isStableLensOD() == 1? true:false);
		chckbxStableLensOS.setSelected(l.isStableLensOS() == 1? true:false);
		
		chckbx_SLE_Pseudophakia_OD.setSelected(l.isPseudophakia_OD() == 1? true:false);
		chckbx_SLE_Pseudophakia_OS.setSelected(l.isPseudophakia_OS() == 1? true:false);
		
		chckbx_SLE_PCO_OD.setSelected(l.isPCO_OD() == 1? true:false);
		chckbx_SLE_PCO_OS.setSelected(l.isPCO_OS() == 1? true:false);
		
		
		
		temp = l.getCoritcal_OD();
		if (temp !=null)
			comboBox_SLE_Coritcal_OD.setSelectedItem(temp);
		else
			//TODO need null value for combo box
		textField_SLE_Cortical_OD.setText(l.getCortical_OD_Notes());
		
		
		
		temp = l.getCoritcal_OS();
		if (temp !=null)
			comboBox_SLE_Coritcal_OS.setSelectedItem(temp);
		else
			//TODO need null value for combo box
		textField_SLE_Cortical_OS.setText(l.getCortical_OS_Notes());
		
		
		
		temp = l.getPSC_OD();
		if (temp !=null)
			comboBox_SLE_PSC_OD.setSelectedItem(temp);
		else 
			//TODO need null value for combo box
		textField_SLE_PSC_OD.setText(l.getPSC_OD_Notes());
		
		
		
		temp = l.getPSC_OS();
		if (temp !=null)
			comboBox_SLE_PSC_OS.setSelectedItem(temp);
		else
			//TODO need null value for combo box
		textField_SLE_PSC_OS.setText(l.getPSC_OS_Notes());
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
		return ((PanelSLE)this.getParent()).getHomeView();
	}
}
