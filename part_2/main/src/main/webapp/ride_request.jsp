<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Demande</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</head>
<body>
<div class="my-3">
    <div class="card mx-auto w-50">
        <div class="card-header bg-info text-white">Demander un trajet</div>
        <div class="card-body">
            <form method="post">
                <div class="d-flex flex-column gap-2">
                    <div class="input-group">
                        <span class="input-group-text col-3">Point de départ</span>
                        <input type="text" name="start_point" class="form-control" placeholder="Point de départ" />
                    </div>
                    <div class="input-group">
                        <span class="input-group-text col-3">Point d'arrivée</span>
                        <input type="text" name="end_point" class="form-control" placeholder="Point d'arrivée" />
                    </div>
                    <div class="input-group">
                        <span class="input-group-text col-3">Date de départ</span>
                        <input type="datetime-local" name="start_date" class="form-control" />
                    </div>
                    <div class="input-group">
                        <span class="input-group-text col-3">Date d'arrivée</span>
                        <input type="datetime-local" name="end_date" class="form-control" />
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-info">Demander</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>