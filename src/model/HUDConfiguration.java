package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import hudelement.HUDElement;

public class HUDConfiguration implements Iterable<HUDElement> {
	
	private Set<HUDElement> myElements;
	
	// Holds the hud elements
	public HUDConfiguration(){
		super();
		myElements = new HashSet<>();
	}
	
	/**
	 * replace the HUDElements that are set by the game maker
	 * @param elementsConfig
	 */
	public void replaceElementsSet(Set<HUDElement> elementsConfig){
		myElements = elementsConfig;
	}

    @Override
    public Iterator<HUDElement> iterator() {
        return myElements.iterator();
    }
	
	/** Observing some sort of interaction engine that might want to 
	 * pop up some sort of hud element like dialogue, etc.
	 */
//	@Override
//	public void update(HUDElement arg) {
//		if(arg.expired()){
//			removeAndNotify(arg);
//		} else if (!myElements.contains(arg)){
//			addAndNotify(arg);
//		} else {
//			notifyObservers(arg);
//		}
//	}
//	
//	/**
//	 * notifies observers and adds the HUDElement
//	 * @param h
//	 */
//	private void addAndNotify(HUDElement h){
//        myElements.add(h);
//		notifyObservers(h);
//	}
//	
//	/**
//	 * notifies observers and removes the HUDElement
//	 * @param h
//	 */
//	private void removeAndNotify(HUDElement h){
//        myElements.remove(h);
//		notifyObservers(h);
//	}

}
