package view.editor.base;

import javafx.scene.layout.Pane;
import view.PaneViewBase;

public abstract class EditorBase<T extends Pane> extends PaneViewBase<T> implements IEditor{

	public EditorBase(T node, double width, double height) {
		super(node, width, height);
	}

}
