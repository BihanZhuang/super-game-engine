package view.custom.dragndrop.listcell;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 * 
 * @author
 * @author Mike Liu
 *
 */
public class ImageListCell extends DragNDropListCell {
    public static final String STYLE_CLASS = "imageListCell";
    private List<Image> myImages;

    public ImageListCell(List<Image> images) {
        super();
        this.getStyleClass().add(STYLE_CLASS);
        myImages = images;
        setOnDragDetected(event -> {
            if (getItem() != null) {
                ObservableList<String> items = getListView().getItems();
                Dragboard dragBoard = startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(getItem());

                // TODO
                // DragContext dragContext = new DragContext();
                // dragContext.mouseAnchorX = event.getSceneX() -
                // this.getBoundsInParent().getMinX();
                // dragContext.mouseAnchorY = event.getSceneX() -
                // this.getParent().getParent().getLayoutY();
                //
                // System.out.println("MouseAnchorX: " + dragContext.mouseAnchorX);
                // System.out.println("MouseAnchorY: " + dragContext.mouseAnchorY);
                // content.put(DragContextData, dragContext);

                dragBoard.setDragView(images.get(items.indexOf(getItem())));
                dragBoard.setContent(content);
                event.consume();
            }
        });

        setOnDragDropped(event -> {
            if (getItem() == null)
                return;
            Dragboard dragBoard = event.getDragboard();
            boolean success = false;
            if (dragBoard.hasString()) {
                ObservableList<String> items = getListView().getItems();
                int sourceIndex = items.indexOf(dragBoard.getString());
                int targetIndex = items.indexOf(getItem());

                Image srcImg = images.get(sourceIndex);
                images.set(sourceIndex, images.get(targetIndex));
                images.set(targetIndex, srcImg);

                items.set(sourceIndex, getItem());
                items.set(targetIndex, dragBoard.getString());

                List<String> itemsCopy = new ArrayList<>(items);
                getListView().getItems().setAll(itemsCopy);

                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setGraphic(new ImageView(myImages.get(getListView().getItems().indexOf(item))));
        }
    }

}
