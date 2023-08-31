package malinda.appointments.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import malinda.appointments.models.Appointment;
import malinda.appointments.models.Consultant;
import malinda.appointments.models.JobSeeker;
import malinda.appointments.models.User;
import malinda.appointments.services.AppointmentService;
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

	private void deleteAppointment(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
			request.getRequestDispatcher("/views/appointment/_form2.jsp").forward(request, response);
		}catch(Exception ex) {
			
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
