package eu.johannesrave.drawingApp.model;

import javafx.scene.paint.Paint;

public abstract class Figure {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Paint fill;
	protected Paint stroke;
	protected String shape;

	public Figure() {}
	
	public Figure(int x, int y, Paint fill, Paint stroke) {
		super();
		this.x = x;
		this.y = y;
		this.fill = fill;
		this.stroke = stroke;
	}


	public void showInfo() {
		System.out.println(shape + " at " + x + ", " + y);
		System.out.println("Circumference: " + getPerimeter());
		System.out.println("Area: " + getArea());
	}

	public abstract boolean isInside(double x, double y);
	
	public String getShapeName(){
		return shape;		
	};

	public abstract double getPerimeter();

	public abstract double getArea();

	
	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}
	
	public abstract void setWidth(int width);

	public int getHeight() {
		return height;
	}

	public abstract void setHeight(int height);

	public Paint getFill() {
		return fill;
	}

	public void setFill(Paint fill) {
		this.fill = fill;
	}

	public Paint getStroke() {
		return stroke;
	}

	public void setStroke(Paint stroke) {
		this.stroke = stroke;
	}
};
