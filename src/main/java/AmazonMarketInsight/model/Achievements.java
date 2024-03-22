package AmazonMarketInsight.model;

import java.sql.Timestamp;

/**
 * Achievements is a simple, plain old java objects (POJO).
 * 
 * Achievements/AchievementsDao is the superclass for Administrators/AdministratorsDao and
 * BlogUsers/BlogUsersDao. Our implementation of Achievements is a concrete class. This allows 
 * us to create records in the Achievements MySQL table without having the associated records
 * in the Administrators or BlogUsers MySQL tables. Alternatively, Achievements could be an
 * interface or an abstract class, which would force a Achievements record to be created only
 * if an Administrators or BlogUsers record is created, too.
 */
public class Achievements {
    protected int achievementId;
    protected Timestamp created;
    
    public Achievements(int achievementId, Timestamp created) {
        this.achievementId = achievementId;
        this.created = created;
    }
    
    public Achievements(int achievementId) {
        this.achievementId = achievementId;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
