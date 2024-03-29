package AmazonMarketInsight.dal;

import AmazonMarketInsight.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Administrators table in your
 * MySQL instance. This is used to store {@link Administrators} into your MySQL instance and 
 * retrieve {@link Administrators} from MySQL instance.
 */
public class AdministratorsDao extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	private static AdministratorsDao instance = null;
	protected AdministratorsDao() {
		super();
	}
	public static AdministratorsDao getInstance() {
		if(instance == null) {
			instance = new AdministratorsDao();
		}
		return instance;
	}
	
	public Administrators create(Administrators administrator) throws SQLException {
		// Insert into the superclass table first.
		create(new Persons(administrator.getUserName()));

		String insertAdministrator = "INSERT INTO Administrators(UserName,CanEditPosts,CanDeletePosts) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAdministrator);
			insertStmt.setString(1, administrator.getUserName());
			insertStmt.setBoolean(2, administrator.getCanEditPosts());
			insertStmt.setBoolean(3, administrator.getCanDeletePosts());
			insertStmt.executeUpdate();
			return administrator;
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
	 * Update the LastName of the Administrators instance.
	 * This runs a UPDATE statement.
	 */
	public Administrators updateLastName(Administrators administrator, String newLastName) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		super.updateLastName(administrator, newLastName);
		return administrator;
	}

	/**
	 * Delete the Administrators instance.
	 * This runs a DELETE statement.
	 */
	public Administrators delete(Administrators administrator) throws SQLException {
		String deleteAdministrator = "DELETE FROM Administrators WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAdministrator);
			deleteStmt.setString(1, administrator.getUserName());
			deleteStmt.executeUpdate();

			// Then also delete from the superclass.
			// Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
			// super.delete() without even needing to delete from Administrators first.
			super.delete(administrator);

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
	
	public Administrators getAdministratorFromUserName(String userName) throws SQLException {
		// To build an Administrator object, we need the Persons record, too.
		String selectAdministrator =
			"SELECT Administrators.UserName AS UserName, Password, FirstName, LastName, Email, PhoneNumber,CanEditPosts,CanDeletePosts   " +
			"FROM Administrators INNER JOIN Persons " +
			"  ON Administrators.UserName = Persons.UserName " +
			"WHERE Administrators.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdministrator);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				Boolean canEditPosts = results.getBoolean("CanEditPosts");
				Boolean canDeletePosts = results.getBoolean("CanDeletePosts");
				Administrators administrator = new Administrators(resultUserName, password, firstName, lastName, email, phoneNumber,canEditPosts,canDeletePosts);
				return administrator;
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

	public List<Administrators> getAdministratorsFromFirstName(String firstName)
			throws SQLException {
		List<Administrators> administrators = new ArrayList<Administrators>();
		String selectAdministrators =
			"SELECT Administrators.UserName AS UserName, Password, FirstName, LastName, Email, PhoneNumber,CanEditPosts,CanDeletePosts   " +
			"FROM Administrators INNER JOIN Persons " +
			"  ON Administrators.UserName = Persons.UserName " +
			"WHERE Persons.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAdministrators);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String resultfirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				String phoneNumber = results.getString("PhoneNumber");
				Boolean canEditPosts = results.getBoolean("CanEditPosts");
				Boolean canDeletePosts = results.getBoolean("CanDeletePosts");
				Administrators administrator = new Administrators(userName, password, resultfirstName, lastName, email, phoneNumber,canEditPosts,canDeletePosts);
				administrators.add(administrator);
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
		return administrators;
	}
}
