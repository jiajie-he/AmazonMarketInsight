package amazon.dal;

import amazon.model.PostComments;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostCommentsDao {
    protected ConnectionManager connectionManager;
    private static PostCommentsDao instance = null;

    protected PostCommentsDao() {
        connectionManager = new ConnectionManager();
    }

    public static PostCommentsDao getInstance() {
        if (instance == null) {
            instance = new PostCommentsDao();
        }
        return instance;
    }

    public PostComments create(PostComments postComment) throws SQLException {
        String insertComment = "INSERT INTO PostComments(Created, Comment, UpVotes, DownVotes, PostId, UserName) VALUES(?,?,?,?,?,?);";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertComment, PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStmt.setDate(1, postComment.getCreated());
            insertStmt.setString(2, postComment.getComment());
            insertStmt.setInt(3, postComment.getUpVotes());
            insertStmt.setInt(4, postComment.getDownVotes());
            insertStmt.setInt(5, postComment.getPostId());
            insertStmt.setString(6, postComment.getUserName());
            insertStmt.executeUpdate();

            try (ResultSet resultKey = insertStmt.getGeneratedKeys()) {
                if (resultKey.next()) {
                    postComment.setPostCommentId(resultKey.getInt(1));
                } else {
                    throw new SQLException("Unable to retrieve auto-generated key.");
                }
            }
        }
        return postComment;
    }

    public PostComments updateUpVotes(int postCommentId, int newUpVotes) throws SQLException {
        String updateComment = "UPDATE PostComments SET UpVotes=? WHERE PostCommentId=?;";
        PostComments postComment = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement updateStmt = connection.prepareStatement(updateComment)) {
            updateStmt.setInt(1, newUpVotes);
            updateStmt.setInt(2, postCommentId);
            updateStmt.executeUpdate();
            
            postComment = getPostCommentById(postCommentId);
        }
        return postComment;
    }

    public PostComments updateDownVotes(int postCommentId, int newDownVotes) throws SQLException {
        String updateComment = "UPDATE PostComments SET DownVotes=? WHERE PostCommentId=?;";
        PostComments postComment = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement updateStmt = connection.prepareStatement(updateComment)) {
            updateStmt.setInt(1, newDownVotes);
            updateStmt.setInt(2, postCommentId);
            updateStmt.executeUpdate();
            
            postComment = getPostCommentById(postCommentId);
        }
        return postComment;
    }

}
