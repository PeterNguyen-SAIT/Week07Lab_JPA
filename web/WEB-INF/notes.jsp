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
                    <td>
                        <form action="" method="get">
                            <input type="hidden" name="userName" value="${note.title}"/>
                            <input type="hidden" name="action" value="edit" />
                            <input type="hidden" name="selectedEdit" value="${status.count}">
                            <input type="submit" value="Edit"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>  
        </table>
        <h2>Edit Note</h2>
        <form action="notes" method="POST">
            <input type="submit" value="Delete Note"><br><br>
            <input type="hidden" name="tracker" value="${currentValue}">
            <input type="text" name="titleJSP" value="${selectedTitle}">
            <input type="hidden" name="contentJSP" value="${selectedContents}">
            <input type="hidden" name="action" value="delete"/>
        </form>
        <form action="notes" method="POST">
            <input type="hidden" name="titleJSP" value="${selectedTitle}">
            <input type="hidden" name="tracker" value="${currentValue}">
            <textarea rows="10" cols="50" name="contentJSP">${selectedContents}</textarea>
            <input type="hidden" name="action" value="save"/>
            <input type="submit" value="Save">
        </form>
    </body>
</html>
