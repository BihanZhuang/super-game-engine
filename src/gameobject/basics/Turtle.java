package gameobject.basics;

import gameobject.IGameObject;
import gameobject.basics.GeneratorStrategy.OnetimeStrategy;
import gameobject.component.Attack;
import gameobject.component.Generator;
import gameobject.component.Health;
import gameobject.component.type.ComponentTypes;

@SuppressWarnings("serial")
public class Turtle extends Enemy {
    
	public Turtle(String imagePath) {
		super("Turtle", imagePath);
		this.addComponents(
				new Generator()
		);
		Generator g = this.getComponent(ComponentTypes.GENERATOR);
		IGameObject obj = new Shell("Shell.png").newInstance(0);
		obj.getComponent(ComponentTypes.AI).setLeft(200);
		obj.getComponent(ComponentTypes.AI).setRight(200);
		g.setTemplate(obj);
		g.changeRebornCheck(new OnetimeStrategy(-1));
		g.setGenerateCnt(1);
		//g.setGenerateCheck(false);
		//g.setGenerate(false);
		
		Health h = this.getComponent(ComponentTypes.HEALTH);
		h.setMaxHealth(100);
		
		Attack a = this.getComponent(ComponentTypes.ATTACK);
		a.setDamage(100.0);
		a.setRange(0);
	}
	
}
