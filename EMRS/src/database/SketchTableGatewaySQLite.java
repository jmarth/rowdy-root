package database;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SketchTableGatewaySQLite implements SketchTableGateway {
	
	/**
	 * external DB connection
	 */
	private Connection conn = null;
	
	/**
	 * Constructor: creates database connection
	 * @throws GatewayException
	 * @throws IOException 
	 */
	public SketchTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch visits from db for patient
	 * @return list of visits for patient
	 * @throws GatewayException
	 */
	
	public List<Image> fetchSketchesForPatinet(long vid) throws GatewayException {
		
		ArrayList<Image> sketches = new ArrayList<Image>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select image from sketches where vid=?");
			st.setLong(1, vid);
			
			rs = st.executeQuery();
			
			//add each to list of parts to return
			while(rs.next()) {
				
				//sketches.add(rs.getbin)
				InputStream stream = rs.getBinaryStream("image");
			    ByteArrayOutputStream output = new ByteArrayOutputStream();
			    
			    int a1 = stream.read();
			    
			    while (a1 >= 0) {
			      output.write((char) a1);
			      a1 = stream.read();
			    }
			    
			    Image sketch = Toolkit.getDefaultToolkit().createImage(output.toByteArray());
				sketches.add(sketch);
				
				output.close();
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
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
		
		return sketches;
	}
	
	/**
	 * Inserts sketch into sketchs table
	 */
	public long insertSketch(File f, long vid) throws GatewayException {
		
		long newId = 0;
		
		PreparedStatement st = null;
		FileInputStream fis = null;
		ResultSet rs = null;
		
		try {
			
			try {
				
				fis = new FileInputStream(f);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			st = conn.prepareStatement("insert INTO sketches (vid,"
					+ " image) "
					+ " values ( ?, ?) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, vid);
			st.setBinaryStream(2, fis, (int) f.length());
	
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

	@Override
	public long insertSketchToTable(File f, long vid, String table) throws GatewayException {

		long newId = 0;
		
		PreparedStatement st = null;
		FileInputStream fis = null;
		ResultSet rs = null;
		
		try {
			
			try {
				
				fis = new FileInputStream(f);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			st = conn.prepareStatement("insert INTO "+table+" (vid,"
					+ " image) "
					+ " values ( ?, ?) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			st.setLong(1, vid);
			st.setBinaryStream(2, fis, (int) f.length());
	
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

	@Override
	public List<Image> fetchSketchesForVisitByTable(long vid, String table) throws GatewayException {

		//TODO No list, just a single class, Sketch
		ArrayList<Image> sketches = new ArrayList<Image>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//fetch parts
			st = conn.prepareStatement("select image from "+table+" where vid=?");
			st.setLong(1, vid);
			
			rs = st.executeQuery();
			
			//add each to list of parts to return
			while(rs.next()) {
				
				//sketches.add(rs.getbin)
				InputStream stream = rs.getBinaryStream("image");
			    ByteArrayOutputStream output = new ByteArrayOutputStream();
			    
			    int a1 = stream.read();
			    
			    while (a1 >= 0) {
			      output.write((char) a1);
			      a1 = stream.read();
			    }
			    
			    Image sketch = Toolkit.getDefaultToolkit().createImage(output.toByteArray());
				sketches.add(sketch);
				
				output.close();
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
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
		
		return sketches;
	}

}
