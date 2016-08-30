package panels;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import models.AnteriorChamber;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

@SuppressWarnings("serial")
public class PanelAC extends JPanel {

	private JCheckBox chkbx_ACODNormal;
	private JCheckBox chkbx_ACOSNormal;

	private JRadioButton rdbtn_ACDepthOD1;
	private JRadioButton rdbtn_ACDepthOD2;
	private JRadioButton rdbtn_ACDepthOD3;
	private JRadioButton rdbtn_ACDepthOD4;

	private JRadioButton rdbtn_ACDepthOS1;
	private JRadioButton rdbtn_ACDepthOS2;
	private JRadioButton rdbtn_ACDepthOS3;
	private JRadioButton rdbtn_ACDepthOS4;

	private JRadioButton rdbtn_ACAngleODOpen;
	private JRadioButton rdbtn_ACAngleODClosed;

	private JRadioButton rdbtn_ACAngleOSOpen;
	private JRadioButton rdbtn_ACAngleOSClosed;

	private JRadioButton rdbtn_N_PASOD;
	private JRadioButton rdbtn_Y_PASOD;
	private JRadioButton rdbtn_N_PASOS;
	private JRadioButton rdbtn_Y_PASOS;

	private JRadioButton rdbtn_ACODKP1;
	private JRadioButton rdbtn_ACODKP2;
	private JRadioButton rdbtn_ACODKP3;
	private JRadioButton rdbtn_ACODKP4;

	private JRadioButton rdbtn_ACOSKP1;
	private JRadioButton rdbtn_ACOSKP2;
	private JRadioButton rdbtn_ACOSKP3;
	private JRadioButton rdbtn_ACOSKP4;

	private JRadioButton rdbtn_ShuntOD;
	private JRadioButton rdbtn_ScarringOD;
	private JRadioButton rdbtn_TraumaOD;
	private JRadioButton rdbtn_BlebOD;

	private JRadioButton rdbtn_ShuntOS;
	private JRadioButton rdbtn_ScarringOS;
	private JRadioButton rdbtn_TraumaOS;
	private JRadioButton rdbtn_BlebOS;

	private JRadioButton rdbtn_VascularOD;
	private JRadioButton rdbtn_AvascularOD;
	private JRadioButton rdbtn_BlebOD1;
	private JRadioButton rdbtn_BlebOD2;
	private JRadioButton rdbtn_BlebOD3;
	private JRadioButton rdbtn_BlebOD4;

	private JRadioButton rdbtn_AvascularOS;
	private JRadioButton rdbtn_VascularOS;
	private JRadioButton rdbtn_BlebOS1;
	private JRadioButton rdbtn_BlebOS2;
	private JRadioButton rdbtn_BlebOS3;
	private JRadioButton rdbtn_BlebOS4;

	private JRadioButton rdbtn_Y_KSpindleOD;
	private JRadioButton rdbtn_N_KSpindleOD;

	private JRadioButton rdbtn_Y_KSpindleOS;
	private JRadioButton rdbtn_N_KSpindleOS;
	
	private final NoneSelectedButtonGroup bgODDepth = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgODAngle = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgODPAS = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgODKP = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgODVascular = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgODBleb = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgODKSpin = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSDepth = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSAngle = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSPAS = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSKP = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSVascular = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSBleb = new NoneSelectedButtonGroup();
	private final NoneSelectedButtonGroup bgOSKSpin = new NoneSelectedButtonGroup();

