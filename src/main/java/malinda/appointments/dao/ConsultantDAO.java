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
			consultant.setExpertise(rs.getString("expertise"));
			consultant.setTelephone(rs.getString("telephone"));
			consultant.setIs_active(rs.getInt("is_active"));
		}
		
		ps.close();
		con.close();
		
		return consultant;
	}
	
	public static List<Consultant> getAllConsultants(int id) throws ClassNotFoundException, SQLException{
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
			consultant.setExpertise(rs.getString("expertise"));
			consultant.setTelephone(rs.getString("telephone"));
			consultant.setIs_active(rs.getInt("is_active"));
			
			consultants.add(consultant);
		}
		
		st.close();
		con.close();
		
		return consultants;
	}
	
//	public static boolean createConsultant(Consultant consultant) {
//		int u_id=0;
//		DbConnection connector=new DbConnection();
//		Connection con=connector.connectDb();
//		String query1 = "insert into users (name,email,password,type,is_active) values(?,?,?,?,?)";
//		PreparedStatement ps1 = con.prepareStatement(query1);
//		
////		ps1.setString(2, jobSeeker.getEmail());
////		ps1.setString(3, "0000");
////		ps1.setString(4, "2");
////		ps1.setInt(5, jobSeeker.getIs_active());
////		boolean resups1.setString(1, jobSeeker.getName());
////		lt1 = ps1.executeUpdate() > 0;
////		ps1.close();
//		
////		String q2 = "select id from users where email=?";
////		PreparedStatement pss = con.prepareStatement(query1);
////		pss.setString(1, jobSeeker.getEmail());
////		ResultSet res = pss.executeQuery();
//		
////		String query = "insert into job_seekers (user_id,name,address,email,country,seeking_position,telephone,is_active) values(?,?,?,?,?,?,?,?)";
////		PreparedStatement ps = con.prepareStatement(query);
//		
////		ps.setInt(1, u_id);
////		ps.setString(2, jobSeeker.getName());
////		ps.setString(3, jobSeeker.getAddress());
////		ps.setString(4, jobSeeker.getEmail());
////		ps.setString(5, jobSeeker.getCountry());
////		ps.setString(6, jobSeeker.getSeeking_position());
////		ps.setString(7, jobSeeker.getTelephone());
////		ps.setInt(8, jobSeeker.getIs_active());
//		
////		boolean result = ps.executeUpdate() > 0;
////		ps.close();
////		con.close();
//		
//		return result;
//	}
}
