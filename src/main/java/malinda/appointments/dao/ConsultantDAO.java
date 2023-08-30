package malinda.appointments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import malinda.appointments.models.Consultant;

public class ConsultantDAO {

	public static Consultant getConsultantByID(int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		
		String query = "select * from consultants where id =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		Consultant consultant = new Consultant();
		
		if (rs.next()) {
			consultant.setId(rs.getInt("id"));
			consultant.setUser_id(rs.getInt("user_id"));
			consultant.setName(rs.getString("name"));
			consultant.setAddress(rs.getString("address"));
			consultant.setEmail(rs.getString("email"));
			consultant.setCountry(rs.getString("country"));
			consultant.setExpertise(rs.getString("expertise_area"));
			consultant.setTelephone(rs.getString("telephone"));
			consultant.setIs_active(rs.getInt("is_active"));
		}
		
		ps.close();
		con.close();
		
		return consultant;
	}
	
	public static List<Consultant> getAllConsultants() throws ClassNotFoundException, SQLException{
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "select * from consultants";
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(query);
		List<Consultant> consultants = new ArrayList<Consultant>();
		System.out.println("List: consultants"+rs.getFetchSize());
		while (rs.next()) {
			Consultant consultant = new Consultant();
			consultant.setId(rs.getInt("id"));
			consultant.setUser_id(rs.getInt("user_id"));
			consultant.setName(rs.getString("name"));
			consultant.setAddress(rs.getString("address"));
			consultant.setEmail(rs.getString("email"));
			consultant.setCountry(rs.getString("country"));
			consultant.setExpertise(rs.getString("expertise_area"));
			consultant.setTelephone(rs.getString("telephone"));
			consultant.setIs_active(rs.getInt("is_active"));
			
			consultants.add(consultant);
		}
		
		st.close();
		con.close();
		
		return consultants;
	}
	
	public static boolean createConsultant(Consultant consultant) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		
		String query = "insert into consultants (user_id,name,address,email,country,expertise_area,telephone,is_active) values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1, consultant.getUser_id());
		ps.setString(2, consultant.getName());
		ps.setString(3, consultant.getAddress());
		ps.setString(4, consultant.getEmail());
		ps.setString(5, consultant.getCountry());
		ps.setString(6, consultant.getExpertise());
		ps.setString(7, consultant.getTelephone());
		ps.setInt(8, consultant.getIs_active());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	
	public static boolean updateConsultant(Consultant consultant, int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "update consultants set user_id=?, name=?, address=?, email=?, country=?, expertise_area=?, telephone=?, is_active=? where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1, consultant.getUser_id());
		ps.setString(2, consultant.getName());
		ps.setString(3, consultant.getAddress());
		ps.setString(4, consultant.getEmail());
		ps.setString(5, consultant.getCountry());
		ps.setString(6, consultant.getExpertise());
		ps.setString(7, consultant.getTelephone());
		ps.setInt(8, consultant.getIs_active());
		ps.setInt(9, id);
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	
	public static boolean deleteConsultant(int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "delete from consultants where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
}
