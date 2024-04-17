package AmazonMarketInsight.model;

import java.sql.Timestamp;


public class AchievementsEarned {
	protected int achievementsEarnedId;
	protected Users userName;
	protected Achievements achievementId;
	protected Timestamp dateEarned;
	
	public AchievementsEarned(int achievementsEarnedId, Users userName, Achievements achievementId, Timestamp dateEarned) {
		this.achievementsEarnedId = achievementsEarnedId;
		this.userName = userName;
		this.achievementId = achievementId;
		this.dateEarned = dateEarned;
	}
	
	public AchievementsEarned(int achievementsEarnedId) {
		this.achievementsEarnedId = achievementsEarnedId;
	}
	
	public AchievementsEarned( Users userName, Achievements achievementId, Timestamp dateEarned) {
		this.userName = userName;
		this.achievementId = achievementId;
		this.dateEarned = dateEarned;
	}

	public int getAchievementsEarnedId() {
		return achievementsEarnedId;
	}

	public void setAchievementsEarnedId(int achievementsEarnedId) {
		this.achievementsEarnedId = achievementsEarnedId;
	}

	public Users getUserName() {
		return userName;
	}

	public void setUserName(Users userName) {
		this.userName = userName;
	}

	public Achievements getAchievementId() {
		return achievementId;
	}

	public void setAchievementId(Achievements achievementId) {
		this.achievementId = achievementId;
	}

	public Timestamp getDateEarned() {
		return dateEarned;
	}

	public void setDateEarned(Timestamp dateEarned) {
		this.dateEarned = dateEarned;
	}

	


	}
	

