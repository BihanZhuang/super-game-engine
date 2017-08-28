package view.custom;

import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.control.SplitPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

public class DoubleSwappablePane extends Pane{
	private SplitPane splitPane;
	private Pane pane1;
	private Pane pane2;

	private double width;
	private double height;
	private Orientation orientation;

	public DoubleSwappablePane(double w, double h, Pane paneA, Pane paneB, Orientation orien) {
		width = w;
		height = h;
		orientation = orien;
		this.setPrefSize(width, height);
		splitPane = new SplitPane();
		splitPane.setOrientation(orientation);
		this.pane1 = paneA;
		this.pane2 = paneB;

		if (orientation.equals(Orientation.HORIZONTAL)) {
			pane1.setPrefSize(width / 2, height);
			pane2.setPrefSize(width / 2, height);
		} else if (orientation.equals(Orientation.VERTICAL)) {
			pane1.setPrefSize(width, height / 2);
			pane2.setPrefSize(width, height / 2);
		}

		splitPane.getItems().addAll(pane1, pane2);
		this.getChildren().add(splitPane);

		initialize();
	}
	
	public void setDividerPercentPosition(double percent) {
		splitPane.setDividerPosition(0, percent);
	}

	private void initialize() {
		dragDetected(pane1, 1);
		dragDetected(pane2, 2);

		dragOver(pane1);
		dragOver(pane2);

		dragDropped(pane1);
		dragDropped(pane2);
	}

	private void dragDetected(Pane pane, Integer paneNum) {
		pane.setOnDragDetected(event -> {
			Dragboard dragBoard = pane.startDragAndDrop(TransferMode.ANY);
			ClipboardContent content = new ClipboardContent();
			content.putString(paneNum.toString());
			dragBoard.setContent(content);
			event.consume();
		});
	}

	private void dragOver(Pane pane) {
		pane.setOnDragOver(event -> {
			if (event.getDragboard().hasString()) {
				if((event.getDragboard().getString().equals("1") && pane.equals(pane2))||
				   (event.getDragboard().getString().equals("2") && pane.equals(pane1)))
				event.acceptTransferModes(TransferMode.ANY);
			}
			event.consume();
		});
	}

	private void dragDropped(Pane pane) {
		pane.setOnDragDropped(event -> {
			boolean success = false;
			if (event.getDragboard().hasString()) {
				splitPane.getItems().clear();
				Point2D local = pane.sceneToLocal(event.getSceneX(), event.getSceneY());
				if (splitPane.getOrientation().equals(Orientation.HORIZONTAL)) {
					splitPane.setOrientation(Orientation.VERTICAL);
					splitPane.setDividerPosition(0, 0.5d);
					pane1.setPrefSize(width, height / 2);
					pane2.setPrefSize(width, height / 2);

					if ((upper(local) && pane.equals(pane2)) || !upper(local) && pane.equals(pane1)) {
						splitPane.getItems().addAll(pane1, pane2);
					} else {
						splitPane.getItems().addAll(pane2, pane1);
					}
				} else {
					splitPane.setOrientation(Orientation.HORIZONTAL);
					splitPane.setDividerPosition(0, 0.5d);
					pane1.setPrefSize(width / 2, height);
					pane2.setPrefSize(width / 2, height);
					if ((left(local) && pane.equals(pane2)) || !left(local) && pane.equals(pane1)) {
						splitPane.getItems().addAll(pane1, pane2);
					} else {
						splitPane.getItems().addAll(pane2, pane1);
					}
				}
				success = true;
			}
			event.setDropCompleted(success);
			event.consume();
		});

	}

	private boolean upper(Point2D point) {
		return point.getY() < height / 2;
	}

	private boolean left(Point2D point) {
		return point.getX() < width / 2;
	}

}
