package eu.johannesrave.drawingApp.model;

public class Polygon extends Figure {

	int numberOfSides;
	double lengthOfSides;
	double pseudoradius;

	public Polygon(int x, int y, int numberOfSides, double lengthOfSides) {
		this.numberOfSides = numberOfSides;
		this.lengthOfSides = lengthOfSides;
		this.pseudoradius = Math.sqrt((getArea() / Math.PI));
		this.width = (int) (2 * pseudoradius);
		this.height = (int) (2 * pseudoradius);
	}

	@Override
	public void showInfo() {
		System.out.println("Number of Sides: " + numberOfSides);
		System.out.println("Length of Sides: " + lengthOfSides);
		System.out.println("Circumference: " + getPerimeter());
		System.out.println("Area: " + getArea());
	}

	@Override
	public double getPerimeter() {
		return numberOfSides * lengthOfSides;
	}

	@Override
	public double getArea() {
		return (numberOfSides * lengthOfSides * lengthOfSides) / (4 * Math.tan(Math.PI / numberOfSides));
	}

	// Calculate radius from area of approximate circle:
	// pi*r^2 = area --> sqr(area / pi) = r
	@Override
	public boolean isInside(double clickX, double clickY) {		
		double centerX = this.x + pseudoradius;
		double centerY = this.y + pseudoradius;
		double distance = Math.sqrt((centerX - clickX) * (centerX - clickX) + (centerY - clickY) * (centerY - clickY));
		return (pseudoradius < distance);
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
