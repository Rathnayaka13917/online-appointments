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
	public  boolean create(User obj) throws ClassNotFoundException, SQLException {
		return UserDAO.createUser(obj);
	}

	@Override
	public boolean update(User obj,int id) throws ClassNotFoundException, SQLException {
		return UserDAO.updateUser(obj, id);
	}

	@Override
	public boolean delete(int id) throws ClassNotFoundException, SQLException {
		return UserDAO.deleteUser(id);
	}

	@Override
	public List<User> getAll() throws ClassNotFoundException, SQLException {
		return UserDAO.getAllUsers();
	}

}
