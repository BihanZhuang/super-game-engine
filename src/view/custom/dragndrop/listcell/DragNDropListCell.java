package view.custom.dragndrop.listcell;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 * ListCells that support drag and drop to reposition.
 * To achieve more flexibility (i.e. data type of not only String),
 * need to implement DataFormat in the ClipboardContent container
 * @author Feng
 *
 * @param <String>
 */
public class DragNDropListCell extends ListCell<String>{
	private static final Double DRAG_ENTER_OPACITY = 0.3d;
	private static final Double DRAG_EXIT_OPACITY = 1d;
	
	public DragNDropListCell(){
		ListCell<String> thisCell = this;
		setAlignment(Pos.CENTER);
		
		setOnDragDetected(event -> {
			if(getItem() == null)
				return;
			Dragboard dragBoard = startDragAndDrop(TransferMode.ANY);
			ClipboardContent content = new ClipboardContent();
			content.putString(getItem());
			dragBoard.setContent(content);
			event.consume();
		});
		
		setOnDragOver(event -> {
			if(event.getGestureSource() != thisCell &&
					event.getDragboard().hasString()){
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});
		
		setOnDragEntered(event -> {
			if(event.getGestureSource() != thisCell && 
					event.getDragboard().hasString())
				setOpacity(DRAG_ENTER_OPACITY);
		});
		
		setOnDragExited(event -> {
            if (event.getGestureSource() != thisCell &&
                    event.getDragboard().hasString()) {
                setOpacity(DRAG_EXIT_OPACITY);
            }
        });
		
		setOnDragDropped(event -> {
			if(getItem() == null)
				return;
			Dragboard dragBoard = event.getDragboard();
			boolean success = false;
			if(dragBoard.hasString()){
				ObservableList<String> items = getListView().getItems();
				int sourceIndex = items.indexOf(dragBoard.getString());
				int targetIndex = items.indexOf(getItem());
				
				items.set(sourceIndex, getItem());
				items.set(targetIndex, dragBoard.getString());
				
				List<String> itemsCopy = new ArrayList<>(items);
				getListView().getItems().setAll(itemsCopy);				
			    
				success = true;
			}
			event.setDropCompleted(success);
			event.consume();
		});
		
		 setOnDragDone(event -> event.consume());
	}
	
	@Override 
	protected void updateItem(String item, boolean empty){
		super.updateItem(item, empty);
		if(item == null|| empty)
			return;
		else{
			setText(item);
		}
	}

}
