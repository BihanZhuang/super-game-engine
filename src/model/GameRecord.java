package model;

import system.ControlBus;

public class GameRecord {

    private ILevel myLevel;
    private int currentLevel, myControlID;
    private GameStats myStats;
    private ControlBus myControl;
    
    public GameRecord(ILevel level, GameStats stats, ControlBus controlBus, int levelNum, int controlID) {
        myLevel = level;
        myStats = stats;
        myControl = controlBus;
        currentLevel = levelNum;
        myControlID = controlID;
    }
    
    public ILevel getLevel() {
        return myLevel;
    }
    
    public GameStats getStats() {
        return myStats;
    }
    
    public ControlBus getControlBus() {
        return myControl;
    }
    
    public int currentLevel() {
        return currentLevel;
    }
    
    public int getControlID() {
        return myControlID;
    }
    
}
