package AmazonMarketInsight.dal;

import AmazonMarketInsight.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Achievements table in your MySQL
 * instance. This is used to store {@link Achievements} into your MySQL instance and retrieve 
 * {@link Achievements} from MySQL instance.
 */
public class AchievementsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AchievementsDao instance = null;
	protected AchievementsDao() {
		connectionManager = new ConnectionManager();
	}
	public static AchievementsDao getInstance() {
		if(instance == null) {
			instance = new AchievementsDao();
		}
		return instance;
	}

	/**
	 * Save the Achievements instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Achievements create(Achievements achievement) throws SQLException {
		String insertAchievement = "INSERT INTO Achievements(AchievementId, Created) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAchievement);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setInt(1, achievement.getAchievementId());
			insertStmt.setTimestamp(2, achievement.getCreated());
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Note 1: if this was an UPDATE statement, then the achievement fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param achievement.
			return achievement;
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
	 * Update the LastName of the Achievements instance.
	 * This runs a UPDATE statement.
	 */
	public Achievements updateCreated(Achievements achievement, Timestamp newCreated) throws SQLException {
		TimeStamp updateAchievement = "UPDATE Achievements SET Created=? WHERE AchievementId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAchievement);
			updateStmt.setTimeStamp(1, newCreated);
			updateStmt.setInt(2, achievement.getAchievementId());
			updateStmt.executeUpdate();
			
			// Update the achievement param before returning to the caller.
			achievement.setAchievementId(newCreated);
			return achievement;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the Achievements instance.
	 * This runs a DELETE statement.
	 */
	public Achievements delete(Achievements achievement) throws SQLException {
		String deleteAchievement = "DELETE FROM Achievements WHERE AchievementId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAchievement);
			deleteStmt.setInt(1, achievement.getAchievementId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Achievements instance.
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

	/**
	 * Get the Achievements record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Achievements instance.
	 */
	public Achievements getAchievementFromAchievementId(Int achievementId) throws SQLException {
		String selectAchievement = "SELECT AchievementId, Created FROM Achievements WHERE AchievementId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAchievement);
			selectStmt.setInt(1, achievementId);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				Int resultAchievementId = results.getInt("AchievementId");
				Timestamp Created = results.getTimestamp("Created");
				Achievements achievement = new Achievements(resultAchievementId, Created);
				return achievement;
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
}
