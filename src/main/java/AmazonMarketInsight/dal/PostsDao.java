package AmazonMarketInsight.dal;

import AmazonMarketInsight.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Posts dao.
 */
public class PostsDao {
  /**
   * The Connection manager.
   */
  protected ConnectionManager connectionManager;

  private static PostsDao instance = null;

  /**
   * Instantiates a new Posts dao.
   */
  protected PostsDao() {
    connectionManager = new ConnectionManager();
  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static PostsDao getInstance() {
    if(instance == null) {
      instance = new PostsDao();
    }
    return instance;
  }

  /**
   * Create post.
   *
   * @param post the post
   * @return the post
   * @throws SQLException the sql exception
   */
  public Post create(Post post) throws SQLException {
    String insertPost = "INSERT INTO Posts(Created, Review, Rating, NumInteractions, Active, UpVotes, DownVotes, Shares, UserName, ProductId) " +
        "VALUES(?,?,?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertPost, PreparedStatement.RETURN_GENERATED_KEYS);

      insertStmt.setTimestamp(1, post.getCreated());
      insertStmt.setString(2, post.getReview());
      insertStmt.setDouble(3, post.getRating());
      insertStmt.setInt(4, post.getNumInteractions());
      insertStmt.setBoolean(5, post.isActive());
      insertStmt.setInt(6, post.getUpVotes());
      insertStmt.setInt(7, post.getDownVotes());
      insertStmt.setInt(8, post.getShares());
      insertStmt.setString(9, post.getUserName());
      insertStmt.setString(10, post.getProductId());

      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      resultKey = insertStmt.getGeneratedKeys();
      int postId = -1;
      if(resultKey.next()) {
        postId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      post.setPostId(postId);
      return post;
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
   * Gets post by id.
   *
   * @param postId the post id
   * @return the post by id
   * @throws SQLException the sql exception
   */
  public Post getPostById(int postId) throws SQLException {
    String selectPost = "SELECT * FROM Posts WHERE PostId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectPost);
      selectStmt.setInt(1, postId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        return extractPostFromResultSet(results);
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
   * Gets posts by username.
   *
   * @param username the username
   * @return the posts by username
   */
  public List<Post> getPostsByUsername(String username) {
    List<Post> posts = new ArrayList<>();
    String selectPosts = "SELECT * FROM Posts WHERE UserName=?;";
    try (Connection connection = connectionManager.getConnection();
         PreparedStatement selectStmt = connection.prepareStatement(selectPosts)) {

      selectStmt.setString(1, username);
      try (ResultSet results = selectStmt.executeQuery()) {
        while (results.next()) {
          posts.add(extractPostFromResultSet(results));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return posts;
  }

  private Post extractPostFromResultSet(ResultSet results) throws SQLException {
    return new Post(
        results.getInt("PostId"),
        results.getTimestamp("Created"),
        results.getString("Review"),
        results.getDouble("Rating"),
        results.getInt("NumInteractions"),
        results.getBoolean("Active"),
        results.getInt("UpVotes"),
        results.getInt("DownVotes"),
        results.getInt("Shares"),
        results.getString("UserName"),
        results.getString("ProductId")
    );
  }

  /**
   * Update rating post.
   *
   * @param post      the post
   * @param newRating the new rating
   * @return the post
   * @throws SQLException the sql exception
   */
  public Post updateRating(Post post, double newRating) throws SQLException {
    String updatePost = "UPDATE Posts SET Rating=? WHERE PostId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updatePost);
      updateStmt.setDouble(1, newRating);
      updateStmt.setInt(2, post.getPostId());
      updateStmt.executeUpdate();

      post.setRating(newRating);
      return post;
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
   * Delete post.
   *
   * @param post the post
   * @return the post
   * @throws SQLException the sql exception
   */
  public Post delete(Post post) throws SQLException {
    String deletePost = "DELETE FROM Posts WHERE PostId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deletePost);
      deleteStmt.setInt(1, post.getPostId());
      deleteStmt.executeUpdate();

      // Return null to indicate that the post has been deleted
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

}
