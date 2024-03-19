package AmazonMarketInsight.dal;

import AmazonMarketInsight.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AchievementsEarnedDao {
	protected ConnectionManager connectionManager;

	private static AchievementsEarnedDao instance = null;
	protected AchievementsEarnedDao() {
		connectionManager = new ConnectionManager();
	}
	public static AchievementsEarnedDao getInstance() {
		if(instance == null) {
			instance = new AchievementsEarnedDao();
		}
		return instance;
	}

	public AchievementsEarned create(AchievementsEarned achievementsEarned) throws SQLException {
		String insertAchievementsEarned =
			"INSERT INTO AchievementsEarned(AchievementEarnedId,UserName,AchievementId,DateEarned) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAchievementsEarned,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(2, achievementsEarned.getUser().getUserName());
			insertStmt.setInt(3, achievementsEarned.getAchievement().getAchievementId());
			insertStmt.setTimestamp(4, new Timestamp(achievementsEarned.getDateEarned().getTime()));
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int achievementsEarnedId = -1;
			if(resultKey.next()) {
				achievementsEarnedId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			achievementsEarned.setAchievementsEarnedId(achievementsEarnedId);
			return achievementsEarned;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	/**
	 * Update the content of the AchievementsEarned instance.
	 * This runs a UPDATE statement.
	 */
	public AchievementsEarned updateDateEarned(AchievementsEarned achievementsEarned, Timestamp newDateEarned) throws SQLException {
		String updateAchievementsEarned = "UPDATE AchievementsEarned SET DateEarned=? WHERE AchievementsEarnedId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAchievementsEarned);
			Date newDateEarnedTimestamp = new Timestamp();
			updateStmt.setInt(1, achievementsEarned.getAchievementsEarnedId());
			updateStmt.setString(2, achievementsEarned.getUserName());
			updateStmt.executeUpdate();

			// Update the achievementsEarned param before returning to the caller.
			achievementsEarned.setDateEarned(newDateEarned);
			return achievementsEarned;
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
	 * Delete the AchievementsEarned instance.
	 * This runs a DELETE statement.
	 */
	public AchievementsEarned delete(AchievementsEarned achievementsEarned) throws SQLException {
		String deleteAchievementsEarned = "DELETE FROM AchievementsEarned WHERE AchievementsEarnedId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAchievementsEarned);
			deleteStmt.setInt(1, achievementsEarned.getAchievementsEarnedId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the AchievementsEarned instance.
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
	 * Get the AchievementsEarned record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single AchievementsEarned instance.
	 * Note that we use AchievementsDao and UsersDao to retrieve the referenced
	 * Achievements and Users instances.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the AchievementsEarned, Achievements, Users tables and then build each object.
	 */
	public AchievementsEarned getAchievementsEarnedById(int achievementsEarnedId) throws SQLException {
		String selectAchievementsEarned =
			"SELECT AchievementsEarnedId,UserName,AchievementId,DateEarned " +
			"FROM AchievementsEarned " +
			"WHERE AchievementsEarnedId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAchievementsEarned);
			selectStmt.setInt(1, achievementsEarnedId);
			results = selectStmt.executeQuery();
			AchievementsDao achievementsDao = AchievementsDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			if(results.next()) {
				int resultAchievementsEarnedId = results.getInt("AchievementsEarnedId");
				String userName = results.getString("UserName");
				int achievementId = results.getInt("AchievementId");
				Timestamp dateEarned =  results.getTimestamp("DateEarned");
				Users user = usersDao.getUserFromUserName(userName);
				Achievements achievement = achievementsDao.getAchievementById(achievementId);
				AchievementsEarned achievementsEarned = new AchievementsEarned(resultAchievementsEarnedId,
					user,achievement,dateEarned);
				return achievementsEarned;
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

	/**
	 * Get the all the AchievementsEarned for a user.
	 */
	public List<AchievementsEarned> getAchievementsEarnedForUserName(Users user) throws SQLException {
		List<AchievementsEarned> achievementsEarnedList = new ArrayList<AchievementsEarned>();
		String selectAchievementsEarned =
			"SELECT AchievementsEarnedId,UserName,AchievementId,DateEarned " +
			"FROM AchievementsEarned " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAchievementsEarned);
			selectStmt.setString(1, user.getUserName());
			results = selectStmt.executeQuery();
			AchievementsDao achievementsDao = AchievementsDao.getInstance();
			while(results.next()) {
				int achievementsEarnedId = results.getInt("AchievementsEarnedId");
				int achievementId = results.getInt("AchievementId");
				Timestamp dateEarned =  results.getTimestamp("DateEarned");
				Achievements achievement = achievementsDao.getAchievementById(achievementId);
				AchievementsEarned achievementsEarned = new AchievementsEarned(achievementsEarnedId,
					user,achievement, dateEarned);
				achievementsEarnedList.add(achievementsEarned);
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
		return achievementsEarnedList;
	}
	
	/**
	 * Get the all the AchievementsEarned for a post.
	 */
	public List<AchievementsEarned> getAchievementsEarnedForAchievement(Achievements achievement) throws SQLException {
		List<AchievementsEarned> achievementsEarnedList = new ArrayList<AchievementsEarned>();
		String selectAchievements =
			"SELECT AchievementsEarnedId,UserName,AchievementId,DateEarned " +
			"FROM AchievementsEarned " +
			"WHERE AchievementId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAchievements);
			selectStmt.setInt(1, achievement.getAchievementId());
			results = selectStmt.executeQuery();
			UsersDao UsersDao = UsersDao.getInstance();
			while(results.next()) {
				int achievementsEarnedId = results.getInt("AchievementsEarnedId");
				int userName = results.getInt("UserName");
				Timestamp dateEarned =  results.getTimestamp("DateEarned");

				Users user = usersDao.getUserFromUserName(userName);
				AchievementsEarned achievementsEarned = new AchievementsEarned(achievementsEarnedId, user, achievement, dateEarned);
				achievementsEarnedList.add(achievementsEarned);
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
		return achievementsEarnedList;
	}
}
