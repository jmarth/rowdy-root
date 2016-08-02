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

import models.Med;
import models.Patient;

/** mysql gateway
 * 
 * @author Mark
 *
 */

public class MedicationsTableGatewayMySQL {

	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public MedicationsTableGatewayMySQL() throws GatewayException, IOException {

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
	 * Fetch all meds in the DB
	 * @throws GatewayException 
	 */
	public List<Med> fetchMeds() throws GatewayException{
		
		ArrayList<Med> meds = new ArrayList<Med>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch Meds
			st = conn.prepareStatement("select * from medications");
			rs = st.executeQuery();
			
			//add each to list of meds to return
			while(rs.next()) {
				Med tmpMed = new Med(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("name"),
						rs.getString("date"),
						rs.getString("reason")
						);
				meds.add(tmpMed);
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
		
		return meds;
	}
	
	/**
	 * Fetch all Meds for a Patient in the DB
	 * @throws GatewayException 
	 */
	public List<Med> fetchMedsForPatient(Patient p) throws GatewayException{
		
		ArrayList<Med> meds = new ArrayList<Med>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch Meds
			st = conn.prepareStatement("select * from medications WHERE pid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			//add each to list of Meds to return
			while(rs.next()) {
				Med tmpMed = new Med(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("name"),
						rs.getString("date"),
						rs.getString("reason")
						);
				meds.add(tmpMed);
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
		
		return meds;
	}
	
	/**
	 * Insert Med into Database
	 */
	public long insertMed(Med m) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("insert INTO meds (pid,"
					+ " name,"
					+ " date,"
					+ " reason) "
					+ " values ( ?, ?, ?, ? ) ", PreparedStatement.RETURN_GENERATED_KEYS);
			//st.setInt(1, p.getHasPatientName() ? 1 : 0);
			st.setLong(1, m.getPid());
			st.setString(2, m.getName());
			st.setString(3, m.getDate());
			st.setString(4, m.getReason());
	
			st.executeUpdate();
			
			//get the generated key
			rs = st.getGeneratedKeys();
			
			if(rs != null && rs.next()) {
				
			    newId = rs.getLong(1);
			    
			    System.out.println("Med is ID: " + newId + "");
			    
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
	 * Update an Med in the DB
	 * @param m Med to update
	 */
	public void updateMed(Med m) throws GatewayException{
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("UPDATE medications SET"
					+ " name = ?,"
					+ " date = ?,"
					+ " reason = ?"
					+ " WHERE id = ? ", PreparedStatement.RETURN_GENERATED_KEYS);
			
			//st.setInt(1, p.getHasPatientName() ? 1 : 0);
			st.setString(1, m.getName());
			st.setString(2, m.getDate());
			st.setString(3, m.getReason());
			st.setLong(4, m.getId());

			st.executeUpdate();
			
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
		
	}
	
	/**
	 * Remove an Med from the DB
	 * @param a Med to update
	 */
	public void removeMed(Long mid) throws GatewayException{
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM medications"
					+ " WHERE id = ? ", PreparedStatement.RETURN_GENERATED_KEYS);
			//st.setInt(1, p.getHasPatientName() ? 1 : 0);
			st.setLong(1, mid);

			st.executeUpdate();
			
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
