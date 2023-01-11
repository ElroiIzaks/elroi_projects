package q2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.Arrays;

public class FourInRowController {

	private enum color {//for the corresponding array
		EMPTY, RED, BLUE;
	}

	@FXML
	private AnchorPane drawingAreaAnchorPane;

	@FXML
	private Button button1;

	@FXML
	private Button button2;

	@FXML
	private Button button3;

	@FXML
	private Button button4;

	@FXML
	private Button button5;

	@FXML
	private Button button6;

	@FXML
	private Button button7;

	@FXML
	private Button clearButton;

	private color[][] gameBoard = new color[7][5];//the corresponding array
	private color correntColor; //corrent player

	public void initialize() {
		drawingAreaAnchorPane.getChildren().clear(); // clear the canvas
		
		//disable all buttons
		button1.setDisable(false);
		button2.setDisable(false);
		button3.setDisable(false);
		button4.setDisable(false);
		button5.setDisable(false);
		button6.setDisable(false);
		button7.setDisable(false);
		
		//make lines
		for (int i = 0; i < 6; i++) {
			drawingAreaAnchorPane.getChildren().add(new Line(86.5 + i * 87, 0, 86.5 + i * 87, 247.5));
		}
		for (int i = 0; i < 4; i++) {
			drawingAreaAnchorPane.getChildren().add(new Line(0,196.5-i*49,608,196.5-i*49));
			}

		
		correntColor = color.RED;//initial player
		for (color[] row : gameBoard) //make the array empty
			Arrays.fill(row, color.EMPTY);
	}

	@FXML
	void AbuttonPressed(ActionEvent event) {
		ButtonPressed(1);
	}

	@FXML
	void BbuttonPressed(ActionEvent event) {
		ButtonPressed(2);
	}

	@FXML
	void CbuttonPressed(ActionEvent event) {
		ButtonPressed(3);
	}

	@FXML
	void ClearButtonPressed(ActionEvent event) {
		initialize();//clear all and start over
	}

	@FXML
	void DbuttonPressed(ActionEvent event) {
		ButtonPressed(4);
	}

	@FXML
	void EbuttonPressed(ActionEvent event) {
		ButtonPressed(5);
	}

	@FXML
	void FbuttonPressed(ActionEvent event) {
		ButtonPressed(6);
	}

	@FXML
	void GbuttonPressed(ActionEvent event) {
		ButtonPressed(7);
	}

	private int lowestPlaceOnRow(int rowNumber) {//function to find the lowest free place
		for (int i = 0; i < 5; i++) {
			if (gameBoard[rowNumber - 1][i] == color.EMPTY)
				return i;
		}
		return -1;
	}

	private void changeCorrentColor() { //change the color each turn
		correntColor = (correntColor == color.RED ? color.BLUE : color.RED);
	}

	private void ButtonPressed(int rowNumber) {
		int lowestPlace = lowestPlaceOnRow(rowNumber);

		gameBoard[rowNumber - 1][lowestPlace] = correntColor; //place value in the array
		changeCorrentColor();//change next player
		if (lowestPlace == 4) { //end of row
			switch (rowNumber) { //disable the corresponding button
			case 1:
				button1.setDisable(true);
				break;
			case 2:
				button2.setDisable(true);
				break;
			case 3:
				button3.setDisable(true);
				break;
			case 4:
				button4.setDisable(true);
				break;
			case 5:
				button5.setDisable(true);
				break;
			case 6:
				button6.setDisable(true);
				break;
			case 7:
				button7.setDisable(true);
				break;
			}
		}
		
		//paint the circle
		Color circleColor = correntColor == color.RED ? Color.BLUE : Color.RED;
		Circle newCircle = new Circle(44 + (rowNumber - 1) * 87, 222 - lowestPlace * 49, 23, circleColor);
		drawingAreaAnchorPane.getChildren().add(newCircle);
		checkIfWin(); //check if win in this turn
	}
	
	private void checkIfWin() {
		//check if win orizontaly
		for (int i = 0; i<5;i++)
			for (int j = 0; j<4;j++)
				if (winByOrizontal(gameBoard,j,i) != color.EMPTY) winCeremony(winByOrizontal(gameBoard,j,i));

		//check if win vertacly
		for (int i = 0; i<2;i++)
			for (int j = 0; j<7;j++)
				if (winByVertical(gameBoard,j,i) != color.EMPTY)  winCeremony(winByOrizontal(gameBoard,j,i));

		//check if win diagnoly
		for (int i = 0; i<2;i++)
			for (int j = 0; j<4;j++)
				if (winByDiagnolRight(gameBoard,j,i) != color.EMPTY)  winCeremony(winByOrizontal(gameBoard,j,i));
		
		for (int i = 0; i<2;i++)
			for (int j = 4; j<7;j++)
				if (winByDiagnolLeft(gameBoard,j,i) != color.EMPTY)  winCeremony(winByOrizontal(gameBoard,j,i));
	}
	
	//check if win by orizontal. (x,y) valuse for the lowest and leftiest place
	private color winByVertical(color [][] array, int rowNumber, int lineNumber) {
		if (array[rowNumber][lineNumber] == array[rowNumber][lineNumber+1] &&
			array[rowNumber][lineNumber] == array[rowNumber][lineNumber+2] &&
			array[rowNumber][lineNumber] == array[rowNumber][lineNumber+3])
			return array[rowNumber][lineNumber];
		return color.EMPTY;
	}

	//check if win by vertical. (x,y) valuse for the lowest and leftiest place
	private color winByOrizontal(color [][] array, int rowNumber, int lineNumber) {
		if (array[rowNumber][lineNumber] == array[rowNumber+1][lineNumber] &&
			array[rowNumber][lineNumber] == array[rowNumber+2][lineNumber] &&
			array[rowNumber][lineNumber] == array[rowNumber+3][lineNumber])
			return array[rowNumber][lineNumber];
		return color.EMPTY;
	}
	
	//check if win by diagonal right. (x,y) valuse for the lowest and leftiest place
	private color winByDiagnolRight(color [][] array, int rowNumber, int lineNumber) {
		if (array[rowNumber][lineNumber] == array[rowNumber+1][lineNumber+1] &&
			array[rowNumber][lineNumber] == array[rowNumber+2][lineNumber+2] &&
			array[rowNumber][lineNumber] == array[rowNumber+3][lineNumber+3])
			return array[rowNumber][lineNumber];
		return color.EMPTY;
	}
	
	//check if win by diagonal left. (x,y) valuse for the lowest and leftiest place
	private color winByDiagnolLeft(color [][] array, int rowNumber, int lineNumber) {
		if (array[rowNumber][lineNumber] == array[rowNumber-1][lineNumber+1] &&
			array[rowNumber][lineNumber] == array[rowNumber-2][lineNumber+2] &&
			array[rowNumber][lineNumber] == array[rowNumber-3][lineNumber+3])
			return array[rowNumber][lineNumber];
		return color.EMPTY;
	}
	
	//ceremony when win
	private void winCeremony(color winner) {
		//only button available is clear for start over 
		button1.setDisable(true);
		button2.setDisable(true);
		button3.setDisable(true);
		button4.setDisable(true);
		button5.setDisable(true);
		button6.setDisable(true);
		button7.setDisable(true);
		
		//winner massage
		Text text = new Text((winner == color.BLUE? "blue":"red")+" win!");
		text.setX(235);
		text.setY(135);
		text.setFill(winner == color.BLUE? Color.BLUE:Color.RED);
		text.setStroke(Color.BLACK);
		text.setStyle("-fx-font: 45 arial;");
		drawingAreaAnchorPane.getChildren().add(text);
	}
}
