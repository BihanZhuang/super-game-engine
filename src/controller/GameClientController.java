// This entire file is part of my masterpiece.
// Bihan Zhuang
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gameobject.ObjectInfo;
import gameobject.Template;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.Boundary;
import model.ChampionPool;
import network.Client;
import network.ClientKeyEvent;
import network.ClientKeyEvent.Type;
import view.gameplay.GameViewCore;

/**
 * Controls the Client.
 * @author Bihan Zhuang
 *
 */
public class GameClientController {
	
	private int myControlID; 
	private GameViewCore myGameView;
	private GameClientView myGameClientView;
	private GameClient myClient;
	private Stage myStage;

	public GameClientController(String host, int port) throws IOException {
		myStage = new Stage();
        myGameView = new GameViewCore(new HeroControl() {

            @Override
            public void onKeyPressed(KeyCode code) {
                try {
                    myClient.handleKeyPressed(code);
                } catch (IOException e) {
                    new Alert(AlertType.ERROR, e.getMessage()).show();
                }
            }

            @Override
            public void onKeyReleased(KeyCode code) {
                try {
                    myClient.handleKeyReleased(code);
                } catch (IOException e) {
                    new Alert(AlertType.ERROR, e.getMessage()).show();
                }
            }
            
        });
        myGameClientView = new GameClientView(myStage, myGameView);
		myClient = new GameClient(host, port);
	}
	
	public void launch() {
	    myGameClientView.show();
	}
	
	public int getMyControlID() {
		return myControlID;
	}

	public void setMyControlID(int myControlID) {
		this.myControlID = myControlID;
	}

	private class GameClient extends Client {
		
		private ObjectInputStream in;
		private ObjectOutputStream out;

		public GameClient(String host, int port) throws IOException {
			super(host, port);
		}
		
		@Override
		protected Template chooseChampion(ChampionPool pool) {
			return myGameView.chooseChampion(pool);
		}

		@Override
		protected void messageReceived(Object message) {
			if(message instanceof ObjectInfo) {
				Platform.runLater(() -> {
					myGameView.update((ObjectInfo)message);
				});
			}
		}	
		
		@Override
		protected void extraHandshake(ObjectInputStream input, ObjectOutputStream output) throws IOException {
			this.in = input;
			this.out = output;
			out.writeObject(chooseChampion(getPool()));
			try {
				Object id;
				while(!((id = in.readObject()) instanceof Integer));
				setMyControlID((Integer)id);
				ObjectInfo champion = (ObjectInfo) in.readObject();
				Boundary boundary = (Boundary) in.readObject();
				myGameView.startGame(champion, boundary.getBounds());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		protected void handleKeyPressed(KeyCode code) throws IOException {
			handleKey(code, Type.PRESS);
		}

		@Override
		protected void handleKeyReleased(KeyCode code) throws IOException {
			handleKey(code, Type.RELEASE);
		}
		
		@Override
		protected void connectionClosedByError(String message) {}

		@Override
		protected void serverShutdown(String message) {}
		
		private void handleKey(KeyCode code, Type type) throws IOException {
			ClientKeyEvent event = new ClientKeyEvent(code, myControlID, type);
			out.writeObject(event);
			out.flush();
		}
	}
}
