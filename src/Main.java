
import javafx.application.Application;
import javafx.stage.Stage;
import view.GUI;

public class Main extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		new GUI(stage).show();
	}
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
