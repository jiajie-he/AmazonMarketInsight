package AmazonMarketInsight.model;

/**
 * Administrators is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class Administrators extends Persons {
	protected Boolean canEditPosts;
	protected Boolean canDeletePosts;
	
	public Administrators(String userName, String password, String firstName, String lastName, String email,
			String phoneNumber, Boolean canEditPosts, Boolean canDeletePosts) {
		super(userName, password, firstName, lastName, email, phoneNumber);
		this.canEditPosts = canEditPosts;
		this.canDeletePosts = canDeletePosts;
	}
	
	public Administrators(String userName) {
		super(userName);
	}
	/** Getters and setters. */
	public Boolean getCanEditPosts() {
		return canEditPosts;
	}

	public void setCanEditPosts(Boolean canEditPosts) {
		this.canEditPosts = canEditPosts;
	}

	public Boolean getCanDeletePosts() {
		return canDeletePosts;
	}

	public void setCanDeletePosts(Boolean canDeletePosts) {
		this.canDeletePosts = canDeletePosts;
	}
}
