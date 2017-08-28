package view.chat;

import java.io.IOException;

import gameobject.Template;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.ChampionPool;
import network.Client;
import network.ForwardedMessage;
import util.view.ControlFactory;

public class ChatWindow {
	
	public final static int PORT = 37829;
	public static final Dimension2D DEFAULT_SIZE = new Dimension2D(400, 800);
	
	private Stage myStage;
	private Scene myScene;
	private BorderPane myRoot;
	private Button sendButton, quitButton;
	
	private TextField input;
	private TextArea transcript;
	private ChatClient connection;
	private volatile boolean connected;

	public ChatWindow(String host) {
		myStage = new Stage();
		myRoot = new BorderPane();
		myScene = new Scene(myRoot, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
		initTextField();
		initTextArea();
		initButtons();
		HBox inputBar = new HBox(input, sendButton, quitButton);
		inputBar.setSpacing(20);
		myRoot.setBottom(inputBar);
		new Thread() {
			public void run() {
				try {
					addToTranscript("Connecting to " + host + " ...");
                    connection = new ChatClient(host);
                    connected = true;
                    input.setEditable(true);
                    input.setDisable(false);
                    sendButton.setDisable(false);
				}
				catch(IOException e) {
					addToTranscript("Connection attempt failed.");
                    addToTranscript("Error: " + e);
				}
			}
		}.start();
	}
	
	public void show() {
		myStage.setScene(myScene);
		myStage.show();
	}
	
	private void initTextField() {
		input = new TextField();
		input.setPadding(new Insets(3,3,3,3));
		input.setEditable(true);
	}
	
	private void initTextArea() {
		transcript = new TextArea();
		transcript.setPadding(new Insets(5,5,5,5));
		transcript.setEditable(false);
		myRoot.setCenter(transcript);
	}
	
	private void initButtons() {
		sendButton = ControlFactory.createButton("Send", e -> {
			String msg = input.getText();
			if(msg.trim().length() == 0) {
				return;
			}
			connection.send(msg);
			input.selectAll();
            input.clear();
		});
		quitButton = ControlFactory.createButton("Quit", e -> doQuit());
	}
	
	private void addToTranscript(String message) {
        transcript.appendText(message);
        transcript.appendText("\n\n");
    }
	
	private void doQuit() {
        if (connected)
            connection.disconnect();  // Sends a DisconnectMessage to the server.
        myStage.close();
        try {
            Thread.sleep(1000); // Time for DisconnectMessage to actually be sent.
        }
        catch (InterruptedException e) {
        }
        System.exit(0);
    }
	
	/*************** ChatClient Helper Class *****************/
	
	private class ChatClient extends Client {

		public ChatClient(String host) throws IOException {
			super(host, PORT);
		}

		@Override
		protected void messageReceived(Object message) {
			if(message instanceof ForwardedMessage) {
				ForwardedMessage fm = (ForwardedMessage) message;
				addToTranscript("#" + fm.senderID + " SAYS:  " + fm.message);
			}
		}
		
		protected void connectionClosedByError(String message) {
            addToTranscript("Sorry, communication has shut down due to an error:\n     " + message);
            sendButton.setDisable(true);
            input.setDisable(true);
            input.setEditable(false);
            input.setText("");
            connected = false;
            connection = null;
        }
		
		protected void playerConnected(int newPlayerID) {
            addToTranscript("Someone new has joined the chat room, with ID number " + newPlayerID);
        }
		
		protected void playerDisconnected(int departingPlayerID) {
            addToTranscript("The person with ID number " + departingPlayerID + " has left the chat room");
        }

		@Override
		protected void serverShutdown(String message) {}

		@Override
		protected void handleKeyPressed(KeyCode code) throws IOException {}

		@Override
		protected void handleKeyReleased(KeyCode code) throws IOException {}

		@Override
		protected Template chooseChampion(ChampionPool pool) {
			return null;
		}
		
	}

}
