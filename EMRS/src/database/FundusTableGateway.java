package database;

import models.FundusExam;

public interface FundusTableGateway {

	public long insertFundusExam(FundusExam v) throws GatewayException;

	public FundusExam fetchFundusExamForVisit(long vid) throws GatewayException;

	public void updateFundusExamForVisit(long vid) throws GatewayException;

	public void removeFundusExam(Long vid) throws GatewayException;
}
