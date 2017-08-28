package system;

import java.util.HashMap;
import java.util.function.Consumer;

import engine.PhysicsEngine;
import model.IGame;
import model.IWorld;
import model.LevelInfo;
import model.WorldInfo;

public class SystemManager implements ISystemInfo {

	private HashMap<Integer, GameSystem> mySubsystem;
	private IWorld myWorld;
	private IGame myGame;
	private PhysicsEngine myEngine;
	private double systemTime;
	private int nextId;
	private Consumer<Boolean> myWinLoseOperation;

	public SystemManager(IWorld world, IGame game, Consumer<Boolean> operation) {
		mySubsystem = new HashMap<>();
		myGame = game;
        myWorld = world;
        myEngine = new PhysicsEngine(myWorld, name -> myWorld.getEnvironment().getConstant(name));
		systemTime = 0;
		nextId = 0;
		myWinLoseOperation = operation;

		addSystems(new AnimationSystem(this, myEngine), new BirthSystem(this, myEngine),
				new CollideSystem(this, myEngine), new DeathSystem(this, myEngine), new MoveSystem(this, myEngine),
				new AISystem(this, myEngine), new GoalSystem(this, myEngine));
	}

	public void step(double elapsedTime) {
		systemTime += elapsedTime;
		// System.out.println(systemTime);
		for (GameSystem subsystem : mySubsystem.values()) {
			subsystem.step(elapsedTime);
		}
	}

	@Override
	public WorldInfo getWorld() {
		return myWorld;
	}

	@Override
	public int getScore() {
		return myGame.getStats().getScore();
	}

	@Override
	public void addScore(int value) {
		myGame.getStats().addScore(value);
	}

	@Override
	public double systemTime() {
		return systemTime;
	}

	private void addSystems(GameSystem... systems) {
		for (GameSystem system : systems) {
			mySubsystem.put(nextId++, system);
			registerSystem(system);
		}
	}

	public void registerSystem(GameSystem system) {
		myWorld.forEach(obj -> obj.addObserver(system));
		myWorld.addObserver(system);
	}

	public void deregisterSystem(GameSystem system) {
		myWorld.forEach(obj -> obj.removeObserver(system));
		myWorld.removeObserver(system);
	}

	@Override
	public void setWin() {
		if (myGame.currentLevel() >= myGame.numLevels()) {
			myWinLoseOperation.accept(true);
		} else {
			myGame.toLevel(myGame.currentLevel() + 1);
		}
	}

	@Override
	public void setFail() {
		myWinLoseOperation.accept(false);
	}

	@Override
	public LevelInfo getGoalInfo() {
		return myGame.getLevelInfo();
	}

}
