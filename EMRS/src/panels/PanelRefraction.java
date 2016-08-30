package panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import models.Refraction;
import net.miginfocom.swing.MigLayout;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.border.EtchedBorder;
import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class PanelRefraction extends JPanel {

	private JRadioButton rdbtn_AutoRefraction;
	private JRadioButton rdbtn_ManifestRefraction;
	
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
	
	private final NoneSelectedButtonGroup buttonGroup = new NoneSelectedButtonGroup();

	
	/**
	 * Create the panel.
	 */
	public PanelRefraction() {
				
		setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Refraction", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, new Font("Tahoma", Font.BOLD, 20), new Color(0, 0, 0)));
				
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][][][][][][][][][]"));
		
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(64, 64, 64)), "Type", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		add(panel, "cell 0 0 4 1,grow");
		
		rdbtn_ManifestRefraction = new JRadioButton("Manifest Refraction");
		buttonGroup.add(rdbtn_ManifestRefraction);
		panel.add(rdbtn_ManifestRefraction);
		
		rdbtn_AutoRefraction = new JRadioButton("Autorefraction");
		buttonGroup.add(rdbtn_AutoRefraction);
		panel.add(rdbtn_AutoRefraction);
		
		JLabel lblSphere_AR = new JLabel("Sphere");
		add(lblSphere_AR, "cell 1 1,alignx center");
		
		JLabel lblCylinder_AR = new JLabel("Cylinder");
		add(lblCylinder_AR, "cell 2 1,alignx center");
		
		JLabel lblAxis_AR = new JLabel("Axis");
		add(lblAxis_AR, "cell 3 1,alignx center");
		
		JSeparator separator_AR1 = new JSeparator();
		add(separator_AR1, "cell 0 2 4 1,growx");
		
		JLabel lblSC = new JLabel("s\u0304c");
		add(lblSC, "cell 0 3");
		
		JLabel lblOd_1 = new JLabel("OD");
		add(lblOd_1, "cell 0 4,alignx trailing");
		
		textField_ARSC_OD_Sphere = new JTextField();
		add(textField_ARSC_OD_Sphere, "cell 1 4,growx");
		textField_ARSC_OD_Sphere.setColumns(8);
		
		textField_ARSC_OD_Cyl = new JTextField();
		add(textField_ARSC_OD_Cyl, "cell 2 4,growx");
		textField_ARSC_OD_Cyl.setColumns(8);
		
		textField_ARSC_OD_Axis = new JTextField();
		textField_ARSC_OD_Axis.setColumns(8);
		add(textField_ARSC_OD_Axis, "cell 3 4,growx");
		
		JLabel lblOs_1 = new JLabel("OS");
		add(lblOs_1, "cell 0 5,alignx trailing");
		
		textField_ARSC_OS_Sphere = new JTextField();
		textField_ARSC_OS_Sphere.setColumns(8);
		add(textField_ARSC_OS_Sphere, "cell 1 5,growx");
		
		textField_ARSC_OS_Cyl = new JTextField();
		textField_ARSC_OS_Cyl.setColumns(8);
		add(textField_ARSC_OS_Cyl, "cell 2 5,growx");
		
		textField_ARSC_OS_Axis = new JTextField();
		textField_ARSC_OS_Axis.setColumns(8);
		add(textField_ARSC_OS_Axis, "cell 3 5,growx");
		
		JSeparator separator_AR2 = new JSeparator();
		add(separator_AR2, "cell 0 6 4 1,growx");
		
		JLabel lblARCC = new JLabel("c\u0304c");
		add(lblARCC, "cell 0 7");
		
		JLabel lblOd_2 = new JLabel("OD");
		add(lblOd_2, "cell 0 8,alignx trailing");
		
		textField_ARCC_OD_Sphere = new JTextField();
		textField_ARCC_OD_Sphere.setColumns(8);
		add(textField_ARCC_OD_Sphere, "cell 1 8,growx");
		
		textField_ARCC_OD_Cyl = new JTextField();
		textField_ARCC_OD_Cyl.setColumns(8);
		add(textField_ARCC_OD_Cyl, "cell 2 8,growx");
		
		textField_ARCC_OD_Axis = new JTextField();
		textField_ARCC_OD_Axis.setColumns(8);
		add(textField_ARCC_OD_Axis, "cell 3 8,growx");
		
		JLabel lblOs_2 = new JLabel("OS");
		add(lblOs_2, "cell 0 9,alignx trailing");
		
		textField_ARCC_OS_Sphere = new JTextField();
		textField_ARCC_OS_Sphere.setColumns(8);
		add(textField_ARCC_OS_Sphere, "cell 1 9,growx");
		
		textField_ARCC_OS_Cyl = new JTextField();
		textField_ARCC_OS_Cyl.setColumns(8);
		add(textField_ARCC_OS_Cyl, "cell 2 9,growx");
		
		textField_ARCC_OS_Axis = new JTextField();
		textField_ARCC_OS_Axis.setColumns(8);
		add(textField_ARCC_OS_Axis, "cell 3 9,growx");
	}

	public Refraction createNewRefraction() {
		Refraction r = new Refraction(
				(
				rdbtn_ManifestRefraction.isSelected() ? 1 : rdbtn_AutoRefraction.isSelected() ? 0 : -1),
				textField_ARSC_OD_Sphere.getText(),
				textField_ARSC_OD_Cyl.getText(),
				textField_ARSC_OD_Axis.getText(),
				textField_ARSC_OS_Sphere.getText(),
				textField_ARSC_OS_Cyl.getText(),
				textField_ARSC_OS_Axis.getText(),
				textField_ARCC_OD_Sphere.getText(),
				textField_ARCC_OD_Cyl.getText(),
				textField_ARCC_OD_Axis.getText(),
				textField_ARCC_OS_Sphere.getText(),
				textField_ARCC_OS_Cyl.getText(),
				textField_ARCC_OS_Axis.getText()
				);
		
		return r;
	}
	
	/**
	 * Gets the selected button's name from a button group.
	 * 
	 * @param buttonGroup
	 * @return The string of the name of the selected radio button of the
	 *         buttonGroup
	 */
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}
	
	public void setFields(ArrayList<Object> refractCols) {
		String temp = refractCols.get(0).toString();
		if (temp.equals("0")) {
			rdbtn_AutoRefraction.setSelected(true);
		} else if (temp.equals("1")) {
			rdbtn_ManifestRefraction.setSelected(true);
		}
	}
}
