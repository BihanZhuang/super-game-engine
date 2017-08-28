package model;

import java.util.function.Consumer;

import gameobject.IGameObject;
import gameobject.component.Health;
import gameobject.component.type.ComponentTypes;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import system.SystemManager;
import util.Constants;

/**
 * Contains the back-end data and logic of the game and
 * game authoring environment.
 * @author Mike Liu
 *
 */
public class Model {

    public static final int DEFAULT_FPS = 30;
    
    private IGame myGame;
    private SystemManager myManager;
    private Timeline myTime;
    private Boundary myBoundary;
    
    public Model(Consumer<Boolean> endGameHandler) {
        IWorld world = new World();
        myGame = new Game(world);
        myManager = new SystemManager(world, myGame, endGameHandler);
        myTime = getTimeline();
        myBoundary = world.getBoundary();
    }
    
    public void play() {
        myTime.play();
    }
    
    public void pause() {
        myTime.pause();
    }
    
    public void step() {
        step(1.0 / DEFAULT_FPS);
    }
    
    /**
     * Performs the next step of the game given <code>elapsedTime</code> in seconds
     * @param elapsedTime
     */
    public void step(double elapsedTime) {
        myManager.step(elapsedTime);
        myGame.getWorld().forEach(obj -> {
        	IGameObject lite = obj.copy(obj.getID());
        	lite.removeAllComponents();
        	Health health = obj.getComponent(ComponentTypes.HEALTH);
        	if(health != null) {
        		lite.addComponent(health);
        	}
        	myGame.getWorld().notifyInfoObservers(lite);
        });
    }
    
    public IGame getGame() {
        return myGame;
    }
    
    public Boundary getBoundary() {
    	return myBoundary;
    }

    private Timeline getTimeline() {
        Timeline tl = new Timeline();
        tl.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.millis(Constants.MILLIS_PER_SECOND / DEFAULT_FPS),
                e -> step(1.0 / DEFAULT_FPS));
        tl.getKeyFrames().add(frame);
        return tl;
    }
    
}
