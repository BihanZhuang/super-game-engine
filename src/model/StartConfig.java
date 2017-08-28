package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameobject.IGameObject;
import gameobject.ObjectInfo;
import gameobject.Template;
import util.IDControl;
import util.ObservableBase;
import util.Observer;
import util.Replicate;

public class StartConfig extends ObservableBase<ObjectInfo> implements Replicate<StartConfig> {
    
    private Map<Integer, ObjectInfo> myConfig;
    private IDControl myID;
    private Observer<IGameObject> myConfigObserver;
    
    public StartConfig() {
        myConfig = new HashMap<>();
        myID = new IDControl();
        myConfigObserver = obj -> {
            if (myConfig.values().contains(obj) && obj.expired()) {
                myConfig.remove(obj.getID());
                myID.recycle(obj.getID());
                notifyObservers(obj);
            }
        };
    }

    public int size() {
        return myConfig.size();
    }

    public ObjectInfo addConfig(Template template, double x, double y) {
        IGameObject obj = template.newInstance(myID.nextId());
        obj.removeAllComponents();
        obj.getPosition().set(x, y);
        myConfig.put(obj.getID(), obj);
        obj.addObserver(myConfigObserver);
        notifyObservers(obj);
        return obj;
    }

    public Iterable<ObjectInfo> getAllConfig() {
        return myConfig.values();
    }
    
    public ObjectInfo chooseRandom() {
        List<ObjectInfo> choices = new ArrayList<>(myConfig.values());
        Collections.shuffle(choices);
        return choices.get(0);
    }

    @Override
    public void copyFrom(StartConfig other) {
        new ArrayList<>(myConfig.values()).forEach(ObjectInfo::setExpired);
        myID = other.myID;
        other.getAllConfig().forEach(config -> {
            myConfig.put(config.getID(), config);
            notifyObservers(config);
        });
    }

}
