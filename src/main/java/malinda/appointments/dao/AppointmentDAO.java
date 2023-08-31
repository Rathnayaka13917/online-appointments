package malinda.appointments.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import malinda.appointments.models.Appointment;

public class AppointmentDAO {
	public static List<Appointment> getAllAppointments() throws ClassNotFoundException, SQLException{
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "select * from appointments";
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(query);
		List<Appointment> appointments = new ArrayList<Appointment>();
		while (rs.next()) {
			Appointment appointment = new Appointment();
			appointment.setId(rs.getInt("id"));
			appointment.setConsultant(rs.getInt("consultant"));
			appointment.setJob_seeker(rs.getInt("job_seeker"));
			appointment.setRemarks(rs.getString("remarks"));
			appointment.setAvailability(rs.getInt("availability"));
			
			appointments.add(appointment);
		}
		st.close();
		con.close();
		
		return appointments;
	}
	public static List<Appointment> getAppointmentsByConsultant(int id) throws ClassNotFoundException, SQLException{
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "select * from appointments where consultant='"+id+"'";
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(query);
		List<Appointment> appointments = new ArrayList<Appointment>();
		while (rs.next()) {
			Appointment appointment = new Appointment();
			appointment.setId(rs.getInt("id"));
			appointment.setConsultant(rs.getInt("consultant"));
			appointment.setJob_seeker(rs.getInt("job_seeker"));
			appointment.setRemarks(rs.getString("remarks"));
			appointment.setAvailability(rs.getInt("availability"));
			
			appointments.add(appointment);
		}
		st.close();
		con.close();
		
		return appointments;
	}
	public static List<Appointment> getAppointmentsBySeeker(int id) throws ClassNotFoundException, SQLException{
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "select * from appointments where job_seeker='"+id+"'";
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(query);
		List<Appointment> appointments = new ArrayList<Appointment>();
		while (rs.next()) {
			Appointment appointment = new Appointment();
			appointment.setId(rs.getInt("id"));
			appointment.setConsultant(rs.getInt("consultant"));
			appointment.setJob_seeker(rs.getInt("job_seeker"));
			appointment.setRemarks(rs.getString("remarks"));
			appointment.setAvailability(rs.getInt("availability"));
			
			appointments.add(appointment);
		}
		st.close();
		con.close();
		
		return appointments;
	}
	public static boolean insert(Appointment obj) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		
		String query = "insert into appointments(consultant,job_seeker,remarks,availability) values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, obj.getConsultant());
		ps.setInt(2, obj.getJob_seeker());
		ps.setString(3, obj.getRemarks());
		ps.setInt(4, obj.getAvailability());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	public static boolean update(Appointment obj,int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		
		String query = "update appointments set consultant=?,job_seeker=?,remarks=?,availability=? where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, obj.getConsultant());
		ps.setInt(2, obj.getJob_seeker());
		ps.setString(3, obj.getRemarks());
		ps.setInt(4, obj.getAvailability());
		ps.setInt(5, obj.getId());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	public static boolean delete(int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		String query = "delete from appointments where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	public static Appointment getAppointmentById(int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		
		String query = "select * from appointments where id =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		Appointment obj=new Appointment();
		if(rs.next()) {
			obj.setId(rs.getInt("id"));
			obj.setConsultant(rs.getInt("consultant"));
			obj.setJob_seeker(rs.getInt("job_seeker"));
			obj.setRemarks(rs.getString("remarks"));
			obj.setAvailability(rs.getInt("availability"));
		}
		return obj;
	}
}
 