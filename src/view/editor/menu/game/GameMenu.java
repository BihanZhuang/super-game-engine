package view.editor.menu.game;

import javafx.stage.Stage;
import model.IGame;
import util.DirectorySelector;
import util.view.ControlFactory;
import view.MenuView;

/**
 * 
 * @author
 * @author Mike Liu
 *
 */
public class GameMenu extends MenuView {

	public GameMenu(Stage stage, IGame game) {
		super("Game");
		DirectorySelector dirSelector = new DirectorySelector();
		addItems(ControlFactory.createMenuItem("Load Game", e -> dirSelector.showDialog(stage).ifPresent(file -> {
			game.loadGame(file);
		})), ControlFactory.createMenuItem("Save Game", e -> dirSelector.showDialog(stage).ifPresent(game::saveGame)),
				ControlFactory.createMenuItem("Add Level", e -> {
					game.addLevel();
				}), ControlFactory.createMenuItem("Add Goal", e -> new CustomGoalView(game)),
				new LevelOptionList(game).getView());
	}

}
