package malinda.appointments.controllers;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}
