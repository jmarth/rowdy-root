package visitPanels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import models.CL;
import models.GlassesRx;
import models.MasterModel;
import net.miginfocom.swing.MigLayout;
import views.HomeView;
import views.viewinterface;

@SuppressWarnings("serial")
public class PanelGlassesRx extends JPanel implements viewinterface {
	
	private int index;

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
	public PanelGlassesRx(int index) {
		
		this.index = index;
		setBackground(CL.turq);
		setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Glasses Rx", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, new Font("Tahoma", Font.BOLD, 20), new Color(0, 0, 0)));
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
	
	public void setFields() {
		GlassesRx g = getMasterModel().getCurrentPatientVisitList().get(index).getMyGlsRx();
		
		textField_Rx_OD_Sphere.setText(g.getRx_OD_Sphere());
		textField_Rx_OD_Cyl.setText(g.getRx_OD_Cyl());
		textField_Rx_OD_Axis.setText(g.getRx_OD_Axis());
		textField_Rx_OD_Add.setText(g.getRx_OD_Add());
		textField_Rx_OS_Sphere.setText(g.getRx_OS_Sphere());
		textField_Rx_OS_Cyl.setText(g.getRx_OS_Cyl());
		textField_Rx_OS_Axis.setText(g.getRx_OS_Axis());
		textField_Rx_OS_Add.setText(g.getRx_OS_Add());
		textField_GlassesRxNotes.setText(g.getGlassesRxNotes());
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
		return ((PanelVision)this.getParent()).getHomeView();
	}

	public GlassesRx newGRx() {
		GlassesRx g = new GlassesRx(
				textField_Rx_OD_Sphere.getText(),
				textField_Rx_OD_Cyl.getText(),
				textField_Rx_OD_Axis.getText(),
				textField_Rx_OD_Add.getText(),
				textField_Rx_OS_Sphere.getText(),
				textField_Rx_OS_Cyl.getText(),
				textField_Rx_OS_Axis.getText(),
				textField_Rx_OS_Add.getText(),
				textField_GlassesRxNotes.getText()
				);
		return g;
	}
}