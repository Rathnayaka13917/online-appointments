package malinda.appointments.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbConnector {
	public Connection connectDb() throws ClassNotFoundException, SQLException;
}
