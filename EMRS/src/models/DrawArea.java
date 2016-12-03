package models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class DrawArea extends JComponent {
	private BufferedImage image;
	private Graphics2D g2;
	private int currX, currY, oldX, oldY;
	private Dimension d;

	public DrawArea(Dimension d) {
		this.d = d;
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// save coord x,y when mouse is pressed
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				currX = e.getX();
				currY = e.getY();
				if (g2 != null) {
					g2.drawLine(oldX, oldY, currX, currY);
					repaint();
					oldX = currX;
					oldY = currY;
				}
			}
		});
		
		image = new BufferedImage(d.width,d.height, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) image.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		setPreferredSize((new Dimension(getSize().width, getSize().height)));
		clear();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		if (image == null) {
//			BufferedImage bi = new BufferedImage(getSize().width,getSize().height, BufferedImage.TYPE_INT_ARGB);
//			g2 = (Graphics2D) image.getGraphics();
//			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			setPreferredSize((new Dimension(getSize().width, getSize().height)));
//			clear();
//		}
		g.drawImage(image, 0, 0, d.width, d.height, null);
	}


	public void setBackgroundImage(BufferedImage bgImage) throws IOException {
		// image = bgImage;
		int width = bgImage.getWidth();
		int height = bgImage.getHeight();

		// double scaleSize = 1;

		// // scale image if to large
		// if (height > getHeight()) {
		// scaleSize = (double) getHeight() / height;
		// height *= scaleSize;
		// width *= scaleSize;
		// }
		// if (width > getWidth()) {
		// scaleSize = getWidth() / width;
		// height *= scaleSize;
		// width *= scaleSize;
		// }

		g2.drawImage(bgImage, 0, 0, width, height, null);

		repaint();
	}

//	public BufferedImage createBufferedImage() {
//		BufferedImage bi = null;
//		if (image == null) {
//			System.err.println("image is null " + image);
//			return null;
//		}
//		bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
//
//		Graphics2D gg = bi.createGraphics();
//		gg.drawImage(image, null, null);
//
//		return bi;
//	}

	public void clear() {
		g2.setPaint(Color.white);
		g2.fillRect(0, 0, d.width, d.height);
		g2.setPaint(Color.black);
		repaint();
	}

	public void setColor(Color color) {
		g2.setPaint(color);
	}

	public BufferedImage getMyBI() {
		BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D gg = bi.createGraphics();
		gg.drawImage(image, null, null);
		return bi;
	}
}