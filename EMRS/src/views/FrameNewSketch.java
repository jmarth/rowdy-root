package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.DrawArea;

@SuppressWarnings("serial")
public class FrameNewSketch extends JFrame {
	
	private String filename;
	private JButton clearBtn, colorPickerBtn, setBackgroundBtn;
	
	private DrawArea drawArea;
	
	private File sketch = null;
	
	
	
	private JPanel panelSideBar;
	
	private JButton btnImage1;
	private JButton btnImage2;
	private JButton btnImage3;
	
	private JButton btnSave;
	
	private JLabel sketchLabel;
	

	public FrameNewSketch (final JLabel sketchLabel, final String filename) {
		
		this.sketchLabel = sketchLabel;
		
		ActionListener paintController = new PaintListener();
		
		this.setMaximumSize(new Dimension(500, 500));
		this.filename = filename;

		getContentPane().setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();

		// create draw area
		drawArea = new DrawArea(null);
		
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
//					e2.printStackTrace();
				}
				// Image image = imageIcon.getImage(); // transform it
				try {
					drawArea.setBackgroundImage(bimg);
				} catch (IOException e1) {
					System.err.println("From Paint, IO error, setBackgroundImage");
//					e1.printStackTrace();
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
//					e2.printStackTrace();
				}
				// Image image = imageIcon.getImage(); // transform it
				try {
					drawArea.setBackgroundImage(bimg);
				} catch (IOException e1) {
					System.err.println("From Paint, IO error, setBackgroundImage");
//					e1.printStackTrace();
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
//					e2.printStackTrace();
				}
				// Image image = imageIcon.getImage(); // transform it
				try {
					drawArea.setBackgroundImage(bimg);
				} catch (IOException e1) {
					System.err.println("From Paint, IO error, setBackgroundImage");
//					e1.printStackTrace();
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

		clearBtn = new JButton("Clear");
		clearBtn.addActionListener(paintController);
		
		colorPickerBtn = new JButton("Color Picker");
		colorPickerBtn.addActionListener(paintController);
		
		setBackgroundBtn = new JButton("Set background image");
		setBackgroundBtn.addActionListener(paintController);

		// Hiding because not sure if we still need this since we have preset images TODO what?
		setBackgroundBtn.setVisible(false);

		// add to panel
		controlPanel.add(colorPickerBtn);
		controlPanel.add(setBackgroundBtn);
		controlPanel.add(clearBtn);

		// add to content pane
		add(controlPanel, BorderLayout.NORTH);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveListener());
		controlPanel.add(btnSave);
	}
	
	public class SaveListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			
			
			BufferedImage im = new BufferedImage(drawArea.getWidth(), drawArea.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			drawArea.paint(im.getGraphics());
//			drawArea.getGraphics();
			try {
				
				ImageIO.write(im, "PNG", new File(filename + ".png"));
				BufferedImage bufImg = ImageIO.read(new File(filename + ".png"));//TODO jpg
				if (bufImg == null) {
					System.err.println("null");
				}
				if (sketchLabel == null) {
					System.err.println("null lab");
				}
				
				
				sketchLabel.setIcon(new ImageIcon(bufImg));

				dispose();
			} catch (IOException e1) {
				System.err.println("From FrameNewSketch, IO error during save");
//				e1.printStackTrace();
			}
		}
	}

	private class PaintListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == clearBtn) {
				
				drawArea.clear();
				
			} else if (e.getSource() == colorPickerBtn) {
				
				Color color = JColorChooser.showDialog(null, "JColorChooser Sample", Color.BLACK);
				drawArea.setColor(color);
				
			} else if (e.getSource() == setBackgroundBtn) {
				
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
//					System.out.println(imagePath);
					
					BufferedImage bimg = null;
					
					try {
						
						bimg = ImageIO.read(new File(imagePath));
						
					} catch (IOException e2) {
						System.err.println("From Paint, IO error");
//						e2.printStackTrace();
					}
					
//					ImageIcon imageIcon = new ImageIcon(imagePath);
//					Image image = imageIcon.getImage(); // transform it
					try {
						drawArea.setBackgroundImage(bimg);
					} catch (IOException e1) {
						System.err.println("From Paint, IO error, setBackgroundImage");
//						e1.printStackTrace();
					}
				}
			}
		}
		
	}
	
	public File getSketch() {
		return sketch;
	}

	public void setSketch(File sketch) {
		this.sketch = sketch;
	}

}