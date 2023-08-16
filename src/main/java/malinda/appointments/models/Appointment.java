package malinda.appointments.models;

public class Appointment {
	private int id;
	private int consultant;
	private String start_time;
	private String end_time;
	private String date;
	private int job_seeker;
	private String remarks;
	
	public Appointment() {
	}

	public Appointment(int id, int consultant, String start_time, String end_time, String date, int job_seeker,String remarks) {
		this.id = id;
		this.consultant = consultant;
		this.start_time = start_time;
		this.end_time = end_time;
		this.date = date;
		this.job_seeker = job_seeker;
		this.remarks = remarks;
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

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
	
}
