package controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import database.GatewayException;
import database.PatientTableGatewaySQLite;
import database.SurgeryTableGatewaySQLite;
import database.VitalsTableGateway;
import database.VitalsTableGatewaySQLite;
import models.Patient;
import models.Surgery;
import models.Vital;
import networksetup.message;
import views.HomeView;

public class rminotification {
	
	public transient static final int PATIENT_INSERT =8;
	public transient static final int PATIENT_UPDATE =9;
	public transient static final int PATIENT_DELETE =10;
	public transient static final int SURGERY_INSERT=11;
	public transient static final int VITAL_INSERT=12;
	public transient static final int VITAL_UPDATE=13;
	public static void messageaction(message m){
		try{
			switch (m.getCommand()){
				case PATIENT_UPDATE:
				case PATIENT_INSERT:
					PatientTableGatewaySQLite pgateway = new PatientTableGatewaySQLite();
					pgateway.insertPatient((Patient) m.getData());
					PatientTableGatewaySQLite pgateway1 = new PatientTableGatewaySQLite();
					pgateway1.updatePatient((Patient) m.getData());
					Patient p = (Patient) m.getData();
					if(checkcurrentpatient(p.getId()) == true){
						shownotified("Current patient Demographics update!");						
					}else if(p==null && EMRS.notification.getHomeview()!=null){
						shownotified("New patient inserted");
					}
					
					break;
				case PATIENT_DELETE:
					break;
				case SURGERY_INSERT:
					Surgery s = (Surgery) m.getData();
					if(checkcurrentpatient(s.getPid()) == true){
						HomeView hv = EMRS.notification.getHomeview();
						hv.getMasterModel().getsL().insert(s);
						shownotified("New Surgical Procedure inserted!");
					}else{
						SurgeryTableGatewaySQLite myGateway = new SurgeryTableGatewaySQLite();
						myGateway.insertSurgery(s);
					}
					break;
				case VITAL_INSERT:
				case VITAL_UPDATE:
					System.out.println("receive notification addddddddd");
					Vital v = (Vital) m.getData();
					if(checkcurrentpatient(v.getPid()) == true){
						HomeView hv = EMRS.notification.getHomeview();
						hv.getMasterModel().getVitalsL().insert(v);
						shownotified("Patient Vital got modified on " + v.getDateCreated());
					}else{
						VitalsTableGateway myGateway = new VitalsTableGatewaySQLite();
						myGateway.insertVitals(v);
					}
					break;
				default:
			}
		}catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static private 	boolean checkcurrentpatient(long pid){
		HomeView hv = EMRS.notification.getHomeview();
		if(hv!=null){
			Patient p = hv.getMasterModel().getCurrPatient();
			if(p!=null){
				if(p.getId() == pid)
					return true;
			}
			return false;	
		}
		return false;
	}
	static private void shownotified(String message){	
		JOptionPane.showMessageDialog(null, message);
	}
	
	
}
