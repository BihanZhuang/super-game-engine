package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import gameobject.Template;
import gameobject.basics.Hero;
import gameobject.component.type.ComponentTypes;
import util.VoogaException;

/**
 * Stores and manages all the possible champions in the game.
 * 
 * @author Mike Liu
 *
 */
public class ChampionPool implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, Template> myChampions;
    
    public ChampionPool() {
        myChampions = new HashMap<>();
        addChampion(new Hero("mario.gif"));
    }

    /**
     * Adds a champion represented by the <code>Template</code>.
     * 
     * @param template
     */
    public void addChampion(Template template) {
        if (template.has(ComponentTypes.CHAMPION)) {
            myChampions.put(template.getName(), template);
        } else {
            throw new VoogaException("Template is not Champion");
        }
    }

    public boolean containsChampion(String name) {
        return myChampions.containsKey(name);
    }

    public Template getChampion(String name) {
        return myChampions.get(name);
    }
    
    public void clearAll(){
    	myChampions.clear();
    }
    
    public Collection<Template> getChampions(){
    	return myChampions.values();
    }

}