	/**
	 * Create the panel.
	 */
	public PanelAC() {

		setBorder(new TitledBorder(null, "Anterior Chamber", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		setLayout(new MigLayout("", "[grow]", "[][]"));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "OD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][]"));

		chkbx_ACODNormal = new JCheckBox("Normal");
		chkbx_ACODNormal.addActionListener(new ACODNormalListener());
		panel.add(chkbx_ACODNormal, "cell 0 0,aligny top");
		chkbx_ACODNormal.setAlignmentY(0.0f);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Depth", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2, "cell 1 0,grow");
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		rdbtn_ACDepthOD1 = new JRadioButton("+1");
		bgODDepth.add(rdbtn_ACDepthOD1);
		panel_2.add(rdbtn_ACDepthOD1);

		rdbtn_ACDepthOD2 = new JRadioButton("+2");
		bgODDepth.add(rdbtn_ACDepthOD2);
		panel_2.add(rdbtn_ACDepthOD2);

		rdbtn_ACDepthOD3 = new JRadioButton("+3");
		bgODDepth.add(rdbtn_ACDepthOD3);
		panel_2.add(rdbtn_ACDepthOD3);

		rdbtn_ACDepthOD4 = new JRadioButton("+4");
		bgODDepth.add(rdbtn_ACDepthOD4);
		panel_2.add(rdbtn_ACDepthOD4);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Angle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_3, "cell 2 0,grow");
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		rdbtn_ACAngleODOpen = new JRadioButton("Open");
		bgODAngle.add(rdbtn_ACAngleODOpen);
		panel_3.add(rdbtn_ACAngleODOpen);

		rdbtn_ACAngleODClosed = new JRadioButton("Closed");
		bgODAngle.add(rdbtn_ACAngleODClosed);
		panel_3.add(rdbtn_ACAngleODClosed);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "PAS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_4, "cell 3 0,grow");
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		rdbtn_Y_PASOD = new JRadioButton("Present");
		bgODPAS.add(rdbtn_Y_PASOD);
		panel_4.add(rdbtn_Y_PASOD);

