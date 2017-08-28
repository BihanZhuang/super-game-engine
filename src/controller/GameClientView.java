package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.gameplay.GameViewBase;
import view.gameplay.GameViewCore;

public class GameClientView extends GameViewBase {

    public GameClientView(Stage stage, GameViewCore core) {
        super(stage, core);
        init();
    }

    @Override
    protected Scene createScene(GameViewCore gameView) {
        return new Scene(gameView.getView());
    }

}
