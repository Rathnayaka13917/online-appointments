<%@page import="malinda.appointments.services.UserService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="malinda.appointments.models.User" %>
<%@page import="malinda.appointments.services.ConsultantAvailabilitiService"%>
<%@page import="malinda.appointments.models.ConsultantAvailability"%>
<%@page import="malinda.appointments.services.AppointmentService"%>
<%@page import="malinda.appointments.services.ConsultantService"%>
<%@page import="malinda.appointments.models.Consultant"%>
<%@page import="malinda.appointments.services.JobSeekerService"%>
<%@page import="malinda.appointments.models.JobSeeker"%>
<%@page import="malinda.appointments.models.Appointment"%>
<!DOCTYPE html>
<html>
<%@include file="/layout/head.jsp"%>
<body>
<%@include file="/layout/nav_bar.jsp"%>
<div class="container">
<br>
<br>
	<% if(session.getAttribute("user")!=null) {
   	   User user=(User)session.getAttribute("user");
   	   if(user.getType().equals("1")){
    %>
    <%
		List<Appointment> appointment_list=(List<Appointment>)request.getAttribute("appointmentlist");
    	List<Consultant> consultants = (List<Consultant>)new ConsultantService().getAll();
    	List<JobSeeker> seekers = (List<JobSeeker>)new JobSeekerService().getAll();
    	List<User> users = (List<User>)new UserService().getAll();
	%>
	<div class="row">
		<div class="col-lg-3">
			<div class="card" style="background-color: #229DC4">
			  <div class="card-body">
			    <h3 style="text-align: center;color: #ffffff">Registered Users</h3>
			    <h4 style="text-align: center;color: #ffffff"><%= users.size() %></h4>
			  </div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="card" style="background-color: #ED6410">
			  <div class="card-body">
			    <h3 style="text-align: center;color: #ffffff">Consultant Count</h3>
			    <h4 style="text-align: center;color: #ffffff"><%= consultants.size() %></h4>
			  </div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="card" style="background-color: #249710">
			  <div class="card-body">
			    <h3 style="text-align: center;color: #ffffff">Job Seeker Count</h3>
			    <h4 style="text-align: center;color: #ffffff"><%= seekers.size() %></h4>
			  </div>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="card" style="background-color: #0F1DA8">
			  <div class="card-body">
			    <h3 style="text-align: center;color: #ffffff">Appointment Count</h3>
			    <h4 style="text-align: center;color: #ffffff"><%= appointment_list.size() %></h4>
			  </div>
			</div>
		</div>
	</div>
	<div class="mt-10">
		<div class="card mx-5 my-5">
		    <div class="card-header border-0 pt-6 my-1">
		        <div class="card-title">
		            <h5>All Appointments</h5>
		        </div>
		    </div>
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
						</tr>
						<% } %>
		            </tbody>
		        </table>
		    </div>
		</div>
	</div>
	<%
  	   }else if(user.getType().equals("2")){
  	 %>
  	 <div class="mt-10">
		<div class="card mx-5 my-5">
		    <div class="card-header border-0 pt-6 my-1">
		        <div class="card-title">
		            <h5>Appointments</h5>
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
						</tr>
						<% } %>
		            </tbody>
		        </table>
		    </div>
		</div>
	</div>
  	 <%
  	   }else if(user.getType().equals("3")){
  	 %>
  	 <div class="mt-10">
		<div class="card mx-5 my-5">
		    <div class="card-header border-0 pt-6 my-1">
		        <div class="card-title">
		            <h5>Appointments</h5>
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
						</tr>
						<% } %>
		            </tbody>
		        </table>
		    </div>
		</div>
	</div>
  	 <% } 
   	} %>
</div>
</body>
</html>