package view.gameplay;

import java.util.HashMap;
import java.util.Map;

import controller.HeroControl;
import gameobject.ObjectInfo;
import gameobject.Template;
import gameobject.component.type.ComponentTypes;
import goalstrategies.IGoal;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.ChampionPool;
import model.GameInfo;
import model.GameStats;
import model.HUDConfiguration;
import util.VoogaException;
import view.Sound;
import view.gameplay.hud.HUDLayerGameplay;

public class GameViewCore {
	
    public static final String MUSICFILE = "music/play.mp3";
    public static final String LOSEMUSIC = "music/Gameover.mp3";
    public static final String WINMUSIC = "music/win.mp3";

    private Pane worldView;
    private ScrollPane worldScroller;
    private StackPane viewStack;
    private ObjectInfo myHero;
    private Map<Integer, ObjectView> myObjects;
    private Bounds myBounds;
    private HUDLayerGameplay myHUDLayer;
    private Sound myMusic;
    private HeroControl myController;

    public GameViewCore(HeroControl controller) {
        myController = controller;
        worldView = createWorldView();
        worldScroller = createScrollPane(worldView);
        viewStack = createViewStack(worldScroller);
        myObjects = new HashMap<>();
        myMusic = new Sound(MUSICFILE);
        myMusic.play();
    }
    
    public Pane getView() {
        return viewStack;
    }
    
    public void attachKeyHandler(Scene scene) {
        scene.setOnKeyPressed(e -> myController.onKeyPressed(e.getCode()));
        scene.setOnKeyReleased(e -> myController.onKeyReleased(e.getCode()));
    }

    public Template chooseChampion(ChampionPool pool) {
        return new ChampionSelector(pool).show();
    }

    public void startGame(ObjectInfo champion, Bounds bounds) {
        if (!champion.has(ComponentTypes.CHAMPION)) {
            throw new VoogaException("Object is not champion");
        } else {
            myHero = champion;
//            myBounds = bounds;
//            worldView.setPrefWidth(bounds.getWidth());
//            worldView.setPrefHeight(bounds.getHeight());
//            myHUDLayer = new HUDLayerGameplay(myHero, myHUDConfig);
        }
    }

    /**
     * sets and loads the HUDConfiguration as a layer over the scrolling game
     * 
     * @param config
     */
    public void loadHUDConfig(HUDConfiguration config) {
        myHUDLayer = new HUDLayerGameplay(myHero, config);
        viewStack.getChildren().add(myHUDLayer.getPane());
    }

    public void update(ObjectInfo arg) {
        if (arg.expired()) {
            worldView.getChildren().remove(myObjects.get(arg.getID()).getView());
            myObjects.remove(arg.getID());
        } else if (!myObjects.containsKey(arg.getID())) {
            addObject(arg);
        } else {
            myObjects.get(arg.getID()).update(arg);
            // if(arg.getID() == myHero.getID()) {
            // hud.updateAllInfoViews();
            // }
        }
        if (myHero != null && arg.getID() == myHero.getID()) {
            scroll();
        }
        if (myHUDLayer != null) {
            myHUDLayer.updateWithGame(null, null, null);
        }
    }

    public void update(GameInfo arg) {
        if (myHUDLayer != null) {
            myHUDLayer.updateWithGame(arg, null, null);
        }
    }

    public void updateStats(GameStats arg) {
        if (myHUDLayer != null) {
            myHUDLayer.updateWithGame(null, arg, null);
        }
    }

    public void updateGoal(IGoal arg) {
        if (myHUDLayer != null) {
            myHUDLayer.updateWithGame(null, null, arg);
        }
    }
    
    public void end(boolean win) {
        if(win) {
            terminate("You Win!");
            myMusic.setMusic(WINMUSIC);
        } else {
            terminate("You failed :(");
            myMusic.setMusic(LOSEMUSIC);
        }
    }

    private Pane createWorldView() {
        Pane pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
        pane.setId("game-root");
        return pane;
    }

    private StackPane createViewStack(Node... children) {
        StackPane viewStack = new StackPane();
        viewStack.setMouseTransparent(true);
        viewStack.getChildren().addAll(children);
        return viewStack;
    }

    private void addObject(ObjectInfo obj) {
        ObjectView objectView = new ObjectView(obj);
        myObjects.put(obj.getID(), objectView);
        worldView.getChildren().add(objectView.getView());
    }

    private void terminate(String message) {
    	System.out.println("entered");
        Text winLose = new Text(message);
        worldView.getChildren().add(winLose);
        winLose.setFont(Font.font(40));
        winLose.setX(worldView.getWidth() / 2);
        winLose.setY(worldView.getHeight() / 2);
    }

    private ScrollPane createScrollPane(Pane worldView) {
        ScrollPane pane = new ScrollPane();
        pane.setContent(worldView);
        pane.setHbarPolicy(ScrollBarPolicy.NEVER);
        pane.setVbarPolicy(ScrollBarPolicy.NEVER);
        return pane;
    }

    private void scroll() {
        if (true) {
            // scroll horizontally
            worldScroller.setHvalue((myHero.getPosition().getX() - 0.5 * worldScroller.getViewportBounds().getWidth())
                    / (worldView.getWidth() - worldScroller.getViewportBounds().getWidth()));
            // scroll vertically
            worldScroller.setVvalue((myHero.getPosition().getY() - 0.9* worldScroller.getViewportBounds().getHeight())
                    / (worldView.getHeight() - worldScroller.getViewportBounds().getHeight()));
//           worldScroller.setVvalue((myHero.getPosition().getY() - worldScroller.getViewportBounds().getHeight()));
        }
    }
}
