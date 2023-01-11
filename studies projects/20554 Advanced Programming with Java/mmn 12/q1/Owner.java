package q1;

public class Owner {
	private String name;
	private String phoneNumber;

	/**
	 * two-argument constructor initializes owner's name and phone number
	 * 
	 * @param Name        the name of the owner (string)
	 * @param phoneNumber the phone number of the owner (string
	 */
	public Owner(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * return the name of the owner
	 * 
	 * @return the name of the owner
	 */
	public String getOwnerName() {
		return this.name;
	}

	/**
	 * set a new name to the owner
	 * 
	 * @param newName the new name of the owner
	 */

	public void setOwnerName(String newName) {
		this.name = newName;
	}

	/**
	 * return the phone number of the owner
	 * 
	 * @return the phone number of the owner
	 */
	public String getOwnerPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * set (change for) a new phone number to the owner
	 * 
	 * @param newName the new phone number of the owner
	 */
	public void setOwnerPhoneNumber(String newPhoneNumber) {
		this.phoneNumber = newPhoneNumber;
	}

	// return String representation of owner
	/**
	 * toString function format: "owner- name: david, phoneNumber: 054-541-7528"
	 */
	public String toString() {
		return "Owner- name: " + this.name + " ,phoneNumber: " + this.phoneNumber;
	}
	
	/**
	 * equals function
	 * 
	 * @param otherOwner the owner to compare
	 * @return true if the owners have the same name and phone numbers
	 */
	public boolean equals(Owner otherOwner) {
		if (this.name == otherOwner.name && this.phoneNumber == otherOwner.phoneNumber)
			return true;
		return false;
	}
}
