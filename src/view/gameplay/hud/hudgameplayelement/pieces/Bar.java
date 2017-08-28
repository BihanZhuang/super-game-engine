package view.gameplay.hud.hudgameplayelement.pieces;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Bar used for hud 
 * @author gabrielchen
 *
 */
public class Bar {
	
//	public static final double FULL_BAR_LENGTH = 100;
	
	public static final double DEFAULT_BAR_HEIGHT = 20;
	public static final double DEFAULT_BAR_STROKE_WIDTH = 1;
	public static final Color DEFAULT_BAR_STROKE_COLOR = Color.BLACK;
	
	private double myBarMaxValue, myBarCurrValue, myBarLength;
	
	private double currBarPercentage;
	
	private Pane barWrapper;
	private Rectangle rectMaxValue, rectCurrValue;
	
	private String rectCurrValueColor, textCurrValueColor;
	
	
	public Bar(double barMaxLength, double barHeight, double maxValue, double currValue, String barColor, String textColor){
		myBarLength = barMaxLength;
		myBarMaxValue = maxValue;
		myBarCurrValue = currValue;
		rectCurrValueColor = barColor;
		textCurrValueColor = textColor;
		
		barWrapper = new Pane();
		createBar();
	}
	
	public Pane getBarPane(){
		return barWrapper;
	}
	
	/*
	 * For updating the maximum value that can be set in the bar
	 */
	public void updateBarMax(double newMaxValue){
		myBarMaxValue = newMaxValue;
		calculateCurrBarPercentage();
		updateBarVisual();
	}
	
	/*
	 * For updating the current length of the bar (ie. when taking damage)
	 */
	public void updateCurrBarValue(double newCurrValue){
		if(newCurrValue > myBarMaxValue){
			myBarCurrValue = myBarMaxValue;
		} else{
			myBarCurrValue = newCurrValue;
		}
		if(newCurrValue < 0){
			myBarCurrValue = 0;
		}
		calculateCurrBarPercentage();
		updateBarVisual();
	}
	
	/*
	 * update the exact percentage of the bar that you want
	 * maybe this could be useful if u want to reset the health to 100%
	 */
	public void updateCurrBarPercentage(double percentage){
		currBarPercentage = percentage;
		updateBarVisual();
	}
	
	/*
	 * updates the visuals of the bar upon calls that change the curr bar value and the max bar value
	 */
	private void updateBarVisual(){
		rectCurrValue.setWidth(myBarLength * currBarPercentage);
	}
	
	/*
	 * make the bar visual
	 */
	private void createBar(){
		calculateCurrBarPercentage();
		
		rectMaxValue = createRectangle(myBarLength, "#ffffff", 0);
		rectCurrValue = createRectangle(myBarLength * currBarPercentage, rectCurrValueColor, 1);
		
		barWrapper.getChildren().addAll(rectMaxValue, rectCurrValue);
	}
	
	private Rectangle createRectangle(double rectangleWidth, String fillColor, double opacity){
		Rectangle ret = new Rectangle(rectangleWidth, DEFAULT_BAR_HEIGHT);
		ret.setFill(Color.web(fillColor));
		ret.setStrokeWidth(DEFAULT_BAR_STROKE_WIDTH);
		ret.setStroke(DEFAULT_BAR_STROKE_COLOR);
		ret.setX(0);
		ret.setY(0);
		return ret;
	}
	
	private void calculateCurrBarPercentage(){
		currBarPercentage = myBarCurrValue/myBarMaxValue;
	}
	
}
