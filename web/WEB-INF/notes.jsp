<%-- 
    Document   : notes
    Created on : Mar 27, 2020, 11:05:34 AM
    Author     : 810585
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Week 7 Lab</title>
    </head>
    <body>
        <h1>Notes!</h1>
        <table>
            <tr>
                <th>Date Created</th>
                <th>Title</th>
                <th></th>
            </tr>
            <c:forEach var="note" items="${notes}" varStatus="status">
                <tr>
                    <td>${note.datecreated}&nbsp;</td>
                    <td>${note.title}&nbsp;</td>
                <form action="" method="get">
                    <td><input type="hidden" name="userName" value="${note.title}"/>
                    <input type="hidden" name="action" value="edit" />
                    <input type="hidden" name="selectedEdit" value="${status.count}">
                    <input type="submit" value="Edit"/></td>
                </form>
                </tr>
            </c:forEach>  
        </table>
        <h2>Edit Note</h2>
        <form action="" method="post">
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="Delete Note">
        </form><br>
        <input type="text" value="${selectedTitle}"><br>
        <form action="" method="post">
            <textarea rows="10" cols="50">${selectedContents}</textarea>
            <input type="hidden" name="action" value="save">
            <input type="submit" value="Save">
        </form>
    </body>
</html>
