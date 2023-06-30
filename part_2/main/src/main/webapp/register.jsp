<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Inscription</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</head>
<body>
    <div class="w-50 mx-auto mt-3">
        <form method="post">
            <div class="d-flex flex-column gap-2">
                <input type="text" name="name" class="form-control" placeholder="john"/>
                <input type="text" name="lastname" class="form-control" placeholder="doe"/>
                <input type="text" name="email" class="form-control" placeholder="user@example.com"/>
                <input type="password" name="password" class="form-control" placeholder="password" />
                <button class="btn btn-info text-center">Inscription</button>
            </div>
        </form>
    </div>
</body>
</html>

