
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Dictionary implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<DictionaryWord> words;

	//constructor
	public Dictionary() {
		words = new ArrayList<DictionaryWord>();
	}

	//get method
	public ArrayList<DictionaryWord> getWordList() {
		return words;
	}

	/**
	 * adding dictionearyWord by dictionearyWord's parameters
	 * @param word the new dictionearyWord's word-field
	 * @param meaning the new dictionearyWord's meaning
	 */
	public void newDictionaryWord(String word, String meaning) {
		words.add(new DictionaryWord(word, meaning));
		words.sort(null);
	}

	/**
	 * adding dictionearyWord by other dictionearyWord
	 * @param dictWord the new dictionearyWord 
	 */
	public void newDictionaryWord(DictionaryWord dictWord) {
		words.add(dictWord);
		words.sort(null);
	}

	/**
	 * delete a dictionearyWord
	 * @param word the word to delete
	 */
	public void deleteDictionaryWord(DictionaryWord word) {
		words.remove(word);
	}

	/**
	 * update a dictionearyWord (known by the word's field)
	 * @param word the word-field of the dictionearyWord
	 * @param newMeaning the new meaning
	 */
	public void updateDictionaryWord(DictionaryWord word, String newMeaning) {
		words.get(words.indexOf(word)).changeMeaning(newMeaning);
	}

	/**
	 * search a dictionearyWord (known by the word-field)
	 * @param word the dictionearyWord word-field
	 * @return the dictionearyWord as object
	 */
	public DictionaryWord searchDictionaryWord(String word) {
		Iterator<DictionaryWord> iterator = words.iterator();
		DictionaryWord temp;
		while (iterator.hasNext()) {
			temp = iterator.next();
			if (temp.getWord().equals(word))
				return temp;
		}
		return null;

	}

}
