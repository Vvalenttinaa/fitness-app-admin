package dto;

import java.io.Serializable;

public class User implements Serializable {
	
	private Integer id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String active;
	private String mail;
	private String card;
	private String city;
	private Integer diaryId;
	
	
	public User(Integer id,String firstName, String lastName, String username, String password, String card,
			String active, String mail, String city, Integer diaryId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.active = active;
		this.mail = mail;
		this.card = card;
		this.city = city;
		this.diaryId = diaryId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", active=" + active + ", mail=" + mail + ", card=" + card + ", city="
				+ city + ", diaryId=" + diaryId + "]";
	}	

}
