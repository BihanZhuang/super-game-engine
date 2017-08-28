package view.editor.componentwindow;

import java.util.ArrayList;
import java.util.List;

import engine.physicsStrategies.BrickStrategy;
import engine.physicsStrategies.CharacterStrategy;
import engine.physicsStrategies.NullVelocityStrategy;
import gameobject.basics.CollisionStrategy.InvulnerableStrategy;
import gameobject.basics.CollisionStrategy.ItemStrategy;
import gameobject.basics.CollisionStrategy.TouchGeneratorStrategy;
import gameobject.basics.GeneratorStrategy.OnetimeStrategy;
import gameobject.component.AI;
import gameobject.component.Actor;
import gameobject.component.Attack;
import gameobject.component.BonusScore;
import gameobject.component.Collider;
import gameobject.component.Component;
import gameobject.component.Dimension;
import gameobject.component.Generator;
import gameobject.component.Health;
import gameobject.component.Mass;
import gameobject.component.PhysicsBehavior;
import gameobject.component.Team;
import gameobject.component.Vector2D;
import javafx.scene.Node;
import view.editor.attribute.AttributeSlider;
import view.editor.imageselector.AnimationImageSelectorView;

public class ComponentController {
	public List<Node> displayContent;
	public ComponentData componentdata;
	
	// public ComponentFactory componentfactory;

	public ComponentController() {
		this.componentdata = new ComponentData();
		this.displayContent = new ArrayList<Node>();
		// this.componentfactory = new ComponentFactory();
	}

	public ComponentController(ComponentData cd) {
		this();
		this.componentdata = cd;
	}

	public void clear() {
		this.displayContent.clear();
	}

	public void initial(Field key, String value, String display) {
		this.componentdata.put(key, value);
		Node s = null;
		if (display.equals("Slider")) {
			s = new AttributeSlider(key.toString(), 0, Integer.parseInt(value) , componentdata).getView();
		}
		if (display.equals("KVP")) {
			s = new KeyValuePair(key.toString(), value, componentdata).getDisplayContent();
		}
		if (display.equals("ISB")) {
			s = new ImageSelectedButton(key.toString(), value, componentdata).getDisplayContent();
		}
		if (display.equals("CheckBox")) {
			s = new CheckPropertyBox(key.toString(), value, componentdata).getDisplayContent();
		}
		this.displayContent.add(s);
	}

	public void initial(Field key, List<String> value, String display) {
		this.componentdata.put(key, value.get(0));
		Node s = null;
		if (display.equals("SelectionBox")) {
			Selection temp = new Selection(key.toString(), value, this.componentdata);
			s = temp.getDisplayContent();
		}
		this.displayContent.add(s);
	}

/*	public void put(String key, String value) {
		this.componentdata.put(key, value);
	}

	public void remove(String key, String value) {
		this.componentdata.remove(key, value);
	}*/

	public Component produce(Component com, String type) {
		set(com, type);
		return com;
	}

