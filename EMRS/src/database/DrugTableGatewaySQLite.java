package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Drug;

public class DrugTableGatewaySQLite implements DrugTableGateway {
	
	private Connection conn = null;

	public DrugTableGatewaySQLite() throws GatewayException, IOException {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Drug> fetchDrugs() throws GatewayException {
		ArrayList<Drug> drugs = new ArrayList<Drug>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch Drugs
			st = conn.prepareStatement("select * from drugs");
			rs = st.executeQuery();
			
			//add each to list of drugs to return
			while(rs.next()) {
				Drug tmpDrug = new Drug(
						rs.getString("PROPRIETARYNAME"),
						rs.getString("NONPROPRIETARYNAME")
						);
				drugs.add(tmpDrug);
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			//clean up
			try {
				if(rs != null)
					rs.close();
				
				if(st != null)
					st.close();
				
			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
		
		return drugs;
	}

	@Override
	public Drug fetchDrug(String trade) throws GatewayException {
		Drug tmpDrug = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select * from drugs WHERE PROPRIETARYNAME=?");
			st.setString(1, trade);
			
			rs = st.executeQuery();
			
			if (rs.next()) {
				tmpDrug = new Drug(
						rs.getString("PROPRIETARYNAME"),
						rs.getString("NONPROPRIETARYNAME"));
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			
			try {
				if(rs != null)
					rs.close();
				
				if(st != null)
					st.close();
				
			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
		
		return tmpDrug;
	}

	@Override
	public List<Drug> searchByPrefix(String prefix) throws GatewayException {
		ArrayList<Drug> tmpList = new ArrayList<Drug>();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM drugs WHERE PROPRIETARYNAME LIKE " + "'" + prefix + "%'" +  "or NONPROPRIETARYNAME LIKE" + "'" + prefix + "%'");
			
			rs = st.executeQuery();
			
			while 
			(rs.next()) {
				Drug tmp = new Drug(
						rs.getString("PROPRIETARYNAME"),
						rs.getString("NONPROPRIETARYNAME")
						);
				tmpList.add(tmp);
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			//clean up
			try {
				if(rs != null)
					rs.close();
				
				if(st != null)
					st.close();
				
			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
		
		return tmpList;
	}

}
