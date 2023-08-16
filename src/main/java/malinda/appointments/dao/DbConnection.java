package malinda.appointments.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection implements DbConnector {

	@Override
	public Connection connectDb() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/online_appointment";
		String userName ="root";
		String password ="admin";
		Connection con=DriverManager.getConnection(url, userName, password);
		return con;
	}

}
