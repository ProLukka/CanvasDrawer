package lucastf.canvasdrawer;

import lucastf.canvasdrawer.gui.CanvasGUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		new CanvasGUI(stage);
	}

}
