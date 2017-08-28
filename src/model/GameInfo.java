package model;

import util.Observer;

public interface GameInfo {
	
	HUDConfiguration getHUDConfig();

    WorldInfo getWorld();
    
    LevelInfo getLevelInfo();

    GameStats getStats();
    
    int numLevels();

    int currentLevel();

    void toLevel(int level);
    
    ChampionPool getChampionPool();
    
    void addChangeObserver(Observer<GameInfo> observer);
    
    void removeChangeObserver(Observer<GameInfo> observer);

}