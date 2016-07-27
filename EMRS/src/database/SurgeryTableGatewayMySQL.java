package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import models.Patient;
import models.Surgery;

public class SurgeryTableGatewayMySQL {
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public SurgeryTableGatewayMySQL() throws GatewayException, IOException {

		//read the properties file to establish the db connection
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
	 * Fetch all Surgeries in the DB
	 * @throws GatewayException 
	 */
	public List<Surgery> fetchSurgeries() throws GatewayException{
		
		ArrayList<Surgery> surgeries = new ArrayList<Surgery>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch Surgeries
			st = conn.prepareStatement("select * from surgeries");
			rs = st.executeQuery();
			
			//add each to list of surgeries to return
			while(rs.next()) {
				Surgery tmpSurgery = new Surgery(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("title"),
						rs.getString("body")
						);
				surgeries.add(tmpSurgery);
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
		
		return surgeries;
	}
	
	/**
	 * Fetch all Surgeries for a Patient in the DB
	 * @throws GatewayException 
	 */
	public List<Surgery> fetchSurgeriesForPatient(Patient p) throws GatewayException{
		
		ArrayList<Surgery> surgeries = new ArrayList<Surgery>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch Surgeries
			st = conn.prepareStatement("select * from Surgeries WHERE pid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			//add each to list of surgeries to return
			while(rs.next()) {
				Surgery tmpSurgery = new Surgery(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("title"),
						rs.getString("body")
						);
				surgeries.add(tmpSurgery);
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
		
		return surgeries;
	}
	
	/**
	 * Insert Allergy into Database
	 */
	public long insertSurgery(Surgery s) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("insert INTO surgeries (pid,"
					+ " title,"
					+ "body,"
					+ " values ( ?, ?, ?) ", PreparedStatement.RETURN_GENERATED_KEYS);
			st.setLong(1, s.getPid());
			st.setString(2, s.getTitle());
			st.setString(3, s.getBody());
	
			st.executeUpdate();
			
			//get the generated key
			rs = st.getGeneratedKeys();
			
			if(rs != null && rs.next()) {
				
			    newId = rs.getLong(1);			    
			} else {
				throw new GatewayException("Could not insert new record.");
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			//clean up
			try {
				if(st != null)
					st.close();
				
			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
		
		return newId;
	}
	
	/**
	 * create a MySQL data source with credentials and DB URL in db.properties file
	 * @return
	 * @throws RuntimeException
	 * @throws IOException
	 */
	private DataSource getDataSource() throws RuntimeException, IOException {
		//read DB credentials from properties file
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
