package malinda.appointments.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import malinda.appointments.models.JobSeeker;
import malinda.appointments.services.JobSeekerService;

/**
 * Servlet implementation class JobSeekerController
 */
public class JobSeekerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("get"+action);
		
		if(action.equals("/JobSeekers")) {
			getAllJobSeekers(request, response);
		}else if(action.equals("/JobSeekers/new")) {
			getCreatePage(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("post "+action);
		if(action.equals("/JobSeekers/new")) {
			addJobSeeker(request, response);
		}
	}
	
	private void getAllJobSeekers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg ="";
		JobSeekerService service = new JobSeekerService();
		try {
			List<JobSeeker> seekers = service.getAll();
			
			if (seekers.isEmpty()) {
				msg = "Empty job seekers!!!";
			}
			request.setAttribute("seekerList", seekers);
			
		} catch (Exception e) {
			msg = e.getMessage();
		}
		
		request.setAttribute("message", msg);

		request.getRequestDispatcher("/views/job_seekers/index.jsp").forward(request, response);
	}
	
	private void getJobSeekerById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		JobSeekerService service = new JobSeekerService();
		int id = Integer.parseInt(request.getParameter("job_seeker"));
	   
		JobSeeker seeker= new JobSeeker();
		try {
		    seeker = service.findById(id);
		    if(seeker.getName().isEmpty() ) {
		    	msg = "There is no any job seeker under User Id:" +id;
		    }
		} catch (ClassNotFoundException | SQLException e) {
		   msg = e.getMessage();
		}
	   
	    request.setAttribute("message", msg);
	    request.setAttribute("seeker", seeker);
		request.getRequestDispatcher("/views/job_seekers/_form.jsp").forward(request, response);
	}
	public void getCreatePage(HttpServletRequest req,HttpServletResponse res) {
		try {
			req.getRequestDispatcher("/views/job_seekers/_form.jsp").forward(req, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addJobSeeker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		JobSeekerService service = new JobSeekerService();
		JobSeeker seeker = new JobSeeker();
		seeker.setUser_id(Integer.parseInt("0"));
		seeker.setName(request.getParameter("name"));
		seeker.setAddress(request.getParameter("address"));
		seeker.setEmail(request.getParameter("email"));
		seeker.setCountry(request.getParameter("country"));
		seeker.setSeeking_position(request.getParameter("position"));
		seeker.setTelephone(request.getParameter("telephone"));
		seeker.setIs_active(Integer.parseInt(request.getParameter("status")));
		
		try {
			boolean result = service.create(seeker);
			 if(result) {
				msg = "This Job Seeker has been added successfully! Seeker Name:" + seeker.getName();
			 }
			 else {
				 msg = "Failed to add the job seeker! seeker name:" + seeker.getName();
			 }
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
		}
		
		request.setAttribute("message", msg);	
		response.sendRedirect("http://localhost:8080/online-appointments/JobSeekers");
	}
	
	private void updateJobSeeker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		int id = Integer.parseInt(request.getParameter("id"));
		JobSeekerService service = new JobSeekerService();
		JobSeeker seeker = new JobSeeker();
		seeker.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		seeker.setName(request.getParameter("name"));
		seeker.setAddress(request.getParameter("address"));
		seeker.setEmail(request.getParameter("email"));
		seeker.setCountry(request.getParameter("country"));
		seeker.setSeeking_position(request.getParameter("seeking_position"));
		seeker.setTelephone(request.getParameter("telephone"));
		seeker.setIs_active(Integer.parseInt(request.getParameter("status")));
		
		try {
			boolean result = service.update(seeker, id);
			 if(result) {
				msg = "This job seeker has been updated successfully! seeker Name:" + seeker.getName();
			 }
			 else {
				 msg = "Failed to update the job seeker! seeker name:" + seeker.getName();
			 }
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
		}
		   
		request.setAttribute("message", msg);	
		request.getRequestDispatcher("/views/job_seekers/_form.jsp").forward(request, response);
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg = "";
		JobSeekerService service = new JobSeekerService();
		int id = Integer.parseInt(request.getParameter("job_seeker"));
		try {
			service.delete(id);
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
		}
	   
		HttpSession session = request.getSession();
		session.setAttribute("deleteMessage", msg);
	   
	   response.sendRedirect("/views/job_seekers/index?action=all");
	}

}
