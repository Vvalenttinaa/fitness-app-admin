package dto;

import java.io.Serializable;

public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private boolean loggedIn;
	
	public Admin() {
	}
	
	public Admin(Integer id, String username, String password, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public boolean getLoggedIn() {
		return loggedIn;
	}


	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", loggedIn=" + loggedIn + "]";
	}


	

}
