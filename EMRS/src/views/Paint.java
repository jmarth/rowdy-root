package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
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
 
  public Paint() {
    contentPane = new JPanel();
    // set layout on content pane
    contentPane.setLayout(new BorderLayout());
    
    // create draw area
    drawArea = new DrawArea();

    // add to content pane
    contentPane.add(drawArea, BorderLayout.CENTER);
    //scrollPane.add(drawArea);
 
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