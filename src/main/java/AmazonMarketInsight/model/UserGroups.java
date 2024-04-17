package AmazonMarketInsight.model;

import java.sql.Timestamp;
// import java.util.Locale.Category;

public class UserGroups {
    private int groupId;
    private String groupName;
    private String role;
    private java.sql.Timestamp joinDate;
    private String userName;
    private Category category;
    public enum Category {
        OWNER(0), ADMIN(1), MEMBER(2);
    
        private final int id;
    
        Category(int id) {
            this.id = id;
        }
    
        public int getId() {
            return this.id;
        }
    
        public static Category fromId(int id) {
            for (Category cat : values()) {
                if (cat.getId() == id) {
                    return cat;
                }
            }
            throw new IllegalArgumentException("No category with id: " + id);
        }
    }
    // Constructors, getters, setters
   

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
