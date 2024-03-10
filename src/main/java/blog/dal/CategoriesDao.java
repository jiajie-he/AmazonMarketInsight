package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDao {
	protected ConnectionManager connectionManager;

	private static CategoriesDao instance = null;

	protected CategoriesDao() {
		connectionManager = new ConnectionManager();
	}

	public static CategoriesDao getInstance() {
		if (instance == null) {
			instance = new CategoriesDao();
		}
		return instance;
	}

	public Categories create(Categories category) throws SQLException {
		String insertCategory = "INSERT INTO Categories(CategoryId, Name) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCategory);
			insertStmt.setInt(1, category.getCategoryId());
			insertStmt.setString(2, category.getName());

			insertStmt.executeUpdate();
			return category;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Categories update(Categories category) throws SQLException {
		String updateCategory = "UPDATE Categories SET Name=? WHERE categoryId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCategory);
			updateStmt.setString(1, category.getName());
			updateStmt.setInt(2, category.getCategoryId());

			updateStmt.executeUpdate();
			return category;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public boolean delete(String categoryId) throws SQLException {
		String deleteCategory = "DELETE FROM Categories WHERE categoryId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCategory);
			deleteStmt.setString(1, categoryId);

			int affectedRows = deleteStmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Categories getCategoryById(int categoryId) throws SQLException {
		String selectCategory = "SELECT CategoryId,Name FROM Categories WHERE categoryId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet resultSet = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCategory);
			selectStmt.setInt(1, categoryId);
			resultSet = selectStmt.executeQuery();
			if (resultSet.next()) {
				return parseCategory(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return null;
	}

	public List<Categories> getAllCategories() throws SQLException {
		List<Categories> categoryList = new ArrayList<>();
		String selectAllCategories = "SELECT * FROM Categories;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet resultSet = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAllCategories);
			resultSet = selectStmt.executeQuery();
			while (resultSet.next()) {
				Categories category = parseCategory(resultSet);
				categoryList.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return categoryList;
	}

	private Categories parseCategory(ResultSet resultSet) throws SQLException {
		int categoryId = resultSet.getInt("CategoryId");
		String name = resultSet.getString("Name");

		return new Categories(categoryId, name);
	}
}
