package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import models.SurgeryTemplate;

public class SurgeryTemplatesTableGatewaySQLite implements SurgeryTemplatesTableGateway {

	private Connection conn = null;
	
	public SurgeryTemplatesTableGatewaySQLite() throws GatewayException, IOException {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public List<SurgeryTemplate> fetchAllSurgeryTemplates() throws GatewayException {
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

	public long insertSurgeryTemplate(SurgeryTemplate s) throws GatewayException {
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




	@Override
	public void updateSurgeryTemplate(SurgeryTemplate a) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void removeSurgeryTemplate(long id) {
		// TODO Auto-generated method stub
		
	}

}
