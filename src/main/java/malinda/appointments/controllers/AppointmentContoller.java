package malinda.appointments.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import malinda.appointments.models.Appointment;
import malinda.appointments.models.Consultant;
import malinda.appointments.models.ConsultantAvailability;
import malinda.appointments.models.JobSeeker;
import malinda.appointments.models.User;
import malinda.appointments.services.AppointmentService;
import malinda.appointments.services.ConsultantAvailabilitiService;
import malinda.appointments.services.ConsultantService;
import malinda.appointments.services.JobSeekerService;

/**
 * Servlet implementation class AppointmentContoller
 */
public class AppointmentContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getRequestURI().substring(request.getContextPath().length());
		if(action.equals("/appointments")) {
			getAllAppointments(request, response);
		}else if(action.equals("/appointments/new")) {
			getCreatePage(request, response);
		}else if(action.equals("/appointments/view")) {
			getAppointmenttById(request, response);
		}else if(action.equals("/appointments/update")) {
			getAppointmenttById(request, response);
		}else if(action.equals("/appointments/delete")) {
			deleteAppointment(request, response);
		}
	}

	private void deleteAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg = "";
		AppointmentService service = new AppointmentService();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			service.delete(id);
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
		}
	   
		HttpSession session = request.getSession();
		session.setAttribute("deleteMessage", msg);
	   
	   response.sendRedirect("http://localhost:8080/online-appointments/appointments");
		
	}

	private void getAppointmenttById(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("consultants", new ConsultantService().getAll());
			request.setAttribute("job_seekers", new JobSeekerService().getAll());
			Appointment obj=new AppointmentService().findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("appointment", obj);
			Consultant consultant=new ConsultantService().findById(obj.getConsultant());
			request.setAttribute("consultant", consultant);
			JobSeeker seeker=new JobSeekerService().findById(obj.getId());
			request.setAttribute("seeker", seeker);
			List<ConsultantAvailability> availability_list=new ConsultantAvailabilitiService().getconsultantAllAvailabality(consultant.getId());
			request.setAttribute("time_slots", availability_list);
			request.getRequestDispatcher("/views/appointment/_form2.jsp").forward(request, response);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	private void getCreatePage(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("consultants", new ConsultantService().getAll());
			request.setAttribute("job_seekers", new JobSeekerService().getAll());
			request.getRequestDispatcher("/views/appointment/_form.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private void getAllAppointments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg ="";
		User user=(User)request.getSession().getAttribute("user");
		AppointmentService service=new AppointmentService();
		try {
			List<Appointment> appointments = service.getAll();
			if(user.getType().equals("3")) {
				Consultant consultant=new ConsultantService().findByUserId(user.getId());
				appointments=service.getAppointmentByConsultant(consultant.getId());
			}else if(user.getType().equals("2")) {
				JobSeeker seeker=new JobSeekerService().findByUserId(user.getId());
				appointments=service.getAppointmentByJobSeeker(seeker.getId());
			}
			if (appointments.isEmpty()) {
				msg = "Empty consultant!!!";
			}
			request.setAttribute("appointmentlist", appointments);
			
		} catch (Exception e) {
			msg = e.getMessage();
			System.out.println("msg "+msg);
		}
		
		request.setAttribute("message", msg);

		request.getRequestDispatcher("/views/appointment/index.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String msg="";
		String action = request.getRequestURI().substring(request.getContextPath().length());
		if(action.equals("/appointments/new")) {
			String step=request.getParameter("step");
			int seeker_id=Integer.parseInt(request.getParameter("seeker"));
			int consultant_id=Integer.parseInt(request.getParameter("consultant"));
			request.setAttribute("consultants", new ConsultantService().getAll());
			request.setAttribute("job_seekers", new JobSeekerService().getAll());
			if(step.equals("1")) {
				JobSeeker seeker=new JobSeekerService().findById(seeker_id);
				Consultant consultant=new ConsultantService().findById(consultant_id);
				request.setAttribute("consultant", consultant);
				request.setAttribute("seeker", seeker);
				List<ConsultantAvailability> availability_list=new ConsultantAvailabilitiService().getconsultantAvailableSlots(consultant_id);
				request.setAttribute("time_slots", availability_list);
				request.getRequestDispatcher("/views/appointment/_form2.jsp").forward(request, response);
			}else if(step.equals("2")) {
				int availability_id=Integer.parseInt(request.getParameter("availability"));
				String remarks=request.getParameter("remarks");
				Appointment obj=new Appointment();
				obj.setConsultant(consultant_id);
				obj.setJob_seeker(seeker_id);
				obj.setAvailability(availability_id);
				obj.setRemarks(remarks);
				AppointmentService service=new AppointmentService();
				boolean result = service.create(obj);
				 if(result) {
					ConsultantAvailability availability=new ConsultantAvailabilitiService().findById(availability_id);
					availability.setReserved(1);
					new ConsultantAvailabilitiService().update(availability, availability_id);
					msg = "This  Appointment has been added successfully!";
				 }
				 else {
					 msg = "Failed to add the consultant!";
				 }
				 response.sendRedirect("http://localhost:8080/online-appointments/appointments");
			}
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	

}
