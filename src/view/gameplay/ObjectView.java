package view.gameplay;

import gameobject.ObjectInfo;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.ViewBase;

public class ObjectView extends ViewBase<ImageView> {

    private String myImagePath;

    public ObjectView(ObjectInfo obj) {
        super(new ImageView());
        myImagePath = "";
        update(obj);
    }
    
    public void update(ObjectInfo obj) {
        if(!myImagePath.equals(obj.getImagePath())) {
            myImagePath = obj.getImagePath();
            getView().setImage(new Image(getClass().getClassLoader().getResourceAsStream(myImagePath)));
        }
        getView().setX(obj.getPosition().getX() - obj.getDimension().getX() / 2);
        getView().setY(obj.getPosition().getY() - obj.getDimension().getY() / 2);
        getView().setFitWidth(obj.getDimension().getX());
        getView().setFitHeight(obj.getDimension().getY());
    }

}
