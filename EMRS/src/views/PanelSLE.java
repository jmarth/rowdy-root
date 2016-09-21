package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import models.CL;
import models.Visit;
import net.miginfocom.swing.MigLayout;
import visitPanels.PanelAC;
import visitPanels.PanelLens;
import visitPanels.PanelPupils;

@SuppressWarnings("serial")
public class PanelSLE extends JPanel {

//	private final MasterModel masterModel;
//	private Patient patient;
	
	private Visit myVisit;
	
	private PanelPupils panel_Pupils;
	private PanelAC panel_AC;
	private PanelLens panel_Lens;
	
	private JPanel panel_SLE_Sketch;
	private JLabel label_SLE_Sketch;
	
//	private Image image_SLE;
	
	public PanelSLE(Visit myVisit) {
		
//		this.masterModel = masterModel;
//		this.patient = patient;
		
		setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Slit Lamp Exam", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, new Font("Tahoma", Font.BOLD, 20), new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[grow]", "[grow][][grow]"));
		setBackground(CL.turq);
		
		
		
		// PUPILS
		panel_Pupils = new PanelPupils(myVisit);
		add(panel_Pupils, "cell 0 0,grow");
		
		// AC
		panel_AC = new PanelAC(myVisit);
		add(panel_AC, "cell 0 1,grow");
		
		// SLE Lens
		panel_Lens = new PanelLens(myVisit);
		add(panel_Lens, "cell 0 2,grow");
		
		// SLE SKETCH
		JPanel panel_SLE_Diagram = new JPanel();
		add(panel_SLE_Diagram, "cell 0 3,growx");
		panel_SLE_Diagram.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Diagram", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_SLE_Diagram.setLayout(new BoxLayout(panel_SLE_Diagram, BoxLayout.Y_AXIS));
	
		
		//TODO Set that sketch yooooo
		
		JButton btnSLESketch = new JButton("Sketch");
		btnSLESketch.addActionListener(new CreateSketchListener());
		panel_SLE_Diagram.add(btnSLESketch);
	
		
		//TODO Not sure how this works
		label_SLE_Sketch = new JLabel("");
		panel_SLE_Diagram.add(label_SLE_Sketch);
	}
	
	public void setFields() {
		panel_Pupils.setFields();
		panel_AC.setFields();
		panel_Lens.setFields();
				
		Image image_SLE = myVisit.getSketches().getImageSLE();
//		Image image_Gonio = myVisit.getSketches().getImageGonio();
//		Image image_Fundus = myVisit.getSketches().getImageFundus();
		ImageIcon iconSLE = new ImageIcon(image_SLE);
//		ImageIcon iconGonio = new ImageIcon(image_Gonio);
//		ImageIcon iconFundus = new ImageIcon(image_Fundus);
		label_SLE_Sketch.setIcon(iconSLE);
	}

	private class CreateSketchListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			FrameNewSketch firstSketch = new FrameNewSketch(label_SLE_Sketch, "firstSketch");
			firstSketch.setContentPane(firstSketch.getContentPane());
			firstSketch.setSize(new Dimension(600,600));
			firstSketch.setResizable(false);
			
			panel_SLE_Sketch = (JPanel) firstSketch.getContentPane();
			panel_SLE_Sketch.setVisible(true);
			firstSketch.setVisible(true);
		}
		
	}
}
