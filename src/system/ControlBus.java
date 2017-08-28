package system;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import gameobject.IGameObject;
import gameobject.component.type.ComponentTypes;
import javafx.scene.input.KeyCode;
import util.IDControl;
import util.VoogaException;

public class ControlBus {

    private Map<Integer, UserControl> myControllers;
    private IDControl idControl;
    
    public ControlBus() {
        myControllers = new HashMap<>();
        idControl = new IDControl();
    }
    
    public int addControl(IGameObject obj) {
        if(obj.has(ComponentTypes.CHAMPION)) {
            int id = idControl.nextId();
            myControllers.put(id, new UserControl(obj, 
            									  obj.getComponent(ComponentTypes.CHAMPION).getHSpeed(),
            									  obj.getComponent(ComponentTypes.CHAMPION).getVSpeed()));
            return id;
        } else {
            throw new VoogaException("Object is not Champion");
        }
    }
    
    public void changeControl(Integer id, IGameObject champion) {
        if(myControllers.containsKey(id)) {
            myControllers.get(id).setChampion(champion);
        } else {
            throw new VoogaException("Controller does not exist");
        }
    }
    
    public Iterable<Integer> getIDs() {
        return myControllers.keySet();
    }
    
    public IGameObject getChampion(Integer id) {
        if(myControllers.containsKey(id)) {
            return myControllers.get(id).getChampion();
        } else {
            throw new VoogaException("Controller does not exist");
        }
    }
    
    public Iterable<IGameObject> getChampions() {
        return myControllers.values().stream().map(UserControl::getChampion).collect(Collectors.toList());
    }
    
    public void handleKeyPressed(Integer id, KeyCode code) {
        myControllers.get(id).onKeyPrssed(code);
    }
    
    public void handleKeyReleased(Integer id, KeyCode code) {
        myControllers.get(id).onKeyReleased(code);
    }
    
}
