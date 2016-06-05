<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h4>Loaded files:</h4>

    <table class="table table-striped">
        <c:forEach items="${file_name}" var="file">
            <tr>
                <td>${file}</td>
            </tr>
        </c:forEach>

        <br>
        <td><a href="/clear"> Clear list, load new files</a></td>
        </tr>
    </table>
    <form action="/zip" enctype="multipart/form-data" method="POST">
        <button type="submit" class="btn btn-default">Get archive</button>
    </form>


</div>
