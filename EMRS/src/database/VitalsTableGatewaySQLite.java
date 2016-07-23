package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import models.Vitals;
import models.Patient;

public class VitalsTableGatewaySQLite implements VitalsTableGateway {
	
	private Connection conn = null;
	
	public VitalsTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Vitals> fetchVitals() throws GatewayException {
		
		ArrayList<Vitals> vitals = new ArrayList<Vitals>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			// fetch all the vitals
			st = conn.prepareStatement("select * from vitals");
			rs = st.executeQuery();
			
			// add each to list of vitals to return
			
			while(rs.next()) {
				
				Vitals tmpVitals = new Vitals(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getFloat("bps"),
						rs.getFloat("bpd"),
						rs.getString("bpUnit"),
						rs.getInt("hFeet"),
						rs.getInt("hInches"),
						rs.getInt("hcm"),
						rs.getString("hUnit"),
						rs.getFloat("weight"),
						rs.getString("wUnit"),
						rs.getString("notes")
						);
				
				vitals.add(tmpVitals);
				
			}
			
		} catch (SQLException e) {
			
			throw new GatewayException(e.getMessage());
			
		} finally {
			
			// clean up
			
			try {
				
				if(rs != null)
					rs.close();
				
				if(st != null)
					st.close();
				
			} catch (SQLException e) {
				
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}
			System.out.println("from vitalstg, what was resultset:");
			for (Vitals v: vitals )
				System.out.println(v.getId());
			return vitals;
	}

	@Override
	public List<Vitals> fetchVitalsForPatient(Patient p) throws GatewayException {
		
		ArrayList<Vitals> vitals = new ArrayList<Vitals>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			// fetch all the vitals for a given patient id
			
			st = conn.prepareStatement("select * from vitals WHERE pid=?");
			st.setLong(1, p.getId());
			
			rs = st.executeQuery();
			
			//add each to list of vitals to return
			
			while(rs.next()) {
				
				Vitals tmpVitals = new Vitals(
						rs.getLong("id"),
						rs.getLong("pid"),
						rs.getFloat("bps"),
						rs.getFloat("bpd"),
						rs.getString("bpUnit"),
						rs.getInt("hFeet"),
						rs.getInt("hInches"),
						rs.getInt("hcm"),
						rs.getString("hUnit"),
						rs.getFloat("weight"),
						rs.getString("wUnit"),
						rs.getString("notes")
						);
				
				vitals.add(tmpVitals);
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
			return vitals;
	}

	@Override
	public long insertVitals(Vitals v) throws GatewayException {
		
		//init new id to invalid
		long newId = 0;
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("insert INTO vitals (pid, bps, bpd, bpunit, hfeet, hinches, hcm, hunit, weight, wunit, notes) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, v.getPid());
			st.setFloat(2, v.getBps());
			st.setFloat(3, v.getBpd());
			st.setString(4, String.valueOf(v.getBpUnit()));
			st.setInt(5, v.getHFeet());
			st.setInt(6, v.getHInches());
			st.setInt(7, v.getHCm());
			st.setString(8, String.valueOf(v.getHUnit()));
			st.setFloat(9, v.getWeight());
			st.setString(10, String.valueOf(v.getWUnit()));
			st.setString(11, v.getNotes());
			
			st.executeUpdate();
			
			//get the generated key
			rs = st.getGeneratedKeys();
			
			if(rs != null && rs.next()) {
				
			    newId = rs.getLong(1);
			    System.out.println("Vitals is ID: " + newId + "");
			    
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
	
	public long updateVitals(Vitals v) throws GatewayException{
		
		PreparedStatement st = null;
		//ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE vitals SET"
					+ " bps = ?,"
					+ " bpd = ?,"
					+ " bpunit = ?,"
					+ " hfeet = ?,"
					+ " hinches = ?,"
					+ " hcm = ?,"
					+ " hunit = ?,"
					+ " weight = ?,"
					+ " wunit = ?,"
					+ " notes = ?"
					+ " WHERE id = ? ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setFloat(1, v.getBps());
			st.setFloat(2, v.getBpd());
			st.setString(3, v.getBpUnit());
			st.setInt(4, v.getHFeet());
			st.setInt(5, v.getHInches());
			st.setInt(6, v.getHCm());
			st.setString(7, v.getHUnit());
			st.setFloat(8, v.getWeight());
			st.setString(9, v.getWUnit());
			st.setString(10, v.getNotes());
			st.setLong(11, v.getId());
			st.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("vital being prepared: ");
			System.out.println(v.toString());

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
		
		return v.getId();
	}
	
	/**
	 * Remove an Vitals from the DB
	 * @param a Vitals to update
	 */
	public void removeVitals(Long vid) throws GatewayException{
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM vitals"	+ " WHERE id = ? ", PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, vid);
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
}
