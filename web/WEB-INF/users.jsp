<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        <b>${error}</b>
        <c:if test="${user.size() lt 1}">
            <b>No users found.Please add a user.</b>
        </c:if>

        <c:if test="${user.size() gt 0}">
            <table border="1">
                <tr>
                    <th>Email</th>
                    <th>Fist Name</th>
                    <th>last Name</th>
                    <th>Role</th>
                </tr>
                <c:forEach var="user" items="${user}">            
                    <tr>
                        <td>${user.email}</td>
                        <td>${user.firstname}</td>
                        <td>${user.lastname}</td>
                        <td>${user.role.roleName}</td>

                        <td><a href="<c:url value='/User'><c:param name='action' value='edit'/>
                                   <c:param name='Email' value='${user.email}'/>
                               </c:url>">Edit</a></td>

                        <td><a href="<c:url value='/User'><c:param name='action' value='delete'/>
                                   <c:param name='Email' value='${user.email}'/>
                               </c:url>">delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${change eq 'edit'}">
            <h2>Edit Users</h2>
            <b>${message}</b>
            <form action="User" method="post">
                Email: ${editUser.email}<br>
                First name: <input type="text" name="Fname" value="${editUser.firstname}"><br>
                Last Name: <input type="text" name="Lname" value="${editUser.lastname}"><br>
                Password: <input type="password" name="password"><br>
                Role:

                <select name="role"><br>
                    <option value="1" <c:if test="${editUser.role.roleID == 1}">selected</c:if>>System Admin</option>
                    <option value="2" <c:if test="${editUser.role.roleID == 2}">selected</c:if>>Regular User</option>
                    </select> <br>
                    <input type="hidden" name="email" value="${editUser.email}">
                <input type="submit" value="Update" name="action">
                <input type="submit" value="Cancel" name="action">
            </form>  
        </c:if>

        <c:if test="${change ne 'edit'}">
            <h2>Add Users</h2>
            <b>${message}</b>
            <form action="User?action=add" method="post">
                Email: <input type="text" name="email"><br>            
                First name: <input type="text" name="Fname"><br>            
                Last Name: <input type="text" name="Lname"><br>
                Password: <input type="password" name="password"><br>
                Role: <select name="role"><br>
                    <option value="1">System Admin</option>
                    <option value="2">Regular User</option>
                </select> <br>
                <input type="submit" value="Add">
            </form>

        </c:if>
    </body>
</html>
