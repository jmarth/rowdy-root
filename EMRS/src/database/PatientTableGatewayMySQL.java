package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
//import java.util.Random;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import models.Patient;

public class PatientTableGatewayMySQL implements PatientTableGateway {

	// private static final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	// private static final boolean DEBUG = true;
	// private static final int QUERY_TIMEOUT = 70; //query timeout threshold in seconds
	// private static final Random roller = new Random();

	/**
	 * external DB connection
	 */
	private Connection conn = null;

	/**
	 * Constructor: creates database connection
	 * 
	 * @throws GatewayException
	 * @throws IOException
	 */
	public PatientTableGatewayMySQL() throws GatewayException, IOException {

		// read the properties file to establish the db connection
		DataSource ds = null;

		try {
			ds = getDataSource();
		} catch (RuntimeException e) {
			throw new GatewayException(e.getMessage());
		}
		if (ds == null) {
			throw new GatewayException("Datasource is null!");
		}
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new GatewayException("SQL Error: " + e.getMessage());
		}
	}

	/**
	 * create a MySQL data source with credentials and DB URL in db.properties
	 * file
	 * 
	 * @return
	 * @throws RuntimeException
	 * @throws IOException
	 */
	private DataSource getDataSource() throws RuntimeException, IOException {

		// read DB credentials from properties file
		Properties props = new Properties();
		FileInputStream fis = null;
		fis = new FileInputStream("db.properties");
		props.load(fis);
		fis.close();

		// create the datasource
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
		mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
		mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
		return mysqlDS;
	}

	/**
	 * Fetch a resultset of all patient rows in DB and populate a collection
	 * with them. All patient instances are given a reference to this Gateway.
	 * 
	 * @return a List of patient objects (ArrayList)
	 * @throws GatewayException
	 */
	public List<Patient> fetchPatients() throws GatewayException {

		ArrayList<Patient> patients = new ArrayList<Patient>();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			// fetch patients
			st = conn.prepareStatement("select * from patients");

			rs = st.executeQuery();

			// add each to list of patients to return
			while (rs.next()) {
				Patient p = new Patient(rs.getLong("id"),
						rs.getBoolean("unidentified_patient"),
						rs.getString("first_name"),
						rs.getString("middle"),
						rs.getString("last_name"),
						rs.getString("gender"),
						rs.getInt("birth_day"),
						rs.getString("birth_month"),
						rs.getInt("birth_year"),
						rs.getInt("estimated_birth_years"),
						rs.getInt("estimated_birth_months"),
						rs.getString("address"), rs.getString("address_2"),
						rs.getString("city_village"),
						rs.getString("state_province"),
						rs.getString("country"),
						rs.getString("postal_code"),
						rs.getString("phone_number"),
						rs.getString("pic_path"));
				
				patients.add(p);
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			// clean up
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();

			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}

		return patients;
	}

	public long insertPatient(Patient p) throws GatewayException {

		// init new id to invalid
		long newId = 0;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"insert INTO patients (unidentified_patient,"
							+ " first_name,"
							+ " middle,"
							+ " last_name,"
							+ " gender,"
							+ " birth_day,"
							+ " birth_month,"
							+ " birth_year,"
							+ " estimated_birth_years,"
							+ " estimated_birth_months,"
							+ " address,"
							+ " address_2,"
							+ " city_village,"
							+ " state_province,"
							+ " country,"
							+ " postal_code,"
							+ " phone_number, "
							+ " pic_path) "
							+ " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, p.getHasPatientName() ? 1 : 0);
			st.setString(2, p.getFirstName());
			st.setString(3, p.getMiddleName());
			st.setString(4, p.getLastName());
			st.setString(5, p.getGender());
			st.setInt(6, p.getBirthDay());
			st.setString(7, p.getBirthMonth());
			st.setInt(8, p.getBirthYear());
			st.setInt(9, p.getEstBirthYears());
			st.setInt(10, p.getEstBirthMonths());
			st.setString(11, p.getAddress());
			st.setString(12, p.getAddress2());
			st.setString(13, p.getCity());
			st.setString(14, p.getState());
			st.setString(15, p.getCountry());
			st.setString(16, p.getPostalCode());
			st.setString(17, p.getPhoneNumber());
			st.setString(18, p.getPicPath());

			st.executeUpdate();

			// get the generated key
			rs = st.getGeneratedKeys();

			if (rs != null && rs.next()) {
				newId = rs.getLong(1);
			} else {
				throw new GatewayException("Could not insert new record.");
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new GatewayException(e.getMessage());
		} finally {
			// clean up
			try {
				if (st != null)
					st.close();

			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}

		return newId;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
