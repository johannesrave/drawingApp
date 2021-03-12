package eu.johannesrave.drawingApp.model;

import javafx.scene.paint.Paint;

public class Rectangle extends Figure {

	public Rectangle(int x, int y, int width, int height, Paint fill, Paint stroke) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fill = fill;
		this.stroke = stroke;
		this.shape = "Rectangle";
	}

	@Override
	public double getPerimeter() {
		return width + width + height + height;
	}

	@Override
	public double getArea() {
		return width * height;
	}

	@Override
	public boolean isInside(double x, double y) {
		return ((this.x < x && x < this.x + this.width) && (this.y < y && y < this.y + this.height));
	}

	@Override
	public void setWidth(int width) {
		this.width = width;		
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
		
	}
	
};
