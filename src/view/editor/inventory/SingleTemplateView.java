package view.editor.inventory;

import gameobject.Template;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Constants;
import util.ResourceParser;
import util.view.ImageProcessor; 

/**
 * Single Template Model on the TemplateTab
 * that is clickable and draggable. Once clicked 
 * a new instance of the current template will be 
 * generated. 
 * @author Feng
 *
 */
public class SingleTemplateView{
	public static final Integer TEMPLATE_WIDTH = 50;
	public static final Integer TEMPLATE_HEIGHT = 50;
	public static final String IMAGE = "imagepath";
	public static final String DESCRIPTION = "description";
	private ResourceParser mResource;
	private String mName;
	private Image mImage;
	private String mDescription;
	
	
	public SingleTemplateView(Template template){
		mName = template.getName();
		mResource = new ResourceParser(Constants.RESOURCE_PACKAGE.concat(mName));
		
		ImageView view = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(template.getImagePath())));
		view.setFitWidth(TEMPLATE_WIDTH);
		view.setFitHeight(TEMPLATE_HEIGHT);
		view.setSmooth(true);
		view.setPreserveRatio(true);
		Image original = new Image(getClass().getClassLoader().getResourceAsStream(template.getImagePath()));
		mImage = ImageProcessor.scaleImage(original, TEMPLATE_WIDTH, TEMPLATE_HEIGHT, false, true);
		mDescription = mResource.getString(DESCRIPTION);	
	}
	
	public String getDescription(){
		return mDescription;
	}
	
	public Image getImage(){
		return mImage;
	}

}
