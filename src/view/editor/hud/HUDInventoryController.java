package view.editor.hud;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HUDInventoryController {
	
	public HUDInventoryController(){
		ColorPicker c = new ColorPicker();
		String s = String.format("#%02X%02X%02X", (int) c.getValue().getRed()*255, (int) c.getValue().getGreen()*255, (int) c.getValue().getBlue() * 255);
		Rectangle p = new Rectangle();
		p.setFill(Color.web(s));
	}
}
