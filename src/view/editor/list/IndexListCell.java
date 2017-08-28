package view.editor.list;

import gameobject.ObjectInfo;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.WorldInfo;
import util.view.ImageProcessor;

public class IndexListCell extends ListCell<Integer> {
    
	public static final Integer THUMBNAIL_SIZE = 20;
	
	private WorldInfo myWorld;
	
	public IndexListCell(WorldInfo world){
		myWorld = world;
	}
	
	@Override 
	protected void updateItem(Integer id, boolean empty){
		super.updateItem(id, empty);
		if(id == null || empty){
			return;
		}
	    ObjectInfo obj = myWorld.getObject(id);
	    Image thumbNail = new Image(getClass().getClassLoader().getResourceAsStream(obj.getImagePath()));
	    ImageView thumbView = new ImageView(ImageProcessor.scaleImage(thumbNail, THUMBNAIL_SIZE, THUMBNAIL_SIZE));
	    setGraphic(thumbView);
	    String nameAndIndex = obj.getName() + "                   " + id.toString();
	    setText(nameAndIndex);
	}
	

}
