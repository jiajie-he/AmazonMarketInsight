<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Achievements</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>AchievementId</th>
                <th>Created</th>
            </tr>
            <c:forEach items="${achievements}" var="achievement" >
                <tr>
                    <td><c:out value="${achievement.getAchievementId()}" /></td>
                    <td><fmt:formatDate value="${achievement.getCreated()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
                    <td><a href="achievementsearned?achievementid=<c:out value="${achievement.getAchievementId()}"/>">AchievementsEarned</a></td>
                    <td><a href="deleteachievement?achievementid=<c:out value="${achievement.getAchievementId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>