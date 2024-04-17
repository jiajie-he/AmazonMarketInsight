<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>DoB</th>
                <th>Subscribed</th>
            </tr>
            <c:forEach items="${users}" var="user" >
                <tr>
                    <td><c:out value="${user.getUserName()}" /></td>
                    <td><fmt:formatDate value="${user.getDoB()}" pattern="MM-dd-yyyy"/></td>
                    <td><c:out value="${user.getSubscribed()}" /></td>
                    <td><a href="creditcard?username=<c:out value="${user.getUserName()}"/>">CreditCards</a></td>
                    <td><a href="delete?username=<c:out value="${user.getUserName()}"/>">Delete</a></td>
                    <td><a href="achievementsearned?username=<c:out value="${user.getUserName()}"/>">AchievementsEarned</a></td>
                    <td><a href="deleteuser?username=<c:out value="${user.getUserName()}"/>">Delete</a></td>
                    <td><a href="wishlist?username=<c:out value="${user.getUserName()}"/>">WishLists</a></td>     
                    <td><a href="usergroup?username=<c:out value="${user.getUserName()}"/>">UserGroups</a></td>
                    <td><a href="post?username=<c:out value="${user.getUserName()}"/>">Posts</a></td>
                    <td><a href="postcomment?username=<c:out value="${user.getUserName()}"/>">PostComments</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
