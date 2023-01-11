package q1;

public class Chicken extends Bird {
	private Owner owner;

	/**
	 * four-argument constructor initializes chicken's name, age, color and owner
	 * 
	 * @param Name  the name of the chicken (string)
	 * @param Age   the age of the chicken (int)
	 * @param Color the color of the chicken (string)
	 * @param owner the owner of the chicken
	 */
	public Chicken(String name, int age, String color, Owner owner) {
		super(name, age, color);
		this.setOwner(owner);
	}

	/**
	 * return the owner of the chicken
	 * 
	 * @return the owner as an object
	 */
	public Owner getOwner() {
		return this.owner;
	}

	/**
	 * set (give) a new owner to the chicken
	 * 
	 * @param newOwner the new name of the chicken
	 */
	public void setOwner(Owner newOwner) {
		owner = newOwner;
	}

	/**
	 * represent eating by string
	 */
	public void eat() {
		System.out.println("The chicken eat a seeds");
	}

	/**
	 * clone the chicken (deep copy)
	 * 
	 * @return the properties of the chicken as new chicken
	 */
	protected Chicken clone() {
		Chicken copy = new Chicken(getName(), getAge(), getColor(),
				new Owner(getOwner().getOwnerName(), getOwner().getOwnerPhoneNumber()));
		return copy;
	}

	// return String representation of chicken
	/**
	 * toString function format: "chicken,bird- name: david, age: 2, color: black"
	 */
	public String toString() {
		return "chicken, " + super.toString() + "; " + this.owner;
	}

	/**
	 * equals function
	 * 
	 * @param otherChicken the chicken to compare
	 * @return true if the chickens has the same name, age, color and owner (by
	 *         properties)
	 */
	public boolean equals(Chicken otherChicken) {
		if (super.equals((Animal) otherChicken) == true && this.owner.equals(otherChicken.getOwner()))
			return true;
		return false;
	}
}
