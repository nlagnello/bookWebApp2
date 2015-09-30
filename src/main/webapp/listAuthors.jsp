<%-- 
    Document   : listAuthors
    Created on : Sep 21, 2015, 9:36:05 PM
    Author     : jlombardo
    Purpose    : display list of author records and (in the future) provide
                 a way to add/edit/delete records
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author List</title>
        <link href="list.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="list.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>Author List</h1>
        <div id="menu">
            <h1>Pick an Administrative Task</h1>
            <ol>
                <li><button type="button" id="create" name="Submit">Add New Author</button></li>
                <li><button type="button" id="update" name="Submit">Update an Author</button></li>
                <li><button type="button" id="delete" name="Submit">Delete an Author</button></li>
            </ol>
        </div>
        <div id="table">
            <table width="500" border="1" cellspacing="0" cellpadding="4">
                <tr style="background-color: black;color:white;">
                    <th align="left" class="tableHead">ID</th>
                    <th align="left" class="tableHead">Author Name</th>
                    <th align="right" class="tableHead">Date Added</th>
                </tr>
                <c:forEach var="a" items="${authors}" varStatus="rowCount">
                    <c:choose>
                        <c:when test="${rowCount.count % 2 == 0}">
                            <tr style="background-color: white;">
                            </c:when>
                            <c:otherwise>
                            <tr style="background-color: #ccffff;">
                            </c:otherwise>
                        </c:choose>
                        <td align="left">${a.authorId}</td>
                        <td align="left">${a.authorName}</td>
                        <td align="right">
                            <fmt:formatDate pattern="M/d/yyyy" value="${a.dateCreated}"></fmt:formatDate>
                            </td>
                        </tr>
                </c:forEach>
            </table>
            <c:if test="${errMsg != null}">
                <p style="font-weight: bold;color: red;width:500px;">Sorry, data could not be retrieved:<br>
                    ${errMsg}</p>
                </c:if>
        </div>
        <div id="deleteModal" style='display:none'>
            <div id="modalD" title="Delete by Author Id">
                <form method="POST" action="AuthorController?action=delete">
                <label>Author Id:</label><input type="text" name="deleteId" id="deleteId" placeholder="Author Id">
                <button type="submit" id="deleteBtn" name="deleteBtn">Submit</button>
            </form>
            </div>
        </div>
        <div id="createModal" style='display:none'>
            <div id="modalC" title="Create new Record">
                <form method="POST" action="AuthorController?action=create">
<!--                    <label>Author Id:</label><input type="text" name="createId" id="createId" placeholder="Author Id">-->
                <label>Author Name:</label><input type="text" name="createName" id="createName" placeholder="Author Name">
                <button type="submit" id="createBtn" name="createBtn">Submit</button>
            </form>
            </div>
        </div>
        <div id="updateModal" style='display:none'>
            <div id="modalU" title="Update by Author Id">
                <form method="POST" action="AuthorController?action=update">
                    <label>Author Id:</label><input type="text" name="updateId" id="updateId" placeholder="Author Id">
                <label>Author Name:</label><input type="text" name="updateName" id="updateName" placeholder="Author Name">
                <button type="submit" id="updateBtn" name="updateBtn">Submit</button>
            </form>
            </div>
        </div>
    </body>
</html>
