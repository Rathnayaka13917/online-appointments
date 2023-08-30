<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List,java.util.LinkedList"%>
<%@page import="malinda.appointments.models.Consultant"%>
<!DOCTYPE html>
<html>
<%@include file="/layout/head.jsp"%>
<body>
<%@include file="/layout/nav_bar.jsp"%>
<%
	List<Consultant> consultantsList = new LinkedList<>();
	if(request.getAttribute("consultantList")!=null){
		consultantsList =(List<Consultant>)request.getAttribute("consultantList");
	}
%>
<div class="card mx-5 my-5">
    <div class="card-header border-0 pt-6 my-1">
        <div class="card-title">
            <h5>All Consultants</h5>
        </div>
        <div class="card-toolbar">
            <div class="d-flex justify-content-end">
                <a href="/online-appointments/consultants/new"><button type="button" class="btn btn-primary">Add New Consultant</button></a>
            </div>
        </div>
    </div>
    <div class="card-body">
        <table class="table align-middle table-row-dashed fs-6 gy-5" data-order="[[1,'asc']]" data-page-length="25">
            <thead>
                <tr class="text-start text-gray-400 fw-bolder fs-7 text-uppercase gs-0">
                     <th class="min-w-40px">ID</th>
                     <th class="min-w-125px">Name</th>
                     <th class="min-w-125px">Email</th>
                     <th class="min-w-125px">Country</th>
                     <th class="min-w-125px">Status</th>
                     <th class="min-w-60px">Action</th>
                </tr>
            </thead>
            <tbody class="text-gray-600">
            <%
				for(Consultant consultant : consultantsList){
			%>
				<tr>
					<td><%= consultant.getId() %></td>
					<td><%= consultant.getName() %></td>
					<td><%= consultant.getEmail() %></td>
					<td><%= consultant.getCountry() %></td>
					<td><%= (consultant.getIs_active()==1)?"Active" : "Inactive" %></td>
					<td>
						<div class="d-flex justify-content-center">
	                    	<a href="/online-appointments/consultants/view?id=<%= consultant.getId() %>" class="btn btn-warning">VIEW</a>
	                    	<a href="/online-appointments/consultants/update?id=<%= consultant.getId() %>" class="btn btn-success">UPDATE</a>
	                    	<a href="/online-appointments/consultants/delete?id=<%= consultant.getId() %>" class="btn btn-danger">DELETE</a>
	                    </div>
					</td>
				</tr>
			<%} %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>