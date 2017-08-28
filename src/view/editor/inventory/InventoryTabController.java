package view.editor.inventory;

import gameobject.Template;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import model.Inventory;
import util.Constants;
import util.view.ControlFactory;
import util.view.ImageProcessor;
import view.custom.dragndrop.listcell.ImageListCell;

public class InventoryTabController {
    private Inventory inventory;
    private String type;
    private ListView<String> myListView;
	public InventoryTabController(Inventory inventory, String type){
		this.type = type;
		this.inventory = inventory;
	}
	
	public void initializeContent(ObservableList<Image> imageList, ObservableList<String> nameList) {
        for (String name : inventory.getNamesOfType(type)) {
            Template template = inventory.getTemplate(name);
            nameList.add(name);
            imageList.add(ImageProcessor.loadImage(template.getImagePath(), Constants.CELL_SIZE, Constants.CELL_SIZE));
        }
    }
	
	public ListView<String> creatListView(Text textField, ObservableList<Image> imageList, ObservableList<String> nameList) {
		myListView = ControlFactory.createListView(
                Orientation.HORIZONTAL,
                nameList,
                value -> new ImageListCell(imageList));
        myListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (inventory.containsTemplate(newValue)) {
                textField.setText(inventory.getTemplate(newValue).getDescription());
            }
        });
        return myListView;
	}
}
