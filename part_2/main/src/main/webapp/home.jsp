<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Home</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</head>
<body>
<c:if test="${sessionScope.message != null}">
<div>
    ${sessionScope.message}
</div>
</c:if>
<div class="d-flex justify-content-end align-items-center gap-2">
<c:choose>
    <c:when test="${sessionScope.id == null}" >
        <div>
            <a href="/main/login" class="btn btn-info">Connexion</a>
        </div>
        <div>
            <a href="/main/register" class="btn btn-info">Inscription</a>
        </div>
    </c:when>
    <c:when test="${sessionScope.id != null}">
        <div>
            Bienvenue <span class="text-capitalize">${requestScope.user.name}</span> !
        </div>
        <div>
            <a href="/main/logout" class="btn btn-info">Déconnexion</a>
        </div>
    </c:when>
</c:choose>
</div>
<c:if test="${requestScope.user != null}">

</c:if>
<c:if test="${requestScope.user != null}">
    <div class="d-flex justify-content-center gap-2">
        <a href="/main/request" class="btn btn-info">Demander un covoiturage</a>
        <a href="/main/offer" class="btn btn-success">Offrir un covoiturage</a>
    </div>
</c:if>
</body>
</html>