package malinda.appointments.services;

import java.sql.SQLException;
import java.util.List;

import malinda.appointments.dao.ConsultantDAO;
import malinda.appointments.dao.UserDAO;
import malinda.appointments.models.Consultant;
import malinda.appointments.models.User;

public class ConsultantService implements Service<Consultant> {

	@Override
	public Consultant findById(int id) throws ClassNotFoundException, SQLException {
		return ConsultantDAO.getConsultantByID(id);
	}

	@Override
	public boolean create(Consultant obj) throws ClassNotFoundException, SQLException {
		User user = new User();
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		user.setIs_active(obj.getIs_active());
		user.setPassword("0000");
		user.setType("3");
		boolean result = UserDAO.createUser(user);
		int u_id = UserDAO.getUserId(obj.getEmail());
		obj.setUser_id(u_id);
		
		return ConsultantDAO.createConsultant(obj);
	}

	@Override
	public boolean update(Consultant obj, int id) throws ClassNotFoundException, SQLException {
		User user = new User();
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		user.setIs_active(obj.getIs_active());
		user.setPassword("0000");
		user.setType("3");
		boolean result = UserDAO.updateUser(user, obj.getUser_id());
		
		return ConsultantDAO.updateConsultant(obj, id);
	}

	@Override
	public boolean delete(int id) throws ClassNotFoundException, SQLException {
		Consultant consultant = ConsultantDAO.getConsultantByID(id);
		boolean result = UserDAO.deleteUser(consultant.getUser_id());
		return ConsultantDAO.deleteConsultant(id);
	}

	@Override
	public List<Consultant> getAll() throws ClassNotFoundException, SQLException {
		return ConsultantDAO.getAllConsultants();
	}

}
