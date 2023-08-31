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
			appointment.setDate(rs.getString("date"));
			appointment.setStart_time(rs.getString("start_time"));
			appointment.setEnd_time(rs.getString("end_time"));
			appointment.setJob_seeker(rs.getInt("job_seeker"));
			appointment.setRemarks(rs.getString("remarks"));
			
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
			appointment.setDate(rs.getString("date"));
			appointment.setStart_time(rs.getString("start_time"));
			appointment.setEnd_time(rs.getString("end_time"));
			appointment.setJob_seeker(rs.getInt("job_seeker"));
			appointment.setRemarks(rs.getString("remarks"));
			
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
			appointment.setDate(rs.getString("date"));
			appointment.setStart_time(rs.getString("start_time"));
			appointment.setEnd_time(rs.getString("end_time"));
			appointment.setJob_seeker(rs.getInt("job_seeker"));
			appointment.setRemarks(rs.getString("remarks"));
			
			appointments.add(appointment);
		}
		st.close();
		con.close();
		
		return appointments;
	}
	public static boolean insert(Appointment obj) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		
		String query = "insert into appointments(consultant,date,start_time,end_time,job_seeker,remarks) values(?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, obj.getConsultant());
		ps.setString(2, obj.getDate());
		ps.setString(3, obj.getStart_time());
		ps.setString(4, obj.getEnd_time());
		ps.setInt(5, obj.getJob_seeker());
		ps.setString(6, obj.getRemarks());
		
		boolean result = ps.executeUpdate() > 0;
		ps.close();
		con.close();
		
		return result;
	}
	public static boolean update(Appointment obj,int id) throws ClassNotFoundException, SQLException {
		DbConnection connector=new DbConnection();
		Connection con=connector.connectDb();
		
		String query = "update appointments set consultant=?, date=?,start_time=?,end_time=?,job_seeker=?,remarks=? where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, obj.getConsultant());
		ps.setString(2, obj.getDate());
		ps.setString(3, obj.getStart_time());
		ps.setString(4, obj.getEnd_time());
		ps.setInt(5, obj.getJob_seeker());
		ps.setString(6, obj.getRemarks());
		ps.setInt(7, obj.getId());
		
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
			obj.setDate(rs.getString("date"));
			obj.setStart_time(rs.getString("start_time"));
			obj.setEnd_time(rs.getString("end_time"));
			obj.setJob_seeker(rs.getInt("job_seeker"));
			obj.setRemarks(rs.getString("remarks"));
		}
		return obj;
	}
}
 