package system;

import model.LevelInfo;
import model.WorldInfo;

public interface ISystemInfo {

    WorldInfo getWorld();

    int getScore();

    void addScore(int value);

    double systemTime();
    
    void setWin();
    
    void setFail();
    
    LevelInfo getGoalInfo();
}
