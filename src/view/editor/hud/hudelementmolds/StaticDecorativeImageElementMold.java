package view.editor.hud.hudelementmolds;
import java.io.File;

import hudelement.StaticDecorativeImageElement;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import view.editor.hud.HUDCanvasController;

public class StaticDecorativeImageElementMold extends HUDElementMold{
	public static final double DEFAULT_IMAGE_SIDE = 150;
	
	private String imagePath;
	private String imageName;
	private StaticDecorativeImageElement myElement;
	private ImageView image;

	public StaticDecorativeImageElementMold(double xPos, double yPos, HUDCanvasController cController) {
		super(DEFAULT_IMAGE_SIDE, DEFAULT_IMAGE_SIDE, xPos, yPos, cController);
		setHUDElement(myElement);
		imageName = "Untitled-1.png";
		imagePath = getClass().getClassLoader().getResourceAsStream(imageName).toString();
		myElement = new StaticDecorativeImageElement(xPos, yPos, DEFAULT_IMAGE_SIDE, DEFAULT_IMAGE_SIDE, imagePath, "");
		image = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageName).toString()));
		getPane().getChildren().add(image);
	}

	@Override
	protected void showPopUpStartingInfo() {
		
	}

	@Override
	protected void showPopUpInformation() {
		getStage().show();
		Button selectImage = new Button("Select Image");
		
		selectImage.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(getStage());
            if (file != null) {
            	String filePath = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\")+1);
            	String fileName = file.getName();
            	System.out.println(fileName);
				
            	getPane().getChildren().remove(image);
            	imagePath = filePath;
            	imageName = fileName;
            	
            	image = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageName).toString()));
				
            	myElement.setImage(imagePath);
            	//set the image on the selection square so the user can see the image they selected
                getPane().getChildren().add(image);
            }
		});
	}


}
