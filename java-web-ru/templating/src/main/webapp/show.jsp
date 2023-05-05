<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <table>
        <tr>
        <td>ID</td>
        <td>${user.get("id")}</td>
        </tr>
        <tr>
        <td>First Name</td>
        <td>${user.get("firstName")}</td>
        </tr>
        <tr>
        <td>Last Name</td>
        <td>${user.get("lastName")}</td>
        </tr>
        <tr>
        <td>E-mail</td>
        <td>${user.get("email")}</td>
        </tr>
        </table>
        <a href=' /users/delete?id=${user.get("id")}'>DELETE USER</a>
    </body>
</html>
<!-- END -->
