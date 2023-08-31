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
import malinda.appointments.models.ConsultantAvailability;
import malinda.appointments.services.ConsultantAvailabilitiService;
import malinda.appointments.services.ConsultantService;

/**
 * Servlet implementation class ConsultantAvailabilityController
 */
public class ConsultantAvailabilityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("get"+action);
		
		if(action.equals("/consultant-availabilities")) {
			getConsultantAllAvailabilities(request, response);
		}else if(action.equals("/consultant-availabilities/new")) {
			getCreatePage(request, response);
		}else if(action.equals("/consultant-availabilities/view")) {
			getConsultantAvailabilityById(request, response);
		}else if(action.equals("/consultant-availabilities/update")) {
			getConsultantAvailabilityById(request, response);
		}else if(action.equals("/consultant-availabilities/delete")) {
			deleteAvailability(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("post "+action);
		
		if(action.equals("/consultant-availabilities/new")) {
			addConsultantAvailability(request, response);
		}else if(action.equals("/consultant-availabilities/update")) {
			updateConsultantAvailability(request, response);
		}
	}
	
	private void getConsultantAllAvailabilities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg ="";
		int id = Integer.parseInt(request.getParameter("id"));
		ConsultantAvailabilitiService service = new ConsultantAvailabilitiService();
		
		ConsultantService consultantService = new ConsultantService();
		Consultant consultant = new Consultant();
		try {
			consultant = consultantService.findById(id);
			List<ConsultantAvailability> availabilities = service.getconsultantAllAvailabality(id);
			
			if (availabilities.isEmpty()) {
				msg = "Empty consultant availability!!!";
			}
			request.setAttribute("availabilityList", availabilities);
			request.setAttribute("consultant", consultant);
			
		} catch (Exception e) {
			msg = e.getMessage();
			System.out.println("msg "+msg);
		}
		
		request.setAttribute("message", msg);

		request.getRequestDispatcher("/views/consultant_availabilities/index.jsp").forward(request, response);
	}
	
	public void getCreatePage(HttpServletRequest req,HttpServletResponse res) {
		int id = Integer.parseInt(req.getParameter("id"));
		ConsultantService consultantService = new ConsultantService();
		Consultant consultant = new Consultant();
		try {
			consultant = consultantService.findById(id);
			req.setAttribute("consultant", consultant);
			req.getRequestDispatcher("/views/consultant_availabilities/_form.jsp").forward(req, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getConsultantAvailabilityById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		ConsultantAvailabilitiService service = new ConsultantAvailabilitiService();
		ConsultantAvailability availability = new ConsultantAvailability();
		int id = Integer.parseInt(request.getParameter("id"));
		
		ConsultantService consultantService = new ConsultantService();
		Consultant consultant = new Consultant();
		
		try {
			availability = service.findById(id);
		    if(availability.getDay().isEmpty() ) {
		    	msg = "There is no any consultant availability under availability Id:" +id;
		    }
		    consultant = consultantService.findById(availability.getConsultant());
		} catch (ClassNotFoundException | SQLException e) {
		   msg = e.getMessage();
		   System.out.println("msg "+msg);
		}
	   
	    request.setAttribute("message", msg);
	    request.setAttribute("availability", availability);
	    request.setAttribute("consultant", consultant);
		request.getRequestDispatcher("/views/consultant_availabilities/_form.jsp").forward(request, response);
	}
	
	private void addConsultantAvailability(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		ConsultantAvailabilitiService service = new ConsultantAvailabilitiService();
		ConsultantAvailability availability = new ConsultantAvailability();
		availability.setDay(request.getParameter("day"));;
		availability.setStart_time(request.getParameter("start_time"));;
		availability.setEnd_time(request.getParameter("end_time"));
		availability.setConsultant(Integer.parseInt(request.getParameter("consultant")));
		
		try {
			boolean result = service.create(availability);
			 if(result) {
				msg = "This  availability has been added successfully! availibity date :" + availability.getDay();
			 }
			 else {
				 msg = "Failed to add the availability! availability date:" + availability.getDay();
			 }
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
			System.out.println("message : "+msg);
		}
		
		request.setAttribute("message", msg);	
		response.sendRedirect("http://localhost:8080/online-appointments/consultant-availabilities?id="+availability.getConsultant()+"");
	}
	
	private void updateConsultantAvailability(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		int id = Integer.parseInt(request.getParameter("id"));
		ConsultantAvailabilitiService service = new ConsultantAvailabilitiService();
		ConsultantAvailability availability = new ConsultantAvailability();
		availability.setDay(request.getParameter("day"));;
		availability.setStart_time(request.getParameter("start_time"));
		availability.setEnd_time(request.getParameter("end_time"));
		availability.setConsultant(Integer.parseInt(request.getParameter("consultant")));
		
		try {
			boolean result = service.update(availability, id);
			 if(result) {
				msg = "This availability has been updated successfully! availibity date :" + availability.getDay();
			 }
			 else {
				 msg = "Failed to update the availability! availability date:" + availability.getDay();
			 }
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
		}
		System.out.println("msg : "+msg);
		   
		request.setAttribute("message", msg);	
		response.sendRedirect("http://localhost:8080/online-appointments/consultant-availabilities?id="+availability.getConsultant()+"");
	}
	
	private void deleteAvailability(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg = "";
		ConsultantAvailabilitiService service = new ConsultantAvailabilitiService();
		int id = Integer.parseInt(request.getParameter("id"));
		int consultant = Integer.parseInt(request.getParameter("consultant"));
		try {
			service.delete(id);
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
		}
	   
		HttpSession session = request.getSession();
		session.setAttribute("deleteMessage", msg);
	   
	   response.sendRedirect("http://localhost:8080/online-appointments/consultant-availabilities?id="+consultant+"");
	}

}
