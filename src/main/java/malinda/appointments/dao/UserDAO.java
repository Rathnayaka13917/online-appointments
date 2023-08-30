package malinda.appointments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import malinda.appointments.models.User;

public class UserDAO {
	public boolean Login(User obj) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query="select * from users where email="+obj.getEmail();
		return true;
	}
	
	public static User getUserByUserCode(int code) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "select * from users where id =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, code);
		
		ResultSet rs = ps.executeQuery();
		User user = new User();
		System.out.println("List: "+code+" : "+rs.getFetchSize());
		if (rs.next()) {
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setType(rs.getString("type"));
			user.setIs_active(rs.getInt("is_active"));
		}
		
		ps.close();
		con.close();
		
		return user;
	}
	
	public static List<User> getAllUsers() throws ClassNotFoundException, SQLException{
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "select * from users";
		Statement st = con.createStatement();
	
		ResultSet rs = st.executeQuery(query);
		List<User> users = new ArrayList<User>();
		System.out.println("List: "+rs.getFetchSize());
		while (rs.next()) {
			User user = new User(
				rs.getInt("id"), 
				rs.getString("name"), 
				rs.getString("email"),
				rs.getString("password"),
				rs.getString("type"),
				rs.getInt("is_active")
			);
			System.out.println("Name: "+user.getName());
			users.add(user);
		}
		st.close();
		con.close();
		System.out.println("List: "+users);
		return users;
	}
	
	public static boolean createUser(User user) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "insert into users (name,email,password,type,is_active) values(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getType());
		ps.setInt(5, user.getIs_active());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	
	public static boolean updateUser(User user,int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "update users set name=?, email=?, password=?, type=?, is_active=? where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getType());
		ps.setInt(5, user.getIs_active());
		ps.setInt(6, id);

		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	
	public static boolean deleteUser(int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "delete from users where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	
	public static int getUserId(String email) throws ClassNotFoundException, SQLException {
		int u_id=0;
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		
		String query2="select id from users where email='"+email+"'";
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query2);
		while(rs.next()) {
			u_id=rs.getInt("id");
		}
		
		stmt.close();
		rs.close();
		return u_id;
	}
}
