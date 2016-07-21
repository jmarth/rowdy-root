package controller;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import database.AllergyTableGateway;
import database.AllergyTableGatewayMySQL;

import database.VisitTableGateway;
import database.VisitTableGatewayMySQL;
import models.AllergyList;
import models.Patient;
import models.PatientList;
import models.VisitList;
import views.LoginView;

import org.apache.logging.log4j.*;

public class EMRS {
	
	private static final Logger logger = LogManager.getLogger(EMRS.class);
	
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
