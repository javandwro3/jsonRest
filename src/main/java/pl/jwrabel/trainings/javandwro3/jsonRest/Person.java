package pl.jwrabel.trainings.javandwro3.jsonRest;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by jakubwrabel on 26/05/2017.
 */
public class Person {
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Date birthDate;

	public Person() {
	}

	public Person(String name, Date birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", birthDate=" + birthDate +
				'}';
	}
}
