package AmazonMarketInsight.model;

import java.util.Date;

/**
 * CreditCards is a simple, plain old java objects (POJO).
 * Well, almost (it extends NOT).
 */
public class CreditCards{
	protected BigInteger CardNumber;
	protected Date Expiration;
	protected Users UserName;
	
	public CreditCards(BigInteger cardNumber, Date expiration, Users userName) {
		this.CardNumber = cardNumber;
		this.Expiration = expiratoin;
		this.UserName = userName;
	}
	
	public CreditCards(BigInteger cardNumber) {
		this.CardNumber = cardNumber;
	}

	/** Getters and setters. */
	
	public Date getCardNumber() {
		return CardNumber;
	}

	public void setCardNumber(Date cardNumber) {
		this.CardNumber = cardNumber;
	}
	
	public Date getExpiration() {
		return Expiration;
	}

	public void setExpiration(Date expiration) {
		this.Expiration = expiration;
	}
	
	public Date getUserName() {
		return UserName;
	}

	public void setUserName(Date userName) {
		this.UserName = userName;
	}
}
