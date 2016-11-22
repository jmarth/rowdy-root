package controller;

import java.io.IOException;

import database.GatewayException;
import database.PatientTableGatewaySQLite;
import models.Patient;
import networksetup.message;
import views.HomeView;

public class rminotification {
	
	public transient static final int PATIENT_INSERT =8;
	public transient static final int PATIENT_UPDATE =9;
	public transient static final int PATIENT_DELETE =10;
	public static void messageaction(message m){
		try{
			switch (m.getCommand()){
				case PATIENT_INSERT:
					PatientTableGatewaySQLite pgateway = new PatientTableGatewaySQLite();
					pgateway.insertPatient((Patient) m.getData());
					break;
				case PATIENT_UPDATE:
					PatientTableGatewaySQLite pgateway1 = new PatientTableGatewaySQLite();
					pgateway1.updatePatient((Patient) m.getData());
					break;
				case PATIENT_DELETE:
					break;
			}
		}catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
