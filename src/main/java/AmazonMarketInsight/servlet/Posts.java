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


@WebServlet("/posts")
public class Posts extends HttpServlet {
	
	protected PostsDao postsDao;
	
	@Override
	public void init() throws ServletException {
		postsDao = PostsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid username.");
        } else {
        	messages.put("title", "Posts for " + userName);
        }
        
        // Retrieve Users, and store in the request.
        List<Posts> posts = new ArrayList<Posts>();
        try {
        	Users user = new Users(userName);
        	posts = postsDao.getPostsForUser(user);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/Posts.jsp").forward(req, resp);
	}
}
