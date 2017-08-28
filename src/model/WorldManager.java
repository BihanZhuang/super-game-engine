package model;

import java.util.HashMap;
import java.util.Map;

public class WorldManager {

	private Map<Integer, World> myWorldsMap;
	private int myNumOfWorlds;
	private int myCurrentWorld;

	public WorldManager() {
		this(0);
	}

	public WorldManager(int worlds) {
		myNumOfWorlds = worlds;
		myWorldsMap = new HashMap<Integer, World>();
		myCurrentWorld = 0;
		addWorld(new World());
	}

	public void addWorld(World newWorld) {
		myWorldsMap.put(myNumOfWorlds++, newWorld);
	}
	
	public World getWorld(int id){
		return myWorldsMap.get(id);
	}

	//TODO: add exception when trying to remove the current world, Can't remove the current world
	public void removeWorld(int id) {
		if(id != myCurrentWorld){
			myWorldsMap.remove(id);
			myNumOfWorlds --;
		}
	}
	
	public void setCurrentWorld(int id){
		myCurrentWorld = id;
	}
	
	public int getCurrentWorld(){
		return myCurrentWorld;
	}
	
	public int getNumOfWorlds(){
		return myNumOfWorlds;
	}
}
