package pl.jwrabel.trainings.javandwro3.jsonRest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.Arrays;

/**
 * Created by jakubwrabel on 25/05/2017.
 */
public class Json {
	public static void main(String[] args) throws JsonProcessingException {
		{
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);


			Point point = new Point();
			point.setX(100);
			point.setY(300);


			String pointJson = objectMapper.writeValueAsString(point);
			System.out.println(pointJson);













//			ObjectMapper objectMapper = new ObjectMapper();
//			// włączenie formatowania wyjścia
//
//			// ZAMIANA OBIEKTU NA JSON
//			Place place = new Place("Dom", "Warszawa", Arrays.asList(new Point(100, 200)));
//			String placeJson = objectMapper.writeValueAsString(place);
//			System.out.println(placeJson);
//
//			// ZAPIS JSONA DO PLIKU
//			objectMapper.writeValue(new File("placeJackson.json"), place);
//
//			String placeAsJson = "{\n" +
//					"  \"name\" : \"Dom\",\n" +
//					"  \"city\" : \"Warszawa\",\n" +
//					"  \"points\" : [ {\n" +
//					"    \"x\" : 100,\n" +
//					"    \"y\" : 200\n" +
//					"  } ]\n" +
//					"}";
//
//			// ZAMIANA JSONA NA OBIEKT
//			Place placeReader = objectMapper.readValue(placeAsJson, Place.class);
//
//			// ZAMIANA JSONA Z PLIKU NA OBIEKT
////		Place placeReaded = objectMapper.readValue(new File("cust.json"), Place.class);
//
//			// 1. Stworzyć klasę odwzorowującą poniższego JSONA
////		Customer/Person
//			String jsonString = "{\"firstName\":\"Adam\",\"lastName\":\"Kowalski\",\"birthYear\":1980,\"idNumber\":\"ABC\"}";
//
//
//			String jsonString2 = "{\n" +
//					"\"coord\": {\n" +
//					"\"lon\": 139,\n" +
//					"\"lat\": 35\n" +
//					"},\n" +
//					"\"weather\": [\n" +
//					"{\n" +
//					"\"id\": 800,\n" +
//					"\"main\": \"Clear\",\n" +
//					"\"description\": \"clear sky\",\n" +
//					"\"icon\": \"01n\"\n" +
//					"}\n" +
//					"]}";
//			System.out.println(jsonString2);
		}
	}
}
