package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Account;

public class AccountTableGatewaySQLite implements AccountTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: Creates DB connection
	 * 
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public AccountTableGatewaySQLite() throws GatewayException, IOException {
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch all Account rows in the DB
	 * 
	 * @return ArrayList of Accounts
	 * @throws GatewayException
	 */
	public List<Account> fetchAccounts() throws GatewayException {
		
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch Accounts
			st = conn.prepareStatement("select * from accounts");
			rs = st.executeQuery();
			
			//add each to list of parts to return
			while(rs.next()) {
				Account tmpAccount = new Account(rs.getLong("id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("firstName"),
						rs.getString("middleName"),
						rs.getString("lastName"),
						rs.getString("gender"),
						rs.getString("role"));
				accounts.add(tmpAccount);
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
		
		return accounts;
	}
}
