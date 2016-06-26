package models;
	

public class Account {

	// Attributes
	private long id;
	private String username;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String role;
	
	/**
	 * Account constructor without ID attribute
	 * @param username Username
	 * @param password Password
	 * @param firstName First Name
	 * @param middleName Middle Name
	 * @param lastName Last Name
	 * @param gender Gender
	 * @param role Role
	 */
	public Account(String username, String password, String firstName, String middleName, String lastName,
			String gender, String role) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.role = role;
	}

	/**
	 * Account constructor including ID attribute
	 * @param id ID
	 * @param username Username
	 * @param password Password
	 * @param firstName First Name
	 * @param middleName Middle Name
	 * @param lastName Last Name
	 * @param gender Gender
	 * @param role Role
	 */
	public Account(long id, String username, String password, String firstName, String middleName, String lastName,
			String gender, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.role = role;
	}





	/**
	 * Getters and Setters
	 */
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public long getId() {
		return id;
	}
	
	
}
