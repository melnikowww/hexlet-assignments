<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>DELETE</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
    <h5>Do you want delete user ${user.get("firstName")} ${user.get("lastName")}?</h5>
    <form action="/users/delete?id=${id}" method="post">
        <button type="submit" class="btn btn-danger">DELETE</button>
    </form>
    </body>
</html>
<!-- END -->
