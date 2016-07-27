package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

import models.Comment;
import models.Patient;

public class CommentTableGatewaySQLite implements CommentTableGateway {

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
	public CommentTableGatewaySQLite() throws GatewayException, IOException {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:emrs.db");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fetch all Comments in the DB
	 * 
	 * @throws GatewayException
	 */
	public List<Comment> fetchComments() throws GatewayException {

		ArrayList<Comment> comments = new ArrayList<Comment>();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			// fetch Comments
			st = conn.prepareStatement("select * from comments");

			rs = st.executeQuery();

			// add each to list of comments to return

			while (rs.next()) {
				Comment tmpComment = new Comment(rs.getLong("id"), rs.getLong("pid"), rs.getInt("type"), rs.getLong("typeid"), rs.getString("comment"));
				comments.add(tmpComment);
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

		return comments;
	}

	/**
	 * Fetch all Comments for a Patient in the DB
	 * 
	 * @throws GatewayException
	 */
	public List<Comment> fetchCommentsForPatient(Patient p) throws GatewayException {

		ArrayList<Comment> comments = new ArrayList<Comment>();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			// fetch Comments
			st = conn.prepareStatement("select * from comments WHERE pid=?");
			st.setLong(1, p.getId());

			rs = st.executeQuery();

			// add each to list of comments to return
			while (rs.next()) {

				Comment tmpComment = new Comment(rs.getLong("id"), rs.getLong("pid"), rs.getInt("type"), rs.getLong("typeid"), rs.getString("comment"));
				comments.add(tmpComment);
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (st != null)
					st.close();

			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}

		return comments;
	}

	@Override
	public List<Comment> fetchCommentsForPatientByType(Patient p, int t, long tid) throws GatewayException {
		// types = 1 = allergy, 2 = Visit, 3 = vitals, ...
		// need some static finals in a constants class to be more legible

		ArrayList<Comment> comments = new ArrayList<Comment>();

		PreparedStatement st = null;
		ResultSet rs = null;


		
		try {
			st = conn.prepareStatement("select * from comments WHERE pid=? AND type=? AND typeid = ?");

			// fetch Comments
			/*
			switch (t) {
			
			case Comment.allergies:
				st = conn.prepareStatement("select * from comments WHERE pid=? AND type=\'"+ Comment.allergies + "\' AND typeid = ?");

				break;
				
			case Comment.visits:
				st = conn.prepareStatement("select * from comments WHERE pid=? AND type=\'"+ Comment.visits + "\' AND typeid = ?");
				break;
				
			case Comment.vitals:
				st = conn.prepareStatement("select * from comments WHERE pid=? AND type=\'"+ Comment.vitals + "\' AND typeid = ?");
				break;
				
			case 4:
				st = conn.prepareStatement("select * from comments WHERE pid=? AND type='4' AND typeid = ?");
				break;
				
			default:
				throw new GatewayException("Invalid Type!!!");
			}
			*/
			//st = conn.prepareStatement("select * from comments WHERE pid=?");
			st.setLong(1, p.getId());
			st.setInt(2, t);
			st.setLong(3, tid);

			rs = st.executeQuery();

			// add each to list of comments to return
			while (rs.next()) {

				Comment tmpComment = new Comment(rs.getLong("id"), rs.getLong("pid"), rs.getInt("type"), rs.getLong("typeid"), rs.getString("comment"));
				comments.add(tmpComment);
			}
		} catch (SQLException e) {
			throw new GatewayException(e.getMessage());
		} finally {

			try {
				if (rs != null)
					rs.close();

				if (st != null)
					st.close();

			} catch (SQLException e) {
				throw new GatewayException("SQL Error: " + e.getMessage());
			}
		}

		return comments;
	}

	/**
	 * Insert Comment into Database
	 */
	public long insertComment(Comment c) throws GatewayException {

		// init new id to invalid
		long newId = 0;

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("insert INTO comments (pid, type, typeid, comment) " + " values ( ?, ?, ?, ? ) ",
					PreparedStatement.RETURN_GENERATED_KEYS);

			st.setLong(1, c.getPid());
			st.setInt(2, c.getType());
			st.setLong(3, c.getTypeid());
			st.setString(4, c.getCommentString());

			st.executeUpdate();

			// get the generated key
			rs = st.getGeneratedKeys();

			if (rs != null && rs.next()) {
				newId = rs.getLong(1);
				System.out.println("Comment is ID: " + newId + "");

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

	/**
	 * Update an Comment in the DB
	 * 
	 * @param a
	 *            Comment to update
	 */
	public void updateComment(Comment a) throws GatewayException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("UPDATE comments SET" + " comment = ?" + " WHERE id = ? ",
					PreparedStatement.RETURN_GENERATED_KEYS);

			st.setString(1, a.getCommentString());
			st.setLong(2, a.getId());

			st.executeUpdate();

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

	}

	/**
	 * Remove an Comment from the DB
	 * 
	 * @param a
	 *            Comment to update
	 */
	public void removeComment(Long aid) throws GatewayException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("DELETE FROM comments" + " WHERE id = ? ",
					PreparedStatement.RETURN_GENERATED_KEYS);

			st.setLong(1, aid);

			st.executeUpdate();

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
	}

}
