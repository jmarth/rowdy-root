package views;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import models.Patient;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class NewAllergyFormView extends JPanel {
	private final ButtonGroup severityButtonGroup = new ButtonGroup();
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public NewAllergyFormView(final JTabbedPane tabbedPane, Patient patient) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAllergy = new JLabel("Allergy");
		lblAllergy.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblAllergy = new GridBagConstraints();
		gbc_lblAllergy.insets = new Insets(25, 25, 5, 5);
		gbc_lblAllergy.gridx = 0;
		gbc_lblAllergy.gridy = 1;
		add(lblAllergy, gbc_lblAllergy);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(25, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(20);
		
		JLabel lblSeverity = new JLabel("Severity");
		lblSeverity.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblSeverity = new GridBagConstraints();
		gbc_lblSeverity.insets = new Insets(25, 25, 5, 5);
		gbc_lblSeverity.gridx = 0;
		gbc_lblSeverity.gridy = 3;
		add(lblSeverity, gbc_lblSeverity);
		
		JRadioButton rdbtnSevere = new JRadioButton("Severe");
		severityButtonGroup.add(rdbtnSevere);
		GridBagConstraints gbc_rdbtnSevere = new GridBagConstraints();
		gbc_rdbtnSevere.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSevere.insets = new Insets(25, 0, 5, 5);
		gbc_rdbtnSevere.gridx = 1;
		gbc_rdbtnSevere.gridy = 3;
		add(rdbtnSevere, gbc_rdbtnSevere);
		
		JRadioButton rdbtnModerate = new JRadioButton("Moderate");
		severityButtonGroup.add(rdbtnModerate);
		GridBagConstraints gbc_rdbtnModerate = new GridBagConstraints();
		gbc_rdbtnModerate.anchor = GridBagConstraints.WEST;
		gbc_rdbtnModerate.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnModerate.gridx = 1;
		gbc_rdbtnModerate.gridy = 4;
		add(rdbtnModerate, gbc_rdbtnModerate);
		
		JRadioButton rdbtnMild = new JRadioButton("Mild");
		severityButtonGroup.add(rdbtnMild);
		GridBagConstraints gbc_rdbtnMild = new GridBagConstraints();
		gbc_rdbtnMild.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMild.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMild.gridx = 1;
		gbc_rdbtnMild.gridy = 5;
		add(rdbtnMild, gbc_rdbtnMild);
		
		JLabel lblReactions = new JLabel("Reactions");
		lblReactions.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblReactions = new GridBagConstraints();
		gbc_lblReactions.insets = new Insets(25, 25, 5, 5);
		gbc_lblReactions.gridx = 0;
		gbc_lblReactions.gridy = 7;
		add(lblReactions, gbc_lblReactions);
		
		JCheckBox chckbxWheezing = new JCheckBox("Wheezing");
		chckbxWheezing.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxWheezing = new GridBagConstraints();
		gbc_chckbxWheezing.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_chckbxWheezing.insets = new Insets(25, 0, 5, 5);
		gbc_chckbxWheezing.gridx = 1;
		gbc_chckbxWheezing.gridy = 7;
		add(chckbxWheezing, gbc_chckbxWheezing);
		
		JCheckBox chckbxAnxiety = new JCheckBox("Anxiety");
		chckbxAnxiety.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxAnxiety = new GridBagConstraints();
		gbc_chckbxAnxiety.anchor = GridBagConstraints.WEST;
		gbc_chckbxAnxiety.insets = new Insets(25, 0, 5, 5);
		gbc_chckbxAnxiety.gridx = 2;
		gbc_chckbxAnxiety.gridy = 7;
		add(chckbxAnxiety, gbc_chckbxAnxiety);
		
		JCheckBox chckbxHeartPalpitations = new JCheckBox("Heart Palpitations");
		chckbxHeartPalpitations.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxHeartPalpitations = new GridBagConstraints();
		gbc_chckbxHeartPalpitations.anchor = GridBagConstraints.WEST;
		gbc_chckbxHeartPalpitations.insets = new Insets(25, 0, 5, 5);
		gbc_chckbxHeartPalpitations.gridx = 3;
		gbc_chckbxHeartPalpitations.gridy = 7;
		add(chckbxHeartPalpitations, gbc_chckbxHeartPalpitations);
		
		JCheckBox chckbxChills = new JCheckBox("Chills");
		chckbxChills.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxChills = new GridBagConstraints();
		gbc_chckbxChills.anchor = GridBagConstraints.WEST;
		gbc_chckbxChills.insets = new Insets(25, 0, 5, 0);
		gbc_chckbxChills.gridx = 4;
		gbc_chckbxChills.gridy = 7;
		add(chckbxChills, gbc_chckbxChills);
		
		JCheckBox chckbxCoughing = new JCheckBox("Coughing");
		chckbxCoughing.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxCoughing = new GridBagConstraints();
		gbc_chckbxCoughing.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_chckbxCoughing.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCoughing.gridx = 1;
		gbc_chckbxCoughing.gridy = 8;
		add(chckbxCoughing, gbc_chckbxCoughing);
		
		JCheckBox chckbxTired = new JCheckBox("Tired");
		chckbxTired.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxTired = new GridBagConstraints();
		gbc_chckbxTired.anchor = GridBagConstraints.WEST;
		gbc_chckbxTired.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTired.gridx = 2;
		gbc_chckbxTired.gridy = 8;
		add(chckbxTired, gbc_chckbxTired);
		
		JCheckBox chckbxAchy = new JCheckBox("Achy");
		chckbxAchy.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxAchy = new GridBagConstraints();
		gbc_chckbxAchy.anchor = GridBagConstraints.WEST;
		gbc_chckbxAchy.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAchy.gridx = 3;
		gbc_chckbxAchy.gridy = 8;
		add(chckbxAchy, gbc_chckbxAchy);
		
		JCheckBox chckbxNaseua = new JCheckBox("Naseua");
		chckbxNaseua.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_chckbxNaseua = new GridBagConstraints();
		gbc_chckbxNaseua.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxNaseua.anchor = GridBagConstraints.BASELINE;
		gbc_chckbxNaseua.gridx = 4;
		gbc_chckbxNaseua.gridy = 8;
		add(chckbxNaseua, gbc_chckbxNaseua);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(50, 0, 0, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 11;
		add(btnSave, gbc_btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = tabbedPane.indexOfTab("Allergies");
				
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(50, 0, 0, 0);
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 11;
		add(btnCancel, gbc_btnCancel);

	}

}
