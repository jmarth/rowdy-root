package visitPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import models.MasterModel;
import models.Pupils;
import net.miginfocom.swing.MigLayout;
import views.HomeView;
import views.viewinterface;

@SuppressWarnings("serial")
public class PanelPupils extends JPanel implements viewinterface {
	
	private int index;
	
	private JCheckBox chckBx_NormalPupilsBoth;
	private JRadioButton rdbtn_RoundPupilsBoth;
	private JRadioButton rdbtn_IrregularPupilsBoth;
	private JComboBox comboBox_PupilsDiameterBoth;
	private JRadioButton rdbtn_Y_RAPDBoth;
	private JRadioButton rdbtn_N_RAPDBoth;
	private JRadioButton rdbtn_Y_SynechiaBoth;
	private JRadioButton rdbtn_N_SynechiaBoth;
	
	private JCheckBox chckBx_NormalPupilRight;
	private JRadioButton rdbtn_RoundPupilRight;
	private JRadioButton rdbtn_IrregularPupilsRight;
	private JComboBox comboBox_PupilDiameterRight;
	private JRadioButton rdbtn_Y_RAPDRight;
	private JRadioButton rdbtn_N_RAPDRight;
	private JRadioButton rdbtn_Y_SynechiaRight;
	private JRadioButton rdbtn_N_SynechiaRight;
	
	private JCheckBox chckBx_NormalPupilLeft;
	private JRadioButton rdbtn_RoundPupilLeft;
	private JRadioButton rdbtn_IrregularPupilLeft;
	private JComboBox comboBox_PupilDiameterLeft;
	private JRadioButton rdbtn_Y_RAPDLeft;
	private JRadioButton rdbtn_N_RAPDLeft;
	private JRadioButton rdbtn_Y_SynechiaLeft;
	private JRadioButton rdbtn_N_SynechiaLeft;

	private final NoneSelectedButtonGroup bgBothShape = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgRightShape = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgLeftShape = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgRAPDBoth = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgRAPDRight = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgRAPDLeft = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgSyneBoth = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgSyneRight = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgSyneLeft = new NoneSelectedButtonGroup();

