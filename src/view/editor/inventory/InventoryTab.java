package view.editor.inventory;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import model.Inventory;
import view.ViewBase;
import view.custom.DoubleSwappablePane;

/**
 * Hold game objects of the same template type in one tab. Based on the input
 * template type, present related items in a horizontal list, with the chosen
 * item's information in a text pane
 * 
 * @author Feng
 * @author Mike Liu
 *
 */
public class InventoryTab extends ViewBase<Tab>{
    public static final String INVENTORY_ID = "InventoryList";
	private DoubleSwappablePane rootPane;
	// Change to SplitPane
	private InventoryTabController controller;
    private ObservableList<String> nameList = FXCollections.observableArrayList();
    private ObservableList<Image> imageList = FXCollections.observableArrayList();
    private ListView<String> listView;
    private Text description;

	public InventoryTab(double width, double height, Inventory inventory, String type) {
	    super(new Tab(type));
	    controller = new InventoryTabController(inventory, type);
	    controller.initializeContent(imageList, nameList);
		description = new Text("Description");
        listView = controller.creatListView(description, imageList, nameList);
        rootPane = createSplitPane(width, height);
        getView().setContent(rootPane);
    }

	private DoubleSwappablePane createSplitPane(double width, double height) {
	    DoubleSwappablePane pane = new DoubleSwappablePane(width, height, 
	    		createTemplatePane(listView), createTextFlow(description),
	    		Orientation.VERTICAL);
	    pane.setDividerPercentPosition(0.35d);
	    return pane;
	}
	
	private TextFlow createTextFlow(Text... children) {
	    TextFlow textFlow = new TextFlow();
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.getChildren().addAll(children);
        return textFlow;
	}
	
	private Pane createTemplatePane(ListView<String> list) {
	    Pane pane = new Pane();
	    pane.setId(INVENTORY_ID);
	    HBox hbox = new HBox();
	    list.prefWidthProperty().bind(pane.prefWidthProperty());
	    list.prefHeightProperty().bind(pane.prefHeightProperty());
	    hbox.prefWidthProperty().bind(pane.prefWidthProperty());
	    hbox.prefHeightProperty().bind(pane.prefHeightProperty());
	    hbox.getChildren().addAll(list);
        pane.getChildren().addAll(hbox);
        return pane;
	}
}
