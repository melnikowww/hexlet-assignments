<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tag:application>
    <table class="table">
        <head>
            <th>ID</th>
            <th>Полное имя</th>
        </head>
        <body>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.get("id")}</td>
                <td><a href='/users/show?id=${user.get("id")}'>${user.get("firstName")} ${user.get("lastName")}</a></td>
            </tr>
        </c:forEach>
        </body>
    </table>
</tag:application>
