package model;

import java.util.Iterator;

import gameobject.IGameObject;
import gameobject.ObjectInfo;
import gameobject.Template;
import util.Observer;

public interface WorldInfo extends Iterable<IGameObject> {
    
    /**
     * Returns the number of objects in the world.
     * @return
     */
    int size();
    
    StartConfig getStartConfig();
    
    Boundary getBoundary();
    
    void clear();
    
    ObjectInfo addObject(Template template);
    
    ObjectInfo addObject(Template template, double x, double y);

    ObjectInfo getObject(int id);

    Environment getEnvironment();
    
    void addInfoObserver(Observer<ObjectInfo> obs);
    
    void removeInfoObserver(Observer<ObjectInfo> obs);
    
    void notifyInfoObservers(ObjectInfo arg);

    @Override
    Iterator<IGameObject> iterator();

}