package view.editor.componentwindow;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;

public class ImageSelectedButton {
	private Text key;
	private TextField value;

	private ComponentData componentdata;
	private HBox content;

	public ImageSelectedButton(String key, String value, ComponentData componentdata) {
		this.componentdata = componentdata;
		content = new HBox();
		content.getChildren().addAll(createText(key), createTextField(value), load());
	}

	private Node createText(String a) {
		Text t = new Text(a);
		t.setTextAlignment(TextAlignment.CENTER);
		this.key = t;
		HBox w = new HBox(t);
		w.setAlignment(Pos.CENTER);
		w.setPrefWidth(180);
		return w;
	}

	private Node createTextField(String b) {
		TextField tf = new TextField(b);
		this.value = tf;
		// tf.setDisable(true);
		tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					tf.setDisable(true);
					update();
				}
			}
		});
		tf.setOnMouseClicked(e -> tf.setDisable(false));
		tf.setPrefWidth(180);
		return tf;
	}

	private void update() {
		String key = this.key.getText();
		String value = this.value.getText();
		this.componentdata.put(key, value);
	}

	private Button load() {
		Button b = new Button("Load");
		final FileChooser fileChooser = new FileChooser();
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = fileChooser.showOpenDialog(null);
				if (file != null) {
					value.setText(file.getName());
					update();
				}
			}
		});
		b.setPrefWidth(120);
		return b;
	}
	
	public Node getDisplayContent() {
		return this.content;
	}
}
