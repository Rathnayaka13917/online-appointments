package malinda.appointments.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import malinda.appointments.models.User;

class UserDAOTest {

	@Test
	@DisplayName("when login to the system")
	void testLogin() {
		String username="malinda.rathnayaka@gmail.com";
		String pwd="789";
		try {
			User user=UserDAO.Login(username, pwd);
			String expected="malinda.rathnayaka@gmail.com";
			String actual=user.getEmail();
			assertEquals(expected, actual);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("When finding a user record by Id")
	void testGetUserByUserCode() {
		int id=488;
		try {
			User user=UserDAO.getUserByUserCode(id);
			String expected="malinda.rathnayaka@gmail.com";
			String actual=user.getEmail();
			assertEquals(expected, actual);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("When selecting all the records")
	void testGetAllUsers() {
		try {
			List<User> userlist=UserDAO.getAllUsers();
			boolean expected=true;
			boolean actual=userlist.size()>0;
			assertEquals(expected, actual);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testCreateUser() {
		User user=new User(0,"Test Reception","reeption@appointment.com","123456","3",1);
		try {
			boolean actual=UserDAO.createUser(user);
			assertEquals(true, actual);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	void testUpdateUser() {
		User user=new User();
		try {
			boolean actual=UserDAO.updateUser(user,0);
			assertEquals(false, actual);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	@DisplayName("When reading user id from email")
	void testGetUserId() {
		try {
			int id=UserDAO.getUserId("malinda.rathnayaka@gmail.com");
			int expected=488;
			assertEquals(expected, id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
