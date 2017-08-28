package view.gameplay;

import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Constants;

public abstract class GameViewBase {

    public static final String STYLESHEET = "game.css";
    
    private Stage myStage;
    private GameViewCore myCore;
    
    public GameViewBase(Stage stage, GameViewCore core) {
        myStage = stage;
        myCore = core;
    }
    
    public GameViewCore getCore() {
        return myCore;
    }
    
    public void show() {
        myStage.show();
    }
    
    protected void init() {
        Scene scene = createScene(myCore);
        scene.getStylesheets().add(Constants.RESOURCE_PACKAGE + STYLESHEET);
        myCore.attachKeyHandler(scene);
        myStage.setScene(scene);
    }
    
    protected abstract Scene createScene(GameViewCore gameView);
    
}
