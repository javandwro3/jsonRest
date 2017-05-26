package pl.jwrabel.trainings.javandwro3.jsonRest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jakubwrabel on 25/05/2017.
 */
public class Customer {
	@JsonProperty("imie")
	private String firstName;
	@JsonProperty("nazwisko")
	private String lastName;
	private int birthYear;
	private String idNumber;

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

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", birthYear=" + birthYear +
				", idNumber='" + idNumber + '\'' +
				'}';
	}
}
