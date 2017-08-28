package view.editor.menu.game;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.scene.control.Menu;
import model.GameInfo;
import model.IGame;
import view.IView;
import view.custom.MenuOptionList;

/**
 * 
 * @author Mike Liu
 *
 */
public class LevelOptionList implements IView<Menu> {
    
    public static final String TEXT = "Level %d";
    
    private MenuOptionList myOptionList;
    private Consumer<String> myToggleHandler;

    public LevelOptionList(IGame game) {
        myOptionList = new MenuOptionList("To Level");
        myToggleHandler = option -> {
            game.saveGame();
            int level = Integer.parseInt(option.substring(6));
            if(game.currentLevel() != level) {
                game.toLevel(level);
            }
        };
        game.addChangeObserver(this::update);
        update(game);
    }

    @Override
    public Menu getView() {
        return myOptionList.getView();
    }
    
    private void update(GameInfo game) {
        List<String> options = new ArrayList<>();
        for(int i = 1; i <= game.numLevels(); i++) {
            options.add(String.format(TEXT, i));
        }
        myOptionList.setOptions(options, String.format(TEXT, game.currentLevel()), myToggleHandler);
    }
}
