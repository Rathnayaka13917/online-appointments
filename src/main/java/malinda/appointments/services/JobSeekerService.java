package malinda.appointments.services;

import java.sql.SQLException;
import java.util.List;

import malinda.appointments.dao.JobSeekerDAO;
import malinda.appointments.models.JobSeeker;

public class JobSeekerService implements Service<JobSeeker> {

	@Override
	public JobSeeker findById(int id) throws ClassNotFoundException, SQLException {
		return JobSeekerDAO.getJobSeekerById(id);
	}

	@Override
	public boolean create(JobSeeker obj) throws ClassNotFoundException, SQLException {
		return JobSeekerDAO.createJobSeeker(obj);
	}

	@Override
	public boolean update(JobSeeker obj, int id) throws ClassNotFoundException, SQLException {
		return JobSeekerDAO.updateJobSeeker(obj);
	}

	@Override
	public boolean delete(int id) throws ClassNotFoundException, SQLException {
		return JobSeekerDAO.deleteJobSeeker(id);
	}

	@Override
	public List<JobSeeker> getAll() throws ClassNotFoundException, SQLException {
		return JobSeekerDAO.getAllJobSeekers();
	}

}
