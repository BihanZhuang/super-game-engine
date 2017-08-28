package util.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageProcessor {
	
	public static Image scaleImage(Image image, double width, double height){
		return scaleImage(image, width, height, false, false);
	}
	
	public static Image scaleImage(Image image, double width, double height, boolean preserveRatio, boolean smooth){
		ImageView view = new ImageView(image);
		view.setFitWidth(width);
		view.setFitHeight(height);
		view.setSmooth(smooth);
		view.setPreserveRatio(preserveRatio);
		return view.snapshot(null, null);
	}

    public static Image loadImage(String fileName, double width, double height) {
        Image original = new Image(ImageProcessor.class.getClassLoader().getResourceAsStream(fileName));
        Image scaled = ImageProcessor.scaleImage(original, width, height, false, true);
        return scaled;
    }
    
}
