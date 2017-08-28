package view.editor.componentwindow;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class KeyValuePair {
	private Text key;
	private TextField value;
	private ComponentData database;
	private HBox content;

	public KeyValuePair(String key, String value, ComponentData database) {
		this.database = database;
		content = new HBox();
		content.getChildren().addAll(createText(key), createTextField(value));
	}

	public HBox getDisplayContent() {
		return this.content;
	}

	private Node createText(String a) {
		Text t = new Text(a);
		this.key=t;
		HBox w = new HBox(t);
		w.setPrefWidth(250);
		w.setAlignment(Pos.CENTER);
		return w;
	}

	private Node createTextField(String b) {
		TextField tf = new TextField(b);
		this.value=tf;
		//tf.setDisable(true);
		tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					tf.setDisable(true);
					update();
				}
			}
		});
		tf.textProperty().addListener((observable, oldValue, newValue) -> {
			update();
		});
		tf.setOnMouseClicked(e -> tf.setDisable(false));
		tf.setPrefWidth(250);
		return tf;
	}

	private void update() {
		String key = this.key.getText();
		String value = this.value.getText();
		this.database.put(key, value);
	}

}
