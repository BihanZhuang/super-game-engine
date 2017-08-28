package view.editor.view.decorator;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.view.ConstraintsFactory;
import view.editor.base.DecoratorBase;
import view.editor.base.EditorBase;
import view.editor.menu.EditorMenuBar;

public class MenuBarDecorator extends DecoratorBase<GridPane> {
	private static final Integer ROW_CONSTRAINT1 = 6;
	private static final Integer ROW_CONSTRAINT2 = 94;
	private EditorBase<?> myBase;

	public MenuBarDecorator(EditorBase<?> base) {
		super(new GridPane(), base);
		myBase = base;
		initView(base.getWidth(), base.getHeight(), base.getStage());
	}

	private void initView(double width, double height, Stage stage) {
		this.getView().getRowConstraints().addAll(ConstraintsFactory.getRowConstraints(ROW_CONSTRAINT1),
				ConstraintsFactory.getRowConstraints(ROW_CONSTRAINT2));

		EditorMenuBar editorMenuBar = new EditorMenuBar(width, stage, myBase.getGame(), myBase.getInventory());
		myBase.getView().setPrefHeight(height - editorMenuBar.getView().getPrefHeight());
		getView().add(editorMenuBar.getView(), 0, 0);
		getView().add(myBase.getView(), 0, 1);
		editorMenuBar.getView().toFront();
	}

}
