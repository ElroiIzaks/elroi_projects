package q2;

import java.util.Scanner;

public class Main {

	// this program show different arithmetic functions on rationales numbers
	public static void main(String[] args) {

		// main number (first number in functions)
		int numerator = getAnInteger("enter numerator:");
		int denominator = getAnInteger("enter denominator:");
		Rational rational = new Rational(numerator, denominator);
		System.out.println(rational + " (" + rational.reduce() + ").\n");

		// other number (second number in functions)
		System.out.println("enter another rational number fot some functions.");
		numerator = getAnInteger("second's numerator:");
		denominator = getAnInteger("second's denominator:");
		Rational other = new Rational(numerator, denominator);
		System.out.println(other + " (" + other.reduce() + ").\n");

		// greaterThan + equals
		if (!rational.equals(other)) {
			System.out.println(
					rational + " is " + (rational.greaterThan(other) ? "greater" : "smaller") + " than " + other + ".");

		} else {
			System.out.println(rational + " is equals to " + other + ".");
		}
		// plus
		System.out.println(
				rational + " + " + other + " = " + rational.plus(other) + " (" + rational.plus(other).reduce() + ").");
		// minus
		System.out.println(rational + " - " + other + " = " + rational.minus(other) + " ("
				+ rational.minus(other).reduce() + ").");
		// multiply
		System.out.println(rational + " * " + other + " = " + rational.multiply(other) + " ("
				+ rational.multiply(other).reduce() + ").");
		// divide
		System.out.println("(" + rational + ")" + " / " + "(" + other + ")" + " = " + rational.divide(other) + " ("
				+ rational.divide(other).reduce() + ").");

	}

	/**
	 * this function get an integer from the user in case of unInteger input,
	 * massage will be send
	 * 
	 * @param massage the massage which the system will send to the user before
	 * @return the integer
	 */
	public static int getAnInteger(String massage) {
		Scanner sc = new Scanner(System.in);
		int integer = 0;
		System.out.println(massage);
		while (integer == 0) {
			try { // bad input (exemple) - 5.3
				integer = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("only integer is allowed as input. try again");
			}
		}
		return integer;

	}
}
