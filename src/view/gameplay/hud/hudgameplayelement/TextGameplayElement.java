package view.gameplay.hud.hudgameplayelement;

import gameobject.ObjectInfo;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * TextGameplayElement
 * @author Yanbo Fang
 *
 */
public abstract class TextGameplayElement extends HUDGameplayElement{
	
	private static final String DEFAULT_FONT = "Verdana";
	private Text myText;
	
	public TextGameplayElement(ObjectInfo hero, double xPos, double yPos, double height, double width, String type, String textColor, int textSize) {
		super(hero, type);
		myText = createText(xPos, yPos, Color.web(textColor), Font.font(DEFAULT_FONT, textSize));	
		setPos(xPos, yPos);
	}

	protected Text getText(){
		return myText;
	}
	
	protected Text createText(double xPos, double yPos, Paint textColor, Font textFont){
		Text t = new Text();
		t.setX(xPos);
		t.setY(yPos);
		t.setFont(textFont);
		t.setFill(textColor);
		this.getPane().getChildren().add(t);
		return t;
	}
}
