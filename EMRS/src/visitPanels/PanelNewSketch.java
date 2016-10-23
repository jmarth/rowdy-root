package visitPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.DrawArea;
import views.viewinterface;

@SuppressWarnings("serial")
public class PanelNewSketch extends JPanel {

//	private File sketch = null;

	private JButton btnClear, btnColorPicker, btnSetBackground;

	private DrawArea drawArea;

	private JPanel panelSideBar;

	private JButton btnImage1;
	private JButton btnImage2;
	private JButton btnImage3;

	private JButton btnSave;

	private JLabel lblSketch;
	private Image bufferedImage;

	public PanelNewSketch(final JLabel sketchLabel) {

		this.lblSketch = sketchLabel;

		ActionListener paintController = new PaintListener();
		
		this.setMinimumSize(new Dimension(500, 500));
		this.setMaximumSize(new Dimension(500, 500));

		this.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();

		// create draw area
		drawArea = new DrawArea();

		// add drawArea to scroll pane
		scrollPane.add(drawArea);
		scrollPane.setViewportView(drawArea);

		// add to content pane
		add(scrollPane, BorderLayout.CENTER);

		panelSideBar = new JPanel();

		scrollPane.setRowHeaderView(panelSideBar);
		GridBagLayout gbl_panelSideBar = new GridBagLayout();
		gbl_panelSideBar.columnWidths = new int[] { 46, 0 };
		gbl_panelSideBar.rowHeights = new int[] { 14, 0, 0, 0, 0, 0, 0 };
		gbl_panelSideBar.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelSideBar.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelSideBar.setLayout(gbl_panelSideBar);

		JLabel lblNewLabel = new JLabel("Backgrounds ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelSideBar.add(lblNewLabel, gbc_lblNewLabel);

		btnImage1 = new JButton("SLE");
		btnImage1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				BufferedImage bimg = null;

				try {

					bimg = ImageIO.read(new File("eye-diagrams/draw1.jpg"));

				} catch (IOException e2) {
					System.err.println("From Paint, IO on read existing jpg");
					// e2.printStackTrace();
				}
				// Image image = imageIcon.getImage(); // transform it
				try {

					drawArea.setBackgroundImage(bimg);

				} catch (IOException e1) {
					System.err.println("From Paint, IO error, setBackgroundImage");
					// e1.printStackTrace();
				}
			}

		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		panelSideBar.add(btnImage1, gbc_btnNewButton);

		btnImage2 = new JButton("Fundus");
		btnImage2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage bimg = null;
				try {
					bimg = ImageIO.read(new File("eye-diagrams/draw2.jpg"));
				} catch (IOException e2) {
					System.err.println("From Paint, IO on read existing jpg");
					// e2.printStackTrace();
				}
				// Image image = imageIcon.getImage(); // transform it
				try {
					drawArea.setBackgroundImage(bimg);
				} catch (IOException e1) {
					System.err.println("From Paint, IO error, setBackgroundImage");
					// e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnBrownEye = new GridBagConstraints();
		gbc_btnBrownEye.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrownEye.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBrownEye.gridx = 0;
		gbc_btnBrownEye.gridy = 2;
		panelSideBar.add(btnImage2, gbc_btnBrownEye);

		btnImage3 = new JButton("Gonio");
		btnImage3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage bimg = null;
				try {
					bimg = ImageIO.read(new File("eye-diagrams/draw3.jpg"));
				} catch (IOException e2) {
					System.err.println("From Paint, IO on read existing jpg");
					// e2.printStackTrace();
				}
				// Image image = imageIcon.getImage(); // transform it
				try {
					drawArea.setBackgroundImage(bimg);
				} catch (IOException e1) {
					System.err.println("From Paint, IO error, setBackgroundImage");
					// e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnGreenEye = new GridBagConstraints();
		gbc_btnGreenEye.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGreenEye.insets = new Insets(0, 0, 5, 0);
		gbc_btnGreenEye.gridx = 0;
		gbc_btnGreenEye.gridy = 3;
		panelSideBar.add(btnImage3, gbc_btnGreenEye);

		// create controls to apply colors and call clear feature
		JPanel controlPanel = new JPanel();

		btnClear = new JButton("Clear");
		btnClear.addActionListener(paintController);

		btnColorPicker = new JButton("Color Picker");
		btnColorPicker.addActionListener(paintController);

		btnSetBackground = new JButton("Set background image");
		btnSetBackground.addActionListener(paintController);

		// Hiding because not sure if we still need this since we have preset
		// images TODO what?
		btnSetBackground.setVisible(false);

		// add to panel
		controlPanel.add(btnColorPicker);
		controlPanel.add(btnSetBackground);
		controlPanel.add(btnClear);

		// add to content pane
		add(controlPanel, BorderLayout.NORTH);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveListener());
		controlPanel.add(btnSave);
	}

	public class SaveListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			bufferedImage = new BufferedImage(drawArea.getWidth(), drawArea.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			
			drawArea.paint(bufferedImage.getGraphics());
			
			lblSketch.setIcon(new ImageIcon(bufferedImage));
			
			PanelNewSketch.this.showPreviousView();
			// drawArea.getGraphics();
//			try {
//
//				ImageIO.write(im, "JPG", new File(filename + ".png"));
//				BufferedImage bufImg = ImageIO.read(new File(filename + ".png"));// 
//																					// jpg
//				if (bufImg == null) {
//					System.err.println("From PanelNewSketch: bufImg is null.");
//				}
//				if (lblSketch == null) {
//					System.err.println("From PanelNewSketch: sketch Label is null");
//				}
//
//				lblSketch.setIcon(new ImageIcon(bufImg));
//				
//				PanelNewSketch.this.showPreviousView();
//
//			} catch (IOException e1) {
//				System.err.println("From FrameNewSketch, IO error during save");
//				// e1.printStackTrace();
//				PanelNewSketch.this.showPreviousView();
//			}
		}
	}

	private class PaintListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnClear) {

				drawArea.clear();

			} else if (e.getSource() == btnColorPicker) {

				Color selectedColor = JColorChooser.showDialog(null, "JColorChooser Sample", Color.BLACK);
				drawArea.setColor(selectedColor);

			} else if (e.getSource() == btnSetBackground) {

				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));

				// filter the files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
				file.addChoosableFileFilter(filter);

				int result = file.showSaveDialog(null);

				// if the user click on save in JFileChoser
				if (result == JFileChooser.APPROVE_OPTION) {

					File selectedFile = file.getSelectedFile();

					String imagePath = selectedFile.getAbsolutePath();
					// System.out.println(imagePath);

					BufferedImage bufferedImage = null;

					try {

						bufferedImage = ImageIO.read(new File(imagePath));

					} catch (IOException e2) {
						System.err.println("From PanelNewSketch, IO error");
						// e2.printStackTrace();
					}

					// ImageIcon imageIcon = new ImageIcon(imagePath);
					// Image image = imageIcon.getImage(); // transform it
					try {
						drawArea.setBackgroundImage(bufferedImage);
					} catch (IOException e1) {
						System.err.println("From PanelNewSketch, IO error, setBackgroundImage");
						// e1.printStackTrace();
					}
				}
			}
		}

	}
//
//	public File getSketch() {
//		return sketch;
//	}
//
//	public void setSketch(File sketch) {
//		this.sketch = sketch;
//	}
	
	public void showPreviousView() {
		((viewinterface)this.getParent().getParent()).ShowView();
	}
	
	public Image getSketch() {
		return bufferedImage;
	}
}
