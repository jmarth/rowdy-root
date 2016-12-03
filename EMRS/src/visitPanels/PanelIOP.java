package visitPanels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import models.IOP;
import models.MasterModel;
import net.miginfocom.swing.MigLayout;
import views.HomeView;
import views.viewinterface;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class PanelIOP extends JPanel implements viewinterface {
	
	private int index;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelIOP(int index) {
		
		this.index = index;
		
		setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Intraocular Pressure", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, new Font("Tahoma", Font.BOLD, 20), new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0,grow");
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Time", "Eye", "Type", "Measurement"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(0).setMinWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setMinWidth(50);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(50);
		panel.add(table);
	}

	public IOP createNewIOP() {
		
//		IOP iop = new IOP(
//				
//				textField_IOP_Value_OD.getText(),
//				textField_IOP_Type_OD.getText(),
//				textField_IOP_Notes_OD.getText(),
//				textField_IOP_Value_OS.getText()
//				
//				);
		
		return null;
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
		//TODO
	}

	@Override
	public MasterModel getMasterModel() {
		return ((HomeView)this.getParent()).getMasterModel();
	}

	@Override
	public void ShowView() {
		//TODO
	}

	@Override
	public void reload() {
		
	}

	@Override
	public HomeView getHomeView() {
		return ((HomeView)this.getParent());
	}
	
	
}
