package view.editor.minimap;

import javafx.scene.layout.Pane;
import util.Observer;
import view.PaneViewBase;
import view.custom.dragresize.ImageResizablePane;
import view.editor.canvas.CanvasStack;
import view.editor.canvas.CanvasView;
import view.editor.canvas.Layer;

public class MinimapView extends PaneViewBase<Pane> implements Observer<CanvasView>{

	public MinimapView(double width, double height, CanvasStack stack) {
		super(new Pane(), width, height);
		this.getView().setId("Minimap");
		CanvasView canvas = stack.getLayer(Layer.FOREGROUND).get();
		canvas.addObserver(this);
	}

	@Override
	public void update(CanvasView canvas) {
		double xScale = getView().getPrefWidth() / canvas.getWidth();
		double yScale = getView().getPrefHeight() / canvas.getHeight();
		this.getView().getChildren().clear();

		Pane mini = new Pane();
		mini.setPrefSize(canvas.getWidth(), canvas.getHeight());
	    for(ImageResizablePane resizable : canvas.getImages()) {
	    	ImageResizablePane copy = resizable.clone();
	    	mini.getChildren().add(copy.getHost());
	    }
		mini.setScaleX(xScale);
		mini.setScaleY(yScale);
		double diffX = (1 - xScale) * canvas.getWidth() / 2;
		double diffY = (1 - yScale) * canvas.getHeight() / 2;
		mini.setLayoutX(-diffX);
		mini.setLayoutY(-diffY);
		getView().getChildren().add(mini);
	}

}
