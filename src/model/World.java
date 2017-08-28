package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import gameobject.IGameObject;
import gameobject.ObjectInfo;
import gameobject.Template;
import gameobject.component.Belongings;
import gameobject.component.type.ComponentTypes;
import util.IDControl;
import util.Observable;
import util.ObservableBase;
import util.Observer;
import util.VoogaException;

public class World extends ObservableBase<IGameObject> implements IWorld, Observer<IGameObject> {

    private Map<Integer, IGameObject> myObjects;
    private Observable<ObjectInfo> myInfoObservable;
    private StartConfig myStartConfig;
    private Environment myEnv;
    private IDControl myID;
    private Boundary myBoundary;

    public World() {
        myObjects = new HashMap<>();
        myInfoObservable = new ObservableBase<>();
        myID = new IDControl();
        myStartConfig = new StartConfig();
        myEnv = new Environment();
        myBoundary = new Boundary(0, 0, 1600, 5000);
    }

    @Override
    public int size() {
        return myObjects.size();
    }

    @Override
    public StartConfig getStartConfig() {
        return myStartConfig;
    }
    
    @Override
    public Boundary getBoundary() {
        return myBoundary;
    }

    @Override
    public Environment getEnvironment() {
        return myEnv;
    }

    @Override
    public IGameObject addChampion(Template template) {
        ObjectInfo start = myStartConfig.chooseRandom();
        IGameObject champion = addObject(template, start.getPosition().getX(), start.getPosition().getY(),
                temp -> temp.has(ComponentTypes.CHAMPION), "Template is not Champion");
        champion.getDimension().set(start.getDimension().getX(), start.getDimension().getY());
        return champion;
    }

    @Override
    public void copyFrom(WorldInfo other) {
        clear();
        for (IGameObject obj : other) {
            if (!obj.has(ComponentTypes.CHAMPION)) {
                addObject(new Template(obj));
            }
        }
        myStartConfig.copyFrom(other.getStartConfig());
        myEnv.copyFrom(other.getEnvironment());
        myBoundary = other.getBoundary();
    }

    public void clear() {
        removeIf(obj -> true);
    }

    @Override
    public IGameObject addObject(Template template) {
        return addObject(template, template.getPosition().getX(), template.getPosition().getY());
    }

    @Override
    public IGameObject addObject(Template template, double x, double y) {
        return addObject(template, x, y, temp -> !temp.has(ComponentTypes.CHAMPION), "Template is Champion");
    }

    @Override
    public IGameObject getObject(int id) {
        return myObjects.get(id);
    }

    @Override
    public Collection<IGameObject> getIf(Predicate<IGameObject> predicate) {
        Set<IGameObject> ret = new HashSet<>();
        myObjects.values().stream().filter(predicate).forEach(ret::add);
        return ret;
    }

    @Override
    public Collection<IGameObject> removeIf(Predicate<IGameObject> predicate) {
        Collection<IGameObject> toRemove = getIf(predicate);
        toRemove.forEach(IGameObject::setExpired);
        return toRemove;
    }

    @Override
    public void notifyObservers(IGameObject arg) {
        super.notifyObservers(arg);
        myInfoObservable.notifyObservers(arg);
    }

    @Override
    public void addInfoObserver(Observer<ObjectInfo> obs) {
        myInfoObservable.addObserver(obs);
    }

    @Override
    public void removeInfoObserver(Observer<ObjectInfo> obs) {
        myInfoObservable.removeObserver(obs);
    }

    @Override
    public void notifyInfoObservers(ObjectInfo arg) {
        myInfoObservable.notifyObservers(arg);
    }

    @Override
    public void update(IGameObject arg) {
        if (myObjects.values().contains(arg) && arg.expired()) {
            myObjects.remove(arg.getID());
            myID.recycle(arg.getID());
            notifyInfoObservers(arg);
        }
    }

    @Override
    public Iterator<IGameObject> iterator() {
        return myObjects.values().iterator();
    }

    private IGameObject addObject(Template template, double x, double y, Predicate<Template> filter, String message) {
        if (filter.test(template)) {
            IGameObject obj = template.newInstance(myID.nextId());
            obj.getPosition().set(x, y);
            addAndNotify(obj.getID(), obj);
            if (template.has(ComponentTypes.BELONGINGS)) {
                addBelongings(obj, template.getComponent(ComponentTypes.BELONGINGS));
            }
            return obj;
        } else {
            throw new VoogaException(message);
        }
    }

    private void addBelongings(IGameObject owner, Belongings belongings) {
        belongings.getBelongings().forEach(b -> {
            IGameObject obj = copyAndAdd(b);
            if (b.has(ComponentTypes.BELONGINGS)) {
                addBelongings(obj, b.getComponent(ComponentTypes.BELONGINGS));
            }
            owner.getComponent(ComponentTypes.BELONGINGS).addBelonging(obj);
        });
    }

    private IGameObject copyAndAdd(IGameObject obj) {
        int id = myID.nextId();
        IGameObject ret = obj.copy(id);
        addAndNotify(id, ret);
        return ret;
    }

    private void addAndNotify(int id, IGameObject obj) {
        myObjects.put(id, obj);
        obj.addObserver(this);
        notifyObservers(obj);
    }

}