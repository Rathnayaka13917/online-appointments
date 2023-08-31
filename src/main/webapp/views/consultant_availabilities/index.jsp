<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List,java.util.LinkedList"%>
<%@page import="malinda.appointments.models.ConsultantAvailability"%>
<%@page import="malinda.appointments.models.Consultant"%>
<!DOCTYPE html>
<html>
<%@include file="/layout/head.jsp"%>
<body>
<%@include file="/layout/nav_bar.jsp"%>
<%
	List<ConsultantAvailability> availabilities = new LinkedList<>();
	if(request.getAttribute("availabilityList")!=null){
		availabilities =(List<ConsultantAvailability>)request.getAttribute("availabilityList");
	}
	Consultant consultant = new Consultant();
	if(request.getAttribute("consultant")!=null){
		consultant =(Consultant)request.getAttribute("consultant");
	}
%>
<div class="card mx-5 my-5">
    <div class="card-header border-0 pt-6 my-1">
        <div class="card-title">
            <h5><%= consultant.getName() %> Availabilities</h5>
        </div>
        <div class="card-toolbar">
            <div class="d-flex justify-content-end">
                <a href="/online-appointments/consultant-availabilities/new?id=<%= consultant.getId() %>"><button type="button" class="btn btn-primary">Add New Availability</button></a>
            </div>
        </div>
    </div>
    <div class="card-body">
        <table class="table align-middle table-row-dashed fs-6 gy-5" data-order="[[1,'asc']]" data-page-length="25">
            <thead>
                <tr class="text-start text-gray-400 fw-bolder fs-7 text-uppercase gs-0">
                     <th class="min-w-40px">ID</th>
                     <th class="min-w-125px">consultant</th>
                     <th class="min-w-125px">Day</th>
                     <th class="min-w-125px">Start time</th>
                     <th class="min-w-125px">End time</th>
                     <th class="min-w-60px">Action</th>
                </tr>
            </thead>
            <tbody class="text-gray-600">
			<%
				for(ConsultantAvailability availability : availabilities){
			%>
				<tr>
					<td><%= availability.getId() %></td>
					<td><%= consultant.getName() %></td>
					<td><%= availability.getDay() %></td>
					<td><%= availability.getStart_time() %></td>
					<td><%= availability.getEnd_time() %></td>
					<td>
						<div class="d-flex justify-content-center">
	                    	<a href="/online-appointments/consultant-availabilities/view?id=<%= availability.getId() %>" class="btn btn-warning">VIEW</a>
	                    	<a href="/online-appointments/consultant-availabilities/update?id=<%= availability.getId() %>" class="btn btn-success">UPDATE</a>
	                    	<a href="/online-appointments/consultant-availabilities/delete?id=<%= availability.getId() %>&consultant=<%= consultant.getId() %>" class="btn btn-danger">DELETE</a>
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