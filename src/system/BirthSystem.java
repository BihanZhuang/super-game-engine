package system;

import java.util.ArrayList;

import engine.PhysicsEngine;
import gameobject.IGameObject;
import gameobject.Template;
import gameobject.component.Generator;
import gameobject.component.type.ComponentTypes;

public class BirthSystem extends AbstractSystem {
    
	private ArrayList<Template> round;

    public BirthSystem(ISystemInfo info, PhysicsEngine engine) {
        super(info, engine, ComponentTypes.GENERATOR);
        this.round=new ArrayList<Template>();
    }

    @Override
    protected void singleStep(IGameObject obj, double elapsedTime) {
    	Generator g = obj.getComponent(ComponentTypes.GENERATOR);
        if (obj.getComponent(ComponentTypes.GENERATOR).check() && obj.getComponent(ComponentTypes.GENERATOR).getGenerateCnt()>0) {
            if (obj.getComponent(ComponentTypes.GENERATOR).getDeathTime()==Double.MAX_VALUE) {
            	obj.getComponent(ComponentTypes.GENERATOR).dieAt(this.getSystemInfo().systemTime());
            }
        	//System.out.println(this.getSystemInfo().systemTime()-obj.getComponent(ComponentTypes.GENERATOR).getDeathTime());
            //obj.getComponent(ComponentTypes.GENERATOR).dieAt(this.getSystemInfo().systemTime());
            if (g.getRebornCheck().test(obj, this.getSystemInfo().systemTime())) {
                g.setGenerate(true);
            }
        }
        if (g.getGenerate()) {
        	System.out.println("Birth Comes");
        	if (g.getGeneratePosX()==0 && g.getGeneratePosY()==0) {
        		g.setGeneratePosX(obj.getPosition().getX());
        		g.setGeneratePosY(obj.getPosition().getY());
        	}
        	Template newobj = obj.getComponent(ComponentTypes.GENERATOR).generate();
        	this.round.add(newobj);
            g.setGenerate(false);
            //System.out.println("I was Born");
            //g.setGenerateCheck(false);
        }
    }
    
    @Override
    public void step(double elapsedTime) {
        super.step(elapsedTime);
        round.stream().forEach(e->this.getSystemInfo().getWorld().addObject(e));
        round.clear();
    }


}
