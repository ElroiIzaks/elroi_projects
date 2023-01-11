package q1;

public abstract class Animal implements Cloneable {
	private String name;
	private int age;
	private String color;

	/**
	 * three-argument constructor initializes Animal's name, age and color
	 * 
	 * @param newName  the name of the animal (string)
	 * @param newAge   the age of the animal (int)
	 * @param newColor the color of the animal (string)
	 */
	public Animal(String newName, int newAge, String newColor) {
		this.name = newName;
		this.age = newAge;
		this.color = newColor;
	}

	/**
	 * represent sleeping by string
	 */
	public void sleep() {
		System.out.println(this.name + ": z z z");
	}

//abstract functions
	public abstract void eat();

	abstract protected Animal clone();

	/**
	 * set (give) a new name to the animal
	 * 
	 * @param newName the new name of the animal
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * set a new age to the animal
	 * 
	 * @param newAge the new age of the animal
	 */
	public void setAge(int newAge) {
		this.age = newAge;
	}

	/**
	 * set a new color to the animal
	 * 
	 * @param newName the new color of the animal
	 */
	public void setColor(String newColor) {
		this.color = newColor;
	}

	/**
	 * return the name of the animal
	 * 
	 * @return the name of the animal
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * return the age of the animal
	 * 
	 * @return the age of the animal
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * return the color of the animal
	 * 
	 * @return the color of the animal
	 */
	public String getColor() {
		return this.color;
	}

// return String representation of animal
	/**
	 * toString function format: "name: david, age: 2, color: black"
	 */
	public String toString() {
		return "name: " + this.name + ", age: " + this.age + ", color: " + this.color;
	}

	/**
	 * equals function
	 * 
	 * @param otherAnimal the animal to compare
	 * @return true if the animals have the same name, age and color
	 */
	public boolean equals(Animal otherAnimal) {
		if (this.name == otherAnimal.name && this.age == otherAnimal.age && this.color == otherAnimal.color)
			return true;
		return false;
	}

}
