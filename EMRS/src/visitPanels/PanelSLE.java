package visitPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import models.CL;
import models.MasterModel;
import net.miginfocom.swing.MigLayout;
import views.HomeView;
import views.VisitDetailView;
import views.viewinterface;

@SuppressWarnings("serial")
public class PanelSLE extends JPanel implements viewinterface {

	private int index;
	
	private PanelPupils panel_Pupils;
	private PanelAC panel_AC;
	private PanelLens panel_Lens;
	
	private JPanel panel_Sketch;
	private JButton btnSketch;
	private PanelNewSketch panelNewSketch;
	private JLabel lblSketch;
	
	public PanelSLE(int index) {
		
		setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Slit Lamp Exam", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, new Font("Tahoma", Font.BOLD, 20), new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[grow]", "[grow][][grow]"));
		setBackground(CL.turq);
		
		
		
		// PUPILS
		panel_Pupils = new PanelPupils(index);
		add(panel_Pupils, "cell 0 0,grow");
		
		// AC
		panel_AC = new PanelAC(index);
		add(panel_AC, "cell 0 1,grow");
		
		// SLE Lens
		panel_Lens = new PanelLens(index);
		add(panel_Lens, "cell 0 2,grow");
		
		// SLE SKETCH
		panel_Sketch = new JPanel();
		add(panel_Sketch, "cell 0 3,growx");
		panel_Sketch.setBorder(new TitledBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)), "Diagram", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Sketch.setLayout(new BoxLayout(panel_Sketch, BoxLayout.Y_AXIS));
	
		btnSketch = new JButton("Sketch");
		btnSketch.setHorizontalAlignment(SwingConstants.LEFT);
		btnSketch.addActionListener(new SketchListener());
		panel_Sketch.add(btnSketch); //added by showView()
		
		lblSketch = new JLabel("");
		panel_Sketch.add(lblSketch); // added by showView()
		
		panelNewSketch = new PanelNewSketch(lblSketch);
		panelNewSketch.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelNewSketch.setBorder(new TitledBorder(null, "Slit Lamp Exam Sketch", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Sketch.add(panelNewSketch);
		//panelNewSketch added by showNewSketch()
		//since showView() called first, start with panelNewSketch to remove when showView()
	}
	
	public void setSketch() {
		Image image_SLE = getMasterModel().getCurrentPatientVisitList().get(index).getSketches().getImageSLE();
		if (image_SLE != null) {
			ImageIcon iconSLE = new ImageIcon(image_SLE);
			lblSketch.setIcon(iconSLE);
		}
	}
	
	public void setFields() {
		setSketch();
	}

	private class SketchListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			PanelSLE.this.showNewSketch();
			System.err.println(PanelSLE.this.getParent().getClass());
			System.err.println(PanelSLE.this.getParent().getParent().getClass());
			
			

			PanelSLE.this.getParent().validate();
			PanelSLE.this.getParent().repaint();
			//System.err.println(label_SLE_Sketch);
//			FrameNewSketch firstSketch = new FrameNewSketch(label_SLE_Sketch, "firstSketch");
//			firstSketch.setContentPane(firstSketch.getContentPane());
//			firstSketch.setSize(new Dimension(600,600));
//			firstSketch.setResizable(true);
//			
//			panel_SLE_Sketch = (JPanel) firstSketch.getContentPane();
//			panel_SLE_Sketch.setVisible(true);
//			firstSketch.setVisible(true);
		}
		
	}

	@Override
	public void ShowView() {
		panel_Sketch.remove(panelNewSketch);
		
		panel_Sketch.add(btnSketch);
		panel_Sketch.add(lblSketch);

		this.validate();
		this.repaint();
	}
	
	public void showNewSketch() {
		panel_Sketch.remove(btnSketch);
		panel_Sketch.remove(lblSketch);

		panel_Sketch.add(panelNewSketch);
		
		this.validate();
		this.repaint();
	}

	@Override
	public void reload() {
		
		this.ShowView();
		
		this.setFields();
		
		panel_Pupils.reload();
		panel_AC.reload();
		panel_Lens.reload();
	}

	@Override
	public MasterModel getMasterModel() {
		return ((HomeView)this.getHomeView()).getMasterModel();
	}
	
	@Override
	public HomeView getHomeView() {
		return ((VisitDetailView)this.getParent()).getHomeView();
	}
	
	@Override
	public void HideallView() {
		//TODO
	}
	
	public Image getSketch() {
		return panelNewSketch.getSketch();
	}

	public PanelPupils getPanelPupils() {
		return panel_Pupils;
	}

	public PanelLens getPanelLens() {
		return panel_Lens;
	}

	public JPanel getPanelSLESketch() {
		return panel_Sketch;
	}

	public PanelAC getPanelAC() {
		return panel_AC;
	}
}
