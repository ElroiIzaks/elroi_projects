import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DictionaryController {

	private Dictionary dict = new Dictionary();
	private ObservableList<String> observableList = FXCollections.observableArrayList();

	@FXML
	private TextField addWordText;

	@FXML
	private TextField addMeaningText;

	@FXML
	private Button addButton;

	@FXML
	private TextField deleteWordText;

	@FXML
	private Button deleteButton;

	@FXML
	private TextField searchWordText;

	@FXML
	private Button searchButton;

	@FXML
	private Button loadFileButton;

	@FXML
	private Button saveFileButton;

	@FXML
	private TextField fileText;

	@FXML
	private ListView<String> listView;

	
	@FXML
	public void initialize() {
		//clear all the current shown words
		observableList.clear();
		
		//add all the words
		for (DictionaryWord word : dict.getWordList())
			observableList.add(word.toString());
			
		listView.setItems(observableList);
	}

	//add a word to the list (sorted) with the given-values  
	@FXML
	void addButtonPressed(ActionEvent event) {
		if (addWordText.getText().length() > 0 && addMeaningText.getText().length() > 0) {
			dict.newDictionaryWord(addWordText.getText(), addMeaningText.getText());
			initialize();
			addWordText.setText("");
			addMeaningText.setText("");
		}
	}

	//delete from the list, the line with the word value equals to the given value.
	//if non equals word is exist, nothing will happen
	@FXML
	void deleteButtonPressed(ActionEvent event) {
		if (deleteWordText.getText().length() > 0 && dict.searchDictionaryWord(deleteWordText.getText()) != null) {
			dict.deleteDictionaryWord(dict.searchDictionaryWord(deleteWordText.getText()));
			initialize();
			deleteWordText.setText("");
		}
	}

	//search a word from the list (by his word's value) and show is meaning in a massage field.
	//nothing will happen if the word is not exist
	
	@FXML
	void searchButtonPressed(ActionEvent event) {
		if (searchWordText.getText().length() > 0 && dict.searchDictionaryWord(searchWordText.getText()) != null) {
			JOptionPane.showMessageDialog(null, dict.searchDictionaryWord(searchWordText.getText()));
			searchWordText.setText("");
		}
	}

	//load a file with Serializatied dictionary
	//if no file with this name is exist, a massage will appear
	@FXML
	void loadFileButtonPressed(ActionEvent event) {
		if (fileText.getText().length() > 0) {
				ObjectInputStream input;
				try {
					input = new ObjectInputStream(new FileInputStream(fileText.getText()));
					Dictionary temp = (Dictionary) input.readObject();
					for (DictionaryWord word : temp.getWordList()) {
						dict.newDictionaryWord(word);
					}
					fileText.setText("");
					initialize();
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "file :\"" + fileText.getText() + "\" not found");
					fileText.setText("");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}			
		}
	}

	//save a file with the given name as Serializatied dictionary

	@FXML
	void saveFileButtonPressed(ActionEvent event) {
		if (fileText.getText().length() > 0) {
				FileOutputStream fo;
				try {
					fo = new FileOutputStream(fileText.getText());
					ObjectOutputStream output = new ObjectOutputStream(fo);
					output.writeObject(dict);
					output.flush();
					output.close();
					fo.close();
					fileText.setText("");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
