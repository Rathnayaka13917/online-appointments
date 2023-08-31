<%@page import="malinda.appointments.models.JobSeeker"%>
<%@page import="malinda.appointments.models.Consultant"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@include file="/layout/head.jsp"%>
<% String[] action = request.getAttribute("javax.servlet.forward.request_uri").toString().split("/"); %>
<body>
<%@include file="/layout/nav_bar.jsp"%>
<div class="card mx-5 my-5">
    <div class="card-header border-0 pt-6 my-1">
        <div class="card-title">
            <h5><%= (action[action.length-1].equals("new"))?"Add New Appointment" : "Appointment Details" %></h5>
        </div>
        <div class="card-toolbar">
            <div class="d-flex justify-content-end">
                <a href="/online-appointments/users"><button type="button" class="btn btn-primary">Back</button></a>
            </div>
        </div>
    </div>
    <%
    	List<Consultant> consultant_list=(List<Consultant>)request.getAttribute("consultants");
    	List<JobSeeker> jobseeker_list=(List<JobSeeker>)request.getAttribute("job_seekers");
    %>
    <div class="card-body">
        <form action="<%= (action[action.length-1].equals("new"))?"new?step=1" : "update?id="+Integer.parseInt(request.getParameter("id"))+"" %>" method="post">
            <div class="form-group row mb-3">
	            <div class="col-lg-6">
	            <label for="seeker">Job Seeker</label>
	                    <select class="form-select" aria-label="Default select example" id="seeker" name="seeker" required>
	                        <option value="">Select Job Seeker</option>
	                        <% for(JobSeeker obj:jobseeker_list){ %>
	                        	<option value="<%= obj.getId() %>"><%= obj.getName() %>(<%=obj.getCountry()+" "+obj.getSeeking_position() %>)</option>
	                        <% } %>
	                    </select>
	                </div>
                <div class="col-lg-6">
                <label for="consultant">Consultant</label>
                    <select class="form-select" aria-label="Default select example" id="consultant" name="consultant" required>
                        <option value="">Select Consultant</option>
                        <% for(Consultant obj:consultant_list){ %>
                        	<option value="<%= obj.getId() %>"><%= obj.getName() %>(<%=obj.getCountry()+" "+obj.getExpertise() %>)</option>
                        <% } %>
                    </select>
                </div>
                
            </div>
            
            <% if(action[action.length-1].equals("new") || action[action.length-1].equals("update")){  %>
            <div class="form-group row">
                <div class="col-lg-6"></div>
                <div class="col-lg-6" style="text-align-last: right;">
                    <button type="reset" class="btn btn-secondary btn-lg mr-3">
                        <i class="fas fa-redo"></i> Reset
                    </button>
                    <button type="submit" class="btn btn-primary btn-lg mr-3">
                        <i class="fas fa-save"></i> Save
                    </button>
                </div>
            </div>
            <% } %>
        </form>
    </div>
</div>
</body>
</html>