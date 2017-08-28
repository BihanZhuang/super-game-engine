package view.custom.dragresize;

import java.util.function.Consumer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ImageResizablePane extends ResizablePane implements ICloneable<ImageResizablePane>{
	private ImageView image;
	
	public ImageResizablePane(ImageView img) {
		this(img, 1);
	}
	
	public ImageResizablePane(ImageView img, double unit) {
		super(img.getImage().getWidth(), img.getImage().getHeight());
	    this.image = img;
		getHost().setLayoutX(image.getLayoutX());
		getHost().setLayoutY(image.getLayoutY());
		getHost().setTranslateX(image.getTranslateX());
		getHost().setTranslateY(image.getTranslateY());
		getHost().getChildren().add(image);
		image.fitWidthProperty().bind(getHost().prefWidthProperty());
		image.fitHeightProperty().bind(getHost().prefHeightProperty());
		image.setLayoutX(0);
		image.setLayoutY(0);
		
		this.setUnit(unit);
	}
	
	@Override
	public ImageResizablePane clone() {
		Image original = image.getImage();
		ImageView imageView = new ImageView(original);
		ImageResizablePane resizable = new ImageResizablePane(imageView, this.getUnit());
		Pane otherHost = resizable.getHost();
		Pane thisHost = this.getHost();
		otherHost.setTranslateX(thisHost.getTranslateX());
		otherHost.setTranslateY(thisHost.getTranslateY());
        otherHost.setLayoutX(thisHost.getLayoutX());
        otherHost.setLayoutY(thisHost.getLayoutY());
        otherHost.setPrefSize(thisHost.getPrefWidth(), thisHost.getPrefHeight());
		return resizable;
	}

}
