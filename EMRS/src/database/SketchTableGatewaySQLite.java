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

public class SketchTableGatewaySQLite implements SketchTableGateway {

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
	public SketchTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");

		} catch (SQLException e) {
			e.printStackTrace();
		}
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

			st = conn.prepareStatement("insert INTO sketches (vid," + " image) " + " values ( ?, ?) ",
					PreparedStatement.RETURN_GENERATED_KEYS);

			st.setLong(1, vid);
			st.setBinaryStream(2, fis, (int) f.length());

			st.executeUpdate();

			// get the generated key
			rs = st.getGeneratedKeys();

			if (rs != null && rs.next()) {

				newId = rs.getLong(1);

			} else {
				throw new GatewayException("Could not insert new record.");
			}

		} catch (SQLException e) {
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

	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public long insertSketchToTable(File f, long vid, String table) throws GatewayException {

		long newId = -1;

		PreparedStatement st = null;
		FileInputStream fis = null;
		ResultSet rs = null;

		try {

			try {

				fis = new FileInputStream(f);

			} catch (FileNotFoundException e) {
				System.err.println("From SketchTG, file not found");
//				e.printStackTrace();
			}

			st = conn.prepareStatement("insert INTO " + table + " (vid," + " image) " + " values ( ?, ?) ",
					PreparedStatement.RETURN_GENERATED_KEYS);

			st.setLong(1, vid);
			st.setBinaryStream(2, fis, (int) f.length());

			st.executeUpdate();

			// get the generated key
			rs = st.getGeneratedKeys();

			if (rs != null && rs.next()) {

				newId = rs.getLong(1);

			} else {
				throw new GatewayException("Could not insert new record.");
			}

		} catch (SQLException e) {
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
	public Image fetchSketchForVisitByTable(long vid, String table) throws GatewayException {

		Image image = null;

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			// fetch parts
			st = conn.prepareStatement("SELECT image FROM " + table + " WHERE vid=?");
			st.setLong(1, vid);

			rs = st.executeQuery();

			// add each to list of parts to return
			if (rs.next()) {

				InputStream stream = rs.getBinaryStream("image");
				ByteArrayOutputStream output = new ByteArrayOutputStream();

				int i = stream.read();

				while (i >= 0) {
					output.write((char) i);
					i = stream.read();
				}

				image = Toolkit.getDefaultToolkit().createImage(output.toByteArray());
				output.close();

				return image;
			}

		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
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

		return null;
	}

}
