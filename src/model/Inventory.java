package model;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import gameobject.Template;
import util.QuickNotifyObservable;
import util.Serializer;
import util.TextIO;
import util.VoogaException;

/**
 * This is the storage of all possible game objects in the game. Retrieval of
 * items is based on either template type or object name.
 * 
 * @author Mike Liu
 *
 */
public class Inventory extends QuickNotifyObservable<Inventory> implements Iterable<Template> {

    private Map<String, Template> myTemplates;
    private Map<String, Set<String>> typeToName;
    private Serializer mySerializer;
    private TextIO myIO;

    public Inventory() {
        myTemplates = new HashMap<>();
        typeToName = new HashMap<>();
        mySerializer = new Serializer();
        myIO = new TextIO();
    }

    public void addTemplate(Template template) {
        String type = template.getType();
        String name = template.getName();
        myTemplates.put(name, template);
        if (!typeToName.containsKey(type)) {
            typeToName.put(type, new HashSet<>());
        }
        typeToName.get(type).add(name);
        notifyObservers();
    }

    public boolean containsTemplate(String name) {
        return myTemplates.containsKey(name);
    }

    public Template getTemplate(String name) {
        return myTemplates.get(name);
    }

    public void removeTemplate(Template template) {
        if (typeToName.containsKey(template.getType())) {
            typeToName.get(template.getType()).remove(template.getName());
        }
        myTemplates.remove(template.getName());
        notifyObservers();
    }

    public boolean containsType(String type) {
        return typeToName.containsKey(type);
    }

    public Iterable<String> getTypes() {
        return typeToName.keySet();
    }

    public Iterable<String> getNamesOfType(String type) {
        if (typeToName.containsKey(type)) {
            return typeToName.get(type);
        }
        throw new VoogaException("Template of type %s does not exist.", type);
    }

    public void saveInventory(File file) {
        VoogaException.safeIO(() -> myIO.save(file, mySerializer.toXML(myTemplates.values())));
    }

    @SuppressWarnings("unchecked")
    public void loadInventory(File file) {
        ((Collection<Template>) VoogaException.safeIO(() -> mySerializer.fromXML(myIO.load(file), Collection.class)))
        .forEach(this::addTemplate);
    }

    public void loadTemplate(File file) {
        addTemplate(VoogaException.safeIO(() -> mySerializer.fromXML(myIO.load(file), Template.class)));
    }

    @Override
    public Iterator<Template> iterator() {
        return myTemplates.values().iterator();
    }

	@Override
	protected Inventory notification() {
		return this;
	}

}
