package malinda.appointments.services;

import java.sql.SQLException;
import java.util.List;

import malinda.appointments.dao.JobSeekerDAO;
import malinda.appointments.dao.UserDAO;
import malinda.appointments.models.JobSeeker;
import malinda.appointments.models.User;

public class JobSeekerService implements Service<JobSeeker> {

	@Override
	public JobSeeker findById(int id) throws ClassNotFoundException, SQLException {
		return JobSeekerDAO.getJobSeekerById(id);
	}

	@Override
	public boolean create(JobSeeker obj) throws ClassNotFoundException, SQLException {
		User user = new User();
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		user.setIs_active(obj.getIs_active());
		user.setPassword("0000");
		user.setType("2");
		boolean result = UserDAO.createUser(user);
		
		int u_id = UserDAO.getUserId(obj.getEmail());
		obj.setUser_id(u_id);
		return JobSeekerDAO.createJobSeeker(obj);
	}

	@Override
	public boolean update(JobSeeker obj, int id) throws ClassNotFoundException, SQLException {
		User user = new User();
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		user.setIs_active(obj.getIs_active());
		user.setPassword("0000");
		user.setType("2");
		boolean result = UserDAO.updateUser(user, obj.getUser_id());
		return JobSeekerDAO.updateJobSeeker(obj,id);
	}

	@Override
	public boolean delete(int id) throws ClassNotFoundException, SQLException {
		JobSeeker jobSeeker = JobSeekerDAO.getJobSeekerById(id);
		boolean result = UserDAO.deleteUser(jobSeeker.getUser_id());
		return JobSeekerDAO.deleteJobSeeker(id);
	}

	@Override
	public List<JobSeeker> getAll() throws ClassNotFoundException, SQLException {
		return JobSeekerDAO.getAllJobSeekers();
	}

}
