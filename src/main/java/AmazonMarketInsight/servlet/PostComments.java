package AmazonMarketInsight.servlet;

import AmazonMarketInsight.dal.*;
import AmazonMarketInsight.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/postcomments")
public class PostComments extends HttpServlet {
	
	protected PostCommentsDao postCommentsDao;
	
	@Override
	public void init() throws ServletException {
		postCommentsDao = PostCommentsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        List<PostComments> postComments = new ArrayList<PostComments>();
        
		// Retrieve PostComments depending on valid PostId or UserName.
        String postId = req.getParameter("postid");
        String userName = req.getParameter("username");
        
        try {
	        if (postId != null && !postId.trim().isEmpty()) {
	        	// If the postid param is provided then ignore the username param.
	        	Posts post = new Posts(Integer.parseInt(postId));
	        	postComments = postCommentsDao.getPostCommentsForPost(post);
	        	messages.put("title", "PostComments for PostId " + postId);
	        } else if (userName != null && !userName.trim().isEmpty()) {
	        	// If postid is invalid, then use the username param.
	        	Users user = new Users(userName);
	        	postComments = postCommentsDao.getPostCommentsForUser(user);
	        	messages.put("title", "PostComments for UserName " + userName);
	        } else {
	        	messages.put("title", "Invalid PostId and UserName.");
	        }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.setAttribute("postComments", postComments);
        req.getRequestDispatcher("/PostComments.jsp").forward(req, resp);
	}
}
