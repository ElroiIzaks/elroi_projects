package q1;

public abstract class Mammal extends Animal {

	/**
	 * three-argument constructor initializes Animal's name, age and color
	 * 
	 * @param newName  the name of the mammal (string)
	 * @param newAge   the age of the mammal (int)
	 * @param newColor the color of the mammal (string)
	 */
	public Mammal(String name, int age, String color) {
		super(name, age, color);
	}

	// return String representation of mammal
	/**
	 * toString function format: "mammal- name: david, age: 2, color: black"
	 */
	public String toString() {
		return "mammal- " + super.toString();
	}

}