package malinda.appointments.services;

import java.sql.SQLException;
import java.util.List;

import malinda.appointments.dao.AppointmentDAO;
import malinda.appointments.models.Appointment;

public class AppointmentService implements Service<Appointment> {

	@Override
	public Appointment findById(int id) throws ClassNotFoundException, SQLException {
		return AppointmentDAO.getAppointmentById(id);
	}

	@Override
	public boolean create(Appointment obj) throws ClassNotFoundException, SQLException {
		return AppointmentDAO.insert(obj);
	}

	@Override
	public boolean update(Appointment obj, int id) throws ClassNotFoundException, SQLException {
		return AppointmentDAO.update(obj,id);
	}

	@Override
	public boolean delete(int id) throws ClassNotFoundException, SQLException {
		return AppointmentDAO.delete(id);
	}

	@Override
	public List<Appointment> getAll() throws ClassNotFoundException, SQLException {
		return AppointmentDAO.getAllAppointments();
	}
	
	public List<Appointment> getAppointmentByConsultant(int id) throws ClassNotFoundException, SQLException{
		return AppointmentDAO.getAppointmentsByConsultant(id);
	}
	
	public List<Appointment> getAppointmentByJobSeeker(int id) throws ClassNotFoundException, SQLException{
		return AppointmentDAO.getAppointmentsBySeeker(id);
	}

}
