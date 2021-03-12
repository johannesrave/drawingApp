package eu.johannesrave.drawingApp.model;

import javafx.scene.paint.Paint;

public class Circle extends Figure {

	private double radius;

	public Circle(int x, int y, int diameter, Paint fill, Paint stroke) {
		this.x = x;
		this.y = y;
		this.radius = diameter/2;
		this.width = diameter;
		this.height = diameter;
		this.fill = fill;
		this.stroke = stroke;
		this.shape = "Circle";
	}

	@Override
	public double getPerimeter() {
		return Math.PI * 2 * radius;
	}

	@Override
	public double getArea() {
		return Math.PI * (Math.pow(radius, 2));
	}
	
	public double getRadius() {
		return this.radius;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public boolean isInside(double clickX, double clickY) {
		double centerX = this.x + radius;
		double centerY = this.y + radius;
		double distance = Math.sqrt(Math.pow((centerX - clickX), 2) + Math.pow((centerY - clickY), 2));
		System.out.println("Center: " + centerX + ", " + centerY);
		System.out.println("Distance: " + distance);
		
		return (radius > distance);
	}

	@Override
	public void setWidth(int width) {
		this.width = width;		
		this.radius = width/2;		
	}

	@Override
	public void setHeight(int height) {
		this.height = height;		
		this.radius = height/2;		
	}
}
