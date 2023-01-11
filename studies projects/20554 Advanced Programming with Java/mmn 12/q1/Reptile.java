package q1;

public abstract class Reptile extends Animal{
	
	/**
	 * three-argument constructor initializes Animal's name, age and color
	 * 
	 * @param newName  the name of the Reptile (string)
	 * @param newAge   the age of the Reptile (int)
	 * @param newColor the color of the Reptile (string)
	 */
	public Reptile (String name, int age, String color) {
		super(name,age,color);
	}
	
	/**
	 * represent crawling by string
	 */
	public void crawl () {
		System.out.println(getName() + " is now crawling");
	}
	
	// return String representation of reptile
		/**
		 * toString function format: "reptile- name: david, age: 2, color: black"
		 */
	public String toString () {
		return "reptile- " + super.toString();
	}

}
