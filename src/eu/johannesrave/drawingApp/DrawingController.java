package eu.johannesrave.drawingApp;

import eu.johannesrave.drawingApp.model.Drawing;
import eu.johannesrave.drawingApp.model.Figure;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class DrawingController {

    private static final double minWidth = 160;
    private static final double minHeight = 320;
    private static int clickedX;
    private static int clickedY;
    private static int prevX = 0;
    private static int prevY = 0;
    private static Figure widgetFigure = null;
    private static Figure selectedFigure = null;
    private static Shape widgetShape = null;
    private static Node selectedShape = null;

    private static ObservableList<Figure> figures;
    private static int sceneWidth;
    private static int sceneHeight;

    public static BorderPane setup(int sceneWidth, int sceneHeight) {
        // Initialize variables managed list
        DrawingController.sceneWidth = sceneWidth;
        DrawingController.sceneHeight = sceneHeight;

        figures = Drawing.getFigures();

        // Setup root hierarchy
        BorderPane root = new BorderPane();
        root.setMinWidth(minWidth);
        root.setMinHeight(minHeight);

        // Setup central drawingSurface
        Pane drawingSurface = new Pane();
        drawingSurface.setPrefSize(sceneWidth - minWidth, sceneHeight);
        ObservableList<Node> drawnShapes = drawingSurface.getChildren();

        // Setup Sidepanel with listOfShapes and infoBox
        VBox sidepanel = new VBox();
        sidepanel.setPrefWidth(minWidth);

        ListView<String> listOfShapes = new ListView<String>(Drawing.getNames());

        VBox infoBox = new VBox();
        infoBox.setAlignment(Pos.BOTTOM_CENTER);
        infoBox.setPrefHeight(-1);
        infoBox.setSpacing(4);

        VBox.setVgrow(listOfShapes, Priority.ALWAYS);
        VBox.setVgrow(infoBox, Priority.NEVER);

        Label lblIndex = new Label("Index");
		Label lblFigurePosition = new Label("Position");
        Label lblArea = new Label("Area");
        Label lblPerimeter = new Label("Perimeter");
        Label lblMousePosition = new Label("Cursor");

        List<Label> labels = Arrays.asList(
                lblIndex,
				lblFigurePosition,
                lblArea,
                lblPerimeter,
                lblMousePosition);

        for (Label label : labels) {
            label.setAlignment(Pos.CENTER);
            label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            VBox.setMargin(label, new Insets(5));
        }

        infoBox.getChildren().addAll(labels);
        sidepanel.getChildren().addAll(
                listOfShapes,
                infoBox);

        Drawing.setup(drawingSurface);

        // Setup Footer with Tool Buttons
        ToggleGroup tglCreatedShape = new ToggleGroup();

        ToggleButton btnMoveShape = new ToggleButton("Move Shape");
        btnMoveShape.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMoveShape.setToggleGroup(tglCreatedShape);

        ToggleButton btnRectangle = new ToggleButton("New Rectangle");
        btnRectangle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnRectangle.setToggleGroup(tglCreatedShape);

        ToggleButton btnSquare = new ToggleButton("New Square");
        btnSquare.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnSquare.setToggleGroup(tglCreatedShape);

        ToggleButton btnCircle = new ToggleButton("New Circle");
        btnCircle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnCircle.setToggleGroup(tglCreatedShape);

        tglCreatedShape.selectToggle(btnSquare);

        ColorPicker colorpicker = new ColorPicker(Color.AQUAMARINE);
        colorpicker.getStyleClass().add("sidepanel");

        Button btnPopulate = new Button("Populate");
        btnPopulate.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        Button btnDelete = new Button("Remove");
        btnDelete.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        ToggleButton tglStyle = new ToggleButton("Dark Mode");
        tglStyle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        TilePane footer = new TilePane(Orientation.HORIZONTAL);
        footer.setPrefTileWidth(120);
        footer.setPadding(new Insets(10));
        footer.setHgap(10.0);
        footer.setVgap(10.0);
        footer.setAlignment(Pos.CENTER);
        footer.getChildren().addAll(
                btnMoveShape,
                colorpicker,
                btnRectangle,
                btnSquare,
                btnCircle,
                btnPopulate,
                btnDelete,
                tglStyle);

        // Set style classes for CSS
        sidepanel.getStyleClass().add("sidepanel");
        footer.getStyleClass().add("footer");

        // Button Event Handlers
        btnRectangle.setOnAction(m -> {
            System.out.println("Toggled new Shape.");
        });

        btnDelete.setOnAction(e -> {
            if (listOfShapes.getSelectionModel().getSelectedIndex() != -1) {
                int selected = listOfShapes.getSelectionModel().getSelectedIndex();
                Drawing.remove(selected);
                drawnShapes.remove(selected);
            } else {
                System.out.println("Nothing selected, nothing deleted.");
            }
            System.out.println(Drawing.getNames());
        });

        btnPopulate.setOnAction(e -> {
            Drawing.populate(drawingSurface);
            drawnShapes.clear();
            drawnShapes.addAll(Drawing.getShapes());
            System.out.println(Drawing.getNames());
            System.out.println(drawnShapes);
        });

        // Sidepanel Labels update Listener
        listOfShapes.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> obs, Number oldIdx, Number idx) {
                Figure figure = figures.get((int) idx);
                lblIndex.setText("Index: " + idx);
                lblFigurePosition.setText("Position: (" + figure.getX() + ", " + figure.getY() + ")");
                lblArea.setText("Area: " + figure.getArea());
                lblPerimeter.setText("Perimeter: " + figure.getPerimeter());
            }

        });

        root.addEventHandler(MouseEvent.MOUSE_MOVED, m -> {
            lblMousePosition.setText(m.getX() + ", " + m.getY());
        });

        root.addEventHandler(MouseEvent.MOUSE_DRAGGED, m -> {
            lblMousePosition.setText(m.getX() + ", " + m.getY());
        });


        // DarkMode Toggle
        tglStyle.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            String style = DrawingController.class.getResource("style.css").toExternalForm();
            if (isSelected) {
                root.getStylesheets().add(style);
            } else {
                root.getStylesheets().remove(style);
            }
        });


        // Mouse Event Handlers
        root.addEventHandler(MouseEvent.MOUSE_PRESSED, m -> {
            if (m.isSecondaryButtonDown()) return;
            m.consume();
            clickedX = (int) m.getX();
            clickedY = (int) m.getY();
            prevX = (int) m.getX();
            prevY = (int) m.getY();

            // Start to draw new Shape
            if (btnRectangle.isSelected() || btnSquare.isSelected() || btnCircle.isSelected()) {
                System.out.println("Drawing new shape from " + clickedX + ", " + clickedY);
                widgetFigure = Drawing.addRectangle(prevX, prevY, 1, 1, null, Color.BLACK);

                // Draw new shape as dashed rectangular widget
                drawnShapes.clear();
                drawnShapes.addAll(Drawing.getShapes());
                widgetShape = (Shape) drawnShapes.get(drawnShapes.size() - 1);
                widgetShape.getStrokeDashArray().addAll(5d, 10d);

                return;
            }

            // Start to drag existing shape
            if (btnMoveShape.isSelected()) {
                for (int i = figures.size() - 1; i >= 0; i--) {
                    Figure figure = figures.get(i);
                    if (figure.isInside(m.getSceneX(), m.getSceneY())) {
                        listOfShapes.getSelectionModel().clearAndSelect(figures.indexOf(figure));
                        selectedFigure = figure;
                        selectedShape = drawnShapes.get(figures.indexOf(selectedFigure));

                        System.out.println("Clicked on " + selectedFigure.getShapeName());
                        System.out.println("Starting drag at " + prevX + ", " + prevY);
                        return;
                    }
                }
            }

        });
        
        // Deselect all tools on rightclick, select "Move Shape".
        root.addEventHandler(MouseEvent.MOUSE_PRESSED, m -> {
            if (!m.isSecondaryButtonDown()) return;
            tglCreatedShape.selectToggle(btnMoveShape);
 
        });

        root.addEventHandler(MouseEvent.MOUSE_DRAGGED, m -> {
            double deltaX = m.getX() - prevX;
            double deltaY = m.getY() - prevY;
            // Drawing new shape
            if (widgetFigure != null) {
                root.setCursor(Cursor.HAND);

                // Make widget direction independent
                double width = Math.abs(clickedX - m.getX());
                double height = Math.abs(clickedY - m.getY());
                double X = Math.min(clickedX, m.getX());
                double Y = Math.min(clickedY, m.getY());

                // Adjust widget if drawing circle or square
                if (btnSquare.isSelected() || btnCircle.isSelected()) {
                    width = Math.max(width, height);
                    height = width;
                    if (clickedX - m.getX() > 0) {
                        X = clickedX - width;
                    }
                    if (clickedY - m.getY() > 0) {
                        Y = clickedY - height;
                    }
                }

                widgetFigure.setX((int) X);
                widgetFigure.setY((int) Y);
                widgetFigure.setWidth((int) width);
                widgetFigure.setHeight((int) height);

                Drawing.remove(widgetFigure);
                Drawing.add(widgetFigure);

                drawnShapes.clear();
                drawnShapes.addAll(Drawing.getShapes());

                widgetShape = (Shape) drawnShapes.get(drawnShapes.size() - 1);
                widgetShape.getStrokeDashArray().addAll(5d, 10d);
            }

            // Dragging existing shape
            if (selectedFigure != null) {
                root.setCursor(Cursor.CLOSED_HAND);

                selectedFigure.setX((int) deltaX + selectedFigure.getX());
                selectedFigure.setY((int) deltaY + selectedFigure.getY());

                selectedShape.relocate(deltaX + selectedFigure.getX(), deltaY + selectedFigure.getY());
                
                //Update position label while dragging
				lblFigurePosition.setText("Position: (" + selectedFigure.getX() + ", " + selectedFigure.getY() + ")");

                prevY = (int) m.getY();
                prevX = (int) m.getX();
            }
        });

        root.addEventHandler(MouseEvent.MOUSE_RELEASED, m -> {

            if (widgetFigure != null) {
                Drawing.remove(widgetFigure);
                if (tglCreatedShape.getSelectedToggle() == btnRectangle) {
                    Drawing.addRectangle(
                            widgetFigure.getX(),
                            widgetFigure.getY(),
                            widgetFigure.getWidth(),
                            widgetFigure.getHeight(),
                            colorpicker.getValue(), null);
                } else if (tglCreatedShape.getSelectedToggle() == btnSquare) {
                    Drawing.addSquare(
                            widgetFigure.getX(),
                            widgetFigure.getY(),
                            widgetFigure.getWidth(),
                            colorpicker.getValue(), null);
                } else if (tglCreatedShape.getSelectedToggle() == btnCircle) {
                    Drawing.addCircle(
                            widgetFigure.getX(),
                            widgetFigure.getY(),
                            widgetFigure.getWidth(),
                            colorpicker.getValue(), null);
                }

                drawnShapes.clear();
                drawnShapes.addAll(Drawing.getShapes());
                colorpicker.setValue(Color.color(Math.random(), Math.random(), Math.random()));
            }

            System.out.println("Mouse released.");
            selectedFigure = null;
            selectedShape = null;
            widgetFigure = null;
            widgetShape = null;
            root.setCursor(Cursor.DEFAULT);
        });

        // Key Handlers
        // Derive Stage from KeyEvent, then close application with ESC.
        root.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent e) -> {
            if (KeyCode.ESCAPE == e.getCode()) {
                Node source = (Node) e.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });

        // Construct DrawingController and return
        drawnShapes.clear();
        drawnShapes.addAll(Drawing.getShapes());

        root.setCenter(drawingSurface);
        root.setRight(sidepanel);
        root.setBottom(footer);

        return root;
    }
}
