package view.editor.attribute;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import view.IView;
import view.editor.componentwindow.ComponentData;

public class AttributeSlider implements IView<HBox>{
	private static final Integer PADDING = 10;
	private HBox myHBox;
	private ComponentData componentdata;
	double myMin;
	double myMax;
	double myXPos;
	double myYPos;
	Slider mySlider;
	Label myAttribute;
	Label myValue;
	
	public AttributeSlider(String attribute, double min, double max){
		myHBox = new HBox();
		myHBox.setPadding(new Insets(PADDING));
		myHBox.setPrefSize(myHBox.getPrefWidth(), 40);
		myMin= min;
		myMax = max;
		generateSlider();
		generateTitle(attribute);		
		compile();
	}

	
	public AttributeSlider(String attribute, double min, double max, ComponentData componentdata){
		myHBox = new HBox();
		myHBox.setPadding(new Insets(PADDING));
		myHBox.setPrefSize(myHBox.getPrefWidth(), 40);
		myMin= min;
		myMax = max;
		this.componentdata=componentdata;
		generateSlider();
		generateTitle(attribute);		
		compile();
	}

	private void generateSlider() {
		mySlider = new Slider(myMin,myMax, (myMin+myMax)/2);
		mySlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				//System.out.println(myAttribute.getText()+" "+newValue.toString());
				if (componentdata!=null) {
					componentdata.put(myAttribute.getText(), newValue.toString());
				}
			}
		});
		mySlider.setShowTickMarks(true);
		mySlider.setShowTickLabels(true);
		mySlider.lookup(".thumb");//.setStyle("-fx-background-image :url(http://icons.iconarchive.com/icons/double-j-design/diagram-free/128/piggy-bank-icon.png)");
		myValue = new Label();
		myValue.textProperty().bind(Bindings.format("%.1f",mySlider.valueProperty()));
	}
	
	public int getValue(){
		return (int)mySlider.getValue();
	}
	
	private void generateTitle(String attribute){
		myAttribute = new Label(attribute);
	}
	
	private void compile(){
		myHBox.setLayoutX(myXPos);
		myHBox.setLayoutY(myYPos);
		myHBox.getChildren().addAll(myAttribute, mySlider, myValue);
	}
	
	@Override 
	public HBox getView(){
		return myHBox;
	}
	
	public Slider getSlider(){
		return mySlider;
	}
	
	public void setValue(double value){
		mySlider.setValue(value);
	}
}
