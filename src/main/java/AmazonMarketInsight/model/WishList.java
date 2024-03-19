package AmazonMarketInsight.model;

import java.util.Timestamp;


public class WishList {
	protected int wishListId;
	protected Users userName;
	protected Products productId;
	
	public WishList(int wishListId, Users userName, Products productId) {
		this.wishListId = wishListId;
		this.userName = userName;
		this.productId = productId;
	}
	
	public WishList(int wishListId) {
		this.wishListId = wishListId;
	}
	
	public WishList(Users userName, Products productId) {
		this.userName = userName;
		this.productId = productId;
	}

	public int getWishListId() {
		return wishListId;
	}

	public void setWishListId(int wishListId) {
		this.wishListId = wishListId;
	}

	public Users getUserName() {
		return userName;
	}

	public void setUserName(Users userName) {
		this.userName = userName;
	}

	public Products getProductId() {
		return productId;
	}

	public void setProductId(Products productId) {
		this.productId = productId;
	}
	
}
