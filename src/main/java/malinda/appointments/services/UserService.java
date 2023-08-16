package malinda.appointments.services;

import java.sql.SQLException;
import java.util.List;

import malinda.appointments.dao.UserDAO;
import malinda.appointments.models.User;

public class UserService implements Service<User> {

	@Override
	public User findById(int id) throws ClassNotFoundException, SQLException {
		return UserDAO.getUserByUserCode(id);
	}

	@Override
	public  boolean create(User obj) {
		return UserDAO.createUser(obj);
	}

	@Override
	public boolean update(User obj) {
		return UserDAO.updateUser(obj);
	}

	@Override
	public boolean delete(int id) {
		return UserDAO.deleteUser(id);
	}

	@Override
	public List<User> getAll() {
		return UserDAO.getAllUsers();
	}

}
