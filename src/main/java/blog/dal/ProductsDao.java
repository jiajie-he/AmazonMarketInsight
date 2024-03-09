package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao {
    protected ConnectionManager connectionManager;

    private static ProductsDao instance = null;

    protected ProductsDao() {
        connectionManager = new ConnectionManager();
    }

    public static ProductsDao getInstance() {
        if (instance == null) {
            instance = new ProductsDao();
        }
        return instance;
    }

    public Products create(Products product) throws SQLException {
        String insertProduct = "INSERT INTO Products(ProductId, Title, ImageUrl, ProductUrl, Stars, Reviews, " +
                "Price, ListedPrice, CategoryId, BestSeller, BoughtInLastMonth, TimesPosted, NumViews, " +
                "Likes, Dislikes) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertProduct);
            insertStmt.setString(1, product.getProductId());
            insertStmt.setString(2, product.getTitle());
            insertStmt.setString(3, product.getImageUrl());
            insertStmt.setString(4, product.getProductUrl());
            insertStmt.setDouble(5, product.getStars());
            insertStmt.setInt(6, product.getReviews());
            insertStmt.setDouble(7, product.getPrice());
            insertStmt.setDouble(8, product.getListedPrice());
            if (product.getCategoryId() != null) {
                insertStmt.setInt(9, product.getCategoryId());
            } else {
                insertStmt.setNull(9, java.sql.Types.INTEGER);
            }
            insertStmt.setBoolean(10, product.isBestSeller());
            insertStmt.setInt(11, product.getBoughtInLastMonth());
            insertStmt.setInt(12, product.getTimesPosted());
            insertStmt.setInt(13, product.getNumViews());
            insertStmt.setInt(14, product.getLikes());
            insertStmt.setInt(15, product.getDislikes());
            insertStmt.executeUpdate();
            return product;
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

    public Products update(Products product) throws SQLException {
        String updateProduct = "UPDATE Products SET Title=?, ImageUrl=?, ProductUrl=?, Stars=?, Reviews=?, " +
                "Price=?, ListedPrice=?, CategoryId=?, BestSeller=?, BoughtInLastMonth=?, TimesPosted=?, NumViews=?, " +
                "Likes=?, Dislikes=? WHERE ProductId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateProduct);
            updateStmt.setString(1, product.getTitle());
            updateStmt.setString(2, product.getImageUrl());
            updateStmt.setString(3, product.getProductUrl());
            updateStmt.setDouble(4, product.getStars());
            updateStmt.setInt(5, product.getReviews());
            updateStmt.setDouble(6, product.getPrice());
            updateStmt.setDouble(7, product.getListedPrice());
            if (product.getCategoryId() != null) {
                updateStmt.setInt(8, product.getCategoryId());
            } else {
                updateStmt.setNull(8, java.sql.Types.INTEGER);
            }
            updateStmt.setBoolean(9, product.isBestSeller());
            updateStmt.setInt(10, product.getBoughtInLastMonth());
            updateStmt.setInt(11, product.getTimesPosted());
            updateStmt.setInt(12, product.getNumViews());
            updateStmt.setInt(13, product.getLikes());
            updateStmt.setInt(14, product.getDislikes());
            updateStmt.setString(15, product.getProductId());
            updateStmt.executeUpdate();
            return product;
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

    public boolean delete(String productId) throws SQLException {
        String deleteProduct = "DELETE FROM Products WHERE ProductId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteProduct);
            deleteStmt.setString(1, productId);
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

    public Products getProductById(String productId) throws SQLException {
        String selectProduct = "SELECT * FROM Products WHERE ProductId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet resultSet = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectProduct);
            selectStmt.setString(1, productId);
            resultSet = selectStmt.executeQuery();
            if (resultSet.next()) {
                return parseProduct(resultSet);
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

    public List<Products> getAllProducts() throws SQLException {
        List<Products> productList = new ArrayList<>();
        String selectAllProducts = "SELECT * FROM Products;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet resultSet = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAllProducts);
            resultSet = selectStmt.executeQuery();
            while (resultSet.next()) {
                Products product = parseProduct(resultSet);
                productList.add(product);
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
        return productList;
    }

    private Products parseProduct(ResultSet resultSet) throws SQLException {
        String productId = resultSet.getString("ProductId");
        String title = resultSet.getString("Title");
        String imageUrl = resultSet.getString("ImageUrl");
        String productUrl = resultSet.getString("ProductUrl");
        double stars = resultSet.getDouble("Stars");
        int reviews = resultSet.getInt("Reviews");
        double price = resultSet.getDouble("Price");
        double listedPrice = resultSet.getDouble("ListedPrice");
        Integer categoryId = resultSet.getInt("CategoryId");
        boolean bestSeller = resultSet.getBoolean("BestSeller");
        int boughtInLastMonth = resultSet.getInt("BoughtInLastMonth");
        int timesPosted = resultSet.getInt("TimesPosted");
        int numViews = resultSet.getInt("NumViews");
        int likes = resultSet.getInt("Likes");
        int dislikes = resultSet.getInt("Dislikes");
        return new Products(productId, title, imageUrl, productUrl, stars, reviews, price, listedPrice,
                categoryId, bestSeller, boughtInLastMonth, timesPosted, numViews, likes, dislikes);
    }
}
