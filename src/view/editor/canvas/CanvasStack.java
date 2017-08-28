package view.editor.canvas;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import gameobject.ObjectInfo;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.Inventory;
import model.WorldInfo;
import view.ViewBase;

/**
 * Uses a stackPane to hold three layers at the same time. Bind its position,
 * scale and size to all its children canvases'. Allow dragging and zooming.
 * 
 * @author
 * @author Mike Liu
 */
public class CanvasStack extends ViewBase<StackPane> {

    public static final String ID = "canvas";
    public static final double BACKDROP_SIZE = 1000;
    public static final String CANVAS_COLOR = "#4F4D4C";

    private Map<Layer, CanvasView> myLayers;

    public CanvasStack(double width, double height, WorldInfo world, Inventory inventory,
            Consumer<ObjectInfo> objectClickHandler) {
        super(new StackPane());
        init(width, height, world);
        myLayers = new EnumMap<>(Layer.class);
        addLayer(getView(), Layer.FOREGROUND, world, inventory, objectClickHandler);
    }

    public Optional<CanvasView> getLayer(Layer layer) {
        return Optional.ofNullable(myLayers.get(layer));
    }

    private void init(double width, double height, WorldInfo world) {
        getView().setPrefWidth(width);
        getView().setPrefHeight(height);
        getView().toBack();
        world.getBoundary().setWidth(getView().getWidth());
        world.getBoundary().setHeight(getView().getHeight());
        getView().widthProperty()
                .addListener((observable, oldValue, newValue) -> world.getBoundary().setWidth(newValue.doubleValue()));
        getView().heightProperty()
                .addListener((observable, oldValue, newValue) -> world.getBoundary().setHeight(newValue.doubleValue()));
    }

    private void addLayer(Pane parent, Layer layer, WorldInfo world, Inventory inventory,
            Consumer<ObjectInfo> objectClickHandler) {
        CanvasView canvas = new CanvasView(layer, parent, world, inventory, objectClickHandler);
        myLayers.put(layer, canvas);
        getView().getChildren().add(canvas.getView());
    }

}
