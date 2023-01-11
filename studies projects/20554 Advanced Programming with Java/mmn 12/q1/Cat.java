package q1;

public class Cat extends Mammal {
	private Owner owner;

	/**
	 * four-argument constructor initializes cat's name, age, color and owner
	 * 
	 * @param Name  the name of the cat (string)
	 * @param Age   the age of the cat (int)
	 * @param Color the color of the cat (string)
	 * @param owner the owner of the cat
	 */
	public Cat(String name, int age, String color, Owner owner) {
		super(name, age, color);
		this.setOwner(owner);
	}

	/**
	 * return the owner of the cat
	 * 
	 * @return the owner as an object
	 */
	public Owner getOwner() {
		return this.owner;
	}

	/**
	 * set (give) a new owner to the cat
	 * 
	 * @param newOwner the new name of the cat
	 */
	public void setOwner(Owner newOwner) {
		owner = newOwner;
	}

	/**
	 * represent eating by string
	 */
	public void eat() {
		System.out.println("The cat leak some milk");
	}

	/**
	 * clone the cat (deep copy)
	 * 
	 * @return the properties of the cat as new cat
	 */
	protected Cat clone() {
		Cat copy = new Cat(getName(), getAge(), getColor(),
				new Owner(getOwner().getOwnerName(), getOwner().getOwnerPhoneNumber()));
		return copy;
	}

	// return String representation of cat
	/**
	 * toString function format: "cat,mammal- name: david, age: 2, color: black"
	 */
	public String toString() {
		return "cat, " + super.toString() + "; " + this.owner;
	}

	/**
	 * equals function
	 * 
	 * @param otherCat the cat to compare
	 * @return true if the cats has the same name, age, color and owner (by
	 *         properties)
	 */
	public boolean equals(Cat otherCat) {
		if (super.equals((Animal) otherCat) == true && this.owner.equals(otherCat.getOwner()))
			return true;
		return false;
	}
}
