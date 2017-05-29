package pl.jwrabel.trainings.javandwro3.jsonRest;

import java.util.Scanner;

/**
 * Created by jakubwrabel on 29/05/2017.
 */
public class SwitchExample {
	public static void main(String[] args) {
		System.out.println("Podaj x: ");
		double x = new Scanner(System.in).nextDouble();
		System.out.println("Podaj y: ");
		double y = new Scanner(System.in).nextDouble();
		System.out.println("Podaj znak operacji (+,-,*,/)");
		String operation = new Scanner(System.in).nextLine();


		double result = 0;

		if (operation.equals("+")) {
			result = x + y;
		} else if (operation.equals("-")) {
			result = x - y;
		} else if (operation.equals("*")) {
			result = x * y;
		} else if (operation.equals("/")) {
			result = x / y;
		} else {
			System.out.println("Niepoprawny znak operacji");
		}

		// TO TO SAMO CO:
		switch (operation) {
			case "+":
				result = x + y;
				break;
			case "-":
				result = x - y;
				break;
			case "*":
				result = x * y;
				break;
			case "/":
				result = x / y;
				break;
			default:
				System.out.println("Niepoprawny znak operacji");
		}


		System.out.println("Wynik to: " + result);


	}
}
