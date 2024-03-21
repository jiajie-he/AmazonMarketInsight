package amazon.model;
import java.sql.Date;
import amazon.model.*;

public class PostComments {
    private int postCommentId; // This is a surrogate key
    private Date created;
    private String comment;
    private int upVotes;
    private int downVotes;
    private int postId;
    private String userName;
	public PostComments(Date created, String comment, int upVotes, int downVotes, int postId, String userName) {
		super();
		this.created = created;
		this.comment = comment;
		this.upVotes = upVotes;
		this.downVotes = downVotes;
		this.postId = postId;
		this.userName = userName;
	}
	public PostComments(int postCommentId) {
		super();
		this.postCommentId = postCommentId;
	}
	public int getPostCommentId() {
		return postCommentId;
	}
	public void setPostCommentId(int postCommentId) {
		this.postCommentId = postCommentId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getUpVotes() {
		return upVotes;
	}
	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}
	public int getDownVotes() {
		return downVotes;
	}
	public void setDownVotes(int downVotes) {
		this.downVotes = downVotes;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

    
}
