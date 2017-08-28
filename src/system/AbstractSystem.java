package system;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import engine.PhysicsEngine;
import gameobject.IGameObject;
import gameobject.ObjectInfo;
import gameobject.component.type.ComponentType;

public abstract class AbstractSystem implements GameSystem {


    private Collection<IGameObject> myObjects;
    private PhysicsEngine myEngine;
    private Collection<ComponentType<?>> myTypes;
    private ISystemInfo myInfo;
    private Collection<IGameObject> toRemove;

    @SafeVarargs
    public AbstractSystem(ISystemInfo info, PhysicsEngine engine, ComponentType<?>... types) {
        myInfo = info;
        myObjects = new HashSet<>();
        myTypes = new HashSet<ComponentType<?>>(Arrays.asList(types));
        myEngine = engine;
        toRemove = new HashSet<>();
    }

    @Override
    public void step(double elapsedTime) {
        myObjects.stream().filter(obj -> appliesTo(obj)).forEach(obj -> {
        	singleStep(obj, elapsedTime);
//    		if(obj.has(ComponentTypes.CHAMPION)) {
//    			System.out.println(getClass());
//    			System.out.println(System.identityHashCode(obj));
//    			System.out.println("id " + obj.getID() + " " + obj.getComponent(ComponentTypes.VELOCITY).getX());
//    		}
        });
        myObjects.removeAll(toRemove);
        toRemove.clear();
    }

    @Override
    public void update(IGameObject arg) {
        if(arg.expired() || !appliesTo(arg)) {
            toRemove.add(arg);
        } else {
            myObjects.add(arg);
        }
        arg.addObserver(this);
    }

    protected ISystemInfo getSystemInfo() {
        return myInfo;
    }

    protected PhysicsEngine getEngine() {
        return myEngine;
    }

    protected boolean appliesTo(ObjectInfo obj) {
        return obj.hasAll(myTypes);
    }

    protected abstract void singleStep(IGameObject obj, double elapsedTime);

}