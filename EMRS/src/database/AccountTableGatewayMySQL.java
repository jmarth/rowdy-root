package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import models.Account;

public class AccountTableGatewayMySQL implements AccountTableGateway {
	
	//private static final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	//private static final boolean DEBUG = true;
	//private static final int QUERY_TIMEOUT = 70;//query timeout threshold in seconds
	//private static final Random roller = new Random();
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public AccountTableGatewayMySQL() throws GatewayException, IOException {
		
		// read the properties file to establish the DB connection
		
		DataSource ds = null;
		
		try {
			ds = getDataSource();
		} catch (RuntimeException e) {
			throw new GatewayException(e.getMessage());
		}
		
		if(ds == null) {
        	throw new GatewayException("Datasource is null!");
        }
		
		try {
        	conn = ds.getConnection();
		} catch (SQLException e) {
			throw new GatewayException("SQL Error: " + e.getMessage());
		}
	}
	
	/**
	 * Fetch all Account rows in the db
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
	
	/**
	 * create a MySQL data source with credentials and DB URL in db.properties file
	 * @return
	 * @throws RuntimeException
	 * @throws IOException
	 */
	private DataSource getDataSource() throws RuntimeException, IOException {
		
		//read db credentials from properties file
		Properties props = new Properties();
		FileInputStream fis = null;
        fis = new FileInputStream("db.properties");
        props.load(fis);
        fis.close();
        
        //create the data source
        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
        mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
        mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
        
        return mysqlDS;
	}

}
