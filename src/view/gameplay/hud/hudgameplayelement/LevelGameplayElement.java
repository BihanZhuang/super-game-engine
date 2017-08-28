package view.gameplay.hud.hudgameplayelement;

import gameobject.ObjectInfo;

import goalstrategies.IGoal;
import model.GameInfo;
import model.GameStats;

/**
 * LevelGameplayElement
 * @author Yanbo Fang
 *
 */
public class LevelGameplayElement extends TextGameplayElement {

	public LevelGameplayElement(ObjectInfo hero, double xPos, double yPos, double height, double width, String type,
			String textColor, int textSize) {
		super(hero, xPos, yPos, height, width, type, textColor, textSize);
		this.getText().setText(this.getType() + ": 1");
	}

	@Override
	public void update(GameInfo info, GameStats stats, IGoal goal) {
		if (info != null) {
			this.getText().setText(this.getType()+ ": " + Integer.toString(info.currentLevel()));
		}
	}

}
