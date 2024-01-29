package aug_6_2023_Personal_project;
import java.util.Scanner;
import java.util.Random;

public class Random_Dice_Game {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		
		int lives = 3;
		int wins = 0;
		System.out.println("Hello! Would you like to play a dice game? (y/n)");
		char intro = scanner.next().charAt(0);
		scanner.nextLine();
		if (intro == 'y') {
			System.out.println("Pefect! Let's play a guessing game! "
				+ "I will generate a random dice number and you have to guess it."
				+ "If you guess correctly 3 times, you win!"
				+ "If you guess incorrectly 3 times you lose!"
				+ "Let's play!");
			
		while ((lives != 0) && (wins != 3)) {
		System.out.println("Guess a number from 1-6 while I generate one: ");
		int input = scanner.nextInt();
		scanner.nextLine();
		int com = (int)(Math.random()*6+1);
		System.out.println("You picked: " + input + " and I picked: " + com);
		if (input == com) {
			System.out.println("Correct! You get 1 point!");
			wins += 1;
			System.out.println("You have " + wins + " wins");
		}
		else {
			System.out.println("Wrong! You lose 1 life!");
			lives -= 1;
			System.out.println("You have " + lives + " lives remaining");
		}
		}
		if (lives ==0) {
			System.out.println("You Lost! Better luck next time!");
		}
		if (wins == 3) {
			System.out.println("You Won! Good Job!");
		}
		}
		else {
			System.out.println("Thank you. Come Again!");
			System.exit(0);
		}		
		scanner.close();
	}
}