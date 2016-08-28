package panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import models.Pupils;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;

@SuppressWarnings("serial")
public class PanelPupils extends JPanel {

	private JCheckBox chckbx_NormalPupilsBoth;
	private JRadioButton rdbtn_RoundPupilsBoth;
	private JRadioButton rdbtn_IrregularPupilsBoth;
	private JComboBox comboBox_PupilsDiameterBoth;
	private JRadioButton rdbtn_Y_RAPDBoth;
	private JRadioButton rdbtn_N_RAPDBoth;
	private JRadioButton rdbtn_Y_SynechiaBoth;
	private JRadioButton rdbtn_N_SynechiaBoth;
	private JCheckBox chckBx_NormalPupilsRight;
	private JRadioButton rdbtn_RoundPupilRight;
	private JRadioButton rdbtn_IrregularPupilsRight;
	private JComboBox comboBox_PupilDiameterRight;
	private JRadioButton rdbtn_Y_RAPDRight;
	private JRadioButton rdbtn_N_RAPDRight;
	private JRadioButton rdbtn_Y_SynechiaRight;
	private JRadioButton rdbtn_N_SynechiaRight;
	private JCheckBox chkbx_NormalPupilLeft;
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
	public PanelPupils() {

		setBorder(new TitledBorder(null, "Pupils", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[grow]", "[][][]"));

		JPanel panel_PupilsBoth = new JPanel();
		panel_PupilsBoth.setBorder(new TitledBorder(null, "Both", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_PupilsBoth, "cell 0 0,grow");

		chckbx_NormalPupilsBoth = new JCheckBox("Normal");

		chckbx_NormalPupilsBoth.setAlignmentX(Component.CENTER_ALIGNMENT);
		chckbx_NormalPupilsBoth.addActionListener(new NormalBothListener());

		JPanel panel_ShapeB = new JPanel();
		panel_ShapeB.setBorder(new TitledBorder(null, "Shape", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		rdbtn_RoundPupilsBoth = new JRadioButton("Round");
		bgBothShape.add(rdbtn_RoundPupilsBoth);

		rdbtn_IrregularPupilsBoth = new JRadioButton("Irregular");
		bgBothShape.add(rdbtn_IrregularPupilsBoth);
		panel_ShapeB.setLayout(new BoxLayout(panel_ShapeB, BoxLayout.Y_AXIS));
		panel_ShapeB.add(rdbtn_RoundPupilsBoth);
		panel_ShapeB.add(rdbtn_IrregularPupilsBoth);
		panel_PupilsBoth.setLayout(new BoxLayout(panel_PupilsBoth, BoxLayout.X_AXIS));
		panel_PupilsBoth.add(chckbx_NormalPupilsBoth);
		panel_PupilsBoth.add(panel_ShapeB);

		JPanel panel_DiameterB = new JPanel();
		panel_DiameterB
				.setBorder(new TitledBorder(null, "Diameter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_PupilsBoth.add(panel_DiameterB);

		comboBox_PupilsDiameterBoth = new JComboBox();
		comboBox_PupilsDiameterBoth
				.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
		panel_DiameterB.add(comboBox_PupilsDiameterBoth);

		JLabel lblMmBoth = new JLabel("mm");
		panel_DiameterB.add(lblMmBoth);

		JPanel panel_RAPDB = new JPanel();
		panel_RAPDB.setBorder(new TitledBorder(null, "RAPD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_PupilsBoth.add(panel_RAPDB);
		panel_RAPDB.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		rdbtn_Y_RAPDBoth = new JRadioButton("Y");
		bgRAPDBoth.add(rdbtn_Y_RAPDBoth);
		panel_RAPDB.add(rdbtn_Y_RAPDBoth);

		rdbtn_N_RAPDBoth = new JRadioButton("N");
		bgRAPDBoth.add(rdbtn_N_RAPDBoth);
		panel_RAPDB.add(rdbtn_N_RAPDBoth);

		JPanel panel_SynechiaB = new JPanel();
		panel_SynechiaB.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Synechia",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
				.setBorder(new TitledBorder(null, "Right", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_PupilsRight, "cell 0 1,grow");
		panel_PupilsRight.setLayout(new BoxLayout(panel_PupilsRight, BoxLayout.X_AXIS));

		chckBx_NormalPupilsRight = new JCheckBox("Normal");
		chckBx_NormalPupilsRight.setAlignmentX(Component.CENTER_ALIGNMENT);
		chckBx_NormalPupilsRight.addActionListener(new NormalRightListener());
		panel_PupilsRight.add(chckBx_NormalPupilsRight);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Shape", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_PupilsRight.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		rdbtn_RoundPupilRight = new JRadioButton("Round");
		bgRightShape.add(rdbtn_RoundPupilRight);
		panel_1.add(rdbtn_RoundPupilRight);

		rdbtn_IrregularPupilsRight = new JRadioButton("Irregular");
		bgRightShape.add(rdbtn_IrregularPupilsRight);
		panel_1.add(rdbtn_IrregularPupilsRight);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Diameter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_PupilsRight.add(panel_2);

		comboBox_PupilDiameterRight = new JComboBox();
		comboBox_PupilDiameterRight
				.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
		panel_2.add(comboBox_PupilDiameterRight);

		JLabel lbl_MmRight = new JLabel("mm");
		panel_2.add(lbl_MmRight);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "RAPD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_PupilsRight.add(panel_3);

		rdbtn_Y_RAPDRight = new JRadioButton("Y");
		bgRAPDRight.add(rdbtn_Y_RAPDRight);
		panel_3.add(rdbtn_Y_RAPDRight);

		rdbtn_N_RAPDRight = new JRadioButton("N");
		bgRAPDRight.add(rdbtn_N_RAPDRight);
		panel_3.add(rdbtn_N_RAPDRight);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Synechia",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_PupilsRight.add(panel_4);

		rdbtn_Y_SynechiaRight = new JRadioButton("Y");
		bgSyneRight.add(rdbtn_Y_SynechiaRight);
		panel_4.add(rdbtn_Y_SynechiaRight);

		rdbtn_N_SynechiaRight = new JRadioButton("N");
		bgSyneRight.add(rdbtn_N_SynechiaRight);
		panel_4.add(rdbtn_N_SynechiaRight);

		JPanel panel_PupilsLeft = new JPanel();
		panel_PupilsLeft.setBorder(new TitledBorder(null, "Left", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_PupilsLeft, "cell 0 2,grow");
		panel_PupilsLeft.setLayout(new BoxLayout(panel_PupilsLeft, BoxLayout.X_AXIS));

		chkbx_NormalPupilLeft = new JCheckBox("Normal");
		chkbx_NormalPupilLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
		chkbx_NormalPupilLeft.addActionListener(new NormalLeftListener());
		panel_PupilsLeft.add(chkbx_NormalPupilLeft);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Shape", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_PupilsLeft.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		rdbtn_RoundPupilLeft = new JRadioButton("Round");
		bgLeftShape.add(rdbtn_RoundPupilLeft);
		panel_5.add(rdbtn_RoundPupilLeft);

		rdbtn_IrregularPupilLeft = new JRadioButton("Irregular");
		bgLeftShape.add(rdbtn_IrregularPupilLeft);
		panel_5.add(rdbtn_IrregularPupilLeft);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Diameter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_PupilsLeft.add(panel_6);

		comboBox_PupilDiameterLeft = new JComboBox();
		comboBox_PupilDiameterLeft
				.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
		panel_6.add(comboBox_PupilDiameterLeft);

		JLabel label_1 = new JLabel("mm");
		panel_6.add(label_1);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "RAPD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_PupilsLeft.add(panel_7);

		rdbtn_Y_RAPDLeft = new JRadioButton("Y");
		bgRAPDLeft.add(rdbtn_Y_RAPDLeft);
		panel_7.add(rdbtn_Y_RAPDLeft);

		rdbtn_N_RAPDLeft = new JRadioButton("N");
		bgRAPDLeft.add(rdbtn_N_RAPDLeft);
		panel_7.add(rdbtn_N_RAPDLeft);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Synichia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			if (chckbx_NormalPupilsBoth.isSelected()) {
				rdbtn_RoundPupilsBoth.setSelected(true);
				comboBox_PupilsDiameterBoth.setSelectedIndex(2);
				rdbtn_N_RAPDBoth.setSelected(true);
				rdbtn_N_SynechiaBoth.setSelected(true);
			}

		}

	}

	private class NormalRightListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (chckBx_NormalPupilsRight.isSelected()) {

				rdbtn_RoundPupilRight.setSelected(true);
				comboBox_PupilDiameterRight.setSelectedIndex(2);
				rdbtn_N_RAPDRight.setSelected(true);
				rdbtn_N_SynechiaRight.setSelected(true);
			}
		}

	}

	private class NormalLeftListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (chkbx_NormalPupilLeft.isSelected()) {
				rdbtn_RoundPupilLeft.setSelected(true);
				comboBox_PupilDiameterLeft.setSelectedIndex(2);
				rdbtn_N_RAPDLeft.setSelected(true);
				rdbtn_N_SynechiaLeft.setSelected(true);
			}
		}

	}

	public Pupils createNewPupils() {

		String pDiaBoth = (String) comboBox_PupilsDiameterBoth.getSelectedItem();
		String pDiaRight = (String) comboBox_PupilDiameterRight.getSelectedItem();
		String pDiaLeft = (String) comboBox_PupilDiameterLeft.getSelectedItem();

		Pupils p = new Pupils(

				chckbx_NormalPupilsBoth.isSelected() ? 1 : 0,
				getSelectedButtonText(bgBothShape),
				pDiaBoth,
				(rdbtn_Y_RAPDBoth.isSelected() ? 1 : rdbtn_N_RAPDBoth.isSelected() ? 0 : -1),
				(rdbtn_Y_SynechiaBoth.isSelected() ? 1 : rdbtn_N_SynechiaBoth.isSelected() ? 0 : -1),
				
				chckBx_NormalPupilsRight.isSelected() ? 1 : 0,
				getSelectedButtonText(bgRightShape),
				pDiaRight,
				(rdbtn_Y_RAPDRight.isSelected() ? 1 : rdbtn_N_RAPDRight.isSelected() ? 0 : -1),
				(rdbtn_Y_SynechiaRight.isSelected() ? 1 : rdbtn_N_SynechiaRight.isSelected() ? 0 : -1),
				
				chkbx_NormalPupilLeft.isSelected() ? 1 : 0,
				getSelectedButtonText(bgLeftShape),
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

	public void setFields(ArrayList<Object> pupilsCols) {

		int i = -1;

		if (pupilsCols.get(i + 1).toString().equals("1")) {
			chckbx_NormalPupilsBoth.setSelected(true);
		}

		String temp = pupilsCols.get(i + 1).toString();

		if (temp.equals("Round"))
			rdbtn_RoundPupilsBoth.setSelected(true);
		else if (temp.equals("Irregular"))
			rdbtn_IrregularPupilsBoth.setSelected(true);
		else {
			
		}

		temp = pupilsCols.get(i + 1).toString();

		if (temp != null)
			comboBox_PupilsDiameterBoth.setSelectedItem(temp);

		temp = pupilsCols.get(i + 1).toString();

		if (temp.equals("1"))
			rdbtn_Y_RAPDBoth.setSelected(true);
		else if (temp.equals("0"))
			rdbtn_N_RAPDBoth.setSelected(true);
		else {
			
		}

		temp = pupilsCols.get(i + 1).toString();

		if (temp.equals("1"))
			rdbtn_Y_SynechiaBoth.setSelected(true);
		else if (temp.equals("0"))
			rdbtn_N_SynechiaBoth.setSelected(true);
		else {
			System.out.println("WHAT???");
		}

		if (pupilsCols.get(i + 1).toString().equals("1"))
			chckBx_NormalPupilsRight.setSelected(true);

		temp = pupilsCols.get(i + 1).toString();

		if (temp.equals("Round"))
			rdbtn_RoundPupilRight.setSelected(true);
		else if (temp.equals("Irregular"))
			rdbtn_IrregularPupilsRight.setSelected(true);
		else {
			
		}

		temp = pupilsCols.get(i + 1).toString();

		if (temp != null)
			comboBox_PupilDiameterRight.setSelectedItem(temp);

		temp = pupilsCols.get(i + 1).toString();

		if (temp.equals("1"))
			rdbtn_Y_RAPDRight.setSelected(true);
		else if (temp.equals("0"))
			rdbtn_N_RAPDRight.setSelected(true);
		else {
			
		}

		temp = pupilsCols.get(i + 1).toString();

		if (temp.equals("1"))
			rdbtn_Y_SynechiaRight.setSelected(true);
		else if (temp.equals("0"))
			rdbtn_N_SynechiaRight.setSelected(true);
		else {
			
		}

		if (pupilsCols.get(i + 1).toString().equals("1"))
			chkbx_NormalPupilLeft.setSelected(true);

		temp = pupilsCols.get(i + 1).toString();

		if (temp.equals("Round"))
			rdbtn_RoundPupilLeft.setSelected(true);
		else if (temp.equals("Irregular"))
			rdbtn_IrregularPupilLeft.setSelected(true);
		else {
			
		}

		temp = pupilsCols.get(i + 1).toString();

		if (temp != null)
			comboBox_PupilDiameterLeft.setSelectedItem(temp);

		temp = pupilsCols.get(i + 1).toString();

		if (temp.equals("1"))
			rdbtn_Y_RAPDLeft.setSelected(true);
		else if (temp.equals("0"))
			rdbtn_N_RAPDLeft.setSelected(true);
		else {
			
		}

		temp = pupilsCols.get(i + 1).toString();

		if (temp.equals("1"))
			rdbtn_Y_SynechiaLeft.setSelected(true);
		else if (temp.equals("0"))
			rdbtn_N_SynechiaLeft.setSelected(true);
		else {
			
		}

	}
}