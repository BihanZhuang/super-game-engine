package controller;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import view.chat.ChatWindow;
import view.editor.view.EditorView;

/**
 * Assembles model and view and coordinates the procession of the game.
 * 
 * @author Mike Liu
 *
 */
public class Controller {

	public void launchGame() throws IOException {
	    new GameHostController().launch();
	}

	public void launchEditor() {
		Stage stage = new Stage();
		DisplayMode mode = 
		        GraphicsEnvironment.getLocalGraphicsEnvironment().
		        getDefaultScreenDevice().getDisplayMode();
		stage.setWidth(mode.getWidth());
		stage.setHeight(mode.getHeight());
		EditorView editorView = new EditorView(stage);
		editorView.show();
	}
	
	public void connectToGame() throws IOException {
		Optional<String> result = createDialog().showAndWait();
		if(result.isPresent() && !result.get().isEmpty()) {
			try {
				new GameClientController(result.get(), GameHostController.PORT).launch();
			}
			catch(Exception e) {
				alert();
			}
		}
	}
	
	public void launchChat() {
		ChatWindow cw = new ChatWindow("localhost");
		cw.show();
	}
	
	private TextInputDialog createDialog() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText("Enter host computer name or IP address: ");
		dialog.setOnCloseRequest(e -> dialog.close());
		dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setOnMouseClicked(e -> {
			dialog.close();
		});
		return dialog;
	}
	
	private void alert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Connection Error");
		alert.setContentText("Host not found or connection failed due to other reasons.");
		alert.showAndWait();
	}

}
