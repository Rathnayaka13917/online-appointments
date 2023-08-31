package malinda.appointments.models;

public class Appointment {
	private int id;
	private int consultant;
	private int job_seeker;
	private String remarks;
	private int availability;
	
	public Appointment() {
	}

	public Appointment(int id, int consultant, int job_seeker,String remarks, int availability) {
		this.id = id;
		this.consultant = consultant;
		this.job_seeker = job_seeker;
		this.remarks = remarks;
		this.availability = availability;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConsultant() {
		return consultant;
	}

	public void setConsultant(int consultant) {
		this.consultant = consultant;
	}

	public int getJob_seeker() {
		return job_seeker;
	}

	public void setJob_seeker(int job_seeker) {
		this.job_seeker = job_seeker;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}
	
}
