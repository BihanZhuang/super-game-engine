package view;

import javafx.scene.layout.Pane;

public class PaneViewBase<T extends Pane> extends ViewBase<T> {
    public PaneViewBase(T node, double width, double height) {
        super(node);
        getView().setPrefWidth(width);
        getView().setPrefHeight(height);
    }
    
    public double getWidth() {
    	return getView().getPrefWidth();
    }
    
    public double getHeight() {
    	return getView().getPrefHeight();
    }
    
    

}
