import java.io.Serializable;

public class DictionaryWord extends Object implements Comparable<DictionaryWord>, Serializable{
	 
	private static final long serialVersionUID = 1L;
	private String word;
	private String meaning;
	
	//constructors
	public DictionaryWord (String word, String meaning) {
		this.word = word;
		this.meaning = meaning;
	}
	
	/**
	 * change the meaning of the word
	 * @param newMeaning the new meaning
	 */
	public void changeMeaning (String newMeaning) {
		this.meaning = newMeaning;
	}
	
	/**
	 * compare by comparing the words fields
	 * @param otherWord the word to compare with
	 * @return integer, by regular compare method
	 */
	public int compareTo (DictionaryWord otherWord) {
		return this.word.compareTo(otherWord.word);
	}
	
	//gets methods
	public String getWord () {
		return this.word;
	}
	
	public String getMeaning () {
		return this.meaning;
	}
	
	//sets methods
	public void setWord (String str) {
		this.word = str;
	}
	
	public void setMeaning (String str) {
		this.meaning = str;
	}
	
	//toString method
	public String toString () {
		return word + " - " + meaning;
	}
}
