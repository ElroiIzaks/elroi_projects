package q1;

public class Turtle extends Reptile {
	private Owner owner;

	/**
	 * four-argument constructor initializes turtle's name, age, color and owner
	 * 
	 * @param Name  the name of the turtle (string)
	 * @param Age   the age of the turtle (int)
	 * @param Color the color of the turtle (string)
	 * @param owner the owner of the turtle
	 */
	public Turtle(String name, int age, String color, Owner owner) {
		super(name, age, color);
		this.setOwner(owner);
	}

	/**
	 * return the owner of the turtle
	 * 
	 * @return the owner as an object
	 */
	public Owner getOwner() {
		return this.owner;
	}

	/**
	 * set (give) a new owner to the turtle
	 * 
	 * @param newOwner the new name of the turtle
	 */
	public void setOwner(Owner newOwner) {
		owner = newOwner;
	}

	/**
	 * represent eating by string
	 */
	public void eat() {
		System.out.println("The turtle eat a carrot");
	}

	/**
	 * clone the turtle (deep copy)
	 * 
	 * @return the properties of the turtle as new turtle
	 */
	protected Turtle clone() {
		Turtle copy = new Turtle(getName(), getAge(), getColor(),
				new Owner(getOwner().getOwnerName(), getOwner().getOwnerPhoneNumber()));
		return copy;
	}

	// return String representation of turtle
	/**
	 * toString function format: "turtle,mammal- name: david, age: 2, color: black"
	 */
	public String toString() {
		return "turtle, " + super.toString() + "; " + this.owner;
	}

	/**
	 * equals function
	 * 
	 * @param otherTurtle the turtle to compare
	 * @return true if the turtles has the same name, age, color and owner (by
	 *         properties)
	 */
	public boolean equals(Turtle otherTurtle) {
		if (super.equals((Animal) otherTurtle) == true && this.owner.equals(otherTurtle.getOwner()))
			return true;
		return false;
	}
}