	public void set(Component com, String type) {
		Double value;
		if (equal(type, Type.HEALTH)) {
			Health cur = (Health) com;
			value = Double.parseDouble(this.componentdata.get(Field.MAXHEALTH));
			cur.setMaxHealth(value);
			value = Double.parseDouble(this.componentdata.get(Field.CURHEALTH));
			cur.setCurrentHealth(value);
		}
		if (equal(type, Type.ATTACK)) {
			Attack cur = (Attack) com;
			value = Double.parseDouble(this.componentdata.get(Field.ATTACK));
			cur.setDamage(value);
			value = Double.parseDouble(this.componentdata.get(Field.RANGE));
			cur.setRange(value);
		}
		if (equal(type, Type.BONUSSCORE)) {
			BonusScore cur = (BonusScore) com;
			Integer intvalue = Integer.parseInt(this.componentdata.get(Field.BONUSSCORE));
			cur.setBonusScore(intvalue);
		}
		if (equal(type, Type.DIMENSION)) {
			Dimension cur = (Dimension) com;
			value = Double.parseDouble(this.componentdata.get(Field.WIDTH));
			cur.setX(value);
			value = Double.parseDouble(this.componentdata.get(Field.HEIGHT));
			cur.setY(value);
		}
		if (equal(type, Type.MASS)) {
			Mass cur = (Mass) com;
			value = Double.parseDouble(this.componentdata.get(Field.MASS));
			cur.setValue(value);
		}
		if (equal(type, Type.VELOCITY)) {
			Vector2D cur = (Vector2D) com;
			value = Double.parseDouble(this.componentdata.get(Field.VELOCITYX));
			cur.setX(value);
			value = Double.parseDouble(this.componentdata.get(Field.VELOCITYY));
			cur.setY(value);
		}
		if (equal(type, Type.COLLIDER)) {
			Collider cur = (Collider) com;
			if (equal(componentdata.get(Field.COLLISIONSTRATEGY), Type.ITEMSTRATEGY))
				cur.add(new ItemStrategy(componentdata.get(Field.ITEMIMAGENAME),
						Double.parseDouble(componentdata.get(Field.ITEMHEALTHINCREASE))));
			if (equal(componentdata.get(Field.COLLISIONSTRATEGY),Type.INVULNERABLESTRATEGY))
				cur.add(new InvulnerableStrategy());
			if (equal(componentdata.get(Field.COLLISIONSTRATEGY), Type.TOUCHGENERATORSTRATEGY))
				cur.add(new TouchGeneratorStrategy(componentdata.get(Field.POWERUPBIRCKIMAGENAME)));
		}
		if (equal(type, Type.GENERATOR)) {
			Generator cur = (Generator) com;
			if (equal(componentdata.get(Field.GENERATORSTRATEGY), Type.ONETIMESTRATEGY))
				cur.changeRebornCheck(new OnetimeStrategy(Double.parseDouble(componentdata.get(Field.GENERATEREBORNTIME))));
			cur.setGenerateDiffX(Double.parseDouble(componentdata.get(Field.GENERATEDIFFX)));
			cur.setGenerateDiffY(Double.parseDouble(componentdata.get(Field.GENERATEDIFFY)));
			cur.setGenerateCnt(Integer.parseInt(componentdata.get(Field.GENERATECNT)));
			cur.setRebornTime(Double.parseDouble(componentdata.get(Field.GENERATEREBORNTIME)));
		}
		if (equal(type, Type.AI)) {
			AI cur = (AI) com;
			cur.setLeft(Double.parseDouble(componentdata.get(Field.LEFTTERRITORY)));
			cur.setRight(Double.parseDouble(componentdata.get(Field.RIGHTTERRITORY)));
			cur.setHSpeed(Double.parseDouble(componentdata.get(Field.HORIZONTALSPEED)));
			cur.setVSpeed(Double.parseDouble(componentdata.get(Field.VERTICALSPEED)));
		}
		if (equal(type, Type.CHAMPION)) {
			Actor cur = (Actor) com;
			cur.setHSpeed(Double.parseDouble(componentdata.get(Field.CHAMPIONHORIZONTALSPEED)));
			cur.setVSpeed(Double.parseDouble(componentdata.get(Field.CHAMPIONVERTICALSPEED)));
		}
		if (equal(type, Type.PHYSICSBEHAVIOR)) {
			PhysicsBehavior cur = (PhysicsBehavior) com;
			boolean GravityOn = componentdata.get(Field.GRAVITYON).equals("1");
			boolean CollisionOn = componentdata.get(Field.COLLISIONON).equals("1");
			if (equal(componentdata.get(Field.PHYSICSBEHAVIOR), Type.CHARACTERSTRATEGY)) {
				//new CharacterStrategy()
				cur.setStrategy(new CharacterStrategy(GravityOn,CollisionOn, false));
			}
			if (equal(componentdata.get(Field.PHYSICSBEHAVIOR), Type.NULLVELOCITYSTRATEGY)) {
				cur.setStrategy(new NullVelocityStrategy(CollisionOn));
			}
			if (equal(componentdata.get(Field.PHYSICSBEHAVIOR), Type.BRICKSTRATEGY)) {
				cur.setStrategy(new BrickStrategy());
			}
		}
		if (equal(type, Type.TEAM)) {
			Team cur = (Team) com;
			cur.setTeamnumber(Integer.parseInt(componentdata.get(Field.TEAMNUMBER)));
		}
	}

