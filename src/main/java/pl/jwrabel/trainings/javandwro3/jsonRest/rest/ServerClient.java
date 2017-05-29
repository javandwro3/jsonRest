package pl.jwrabel.trainings.javandwro3.jsonRest.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by jakubwrabel on 29/05/2017.
 */
public class ServerClient {
	public static void main(String[] args) {
		setupUnirest();

		while (true) {
			System.out.println("===== MENU ====");
			System.out.println("1. Wyświetl wszystkich klientów");
			System.out.println("2. Wyświetl dane jednego klienta");
			System.out.println("3. Stwórz nowego klienta");
			System.out.println("4. Zaktualizuj dane klienta");
			System.out.println("5. Usuń klienta");
			System.out.println("0. Zakończ program");
			System.out.println("\nPodaj kod operacji: ");

			String operationString = new Scanner(System.in).nextLine();

			switch (operationString) {
				case "0":
					System.exit(0);
				case "1":
					printAllCustomers();
					break;
				default:
					System.out.println("Niepoprawny kod operacji");

			}
		}
	}

	private static void printAllCustomers() {

	}

	private static void setupUnirest() {
		Unirest.setObjectMapper(new ObjectMapper() {
			private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
					= new com.fasterxml.jackson.databind.ObjectMapper();

			public <T> T readValue(String value, Class<T> valueType) {
				try {
					return jacksonObjectMapper.readValue(value, valueType);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

			public String writeValue(Object value) {
				try {
					return jacksonObjectMapper.writeValueAsString(value);
				} catch (JsonProcessingException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
}
