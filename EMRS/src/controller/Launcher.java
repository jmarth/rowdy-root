package controller;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import database.GatewayException;
import database.PatientTableGateway;
import database.PatientTableGatewayMySQL;
import models.Patient;
import models.PatientList;
import views.Login;

public class Launcher {
	public static void main(String[] args) {
		
		try {
	        UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		//initComponents();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Login login = new Login();
				try {
					login.newWindow();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
