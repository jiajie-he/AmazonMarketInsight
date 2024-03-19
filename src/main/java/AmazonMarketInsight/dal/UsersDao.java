package AmazonMarketInsight.dal;
import AmazonMarketInsight.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class UsersDao extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		super();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public Users create(Users users) throws SQLException {
		// Insert into the superclass table first.
		create(new Persons(users.getUserName()));

		String insertUser = "INSERT INTO Users(UserName,DoB,Subscribed) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, users.getUserName());
			insertStmt.setTimestamp(2, new Timestamp(users.getDoB().getTime()));
			insertStmt.setBoolean(3, users.getSubscribed());
			
			insertStmt.executeUpdate();
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Update the LastName of the Users instance.
	 * This runs a UPDATE statement.
	 */
	public Users updateLastName(Users user, String newLastName) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updateLastName(user, newLastName);
		return user;
	}

	/**
	 * Delete the Users instance.
	 * This runs a DELETE statement.
	 */
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + user.getUserName());
			}

			// Then also delete from the superclass.
			// Notes:
			// 1. Due to the fk constraint (ON DELETE CASCADE), we could simply call
			//    super.delete() without even needing to delete from Administrators first.
			// 2. BlogPosts has a fk constraint on Users with the reference option
			//    ON DELETE SET NULL. If the BlogPosts fk reference option was instead
			//    ON DELETE RESTRICT, then the caller would need to delete the referencing
			//    BlogPosts before this User can be deleted.
			//    Example to delete the referencing BlogPosts:
			//    List<BlogPosts> posts = BlogPostsDao.getBlogPostsForUser(user.getUserName());
			//    for(BlogPosts p : posts) BlogPostsDao.delete(p);
			super.delete(user);

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Users getUserFromUserName(String userName) throws SQLException {
		// To build an User object, we need the Persons record, too.
		String selectUser =
			"SELECT Users.UserName AS UserName, Password, FirstName, LastName, Email, PhoneNumber, DoB, Subscribed " +
			"FROM Users INNER JOIN Persons " +
			"  ON Users.UserName = Persons.UserName " +
			"WHERE Users.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				Date doB = new Date(results.getDate("DoB").getTime());
				Boolean subscribed = results.getBoolean("Subscribed");
				Users user = new Users(resultUserName, password, firstName, lastName, email, phoneNumber, doB, subscribed);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<Users> getUsersFromFirstName(String firstName)
			throws SQLException {
		List<Users> users = new ArrayList<Users>();
		String selectUsers =
			"SELECT Users.UserName AS UserName, Password, FirstName, LastName, Email, PhoneNumber, DoB, Subscribed " +
			"FROM Users INNER JOIN Persons " +
			"  ON Users.UserName = Persons.UserName " +
			"WHERE Persons.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String UserName = results.getString("UserName");
				String password = results.getString("Password");
				String resultfirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				Date doB = new Date(results.getDate("DoB").getTime());
				Boolean subscribed = results.getBoolean("Subscribed");
				Users user = new Users(UserName, password, resultfirstName, lastName, email, phoneNumber, doB, subscribed);
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return users;
	}
}
