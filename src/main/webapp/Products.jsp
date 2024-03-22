<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>ProductId</th>
                <th>Title</th>
                <th>ImgUrl</th>
                <th>ProductUrl</th>
                <th>Stars</th>
                <th>Reviews</th>
                <th>Price</th>
                <th>ListedPrice</th>
                <th>CategoryId</th>
                <th>BestSeller</th>
                <th>BoughtInLastMonth</th>
                <th>TimesPosted</th>
                <th>NumViews</th>
                <th>Likes</th>
                <th>Dislikes</th>
            </tr>
            <c:forEach items="${products}" var="product" >
                <tr>
                    <td><c:out value="${product.getProductId()}" /></td>
                    <td><c:out value="${product.getTitle()}" /></td>
                    <td><c:out value="${product.getImgUrl()}" /></td>
                    <td><c:out value="${product.getProductUrl()}" /></td>
                    <td><c:out value="${product.getStars()}" /></td>
                    <td><c:out value="${product.getReviews()}" /></td>
                    <td><c:out value="${product.getPrice()}" /></td>
                    <td><c:out value="${product.getListedPrice()}" /></td>
                    <td><c:out value="${product.getCategory().getCategoryId()}" /></td>
                    <td><c:out value="${product.getBestSeller()}" /></td>
                    <td><c:out value="${product.getBoughtInLastMonth()}" /></td>
                    <td><c:out value="${product.getTimesPosted()}" /></td>
                    <td><c:out value="${product.getNumViews()}" /></td>
                    <td><c:out value="${product.getLikes()}" /></td>
                    <td><c:out value="${product.getDislikes()}" /></td>
                    <td><a href="wishlists?productid=<c:out value="${product.getProductId()}"/>">WishLists</a></td>
                    <td><a href="deleteproduct?productid=<c:out value="${product.getProductId()}"/>">Delete</a></td>
                    <td><a href="posts?productid=<c:out value="${product.getProductId()}"/>">Posts</a></td>
                    <td><a href="deleteproduct?productid=<c:out value="${product.getProductId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
