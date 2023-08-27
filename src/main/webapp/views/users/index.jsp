<%@page import="malinda.appointments.models.User"%>
<%@page import="java.util.List,java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@include file="/layout/head.jsp"%>
<body>
<%@include file="/layout/nav_bar.jsp"%>

<%
	List<User> userList=new LinkedList<>();
if(request.getAttribute("userList")!=null){
	userList=(List<User>)request.getAttribute("userList");
}
%>
<div class="card mx-5 my-5">
	<% String msg = "hello"; %>
    <div class="card-header border-0 pt-6 my-1">
        <div class="card-title">
            <h5>All Users</h5>
        </div>
        <div class="card-toolbar">
            <div class="d-flex justify-content-end">
                <a href="/online-appointments/users/new"><button type="button" class="btn btn-primary">Add New Users</button></a>
            </div>
        </div>
    </div>
    <div class="card-body">
        <table class="table align-middle table-row-dashed fs-6 gy-5" >
            <thead>
                <tr class="text-start text-gray-400 fw-bolder fs-7 text-uppercase gs-0">
                     <th class="min-w-40px">ID</th>
                     <th class="min-w-175px">Name</th>
                     <th class="min-w-175px">Email</th>
                     <th class="min-w-125px">Status</th>
                     <th class="min-w-60px">Action</th>
                </tr>
            </thead>
            <tbody class="text-gray-600">
			<%
				for(User user : userList){
			%>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= (user.getIs_active()==1)?"Active" : "Inactive" %></td>
                    <td>
	                    <div class="d-flex justify-content-center">
	                    	<a href="/online-appointments/users/view?action=view &id=<%= user.getId() %>"><button type="button" class="btn btn-warning">VIEW</button></a>
	                    	<a href="/online-appointments/users/update?id=<%= user.getId() %>"><button type="button" class="btn btn-success">UPDATE</button></a>
	                    	<a href="/online-appointments/users/delete?id=<%= user.getId() %>"><button type="button" class="btn btn-danger">DELETE</button></a>
	                    </div>
                    </td>
                </tr>
			<%
				}
			%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>