		rdbtn_N_PASOD = new JRadioButton("Absent");
		bgODPAS.add(rdbtn_N_PASOD);
		panel_4.add(rdbtn_N_PASOD);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "KP", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_5, "cell 0 1,grow");
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		rdbtn_ACODKP1 = new JRadioButton("+1");
		bgODKP.add(rdbtn_ACODKP1);
		panel_5.add(rdbtn_ACODKP1);

		rdbtn_ACODKP2 = new JRadioButton("+2");
		bgODKP.add(rdbtn_ACODKP2);
		panel_5.add(rdbtn_ACODKP2);

		rdbtn_ACODKP3 = new JRadioButton("+3");
		bgODKP.add(rdbtn_ACODKP3);
		panel_5.add(rdbtn_ACODKP3);

		rdbtn_ACODKP4 = new JRadioButton("+4");
		bgODKP.add(rdbtn_ACODKP4);
		panel_5.add(rdbtn_ACODKP4);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Other", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_6, "cell 1 1,grow");
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));

		rdbtn_ShuntOD = new JRadioButton("Shunt");
		panel_6.add(rdbtn_ShuntOD);

		rdbtn_ScarringOD = new JRadioButton("Scarring");
		panel_6.add(rdbtn_ScarringOD);

		rdbtn_TraumaOD = new JRadioButton("Trauma");
		panel_6.add(rdbtn_TraumaOD);

		rdbtn_BlebOD = new JRadioButton("Bleb");
		panel_6.add(rdbtn_BlebOD);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Bleb", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_7, "cell 2 1,grow");
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));

		Box horizontalBox_6 = Box.createHorizontalBox();
		panel_7.add(horizontalBox_6);

		rdbtn_VascularOD = new JRadioButton("Vascular");
		bgODVascular.add(rdbtn_VascularOD);
		horizontalBox_6.add(rdbtn_VascularOD);

		rdbtn_AvascularOD = new JRadioButton("Avascular");
		bgODVascular.add(rdbtn_AvascularOD);
		horizontalBox_6.add(rdbtn_AvascularOD);

		rdbtn_BlebOD1 = new JRadioButton("+1");
		bgODBleb.add(rdbtn_BlebOD1);
		rdbtn_BlebOD1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_7.add(rdbtn_BlebOD1);

		rdbtn_BlebOD2 = new JRadioButton("+2");
		bgODBleb.add(rdbtn_BlebOD2);
		rdbtn_BlebOD2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_7.add(rdbtn_BlebOD2);

		rdbtn_BlebOD3 = new JRadioButton("+3");
		bgODBleb.add(rdbtn_BlebOD3);
		rdbtn_BlebOD3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_7.add(rdbtn_BlebOD3);

		rdbtn_BlebOD4 = new JRadioButton("+4");
		bgODBleb.add(rdbtn_BlebOD4);
		rdbtn_BlebOD4.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_7.add(rdbtn_BlebOD4);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "K Spindle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_8, "cell 3 1,grow");
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));

		rdbtn_Y_KSpindleOD = new JRadioButton("Y");
		bgODKSpin.add(rdbtn_Y_KSpindleOD);
		panel_8.add(rdbtn_Y_KSpindleOD);

		rdbtn_N_KSpindleOD = new JRadioButton("N");
		bgODKSpin.add(rdbtn_N_KSpindleOD);
		panel_8.add(rdbtn_N_KSpindleOD);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "OS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow]"));

		chkbx_ACOSNormal = new JCheckBox("Normal");
		chkbx_ACOSNormal.addActionListener(new ACOSNormalListener());
		panel_1.add(chkbx_ACOSNormal, "cell 0 0,aligny top");
		chkbx_ACOSNormal.setAlignmentY(0.0f);

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Depth", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_9, "cell 1 0,grow");
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		rdbtn_ACDepthOS1 = new JRadioButton("+1");
		bgOSDepth.add(rdbtn_ACDepthOS1);
		panel_9.add(rdbtn_ACDepthOS1);

		rdbtn_ACDepthOS2 = new JRadioButton("+2");
		bgOSDepth.add(rdbtn_ACDepthOS2);
		panel_9.add(rdbtn_ACDepthOS2);

		rdbtn_ACDepthOS3 = new JRadioButton("+3");
		bgOSDepth.add(rdbtn_ACDepthOS3);
		panel_9.add(rdbtn_ACDepthOS3);

		rdbtn_ACDepthOS4 = new JRadioButton("+4");
		bgOSDepth.add(rdbtn_ACDepthOS4);
		panel_9.add(rdbtn_ACDepthOS4);

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "Angle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_10, "cell 2 0,grow");
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));

		rdbtn_ACAngleOSOpen = new JRadioButton("Open");
		bgOSAngle.add(rdbtn_ACAngleOSOpen);
		panel_10.add(rdbtn_ACAngleOSOpen);

		rdbtn_ACAngleOSClosed = new JRadioButton("Closed");
		bgOSAngle.add(rdbtn_ACAngleOSClosed);
		panel_10.add(rdbtn_ACAngleOSClosed);

		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "PAS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_11, "cell 3 0,grow");
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.Y_AXIS));

		rdbtn_Y_PASOS = new JRadioButton("Present");
		bgOSPAS.add(rdbtn_Y_PASOS);
		panel_11.add(rdbtn_Y_PASOS);

		rdbtn_N_PASOS = new JRadioButton("Absent");
		bgOSPAS.add(rdbtn_N_PASOS);
		panel_11.add(rdbtn_N_PASOS);

		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "KP", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_12, "cell 0 1,grow");
		panel_12.setLayout(new BoxLayout(panel_12, BoxLayout.Y_AXIS));

		rdbtn_ACOSKP1 = new JRadioButton("+1");
		bgOSKP.add(rdbtn_ACOSKP1);
		panel_12.add(rdbtn_ACOSKP1);

		rdbtn_ACOSKP2 = new JRadioButton("+2");
		bgOSKP.add(rdbtn_ACOSKP2);
		panel_12.add(rdbtn_ACOSKP2);

		rdbtn_ACOSKP3 = new JRadioButton("+3");
		bgOSKP.add(rdbtn_ACOSKP3);
		panel_12.add(rdbtn_ACOSKP3);

		rdbtn_ACOSKP4 = new JRadioButton("+4");
		bgOSKP.add(rdbtn_ACOSKP4);
		panel_12.add(rdbtn_ACOSKP4);

		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(null, "Other", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_13, "cell 1 1,grow");
		panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.Y_AXIS));

		rdbtn_ShuntOS = new JRadioButton("Shunt");
		panel_13.add(rdbtn_ShuntOS);

		rdbtn_ScarringOS = new JRadioButton("Scarring");
		panel_13.add(rdbtn_ScarringOS);

		rdbtn_TraumaOS = new JRadioButton("Trauma");
		panel_13.add(rdbtn_TraumaOS);

		rdbtn_BlebOS = new JRadioButton("Bleb");
		panel_13.add(rdbtn_BlebOS);

		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new TitledBorder(null, "Bleb", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_14, "cell 2 1,grow");
		panel_14.setLayout(new BoxLayout(panel_14, BoxLayout.Y_AXIS));

		Box horizontalBox = Box.createHorizontalBox();
		panel_14.add(horizontalBox);

		rdbtn_VascularOS = new JRadioButton("Vascular");
		bgOSVascular.add(rdbtn_VascularOS);
		horizontalBox.add(rdbtn_VascularOS);

		rdbtn_AvascularOS = new JRadioButton("Avascular");
		bgOSVascular.add(rdbtn_AvascularOS);
		horizontalBox.add(rdbtn_AvascularOS);

		rdbtn_BlebOS1 = new JRadioButton("+1");
		bgOSBleb.add(rdbtn_BlebOS1);
		rdbtn_BlebOS1.setAlignmentX(0.5f);
		panel_14.add(rdbtn_BlebOS1);

		rdbtn_BlebOS2 = new JRadioButton("+2");
		bgOSBleb.add(rdbtn_BlebOS2);
		rdbtn_BlebOS2.setAlignmentX(0.5f);
		panel_14.add(rdbtn_BlebOS2);

		rdbtn_BlebOS3 = new JRadioButton("+3");
		bgOSBleb.add(rdbtn_BlebOS3);
		rdbtn_BlebOS3.setAlignmentX(0.5f);
		panel_14.add(rdbtn_BlebOS3);

		rdbtn_BlebOS4 = new JRadioButton("+4");
		bgOSBleb.add(rdbtn_BlebOS4);
		rdbtn_BlebOS4.setAlignmentX(0.5f);
		panel_14.add(rdbtn_BlebOS4);

		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(null, "K Spindle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_15, "cell 3 1,grow");
		panel_15.setLayout(new BoxLayout(panel_15, BoxLayout.Y_AXIS));

		rdbtn_Y_KSpindleOS = new JRadioButton("Y");
		bgOSKSpin.add(rdbtn_Y_KSpindleOS);
		panel_15.add(rdbtn_Y_KSpindleOS);

		rdbtn_N_KSpindleOS = new JRadioButton("N");
		bgOSKSpin.add(rdbtn_N_KSpindleOS);
		panel_15.add(rdbtn_N_KSpindleOS);
	}

	private class ACODNormalListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (chkbx_ACODNormal.isSelected()) {

				rdbtn_ACDepthOD4.setSelected(true);
				rdbtn_ACAngleODOpen.setSelected(true);
				rdbtn_N_PASOD.setSelected(true);
				bgODKP.clearSelection();
				rdbtn_ShuntOD.setSelected(false);
				rdbtn_ScarringOD.setSelected(false);
				rdbtn_TraumaOD.setSelected(false);
				rdbtn_BlebOD.setSelected(false);
				bgODVascular.clearSelection();
				bgODBleb.clearSelection();
				bgODKSpin.clearSelection();

			}
		}

	}

	private class ACOSNormalListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (chkbx_ACOSNormal.isSelected()) {

				rdbtn_ACDepthOS4.setSelected(true);
				rdbtn_ACAngleOSOpen.setSelected(true);
				rdbtn_N_PASOS.setSelected(true);
				bgOSKP.clearSelection();
				rdbtn_ShuntOS.setSelected(false);
				rdbtn_ScarringOS.setSelected(false);
				rdbtn_TraumaOS.setSelected(false);
				rdbtn_BlebOS.setSelected(false);
				bgOSVascular.clearSelection();
				bgOSBleb.clearSelection();
				bgOSKSpin.clearSelection();
			}

		}

	}

	public AnteriorChamber createNewAC() {

		AnteriorChamber ac = new AnteriorChamber(

				chkbx_ACODNormal.isSelected() ? 1 : 0,
				chkbx_ACOSNormal.isSelected() ? 1 : 0,

				getSelectedButtonText(bgODDepth) == null ? " " : getSelectedButtonText(bgODDepth),
				getSelectedButtonText(bgOSDepth) == null ? " " : getSelectedButtonText(bgOSDepth),

				getSelectedButtonText(bgODAngle) == null ? " " : getSelectedButtonText(bgODAngle),
				getSelectedButtonText(bgOSAngle) == null ? " " : getSelectedButtonText(bgOSAngle),

				getSelectedButtonText(bgODPAS) == null ? " " : getSelectedButtonText(bgODPAS),
				getSelectedButtonText(bgOSPAS) == null ? " " : getSelectedButtonText(bgOSPAS),

				getSelectedButtonText(bgODKP) == null ? " " : getSelectedButtonText(bgODKP),
				getSelectedButtonText(bgOSKP) == null ? " " : getSelectedButtonText(bgOSKP),

				rdbtn_ShuntOD.isSelected() ? 1:0,
				rdbtn_ScarringOD.isSelected()? 1:0,
				rdbtn_TraumaOD.isSelected()? 1:0,
				rdbtn_BlebOD.isSelected()? 1:0,

				rdbtn_ShuntOS.isSelected() ? 1 : 0,
				rdbtn_ScarringOS.isSelected() ? 1 : 0,
				rdbtn_TraumaOS.isSelected() ? 1 : 0,
				rdbtn_BlebOS.isSelected() ? 1 : 0,

				(rdbtn_VascularOD.isSelected() ? 1 : rdbtn_AvascularOD.isSelected() ? 0 : -1),
				getSelectedButtonText(bgODBleb) == null ? " " : getSelectedButtonText(bgODBleb),

				(rdbtn_VascularOS.isSelected() ? 1 : rdbtn_AvascularOS.isSelected() ? 0 : -1),
				getSelectedButtonText(bgOSBleb) == null ? " " : getSelectedButtonText(bgOSBleb),

				(rdbtn_Y_KSpindleOD.isSelected() ? 1 : rdbtn_N_KSpindleOD.isSelected() ? 0 : -1),
				(rdbtn_Y_KSpindleOS.isSelected() ? 1 : rdbtn_N_KSpindleOS.isSelected() ? 0 : -1)
				);

		return ac;
	}

	/**
	 * Gets the selected button's name from a button group.
	 * 
	 * @param NoneSelectedButtonGroup
	 * @return The string of the name of the selected radio button of the
	 *         NoneSelectedButtonGroup
	 */
	public String getSelectedButtonText(NoneSelectedButtonGroup NoneSelectedButtonGroup) {
		for (Enumeration<AbstractButton> buttons = NoneSelectedButtonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}

	public void setFields(ArrayList<Object> acCols) {

		// will be iterating manually through the tuples
		int i = -1;

//		System.out.println("AC cols: " + acCols.toString());
		String temp;
		
		if (acCols.get(++i).toString().equals("1"))
			chkbx_ACODNormal.setSelected(true);

		
		
		if (acCols.get(++i).toString().equals("1"))
			chkbx_ACOSNormal.setSelected(true);

		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("+1")) {
			rdbtn_ACDepthOD1.setSelected(true);
		} else if (temp.equals("+2")) {
			rdbtn_ACDepthOD2.setSelected(true);
		} else if (temp.equals("+3")) {
			rdbtn_ACDepthOD3.setSelected(true);
		} else if (temp.equals("+4")) {
			rdbtn_ACDepthOD4.setSelected(true);
		}
		
		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("+1")) {
			rdbtn_ACDepthOS1.setSelected(true);
		} else if (temp.equals("+2")) {
			rdbtn_ACDepthOS2.setSelected(true);
		} else if (temp.equals("+3")) {
			rdbtn_ACDepthOS3.setSelected(true);
		} else if (temp.equals("+4")) {
			rdbtn_ACDepthOS4.setSelected(true);
		}

		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("Open"))
			rdbtn_ACAngleODOpen.setSelected(true);
		else if (temp.equals("Closed"))
			rdbtn_ACAngleODClosed.setSelected(true);
		
		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("Open"))
			rdbtn_ACAngleOSOpen.setSelected(true);
		else if (temp.equals("Closed"))
			rdbtn_ACAngleOSClosed.setSelected(true);

		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("Absent"))
			rdbtn_N_PASOD.setSelected(true);
		else if (temp.equals("Present"))
			rdbtn_Y_PASOD.setSelected(true);

		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("Absent"))
			rdbtn_N_PASOS.setSelected(true);
		else if (temp.equals("Present"))
			rdbtn_Y_PASOS.setSelected(true);

		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("+1")) {
			rdbtn_ACODKP1.setSelected(true);
		} else if (temp.equals("+2")) {
			rdbtn_ACODKP2.setSelected(true);
		} else if (temp.equals("+3")) {
			rdbtn_ACODKP3.setSelected(true);
		} else if (temp.equals("+4")) {
			rdbtn_ACODKP4.setSelected(true);
		}

		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("+1")) {
			rdbtn_ACOSKP1.setSelected(true);
		} else if (temp.equals("+2")) {
			rdbtn_ACOSKP2.setSelected(true);
		} else if (temp.equals("+3")) {
			rdbtn_ACOSKP3.setSelected(true);
		} else if (temp.equals("+4")) {
			rdbtn_ACOSKP4.setSelected(true);
		}

		
		
		if (acCols.get(++i).toString().equals("1"))
			rdbtn_ShuntOD.setSelected(true);
		if (acCols.get(++i).toString().equals("1"))
			rdbtn_ScarringOD.setSelected(true);
		if (acCols.get(++i).toString().equals("1"))
			rdbtn_TraumaOD.setSelected(true);
		if (acCols.get(++i).toString().equals("1"))
			rdbtn_BlebOD.setSelected(true);

		
		
		if (acCols.get(++i).toString().equals("1"))
			rdbtn_ShuntOS.setSelected(true);
		if (acCols.get(++i).toString().equals("1"))
			rdbtn_ScarringOS.setSelected(true);
		if (acCols.get(++i).toString().equals("1"))
			rdbtn_TraumaOS.setSelected(true);
		if (acCols.get(++i).toString().equals("1"))
			rdbtn_BlebOS.setSelected(true);

		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("0"))
			rdbtn_AvascularOD.setSelected(true);
		else if (temp.equals("1"))
			rdbtn_VascularOD.setSelected(true);

		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("+1")) {
			rdbtn_BlebOD1.setSelected(true);
		} else if (temp.equals("+2")) {
			rdbtn_BlebOD2.setSelected(true);
		} else if (temp.equals("+3")) {
			rdbtn_BlebOD3.setSelected(true);
		} else if (temp.equals("+4")) {
			rdbtn_BlebOD4.setSelected(true);
		}

		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("0"))
			rdbtn_AvascularOS.setSelected(true);
		else if (temp.equals("1"))
			rdbtn_VascularOS.setSelected(true);

		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("+1")) {
			rdbtn_BlebOS1.setSelected(true);
		} else if (temp.equals("+2")) {
			rdbtn_BlebOS2.setSelected(true);
		} else if (temp.equals("+3")) {
			rdbtn_BlebOS3.setSelected(true);
		} else if (temp.equals("+4")) {
			rdbtn_BlebOS4.setSelected(true);
		}

		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("0"))
			rdbtn_N_KSpindleOD.setSelected(true);
		else if (temp.equals("1"))
			rdbtn_Y_KSpindleOD.setSelected(true);

		
		
		temp = acCols.get(++i).toString();
		if (temp.equals("0"))
			rdbtn_N_KSpindleOS.setSelected(true);
		else if (temp.equals("1"))
			rdbtn_Y_KSpindleOS.setSelected(true);
	}
}
