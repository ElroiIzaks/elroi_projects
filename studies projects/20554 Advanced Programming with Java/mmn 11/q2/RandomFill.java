package q2;
/*
 *creator: Elroi Izaks
 *email: elroiizaks@gmail.com
 *version: 9/4/22
 */

//RandomFill class offer us an animation table with option to change "fill" with a matter of pressing a button
// The user can click a button to regenerate the random order of fills' squares.

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RandomFill extends Application {
	public void start(Stage stage) throws Exception {
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("RandomFill.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("RandomFill");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}