package pl.jwrabel.trainings.javandwro3.jsonRest.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by jakubwrabel on 29/05/2017.
 */
public class ServerClient {

	public static final Scanner SCANNER = new Scanner(System.in);



	public static void main(String[] args) throws UnirestException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
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
				case "2":
					findCustomerById();
					break;
				case "3":
					createCustomer();
					break;
				default:
					System.out.println("Niepoprawny kod operacji");

			}
		}
	}

	private static void createCustomer() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Podaj imię: ");
		String firstName = SCANNER.nextLine();
		System.out.println("Podaj nazwisko: ");
		String lastName = scanner.nextLine();
		System.out.println("Podaj datę urodzenia: ");
		String birthYear = scanner.nextLine();
		System.out.println("Podaj wzrost (w cm): ");
		Double height = scanner.nextDouble();

		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setBirthYear(birthYear);
		customer.setHeight(height);
		customer.setId(UUID.randomUUID().toString());

		String response = null;
		try {
			response = Unirest.post("http://195.181.209.160:8080/api/v1/customers")
					.header("Content-Type", "application/json")
					.body(customer)
					.asString().getBody();
			System.out.println("Odpowiedź serwera: " + response);
		} catch (UnirestException e) {
			e.printStackTrace();
		}


	}

	private static void findCustomerById() {
		System.out.println("Podaj id klienta:");
		String id = new Scanner(System.in).nextLine();
		try {
			Customer customer = Unirest.get("http://195.181.209.160:8080/api/v1/customers/" + id).asObject(Customer.class).getBody();
			System.out.println("Dane użytkownika o ID: " + id);
			System.out.println(customer);
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	private static void printAllCustomers() {
		System.out.println("=== WSZYSCY KLIENCI ===");
		String customersJson = null;
		try {
			customersJson = Unirest.get("http://195.181.209.160:8080/api/v1/customers").asString().getBody();

			com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
			List<Customer> allCustomersList = objectMapper.readValue(customersJson,
					TypeFactory.defaultInstance().constructCollectionType(List.class, Customer.class));

			for (Customer customer : allCustomersList) {
				System.out.println(customer);
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void setupUnirest() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
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
