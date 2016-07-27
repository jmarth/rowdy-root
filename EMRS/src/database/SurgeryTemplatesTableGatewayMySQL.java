package database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.SurgeryTemplate;

public class SurgeryTemplatesTableGatewayMySQL implements SurgeryTemplatesTableGateway {
	
	private Connection conn = null;
	
	public SurgeryTemplatesTableGatewayMySQL() throws GatewayException, IOException {
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
	
	
	
	
	@Override
	public List<SurgeryTemplate> fetchAllSurgeries() throws GatewayException {
		ArrayList<SurgeryTemplate> surgeries = new ArrayList<SurgeryTemplate>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			// fetch all surgery templates from table
			statement = conn.prepareStatement("select * from SurgeryTemplates");
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				SurgeryTemplate tmp = new SurgeryTemplate(
						resultSet.getLong(1),
						resultSet.getString("title"),
						resultSet.getString("description")
						);
				
				surgeries.add(tmp);
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			//clean up
			try {
				if(resultSet != null)
					resultSet.close();
				
				if(statement != null)
					statement.close();
				
			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
		return surgeries;
	}

	@Override
	public long insertSurgery(SurgeryTemplate s) throws GatewayException {
		long newID = 0;
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = conn.prepareStatement("insert INTO SurgeryTemplates (title, description) values ( ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, s.getTitle());
			statement.setString(2, s.getDescription());
			
			statement.executeUpdate();
			
			resultSet = statement.getGeneratedKeys();
			
			if (resultSet != null && resultSet.next()) {
				newID = resultSet.getLong(1);
			} else {
				throw new GatewayException("Could not insert new record.");
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {
			//clean up
			try {
				if(statement != null)
					statement.close();
				
			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
		
		return newID;
	}
	
	private DataSource getDataSource() throws IOException, RuntimeException {
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
