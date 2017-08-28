package view.gameplay;

import controller.HostControl;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameHostView extends GameViewBase {
    
    private HostControl myController;
    
    public GameHostView(Stage stage, GameViewCore core, HostControl controller) {
        super(stage, core);
        myController = controller;
        init();
    }

    @Override
    protected Scene createScene(GameViewCore gameView) {
        BorderPane root = new BorderPane();
        root.setTop(new GameMenuBar(myController).getView());
        root.setCenter(gameView.getView());
        return new Scene(root);
    }

}
