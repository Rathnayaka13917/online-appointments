package malinda.appointments.models;

public class Consultant {
	private int id;
	private int user_id;
	private String name;
	private String address;
	private String email;
	private String country;
	private String expertise;
	private String telephone;
	private int is_active;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public Consultant(int id, int user_id, String name, String address, String email, String country, String expertise,
			String telephone, int is_active) {
		this.id = id;
		this.user_id = user_id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.country = country;
		this.expertise = expertise;
		this.telephone = telephone;
		this.is_active = is_active;
	}
	public Consultant() {
		
	}
	
}
