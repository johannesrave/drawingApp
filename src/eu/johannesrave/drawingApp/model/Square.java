package eu.johannesrave.drawingApp.model;

import javafx.scene.paint.Paint;

public class Square extends Rectangle {
	public Square(int x, int y, int side, Paint fill, Paint stroke) {
		super(x, y, side, side, fill, stroke);
		this.shape = "Square";
	}
};
