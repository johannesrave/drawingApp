package eu.johannesrave.drawingApp.model;

import javafx.scene.paint.Paint;

public class Triangle extends Figure {

	private double side1;
	private double side2;
	private double side3;

	public Triangle(int x, int y, int side, Paint fill, Paint stroke) {
		this.x = x;
		this.y = y;
		this.side1 = side;
		this.side2 = side;
		this.side3 = side;
		this.width = side;
		this.height = side;
		this.shape = this.getClass().getSimpleName();

	}

	@Override
	public double getPerimeter() {
		return side1 + side2 + side3;
	}

	@Override
	public double getArea() {
		double s = (side1+side2+side3)/2;       
        return Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
	}

	@Override
	public boolean isInside(double x, double y) {
		// TODO
		return false;
	}

	@Override
	public void setWidth(int width) {
		// TODO
		
	}

	@Override
	public void setHeight(int height) {
		// TODO
		
	}
};
