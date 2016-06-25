package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import models.DrawArea;
 
public class Paint extends JFrame{
 
  private JButton clearBtn, colorPickerBtn;
  private DrawArea drawArea;
  private JPanel contentPane;
  ActionListener actionListener = new ActionListener() {
 
  public void actionPerformed(ActionEvent e) {
      if (e.getSource() == clearBtn) {
        drawArea.clear();
      } else if (e.getSource() == colorPickerBtn) {
    	Color color = JColorChooser.showDialog(null, "JColorChooser Sample", Color.BLACK);
    	drawArea.setColor(color);
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
 
    // create controls to apply colors and call clear feature
    JPanel controls = new JPanel();
 
    clearBtn = new JButton("Clear");
    clearBtn.addActionListener(actionListener);
    colorPickerBtn = new JButton("Color Picker");
    colorPickerBtn.addActionListener(actionListener);
 
    // add to panel
    controls.add(colorPickerBtn);
    controls.add(clearBtn);
 
    // add to content pane
    contentPane.add(controls, BorderLayout.NORTH);
  }
  
  public Container getContentPane() {
	  return contentPane;
  }
 
}