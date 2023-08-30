<%@page import="java.util.List,java.util.LinkedList"%>
<%@page import="malinda.appointments.models.JobSeeker"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@include file="/layout/head.jsp"%>
<body>
<%@include file="/layout/nav_bar.jsp"%>
<%
	List<JobSeeker> seekersList=new LinkedList<>();
if(request.getAttribute("seekerList")!=null){
	seekersList =(List<JobSeeker>)request.getAttribute("seekerList");
}
%>
<div class="card mx-5 my-5">
    <div class="card-header border-0 pt-6 my-1">
        <div class="card-title">
            <h5>Job Seekers</h5>
        </div>
        <div class="card-toolbar">
            <div class="d-flex justify-content-end">
                <a href="/online-appointments/JobSeekers/new"><button type="button" class="btn btn-primary">Add New Job Seeker</button></a>
            </div>
        </div>
    </div>
    <div class="card-body">
        <table class="table align-middle table-row-dashed fs-6 gy-5" data-order="[[1,'asc']]" data-page-length="25">
            <thead>
                <tr class="text-start text-gray-400 fw-bolder fs-7 text-uppercase gs-0">
                     <th class="min-w-40px">ID</th>
                     <th class="min-w-125px">name</th>
                     <th class="min-w-125px">email</th>
                     <th class="min-w-125px">country</th>
                     <th class="min-w-125px">telephone</th>
                     <th class="min-w-60px">Action</th>
                </tr>
            </thead>
            <tbody class="text-gray-600">
				<%
					for(JobSeeker seeker : seekersList){
				%>
				<tr>
                    <td><%= seeker.getId() %></td>
                    <td><%= seeker.getName() %></td>
                    <td><%= seeker.getEmail() %></td>
                    <td><%= seeker.getCountry() %></td>
                    <td><%= seeker.getTelephone() %></td>
                    <td>
                    	<div class="d-flex justify-content-center">
	                    	<a href="/online-appointments/JobSeekers/view?id=<%= seeker.getId() %>" class="btn btn-warning">VIEW</a>
	                    	<a href="/online-appointments/JobSeekers/update?id=<%= seeker.getId() %>" class="btn btn-success">UPDATE</a>
	                    	<a href="/online-appointments/JobSeekers/delete?id=<%= seeker.getId() %>" class="btn btn-danger">DELETE</a>
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