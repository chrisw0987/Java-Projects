//SHORT JAVA HYPOTENUSE MATH PROJECT

package aug_6_2023_Personal_project;
import java.util.Scanner;

public class Find_Hypotenuse {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Give side A length: ");
		double A = scanner.nextDouble();
		System.out.println("Give side B length: ");
		double B = scanner.nextDouble();
			
		double C = Math.sqrt((Math.pow(A, 2) + Math.pow(B, 2)));
		System.out.println("Side C length is: " + C);
			
		scanner.close();
		}
}