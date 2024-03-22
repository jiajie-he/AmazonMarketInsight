package AmazonMarketInsight.model;

import java.sql.Timestamp;

/**
 * The type Post.
 */
public class Post {
  private int postId;
  private Timestamp created;
  private String review;
  private double rating;
  private int numInteractions;
  private boolean active;
  private int upVotes;
  private int downVotes;
  private int shares;
  private String userName;
  private String productId;

  /**
   * Instantiates a new Post.
   *
   * @param postId          the post id
   * @param created         the created
   * @param review          the review
   * @param rating          the rating
   * @param numInteractions the num interactions
   * @param active          the active
   * @param upVotes         the up votes
   * @param downVotes       the down votes
   * @param shares          the shares
   * @param userName        the user name
   * @param productId       the product id
   */
  public Post(int postId, Timestamp created, String review, double rating, int numInteractions, boolean active,
              int upVotes, int downVotes, int shares, String userName, String productId) {
    this.postId = postId;
    this.created = created;
    this.review = review;
    this.rating = rating;
    this.numInteractions = numInteractions;
    this.active = active;
    this.upVotes = upVotes;
    this.downVotes = downVotes;
    this.shares = shares;
    this.userName = userName;
    this.productId = productId;
  }

  // Getters and setters for each field

  /**
   * Gets post id.
   *
   * @return the post id
   */
  public int getPostId() {
    return postId;
  }

  /**
   * Sets post id.
   *
   * @param postId the post id
   */
  public void setPostId(int postId) {
    this.postId = postId;
  }

  /**
   * Gets created.
   *
   * @return the created
   */
  public Timestamp getCreated() {
    return created;
  }

  /**
   * Sets created.
   *
   * @param created the created
   */
  public void setCreated(Timestamp created) {
    this.created = created;
  }

  /**
   * Gets review.
   *
   * @return the review
   */
  public String getReview() {
    return review;
  }

  /**
   * Sets review.
   *
   * @param review the review
   */
  public void setReview(String review) {
    this.review = review;
  }

  /**
   * Gets rating.
   *
   * @return the rating
   */
  public double getRating() {
    return rating;
  }

  /**
   * Sets rating.
   *
   * @param rating the rating
   */
  public void setRating(double rating) {
    this.rating = rating;
  }

  /**
   * Gets num interactions.
   *
   * @return the num interactions
   */
  public int getNumInteractions() {
    return numInteractions;
  }

  /**
   * Sets num interactions.
   *
   * @param numInteractions the num interactions
   */
  public void setNumInteractions(int numInteractions) {
    this.numInteractions = numInteractions;
  }

  /**
   * Is active boolean.
   *
   * @return the boolean
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Sets active.
   *
   * @param active the active
   */
  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Gets up votes.
   *
   * @return the up votes
   */
  public int getUpVotes() {
    return upVotes;
  }

  /**
   * Sets up votes.
   *
   * @param upVotes the up votes
   */
  public void setUpVotes(int upVotes) {
    this.upVotes = upVotes;
  }

  /**
   * Gets down votes.
   *
   * @return the down votes
   */
  public int getDownVotes() {
    return downVotes;
  }

  /**
   * Sets down votes.
   *
   * @param downVotes the down votes
   */
  public void setDownVotes(int downVotes) {
    this.downVotes = downVotes;
  }

  /**
   * Gets shares.
   *
   * @return the shares
   */
  public int getShares() {
    return shares;
  }

  /**
   * Sets shares.
   *
   * @param shares the shares
   */
  public void setShares(int shares) {
    this.shares = shares;
  }

  /**
   * Gets user name.
   *
   * @return the user name
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Sets user name.
   *
   * @param userName the user name
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Gets product id.
   *
   * @return the product id
   */
  public String getProductId() {
    return productId;
  }

  /**
   * Sets product id.
   *
   * @param productId the product id
   */
  public void setProductId(String productId) {
    this.productId = productId;
  }
}
