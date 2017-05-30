package pl.jwrabel.trainings.javandwro3.jsonRest.rest;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by jakubwrabel on 30/05/2017.
 */
public class CountriesTemperature {
	public static void main(String[] args) throws UnirestException {
		JsonNode jsonNode = Unirest.get("https://restcountries-v1.p.mashape.com/all")
				.header("X-Mashape-Key", "YOUR_MASHAPE_KEY")
				.header("Accept", "application/json")
				.asJson().getBody();

		JSONArray jsonArray = jsonNode.getArray();

		System.out.println("Cities size: " + jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.optJSONObject(i);

			System.out.println("------------");
			String name = jsonObject.getString("name");
			System.out.println("Country name: " + name);
			String capital = jsonObject.getString("capital");
			System.out.println("Capital: " + capital);

			double tempForCity = getTempForCity(capital);
			System.out.println("Temp: " + tempForCity + "\u00b0C");
		}

	}

	public static double getTempForCity(String cityName) {
		String apiKey = "YOUR_OPENWEATHERMAP_KEY";

		cityName = cityName.replace(" ", "%20");

		if(cityName.equals("")){
			return -100000;
		}
		// LUB
		if(cityName.isEmpty()){
			return -100000;
		}

		try {
			JsonNode jsonNode = Unirest
					.get("http://api.openweathermap.org/data/2.5/weather?units=metric&q=" + cityName + "&appid=" + apiKey)
					.asJson()
					.getBody();
			double temp = jsonNode.getObject().optJSONObject("main").getDouble("temp");
			return temp;
		} catch (UnirestException e) {
			e.printStackTrace();
		}

		return -10000;
	}
}
