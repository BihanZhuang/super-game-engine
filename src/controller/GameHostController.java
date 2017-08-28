// This entire file is part of my masterpiece.
// Bihan Zhuang
package controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.IGame;
import model.Model;
import network.Server;
import util.DirectorySelector;
import util.FileSelector;
import view.gameplay.GameHostView;
import view.gameplay.GameViewCore;

/**
 * This class serves as an assembly place for the model, view and server on the game 
 * host side. It connects model and view through the Observer pattern and handles all logics
 * with proper control handlers using the Strategy pattern. The control handlers, all 
 * defined as Interfaces, were first concretely implemented and then injected into appropriate 
 * constructors in this class. They are:
 * 1. HostControll that handles animation and save/load in the GameView and it is defined
 * as a private class of this class; 
 * 2. HeroControll that handles key inputs/outputs in the GameView and it is defined as a 
 * private class of this class;
 * 3. ServerControl that facilitates handling of key inputs/outputs from the clients when
 * creating the server.
 * 
 * This class demonstrates good design because through proper usage of design patterns 
 * mentioned above and convenient lambda expressions, all the necessary connections to run a 
 * game host can be made within this class. The Observer pattern makes it easy and light-wieght
 * to transfer information between corresponding frontend and backend components. The Strategy
 * pattern allows the concrete implementations of our control handlers to be changed at any 
 * time within this class. It also makes the control handlers extensible to different usage
 * in other kinds of controllers. 
 * 
 * In order to support a multi-player networked game using host-client architecture, in this class
 * the server is also initialized and is capable of handling the switch between the data-transfer 
 * thread and the JavaFX thread.
 * @author Bihan Zhuang
 *
 */
public class GameHostController {

	public static final int PORT = 37829;

	private int myControlID;
	private Model myModel;
	private GameHostView myGameHostView;
	private GameViewCore myGameViewCore;
	private FileSelector myFileSelector;
	private DirectorySelector myDirectorySelector;

	private Server myGameServer;
	private Stage myStage;

    public GameHostController() throws IOException {
        myFileSelector = new FileSelector("Game Record", "*.vooga");
        myDirectorySelector = new DirectorySelector();
        myStage = new Stage();
        myStage.setOnCloseRequest(e -> {
        	myGameServer.shutDownServer();
        });
        myGameViewCore = new GameViewCore(new MyHeroControl());
        myGameHostView = new GameHostView(myStage, myGameViewCore, new MyGameControl());
        
        myModel = new Model(win -> {
            myGameViewCore.end(win);
            myModel.pause();
        });
        IGame game = myModel.getGame();
        setupObserve(game);
		myGameServer = createServer(game);
    }
   
    /*
     * To be called in the Controller class for launching the game host gameview. 
     */
	public void launch() {
        myGameHostView.show();
    }
	
	private void setupObserve(IGame game) {
		game.getWorld().forEach(myGameViewCore::update);
		game.getWorld().addInfoObserver(arg -> {
			Platform.runLater(() -> myGameViewCore.update(arg));
			myGameServer.sendToAll(arg);
		});

		game.addChangeObserver(arg -> {
			Platform.runLater(() -> myGameViewCore.update(arg));
		});

		game.getStats().addObserver(arg -> {
			Platform.runLater(() -> myGameViewCore.updateStats(arg));
		});

		game.getLevelInfo().getGoals().forEach(arg -> arg.addObserver(arg2 -> {
			Platform.runLater(() -> myGameViewCore.updateGoal(arg2));
		}));
	}
	
	private Server createServer(IGame game) throws IOException {
		Server server = new Server(PORT, game, new ServerControl() {

			@Override
			public void handleClientKeyPressed(int id, KeyCode code) {
				myModel.getGame().handleKeyPressed(id, code);
			}

			@Override
			public void handleClientKeyReleased(int id, KeyCode code) {
				myModel.getGame().handleKeyReleased(id, code);
			}
        });
		server.getPoolFromGame(game.getChampionPool());
		return server;
	}

    private class MyGameControl implements HostControl {

        @Override
        public void run() {
            myModel.play();
        }

        @Override
        public void pause() {
            myModel.pause();
        }

        @Override
        public void saveRecord() {
            myFileSelector.showSaveDialog(myStage).ifPresent(file -> myModel.getGame().saveRecord(file, myControlID));
        }

        @Override
        public void loadRecord() {
            myFileSelector.showOpenDialog(myStage).ifPresent(file -> myControlID = myModel.getGame().loadRecord(file));
            myModel.step();
        }

        @Override
        public void loadGame() {
            myDirectorySelector.showDialog(myStage).ifPresent(myModel.getGame()::loadGame);
            IGame game = myModel.getGame();
            myControlID = game.connect(myGameViewCore.chooseChampion(game.getChampionPool()));
            myGameViewCore.startGame(game.getChampion(myControlID), game.getWorld().getBoundary().getBounds());
            myGameViewCore.loadHUDConfig(game.getHUDConfig());
            myModel.play();
        }
        
        @Override
        public void saveGame() {
            myDirectorySelector.showDialog(myStage).ifPresent(myModel.getGame()::saveGame);
        }

    }
    
    private class MyHeroControl implements HeroControl {

        @Override
        public void onKeyPressed(KeyCode code) {
            myModel.getGame().handleKeyPressed(myControlID, code);
        }

        @Override
        public void onKeyReleased(KeyCode code) {
            myModel.getGame().handleKeyReleased(myControlID, code);
        }
    }
}
