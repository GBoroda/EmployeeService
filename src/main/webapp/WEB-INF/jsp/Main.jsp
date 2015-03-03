<%@ page import="com.misha.controller.MainController" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="../../css/Main.css"/>" >
    <meta http-equiv="content-type" content="text/html; charset=windows-1251">
</head>

<body>
        <table style="float: left;">
            <tr style="background-color: teal; color: white; text-align: center">
                <th>id</th>
                <th>Name</th>
                <th width="200">Avg. Salary</th>
                <th></th>
            </tr>
            <c:forEach var="dep" items="${deps}">
                <tr style="background-color: white; color: black; text-align: center">
                    <td>${dep.id}</td>
                    <td width="175">${dep.name}</td>
                    <td>${dep.avgSalary}</td>
                    <td><form:form method="DELETE" action="/remove/department/${dep.id}"><input type="submit" value="delete"></form:form></td>
                </tr>
            </c:forEach>
            <td style="background-color: #ffffff"></td>
            <td width="200"><div>
                <form action="<c:url value="/department/add"/>" method="POST">
                    <p>Name:<input type="text" name="name"/></p>
                    <input type="submit" value="Add"/>
                </form>
            </div></td>
        </table>


        <table style="float: right;;">
            <tr style="background-color: teal; color: white; text-align: center;">
                <th width="30">id</th>
                <th width="200">First Name</th>
                <th width="200">Last Name</th>
                <th width="60">Salary</th>
                <th width="200">Department id</th>
                <th width="60"></th>
            </tr>
            <c:forEach var="employee" items="${emps}">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.salary}</td>
                    <td>${employee.departmentId}</td>
                    <td><form:form method="DELETE" action="/remove/employee/${employee.id}"><input type="submit" value="delete"></form:form></td>
                </tr>
            </c:forEach>
            <td>
                <form action="<c:url value="/employee/add"/>" method="POST">
                    <p>
                    <label for="departmentIdInput">Department ID: </label>
                    <input type="" name="departmentId" id="departmentIdInput"/>
                    </p><p>
                    <label for="firstNameInput">First Name: </label>
                    <input type="text" name="firstName" id="firstNameInput" />
                    </p><p>
                    <label for="lastNameInput">Last Name: </label>
                    <input type="text" name="lastName" id="lastNameInput" />
                    </p><p>
                    <label for="salaryInput">Salary: </label>
                    <input type="text" name="salary" id="salaryInput" />
                    </p><p>
                    <input type="submit" value="Add"/></p>
                </form>
                <P><form:form method="GET" action="/edit/employee/"><input type="submit" value="edit page"></form:form></p>
            </td>
        </table>
</body>
</html>