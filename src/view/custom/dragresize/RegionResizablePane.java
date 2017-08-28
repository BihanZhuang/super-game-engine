package view.custom.dragresize;

import javafx.scene.layout.Region;

public class RegionResizablePane extends ResizablePane {

    public RegionResizablePane(Region region) {
        this(region, 1);
    }

    public RegionResizablePane(Region region, double unit) {
        super(region.getPrefWidth(), region.getPrefHeight());
        getHost().setLayoutX(region.getLayoutX());
        getHost().setLayoutY(region.getLayoutY());
        getHost().setTranslateX(region.getTranslateX());
        getHost().setTranslateY(region.getTranslateY());
        getHost().getChildren().add(region);
        region.prefWidthProperty().bind(getHost().prefWidthProperty());
        region.prefHeightProperty().bind(getHost().prefHeightProperty());
        region.setLayoutX(0);
        region.setLayoutY(0);
        setUnit(unit);
    }

}
