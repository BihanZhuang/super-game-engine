package view.editor.imageselector;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ImageSelectorSquare {
	private Pane square;
	private String defaultImagePath;
	private ImageView backgroundImageView;
	private double sideLength;
	
	
	public ImageSelectorSquare(String imageIndicatorBackgroundPath, double squareSideLength){
		defaultImagePath = imageIndicatorBackgroundPath;
		square = new Pane();
		sideLength = squareSideLength;
		
		setDefaultImage();
		
//		square.setPrefSize(squareSideLength, squareSideLength);
		square.setPrefSize(sideLength, sideLength);
//		square.setPadding(new Insets(2, 2, 2, 2));
//		square.setStyle("-fx-border-color: #A9A9A9");
		square.getChildren().add(backgroundImageView);
	}
	
	private void setDefaultImage(){
		backgroundImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(defaultImagePath)));
		backgroundImageView.setFitWidth(sideLength);
		backgroundImageView.setFitHeight(sideLength);
	}
	
	/*
	 * call this to set displayed image to desired image upon user selection
	 */
	public void setImage(String imageName){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(imageName));
		backgroundImageView.setImage(image);
	}
	
	/*
	 * call this to set displayed image to default image if user removes a previously selected image
	 */
	public void resetImageToDefault(){
		backgroundImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(defaultImagePath)));
	}
	
	public Pane getSquare(){
		return square;
	}
	
	public double getSideLength(){
		return sideLength;
	}
}
