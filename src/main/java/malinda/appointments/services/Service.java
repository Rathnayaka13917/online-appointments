package malinda.appointments.services;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
	public T findById(int id) throws ClassNotFoundException, SQLException;
	public boolean create(T obj) throws ClassNotFoundException, SQLException;
	public boolean update(T obj) throws ClassNotFoundException, SQLException;
	public boolean delete(int id) throws ClassNotFoundException, SQLException;
	public List<T> getAll() throws ClassNotFoundException, SQLException;
}
