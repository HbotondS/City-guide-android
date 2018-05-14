package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	public static final int WIDTH = 450;
	public static final int HEIGHT = 600;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("welcomeScreen.fxml"));
		primaryStage.setTitle("Space Invaders");
		primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("images/invader2.png").toString()));
		primaryStage.centerOnScreen();
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
