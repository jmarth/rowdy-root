package views;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import models.MasterModel;

import java.awt.BorderLayout;


public class SlideShowPanel extends JPanel implements viewinterface {
	JLabel pic;
	Timer tm;
	int x = 0;

	//Images Path In Array
	String[] list = {
			"home-screen-images/100_4104.jpg",
			"home-screen-images/2007-04-22 13.04.10.jpg",
			"home-screen-images/2007-06-02 18.02.13-1.jpg",
			"home-screen-images/2007-06-02 18.02.18-1.jpg",
			"home-screen-images/2007-06-02 18.02.27-1.jpg",
			"home-screen-images/2007-08-06 00.42.35.jpg",
			"home-screen-images/2007-10-13 15.51.44.jpg",
			"home-screen-images/2008-06-10 10.17.08.jpg",
			"home-screen-images/2008-07-04 22.10.28.jpg",
			"home-screen-images/2008-07-06 11.15.19.jpg",
			"home-screen-images/2009-05-30 19.32.06.jpg",
			"home-screen-images/2009-07-25 12.49.45.jpg",
			"home-screen-images/2010-08-10 13.17.28.jpg",
			"home-screen-images/IMG_0593.jpg",
			"home-screen-images/IMG_1107.jpg",
			"home-screen-images/IMG_2349.jpg",

			// image list
		
    };
	
	public SlideShowPanel(){
	    super();
	    pic = new JLabel();
	    pic.setBounds(0, 0, 1139, 1124);
	    
	    //Call The Function SetImageSize
        //SetImageSize(0);
        

       //set a timer
        tm = new Timer(550000,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("switching");
                SetImageSize(x);
                x += 1;
                if(x >= list.length )
                    x = 0; 
            }
        });
        tm.setInitialDelay(600);
        setLayout(new BorderLayout(0, 0));
        add(pic);
        tm.start();
        setSize(pic.getWidth(), pic.getHeight());
        setVisible(true);
        this.revalidate();
        this.repaint();
    }
	
	 //create a function to resize the image 
    public void SetImageSize(int i){
        ImageIcon icon = new ImageIcon(list[i]);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setIcon(newImc);
    }

	@Override
	public void HideallView() {
		this.setVisible(false);
		
	}

	@Override
	public void ShowView() {
		this.setVisible(true);
		
	}

	@Override
	public MasterModel getMasterModel() {
		// TODO Auto-generated method stub
		return ((HomeView)this.getParent()).getMasterModel();
	}



}
