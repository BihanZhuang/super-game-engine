package view.editor.attribute;

import java.util.function.BiConsumer;

import gameobject.component.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import util.Constants;
import util.view.ControlFactory;

public class ComponentList {
    
    private Stage myStage;

    public ComponentList(BiConsumer<Component, String> handler) {
        myStage = new Stage();
        Scene scene = new Scene(createListView(FXCollections.observableArrayList(ComponentFactory.COMPONENTS), handler));
        scene.getStylesheets().add(Constants.RESOURCE_PACKAGE+Constants.DRACULA_STYLE);
        myStage.setScene(scene);
    }
    
    public void show() {
        myStage.show();
    }
    
    private ListView<String> createListView(ObservableList<String> items, BiConsumer<Component, String> handler) {
        ListView<String> listView = ControlFactory.createListView(
                Orientation.VERTICAL,
                items,
                value -> new ListCell<String>() {
                    
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item);
                        }
                    }
                    
                });
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handler.accept(ComponentFactory.getComponent(newValue), newValue);
        });
        return listView;
    }
    
}
