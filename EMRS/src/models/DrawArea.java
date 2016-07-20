package models;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DrawArea extends JComponent {

	// Image in which we're going to draw
	private Image image;

	// Graphics2D object ==> used to draw on
	private Graphics2D g2;

	// Mouse coordinates
	private int currentX, currentY, oldX, oldY;

	//private BufferedImage bimage;

	public DrawArea() {

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

				// coord x,y when drag mouse
				currentX = e.getX();
				currentY = e.getY();

				if (g2 != null) {

					// draw line if g2 context not null
					g2.drawLine(oldX, oldY, currentX, currentY);

					// refresh draw area to repaint
					repaint();

					// store current coords x,y as olds x,y
					oldX = currentX;
					oldY = currentY;
				}
			}
		});
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (image == null) {

			// image to draw null ==> we create
			image = createImage(getSize().width, getSize().height);

			g2 = (Graphics2D) image.getGraphics();

			// enable antialiasing
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			setPreferredSize((new Dimension(getSize().width, getSize().height)));

			// clear draw area
			clear();
		}

		g.drawImage(image, 0, 0, getSize().width, getSize().height, null);
	}

	// now we create exposed methods
	public void clear() {

		g2.setPaint(Color.white);

		// draw white on entire draw area to clear
		g2.fillRect(0, 0, getSize().width, getSize().height);
		g2.setPaint(Color.black);

		repaint();
	}

	public void setColor(Color color) {
		g2.setPaint(color);
	}

	public void setBackgroundImage(BufferedImage image) throws IOException {

		int width = image.getWidth();
		int height = image.getHeight();

		double scaleSize = 1;

		g2.setPaint(Color.white);

		// draw white on entire draw area to clear
		g2.fillRect(0, 0, getSize().width, getSize().height);

		// scale image if to large
		if (height > getHeight()) {
			scaleSize = (double) getHeight() / height;
			height *= scaleSize;
			width *= scaleSize;
		}
		if (width > getWidth()) {
			scaleSize = getWidth() / width;
			height *= scaleSize;
			width *= scaleSize;
		}

		g2.drawImage(image, 0, 0, width, height, null);
		g2.setPaint(Color.black);

		repaint();
	}
}
