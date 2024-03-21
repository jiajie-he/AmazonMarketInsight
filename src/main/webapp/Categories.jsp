<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categories</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>CategoryId</th>
                <th>Name</th>
            </tr>
            <c:forEach items="${categories}" var="category" >
                <tr>
                    <td><c:out value="${category.getCategoryId()}" /></td>
                    <td><c:out value="${category.getName()}" /></td>
                    <td><a href="products?categoryid=<c:out value="${category.getCategoryId()}"/>">Products</a></td>
                    <td><a href="usergroups?categoryid=<c:out value="${category.getCategoryId()}"/>">UserGroups</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>