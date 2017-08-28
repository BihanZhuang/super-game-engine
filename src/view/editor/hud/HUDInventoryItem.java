package view.editor.hud;

import javafx.geometry.Insets;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HUDInventoryItem {
	public static final double DEFAULT_WIDTH = 80;
	public static final double DEFAULT_HEIGHT = 30;
	
	private String myType;
	private StackPane myPane;
	
	public HUDInventoryItem(String type){
		myType = type;
		myPane = new StackPane();
		myPane.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		myPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		myPane.setBackground(new Background((new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))));
		myPane.getChildren().add(new Text(type));
		
		setDragBehavior();
	}
	
	private void setDragBehavior(){
		myPane.setOnDragDetected(e -> startDrag(e));
	}
	
	private void startDrag(MouseEvent e){
		Dragboard d = myPane.startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString(myType);
		
		d.setContent(content);
		e.consume();	
	}
	
	public StackPane getPane(){
		return myPane;
	}
}
