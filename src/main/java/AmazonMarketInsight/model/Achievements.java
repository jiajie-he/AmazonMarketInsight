package AmazonMarketInsight.model;

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
	protected Int AchievementId;
	protected Timestamp created;
	
	public Achievements(Int achievementId, Timestamp created) {
		this.achievementId = achievementId;
		this.created = created;
	}
	
	public Achievements(Int achievementId) {
		this.achievementId = achievementId;
	}

	public Int getAchievementId() {
		return achievementId;
	}

	public void setAchievementId(Int achievementId) {
		this.achievementId = achievementId;
	}

	public TimeStamp getCreated() {
		return created;
	}

	public void setCreated(TimeStamp created) {
		this.created = created;
	}
}
