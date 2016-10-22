package controller;

import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import networksetup.mastercomunication;
import views.LoginView;

public class EMRS {
	
	//TODO
	// medication extends drug
	// visitpanel extends JXTaskPane, during creation to optimize, extend any others to JXTaskPane
	// keep homemodel as a final reference in all views
	// hashmap list to keep track of all lists
	// list models are only ones that use gateways to CRUD DB
	// bilateral symmetry solution: what has changed? highlight changes of document, (no b/c doesn't handle previous 
	
	private static final Logger logger = LogManager.getLogger(EMRS.class);
	public static mastercomunication notification;
	
	public static void main(String[] args) {
		
		/**
		 * Sets look and feel of the swing UI to that of the native operating system.
		 * I.e. The tabs will either be Mac style or Windows style
		 */
		
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
		
		logger.info("Launching Application");
		
		
		// init the login view
		LoginView login = new LoginView();
		
		// set it visible
		try {
			login.newWindow();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
