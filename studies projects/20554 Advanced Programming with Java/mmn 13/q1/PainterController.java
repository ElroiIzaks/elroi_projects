package q1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;

public class PainterController {

	@FXML
	private RadioButton blackRadioButton;

	/*all colors*/
	@FXML
	private ToggleGroup colorToggleGroup;

	@FXML
	private RadioButton redRadioButton;

	@FXML
	private RadioButton greenRadioButton;

	@FXML
	private RadioButton blueRadioButton;

	@FXML
	private RadioButton lineButton;

	/*all shapes*/
	@FXML
	private ToggleGroup shapeToggleGroup;

	@FXML
	private RadioButton rectangleButton;

	@FXML
	private RadioButton circleButton;

	@FXML
	private Button filledButton;

	@FXML
	private Button undoButton;

	@FXML
	private Button clearButton;

	@FXML
	private AnchorPane drawingAreaAnchorPane;

	// instance variables for managing Painter state
	private Paint brushColor = Color.BLACK; // drawing color
	
	/*for setting the shapes*/
	private double tempX;
	private double tempY;
	
	private String shape = "line"; // shape
	private boolean filled = true; //filled/unfilled
	
	// set user data for the RadioButtons
	public void initialize() {
		// user data on a control can be any Object
		blackRadioButton.setUserData(Color.BLACK);
		redRadioButton.setUserData(Color.RED);
		greenRadioButton.setUserData(Color.GREEN);
		blueRadioButton.setUserData(Color.BLUE);
		lineButton.setUserData("line");
		circleButton.setUserData("circle");
		rectangleButton.setUserData("rectangle");
	}

	@FXML
	private void clearButtonPressed(ActionEvent event) {
		drawingAreaAnchorPane.getChildren().clear(); // clear the canvas
	}

	// handles color RadioButton's ActionEvents
	@FXML
	private void colorRadioButtonSelected(ActionEvent e) {
		// user data for each color RadioButton is the corresponding Color
		brushColor = (Color) colorToggleGroup.getSelectedToggle().getUserData();
	}

	//for knowing the start position
	@FXML
	void drawingAreaMousePressed(MouseEvent event) {
		tempX = event.getX();
		tempY = event.getY();
		
		//for cleaning. give the option to sea the shape while dragging
		drawingAreaAnchorPane.getChildren().add(new Circle(0,0,0));
	}

	// handles drawingArea's onMouseDragged MouseEvent
	@FXML
	private void drawingAreaMouseDragged(MouseEvent e) {
		switch (shape) { //what shape where chosen
		case "circle":
			Circle newCircle = new Circle( (e.getX()+tempX)/2 , (e.getY()+tempY)/2, new Point2D(tempX, tempY).distance(e.getX(), e.getY())/2, brushColor);
			newCircle.setStroke(brushColor);
			newCircle.setStrokeWidth(5);
			if (!filled) newCircle.setFill(null);
			drawingAreaAnchorPane.getChildren().add(newCircle);	
			break;
			
		case "line":
			Line newLine = new Line(tempX, tempY, e.getX(), e.getY());
			newLine.setStroke(brushColor);
			newLine.setStrokeWidth(5);
			newLine.setStrokeLineCap(StrokeLineCap.ROUND);
			drawingAreaAnchorPane.getChildren().add(newLine);	
			break;			
		
		case "rectangle":
			Rectangle newRectangle = new Rectangle(smallest(tempX,e.getX()),smallest(tempY,e.getY()), Math.abs(e.getX()-tempX), Math.abs(e.getY()-tempY));
			newRectangle.setStroke(brushColor);
			newRectangle.setFill(brushColor);
			newRectangle.setStrokeWidth(5);
			if (!filled) newRectangle.setFill(null);
			drawingAreaAnchorPane.getChildren().add(newRectangle);	
			break;
		}
		
		int count = drawingAreaAnchorPane.getChildren().size(); //cleaning while dragging
			drawingAreaAnchorPane.getChildren().remove(count - 2);
	
	}

	@FXML
	void filledButtonPressed(ActionEvent event) {
		filledButton.setText(filled?"unfilled":"filled");//change the text on the button
		filled = !filled; //change the value
	}

	// handles shape RadioButton's ActionEvents
	@FXML
	private void shapeButtonSelected(ActionEvent e) {
		// user data for each shape RadioButton is the corresponding shape
		shape = (String) shapeToggleGroup.getSelectedToggle().getUserData();
	}

	@FXML
	// handles Undo Button's ActionEvents
	private void undoButtonPressed(ActionEvent event) {
		int count = drawingAreaAnchorPane.getChildren().size();

		// if there are any shapes remove the last one added
		if (count > 0)
			drawingAreaAnchorPane.getChildren().remove(count - 1);
	}
	
	//the smallest from the two doubles
	private double smallest (double a,double b) {
		if (a>b) return b;
		return a;
	}
}
