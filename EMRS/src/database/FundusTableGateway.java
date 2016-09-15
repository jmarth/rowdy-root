package database;

import java.util.ArrayList;
import java.util.List;

import models.Patient;
import models.FundusExam;

public interface FundusTableGateway {
	public long insertFundusExam(FundusExam v) throws GatewayException;
	//public long updateVitals(FundusExam v) throws GatewayException;
	public void removeFundusExam(Long vid) throws GatewayException;
	public abstract ArrayList<Object> fetchFundusExamsForVisit(long id)throws GatewayException;
	public FundusExam fetchFundusExamForVisit(long vid)throws GatewayException;
}
