package eu.johannesrave.drawingApp.model;

public class Ellipse extends Figure {

	private double axis1;
	private double axis2;

	public Ellipse(int x, int y, double axis1, double axis2) {
		this.x = x;
		this.y = y;
		this.axis1 = axis1;
		this.axis2 = axis2;	
	}

	@Override
	public void showInfo() {
		System.out.println("Axis 1: " + axis1);
		System.out.println("Axis 2: " + axis2);
		System.out.println("Circumference: " + getPerimeter());
		System.out.println("Area: " + getArea());
	}

	public double getPerimeter() {
		return (double)2 * Math.PI * Math.sqrt((axis1 * axis1 + axis2 * axis2) / (2 * 1.0));
	}

	public double getArea() {
		return (double) Math.PI * axis1 * axis2;
	}

	public double getAxis1() {
		return axis1;
	}

	public void setAxis1(double axis1) {
		this.axis1 = axis1;
	}

	public double getAxis2() {
		return axis2;
	}

	public void setAxis2(double axis2) {
		this.axis2 = axis2;
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
}
