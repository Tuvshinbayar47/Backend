package ItpecB;

import java.util.Scanner;

public class Grader {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter your score: ");
			int score = scanner.nextInt();  // user input

			String ret;

			if (score >= 80) {
			    ret = "A" + " ----You Pass----";
			} else if (score >= 50) {
			    ret = "B" + " ----Do your Best !!!----";
			} else {
			    ret = "C" + "----Fail----";
			}

			System.out.println("Your grade is: " + ret );
		}
    }
}

