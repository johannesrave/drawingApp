package eu.johannesrave.drawingApp.model;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public interface IShapeAdapter {
	
	static Shape convertToShape(Figure figure){
		
		Shape shape = null;

		switch (figure.getShapeName()) {
			case "Rectangle":
				shape = new Rectangle(
						figure.getX(),
						figure.getY(),
						figure.getWidth(),
						figure.getHeight());
				break;
			case "Square":
				shape = new Rectangle(
						figure.getX(),
						figure.getY(),
						figure.getWidth(),
						figure.getWidth());

				break;
			case "Circle":
				shape = new Circle(
						figure.getX() + figure.getWidth() / 2,
						figure.getY() + figure.getWidth() / 2,
						figure.getWidth() / 2);
				break;
		}
		
		System.out.println("Adapting a " + figure.getShapeName());
		System.out.println(shape);
		assert shape != null;
		shape.setFill(figure.getFill());
		shape.setStroke(figure.getStroke());
		return shape;
	}
}
