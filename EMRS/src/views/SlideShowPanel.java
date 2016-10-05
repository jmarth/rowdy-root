package views;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import models.MasterModel;

import java.awt.BorderLayout;



public class SlideShowPanel extends JPanel implements viewinterface {
	//JLabel pic;
	//Timer tm;
	private int x = 0;
	private BufferedImage currentimage;

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
	    x=0;
	
	    //Call The Function SetImageSize
        //SetImageSize(0);
       //set a timer 550000
	    Timer tm;
        tm = new Timer(10000,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	//System.out.println("switching");
                //SetImageSize(x);
                x += 1;
                if(x >= list.length )
                    x = 0;
                try {
					currentimage = ImageIO.read(new File(list[x]));
				} catch (IOException e1) {
					System.err.println("can not load image in slideshowview");
					e1.printStackTrace();
				}
                SlideShowPanel.this.repaint();
            }
        });
        tm.setInitialDelay(0);
        tm.start();     
    }
	
	 @Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(currentimage,0,0,
				SlideShowPanel.this.getWidth(),SlideShowPanel.this.getHeight(),null);
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
		return getHomeView().getMasterModel();
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HomeView getHomeView() {
		return (HomeView)SwingUtilities.getRoot(this);
	}
}
