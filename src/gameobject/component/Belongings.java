package gameobject.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import gameobject.IGameObject;
import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;

public class Belongings implements Component {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Collection<IGameObject> myBelongings;

    public Belongings() {
        myBelongings = new ArrayList<>();
    }
    
    public Belongings(Belongings a) {
    	this.myBelongings=new ArrayList<>();
    	//this.myBelongings=a.myBelongings;
    }

    public void addBelonging(IGameObject obj) {
        myBelongings.add(obj);
    }

    public Collection<IGameObject> getBelongings() {
        return Collections.unmodifiableCollection(myBelongings);
    }
    
    public void clearBelongings() {
    	for(IGameObject obj : myBelongings) {
    		obj.setExpired();
    	}
    	myBelongings.clear();
    }

    /**
     * Recursively collects all the items of this <code>Belongings</code> and
     * the belongings of its items, if any.
     * 
     * @return
     */
    public Collection<IGameObject> getChildren() {
        Collection<IGameObject> children = new ArrayList<>();
        for (IGameObject obj : myBelongings) {
            children.add(obj);
            if (obj.has(ComponentTypes.BELONGINGS)) {
                children.addAll(obj.getComponent(ComponentTypes.BELONGINGS).getChildren());
            }
        }
        return children;
    }
    
    public void attack(double x, double y) {
		System.out.println("Birth");
		
    	for (IGameObject obj : myBelongings) {
    		if (obj.has(ComponentTypes.ATTACK) && obj.has(ComponentTypes.GENERATOR)) {
    			Generator g = obj.getComponent(ComponentTypes.GENERATOR);
    			g.setGenerate(true);
    			g.setGenerateCheck(true);
    			System.out.println("Birth");
    			g.setGeneratePosX(x);
    			g.setGeneratePosY(y);
    		}
    	}
    }
    
    /*public void useitems(IGameObject user) {
    	for (IGameObject obj: myBelongings) {
    		if (obj.has(ComponentTypes.COLLIDE)) {
    			List<BiConsumer<IGameObject, IGameObject>> effectList = obj.getComponent(ComponentTypes.COLLIDE).test(user);
    			for (BiConsumer<IGameObject, IGameObject> effect : effectList) {
    				effect.accept(user, obj);
    			}
    		}
    	}
    }*/
    @Override
    public ComponentType<Belongings> getType() {
        return ComponentTypes.BELONGINGS;
    }

    @Override
    public Component copy() {
        return new Belongings();
    }

}
