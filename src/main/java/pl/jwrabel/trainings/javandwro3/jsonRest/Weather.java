package pl.jwrabel.trainings.javandwro3.jsonRest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jakubwrabel on 26/05/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
	private String name;
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Weather{" +
				"name='" + name + '\'' +
				", id='" + id + '\'' +
				'}';
	}
}
