package q2;

//
public class Rational {

	private int numerator;
	private int denominator;

	/**
	 * constructor initializes numerator and denominator
	 * 
	 * @param numerator
	 * @param denominator
	 */
	public Rational(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		if (denominator <= 0) {
			throw new ArithmeticException("Denominator can be only positive integer.");
		}
	}

	/**
	 * get function return the numerator
	 * 
	 * @return the numerator
	 */
	public int getNumerator() {
		return numerator;
	}

	/**
	 * get function return the denominator
	 * 
	 * @return the denominator
	 */
	public int getDenominator() {
		return denominator;
	}

	/**
	 * check if the rational number is greater than other rational
	 * 
	 * @param other the rational number for comparing
	 * @return true if the "this" rational number is greater
	 */
	public boolean greaterThan(Rational other) {
		if (numerator * other.getDenominator() > denominator * other.getNumerator())
			return true;
		return false;
	}

	/**
	 * equals function
	 * 
	 * @param other the rational number to compare
	 * @return true if the numbers are equals (mathematically)
	 */
	public boolean equals(Rational other) {
		if (numerator * other.getDenominator() == denominator * other.getNumerator())
			return true;
		return false;
	}

	/**
	 * plus function
	 * 
	 * @param other the rational number to add
	 * @return the answer to the adding as a Rational object
	 */
	public Rational plus(Rational other) {
		Rational rational = new Rational(numerator * other.getDenominator() + denominator * other.getNumerator(),
				denominator * other.getDenominator());
		return rational;
	}

	/**
	 * minus function
	 * 
	 * @param other the rational number to decrease
	 * @return the answer to the decreasing as a Rational object
	 */
	public Rational minus(Rational other) {
		Rational rational = new Rational(numerator * other.getDenominator() - denominator * other.getNumerator(),
				denominator * other.getDenominator());
		return rational;
	}

	/**
	 * multiply function
	 * 
	 * @param other the rational number to multiply
	 * @return the answer to the multiplying as a Rational object
	 */
	public Rational multiply(Rational other) {
		Rational rational = new Rational(numerator * other.getNumerator(), denominator * other.getDenominator());
		return rational;
	}

	/**
	 * divide function
	 * 
	 * @param other the rational number to divide
	 * @return the answer to the dividing as a Rational object
	 */
	public Rational divide(Rational other) {
		if (other.getNumerator() == 0) {
			throw new ArithmeticException("can not divide by 0.");
		}
		Rational rational = new Rational(other.denominator, other.numerator);
		return this.multiply(rational);
	}

	// return String representation of eagle
	/**
	 * toString function format: a/b
	 */
	public String toString() {
		return numerator + "/" + denominator;
	}

	/**
	 * reduce function
	 * 
	 * @return the answer to the reducing as a Rational object
	 */
	public Rational reduce() {
		int a = numerator;
		int b = denominator;
		int temp;
		while (b != 0) {
			temp = a % b;
			a = b;
			b = temp;
		}
		if (a < 0)
			return new Rational(numerator / a * (-1), denominator / a * (-1));
		return new Rational(numerator / a, denominator / a);
	}

}
