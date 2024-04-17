package AmazonMarketInsight.dal;

import AmazonMarketInsight.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class WishListDao {
	protected ConnectionManager connectionManager;

	private static WishListDao instance = null;
	protected WishListDao() {
		connectionManager = new ConnectionManager();
	}
	public static WishListDao getInstance() {
		if(instance == null) {
			instance = new WishListDao();
		}
		return instance;
	}

	public WishList create(WishList wishList) throws SQLException {
		String insertWishList =
			"INSERT INTO WishList(WishListId,UserName,ProductId) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertWishList,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(2, wishList.getUserName().getUserName());
			insertStmt.setString(3, wishList.getProductId().getProductId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int wishListId = -1;
			if(resultKey.next()) {
				wishListId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			wishList.setWishListId(wishListId);
			return wishList;
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
	 * Delete the WishList instance.
	 * This runs a DELETE statement.
	 */
	public WishList delete(WishList wishList) throws SQLException {
		String deleteWishList = "DELETE FROM WishList WHERE WishListId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWishList);
			deleteStmt.setInt(1, wishList.getWishListId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the WishList instance.
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
	 * Get the WishList record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single WishList instance.
	 * Note that we use ProductsDao and UsersDao to retrieve the referenced
	 * Products and Users instances.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the WishList, Products, Users tables and then build each object.
	 */
	public WishList getWishListById(int wishListId) throws SQLException {
		String selectWishList =
			"SELECT WishListId,UserName,ProductId " +
			"FROM WishList " +
			"WHERE WishListId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWishList);
			selectStmt.setInt(1, wishListId);
			results = selectStmt.executeQuery();
			ProductsDao productsDao = ProductsDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			if(results.next()) {
				int resultWishListId = results.getInt("WishListId");
				String userName = results.getString("UserName");
				String productId = results.getString("ProductId");
				Users user = usersDao.getUserFromUserName(userName);
				Products product = productsDao.getProductById(productId);
				WishList wishList = new WishList(resultWishListId,
					user,product);
				return wishList;
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
	 * Get the all the WishList for a user.
	 */
	public List<WishList> getWishListFromUser(Users user) throws SQLException {
		List<WishList> wishListList = new ArrayList<WishList>();
		String selectWishList =
			"SELECT WishListId,UserName,ProductId " +
			"FROM WishList " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWishList);
			selectStmt.setString(1, user.getUserName());
			results = selectStmt.executeQuery();
			ProductsDao productsDao = ProductsDao.getInstance();
			while(results.next()) {
				int wishListId = results.getInt("WishListId");
				String productId = results.getString("ProductId");
				Products product = productsDao.getProductById(productId);
				WishList wishList = new WishList(wishListId,
					user,product);
				wishListList.add(wishList);
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
		return wishListList;
	}
	
	/**
	 * Get the all the WishList for a post.
	 */
	public List<WishList> getWishListFromProduct(Products product) throws SQLException {
		List<WishList> wishListList = new ArrayList<WishList>();
		String selectProducts =
			"SELECT WishListId,UserName,ProductId " +
			"FROM WishList " +
			"WHERE ProductId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProducts);
			selectStmt.setString(1, product.getProductId());
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			while(results.next()) {
				int wishListId = results.getInt("WishListId");
				String userName = results.getString("UserName");

				Users user = usersDao.getUserFromUserName(userName);
				WishList wishList = new WishList(wishListId, user, product);
				wishListList.add(wishList);
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
		return wishListList;
	}
}