	/**
	 * Create the panel.
	 */
	public PanelPupils(int index) {
		
		this.index = index;
		
		setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Pupils", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[grow]", "[][][]"));

		JPanel panel_PupilsBoth = new JPanel();
		panel_PupilsBoth.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(64, 64, 64)), "Both", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		add(panel_PupilsBoth, "cell 0 0,grow");

		chckBx_NormalPupilsBoth = new JCheckBox("Normal");

		chckBx_NormalPupilsBoth.setAlignmentX(Component.CENTER_ALIGNMENT);
		chckBx_NormalPupilsBoth.addActionListener(new NormalBothListener());

		JPanel panel_ShapeB = new JPanel();
		panel_ShapeB.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)), "Shape", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		rdbtn_RoundPupilsBoth = new JRadioButton("Round");
		bgBothShape.add(rdbtn_RoundPupilsBoth);

		rdbtn_IrregularPupilsBoth = new JRadioButton("Irregular");
		bgBothShape.add(rdbtn_IrregularPupilsBoth);
		panel_ShapeB.setLayout(new BoxLayout(panel_ShapeB, BoxLayout.Y_AXIS));
		panel_ShapeB.add(rdbtn_RoundPupilsBoth);
		panel_ShapeB.add(rdbtn_IrregularPupilsBoth);
		panel_PupilsBoth.setLayout(new BoxLayout(panel_PupilsBoth, BoxLayout.X_AXIS));
		panel_PupilsBoth.add(chckBx_NormalPupilsBoth);
		panel_PupilsBoth.add(panel_ShapeB);

		JPanel panel_DiameterB = new JPanel();
		panel_DiameterB
				.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)), "Diameter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PupilsBoth.add(panel_DiameterB);

		comboBox_PupilsDiameterBoth = new JComboBox();
		comboBox_PupilsDiameterBoth
				.setModel(new DefaultComboBoxModel(new String[] { "N/A", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
		panel_DiameterB.add(comboBox_PupilsDiameterBoth);

		JLabel lblMmBoth = new JLabel("mm");
		panel_DiameterB.add(lblMmBoth);

		JPanel panel_RAPDB = new JPanel();
		panel_RAPDB.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)), "RAPD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PupilsBoth.add(panel_RAPDB);
		panel_RAPDB.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		rdbtn_Y_RAPDBoth = new JRadioButton("Y");
		bgRAPDBoth.add(rdbtn_Y_RAPDBoth);
		panel_RAPDB.add(rdbtn_Y_RAPDBoth);

		rdbtn_N_RAPDBoth = new JRadioButton("N");
		bgRAPDBoth.add(rdbtn_N_RAPDBoth);
		panel_RAPDB.add(rdbtn_N_RAPDBoth);

		JPanel panel_SynechiaB = new JPanel();
		panel_SynechiaB.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)), "Synechia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PupilsBoth.add(panel_SynechiaB);
		panel_SynechiaB.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		rdbtn_Y_SynechiaBoth = new JRadioButton("Y");
		bgSyneBoth.add(rdbtn_Y_SynechiaBoth);
		panel_SynechiaB.add(rdbtn_Y_SynechiaBoth);

		rdbtn_N_SynechiaBoth = new JRadioButton("N");
		bgSyneBoth.add(rdbtn_N_SynechiaBoth);
		panel_SynechiaB.add(rdbtn_N_SynechiaBoth);

		JPanel panel_PupilsRight = new JPanel();
		panel_PupilsRight
				.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(64, 64, 64)), "Right", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		add(panel_PupilsRight, "cell 0 1,grow");
		panel_PupilsRight.setLayout(new BoxLayout(panel_PupilsRight, BoxLayout.X_AXIS));

		chckBx_NormalPupilRight = new JCheckBox("Normal");
		chckBx_NormalPupilRight.setAlignmentX(Component.CENTER_ALIGNMENT);
		chckBx_NormalPupilRight.addActionListener(new NormalRightListener());
		panel_PupilsRight.add(chckBx_NormalPupilRight);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)), "Shape", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PupilsRight.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		rdbtn_RoundPupilRight = new JRadioButton("Round");
		bgRightShape.add(rdbtn_RoundPupilRight);
		panel_1.add(rdbtn_RoundPupilRight);

		rdbtn_IrregularPupilsRight = new JRadioButton("Irregular");
		bgRightShape.add(rdbtn_IrregularPupilsRight);
		panel_1.add(rdbtn_IrregularPupilsRight);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)), "Diameter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PupilsRight.add(panel_2);

		comboBox_PupilDiameterRight = new JComboBox();
		comboBox_PupilDiameterRight
				.setModel(new DefaultComboBoxModel(new String[] { "N/A", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
		panel_2.add(comboBox_PupilDiameterRight);

		JLabel lbl_MmRight = new JLabel("mm");
		panel_2.add(lbl_MmRight);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)), "RAPD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PupilsRight.add(panel_3);

		rdbtn_Y_RAPDRight = new JRadioButton("Y");
		bgRAPDRight.add(rdbtn_Y_RAPDRight);
		panel_3.add(rdbtn_Y_RAPDRight);

		rdbtn_N_RAPDRight = new JRadioButton("N");
		bgRAPDRight.add(rdbtn_N_RAPDRight);
		panel_3.add(rdbtn_N_RAPDRight);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)), "Synechia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PupilsRight.add(panel_4);

		rdbtn_Y_SynechiaRight = new JRadioButton("Y");
		bgSyneRight.add(rdbtn_Y_SynechiaRight);
		panel_4.add(rdbtn_Y_SynechiaRight);

		rdbtn_N_SynechiaRight = new JRadioButton("N");
		bgSyneRight.add(rdbtn_N_SynechiaRight);
		panel_4.add(rdbtn_N_SynechiaRight);

		JPanel panel_PupilsLeft = new JPanel();
		panel_PupilsLeft.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(64, 64, 64)), "Left", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		add(panel_PupilsLeft, "cell 0 2,grow");
		panel_PupilsLeft.setLayout(new BoxLayout(panel_PupilsLeft, BoxLayout.X_AXIS));

		chckBx_NormalPupilLeft = new JCheckBox("Normal");
		chckBx_NormalPupilLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
		chckBx_NormalPupilLeft.addActionListener(new NormalLeftListener());
		panel_PupilsLeft.add(chckBx_NormalPupilLeft);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)), "Shape", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PupilsLeft.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		rdbtn_RoundPupilLeft = new JRadioButton("Round");
		bgLeftShape.add(rdbtn_RoundPupilLeft);
		panel_5.add(rdbtn_RoundPupilLeft);

		rdbtn_IrregularPupilLeft = new JRadioButton("Irregular");
		bgLeftShape.add(rdbtn_IrregularPupilLeft);
		panel_5.add(rdbtn_IrregularPupilLeft);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)), "Diameter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PupilsLeft.add(panel_6);

		comboBox_PupilDiameterLeft = new JComboBox();
		comboBox_PupilDiameterLeft
				.setModel(new DefaultComboBoxModel(new String[] { "N/A", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
		panel_6.add(comboBox_PupilDiameterLeft);

		JLabel label_1 = new JLabel("mm");
		panel_6.add(label_1);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)), "RAPD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PupilsLeft.add(panel_7);

		rdbtn_Y_RAPDLeft = new JRadioButton("Y");
		bgRAPDLeft.add(rdbtn_Y_RAPDLeft);
		panel_7.add(rdbtn_Y_RAPDLeft);

		rdbtn_N_RAPDLeft = new JRadioButton("N");
		bgRAPDLeft.add(rdbtn_N_RAPDLeft);
		panel_7.add(rdbtn_N_RAPDLeft);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)), "Synichia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PupilsLeft.add(panel_8);

		rdbtn_Y_SynechiaLeft = new JRadioButton("Y");
		bgSyneLeft.add(rdbtn_Y_SynechiaLeft);
		panel_8.add(rdbtn_Y_SynechiaLeft);

		rdbtn_N_SynechiaLeft = new JRadioButton("N");
		bgSyneLeft.add(rdbtn_N_SynechiaLeft);
		panel_8.add(rdbtn_N_SynechiaLeft);

	}

	private class NormalBothListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (chckBx_NormalPupilsBoth.isSelected()) {
				rdbtn_RoundPupilsBoth.setSelected(true);
				comboBox_PupilsDiameterBoth.setSelectedIndex(2);
				rdbtn_N_RAPDBoth.setSelected(true);
				rdbtn_N_SynechiaBoth.setSelected(true);
			} else {
				rdbtn_RoundPupilsBoth.setSelected(true);
				comboBox_PupilsDiameterBoth.setSelectedIndex(0);
				rdbtn_N_RAPDBoth.setSelected(true);
				rdbtn_N_SynechiaBoth.setSelected(true);
			}

		}

	}

	private class NormalRightListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (chckBx_NormalPupilRight.isSelected()) {

				rdbtn_RoundPupilRight.setSelected(true);
				comboBox_PupilDiameterRight.setSelectedIndex(2);
				rdbtn_N_RAPDRight.setSelected(true);
				rdbtn_N_SynechiaRight.setSelected(true);
			} else {
				
			}
		}

	}

	private class NormalLeftListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (chckBx_NormalPupilLeft.isSelected()) {
				rdbtn_RoundPupilLeft.setSelected(true);
				comboBox_PupilDiameterLeft.setSelectedIndex(2);
				rdbtn_N_RAPDLeft.setSelected(true);
				rdbtn_N_SynechiaLeft.setSelected(true);
				
			} else {
				
			}
		}

	}

	public Pupils createNewPupils() {

		String pDiaBoth = (String) comboBox_PupilsDiameterBoth.getSelectedItem();
		String pDiaRight = (String) comboBox_PupilDiameterRight.getSelectedItem();
		String pDiaLeft = (String) comboBox_PupilDiameterLeft.getSelectedItem();

		Pupils p = new Pupils(

				chckBx_NormalPupilsBoth.isSelected() ? 1 : 0,
				getSelectedButtonText(bgBothShape) == null ? "" : getSelectedButtonText(bgBothShape),
				pDiaBoth,
				(rdbtn_Y_RAPDBoth.isSelected() ? 1 : rdbtn_N_RAPDBoth.isSelected() ? 0 : -1),
				(rdbtn_Y_SynechiaBoth.isSelected() ? 1 : rdbtn_N_SynechiaBoth.isSelected() ? 0 : -1),
				
				chckBx_NormalPupilRight.isSelected() ? 1 : 0,
				getSelectedButtonText(bgRightShape)== null ? "" : getSelectedButtonText(bgRightShape),
				pDiaRight,
				(rdbtn_Y_RAPDRight.isSelected() ? 1 : rdbtn_N_RAPDRight.isSelected() ? 0 : -1),
				(rdbtn_Y_SynechiaRight.isSelected() ? 1 : rdbtn_N_SynechiaRight.isSelected() ? 0 : -1),
				
				chckBx_NormalPupilLeft.isSelected() ? 1 : 0,
				getSelectedButtonText(bgLeftShape)== null ? "" : getSelectedButtonText(bgLeftShape),
				pDiaLeft,
				(rdbtn_Y_RAPDLeft.isSelected() ? 1 : rdbtn_N_RAPDLeft.isSelected() ? 0 : -1),
				(rdbtn_Y_SynechiaLeft.isSelected() ? 1 : rdbtn_N_SynechiaLeft.isSelected() ? 0 : -1)

		);

		return p;
	}

	/**
	 * Gets the selected button's name from a button group.
	 * 
	 * @param buttonGroup
	 * @return The string of the name of the selected radio button of the
	 *         buttonGroup
	 */
	public String getSelectedButtonText(NoneSelectedButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}
	
	public void setFields() {
		
		Pupils p = getMasterModel().getCurrentPatientVisitList().get(index).getMyPupils();
		
		
		
		chckBx_NormalPupilsBoth.setSelected(p.isBothPupilsNormal() == 1? true : false);
		
		if (p.isBothPupilsNormal() == 1) {
			rdbtn_RoundPupilsBoth.setSelected(true);
		} else {
			rdbtn_IrregularPupilsBoth.setSelected(true);
		}
		
		comboBox_PupilsDiameterBoth.setSelectedItem(p.getBothDiameter());
		
		if (p.isBothRAPD() == 1) {
			rdbtn_Y_RAPDBoth.setSelected(true);
		} else {
			rdbtn_N_RAPDBoth.setSelected(true);
		}
		
		if (p.isBothSynechia() == 1) {
			rdbtn_Y_SynechiaBoth.setSelected(true);
		} else {
			rdbtn_N_SynechiaBoth.setSelected(true);
		}
		
		
		
		chckBx_NormalPupilRight.setSelected(p.isRightPupilNormal() == 1? true:false);
		
		if (p.isRightPupilNormal() == 1) {
			rdbtn_RoundPupilRight.setSelected(true);
		} else {
			rdbtn_IrregularPupilsRight.setSelected(true);
		}
		
		comboBox_PupilDiameterRight.setSelectedItem(p.getRightDiameter());

		if (p.isRightRAPD() == 1) {
			rdbtn_Y_RAPDRight.setSelected(true);
		} else {
			rdbtn_N_RAPDRight.setSelected(true);
		}
		
		if (p.isRightSynechia() == 1) {
			rdbtn_Y_SynechiaRight.setSelected(true);
		} else {
			rdbtn_N_SynechiaRight.setSelected(true);
		}
		
		
		
		chckBx_NormalPupilLeft.setSelected(p.isLeftPupilNormal() == 1? true:false);
		
		if (p.isLeftPupilNormal() == 1) {
			rdbtn_RoundPupilLeft.setSelected(true);
		} else {
			rdbtn_IrregularPupilLeft.setSelected(true);
		}
		
		comboBox_PupilDiameterLeft.setSelectedItem(p.getLeftDiameter());

		if (p.isLeftRAPD() == 1) {
			rdbtn_Y_RAPDLeft.setSelected(true);
		} else {
			rdbtn_N_RAPDLeft.setSelected(true);
		}
		
		if (p.isLeftSynechia() == 1) {
			rdbtn_Y_SynechiaLeft.setSelected(true);
		} else {
			rdbtn_N_SynechiaLeft.setSelected(true);
		}
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
