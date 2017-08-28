package view.gameplay.hud.hudgameplayelement;

import java.util.HashMap;
import java.util.Map;

import gameobject.ObjectInfo;
import goalstrategies.IGoal;
import javafx.scene.text.Text;
import model.GameInfo;
import model.GameStats;

/**
 * GoalGameplayElement
 * @author Yanbo Fang
 *
 */
public class GoalGameplayElement extends TextGameplayElement {

	private static final double OFFSET = 20;
	private Map<IGoal, Text> myGoalMap;
	private double myXPos;
	private double myYPos;

	public GoalGameplayElement(ObjectInfo hero, double xPos, double yPos, double height, double width, String type,
			String textColor, int textSize) {
		super(hero, xPos, yPos, height, width, type, textColor, textSize);
		myXPos = xPos;
		myYPos = yPos;
		myGoalMap = new HashMap<IGoal, Text>();
		this.getText().setText(this.getType() + ": ");
	}

	@Override
	public void update(GameInfo info, GameStats stats, IGoal goal) {
		if (goal != null) {
			if (!myGoalMap.containsKey(goal)) {
				Text t = this.createText(myXPos, myYPos + (myGoalMap.size() + 1) * OFFSET, this.getText().getFill(),
						this.getText().getFont());
				t.setText(goal.getStatus());
				myGoalMap.put(goal, t);
			} else {
				myGoalMap.get(goal).setText(goal.getStatus());
			}
		}
	}

}
