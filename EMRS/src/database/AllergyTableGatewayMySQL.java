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
import models.Allergy;
import models.Patient;

public class AllergyTableGatewayMySQL implements AllergyTableGateway {
	
	private static final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final boolean DEBUG = true;
	private static final int QUERY_TIMEOUT = 70;//query timeout threshold in seconds
	private static final Random roller = new Random();
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public AllergyTableGatewayMySQL() throws GatewayException, IOException {
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
	 * Fetch all Allergies in the DB
	 * @throws GatewayException 
	 */
	public List<Allergy> fetchAllergies() throws GatewayException{
		ArrayList<Allergy> allergies = new ArrayList<Allergy>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//fetch Allergies
			st = conn.prepareStatement("select * from allergies");
			rs = st.executeQuery();
			//add each to list of allergies to return
			while(rs.next()) {
				Allergy tmpAllergy = new Allergy(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("allergy"),
						rs.getString("severity"),
						rs.getString("adverse_reaction")
						);
				allergies.add(tmpAllergy);
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
			return allergies;
	}
	
	/**
	 * Fetch all Allergies for a Patient in the DB
	 * @throws GatewayException 
	 */
	public List<Allergy> fetchAllergiesForPatient(Patient p) throws GatewayException{
		ArrayList<Allergy> allergies = new ArrayList<Allergy>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//fetch Allergies
			st = conn.prepareStatement("select * from allergies WHERE pid=?");
			st.setLong(1, p.getId());
			rs = st.executeQuery();
			//add each to list of allergies to return
			while(rs.next()) {
				Allergy tmpAllergy = new Allergy(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("allergy"),
						rs.getString("severity"),
						rs.getString("adverse_reaction")
						);
				allergies.add(tmpAllergy);
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
			return allergies;
	}
	
	/**
	 * Insert Allergy into Database
	 */
	public long insertAllergy(Allergy a) throws GatewayException {
		//init new id to invalid
		long newId = 0;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("insert INTO allergies (pid,"
					+ " allergy,"
					+ " severity,"
					+ " adverse_reaction) "
					+ " values ( ?, ?, ?, ? ) ", PreparedStatement.RETURN_GENERATED_KEYS);
			//st.setInt(1, p.getHasPatientName() ? 1 : 0);
			st.setLong(1, a.getPid());
			st.setString(2, a.getAllergy());
			st.setString(3, a.getSeverity());
			st.setString(4, a.getAdverseReaction());
	
			st.executeUpdate();
			//get the generated key
			rs = st.getGeneratedKeys();
			if(rs != null && rs.next()) {
			    newId = rs.getLong(1);
			    System.out.println("Allergy is ID: " + newId + "");
			} else {
				throw new GatewayException("Could not insert new record.");
			}
		} catch (SQLException e) {
			//e.printStackTrace();
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
	 * create a MySQL datasource with credentials and DB URL in db.properties file
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
        
        //create the datasource
        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
        mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
        mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
        return mysqlDS;
	}

}
