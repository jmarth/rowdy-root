package visitPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.DrawArea;
import views.viewinterface;

@SuppressWarnings("serial")
public class PanelNewSketch extends JPanel {

	private JButton btnClear, btnColorPicker, btnSetBackground, btnCancel;

	private DrawArea drawArea;

	private JPanel panelSideBar;

	private JButton btnSLEDiag;
	private JButton btnFundusDiag;
	private JButton btnGonioDiag;

	private JButton btnSave;

	private JLabel lblSketchOutside;

	public PanelNewSketch() {
		super();
	}
	
	public PanelNewSketch(JLabel lblSketchOutside ) {
		this.lblSketchOutside = lblSketchOutside;

		ActionListener paintController = new PaintListener();

		Dimension d = new Dimension(600, 500);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setMaximumSize(d);

		this.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();

		// create draw area
		drawArea = new DrawArea(d);

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

		btnSLEDiag = new JButton("SLE");
		btnSLEDiag.addActionListener(new ActionListener() {

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
		panelSideBar.add(btnSLEDiag, gbc_btnNewButton);
		
		btnGonioDiag = new JButton("Gonio");
		btnGonioDiag.addActionListener(new ActionListener() {
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
		panelSideBar.add(btnGonioDiag, gbc_btnGreenEye);

		btnFundusDiag = new JButton("Fundus");
		btnFundusDiag.addActionListener(new ActionListener() {
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
		panelSideBar.add(btnFundusDiag, gbc_btnBrownEye);

		

		// create controls to apply colors and call clear feature
		JPanel controlPanel = new JPanel();

		btnClear = new JButton("Clear");
		btnClear.addActionListener(paintController);

		btnColorPicker = new JButton("Color Picker");
		btnColorPicker.addActionListener(paintController);

		btnSetBackground = new JButton("Set background image");
		btnSetBackground.addActionListener(paintController);

		// Hiding because not sure if we still need this since we have preset
		btnSetBackground.setVisible(false);

		// add to panel
		controlPanel.add(btnColorPicker);
		controlPanel.add(btnSetBackground);
		controlPanel.add(btnClear);

		// add to content pane
		add(controlPanel, BorderLayout.NORTH);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new CancelListener());
		controlPanel.add(btnCancel);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveListener());
		controlPanel.add(btnSave);
	}


	public class CancelListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			PanelNewSketch.this.showPreviousView();
		}
	}

	public class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			lblSketchOutside.setIcon(new ImageIcon(drawArea.getMyBI()));
			PanelNewSketch.this.showPreviousView();
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
			/*
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
			}*/
			}
		}
	}

	public void showPreviousView() {
		((viewinterface) this.getParent().getParent()).ShowView();
	}

	public BufferedImage getMyBI() {
		if (drawArea == null) {
			return null;
		}
		return drawArea.getMyBI();
	}
	
	public void drawThis(JLabel lblSketch) {
		Image i = ((ImageIcon)lblSketch.getIcon()).getImage();
		int x = i.getWidth(null);
		int y = i.getHeight(null);
		BufferedImage bi = new BufferedImage(x,y, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gg = bi.createGraphics();
		gg.drawImage(i, 0,0,x,y,null);
		try {
			drawArea.setBackgroundImage(bi);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}