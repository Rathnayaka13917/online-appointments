<%@ page import="malinda.appointments.models.User" %>
<nav class="navbar navbar-expand " style="background-color: indigo" aria-label="Second navbar example">
	 <div class="container-fluid">
	   <a class="navbar-brand text-light" href="/online-appointments">Online Appointments</a>
	   <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
	     <span class="navbar-toggler-icon"></span>
	   </button>
	   <div class="collapse navbar-collapse" id="navbarsExample02">
	     <ul class="navbar-nav me-auto">
	       <li class="nav-item">
	         <a class="nav-link text-light" aria-current="page" href="/online-appointments">Home</a>
	       </li>
	      
	       <% if(session.getAttribute("user")!=null) {
	    	   User user=(User)session.getAttribute("user");
	    	   if(user.getType().equals("1")){
	       %>
			  <li class="nav-item">
				<a class="nav-link text-light" href="/online-appointments/consultants">Consultants</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link text-light" href="/online-appointments/job-seekers">Job Seekers</a>
			  </li>
			   <li class="nav-item">
		         <a class="nav-link text-light" href="/online-appointments/users">Users</a>
		       </li>
		    <%
	    	   }else if(user.getType().equals("3")){
		    %>
			  <li class="nav-item">
				<a class="nav-link text-light" href="/online-appointments/consultant-availabilities?id=<%= user.getId() %>">Availability</a>
			  </li>
			   <li class="nav-item">
				<a class="nav-link text-light" href="/online-appointments/consultants/update?user_id=<%= user.getId() %>">Consultants</a>
			  </li>
		  <% } 
	    	} %>
		  
		  <li class="nav-item">
			<a class="nav-link text-light" href="/online-appointments/appointments">Appointments</a>
		  </li>
	     </ul>
	     <div>
			<ul class="navbar-nav me-auto">
				<% if(session.getAttribute("user")==null) {%>
				<li class="nav-item">
					<a class="nav-link text-light" href="/online-appointments/views/login.jsp">Log In</a>
				</li>
				<% }else{
					User user=(User)session.getAttribute("user");
				%>
				<li class="nav-item">
					<a class="nav-link text-light" href="/online-appointments/users/update?id=<%=user.getId() %>"><%=user.getName() %></a>
				</li>
				<li class="nav-item">
					<a class="nav-link text-light" href="/online-appointments/logout">Log Out</a>
				</li>
				<% } %>
			</ul>
		 </div>
	   </div>
	 </div>
</nav>