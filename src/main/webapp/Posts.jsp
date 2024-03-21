<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Post</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>PostId</th>
                <th>Created</th>
                <th>Review</th>
                <th>Rating</th>
                <th>UserName</th>
                <th>ProductId</th>
                <th>NumInteractions</th>
                <th>Active</th>
                <th>UpVotes</th>
                <th>DownVotes</th>
                <th>Shares</th>
            </tr>
            <c:forEach items="${posts}" var="post" >
                <tr>
                    <td><c:out value="${post.getPostId()}" /></td>
                    <td><fmt:formatDate value="${post.getCreated()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
                    <td><c:out value="${post.getReview()}" /></td>
                    <td><c:out value="${post.getRating()}" /></td>
                    <td><c:out value="${post.getUser().getUserName()}" /></td>
                    <td><c:out value="${post.getProduct().getProductId()}" /></td>
                    <td><c:out value="${post.getNumInteractions()}" /></td>
                    <td><c:out value="${post.getActive)}" /></td>
                    <td><c:out value="${post.getUpVotes()}" /></td>
                    <td><c:out value="${post.getDownVotes()}" /></td>
                    <td><c:out value="${post.getShares()}" /></td>
                    <td><a href="postcomments?postid=<c:out value="${post.getPostId()}"/>">PostComments</a></td>
                    <td><a href="deletepost?postid=<c:out value="${post.getPostId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
