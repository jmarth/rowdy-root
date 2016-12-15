package controller;

import java.io.IOException;
import java.util.List;

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
					PatientTableGatewaySQLite pgateway1 = new PatientTableGatewaySQLite();
					pgateway1.updatePatient((Patient) m.getData());
					Patient p = (Patient) m.getData();
					if(checkcurrentpatient(p.getId()) == true){
						shownotified("Current patient Demographics update!");						
					}
					break;
					
				case PATIENT_INSERT:
					PatientTableGatewaySQLite pgateway = new PatientTableGatewaySQLite();
					pgateway.insertPatient((Patient) m.getData());
					Patient pp = (Patient) m.getData();
					if(checkcurrentpatient(pp.getId()) == false){
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
					System.out.println("receive notification addddddddd");
					Vital v = (Vital) m.getData();
					if(checkcurrentpatient(v.getPid()) == true){
						HomeView hv = EMRS.notification.getHomeview();
						hv.getMasterModel().getVitalsL().insert(v);
						shownotified("Patient Vital inserted on " + v.getDateCreated());
					}else{
						VitalsTableGateway myGateway = new VitalsTableGatewaySQLite();
						myGateway.insertVitals(v);
					}
					break;
				case VITAL_UPDATE:
					System.out.println("receive notification addddddddd");
					Vital vv = (Vital) m.getData();
					VitalsTableGateway myGateway = new VitalsTableGatewaySQLite();
					myGateway.updateVitals(vv);
					if(checkcurrentpatient(vv.getPid()) == true){
						HomeView hv = EMRS.notification.getHomeview();
						List<Vital> vl = hv.getMasterModel().getVitalsL().getMyList();
						int i;
						for(i =0;i<vl.size();i++){
							if(vv.getPid()== vl.get(i).getPid() && vv.getDateCreated().compareTo(vl.get(i).getDateCreated())==0){
								vl.remove(i);
								vl.add(i, vv);
								break;
							}
								
						}
						shownotified("Patient Vital update on " + vv.getDateCreated());
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
