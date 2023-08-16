package malinda.appointments.models;

public class ConsultantAvailability {
	private int id;
	private String day;
	private String start_time;
	private String end_time;
	private int consultant;
	
	public ConsultantAvailability() {
	}

	public ConsultantAvailability(int id, String day, String start_time, String end_time, int consultant) {
		this.id = id;
		this.day = day;
		this.start_time = start_time;
		this.end_time = end_time;
		this.consultant = consultant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
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

	public int getConsultant() {
		return consultant;
	}

	public void setConsultant(int consultant) {
		this.consultant = consultant;
	}
}
