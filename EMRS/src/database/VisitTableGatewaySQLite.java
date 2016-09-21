package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Patient;
import models.Visit;

public class VisitTableGatewaySQLite implements VisitTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public VisitTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch visits from DB for patient
	 * @return list of visits for patient
	 * @throws GatewayException
	 */
	public List<Visit> fetchVisitsForPatient(Patient p) throws GatewayException {
		
		ArrayList<Visit> visits = new ArrayList<Visit>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from visits where pid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			//add each to list of parts to return
			while(rs.next()) {
				Visit v = new Visit(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("chiefComplaint"),
						rs.getString("assessment"),
						rs.getString("plan"),
						rs.getString("dateCreated")
						);
				
				visits.add(v);
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
		
		return visits;
	}
	
	/**
	 * Inserts visit into visits table
	 */
	public long insertVisit(Visit v) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"insert INTO visits"
					+ "(pid,"
					+ " chiefComplaint,"
					+ " assessment,"
					+ " plan) "
					+ " values ( ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, v.getPid());
			st.setString(2, v.getChiefComplaint());
			
			st.setString(3, v.getAssessment());
			st.setString(4, v.getPlan());
	
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

	public void close() {
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Object> fetchVisitsCols(long id) throws GatewayException {
		ArrayList<Object> row = new ArrayList<Object>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select * from visits where id=?");
			st.setLong(1, id);
			
			rs = st.executeQuery();
			
			//get metadata
		    ResultSetMetaData meta = null;
		    meta = rs.getMetaData();
		    
		    int colCount = meta.getColumnCount();
		    //System.out.println("==== ======" + colCount);
			
			while(rs.next()) {
				for (int i = 3; i <= colCount; i++) {
					row.add(rs.getObject(i));
//					System.out.println("column #"+ i + " : " + rs.getObject(i));
				}
			}
			//System.out.println("\n****************\n    ROW:"+row.toString());
			
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
		
		return row;
	}

	@Override
	public void updateVisit(Visit a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVisit(long id) {
		// TODO Auto-generated method stub
		
	}

}
