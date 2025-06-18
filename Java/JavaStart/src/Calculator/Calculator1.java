package Calculator;
import java.util.Scanner;
public class Calculator1 {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
			System.out.print("Enter first number: ");
			double num1 = input.nextDouble();

			System.out.print("Enter operator (+ - * /): ");
			char operator = input.next().charAt(0);

			System.out.print("Enter second number: ");
			double num2 = input.nextDouble();
			
			double result;

			switch (operator) {
			    case '+':
			        result = num1 + num2;
			        break;
			    case '-':
			        result = num1 - num2;
			        break;
			    case '*':
			        result = num1 * num2;
			        break;
			    case '/':
			        if (num2 != 0) {
			            result = num1 / num2;
			        } else {
			            System.out.println("Error: Division by zero");
			            return;
			        }
			        break;
			    default:
			        System.out.println("I can't do that");
			        return;
			}

			System.out.println("Result: " + result);
		}
    }
}
