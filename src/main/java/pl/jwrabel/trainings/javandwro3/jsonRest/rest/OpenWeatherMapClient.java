package pl.jwrabel.trainings.javandwro3.jsonRest.rest;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created by jakubwrabel on 30/05/2017.
 */
public class OpenWeatherMapClient {
	public static void main(String[] args) throws UnirestException {
		String apiKey = "a1fb2306e8575f67c23fc8f23062f7e1";

		// Odpytanie o pogodę dla Warszawy
		String warsawJson = Unirest
				.get("http://api.openweathermap.org/data/2.5/weather?q=Warszawa,PL&appid=" + apiKey)
				.asString()
				.getBody();
		System.out.println("Dane dla Warszawy:");
		System.out.println(warsawJson);

		// Odpytanie o pogodę dla podanego miasta
		String city = "Wrocław,PL";
		String weatherJson = Unirest
				.get("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey)
				.asString()
				.getBody();
		System.out.println("Dane dla miasta " + city + ":");
		System.out.println(weatherJson);


		// Odpytanie o pogodę dla podanego miasta i wyświetlenie temperatury w stopniach Celsjusza (metryczny układ jednostek)
		String city2 = "Wrocław,PL";
		String weatherJson2 = Unirest
				.get("http://api.openweathermap.org/data/2.5/weather?q=" + city2 + "&units=metric&appid=" + apiKey)
				.asString()
				.getBody();
		System.out.println("Dane dla miasta " + city + ":");
		System.out.println(weatherJson2);


		//		// Wyciąganie elementów jsona z użyciem JsonNode -> chodzenie po JSONie bez konieczności mapowania na obiekty
//		// http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=a1fb2306e8575f67c23fc8f23062f7e1
		JsonNode weatherJsonObject = Unirest
				.get("http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=a1fb2306e8575f67c23fc8f23062f7e1")
				.asJson().getBody();

		System.out.println("Dane dla współrzędnych lat=35, lon=139");
		double windSpeed = weatherJsonObject.getObject().optJSONObject("wind").getDouble("speed");
		System.out.println("Prędkość wiatru: " + windSpeed);

		String description = weatherJsonObject.getObject().optJSONArray("weather").optJSONObject(0).getString("description");
		System.out.println("Opis pogody: " + description);
	}
}
