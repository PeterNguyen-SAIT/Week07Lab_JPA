<%-- 
    Document   : notes
    Created on : Mar 27, 2020, 11:05:34 AM
    Author     : 810585
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Week 7 Lab</title>
    </head>
    <body>
        <h1>Notes!</h1>
        <table class="tg">
            <tr>
                <th class="tg-0pky">Date Created</th>
                <th class="tg-0lax">Title</th>
                <th class="tg-0lax"></th>
            </tr>
            <c:forEach var="User" items="${NoteList}" varStatus="status">
                <tr>
                    <td class="tg-0lax">${Note.datecreated}</td>
                    <td class="tg-0lax">${Note.title}</td>
                <form action="" method="post">
                    <input type="hidden" name="userName" value="${Note.title}"/>
                    <input type="hidden" name="action" value="edit" />
                    <input type="submit" value="Edit"/></td>
                </form>
                </tr>
            </c:forEach>  
        </table>
        <h2>Edit Note</h2>
        <form>
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="delete">
        </form><br>
        <input type="text" value="${Title}"><br>
        <form>
            <textarea rows="10" cols="50">${Contents}</textarea>
            <input type="hidden" name="action" value="save">
            <input type="submit" value="save">
        </form>
    </body>
</html>
