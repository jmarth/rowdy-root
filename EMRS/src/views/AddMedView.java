package views;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;

import database.DrugTableGateway;
import database.GatewayException;
import database.MedicationsTableGateway;
import models.CL;
import models.Drug;
import models.MasterModel;
import models.Med;
import models.Patient;
import models.Tabs;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ListSelectionModel;

public class AddMedView extends JPanel implements viewinterface  {
	private JTextField textField;
	
	private JList list;
	
	private DrugTableGateway rtg;
	
	private MedicationsTableGateway mtg;
	
	private JTabbedPane tabbedPane;
	
	private hxView hxView;
	
	private Patient patient;
	private JTextField directionsField;

	/**
	 * Create the panel.
	 * @param hxView 
	 * @param tabbedPane 
	 */
	public AddMedView(DrugTableGateway gt, MedicationsTableGateway mtg, final JTabbedPane tabbedPane, final hxView hxView, Patient p) {
		this.rtg = gt;
		this.mtg = mtg;
		this.tabbedPane = tabbedPane;
		this.hxView = hxView;
		this.patient = p;
		setBackground(CL.belize);
		
		JPanel panel = new JPanel();
		panel.setBackground(CL.belize);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(240, 248, 255)), "Find Drug", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(CL.belize);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setAlignOnBaseline(true);
		panel_1.setBackground(CL.belize);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(8))
		);
		
		JLabel lblPowerSearch = new JLabel("Power Search");
		lblPowerSearch.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPowerSearch.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panel_1.add(lblPowerSearch);
		
		JLabel lblFindByGeneric = new JLabel("Find by Generic and Trade Name");
		lblFindByGeneric.setForeground(Color.DARK_GRAY);
		lblFindByGeneric.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFindByGeneric.setVerticalAlignment(JLabel.BOTTOM);
		panel_1.add(lblFindByGeneric);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(CL.belize);
		panel.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setForeground(new Color(255, 255, 255));

		panel_4.add(lblSearch);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String query = textField.getText();
				
				ArrayList<Drug> drugs = null;
				try {
					drugs = (ArrayList<Drug>) rtg.searchByPrefix(query);
				} catch (GatewayException e1) {
					e1.printStackTrace();
				}
				
				DefaultListModel model = new DefaultListModel();
				for (Drug d : drugs) {
					model.addElement(d.getTrade() + " | " + d.getGeneric());
				}
				
				list.setModel(model);
				
			}
			
		});
		panel_4.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JLabel lblDirections = new JLabel("Directions:");
		panel_2.add(lblDirections);
		
		directionsField = new JTextField();
		panel_2.add(directionsField);
		directionsField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] selectedValues = list.getSelectedValues();
				for (Object s : selectedValues) {
					String[] pieces = ((String) s).split("\\|");
					String directions = directionsField.getText();
					Med tmp = new Med((long) 0, patient.getId(), pieces[0].trim(), pieces[1].trim(), directions);
					try {
						AddMedView.this.mtg.insertMed(tmp);
					} catch (GatewayException e1) {
						e1.printStackTrace();
					}
				}
				hxView.populateMedTable();
				
				int index = tabbedPane.indexOfTab(Tabs.hx);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, hxView);
			}
			
		});
		panel_3.add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				list.setModel(new DefaultListModel());
				int index = tabbedPane.indexOfTab(Tabs.hx);
				tabbedPane.setComponentAt(index, null);
				tabbedPane.setComponentAt(index, hxView);
				
				
			}
			
		});
		panel_3.add(btnCancel);
		setLayout(groupLayout);

	}
	@Override
	public void HideallView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MasterModel getMasterModel() {
		
		return null;
	}
	@Override
	public void ShowView() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public HomeView getHomeView() {
		// TODO Auto-generated method stub
		return null;
	}
}
