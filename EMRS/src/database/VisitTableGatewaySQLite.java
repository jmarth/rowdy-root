package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	 * Fetch all visits from db
	 * @return list of visits
	 * @throws GatewayException
	 */
	public List<Visit> fetchVisits() throws GatewayException {
		
		ArrayList<Visit> visits = new ArrayList<Visit>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			System.out.print("getting info");
			
			st = conn.prepareStatement("select * from visits");
			rs = st.executeQuery();
			
			System.out.print("\ninfo loaded");
			
			//add each to list of parts to return
			while(rs.next()) {
				
				System.out.print("\ncreating visit object");
				
				Visit v = new Visit(rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("chiefComplaint"),
						rs.getDouble("autorefractionOdSphere"),
						rs.getDouble("autorefractionOdCylinder"),
						rs.getDouble("autorefractionOdAxis"),
						rs.getDouble("autorefractionOsSphere"),
						rs.getDouble("autorefractionOsCylinder"),
						rs.getDouble("autorefractionOsdAxis"),
						rs.getDouble("arcOdSphere"),
						rs.getDouble("arcOdCylinder"),
						rs.getDouble("arcOdAxis"),
						rs.getDouble("arcOsSphere"),
						rs.getDouble("arcOsCylinder"),
						rs.getDouble("arcOsAxis"),
						rs.getDouble("feRow1Col1"),
						rs.getDouble("feRow1Col2"),
						rs.getDouble("feRow2Col1"),
						rs.getDouble("feRow2Col2"),
						rs.getString("assessment"),
						rs.getString("dateCreated"));
				
				System.out.print("\nvisit object created");
				
				visits.add(v);
				
				System.out.print("\nvisit object added");
				
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
	 * Fetch visits from db for patient
	 * @return list of visits for patient
	 * @throws GatewayException
	 */
	public List<Visit> fetchVisitsForPatinet(Patient p) throws GatewayException {
		
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
				Visit v = new Visit(rs.getLong("id"),
						rs.getLong("pid"),
						rs.getString("chiefComplaint"),
						rs.getDouble("autorefractionOdSphere"),
						rs.getDouble("autorefractionOdCylinder"),
						rs.getDouble("autorefractionOdAxis"),
						rs.getDouble("autorefractionOsSphere"),
						rs.getDouble("autorefractionOsCylinder"),
						rs.getDouble("autorefractionOsdAxis"),
						rs.getDouble("arcOdSphere"),
						rs.getDouble("arcOdCylinder"),
						rs.getDouble("arcOdAxis"),
						rs.getDouble("arcOsSphere"),
						rs.getDouble("arcOsCylinder"),
						rs.getDouble("arcOsAxis"),
						rs.getDouble("feRow1Col1"),
						rs.getDouble("feRow1Col2"),
						rs.getDouble("feRow2Col1"),
						rs.getDouble("feRow2Col2"),
						rs.getString("assessment"),
						rs.getString("dateCreated"));
				
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
			st = conn.prepareStatement("insert INTO visits (pid,"
					+ "chiefComplaint,"
					+ " autorefractionOdSphere,"
					+ " autorefractionOdCylinder,"
					+ " autorefractionOdAxis,"
					+ " autorefractionOsSphere,"
					+ " autorefractionOsCylinder,"
					+ " autorefractionOsdAxis,"
					+ " arcOdSphere,"
					+ " arcOdCylinder,"
					+ " arcOdAxis,"
					+ " arcOsSphere,"
					+ " arcOsCylinder,"
					+ " arcOsAxis,"
					+ " feRow1Col1,"
					+ " feRow1Col2,"
					+ " feRow2Col1,"
					+ " feRow2Col2, "
					+ " assessment) "
					+ " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, v.getPid());
			st.setString(2, v.getChiefComplaint());
			st.setDouble(3, v.getAutorefractionOdSphere());
			st.setDouble(4,  v.getAutorefractionOdCylinder());
			st.setDouble(5,  v.getAutorefractionOdAxis());
			st.setDouble(6, v.getAutorefractionOsSphere());
			st.setDouble(7, v.getAutorefractionOsCylinder());
			st.setDouble(8, v.getAutorefractionOsdAxis());	
			st.setDouble(9, v.getArcOdSphere());
			st.setDouble(10, v.getArcOdCylinder());
			st.setDouble(11, v.getArcOdAxis());
			st.setDouble(12, v.getArcOsSphere());
			st.setDouble(13, v.getArcOsCylinder());
			st.setDouble(14, v.getArcOsAxis());
			st.setDouble(15, v.getFeRow1Col1());
			st.setDouble(16, v.getFeRow1Col2());
			st.setDouble(17, v.getFeRow2Col1());
			st.setDouble(18, v.getFeRow2Col2());
			st.setString(19, v.getAssessment());
	
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
		// TODO Auto-generated method stub
		
	}

}