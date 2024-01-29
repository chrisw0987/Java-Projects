//THIS PROJECT IS A CURRENCY CONVERTER THAT CURRENTLY SUPPORTS USD, AUD, AND YEN CONVERSIONS//

package aug_6_2023_Personal_project;
import java.util.Scanner;

public class Currency_Converter {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome! Would you like to convert a currency? (yes/no)");
		String intro = scanner.nextLine();
		if (intro.toLowerCase().contains("no")) {
			System.out.println("OK! Have a good day.");
		} 
		else if(intro.toLowerCase().contains("yes")) {
			System.out.println("Great, Let's continue!");
			System.out.println("Which currency do you have?(USD / AUD / YEN)");
			String input = scanner.nextLine();
			
			if (input.toUpperCase().contains("USD")) {
				System.out.println("You picked: " + input.toUpperCase());
				System.out.println("Enter amount in USD: ");
				double amount = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Which currency would you like to convert to?(AUD / YEN)");
				String convert = scanner.nextLine();
				if (convert.toUpperCase().contains("AUD")) {
					System.out.println("The converted amount from USD TO AUD is: " + (amount * 1.52));
				}
				else if (convert.toUpperCase().contains("YEN")) {
					System.out.println("The converted amount from USD TO YEN is: " + (amount * 141.76));
				}				
			}
			if (input.toUpperCase().contains("AUD")) {
				System.out.println("You picked: " + input.toUpperCase());
				System.out.println("Enter amount in AUD: ");
				double amount = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Which currency would you like to convert to?(USD / YEN)");
				String convert = scanner.nextLine();
				if (convert.toUpperCase().contains("USD")) {
					System.out.println("The converted amount from AUD TO USD is: " + (amount * 0.66));
				}
				else if (convert.toUpperCase().contains("YEN")) {
					System.out.println("The converted amount from AUD TO YEN is: " + (amount * 93.52));
				}				
			}
			if (input.toUpperCase().contains("YEN")) {
				System.out.println("You picked: " + input.toUpperCase());
				System.out.println("Enter amount in YEN: ");
				double amount = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Which currency would you like to convert to?(USD / AUD)");
				String convert = scanner.nextLine();
				if (convert.toUpperCase().contains("USD")) {
					System.out.println("The converted amount from YEN TO USD is: " + (amount * 1.52));
				}
				else if (convert.toUpperCase().contains("AUD")) {
					System.out.println("The converted amount from YEN TO AUD is: " + (amount * 141.76));
				}				
			}			
		scanner.close();
	}
}
}