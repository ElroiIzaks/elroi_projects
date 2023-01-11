package q1;

public class Snake extends Reptile {

	/**
	 * three-argument constructor initializes snake's name, age and color
	 * 
	 * @param Name  the name of the snake (string)
	 * @param Age   the age of the snake (int)
	 * @param Color the color of the snake (string)
	 */
	public Snake(String name, int age, String color) {
		super(name, age, color);
	}

	/**
	 * represent eating by string
	 */
	public void eat() {
		System.out.println("The snake eat a mouse");
	}

	/**
	 * clone the snake (deep copy)
	 * 
	 * @return the properties of the snake as new snake
	 */
	protected Snake clone() {
		Snake copy = new Snake(getName(), getAge(), getColor());
		return copy;
	}

// return String representation of snake
	/**
	 * toString function format: "snake,mammal- name: david, age: 2, color: black"
	 */
	public String toString() {
		return "snake, " + super.toString();
	}

	/**
	 * equals function
	 * 
	 * @param otherSnake the snake to compare
	 * @return true if the snakes has the same name, age, color and owner (by
	 *         properties)
	 */
	public boolean equals(Snake otherSnake) {
		if (super.equals((Animal) otherSnake) == true)
			return true;
		return false;
	}
}
