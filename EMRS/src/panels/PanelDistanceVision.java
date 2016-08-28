package panels;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import models.DistanceVision;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PanelDistanceVision extends JPanel {

	//private DistanceVision vision;
	
	private JTextField textField_DVODSC;
	private JTextField textField_DVOSSC;
	private JTextField textField_DVODCC;
	private JTextField textField_DVOSCC;

	/**
	 * Create the panel.
	 */
	public PanelDistanceVision() {

				setBorder(new TitledBorder(null, "Distance Vision", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				setLayout(new MigLayout("", "[grow]", "[][]"));
				
				JPanel panel_DVSC = new JPanel();
				add(panel_DVSC, "cell 0 0,growx");
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
				add(panel_DVCC, "cell 0 1,growx");
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
	}
	
	public void disableFields() {
		for (Component c : this.getComponents()) {
			if (c instanceof JTextField) {
				((JTextField) c).setEditable(false);
			}
		}
	}

	public DistanceVision createNewDistanceVision() {
		DistanceVision vtmp= new DistanceVision(
				textField_DVODSC.getText(),
				textField_DVOSSC.getText(),
				textField_DVODCC.getText(),
				textField_DVOSCC.getText()
				);
		return vtmp;
	}
}
