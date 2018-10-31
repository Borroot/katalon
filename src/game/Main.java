package game;
	
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	public static Stage window;
	
	@Override
	public void start(Stage window) {
		Main.window = window;
		
		URL location = getClass().getResource("/BeginMenuLayout.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(location);
		
		Parent root = null;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			System.out.println("Could not load the fxml file.");
			System.exit(1);
		}
		Scene scene = new Scene(root);
		
		window.setScene(scene);
		window.setTitle("Katalon");
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getStage() {
		return window;
	}
}
