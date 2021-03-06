package visitPanels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import models.CL;
import models.DistanceVision;
import models.MasterModel;
import net.miginfocom.swing.MigLayout;
import views.HomeView;
import views.viewinterface;

@SuppressWarnings("serial")
public class PanelDistanceVision extends JPanel implements viewinterface {

	private int index;
	
	private JTextField textField_DVODSC;
	private JTextField textField_DVOSSC;
	private JTextField textField_DVODCC;
	private JTextField textField_DVOSCC;

	/**
	 * Create the panel.
	 */
	public PanelDistanceVision(int index) {
		
		this.index = index;
		
		setBackground(CL.turq);
		setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Distance Vision", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, new Font("Tahoma", Font.BOLD, 20), new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[grow]", "[][]"));
		
		JPanel panel_DVSC = new JPanel();
		panel_DVSC.setBackground(new Color(238, 238, 238));
		add(panel_DVSC, "cell 0 0,growx");
		panel_DVSC.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(64, 64, 64)), "Without Glasses", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
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
		panel_DVCC.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(64, 64, 64)), "With Glasses", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel_DVCC.setLayout(new MigLayout("", "[][][grow]", "[][]"));
		panel_DVCC.setBackground(new Color(238, 238, 238));
		
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

	@Override
	public void HideallView() {
		//TODO
	}

	@Override
	public MasterModel getMasterModel() {
		return getHomeView().getMasterModel();
	}

	@Override
	public void ShowView() {
		//TODO
	}

	@Override
	public void reload() {
		DistanceVision dv = getMasterModel().getvL().getMyList().get(index).getMyDV();
		if (dv == null)
			System.out.println("Null?");
		textField_DVODSC.setText(dv.getDVODSC());
		textField_DVOSSC.setText(dv.getDVOSSC());
		textField_DVODCC.setText(dv.getDVODCC());
		textField_DVOSCC.setText(dv.getDVOSCC());
	}

	@Override
	public HomeView getHomeView() {
		return ((PanelVision)this.getParent()).getHomeView();
	}

	public DistanceVision newDV() {
		DistanceVision dv = new DistanceVision (
				textField_DVODSC.getText(),
				textField_DVOSSC.getText(),
				textField_DVODCC.getText(),
				textField_DVOSCC.getText()
				);
		return dv;
	}

}
