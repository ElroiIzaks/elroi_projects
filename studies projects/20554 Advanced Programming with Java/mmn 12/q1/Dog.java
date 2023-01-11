package q1;

public class Dog extends Mammal {
	private Owner owner;

	/**
	 * four-argument constructor initializes dog's name, age, color and owner
	 * 
	 * @param Name  the name of the dog (string)
	 * @param Age   the age of the dog (int)
	 * @param Color the color of the dog (string)
	 * @param owner the owner of the dog
	 */
	public Dog(String name, int age, String color, Owner owner) {
		super(name, age, color);
		this.setOwner(owner);
	}

	/**
	 * return the owner of the dog
	 * 
	 * @return the owner as an object
	 */
	public Owner getOwner() {
		return this.owner;
	}

	/**
	 * set (give) a new owner to the dog
	 * 
	 * @param newOwner the new name of the dog
	 */
	public void setOwner(Owner newOwner) {
		owner = newOwner;
	}

	/**
	 * represent eating by string
	 */
	public void eat() {
		System.out.println("The dog eat a bone");
	}

	/**
	 * clone the dog (deep copy)
	 * 
	 * @return the properties of the dog as new dog
	 */
	protected Dog clone() {
		Dog copy = new Dog(getName(), getAge(), getColor(),
				new Owner(getOwner().getOwnerName(), getOwner().getOwnerPhoneNumber()));
		return copy;
	}

	// return String representation of dog
	/**
	 * toString function format: "dog,mammal- name: david, age: 2, color: black"
	 */
	public String toString() {
		return "dog, " + super.toString() + "; " + this.owner;
	}

	/**
	 * equals function
	 * 
	 * @param otherDog the dog to compare
	 * @return true if the dogs has the same name, age, color and owner (by
	 *         properties)
	 */
	public boolean equals(Dog otherDog) {
		if (super.equals((Animal) otherDog) == true && this.owner.equals(otherDog.getOwner()))
			return true;
		return false;
	}
}
