package panels;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import models.Lens;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.MatteBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class PanelLens extends JPanel {

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
	public PanelLens() {
		
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

	public Lens createNewLens() {
		
		String NS_OD = (String)comboBox_SLE_NS_OD.getSelectedItem();
		String NS_OS = (String)comboBox_SLE_NS_OS.getSelectedItem();
		String Coritcal_OD = (String)comboBox_SLE_Coritcal_OD.getSelectedItem();
		String Coritcal_OS = (String)comboBox_SLE_Coritcal_OS.getSelectedItem();
		String PSC_OD = (String)comboBox_SLE_PSC_OD.getSelectedItem();
		String PSC_OS = (String)comboBox_SLE_PSC_OS.getSelectedItem();
		
		Lens l = new Lens(
				
				NS_OD,
				textField_SLE_NS_OD.getText(),
				
				NS_OS,
				textField_SLE_NS_OS.getText(),
				
				chckbxStableLensOD.isSelected() ? 1 : 0,
				chckbxStableLensOS.isSelected() ? 1 : 0,
				
				chckbx_SLE_Pseudophakia_OD.isSelected() ? 1 : 0,
				chckbx_SLE_Pseudophakia_OS.isSelected() ? 1 : 0,
				
				chckbx_SLE_PCO_OD.isSelected() ? 1 : 0,
				chckbx_SLE_PCO_OS.isSelected() ? 1 : 0,
				
				Coritcal_OD,
				textField_SLE_Cortical_OD.getText(),
				
				Coritcal_OS,
				textField_SLE_Cortical_OS.getText(),
				
				PSC_OD,
				textField_SLE_PSC_OD.getText(),
				
				PSC_OS,
				textField_SLE_PSC_OS.getText()
				
				);
		
		return l;
	}

	public void setFields(ArrayList<Object> lensCols) {
		// will be iterating manually through the tuples
				int i = -1;

				String temp;
				
				
				temp = lensCols.get(++i).toString();
				if (temp !=null)
					comboBox_SLE_NS_OD.setSelectedItem(temp);
				
				temp = lensCols.get(++i).toString();
				if (temp !=null)
					textField_SLE_NS_OD.setText(temp);
				
				
				
				temp = lensCols.get(++i).toString();
				if (temp !=null)
					comboBox_SLE_NS_OS.setSelectedItem(temp);
				
				temp = lensCols.get(++i).toString();
				if (temp !=null)
					textField_SLE_NS_OS.setText(temp);
				
				
				
				temp = lensCols.get(++i).toString();
				if (temp.equals("1"))
					chckbxStableLensOD.setSelected(true);
				
				temp = lensCols.get(++i).toString();
				if (temp.equals("1"))
					chckbxStableLensOS.setSelected(true);
				
				
				
				temp = lensCols.get(++i).toString();
				if (temp.equals("1"))
					chckbx_SLE_Pseudophakia_OD.setSelected(true);
				
				temp = lensCols.get(++i).toString();
				if (temp.equals("1"))
					chckbx_SLE_Pseudophakia_OS.setSelected(true);
				
				
				
				temp = lensCols.get(++i).toString();
				if (temp.equals("1"))
					chckbx_SLE_PCO_OD.setSelected(true);
				
				temp = lensCols.get(++i).toString();
				if (temp.equals("1"))
					chckbx_SLE_PCO_OS.setSelected(true);
				
				
				
				temp = lensCols.get(++i).toString();
				if (temp !=null)
					comboBox_SLE_Coritcal_OD.setSelectedItem(temp);
				
				temp = lensCols.get(++i).toString();
				if (temp !=null)
					textField_SLE_Cortical_OD.setText(temp);
				
				
				
				temp = lensCols.get(++i).toString();
				if (temp !=null)
					comboBox_SLE_Coritcal_OS.setSelectedItem(temp);
				
				temp = lensCols.get(++i).toString();
				if (temp !=null)
					textField_SLE_Cortical_OS.setText(temp);
				
				
				
				temp = lensCols.get(++i).toString();
				if (temp !=null)
					comboBox_SLE_PSC_OD.setSelectedItem(temp);
				
				temp = lensCols.get(++i).toString();
				if (temp !=null)
					textField_SLE_PSC_OD.setText(temp);
				
				
				
				temp = lensCols.get(++i).toString();
				if (temp !=null)
					comboBox_SLE_PSC_OS.setSelectedItem(temp);
				
				temp = lensCols.get(++i).toString();
				if (temp !=null)
					textField_SLE_PSC_OS.setText(temp);
	}
}