	public List<Node> getDisplay(Component com, String type) {
		this.displayContent.clear();
		if (equal(type, Type.HEALTH)) {
			initial(Field.MAXHEALTH, "100", "Slider");
			initial(Field.CURHEALTH, "50", "Slider");
		}
		if (equal(type, Type.ATTACK)) {
			initial(Field.ATTACK, "60", "Slider");
			initial(Field.RANGE, "0", "Slider");
		}
		if (equal(type, Type.BONUSSCORE)) {
			initial(Field.BONUSSCORE, "100", "KVP");
		}
		if (equal(type, Type.DIMENSION)) {
			initial(Field.WIDTH, "100", "KVP");
			initial(Field.HEIGHT, "100", "KVP");
		}
		if (equal(type, Type.MASS)) {
			initial(Field.MASS, "100", "KVP");
		}
		if (equal(type, Type.VELOCITY)) {
			initial(Field.VELOCITYX, "0", "Slider");
			initial(Field.VELOCITYY, "100", "Slider");
		}
		if (equal(type, Type.COLLIDER)) {
			List<String> ls = new ArrayList<>();
			ls.add(Type.ITEMSTRATEGY.toString());
			//ls.add(Type.INVULNERABLESTRATEGY.toString());
			//ls.add(Type.BRICKSTRATEGY.toString());
			ls.add(Type.ENEMYSTRATEGY.toString());
			//ls.add(Type.TOUCHEXPIRESTRATEGY.toString());
			ls.add(Type.TOUCHGENERATORSTRATEGY.toString());
			initial(Field.COLLISIONSTRATEGY, ls, "SelectionBox");
		}
		if (type.equals(Type.GENERATOR)) {
			List<String> ls = new ArrayList<>();
			ls.add(Type.ONETIMESTRATEGY.toString());
			initial(Field.GENERATORSTRATEGY, ls, "SelectionBox");
			// initial("GenerateImag")
		}
		if (equal(type, Type.ANIMATION)) {
			AnimationImageSelectorView a =new AnimationImageSelectorView(com);
			this.displayContent.add(a.getGridPane());
		}
		if (equal(type, Type.AI)) {
			initial(Field.LEFTTERRITORY, "100", "KVP");
			initial(Field.RIGHTTERRITORY, "100", "KVP");
			initial(Field.HORIZONTALSPEED, "20", "KVP");
			initial(Field.VERTICALSPEED, "20", "KVP");
		}
		if (equal(type, Type.ANIMATION)) {
			initial(Field.CHAMPIONHORIZONTALSPEED, "100", "KVP");
			initial(Field.CHAMPIONVERTICALSPEED, "200", "KVP");
		}
		if (equal(type, Type.PHYSICSBEHAVIOR)) {
			List<String> ls = new ArrayList<>();
			ls.add(Type.CHARACTERSTRATEGY.toString());
			ls.add(Type.NULLVELOCITYSTRATEGY.toString());
			ls.add(Type.BRICKSTRATEGY.toString());
			initial(Field.PHYSICSBEHAVIOR, ls, "SelectionBox");
		}
		if (equal(type, Type.TEAM)) {
			initial(Field.TEAMNUMBER,"0", "KVP");
		}
		return this.displayContent;
	}
	public boolean equal(String a, Type b) {
		return a.compareTo(b.toString())==0;
	}
	public List<Node> getDisplay(String type) {
		this.displayContent.clear();
		if (equal(type, Type.TOUCHGENERATORSTRATEGY)) {
			initial(Field.POWERUPBIRCKIMAGENAME, "", "ISB");
		}
		if (equal(type, Type.ITEMSTRATEGY)) {
			initial(Field.ITEMIMAGENAME, "", "ISB");
			initial(Field.ITEMHEALTHINCREASE, "100", "Slider");
		}
		if (equal(type, Type.ONETIMESTRATEGY)) {
			initial(Field.GENERATEDIFFX, "100", "Slider");
			initial(Field.GENERATEDIFFY, "100", "Slider");
			initial(Field.GENERATECNT, "10", "Slider");
			initial(Field.GENERATEREBORNTIME, "10", "Slider");
		}
		if (equal(type, Type.CHARACTERSTRATEGY) || equal(type, Type.NULLVELOCITYSTRATEGY) || equal(type, Type.BRICKSTRATEGY)) {
			initial(Field.GRAVITYON, "0","CheckBox");
			initial(Field.COLLISIONON, "0","CheckBox");
		}
		return this.displayContent;
	}
	
	private boolean equal(String a, Field b) {
		return (a.compareTo(b.toString())==0);
	}
}
