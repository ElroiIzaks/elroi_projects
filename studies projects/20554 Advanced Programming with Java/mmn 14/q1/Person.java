package q1;

public class Person implements Comparable<Person> {

	
	private String id;
	private int yearOfBirth;
	private String lastName;
	private String firstName;

	//construction method
	public Person(String id, String firstName, String lastName, int yearOfBirth) {
		setId(id);
		setName(firstName);
		setLastName(lastName);
		setYearOfBirth(yearOfBirth);
	}

	/**
	 * compare between 2 Persons
	 * Person is bigger from other Person if is id number is larger
	 * any id length will work. the method assume that the 2 Person id's has the same length
	 * @return 0 if equals; -1 if "this" object is smaller; 1 if he is bigger
	 */
	@Override
	public int compareTo(Person otherPerson) {
		for (int i = 0; i < this.id.length(); i++) {
			if (this.id.charAt(i) > otherPerson.id.charAt(i))
				return 1;
			if (this.id.charAt(i) < otherPerson.id.charAt(i))
				return -1;
		}
		return 0;
	}

	//sets methods
	public void setId(String num) {
		this.id = num;
	}

	public void setName(String newName) {
		this.firstName = newName;
	}

	public void setLastName(String newLastName) {
		this.lastName = newLastName;
	}

	public void setYearOfBirth(int year) {
		this.yearOfBirth = year;
	}

	//toString method
	public String toString() {
		return ("id: " + this.id + ", name: " + this.firstName + ", last name: " + this.lastName + ", year of birth: "
				+ this.yearOfBirth);
	}
}
