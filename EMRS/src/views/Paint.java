package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.ScrollPane;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.DrawArea;
import javax.swing.JLabel;
import java.awt.Insets;
 
public class Paint extends JFrame{
 
  private JButton clearBtn, colorPickerBtn, setBackgroundBtn;
  private DrawArea drawArea;
  private JPanel contentPane;
  ActionListener actionListener = new ActionListener() {
 
  public void actionPerformed(ActionEvent e) {
      if (e.getSource() == clearBtn) {
        drawArea.clear();
      } else if (e.getSource() == colorPickerBtn) {
    	Color color = JColorChooser.showDialog(null, "JColorChooser Sample", Color.BLACK);
    	drawArea.setColor(color);
      } else if(e.getSource() == setBackgroundBtn) {
    	  JFileChooser file = new JFileChooser();
	      file.setCurrentDirectory(new File(System.getProperty("user.home")));
	      //filter the files
	      FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
	      file.addChoosableFileFilter(filter);
	      int result = file.showSaveDialog(null);
	      //if the user click on save in Jfilechooser
	      if(result == JFileChooser.APPROVE_OPTION){
	        File selectedFile = file.getSelectedFile();
	        String imagePath = selectedFile.getAbsolutePath();
	        System.out.println(imagePath);
	        BufferedImage bimg = null;
			try {
				bimg = ImageIO.read(new File(imagePath));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	        ImageIcon imageIcon = new ImageIcon(imagePath);
	        //Image image = imageIcon.getImage(); // transform it 
	        try {
				drawArea.setBackgroundImage(bimg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }
      }
    }
  };
  private JPanel panel;
  private JLabel lblNewLabel;
  private JButton btnNewButton;
  private JButton btnBrownEye;
  private JButton btnGreenEye;
  private JButton btnBlueEye;
  private JButton btnNewButton_1;
 
  public Paint() {
    contentPane = new JPanel();
	contentPane.setLayout(new BorderLayout());
	
	JScrollPane scrollPane = new JScrollPane();
	
    // create draw area
    drawArea = new DrawArea();
    // add drawArea to scrollpane
    scrollPane.add(drawArea);
    scrollPane.setViewportView(drawArea);
    // add to content pane
    contentPane.add(scrollPane, BorderLayout.CENTER);
    
    panel = new JPanel();
    scrollPane.setRowHeaderView(panel);
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[]{46, 0};
    gbl_panel.rowHeights = new int[]{14, 0, 0, 0, 0, 0, 0};
    gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
    gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    panel.setLayout(gbl_panel);
    
    lblNewLabel = new JLabel("Backgrounds ");
    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
    gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
    gbc_lblNewLabel.gridx = 0;
    gbc_lblNewLabel.gridy = 0;
    panel.add(lblNewLabel, gbc_lblNewLabel);
    
    btnNewButton = new JButton("Circle");
    GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
    gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
    gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
    gbc_btnNewButton.gridx = 0;
    gbc_btnNewButton.gridy = 1;
    panel.add(btnNewButton, gbc_btnNewButton);
    
    btnBrownEye = new JButton("Brown Eye");
    GridBagConstraints gbc_btnBrownEye = new GridBagConstraints();
    gbc_btnBrownEye.insets = new Insets(0, 0, 5, 0);
    gbc_btnBrownEye.fill = GridBagConstraints.HORIZONTAL;
    gbc_btnBrownEye.gridx = 0;
    gbc_btnBrownEye.gridy = 2;
    panel.add(btnBrownEye, gbc_btnBrownEye);
    
    btnGreenEye = new JButton("Hazel Eye");
    GridBagConstraints gbc_btnGreenEye = new GridBagConstraints();
    gbc_btnGreenEye.insets = new Insets(0, 0, 5, 0);
    gbc_btnGreenEye.gridx = 0;
    gbc_btnGreenEye.gridy = 3;
    panel.add(btnGreenEye, gbc_btnGreenEye);
    
    btnBlueEye = new JButton("Blue Eye");
    GridBagConstraints gbc_btnBlueEye = new GridBagConstraints();
    gbc_btnBlueEye.fill = GridBagConstraints.HORIZONTAL;
    gbc_btnBlueEye.insets = new Insets(0, 0, 5, 0);
    gbc_btnBlueEye.gridx = 0;
    gbc_btnBlueEye.gridy = 4;
    panel.add(btnBlueEye, gbc_btnBlueEye);
    
    btnNewButton_1 = new JButton("New button");
    GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
    gbc_btnNewButton_1.gridx = 0;
    gbc_btnNewButton_1.gridy = 5;
    panel.add(btnNewButton_1, gbc_btnNewButton_1);

    // create controls to apply colors and call clear feature
    JPanel controls = new JPanel();
 
    clearBtn = new JButton("Clear");
    clearBtn.addActionListener(actionListener);
    colorPickerBtn = new JButton("Color Picker");
    colorPickerBtn.addActionListener(actionListener);
    setBackgroundBtn = new JButton("Set background image");
    setBackgroundBtn.addActionListener(actionListener);
 
    // add to panel
    controls.add(colorPickerBtn);
    controls.add(setBackgroundBtn);
    controls.add(clearBtn);
 
    // add to content pane
    contentPane.add(controls, BorderLayout.NORTH);
    
    
  }
  
  public Container getContentPane() {
	  return contentPane;
  }
 
}