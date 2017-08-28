package view.editor.hud;

import hudelement.HUDElement;

public class HUDEditorOverlord {
	private String currentMold;
	private HUDElement currentHUDElement;
	
	public HUDEditorOverlord(){
		currentMold = "";
	}
	
	/**
	 * sets the current HUD element that is to be used by the parts
	 * of the HUDEditor
	 * @param element
	 */
	public void setCurrentHUDElement(HUDElement element){
		currentHUDElement = element;
	}
	
	/**
	 * update the attributes Window to reflect the current selected
	 * HUDElement
	 */
	private void updateAttributesWindow(){
		
	}
	
	/**
	 * sets the mold as held by the HUDEditorController to a String
	 * that is the name of the corresponding HUDElementMold Class
	 * @param moldType
	 */
	public void setCurrentMold(String moldType){
		currentMold = moldType + "ElementMold";
	}
	
	public String getCurrentMold(){
		return currentMold;
	}
}
