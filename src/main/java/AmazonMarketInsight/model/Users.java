package AmazonMarketInsight.model;

import java.util.Date;

/**
 * Users is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
//Users Model Class
public class Users extends Persons{
	protected Date doB;
	protected Boolean subscribed;
	
	public Users(String userName, String password, String firstName, String lastName, String email, String phoneNumber,
			Date doB, Boolean subscribed) {
		super(userName, password, firstName, lastName, email, phoneNumber);
		this.doB = doB;
		this.subscribed = subscribed;
	}
	
	public Users(String userName) {
		super(userName);
	}

	public Date getDoB() {
		return doB;
	}

	public void setDoB(Date doB) {
		this.doB = doB;
	}

	public Boolean getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(Boolean subscribed) {
		this.subscribed = subscribed;
	}

	
}
