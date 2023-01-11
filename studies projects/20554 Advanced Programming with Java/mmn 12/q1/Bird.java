package q1;

public abstract class Bird extends Animal {

	/**
	 * three-argument constructor initializes Bird's name, age and color
	 * 
	 * @param newName  the name of the Bird (string)
	 * @param newAge   the age of the Bird (int)
	 * @param newColor the color of the Bird (string)
	 */
	public Bird(String name, int age, String color) {
		super(name, age, color);
	}

	/**
	 * represent flying by string
	 */
	public void fly() {
		System.out.println(getName() + " is now flying");
	}

	// return String representation of bird
	/**
	 * toString function format: "bird- name: david, age: 2, color: black"
	 */
	public String toString() {
		return "bird- " + super.toString();
	}

}