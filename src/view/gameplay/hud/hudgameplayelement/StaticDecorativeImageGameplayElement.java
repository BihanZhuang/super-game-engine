package view.gameplay.hud.hudgameplayelement;

import goalstrategies.IGoal;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.GameInfo;
import model.GameStats;

public class StaticDecorativeImageGameplayElement extends HUDGameplayElement{
	private Pane myPane;


	public StaticDecorativeImageGameplayElement(String imagePath, double xPos, double yPos,
			double height, double width, String type) {
		super(null, type);
		myPane = getPane();
		myPane.getChildren().add(setImageAndAdjustSize(height, width, imagePath));
		
		setPos(xPos, yPos);
	}
	
	private ImageView setImageAndAdjustSize(double height, double width, String imagePath){
		ImageView myImageView = new ImageView(new Image(imagePath));
		myImageView.setFitHeight(height);
		myImageView.setFitWidth(width);
		return myImageView;
	}



	@Override
	public void update(GameInfo info, GameStats stats, IGoal goal) {
		// Do nothing, just a static image
		
	}

}
