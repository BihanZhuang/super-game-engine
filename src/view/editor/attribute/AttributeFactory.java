package view.editor.attribute;

import java.util.ArrayList;
import java.util.Collection;

import gameobject.ObjectInfo;
import gameobject.component.type.ComponentTypes;

public class AttributeFactory {

	private ObjectInfo myObj;
	private Collection<AttributeSlider> mySliders;

	public AttributeFactory(ObjectInfo obj) {
		myObj = obj;
		mySliders = new ArrayList<AttributeSlider>();
	}

	public Collection<AttributeSlider> generateSlider(){	
		if(myObj.has(ComponentTypes.HEALTH)){
			AttributeSlider healthSlr = new AttributeSlider("Health",0,100);
			healthSlr.setValue(myObj.getComponent(ComponentTypes.HEALTH).getMaxHealth());
			mySliders.add(healthSlr);
			healthSlr.getSlider().setOnMouseReleased(e->{
				myObj.getComponent(ComponentTypes.HEALTH).setMaxHealth(healthSlr.getValue());
						});
		}
		if(myObj.has(ComponentTypes.ATTACK)){
			AttributeSlider rangeSlr = new AttributeSlider("Range",0,100);
			AttributeSlider damageSlr = new AttributeSlider("Damage",0,100);
			rangeSlr.setValue(myObj.getComponent(ComponentTypes.ATTACK).getRange());
			damageSlr.setValue(myObj.getComponent(ComponentTypes.ATTACK).getDamage());
			mySliders.add(rangeSlr);
			mySliders.add(damageSlr);
			rangeSlr.getSlider().setOnMouseReleased(e->{
				myObj.getComponent(ComponentTypes.ATTACK).setRange(rangeSlr.getValue());
						});
			damageSlr.getSlider().setOnMouseReleased(e->{
				myObj.getComponent(ComponentTypes.ATTACK).setDamage((damageSlr.getValue()));
						});

		}
		if(myObj.has(ComponentTypes.VELOCITY)){
			AttributeSlider speedSlr = new AttributeSlider("Speed",0.5,2);
			mySliders.add(speedSlr);
			speedSlr.getSlider().setOnMouseReleased(e->{
				myObj.getComponent(ComponentTypes.VELOCITY).setX(myObj.getComponent(ComponentTypes.VELOCITY).getX()*speedSlr.getValue());
				myObj.getComponent(ComponentTypes.VELOCITY).setY(myObj.getComponent(ComponentTypes.VELOCITY).getY()*speedSlr.getValue());
						});
		}
		if(myObj.has(ComponentTypes.MASS)){
			AttributeSlider massSlr = new AttributeSlider("Mass",1,10);
			mySliders.add(massSlr);
			massSlr.setValue(myObj.getComponent(ComponentTypes.MASS).getValue());
			massSlr.getSlider().setOnMouseReleased(e->{
				myObj.getComponent(ComponentTypes.MASS).setValue(massSlr.getValue());
						});
		}
		if(myObj.has(ComponentTypes.BONUSSCORE)){
			AttributeSlider bonusSlr = new AttributeSlider("Bonus Score",1,10);
			mySliders.add(bonusSlr);
			bonusSlr.setValue(myObj.getComponent(ComponentTypes.BONUSSCORE).getBonusScore());
			bonusSlr.getSlider().setOnMouseReleased(e->{
				myObj.getComponent(ComponentTypes.BONUSSCORE).setBonusScore(bonusSlr.getValue());
						});
		}
		if(myObj.has(ComponentTypes.AI)){
			AttributeSlider leftSlr = new AttributeSlider("Left Territory", 10,100);
			AttributeSlider rightSlr = new AttributeSlider("Right Territory", 10,100);
			AttributeSlider horSpeedSlr = new AttributeSlider("Horizontal Speed", 100,200);
			AttributeSlider verSpeedSlr = new AttributeSlider("Vertical Speed", 100,200);
			leftSlr.getSlider().setOnMouseReleased(e->{
				myObj.getComponent(ComponentTypes.AI).setLeft(leftSlr.getValue());
			});
			leftSlr.setValue(myObj.getComponent(ComponentTypes.AI).getLeft());
			rightSlr.getSlider().setOnMouseReleased(e->{
				myObj.getComponent(ComponentTypes.AI).setRight(rightSlr.getValue());
			});
			rightSlr.setValue(myObj.getComponent(ComponentTypes.AI).getRight());
			horSpeedSlr.getSlider().setOnMouseReleased(e->{
				myObj.getComponent(ComponentTypes.AI).setHSpeed(horSpeedSlr.getValue());
			});
			horSpeedSlr.setValue(myObj.getComponent(ComponentTypes.AI).getHSpeed());
			verSpeedSlr.getSlider().setOnMouseReleased(e->{
				myObj.getComponent(ComponentTypes.AI).setVSpeed(verSpeedSlr.getValue());
			});
			verSpeedSlr.setValue(myObj.getComponent(ComponentTypes.AI).getVSpeed());
			mySliders.add(leftSlr);
			mySliders.add(rightSlr);
			mySliders.add(horSpeedSlr);
			mySliders.add(verSpeedSlr);
		}
		return mySliders;
	}

}
