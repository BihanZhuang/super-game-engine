package view.editor.list;

import java.util.function.Consumer;

import gameobject.ObjectInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.WorldInfo;
import util.Observer;
import util.view.ControlFactory;
import view.ViewBase;

/**
 * 
 * @author
 * @author Mike Liu
 *
 */
public class IndexListView extends ViewBase<Pane> implements Observer<ObjectInfo> {
    
	public static final String ID = "ObjectList";

    private ObservableList<Integer> myObjectList;
	private ListView<Integer> myListView;

	public IndexListView(double width, double height, WorldInfo world) {
	    super(new Pane());
	    myObjectList = FXCollections.observableArrayList();
	    myListView = createListView(world, myObjectList);
		getView().setId(ID);
        getView().setPrefSize(width, height);
		getView().getChildren().add(createVBox(ID, getView(), myListView));
	}

    @Override
    public void update(ObjectInfo arg) {
        Integer id = arg.getID();
        if (!myObjectList.contains(id)) {
            myObjectList.add(id);
        }

    }
	
	public void setObjectHighlightHandler(Consumer<String> handler){
	    	
	}

	private ListView<Integer> createListView(WorldInfo world, ObservableList<Integer> items){
	    ListView<Integer> listView = ControlFactory.createListView(
	            Orientation.VERTICAL,
	            items,
	            param -> new IndexListCell(world));
	    VBox.setVgrow(listView, Priority.ALWAYS);
	    return listView;
    }
	
	private VBox createVBox(String id, Pane parent, Node... children) {
	    VBox vBox = new VBox();
	    vBox.setId(id);
	    vBox.prefWidthProperty().bind(parent.prefWidthProperty());
	    vBox.prefHeightProperty().bind(parent.prefHeightProperty());
	    vBox.getChildren().addAll(children);
	    return vBox;
	}
    
    private void setFocusHandler(Consumer<Integer> handler) {
        myListView.getSelectionModel()
        .selectedItemProperty().addListener((ov, old_template, new_template) -> {
            handler.accept(new_template);
        });
    }

}
