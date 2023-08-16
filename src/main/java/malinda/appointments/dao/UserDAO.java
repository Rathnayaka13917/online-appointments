package malinda.appointments.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import malinda.appointments.models.User;

public class UserDAO {
	public boolean Login(User obj) {
		try {
			DbConnection connector=new DbConnection();
			Connection con=connector.connectDb();
			String query="select * from users where email='";
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	public static User getUserByUserCode(int code) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "select * from users where id ="+code;
		
		return new User();
	}
	
	public static List<User> getAllUsers(){
		return new ArrayList<User>();
	}
	
	public static boolean createUser(User user) {
		return false;
		
	}
	
	public static boolean updateUser(User user) {
		return false;
	}
	
	public static boolean deleteUser(int user) {
		return false;
	}
}
