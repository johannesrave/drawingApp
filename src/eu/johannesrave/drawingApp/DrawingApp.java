package eu.johannesrave.drawingApp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/** drawingApp with JavaFX
 * A student project at Technische Hochschule Brandenburg.
 * Developed using JDK8 (corretto 1.8), which still contained JavaFX.
 * @author Johannes Rave, 20216942, rave@th-brandenburg.de
 * @version 1.00, 31.12.20
 */
public class DrawingApp extends Application {

	private static final int sceneWidth = 1600;
	private static final int sceneHeight = 900;

	@Override
	public void start(Stage stage) {

		// Setup root hierarchy
		BorderPane root = DrawingController.setup(sceneWidth, sceneHeight);
		double prefMinWidth = root.getMinWidth();
		double prefMinHeight = root.getMinHeight();
		
		// Setup Scene and minimum size with event handler after showing stage
		// Because OS-specific window decorations aren't taken into account when just setting prefMinWidth() 
		Scene scene = new Scene(root, prefMinWidth, prefMinHeight);
		
		EventHandler<WindowEvent> stageShowing = new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				stage.setMinWidth(stage.getWidth());
				stage.setMinHeight(stage.getHeight());
				
				stage.setWidth(sceneWidth);
				stage.setHeight(sceneHeight);
			}
		};
		
		// Setup Stage
		stage.setX(100);
		stage.setY(100);
		stage.setOnShown(stageShowing);
		stage.setTitle("drawnApart");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}