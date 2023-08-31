<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="malinda.appointments.models.User" %>
<!DOCTYPE html>
<html>
<%@include file="/layout/head.jsp"%>
<% String[] action = request.getAttribute("javax.servlet.forward.request_uri").toString().split("/"); %>
<body>
<%@include file="/layout/nav_bar.jsp"%>
<div class="card mx-5 my-5">
    <div class="card-header border-0 pt-6 my-1">
        <div class="card-title">
            <h5><%= (action[action.length-1].equals("new"))?"Add New User" : "User Details" %></h5>
        </div>
        <div class="card-toolbar">
            <div class="d-flex justify-content-end">
                <a href="/online-appointments/users"><button type="button" class="btn btn-primary">Back</button></a>
            </div>
        </div>
    </div>
    <%
    	User user;
    	if(request.getAttribute("user")==null){
    		user=new User();
    	}else{
    		user=(User)request.getAttribute("user");
    	}
    %>
    <div class="card-body">
        <form action="<%= (action[action.length-1].equals("new"))?"new" : "update?id="+Integer.parseInt(request.getParameter("id"))+"" %>" method="post">
            <div class="form-group row">
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control required" id="name" name="name" placeholder="eg:jone" <%if(request.getAttribute("user")!=null){ %> value="<%= user.getName() %>" <%} %> required>
                        <label for="floatingInput">Name</label>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control required" id="email" name="email" placeholder="jone@example.com" <%if(request.getAttribute("user")!=null){ %> value="<%= user.getEmail() %>" <%} %> required>
                        <label for="floatingInput">Email Address</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control required" id="password" name="password" placeholder="password" <%if(request.getAttribute("user")!=null){ %> value="<%= user.getPassword() %>" <%} %> required>
                        <label for="floatingInput">Password</label>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control required" id="con_password" name="con_password" placeholder="password" <%if(request.getAttribute("user")!=null){ %> value="<%= user.getPassword() %>" <%} %> required>
                        <label for="floatingInput">Confirm Password</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6">
                    <select class="form-select" aria-label="Default select example" id="type" name="type">
                        <option>User Type</option>
                        <option <%= (user.getType().equals("1"))?"selected":"" %> value="1">Admin</option>
                        <option <%= (user.getType().equals("2"))?"selected":"" %> value="2">Job seeker</option>
                        <option <%= (user.getType().equals("3"))?"selected":"" %> value="3">Consultant</option>
                    </select>
                </div>
                <div class="col-lg-6">
                    <label for="status" class="required">Status</label>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" value="1" name="status" id="active" <%= (user.getIs_active()==1)?"checked":"" %>>
                        <label class="form-check-label" for="flexRadioDefault2">
                            Active
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" value="0" name="status" id="inactive" <%= (user.getIs_active()==0)?"checked":"" %> >
                        <label class="form-check-label" for="flexRadioDefault1">
                          InActive
                        </label>
                    </div>
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