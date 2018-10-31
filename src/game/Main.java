package game;
	
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Stage window;
	
	@Override
	public void start(Stage window) {
		Main.window = window;
		
		new GameController();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getWindow() {
		return window;
	}
}
