package view.gameplay.hud.hudgameplayelement;

import gameobject.ObjectInfo;
import goalstrategies.IGoal;
import model.GameInfo;
import model.GameStats;

/**
 * ScoreGameplayElement
 * @author Yanbo Fang
 *
 */
public class ScoreGameplayElement extends TextGameplayElement {

	public ScoreGameplayElement(ObjectInfo hero, double xPos, double yPos, double height, double width, String type,
			String textColor, int textSize) {
		super(hero, xPos, yPos, height, width, type, textColor, textSize);
		this.getText().setText(this.getType() + ": 0");
	}

	@Override
	public void update(GameInfo info, GameStats stats, IGoal goal) {
		if (stats != null) {
			this.getText().setText(this.getType() + ": " + Integer.toString(stats.getScore()));
		}
	}

}
