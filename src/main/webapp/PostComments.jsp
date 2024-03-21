<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PostComments</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>PostCommentId</th>
                <th>Created</th>
                <th>Comment</th>
                <th>UpVotes</th>
                <th>DownVotes</th>
                <th>UserName</th>
                <th>PostId</th>
            </tr>
            <c:forEach items="${postComments}" var="postComment" >
                <tr>
                    <td><c:out value="${postComment.getPostCommentId()}" /></td>
                    <td><fmt:formatDate value="${postComment.getCreated()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
                    <td><c:out value="${postComment.getComment()}" /></td>
                    <td><c:out value="${postComment.getUpVotes()}" /></td>
                    <td><c:out value="${postComment.getDownVotes()}" /></td>
                    <td><c:out value="${postComment.getUser().getUserName()}" /></td>
                    <td><c:out value="${postComment.getPost().getPostId()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
