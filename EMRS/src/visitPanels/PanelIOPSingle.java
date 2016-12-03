package visitPanels;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PanelIOPSingle extends JPanel {
	private JTextField textField;
	
	
	public PanelIOPSingle () {
		createView();
	}
	
	public void createView() {

		setBorder(new TitledBorder(null, "IOP Measurement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[][][][grow,fill]", "[fill]"));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Eye", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, "cell 0 0,aligny center");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JRadioButton rdbtnOd = new JRadioButton("OD");
		panel.add(rdbtnOd);
		
		JRadioButton rdbtnOs = new JRadioButton("OS");
		panel.add(rdbtnOs);
		
		JRadioButton rdbtnBoth = new JRadioButton("Both");
		panel.add(rdbtnBoth);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1, "cell 1 0");
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JRadioButton rdbtnMonitored = new JRadioButton("Monitored");
		panel_1.add(rdbtnMonitored);
		
		JRadioButton rdbtnPostCompression = new JRadioButton("Post Compression");
		panel_1.add(rdbtnPostCompression);
		
		JRadioButton rdbtnPostParacentesis = new JRadioButton("Post Paracentesis");
		panel_1.add(rdbtnPostParacentesis);
		
		JRadioButton rdbtnPostLaser = new JRadioButton("Post Laser");
		panel_1.add(rdbtnPostLaser);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Measurement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_2, "cell 2 0,grow");
		panel_2.setLayout(new MigLayout("", "[]", "[]"));
		
		textField = new JTextField();
		panel_2.add(textField, "cell 0 0,growx");
		textField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, "cell 3 0,grow");
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JButton btnAdd = new JButton("Add");
		panel_3.add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		panel_3.add(btnCancel);
	}

}
