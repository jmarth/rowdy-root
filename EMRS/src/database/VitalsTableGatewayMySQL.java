package database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
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

import models.Patient;
import models.Vitals;

public class VitalsTableGatewayMySQL implements VitalsTableGateway {
	
	//private static final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	//private static final boolean DEBUG = true;
	//private static final int QUERY_TIMEOUT = 60;//query timeout threshold in seconds
	
	private Connection conn = null;
	
	public VitalsTableGatewayMySQL() throws GatewayException, IOException {
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
			throw new GatewayException("SQL Error: \n" + e.getMessage());
		}
	}

	@Override
	public List<Vitals> fetchVitals() throws GatewayException {
		
		
		ArrayList<Vitals> vitals = new ArrayList<Vitals>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//fetch Vitals
			st = conn.prepareStatement("select * from vitals");
			rs = st.executeQuery();
			//add each to list of vitals to return
			while(rs.next()) {
				Vitals tmpVitals = new Vitals(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getFloat("bps"),
						rs.getFloat("bpd"),
						rs.getString("bpUnit").charAt(0),
						rs.getInt("hFeet"),
						rs.getInt("hInches"),
						rs.getInt("hcm"),
						rs.getString("hUnit").charAt(0),
						rs.getFloat("weight"),
						rs.getString("wUnit").charAt(0),
						rs.getString("notes")
						);
				vitals.add(tmpVitals);
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
			return vitals;
	}

	@Override
	public List<Vitals> fetchVitalsForPatient(Patient p) throws GatewayException {
		
		
		return null;
	}

	@Override
	public long insertVitals(Vitals v) throws GatewayException {
		return 0;
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
