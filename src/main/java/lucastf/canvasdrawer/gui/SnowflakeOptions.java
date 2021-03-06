package lucastf.canvasdrawer.gui;

import java.io.IOException;

import lucastf.canvasdrawer.abstractions.OptionsPane;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Slider;

public class SnowflakeOptions extends OptionsPane{
	
	private static final String fxml = "/gui/snowflakeBar.fxml";
	
	@FXML private Slider iterationSlider;

	public SnowflakeOptions(Parent parent) throws IOException {
		super(parent, fxml);
	}
	
	public double getIterationsValue() {
		return iterationSlider.getValue();
	}

}
