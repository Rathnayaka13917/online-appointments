package malinda.appointments.dao;

import java.awt.image.RescaleOp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import malinda.appointments.models.JobSeeker;
import malinda.appointments.models.User;

public class JobSeekerDAO {

	public static JobSeeker getJobSeekerById(int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "select * from job_seekers where id =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		JobSeeker seeker = new JobSeeker();
		
		if (rs.next()) {
			seeker.setId(rs.getInt("id"));
			seeker.setUser_id(rs.getInt("user_id"));
			seeker.setName(rs.getString("name"));
			seeker.setAddress(rs.getString("address"));
			seeker.setEmail(rs.getString("email"));
			seeker.setCountry(rs.getString("country"));
			seeker.setSeeking_position(rs.getString("seeking_position"));
			seeker.setTelephone(rs.getString("telephone"));
			seeker.setIs_active(rs.getInt("is_active"));
		}
		
		ps.close();
		con.close();
		
		return seeker;
	}
	
	public static List<JobSeeker> getAllJobSeekers() throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "select * from job_seekers";
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(query);
		List<JobSeeker> seekers = new ArrayList<JobSeeker>();
		System.out.println("List: seeker"+rs.getFetchSize());
		while (rs.next()) {
			JobSeeker seeker = new JobSeeker();
			seeker.setId(rs.getInt("id"));
			seeker.setUser_id(rs.getInt("user_id"));
			seeker.setName(rs.getString("name"));
			seeker.setAddress(rs.getString("address"));
			seeker.setEmail(rs.getString("email"));
			seeker.setCountry(rs.getString("country"));
			seeker.setSeeking_position(rs.getString("seeking_position"));
			seeker.setTelephone(rs.getString("telephone"));
			seeker.setIs_active(rs.getInt("is_active"));
			
			seekers.add(seeker);
		}
		
		st.close();
		con.close();
		
		return seekers;
	}
	
	public static boolean createJobSeeker(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException {
		int u_id=0;
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query1 = "insert into users (name,email,password,type,is_active) values(?,?,?,?,?)";
		PreparedStatement ps1 = con.prepareStatement(query1);
		
		ps1.setString(1, jobSeeker.getName());
		ps1.setString(2, jobSeeker.getEmail());
		ps1.setString(3, "0000");
		ps1.setString(4, "2");
		ps1.setInt(5, jobSeeker.getIs_active());
		boolean result1 = ps1.executeUpdate() > 0;
		ps1.close();
		
		String query2="select id from users where email='"+jobSeeker.getEmail()+"'";
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query2);
		while(rs.next()) {
			u_id=rs.getInt("id");
		}
		
		stmt.close();
		
		String query = "insert into job_seekers (user_id,name,address,email,country,seeking_position,telephone,is_active) values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		
		ps.setInt(1,u_id);
		ps.setString(2, jobSeeker.getName());
		ps.setString(3, jobSeeker.getAddress());
		ps.setString(4, jobSeeker.getEmail());
		ps.setString(5, jobSeeker.getCountry());
		ps.setString(6, jobSeeker.getSeeking_position());
		ps.setString(7, jobSeeker.getTelephone());
		ps.setInt(8, jobSeeker.getIs_active());
		
		rs.close();
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	
	public static boolean updateJobSeeker(JobSeeker jobSeeker) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "update job_seekers set user_id=?, name=?, address=?, email=?, country=?, seeking_position=?, telephone=?, is_active=? where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1, jobSeeker.getUser_id());
		ps.setString(2, jobSeeker.getName());
		ps.setString(3, jobSeeker.getAddress());
		ps.setString(4, jobSeeker.getEmail());
		ps.setString(5, jobSeeker.getCountry());
		ps.setString(6, jobSeeker.getSeeking_position());
		ps.setString(7, jobSeeker.getTelephone());
		ps.setInt(8, jobSeeker.getIs_active());
		ps.setInt(9, jobSeeker.getId());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	
	public static boolean deleteJobSeeker(int seeker) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "delete from job_seekers where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, seeker);
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
}
