package malinda.appointments.services;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
	public T findById(int id) throws ClassNotFoundException, SQLException;
	public boolean create(T obj);
	public boolean update(T obj);
	public boolean delete(int id);
	public List<T> getAll();
}
