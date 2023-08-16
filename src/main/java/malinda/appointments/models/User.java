package malinda.appointments.models;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private String type;
	private int is_active;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int string) {
		this.is_active = string;
	}
	public User() {
		this.id = 0;
		this.name = "";
		this.email = "";
		this.password = "";
		this.type = "";
		this.is_active = 0;
	}
	public User(int id, String name, String email, String password, String type, int is_active) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
		this.is_active = is_active;
	}
	
}
