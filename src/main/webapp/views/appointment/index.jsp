<%@page import="malinda.appointments.services.ConsultantAvailabilitiService"%>
<%@page import="malinda.appointments.models.ConsultantAvailability"%>
<%@page import="malinda.appointments.services.AppointmentService"%>
<%@page import="malinda.appointments.services.ConsultantService"%>
<%@page import="malinda.appointments.models.Consultant"%>
<%@page import="malinda.appointments.services.JobSeekerService"%>
<%@page import="malinda.appointments.models.JobSeeker"%>
<%@page import="malinda.appointments.models.Appointment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@include file="/layout/head.jsp"%>
<body>
<%@include file="/layout/nav_bar.jsp"%>

<div class="card mx-5 my-5">
    <div class="card-header border-0 pt-6 my-1">
        <div class="card-title">
            <h5>Appointments</h5>
        </div>
        <div class="card-toolbar">
            <div class="d-flex justify-content-end">
                <a href="/online-appointments/appointments/new"><button type="button" class="btn btn-primary">Add New Appointment</button></a>
            </div>
        </div>
    </div>
    <%
    	List<Appointment> appointment_list=(List<Appointment>)request.getAttribute("appointmentlist");
    %>
    <div class="card-body">
        <table class="table align-middle table-row-dashed fs-6 gy-5" >
            <thead>
                <tr class="text-start text-gray-400 fw-bolder fs-7 text-uppercase gs-0">
                     <th class="min-w-40px">ID</th>
                     <th class="min-w-175px">Consultant</th>
                     <th class="min-w-175px">Job Seeker</th>
                     <th class="min-w-125px">Date</th>
                     <th class="min-w-125px">Start Time</th>
                     <th class="min-w-125px">End Time</th>
                     <th class="min-w-60px">Action</th>
                </tr>
            </thead>
            <tbody class="text-gray-600">
            <% for(Appointment obj:appointment_list){ 
            	JobSeeker seeker=new JobSeekerService().findById(obj.getJob_seeker());
            	Consultant consultant=new ConsultantService().findById(obj.getConsultant());
            	ConsultantAvailability availability=new ConsultantAvailabilitiService().findById(obj.getAvailability());
            %>
				<tr>
					<td><%=obj.getId() %></td>
					<td><%= consultant.getName() %></td>
					<td><%= seeker.getName() %></td>
					<td><%= availability.getDay() %></td>
					<td><%= availability.getStart_time() %></td>
					<td><%= availability.getEnd_time() %></td>
					<td>
						<div class="d-flex justify-content-center">
	                    	<a href="/online-appointments/appointments/view?id=<%= obj.getId() %>" class="btn btn-warning">VIEW</a>
	                    	<a href="/online-appointments/appointments/delete?id=<%= obj.getId() %>" class="btn btn-danger">DELETE</a>
	                    </div>
					</td>
				</tr>
				<% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>