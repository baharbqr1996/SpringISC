package Spring.wallet.model;


import java.time.LocalDate;

import jakarta.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull(message = "Username is required")
    private String username;
    @NotNull(message = "Password is required")
    private String password;
    private String role;
    @NotNull(message = "National id is required")
    @Pattern(regexp = "^(?!\\d*(\\d)\\1{9})\\d{10}$", message = "National code must be a 10-digit number and cannot consist of the same digit")
    @Column(unique = true)
    private String nationalId;
    @NotNull(message = "Mobile number is required")
    @Pattern(regexp = "^09[0-9]{9}$", message = "Mobile number must be 11 digits.")
    private String mobileNumber;
    @NotNull(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Birth date is required")
    private LocalDate birthDate;
    @NotNull(message = "Gender is required")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Boolean militaryStatus;
   // @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Account account;
    
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Person(Long id, String nationalId, String mobileNumber, String firstName, String lastName,
			LocalDate birthDate,  Gender gender, Boolean militaryStatus, String email) {
		super();
		this.id = id;
		this.nationalId = nationalId;
		this.mobileNumber = mobileNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.militaryStatus = militaryStatus;
		this.email = email;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Boolean getMilitaryStatus() {
		return militaryStatus;
	}

	public void setMilitaryStatus(Boolean militaryStatus) {
		this.militaryStatus = militaryStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", nationalId=" + nationalId + ", mobileNumber=" + mobileNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + ", gender=" + gender
				+ ", militaryStatus=" + militaryStatus + ", email=" + email + "]";
	}

	

    
}
