package aug_6_2023_Personal_project;

import javax.swing.JOptionPane;


public class Simple_GUI {

	public static void main(String[] args) {
		String name = JOptionPane.showInputDialog("Enter your name: ");
		JOptionPane.showMessageDialog(null, "Hello " + name);
		int age = Integer.parseInt(JOptionPane.showInputDialog("Enter age: "));
		JOptionPane.showMessageDialog(null, "You are " + age + " years old");
		double height = Double.parseDouble(JOptionPane.showInputDialog("Enter height: "));
		JOptionPane.showMessageDialog(null, "You are " + height + " cm tall");
		}
	}