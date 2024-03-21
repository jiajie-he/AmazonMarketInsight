package AmazonMarketInsight.dal;

import AmazonMarketInsight.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserGroupsDAO {
    private Connection connection;

    public UserGroupsDAO(Connection connection) {
        this.connection = connection;
    }
    
    public UserGroups addUserGroup(UserGroups userGroups) throws SQLException {
        String insertQuery = "INSERT INTO UserGroups (GroupName, Role, JoinDate, UserName, CategoryId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, userGroups.getGroupName());
            statement.setString(2, userGroups.getRole());
            statement.setTimestamp(3, userGroups.getJoinDate());
            statement.setString(4, userGroups.getUserName());
            statement.setInt(5, userGroups.getCategory().getCategoryId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userGroups.setGroupId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            // Handle the exception appropriately, such as logging or rethrowing
            e.printStackTrace();
            throw e;
        }
        return userGroups;
    }
    
    
    public List<UserGroups> getUserGroupsByUserName(String userName) throws SQLException {
        List<UserGroups> userGroupsList = new ArrayList<>();
        String selectQuery = "SELECT ug.*, c.* FROM UserGroups ug INNER JOIN Categories c ON ug.CategoryId = c.CategoryId WHERE UserName = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, userName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UserGroups userGroups = new UserGroups();
                    userGroups.setGroupId(resultSet.getInt("GroupId"));
                    userGroups.setGroupName(resultSet.getString("GroupName"));
                    userGroups.setRole(resultSet.getString("Role"));
                    userGroups.setJoinDate(resultSet.getTimestamp("JoinDate"));
                    userGroups.setUserName(resultSet.getString("UserName"));

                    Category category = new Category();
                    category.setCategoryId(resultSet.getInt("CategoryId"));
                    category.setName(resultSet.getString("Name"));

                    userGroups.setCategory(category);
                    userGroupsList.add(userGroups);
                }
            }
        } catch (SQLException e) {
            // Handle the exception appropriately, such as logging or rethrowing
            e.printStackTrace();
            throw e;
        }
        return userGroupsList;
    }
}