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

import models.Allergy;
import models.Drug;

public class DrugTableGatewayMySQL implements DrugTableGateway {

	private Connection conn = null;

	public DrugTableGatewayMySQL() throws GatewayException, IOException {
		
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
