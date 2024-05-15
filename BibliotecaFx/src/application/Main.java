package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			// BorderPane root = new BorderPane();
			// Group root = new Group();
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); // paine
			Scene scene = new Scene(root /* ,400,400 */); // container
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image icon = new Image("bibli2.png");
			primaryStage.getIcons().add(icon); //window
			// primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Salve Maria!");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Salve Maria! */
	}
}
