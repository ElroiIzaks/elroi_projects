package q1;

public class Eagle extends Bird {

	/**
	 * three-argument constructor initializes eagle's name, age and color
	 * 
	 * @param Name  the name of the eagle (string)
	 * @param Age   the age of the eagle (int)
	 * @param Color the color of the eagle (string)
	 */
	public Eagle(String name, int age, String color) {
		super(name, age, color);
	}

	/**
	 * represent eating by string
	 */
	public void eat() {
		System.out.println("The eagle eat a mouse");
	}

	/**
	 * clone the eagle (deep copy)
	 * 
	 * @return the properties of the eagle as new eagle
	 */
	protected Eagle clone() {
		Eagle copy = new Eagle(getName(), getAge(), getColor());
		return copy;
	}

// return String representation of eagle
	/**
	 * toString function format: "eagle,mammal- name: david, age: 2, color: black"
	 */
	public String toString() {
		return "eagle, " + super.toString();
	}

	/**
	 * equals function
	 * 
	 * @param otherEagle the eagle to compare
	 * @return true if the eagles has the same name, age, color and owner (by
	 *         properties)
	 */
	public boolean equals(Eagle otherEagle) {
		if (super.equals((Animal) otherEagle) == true)
			return true;
		return false;
	}
}
