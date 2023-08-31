package malinda.appointments.services;

import java.sql.SQLException;
import java.util.List;

import malinda.appointments.dao.ConsultantAvailabilityDAO;
import malinda.appointments.models.ConsultantAvailability;

public class ConsultantAvailabilitiService implements Service<ConsultantAvailability> {

	@Override
	public ConsultantAvailability findById(int id) throws ClassNotFoundException, SQLException {
		return ConsultantAvailabilityDAO.getConsultantAvailabilityById(id);
	}

	@Override
	public boolean create(ConsultantAvailability obj) throws ClassNotFoundException, SQLException {
		return ConsultantAvailabilityDAO.createConsultantAvailability(obj);
	}

	@Override
	public boolean update(ConsultantAvailability obj, int id) throws ClassNotFoundException, SQLException {
		return ConsultantAvailabilityDAO.updateConsultantAvailability(obj, id);
	}

	@Override
	public boolean delete(int id) throws ClassNotFoundException, SQLException {
		return ConsultantAvailabilityDAO.deleteAvailability(id);
	}

	public List<ConsultantAvailability> getconsultantAllAvailabality(int consultant) throws ClassNotFoundException, SQLException {
		return ConsultantAvailabilityDAO.getConsultantAllAvalability(consultant);
	}

	@Override
	public List<ConsultantAvailability> getAll() throws ClassNotFoundException, SQLException {
		return null;
	}

}
