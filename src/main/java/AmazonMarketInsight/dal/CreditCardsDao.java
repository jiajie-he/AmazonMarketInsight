package AmazonMarketInsight.dal;

import AmazonMarketInsight.model.*;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying CreditCards table in your
 * MySQL instance. This is used to store {@link CreditCards} into your MySQL instance and 
 * retrieve {@link CreditCards} from MySQL instance.
 */

public class CreditCardsDao {
    private static CreditCardsDao instance = null;
    protected ConnectionManager connectionManager;

    protected CreditCardsDao() {
        connectionManager = new ConnectionManager();
    }

    public static CreditCardsDao getInstance() {
        if (instance == null) {
            instance = new CreditCardsDao();
        }
        return instance;
    }
	
	public CreditCards create(CreditCards creditCard) throws SQLException {
		// Insert into the superclass table first.
		create(new CreditCards(creditCard.getCardNumber(), creditCard.getExpiration(),
			creditCard.getUserName()));

		String insertCreditCard = "INSERT INTO CreditCards(CardNumber, Expiration, UserName) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCreditCard);
			insertStmt.setLong(1, creditCard.getCardNumber().longValue());
			insertStmt.setTimestamp(2, new Timestamp(creditCard.getExpiration().getTime()));
			insertStmt.setString(3, creditCard.getUserName().getUserName());
			insertStmt.executeUpdate();
			return creditCard;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Update the LastName of the CreditCards instance.
	 * This runs a UPDATE statement.
	 */
	public CreditCards updateExpiration(CreditCards creditCard, Date newExpiration) throws SQLException {
		String updateuser = "UPDATE CreditCards SET Expiration=? WHERE Expiration=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateuser);
			updateStmt.setTimestamp(1, new Timestamp(newExpiration.getTime())); 
			updateStmt.setLong(2, creditCard.getCardNumber().longValue());
			updateStmt.executeUpdate();
			
			// Update the user param before returning to the caller.
			creditCard.setExpiration(newExpiration);
			return creditCard;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the CreditCards instance.
	 * This runs a DELETE statement.
	 */
	public CreditCards delete(CreditCards creditCard) throws SQLException {
		String deleteCreditCard = "DELETE FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			deleteStmt.setLong(1, creditCard.getCardNumber().longValue());
			deleteStmt.executeUpdate();

			// Then also delete from the superclass.
			// Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
			// super.delete() without even needing to delete from CreditCards first.
			//super.delete(creditCard);

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	public CreditCards getCreditCardByCardNumber(BigInteger cardNumber) throws SQLException {
	    String selectCreditCard = "SELECT CardNumber, Expiration, UserName FROM CreditCards WHERE CardNumber=?;";
	    try (Connection connection = connectionManager.getConnection();
	         PreparedStatement selectStmt = connection.prepareStatement(selectCreditCard)) {
	        selectStmt.setLong(1, cardNumber.longValue()); // Assuming that CardNumber can fit into a long
	        try (ResultSet results = selectStmt.executeQuery()) {
	            if (results.next()) {
	                BigInteger resultCardNumber = BigInteger.valueOf(results.getLong("CardNumber"));
	                Timestamp expirationTimestamp = results.getTimestamp("Expiration");
	                Date expirationDate = new Date(expirationTimestamp.getTime());
	                String userName = results.getString("UserName");
	               
	                UsersDao usersDao = UsersDao.getInstance();
	                Users user = usersDao.getUserFromUserName(userName); 
	                
	                CreditCards creditCard = new CreditCards(resultCardNumber, expirationDate, user);
	                return creditCard;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();  // Consider logging this exception instead
	        throw e;
	    }
	    return null;
	}

	

	public List<CreditCards> getCreditCardsByUserName(String userName) throws SQLException {
	    List<CreditCards> creditCards = new ArrayList<>();
	    String selectCreditCards = "SELECT CardNumber, Expiration, UserName FROM CreditCards WHERE UserName=?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectCreditCards);
	        selectStmt.setString(1, userName);
	        results = selectStmt.executeQuery();
	        while (results.next()) {
	            String cardNumberStr = results.getString("CardNumber");
	            BigInteger cardNumber = new BigInteger(cardNumberStr);  // Convert string to BigInteger
	            Date expiration = new Date(results.getTimestamp("Expiration").getTime());  // Assuming 'Expiration' is stored as a Timestamp
	            String resultUserName = results.getString("UserName");

	            UsersDao usersDao = UsersDao.getInstance();
	            Users user = usersDao.getUserFromUserName(resultUserName);  // Assuming you want to attach the Users object

	            if (user != null) {
	                CreditCards creditCard = new CreditCards(cardNumber, expiration, user);
	                creditCards.add(creditCard);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Better to use a logger
	        throw e;
	    } finally {
	        if (results != null) {
	            results.close();
	        }
	        if (selectStmt != null) {
	            selectStmt.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	    return creditCards;
	}

}
