package panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import models.GlassesRx;
import net.miginfocom.swing.MigLayout;

public class PanelGlassesRx extends JPanel {

	private JTextField textField_Rx_OD_Sphere;
	private JTextField textField_Rx_OD_Cyl;
	private JTextField textField_Rx_OD_Axis;
	private JTextField textField_Rx_OD_Add;
	private JTextField textField_Rx_OS_Sphere;
	private JTextField textField_Rx_OS_Cyl;
	private JTextField textField_Rx_OS_Axis;
	private JTextField textField_Rx_OS_Add;
	private JTextField textField_GlassesRxNotes;

	/**
	 * Create the panel.
	 */
	public PanelGlassesRx() {
		
		setBorder(new TitledBorder(null, "Glasses Rx", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[][grow][grow][grow][grow]", "[][][][]"));
		
		JLabel lblSphereRx = new JLabel("Sphere");
		add(lblSphereRx, "cell 1 0,alignx center");
		
		JLabel lblCylinderRx = new JLabel("Cylinder");
		add(lblCylinderRx, "cell 2 0,alignx center");
		
		JLabel lblAxisRx = new JLabel("Axis");
		add(lblAxisRx, "cell 3 0,alignx center");
		
		JLabel lblAddRx = new JLabel("Add");
		add(lblAddRx, "cell 4 0,alignx center");
		
		JLabel lblOdRx = new JLabel("OD");
		add(lblOdRx, "cell 0 1,alignx trailing");
		
		textField_Rx_OD_Sphere = new JTextField();
		add(textField_Rx_OD_Sphere, "cell 1 1,growx");
		textField_Rx_OD_Sphere.setColumns(8);
		
		textField_Rx_OD_Cyl = new JTextField();
		add(textField_Rx_OD_Cyl, "cell 2 1,growx");
		textField_Rx_OD_Cyl.setColumns(8);
		
		textField_Rx_OD_Axis = new JTextField();
		add(textField_Rx_OD_Axis, "cell 3 1,growx");
		textField_Rx_OD_Axis.setColumns(8);
		
		textField_Rx_OD_Add = new JTextField();
		add(textField_Rx_OD_Add, "cell 4 1,growx");
		textField_Rx_OD_Add.setColumns(8);
		
		JLabel lblOsRx = new JLabel("OS");
		add(lblOsRx, "cell 0 2,alignx trailing");
		
		textField_Rx_OS_Sphere = new JTextField();
		add(textField_Rx_OS_Sphere, "cell 1 2,growx");
		textField_Rx_OS_Sphere.setColumns(8);
		
		textField_Rx_OS_Cyl = new JTextField();
		add(textField_Rx_OS_Cyl, "cell 2 2,growx");
		textField_Rx_OS_Cyl.setColumns(8);
		
		textField_Rx_OS_Axis = new JTextField();
		add(textField_Rx_OS_Axis, "cell 3 2,growx");
		textField_Rx_OS_Axis.setColumns(8);
		
		textField_Rx_OS_Add = new JTextField();
		add(textField_Rx_OS_Add, "cell 4 2,growx");
		textField_Rx_OS_Add.setColumns(8);
		
		JLabel lblGlassesRxNotes = new JLabel("Rx Notes");
		add(lblGlassesRxNotes, "cell 0 3,alignx trailing");
		
		textField_GlassesRxNotes = new JTextField();
		add(textField_GlassesRxNotes, "cell 1 3 4 1,growx");
		textField_GlassesRxNotes.setColumns(20);
	}

	public GlassesRx createNewGlassesRx() {
		
		GlassesRx glsRx = new GlassesRx(
				
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
		
		return glsRx;
	}
}
