<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="malinda.appointments.models.Consultant" %>
    <%@ page import="malinda.appointments.models.ConsultantAvailability" %>
<!DOCTYPE html>
<html>
<%@include file="/layout/head.jsp"%>
<% String[] action = request.getAttribute("javax.servlet.forward.request_uri").toString().split("/"); %>
<body>
<%@include file="/layout/nav_bar.jsp"%>
	<%
    	Consultant consultant;
    	if(request.getAttribute("consultant")==null){
    		consultant=new Consultant();
    	}else{
    		consultant=(Consultant)request.getAttribute("consultant");
    	}
    	ConsultantAvailability availability;
    	if(request.getAttribute("availability")==null){
    		availability=new ConsultantAvailability();
    	}else{
    		availability=(ConsultantAvailability)request.getAttribute("availability");
    	}
    %>
<div class="card mx-5 my-5">
    <div class="card-header border-0 pt-6 my-1">
        <div class="card-title">
            <h5><%= (action[action.length-1].equals("new"))?"Add New Availability" : (action[action.length-1].equals("update"))? "Update Availability Details" : "Availability Details" %></h5>
        </div>
        <div class="card-toolbar">
            <div class="d-flex justify-content-end">
                <a href="/online-appointments/consultant-availabilities?id=<%= consultant.getId() %>"><button type="button" class="btn btn-primary">Back</button></a>
            </div>
        </div>
    </div>
    <div class="card-body">
        <form action="<%= (action[action.length-1].equals("new"))?"new" : "update?id="+Integer.parseInt(request.getParameter("id"))+"" %>" method="post">
            <div class="form-group row">
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="consultant_name" name="consultant_name" <%if(request.getAttribute("consultant")!=null){ %> value="<%= consultant.getName() %>" <%} %> readonly>
                        <label for="floatingInput">Consultant Name</label>
                        <input type="hidden" id="consultant" name="consultant" <%if(request.getAttribute("consultant")!=null){ %> value="<%= consultant.getId() %>" <%} %>>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="date" class="form-control" id="day" name="day" placeholder="start-time" <%if(request.getAttribute("availability")!=null){ %> value="<%= availability.getDay() %>" <%} %> required>
                        <label for="floatingInput">Date</label>
                    </div>
                </div>
            </div>
            <div class="form-group row mt-3">
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="time" class="form-control" id="start_time" name="start_time" placeholder="start-time" <%if(request.getAttribute("availability")!=null){ %> value="<%= availability.getStart_time() %>" <%} %> required>
                        <label for="floatingInput">Start Time</label>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="time" class="form-control" id="end_time" name="end_time" placeholder="end-time" <%if(request.getAttribute("availability")!=null){ %> value="<%= availability.getEnd_time() %>" <%} %> required>
                        <label for="floatingInput">End Time</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6"></div>
                <% if(action[action.length-1].equals("new") || action[action.length-1].equals("update")){  %>
                <div class="col-lg-6" style="text-align-last: right;">
                    <button type="reset" class="btn btn-secondary btn-lg mr-3">
                        <i class="fas fa-redo"></i> Reset
                    </button>
                    <button type="submit" class="btn btn-primary btn-lg mr-3">
                        <i class="fas fa-save"></i> <% if(action[action.length-1].equals("new")){ %>Save <% }else{ %> Update <%} %>
                    </button>
                </div>
                <%} %>
            </div>
        </form>
    </div>
</div>
</body>
</html>