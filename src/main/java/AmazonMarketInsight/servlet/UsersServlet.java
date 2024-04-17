package AmazonMarketInsight.servlet;

import AmazonMarketInsight.dal.UsersDao;
import AmazonMarketInsight.model.Users;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "UsersServlet", urlPatterns = {"/users", "/users/list", "/users/create", "/users/update", "/users/delete", "/users/login", "/users/logout"})
public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	protected UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        usersDao = UsersDao.getInstance();
    }
    
    // For reading a single user or all users
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	String path = req.getServletPath();
    	try {
    		if (path.equals("/users")) {
                resp.sendRedirect(req.getContextPath() + "/users/list");
    		}
                else if (path.contains("/list")) {
                	listUser(req, resp);
        } else if (path.contains("/create")) {
            createUser(req, resp);
            
        } else if (path.contains("/update")) {
            updateUser(req, resp);
            
        }  else if (path.contains("/delete")) {
            deleteUser(req, resp);
        } else if (path.contains("/login")) {
            loginUser(req, resp);
        } else if (path.contains("/logout")) {
            logoutUser(req, resp);
        } else {
            // Handle other cases or show an error message
        	req.setAttribute("message", "Invalid action.");
            req.getRequestDispatcher("/Users.jsp").forward(req, resp);
         
        }
    	} catch (SQLException e) {
            throw new ServletException("SQL error", e);
        }
    }
    
    private void listUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        
        String action = req.getParameter("action");
        
        if (action != null && action.equals("find")) {
            // Code for finding users (findusers.jsp)
            String userName = req.getParameter("username");
            if (userName == null || userName.trim().isEmpty()) {
                messages.put("success", "Please enter a valid username.");
            } else {
                try {
                    Users user = usersDao.getUserFromUserName(userName);
                    if (user == null) {
                        messages.put("success", "User does not exist.");
                    } else {
                        req.setAttribute("user", user);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new IOException(e);
                }
            }
            req.getRequestDispatcher("/Findusers.jsp").forward(req, resp);
        } else {
            // Code for displaying all users (users.jsp)
            List<Users> users;
            try {
                users = usersDao.getAllUsers(); // You'll need to implement this method
                req.setAttribute("users", users);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            req.getRequestDispatcher("/Users.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	String path = req.getServletPath();
    	try {
            if (path.contains("/create")) {
                createUser(req, resp);
            } else if (path.contains("/update")) {
                updateUser(req, resp);
            } else if (path.contains("/delete")) {
                deleteUser(req, resp);
            } else if (path.contains("/login")) {
                loginUser(req, resp);
            } else if (path.contains("/logout")) {
                logoutUser(req, resp);
            } else {
                    req.setAttribute("message", "Invalid action.");
                    req.getRequestDispatcher("/Users.jsp").forward(req, resp);
                    
            }
        } catch (SQLException e) {
            throw new ServletException("SQL error", e);
        }
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        // Extract user data from request...
        // Implement user creation logic...
        // Redirect or forward to the appropriate JSP page...
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the User.
        	String password = req.getParameter("password");
        	String firstName = req.getParameter("firstname");
        	String lastName = req.getParameter("lastname");
        	String email = req.getParameter("email");
        	String phoneNumber = req.getParameter("phonenumber");
        	// dob must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	Date dob = new Date();
        	String stringDob = dateFormat.format(dob);
        	
			boolean subscribed = Boolean.parseBoolean(req.getParameter("subscribed"));
        	try {
        		dob = dateFormat.parse(stringDob);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Users user = new Users(userName, password, firstName, lastName, email, phoneNumber, dob, subscribed);
	        	user = usersDao.create(user);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        // Extract user data from request...
        // Implement user update logic...
        // Redirect or forward to the appropriate JSP page...
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Users user = usersDao.getUserFromUserName(userName);
        		if(user == null) {
        			messages.put("success", "UserName does not exist. No update to perform.");
        		} else {
        			String newLastName = req.getParameter("lastname");
        			if (newLastName == null || newLastName.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid LastName.");
        	        } else {
        	        	user = usersDao.updateLastName(user, newLastName);
        	        	messages.put("success", "Successfully updated " + userName);
        	        }
        		}
        		req.setAttribute("user", user);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        // Extract username from request...
        // Implement user deletion logic...
        // Redirect or forward to the appropriate JSP page...
        // Deletion logic
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        String userName = req.getParameter("username");
        try {
            Users user = usersDao.getUserFromUserName(userName);
            if(user == null) {
    			messages.put("success", "UserName does not exist. No update to perform.");
    		} else {
            user = usersDao.delete(user);
            messages.put("success", "Successfully deleted user: " + userName);
    		}
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
    }
    
    private void loginUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        // Extract username from request...
        // Implement user deletion logic...
        // Redirect or forward to the appropriate JSP page...
        // Deletion logic
    	String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
        Users user = usersDao.loginUser(username, password);
        if (user != null) {
            req.getSession().setAttribute("user", user); // Log in the user
            resp.sendRedirect(req.getContextPath() + "/Welcome.jsp"); // Redirect to user profile or dashboard
            return;
        } else {
        	
            req.setAttribute("loginError", "Invalid username or password");
            req.getRequestDispatcher("/Login.jsp").forward(req, resp); // Return to login page
        }    
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.getRequestDispatcher("/Login.jsp").forward(req, resp);
    }
    
    private void logoutUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Invalidate the session to clear all session attributes
        HttpSession session = req.getSession(false); // Get the session if it exists, don't create a new one
        if (session != null) {
            session.invalidate(); // Destroys the session
        }
        
        // Redirect to the login page or home page
        resp.sendRedirect(req.getContextPath() + "/Login.jsp"); // Change to your login page path
    }
}
