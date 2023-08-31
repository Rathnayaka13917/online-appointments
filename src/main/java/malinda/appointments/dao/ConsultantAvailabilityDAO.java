package malinda.appointments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import malinda.appointments.models.ConsultantAvailability;

public class ConsultantAvailabilityDAO {
	public static ConsultantAvailability getConsultantAvailabilityById(int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		
		String query = "select * from consultant_availabilities where id =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		ConsultantAvailability availability = new ConsultantAvailability();
		
		if (rs.next()) {
			availability.setId(rs.getInt("id"));
			availability.setDay(rs.getString("day"));
			availability.setStart_time(rs.getString("start_time"));
			availability.setEnd_time(rs.getString("end_time"));
			availability.setConsultant(rs.getInt("consultant"));
			availability.setReserved(rs.getInt("reserved"));
		}
		
		ps.close();
		con.close();
		
		return availability;
	}
	
	public static List<ConsultantAvailability> getConsultantAllAvalability(int consultant) throws ClassNotFoundException, SQLException{
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		
		String query = "select * from consultant_availabilities where consultant =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, consultant);
		
		ResultSet rs = ps.executeQuery();
		
		List<ConsultantAvailability> availabilities = new ArrayList<ConsultantAvailability>();
		while (rs.next()) {
			ConsultantAvailability availability = new ConsultantAvailability();
			availability.setId(rs.getInt("id"));
			availability.setDay(rs.getString("day"));
			availability.setStart_time(rs.getString("start_time"));
			availability.setEnd_time(rs.getString("end_time"));
			availability.setConsultant(rs.getInt("consultant"));
			availability.setReserved(rs.getInt("reserved"));
			
			availabilities.add(availability);
		}
		
		ps.close();
		con.close();
		
		return availabilities;
	}
	
	public static boolean createConsultantAvailability(ConsultantAvailability availability) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		
		String query = "insert into consultant_availabilities (day,start_time,end_time,consultant) values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, availability.getDay());
		ps.setString(2, availability.getStart_time());
		ps.setString(3, availability.getEnd_time());
		ps.setInt(4, availability.getConsultant());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	
	public static boolean updateConsultantAvailability(ConsultantAvailability availability, int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		
		String query = "update consultant_availabilities set day=?, start_time=?, end_time=?, consultant=?,reserved=? where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, availability.getDay());
		ps.setString(2, availability.getStart_time());
		ps.setString(3, availability.getEnd_time());
		ps.setInt(4, availability.getConsultant());
		ps.setInt(5, availability.getReserved());
		ps.setInt(6, id);
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	
	public static boolean deleteAvailability(int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "delete from consultant_availabilities where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
}
