package model;

import util.Replicate;

/**
 * Interface for Level, contains information about the world
 * @author Yanbo Fang
 *
 */
public interface ILevel extends LevelInfo, Replicate<ILevel> {
    
	/**
	 * Remove World and Goals
	 */
    void clear();
	
    /**
     * Get the IWorld
     * @return IWorld
     */
    IWorld getWorld();
	
}
