package models;

public class Patient {
	private long id;
	private boolean hasPatientName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private int birthDay;
	private String birthMonth;
	private int birthYear;
	private int estBirthYears;
	private int estBirthMonths;
	private String address;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private String phoneNumber;
	
	public Patient(long id, boolean hasPatientName, String firstName, String middleName, String lastName, String gender, int birthDay,
			String birthMonth, int birthYear, int estBirthYears, int estBirthMonths, String address, String address2,
			String city, String state, String country, String postalCode, String phoneNumber) {
		super();
		this.id = id;
		this.hasPatientName = hasPatientName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDay = birthDay;
		this.birthMonth = birthMonth;
		this.birthYear = birthYear;
		this.estBirthYears = estBirthYears;
		this.estBirthMonths = estBirthMonths;
		this.address = address;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
	}
	
	public Patient(boolean hasPatientName, String firstName, String middleName, String lastName, String gender, int birthDay,
			String birthMonth, int birthYear, int estBirthYears, int estBirthMonths, String address, String address2,
			String city, String state, String country, String postalCode, String phoneNumber) {
		super();
		this.hasPatientName = hasPatientName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDay = birthDay;
		this.birthMonth = birthMonth;
		this.birthYear = birthYear;
		this.estBirthYears = estBirthYears;
		this.estBirthMonths = estBirthMonths;
		this.address = address;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getHasPatientName() {
		return hasPatientName;
	}

	public void setHasPatientName(boolean hasPatientName) {
		this.hasPatientName = hasPatientName;
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

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getEstBirthYears() {
		return estBirthYears;
	}

	public void setEstBirthYears(int estBirthYears) {
		this.estBirthYears = estBirthYears;
	}

	public int getEstBirthMonths() {
		return estBirthMonths;
	}

	public void setEstBirthMonths(int estBirthMonths) {
		this.estBirthMonths = estBirthMonths;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}

	
