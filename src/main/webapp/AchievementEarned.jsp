<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AchievementsEarned</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>AchievementsEarnedId</th>
                <th>UserName</th>
                <th>AchievementId</th>
                <th>DateEarned</th>
            </tr>
            <c:forEach items="${achievementsEarned}" var="achievementsEarned" >
                <tr>
                    <td><c:out value="${achievementsEarned.getAchievementEarnedId()}" /></td>
                    <td><c:out value="${achievementsEarned.getUser().getUserName()}" /></td>
                    <td><c:out value="${achievementsEarned.getAchievement().getAchievementId()}" /></td>
                    <td><fmt:formatDate value="${achievementsEarned.getDateEarned()}" pattern="MM-dd-yyyy"/></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
