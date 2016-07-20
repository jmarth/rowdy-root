package controller;

import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import views.LoginView;

import org.apache.logging.log4j.*;

public class EMRS {

	private static final Logger log = LogManager.getLogger(EMRS.class);

	public static void main(String[] args) {

		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();

		} catch (InstantiationException e1) {

			e1.printStackTrace();

		} catch (IllegalAccessException e1) {

			e1.printStackTrace();

		} catch (UnsupportedLookAndFeelException e1) {

			e1.printStackTrace();
		}

		log.info("Launching Application");

		LoginView login = new LoginView();
		
		try {
			
			login.newWindow();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
