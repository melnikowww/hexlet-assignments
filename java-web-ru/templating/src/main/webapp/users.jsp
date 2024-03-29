<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Users</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <table>
        <c:forEach var="usr" items="${users}">
                    <tr>
                    <td>${usr.get("id")}</td>
                    <td><a href='/users/show?id=${usr.get("id")}'>${usr.get("firstName")}
                    ${usr.get("lastName")}</a></td>
                    </tr>
                </c:forEach>
        </table>
    </body>
</html>
<!-- END -->
