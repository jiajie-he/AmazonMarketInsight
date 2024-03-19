package AmazonMarketInsight.dal;

import AmazonMarketInsight.model.*;

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
public class CreditCardsDao{
	// Single pattern: instantiation is limited to one object.
	private static CreditCardsDao instance = null;
	protected CreditCardsDao() {
		super();
	}
	public static CreditCardsDao getInstance() {
		if(instance == null) {
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
			insertStmt.setBigInt(1, creditCard.getCardNumber());
			insertStmt.setTimestamp(2, new Timestamp(creditCard.Expiration().getTime()));
			insertStmt.setString(3, creditCard.getUserName());
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
			updateStmt.setString(1, newExpiration);
			updateStmt.setString(2, creditCards.getCreditCardNumber());
			updateStmt.executeUpdate();
			
			// Update the user param before returning to the caller.
			creditCards.setExpiration(newExpiration);
			return user;
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
			deleteStmt.setString(1, creditCard.getCardNumber());
			deleteStmt.executeUpdate();

			// Then also delete from the superclass.
			// Note: due to the fk constraint (ON DELETE CASCADE), we could simply call
			// super.delete() without even needing to delete from CreditCards first.
			super.delete(creditCard);

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
	
	public CreditCards getCreditCardByCardNumber(BigInteger CardNumber) throws SQLException {
		String selectCreditCard =
			"SELECT CreditCards.CardNumber AS CardNumber, Expiration, UserName " +
			"FROM CreditCards " +
			"WHERE CreditCards.CardNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setString(1, CardNumber);
			results = selectStmt.executeQuery();
			if(results.next()) {
				BigInteger resultCardNumber = results.getString("CardNumber");
				Date Expiration = results.getString("Expiration");
				String UserName = results.getString("UserName");
				CreditCards creditCard = new CreditCards(resultCardNumber, Expiration, UserName);
				return creditCard;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<CreditCards> getCreditCardsByUserName(String userName)
			throws SQLException {
		List<CreditCards> creditCards = new ArrayList<CreditCards>();
		String selectCreditCards =
			"SELECT CreditCards.CardNumber AS CardNumber, Expiration, UserName" +
			"FROM CreditCards"
			"WHERE Users.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCards);
			selectStmt.setString(1, UserName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				BigInteger CardNumber = results.getString("CardNumber");
				Date Expiration = results.getString("Expiration");
				String resultUserName = results.getString("UserName");
				CreditCards creditCard = new CreditCards(CardNumber, Expiration, resultUserName);
				creditCards.add(creditCard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return creditCards;
	}
}
