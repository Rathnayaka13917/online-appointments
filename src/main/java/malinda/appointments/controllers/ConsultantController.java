package malinda.appointments.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import malinda.appointments.models.Consultant;
import malinda.appointments.models.JobSeeker;
import malinda.appointments.services.ConsultantService;
import malinda.appointments.services.JobSeekerService;

/**
 * Servlet implementation class ConsultantController
 */
public class ConsultantController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("get"+action);
		
		if(action.equals("/consultants")) {
			getAllConsultant(request, response);
		}else if(action.equals("/consultants/new")) {
			getCreatePage(request, response);
		}else if(action.equals("/consultants/view")) {
			getConsultantById(request, response);
		}else if(action.equals("/consultants/update")) {
			getConsultantById(request, response);
		}else if(action.equals("/consultants/delete")) {
			deleteConsultant(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("post "+action);
		
		if(action.equals("/consultants/new")) {
			addConsultant(request, response);
		}else if(action.equals("/consultants/update")) {
			updateConsultant(request, response);
		}
	}
	
	private void getAllConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg ="";
		ConsultantService service = new ConsultantService();
		try {
			List<Consultant> consultant = service.getAll();
			
			if (consultant.isEmpty()) {
				msg = "Empty consultant!!!";
			}
			request.setAttribute("consultantList", consultant);
			
		} catch (Exception e) {
			msg = e.getMessage();
			System.out.println("msg "+msg);
		}
		
		request.setAttribute("message", msg);

		request.getRequestDispatcher("/views/consultants/index.jsp").forward(request, response);
	}
	
	public void getCreatePage(HttpServletRequest req,HttpServletResponse res) {
		try {
			req.getRequestDispatcher("/views/consultants/_form.jsp").forward(req, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		ConsultantService service = new ConsultantService();
		Consultant consultant = new Consultant();
		consultant.setName(request.getParameter("name"));
		consultant.setAddress(request.getParameter("address"));
		consultant.setEmail(request.getParameter("email"));
		consultant.setCountry(request.getParameter("country"));
		consultant.setExpertise(request.getParameter("axp-area"));
		consultant.setTelephone(request.getParameter("telephone"));
		consultant.setIs_active(Integer.parseInt(request.getParameter("status")));
		
		try {
			boolean result = service.create(consultant);
			 if(result) {
				msg = "This  consultant has been added successfully! consultant Name:" + consultant.getName();
			 }
			 else {
				 msg = "Failed to add the consultant! consultant name:" + consultant.getName();
			 }
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
			System.out.println("message : "+msg);
		}
		
		request.setAttribute("message", msg);	
		response.sendRedirect("http://localhost:8080/online-appointments/consultants");
	}
	
	private void getConsultantById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		ConsultantService service = new ConsultantService();
		Consultant consultant = new Consultant();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			consultant = service.findById(id);
		    if(consultant.getName().isEmpty() ) {
		    	msg = "There is no any consultant under consultant Id:" +id;
		    }
		} catch (ClassNotFoundException | SQLException e) {
		   msg = e.getMessage();
		   System.out.println("msg "+msg);
		}
	   
	    request.setAttribute("message", msg);
	    request.setAttribute("consultant", consultant);
		request.getRequestDispatcher("/views/consultants/_form.jsp").forward(request, response);
	}
	
	private void updateConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		int id = Integer.parseInt(request.getParameter("id"));
		ConsultantService service = new ConsultantService();
		Consultant consultant = new Consultant();
		consultant.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		consultant.setName(request.getParameter("name"));
		consultant.setAddress(request.getParameter("address"));
		consultant.setEmail(request.getParameter("email"));
		consultant.setCountry(request.getParameter("country"));
		consultant.setExpertise(request.getParameter("axp-area"));
		consultant.setTelephone(request.getParameter("telephone"));
		consultant.setIs_active(Integer.parseInt(request.getParameter("status")));
		
		try {
			boolean result = service.update(consultant, id);
			 if(result) {
				msg = "This consultant has been updated successfully! consultant Name:" + consultant.getName();
			 }
			 else {
				 msg = "Failed to update the consultant! consultant name:" + consultant.getName();
			 }
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
		}
		System.out.println("msg : "+msg);
		   
		request.setAttribute("message", msg);	
		response.sendRedirect("http://localhost:8080/online-appointments/consultants");
	}
	
	private void deleteConsultant(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg = "";
		ConsultantService service = new ConsultantService();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			service.delete(id);
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
		}
	   
		HttpSession session = request.getSession();
		session.setAttribute("deleteMessage", msg);
	   
	   response.sendRedirect("http://localhost:8080/online-appointments/consultants");
	}

}
