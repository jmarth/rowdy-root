package visitPanels;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import models.IOPMeasurement;
import models.MasterModel;
import net.miginfocom.swing.MigLayout;
import views.HomeView;
import views.viewinterface;

@SuppressWarnings("serial")
public class PanelIOP extends JPanel implements viewinterface {
	
	private int index;
	
	private JTextField textField_IOP_Value_OD;
	private JTextField textField_IOP_Type_OD;
	private JTextField textField_IOP_Notes_OD;
	private JTextField textField_IOP_Value_OS;
	private JTextField textField_IOP_Type_OS;
	private JTextField textField_IOP_Notes_OS;

	/**
	 * Create the panel.
	 */
	public PanelIOP(int index) {
		
		this.index = index;
		
		setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Intraocular Pressure", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, new Font("Tahoma", Font.BOLD, 20), new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[][grow][grow][grow]", "[][][]"));
		
		JLabel lblValue = new JLabel("Value");
		add(lblValue, "cell 1 0,alignx center");
		
		JLabel lblType = new JLabel("Type");
		add(lblType, "cell 2 0,alignx center");
		
		JLabel lblNotes = new JLabel("Notes");
		add(lblNotes, "cell 3 0,alignx center");
		
		JLabel lblOd_3 = new JLabel("OD");
		add(lblOd_3, "cell 0 1,alignx trailing");
		
		textField_IOP_Value_OD = new JTextField();
		add(textField_IOP_Value_OD, "cell 1 1,growx");
		textField_IOP_Value_OD.setColumns(8);
		
		textField_IOP_Type_OD = new JTextField();
		textField_IOP_Type_OD.setColumns(8);
		add(textField_IOP_Type_OD, "cell 2 1,growx");
		
		textField_IOP_Notes_OD = new JTextField();
		textField_IOP_Notes_OD.setColumns(10);
		add(textField_IOP_Notes_OD, "cell 3 1,growx");
		
		JLabel lblOs_3 = new JLabel("OS");
		add(lblOs_3, "cell 0 2,alignx trailing");
		
		textField_IOP_Value_OS = new JTextField();
		textField_IOP_Value_OS.setColumns(8);
		add(textField_IOP_Value_OS, "cell 1 2,growx");
		
		textField_IOP_Type_OS = new JTextField();
		textField_IOP_Type_OS.setColumns(8);
		add(textField_IOP_Type_OS, "cell 2 2,growx");
		
		textField_IOP_Notes_OS = new JTextField();
		textField_IOP_Notes_OS.setColumns(10);
		add(textField_IOP_Notes_OS, "cell 3 2,growx");
	}

	public IOPMeasurement createNewIOP() {
		
		IOPMeasurement iop = new IOPMeasurement(
				
				textField_IOP_Value_OD.getText(),
				textField_IOP_Type_OD.getText(),
				textField_IOP_Notes_OD.getText(),
				textField_IOP_Value_OS.getText(),
				textField_IOP_Type_OS.getText(),
				textField_IOP_Notes_OS.getText()
				
				);
		
		return iop;
	}
	
	public void setFields() {
		
//		IOPMeasurement i = getMasterModel().getCurrentPatientVisitList().get(index).getMyIOPList();
		//TODO
		/*textField_IOP_Value_OD.setText(iopCols.get(++i).toString());
		textField_IOP_Type_OD.setText(iopCols.get(++i).toString());
		textField_IOP_Notes_OD.setText(iopCols.get(++i).toString());
		textField_IOP_Value_OS.setText(iopCols.get(++i).toString());
		textField_IOP_Type_OS.setText(iopCols.get(++i).toString());
		textField_IOP_Notes_OS.setText(iopCols.get(++i).toString());*/
	}

	@Override
	public void HideallView() {
		
	}

	@Override
	public MasterModel getMasterModel() {
		return ((HomeView)this.getParent()).getMasterModel();
	}

	@Override
	public void ShowView() {
		
	}

	@Override
	public void reload() {
		
	}

	@Override
	public HomeView getHomeView() {
		return ((HomeView)this.getParent());
	}
	
	
}
