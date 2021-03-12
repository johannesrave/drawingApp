package eu.johannesrave.drawingApp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class Drawing {
    final private static ObservableList<Figure> figures = FXCollections.observableArrayList();
    final private static ObservableList<Shape> shapes = FXCollections.observableArrayList();
    final private static ObservableList<String> names = FXCollections.observableArrayList();

    public static Figure add(Figure figure) {
        figures.add(figure);
        shapes.add(IShapeAdapter.convertToShape(figure));
        names.add(figure.getShapeName());
        return figure;
    }

    public static void remove(Figure figure) {
        remove(figures.indexOf(figure));
    }

    public static void remove(int idx) {
        if (figures.size() < 1) {
            return;
        } else {
            shapes.remove(idx);
            names.remove(idx);
            figures.remove(idx);
        }
    }

    public static ObservableList<Figure> getFigures() {
        return figures;
    }

    public static ObservableList<Shape> getShapes() {
        return shapes;
    }

    public static ObservableList<String> getNames() {
        return names;
    }

    public static Figure getFigureAtIndex(int idx) {
        return figures.get(idx);
    }

    public static void setup(Pane drawingSurface) {
        populate(drawingSurface);
    }

    public static void populate(Pane drawingSurface) {
        double width = drawingSurface.getWidth();
        double height = drawingSurface.getHeight();
        if (width == 0) width = 1600;
        if (height == 0) height = 900;

        int rectWidth = randomUpTo(width) / 3;
        int rectHeight = randomUpTo(height) / 3;
        int rectX = randomUpTo(width - rectWidth);
        int rectY = randomUpTo(height - rectHeight);

        int circleWidth = randomUpTo(width) / 3;
        int circleX = randomUpTo(width - circleWidth);
        int circleY = randomUpTo(height - circleWidth);

        Drawing.add(new Rectangle(rectX, rectY, rectWidth, rectHeight, randomColor(), null));

        Drawing.add(new Circle(circleX, circleY, circleWidth, randomColor(), null));
    }

    private static Color randomColor() {
        return Color.color(Math.random(), Math.random(), Math.random());
    }

    private static int randomUpTo(double number) {
        return (int) Math.ceil(number * Math.random());
    }

    public static Figure addRectangle(int x, int y, int width, int height, Paint fill, Paint stroke) {
        return Drawing.add(new Rectangle(x, y, width, height, fill, stroke));
    }

    public static Figure addSquare(int x, int y, int width, Paint fill, Paint stroke) {
        return Drawing.add(new Square(x, y, width, fill, stroke));
    }

    public static Figure addCircle(int x, int y, int diameter, Paint fill, Paint stroke) {
        return Drawing.add(new Circle(x, y, diameter, fill, stroke));
    }

}
