package view.editor.inventory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ChampionPool;
import view.gameplay.ChampionSelector;

public class TestCustom extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
    	Pane p = new Pane();  	
    	Button butt = new Button("button");
    	p.getChildren().add(butt);
    	butt.setOnAction(e->{
    		//CustomObjectView objView = new CustomObjectView(new Inventory());
    		ChampionSelector ch = new ChampionSelector(new ChampionPool());
    		//objView.show();
    		ch.show();
    	});
    	Scene testScene = new Scene(p, 500, 500);
    	stage.setScene(testScene);
    	stage.show();    	
    }
}
