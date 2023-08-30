<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="malinda.appointments.models.Consultant" %>
<!DOCTYPE html>
<html>
<%@include file="/layout/head.jsp"%>
<% String[] action = request.getAttribute("javax.servlet.forward.request_uri").toString().split("/"); %>
<body>
<%@include file="/layout/nav_bar.jsp"%>
<div class="card mx-5 my-5">
    <div class="card-header border-0 pt-6 my-1">
        <div class="card-title">
            <h5><%= (action[action.length-1].equals("new"))?"Add New Consultant" : (action[action.length-1].equals("update"))? "Update Consultant Details" : "Consultant Details" %></h5>
        </div>
        <div class="card-toolbar">
            <div class="d-flex justify-content-end">
                <a href="/online-appointments/consultants"><button type="button" class="btn btn-primary">Back</button></a>
            </div>
        </div>
    </div>
    <%
    	Consultant consultant;
    	if(request.getAttribute("consultant")==null){
    		consultant = new Consultant();
    	}else{
    		consultant = (Consultant)request.getAttribute("consultant");
    	}
    %>
    <div class="card-body">
        <form action="<%= (action[action.length-1].equals("new"))?"new" : "update?id="+Integer.parseInt(request.getParameter("id"))+"" %>" method="post">
            <div class="form-group row">
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="name" name="name" placeholder="eg:jone" <%if(request.getAttribute("consultant")!=null){ %> value="<%= consultant.getName() %>" <%} %> required>
                        <label for="floatingInput">Name</label>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" id="email" name="email" placeholder="jone@example.com" <%if(request.getAttribute("consultant")!=null){ %> value="<%= consultant.getEmail() %>" <%} %> required>
                        <label for="floatingInput">Email Address</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-12">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="address" name="address" placeholder="address" <%if(request.getAttribute("consultant")!=null){ %> value="<%= consultant.getAddress() %>" <%} %> >
                        <label for="floatingInput">Address</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="country" name="country" placeholder="country" <%if(request.getAttribute("consultant")!=null){ %> value="<%= consultant.getCountry() %>" <%} %> required>
                        <label for="floatingInput">Specialized Country</label>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="tel" class="form-control" id="telephone" name="telephone" placeholder="telephone" <%if(request.getAttribute("consultant")!=null){ %> value="<%= consultant.getTelephone() %>" <%} %>>
                        <label for="floatingInput">Telephone</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-12">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="axp-area" name="axp-area" placeholder="axp-area" <%if(request.getAttribute("consultant")!=null){ %> value="<%= consultant.getExpertise() %>" <%} %>>
                        <label for="floatingInput">Expertise Area</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6">
                    <label for="status" class="required">Status</label>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="status" id="active" value="1" <%= (consultant.getIs_active()==1)?"checked":"" %>>
                        <label class="form-check-label" for="flexRadioDefault2">
                            Active
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="status" id="inactive" value="0" <%= (consultant.getIs_active()==0)?"checked":"" %>>
                        <label class="form-check-label" for="flexRadioDefault1">
                          InActive
                        </label>
                    </div>
                </div>
            </div>
            <input type="hidden" name="user_id" id="user_id" <%if(request.getAttribute("consultant")!=null){ %> value="<%= consultant.getUser_id() %>" <%} %>>
            <% if(action[action.length-1].equals("new") || action[action.length-1].equals("update")){  %>
            <div class="form-group row">
                <div class="col-lg-6"></div>
                <div class="col-lg-6" style="text-align-last: right;">
                    <button type="reset" class="btn btn-secondary btn-lg mr-3">
                        <i class="fas fa-redo"></i> Reset
                    </button>
                    <button type="submit" class="btn btn-primary btn-lg mr-3">
                        <i class="fas fa-save"></i> <% if(action[action.length-1].equals("new")){ %>Save <% }else{ %> Update <%} %>
                    </button>
                </div>
            </div>
            <%} %>
        </form>
    </div>
</div>
</body>
</html>