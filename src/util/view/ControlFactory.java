package util.view;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.util.Callback;

/**
 * Generates JavaFX UI Controls
 * @author Mike Liu
 *
 */
public class ControlFactory {

    public static Button createButton(String text, EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setOnAction(handler);
        return button;
    }
    
    public static ComboBox<String> createComboBox(String promptText, List<String> content,
            ChangeListener<String> listener) {
        ComboBox<String> cb = new ComboBox<>(FXCollections.observableArrayList(content));
        cb.setPromptText(promptText);
        cb.getSelectionModel().selectedItemProperty().addListener(listener);
        return cb;
    }
    
    public static MenuItem createMenuItem(String text, EventHandler<ActionEvent> handler) {
        MenuItem item = new MenuItem(text);
        item.setOnAction(handler);
        return item;
    }
    
    public static <T> ListView<T> createListView(
            Orientation orient,
            ObservableList<T> items,
            Callback<ListView<T>, ListCell<T>> cellFactory) {
        ListView<T> listView = new ListView<>();
        listView.setOrientation(orient);
        listView.setItems(items);
        listView.setCellFactory(cellFactory);
        return listView;
    }
}
