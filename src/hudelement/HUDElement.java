package hudelement;

import java.io.Serializable;

import gameobject.ObjectInfo;
import view.gameplay.hud.hudgameplayelement.HUDGameplayElement;

/**
 * represents a hud element, such as a bar, a static image, a score display, etc
 * @author Gabriel
 *
 */
public abstract class HUDElement implements Serializable{
	private double myXPos;
	private double myYPos;
	private double myHeight;
	private double myWidth;
	private int myID;
	private boolean expired;
	private String myType;
	

	public HUDElement(double xPos, double yPos, double h, double w, String type){
		expired = false;
		myXPos = xPos;
		myYPos = yPos;
		myHeight = h;
		myWidth = w;
		myType = type;
	}
	
	/**
	 * implemented by all HUDElements to be used for making a gameplay version that
	 * contains unserializable javafx elements
	 */
	public abstract HUDGameplayElement createGameplayInstance(ObjectInfo hero);
	
	/**
	 * sets the type of the HUDElement
	 * @param type
	 */
	public void setType(String type){
		myType = type;
	}
	
	/**
	 * returns expiry status
	 * @return
	 */
	public boolean expired(){
		return expired;
	}
	
	/**
	 * set this hud element to expired
	 */
	public void setExpired(){
		expired = true;
	}
	
	/**
	 * set the xPosition of the HUDElement
	 * @param x
	 */
	public void setXPos(double x){
		myXPos = x;
	}
	
	/**
	 * set the yPosition of the HUDElement
	 * @param y
	 */
	public void setYPos(double y){
		myYPos = y;
	}
	
	/**
	 * set height of the HUDElement
	 * @param h
	 */
	public void setHeight(double h){
		myHeight = h;
	}
	
	/**
	 * set the width of the HUDElement
	 * @param w
	 */
	public void setWidth(double w){
		myWidth = w;
	}
	
	/**
	 * returns height of HUDElement
	 * @return
	 */
	public double getHeight(){
		return myHeight;
	}
	
	/**
	 * returns height of HUDElement
	 * @return
	 */
	public double getWidth(){
		return myWidth;
	}
	
	/**
	 * returns YPos of HUDElement
	 * @return
	 */
	public double getXPos(){
		return myXPos;
	}
	
	/**
	 * returns YPos of HUDElement
	 * @return
	 */
	public double getYPos(){
		return myYPos;
	}
	
	/**
	 * returns the unique id of the HUDElement as designated by the overseeing manager
	 * @return
	 */
	public int getID(){
		return myID;
	}
	
	protected String getType(){
		return myType;
	}
	

}
