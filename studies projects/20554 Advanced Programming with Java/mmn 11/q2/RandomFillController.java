package q2;
/*
 *creator: Elroi Izaks
 *email: elroiizaks@gmail.com
 *version: 9/4/22
 */

// RandomFillController fill random squares based on a button pressed by the user.

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class RandomFillController {

	@FXML
	private Canvas canvas;
	private GraphicsContext gc;

	private static final int NUM_OF_PERCENTAGE = 10;
	private static final int NUM_OF_PIXELS = 10;

	@FXML
	private void initialize() {// for making the net
		double canvasWidth = canvas.getWidth();
		double canvasHeight = canvas.getHeight();
		gc = canvas.getGraphicsContext2D();

		gc.clearRect(0, 0, canvasWidth, canvasHeight); // the background

		for (int i = 0; i < canvasWidth; i++)// vertical lines
			gc.strokeLine(i * NUM_OF_PIXELS, 0, 0 + i * NUM_OF_PIXELS, canvasHeight - (canvasHeight % NUM_OF_PIXELS));
		for (int i = 0; i < canvasHeight; i++)// horizontal lines
			gc.strokeLine(0, i * NUM_OF_PIXELS, canvasWidth - (canvasWidth % NUM_OF_PIXELS), 0 + i * NUM_OF_PIXELS);
		//the last parameters- for not to "slide" outside the canvas

	}

	// fillRandomllyButtonPressed is triggered by a user click on the fill in
	// Randomly button.
	// It fill random (NUM_OF_PERCENT)% of squares from the screen
	@FXML
	private void fillRandomlyButtonPressed(ActionEvent event) {

		gc = canvas.getGraphicsContext2D();
		this.initialize(); // clean the screen

		int numOfSquaresInWidth = ((int) canvas.getWidth() / NUM_OF_PIXELS);
		int numOfSquaresInHeight = ((int) canvas.getHeight() / NUM_OF_PIXELS);
		int totalNumOfSquares = numOfSquaresInHeight * numOfSquaresInWidth;
		int numOfPainted = (int) (totalNumOfSquares * (NUM_OF_PERCENTAGE / 100.0));

		boolean arr[] = new boolean[totalNumOfSquares];

		// fill the first (NUM_OF_PERCENT)% squares
		for (int i = 0; i < numOfPainted; i++)
			arr[i] = true;

		int random;
		for (int i = 1; i <= numOfPainted; i++) { // Switch the first squares with others
			random = (numOfPainted - i) + (int) (Math.random() * (totalNumOfSquares - numOfPainted + i));
			boolean temp = arr[random];
			arr[random] = arr[numOfPainted - i];
			arr[numOfPainted - i] = temp;
		}

		for (int i = 0; i < totalNumOfSquares; i++) { // paint the "chosen" squares
			if (arr[i] == true) {
				int rowNum = i % numOfSquaresInWidth;
				gc.fillRect(rowNum * NUM_OF_PIXELS, ((i - rowNum) / numOfSquaresInWidth) * NUM_OF_PIXELS, NUM_OF_PIXELS,
						NUM_OF_PIXELS);
			}
		}
	}
}