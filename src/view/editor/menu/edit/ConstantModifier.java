package view.editor.menu.edit;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import model.Environment;
import model.Environment.Constant;
import model.WorldInfo;

public class ConstantModifier {
	private Environment environment;
    private Stage stage;
	private ObservableList<Constant> constants;
    
	public ConstantModifier(WorldInfo world) {
		environment = world.getEnvironment();
		constants = FXCollections.observableArrayList(Constant.values());
		stage = new Stage();
		stage.setScene(createScene());	
	}
	
	public void show(){
		stage.show();
	}
	
	private Scene createScene() {
		Pane pane = new Pane();
		pane.setPrefSize(200, 200);
		VBox vbox = new VBox();
		vbox.setPrefSize(pane.getPrefWidth(),pane.getPrefHeight());
		vbox.getChildren().add(createTableView());
		pane.getChildren().add(vbox);
		Scene scene = new Scene(pane);
		return scene;
	}
	
	private TableView<Constant> createTableView() {
		TableView<Constant> constantsTable = new TableView<>(constants);
		fillTable(constantsTable);
		return constantsTable;
	}
	
	private void fillTable(TableView<Constant> tableVariables) {
		// http://docs.oracle.com/javafx/2/ui_controls/table-view.htm#CJAGDAHE
		// http://stackoverflow.com/questions/20020037/editing-a-number-cell-in-a-tableview
		tableVariables.setEditable(true);
		TableColumn<Constant, String> variableNameCol = new TableColumn<Constant, String>("Constant");
		variableNameCol.setCellValueFactory(p -> {
			return new ReadOnlyObjectWrapper(p.getValue().toString());
		});

		TableColumn<Constant, Double> variableValueCol = new TableColumn<Constant, Double>("Value");
		variableValueCol.setCellValueFactory(p -> {
			double value = environment.getConstant(p.getValue());
			return new ReadOnlyObjectWrapper(value);
		});
		variableValueCol
				.setCellFactory(TextFieldTableCell.<Constant, Double>forTableColumn(new DoubleStringConverter()));
		variableValueCol.setOnEditCommit(new EventHandler<CellEditEvent<Constant, Double>>() {
			@Override
			public void handle(CellEditEvent<Constant, Double> t) {
				environment.setConstant(((Constant) t.getTableView().getItems().get(t.getTablePosition().getRow())), t.getNewValue());
			}
		});
		
		tableVariables.getColumns().addAll(variableNameCol, variableValueCol);
	}

}
