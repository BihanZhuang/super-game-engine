package view.editor.test;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.view.ImageProcessor;
import view.custom.dragndrop.DragContext;
import view.custom.dragresize.ResizablePane;

/**
 * Demonstrates a drag-and-drop feature.
 */
public class DragTest extends Application {
    private DragContext mDragContext = new DragContext();;


    @Override public void start(Stage stage) {
    	BorderPane border = new BorderPane();
    	
    	border.setPrefSize(200, 200);
//    	Pane pane = new Pane();
//    	pane.setStyle("-fx-background-color: #676165;");
//    	pane.setPrefSize(50, 50);
    	ImageView image = new ImageView(ImageProcessor.loadImage("Mario1.png", 50, 50));
    	ResizablePane resizable = new ResizablePane(50, 50);

    	resizable.getHost().setOnMousePressed(getSecondaryMousePressedHandler(resizable));
    	resizable.getHost().setOnMouseDragged(getSecondaryMouseDraggedHandler(resizable));
    	

    	Pane top = new Pane();
    	top.setStyle("-fx-background-color: #88EA64;");
    	top.setPrefSize(100, 100);

    	Pane bottom = new Pane();
    	bottom.setStyle("-fx-background-color: #64E6EA;");
    	bottom.setPrefSize(100, 100);

    	Pane left = new Pane();
    	left.setStyle("-fx-background-color: #D2EA64;");
    	left.setPrefSize(100, 100);

    	Pane right = new Pane();
    	right.setStyle("-fx-background-color: #EA64AF;");
    	right.setPrefSize(100, 100);


    	border.setLeft(left);
    	border.setRight(right);
    	border.setBottom(bottom);
    	border.setTop(top);
    	border.setCenter(resizable.getHost());
    	
    	
    	Scene scene = new Scene(border, 500, 500);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public EventHandler<MouseEvent> getSecondaryMousePressedHandler(ResizablePane resizable){
   	 return event -> {
   	  // Check the event to make sure left mouse is pressed
            if(!event.isPrimaryButtonDown() || resizable.isResizing())
                return;
                        
            mDragContext.mouseAnchorX = event.getSceneX();
            mDragContext.mouseAnchorY = event.getSceneY();
            
            Node node = (Node) event.getSource();
            mDragContext.nodeAnchorX = node.getTranslateX();
            mDragContext.nodeAnchorY = node.getTranslateY();
   	 };
    }
    
    public EventHandler<MouseEvent> getSecondaryMouseDraggedHandler(ResizablePane resizable){
   	 return event -> {
   	  // Check the event to make sure left mouse is pressed
            if(!event.isPrimaryButtonDown() || resizable.isResizing())
                return;
            
            // TODO
            Node node = (Node) event.getSource();
            
            node.setTranslateX(mDragContext.nodeAnchorX + (event.getSceneX()-mDragContext.mouseAnchorX));
            node.setTranslateY(mDragContext.nodeAnchorY + (event.getSceneY()-mDragContext.mouseAnchorY));
            
            event.consume();
   	 };
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
